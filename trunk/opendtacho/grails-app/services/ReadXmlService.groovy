class ReadXmlService implements org.opendtacho.serviceinterfaces.ReadXmlServiceInt {

    // This must not be transactional, because then all database modifications would be done finally in one transaction.
    // The desired behaviour is that we want to save objects sequentially for each object type. 
    boolean transactional = false
//    static scope = 'session'

    def insertUpdateService

    /* Next steps:
       - driver with driving license
       - relation driver2card
       - activity changes
     */

    void loadXmlFile(String fileName) {
//        assert(insertUpdateService != null) // ??? this line was necessary for the program to run without error ???
        
        File file = new File(fileName)

        // Parse the complete XML file
        def content = new XmlSlurper().parse(file)

//        content.children().each {
//            println it.name()
//        }

        // TODO: implement driver identification, use direct lookups on tables (implement in helper methods)
        // Driver identification:
        // - first level: driving licence
        // - second level: name, birthdate of driver
        // - third level: name, birthdate of a person (need to create driver then)

        // ---------------------------------------------------------------------------------------------------------
        // EF_Driving_Licence_Info

        def cardDrivingLicenceInformation = content.EF_Driving_Licence_Info.cardDrivingLicenceInformation

        // ---------------------------------------------------------------------------------------------------------
        // EF_Identification

        def driverCardHolderIdentification = content.EF_Identification.driverCardHolderIdentification
        def dtDriver = new DtDriver(driverCardHolderIdentification, cardDrivingLicenceInformation)
        dtDriver = insertUpdateService.addObject(dtDriver)
        insertUpdateService.saveAll(DtDriver)

        def cardIdentification = content.EF_Identification.cardIdentification
        def dtCard = new DtCard(cardIdentification)
        dtCard = insertUpdateService.addObject(dtCard)
        insertUpdateService.saveAll(DtCard)

        def dtCard2driver = new DtCard2driver(dtCard, dtDriver, dtCard.cardValidityBegin, null)
        insertUpdateService.addObject(dtCard2driver)
        insertUpdateService.saveAll(DtCard2driver)

        // TODO: check for other entries in Crad2Driver with other startdate. If one exists, then set enddate to startdate of current card

        // ---------------------------------------------------------------------------------------------------------
        // EF_Vehicles_Used

        def cardVehicleRecords = content.EF_Vehicles_Used.cardVehiclesUsed.cardVehicleRecords

        // first loop to have all vehicles
        cardVehicleRecords.children().each { cardVehicleRecord ->
            def dtVehicle = new DtVehicle(cardVehicleRecord.vehicleRegistration)
            insertUpdateService.addObject(dtVehicle)
        }

        insertUpdateService.saveAll(DtVehicle)

        // ###
        def vehicleUseKeyValues = [card:dtCard]
        insertUpdateService.prefetchObjects(DtVehicleUse, vehicleUseKeyValues)

        // second loop for vehicle uses
        cardVehicleRecords.children().each { cardVehicleRecord ->
            def vehicleKeyValues = [
                    vehicleRegistrationNation:cardVehicleRecord.vehicleRegistration.vehicleRegistrationNation as String,
                    vehicleRegistrationNumber:cardVehicleRecord.vehicleRegistration.vehicleRegistrationNumber.vehicleRegNumber as String
            ]
            def dtVehicle = insertUpdateService.getObject(DtVehicle, vehicleKeyValues)
            def dtVehicleUse = new DtVehicleUse(dtCard, dtVehicle, cardVehicleRecord)
            insertUpdateService.addObject(dtVehicleUse)
        }

        insertUpdateService.saveAll(DtVehicleUse)

        // ---------------------------------------------------------------------------------------------------------
        // EF_Events_Data

        def cardEventRecords = content.EF_Events_Data.cardEventData.cardEventRecords
        cardEventRecords.children().each { cardEventRecord ->
            def vehicleKeyValues = [
                    vehicleRegistrationNation:cardEventRecord.eventVehicleRegistration.vehicleRegistrationNation as String,
                    vehicleRegistrationNumber:cardEventRecord.eventVehicleRegistration.vehicleRegistrationNumber.vehicleRegNumber as String
            ]
            def dtVehicle = insertUpdateService.getObject(DtVehicle, vehicleKeyValues)
            //println dtVehicle
            def dtEvent = new DtEvent(dtCard, dtVehicle, cardEventRecord)
            dtEvent = insertUpdateService.addObject(dtEvent)
            //println dtEvent
        }

        insertUpdateService.saveAll(DtEvent)

        // ---------------------------------------------------------------------------------------------------------
        // EF_Driver_Activity_Data

        // ###
        def activityChangeKeyValues = [driver:dtDriver]
        insertUpdateService.prefetchObjects(DtActivityChange, activityChangeKeyValues)

        def cardActivityDailyRecords = content.EF_Driver_Activity_Data.cardDriverActivity.cardActivityDailyRecords
        cardActivityDailyRecords.children().each { cardActivityDailyRecord ->

            // Get vehicle directly from database (performance could eventually be better, if vehicle is fetched from InsertUpdateService)
            // Use vehicle-use-entries (same card, look for same counter value than cardActivityDailyRecord)
            // TODO: check for validity or change this to use cardIWs, seems to be more accurate
//            def dtVehicleUse = DtVehicleUse.findByCardAndVuDataBlockCounter(
//                    dtCard,
//                    Integer.parseInt(cardActivityDailyRecord.activityDailyPresenceCounter as String)
//            )
            vehicleUseKeyValues = [
                    card:dtCard,
                    vuDataBlockCounter:Integer.parseInt(cardActivityDailyRecord.activityDailyPresenceCounter as String)
            ]
            def dtVehicleUse = insertUpdateService.getObject(DtVehicleUse, vehicleUseKeyValues, false)
            //println dtVehicleUse

            if(dtVehicleUse) {
                cardActivityDailyRecord.activityChangeInfos.children().each { activityChangeInfo ->
                    def dtVehicle = dtVehicleUse.vehicle
                    def dtActivityChange = new DtActivityChange(dtVehicle, dtDriver, cardActivityDailyRecord.activityRecordDate, activityChangeInfo)
                    dtActivityChange = insertUpdateService.addObject(dtActivityChange)
                    //println dtActivityChange
                }
            }
            else
                log.warn("Missing vehicle-use for query '$vehicleUseKeyValues'")
        }

        insertUpdateService.saveAll(DtActivityChange)

        // ---------------------------------------------------------------------------------------------------------
        // Cleanup

        insertUpdateService.cleanup()
    }

}
