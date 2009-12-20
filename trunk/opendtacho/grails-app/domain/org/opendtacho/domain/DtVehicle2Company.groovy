package org.opendtacho.domain

import java.text.SimpleDateFormat

class DtVehicle2Company {

    static belongsTo = [DtCompany,DtVehicle]

    // References
    DtCompany company
    DtVehicle vehicle

    // Data
    Date startdate
    Date enddate

    static domainKeys = ['company','vehicle','startdate','enddate']

    static constraints = {
        enddate(nullable:true)
    }

    private static SimpleDateFormat sdf = new SimpleDateFormat('yyyy-MM-dd HH:mm:ss z')


    DtVehicle2Company() {
    }
}
