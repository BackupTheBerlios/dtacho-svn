import java.text.SimpleDateFormat
import org.springframework.transaction.PlatformTransactionManager

/**
 * Insert/Update-Service
 *
 * This service is used during data import (e.g. reading of XML card files).
 * It locally stores objects of different (arbitrary) grails domain classes, compares them against
 * already existing list members and database content.
 *
 * Objectives:
 * - minimize database read/write
 * - use as much bulk database access, instead of single row access
 * - detect duplicates and avoid constraint violations during database insert
 *
 * Usage:
 * - Use 'addObject' to add objects to lists. This identifies all new and modified objects.
 * - After all objects of one type have been written, use 'saveAll' to write new and modified objects to database.
 *   This method must be called for referenced objects before calling it for referencing objects!
 * - All objects written during the lifetime of this service are available to be referenced by other objects.
 *   Use 'getObject' to retrieve objects formerly written by 'addObject'.
 * - Use 'cleanup' to wipe all stored information. Then the service can be re-used from scratch.
 * - Use 'prefetchObjects' to fetch objects of one type as one batch from database.
 *   Should be called before first 'addObject' of a type. This limits requests to database when adding individual objects.
 *
 * Prerequisites:
 * - Define property 'domainKeys' for identifying attributes as list of property names for all domain classes
 *   that have a limited set of identifying attributes.
 *
 * @author meberspaecher
 */
class InsertUpdateService implements org.opendtacho.serviceinterfaces.InsertUpdateServiceInt {

    // This service is NOT transactional, because then in each method that does not work on the database, a commit is done.
    // This seems to be a bug...
    // Instead, an explicit transaction is used when saving multiple objects.
    boolean transactional = false
//    static scope = 'session'

    PlatformTransactionManager transactionManager

    // Maps to hold other maps (one per object type) with objects created from application
    private def objectEqualMaps = [:]
    private def objectsEqualDomainKeysMaps = [:]

    // Maps to hold other maps (one per object type) with objects fre-fetched from database by application
    private def prefetchedObjectEqualMaps = [:]
    private def prefetchedObjectEqualDomainKeysMaps = [:]

    // A map of sets of objects to be saved to DB, one per object type
    private def objectSaveSets = [:]

    // Set of property names that shall not be used for object comparison or attribute copy
    private static def domainClassIgnoredProperties = [
            'id','version','log','dateCreated','lastUpdated','class','hasMany','belongsTo','constraints','events','errors','metaClass',
            // the following are project specific properties
            'domainKeys','sdf'
    ] as Set

    static SimpleDateFormat sdf = new SimpleDateFormat('yyyy-MM-dd HH:mm:ss z')

