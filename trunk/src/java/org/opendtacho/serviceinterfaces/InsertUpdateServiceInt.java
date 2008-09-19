package org.opendtacho.serviceinterfaces;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: meberspaecher
 * Date: 03.08.2008
 * Time: 20:44:26
 * To change this template use File | Settings | File Templates.
 */
public interface InsertUpdateServiceInt {
    void prefetchObjects(Class objectType, HashMap keys);
    Object addObject(Object object);
    Object getObject(Class objectType, HashMap keys);
    Object getObject(Class objectType, HashMap keys, boolean useDomainKeys);
    void cleanup();
    void saveAll(Class objectType);
}