    /**
     * Add an object to a type-specific in-memory collection of other objects.
     * If an object is not yet contained in the collection, it is fetched from DB and added to the collection.
     * To avoid single-row requests to the database, a bunch of objects may be pre-fetched before.
     * If it not exists in DB, it is created and added to the collection.
     * If an object already exists in the collection with same domain keys, then the modified attributes are updated.
     *
     * @return Consolidated object (if an already existing object is found, this is retruned and this is NOT the same object than 'newObject').
     **/
    def addObject(Object newObject) {
        assert(newObject)

        log.debug('addObject: adding object: ' + newObject)

        def domainType = newObject.class
        ensureObjectCollections(domainType)

        // Derive key values
        def allAttributesKey = getAllAttributesKey(newObject)
        def domainKeysAttributesKey = getDomainKeysAttributesKey(newObject)

        // Check whether object is already in list
        def currentByAllKeys = objectEqualMaps[domainType][allAttributesKey]
        def currentByDomainKeys = objectsEqualDomainKeysMaps[domainType][domainKeysAttributesKey]

        if(!currentByDomainKeys && !currentByAllKeys) {
            // object is not yet in memory, check in prefetched lists and database
            def isPrefetched = false
            def dbObject = fetchObjectFromPrefetchedObjects(domainType, allAttributesKey, domainKeysAttributesKey)
            if(dbObject)
                isPrefetched = true
            else
                dbObject = getObjectFromDb(domainType, newObject)

            if(dbObject) {
                // object exists in DB, check for updated values
                def dbObjAllAttributesKey = getAllAttributesKey(dbObject)
                if(allAttributesKey == dbObjAllAttributesKey) {
                    // object exists in DB with same attribute values, add to list
                    if(isPrefetched) log.debug('In-memory add: Object new in list, found in prefetched list: ' + dbObject)
                    else             log.debug('In-memory add: Object new in list, found in DB: ' + dbObject)
                    objectEqualMaps[domainType][allAttributesKey] = dbObject
                    if(domainKeysAttributesKey)
                        objectsEqualDomainKeysMaps[domainType][domainKeysAttributesKey] = dbObject
                }
                else {
                    // object exists in DB with different attributes, add to list
                    if(isPrefetched) log.debug('In-memory add: Object new in list, found in prefetched list, will be updated: ' + dbObject)
                    else             log.debug('In-memory add: Object new in list, found in DB, will be updated: ' + dbObject)
                    updateAttributes(dbObject, newObject)
                    objectEqualMaps[domainType][allAttributesKey] = dbObject
                    if(domainKeysAttributesKey)
                        objectsEqualDomainKeysMaps[domainType][domainKeysAttributesKey] = dbObject
                    objectSaveSets[domainType] << dbObject
                }

                return dbObject
            }
            else {
                // object is new, add to list
                log.debug('In-memory add: Object new in list, not in DB: ' + newObject)
                objectEqualMaps[domainType][allAttributesKey] = newObject
                if(domainKeysAttributesKey)
                    objectsEqualDomainKeysMaps[domainType][domainKeysAttributesKey] = newObject
                objectSaveSets[domainType] << newObject

                return newObject
            }
        }
        else {
            // object is in list, check for updated values
            if(currentByAllKeys) {
                // identical objects, do nothing
                log.debug('In-memory add: Object already in list: ' + currentByAllKeys)

                return currentByAllKeys
            }
            else {
                // objects differ, update current object with new attributes, add to set with objects to be saved
                log.debug('In-memory add: Object in list will be updated: ' + currentByDomainKeys)

                def oldAllAttributesKey = getAllAttributesKey(currentByDomainKeys)
                objectEqualMaps[domainType].remove(oldAllAttributesKey)

                updateAttributes(currentByDomainKeys, newObject)
                objectEqualMaps[domainType][allAttributesKey] = currentByDomainKeys
                objectSaveSets[domainType] << currentByDomainKeys

                return currentByDomainKeys
            }
        }
    }


    /**
     * Get object of specified type from list.
     * Use domain keys for comparison. This method requires existence of domain keys.
     * @param domainType Identifies domain type of object
     * @param keyValues Key/value-pairs for domain keys
     * @return domain object or null, if none exists for given keys
     */
    def getObject(Class domainType, HashMap keyValues) {
        if(!staticHasDomainKeys(domainType))
            throw new Exception("Missing property 'domainKeys' for class $domainType")

        def domainKeys = domainType['domainKeys']
        assert(domainKeys.size() == keyValues.size())

        def objectMap = objectsEqualDomainKeysMaps[domainType]
        assert objectMap

        // create key for lookup in map of current objects in memory
        StringBuffer sb = new StringBuffer()
        keyValues.keySet().sort().each { key ->
            def value = keyValues[key]
            sb.append(value.toString()).append('|')
        }

        def res = objectMap[sb.toString()]
        if (res)
            assert res.class == domainType

        return res
    }


    /**
     * Get object of specified type from list.
     * @param domainType Identifies domain type of object
     * @param keyValues Key/value-pairs for query
     * @param useDomainKeys if 'true', then use domain keys for comparison
     * @return domain object or null, if none exists for given keys
     */
    def getObject(Class domainType, HashMap keyValues, boolean useDomainKeys) {
        if(useDomainKeys)
            return getObjectFromDb(domainType, keyValues)

        // we need to iterate over all known objects, because in this case no map can be used for access by key
        def objectMap = objectsEqualDomainKeysMaps[domainType]
        def objectSet = objectMap.entrySet()
        assert objectSet
        assert keyValues

        def entry = objectSet.find { item ->
            compareAttributes(item.value, keyValues)
        }
        if(entry?.value)
            assert entry.value.class == domainType

        return entry?.value
    }


    /**
     * Save objects of specified type to database
     */
    void saveAll(Class domainType) {
        assert(domainType)
        
        def objectSet = objectSaveSets[domainType]

        if(objectSet) {
            log.info("Now saving ${objectSet.size()} objects of type: $domainType")

            def transactionStatus = transactionManager.getTransaction()

            try {
                objectSet.each {object ->
                    log.debug("About to save $object")

                    if (!object.save(/*flush:true*/))
                        throw new Exception('Could not save object: ' + object + '(Cause: ' + object.errors.allErrors + ')')
                }
                transactionManager.commit(transactionStatus)
            }
            catch(Exception e) {
                transactionManager.rollback(transactionStatus)
                throw e
            }
        }
        else
            log.info('Nothing to save (no objects found) for: ' + domainType)
    }


    /**
     * Pre-fetch objects of specified type from database.
     * @param domainType Type of objects
     * @param keyValues map with key/value pairs to limit data fetched from database
     */
    void prefetchObjects(Class domainType, HashMap keyValues) {
        def query = {
             keyValues.each { key, value ->
                 if(value != null)
                     eq(key, value)
                 else
                     isNull(key)
             }
        }

        def criteria = domainType.createCriteria()
        def dbObjects = criteria.list(query)

        ensureObjectCollections(domainType)

        dbObjects.each { object ->
            def allAttributesKey = getAllAttributesKey(object)
            def domainKeysAttributesKey = getDomainKeysAttributesKey(object)
            prefetchedObjectEqualMaps[domainType][allAttributesKey] = object
            prefetchedObjectEqualDomainKeysMaps[domainType][domainKeysAttributesKey] = object
        }

        log.info("Prefetched ${dbObjects.size()} objects of type: $domainType")
    }

    
    /**
     * Cleanup in-memory objects of service instance
     */
    void cleanup() {
        log.info('Cleanup called')

        objectEqualMaps = [:]
        objectsEqualDomainKeysMaps = [:]
        prefetchedObjectEqualMaps = [:]
        prefetchedObjectEqualDomainKeysMaps = [:]
        objectSaveSets = [:]
    }


    /**
     *
     */
    private fetchObjectFromPrefetchedObjects(Class domainType, allAttributesKey, domainKeysAttributesKey) {
        def domainKeys
        if(staticHasDomainKeys(domainType))
            domainKeys = domainType['domainKeys']

        def dbObject

        // Different handling for types with/without defined domain keys
        if(domainKeys) {
            dbObject = prefetchedObjectEqualDomainKeysMaps[domainType][domainKeysAttributesKey]
            if(dbObject)
                prefetchedObjectEqualDomainKeysMaps[domainType].remove(domainKeysAttributesKey)
        }
        else {
            dbObject = prefetchedObjectEqualMaps[domainType][allAttributesKey]
            if(dbObject)
                prefetchedObjectEqualMaps[domainType].remove(allAttributesKey)
        }

        return dbObject
    }


    /**
     * Try to retrieve object from DB.
     * Different handling for domain classes with and without domainKeys.
     */
    private getObjectFromDb(Class domainType, Object example) {
        def domainKeys
        if(hasDomainKeys(example))
            domainKeys = example['domainKeys']

        def dbObject

        // Different handling for types with/without defined domain keys
        if(domainKeys) {
            // Query by criteria
            def query = {
                domainKeys.each { keyName ->
                    if(example[keyName] != null)
                        eq(keyName, example[keyName])
                    else
                        isNull(keyName)
                }
            }

            def criteria = domainType.createCriteria()
            dbObject = criteria.get(query)
        }
        else {
            // Query by example
            dbObject = domainType.find(example)
        }

        return dbObject
    }


    /**
     * Get object from type-dependent list
     * Return null, if it does not yet exist
     */
    private xx_getFromList(Class domainType, Object object) {

        objectLists[domainType]?.find { item ->
            boolean res

            // Compare by domain key if it exists. Else compare by all attributes.
            if(hasDomainKeys(item))
                res = isSameDomainKey(item, object)
            else
                res = item.equals(object)

            return res
        }
    }


    /**
     * Ensure that object lists for specified object type exist
     */
    private void ensureObjectCollections(Class domainType) {
        def objectMap = objectEqualMaps[domainType]
        if(! objectMap) {
            objectMap = [:]
            objectEqualMaps[domainType] = objectMap
        }

        objectMap = objectsEqualDomainKeysMaps[domainType]
        if(! objectMap) {
            objectMap = [:]
            objectsEqualDomainKeysMaps[domainType] = objectMap
        }

        objectMap = prefetchedObjectEqualMaps[domainType]
        if(! objectMap) {
            objectMap = [:]
            prefetchedObjectEqualMaps[domainType] = objectMap
        }

        objectMap = prefetchedObjectEqualDomainKeysMaps[domainType]
        if(! objectMap) {
            objectMap = [:]
            prefetchedObjectEqualDomainKeysMaps[domainType] = objectMap
        }

        def objectSet = objectSaveSets[domainType]
        if(! objectSet) {
            objectSet = [] as Set
            objectSaveSets[domainType] = objectSet
        }
    }


    /**
     * Check existence of a domain key definition for object
     */
    private boolean hasDomainKeys(object) {
        if(object.properties['domainKeys'] != null)
            return true
        else
            return false
    }


    private boolean staticHasDomainKeys(domainType) {
        def declareFieldNames = domainType.properties.declaredFields.name as List
        if(declareFieldNames.grep('domainKeys'))
            return true
        else
            return false
    }


    /**
     * Compare two objects by domain key values
     * @return true, if both objects have same domain key values
     */
    private boolean xx_isSameDomainKey(left, right) {
        // Get domain keys
        def domainKeys = left['domainKeys']
        if(domainKeys == null)
            // object has no domain key definition
            return false

        // Get and compare domain key values
        boolean equalKeys = true
        domainKeys.each { keyName ->
            def leftKeyValue = left[keyName]
            def rightKeyValue = right[keyName]
            if(leftKeyValue == null || rightKeyValue == null)
                // no key attribute in object
                equalKeys = false

            if(!leftKeyValue.equals(rightKeyValue))
                equalKeys = false
        }

        return equalKeys
    }


    /**
     * Compare domain keys of an object with given key values
     */
    private boolean xx_compareDomainKeyValues(object, domainKeys, keyValues) {
        // Get and compare domain key values
        boolean equalKeys = true
        domainKeys.each { keyName ->
            def leftKeyValue = object[keyName]
            def rightKeyValue = keyValues[keyName]

            if(leftKeyValue == null) {
                // Special case 'null'
                if(rightKeyValue != null)
                    equalKeys = false
            }
            else {
                if(!leftKeyValue.equals(rightKeyValue))
                    equalKeys = false
            }
        }

        return equalKeys
    }


    /**
     * Update attributes of an existing object by values of a new object
     */
    private void updateAttributes(target, source) {
        source.properties.each { key, newValue ->
            if(! domainClassIgnoredProperties.grep(key)) {
                if(target[key] != newValue)
                    target[key] = newValue
            }
        }
    }


    /**
     * Compare two objects by attributes, ignore domain class special attributes like ID
     * @return true, if both objects have same attributes
     */
    private boolean xx_compareAttributes(left, right) {
        assert left.class == right.class

        boolean res = true
        left.properties.each { key, leftValue ->
            if(! domainClassIgnoredProperties.grep(key)) {
                def rightValue = right.properties[key]
                if(leftValue != null) {
                    if(!leftValue.equals(rightValue))
                        res = false
                }
                else {
                    // Special case 'null'
                    if(!rightValue == null)
                        res = false
                }
            }
        }

        return res;
    }


    /**
     * Compare object with specified set of key/value pairs
     * @return true, if both objects have same attributes
     */
    private boolean compareAttributes(left, HashMap keyValues) {
        boolean res = true
        keyValues.each { key, rightValue ->
            def leftValue = left.properties[key]
            if(leftValue != null) {
                if(!leftValue.equals(rightValue))
                    res = false
            }
            else {
                // Special case 'null'
                if(!rightValue == null)
                    res = false
            }
        }

        return res;
    }


    /**
     * Create unique key with all domain attributes of an object
     */
    private def getAllAttributesKey(object) {
        StringBuffer sb = new StringBuffer()

        def objProps = object.properties
        def objHasManyProps = objProps['hasMany']?.keySet()?.toList()

        def interestingKeys = objProps.keySet() - domainClassIgnoredProperties - objHasManyProps
        interestingKeys.sort().each { key ->
            def value = objProps[key]
            def valueString

            // special handling of some types to ensure unique formatting
            if(value instanceof Date)
                // problem were differences in toString() of Date and SqlTimestamp
                valueString = sdf.format(value as Date)
            else
                valueString = value?.toString()

            sb.append(valueString).append('|')
        }

        return sb.toString()
    }


    /**
     * Create unique key with all domain key attributes of an object
     */
    private def getDomainKeysAttributesKey(object) {
        if(!hasDomainKeys(object))
            return null

        StringBuffer sb = new StringBuffer()

        object.domainKeys.sort().each { key ->
            def value = object[key]
            def valueString

            // special handling of some types to ensure unique formatting
            if(value instanceof Date)
                // problem were differences in toString() of Date and SqlTimestamp
                valueString = sdf.format(value as Date)
            else
                valueString = value?.toString()

            sb.append(valueString).append('|')
        }

        return sb.toString()
    }
}
