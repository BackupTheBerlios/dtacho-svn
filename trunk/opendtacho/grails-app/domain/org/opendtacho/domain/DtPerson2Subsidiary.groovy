package org.opendtacho.domain

import java.text.SimpleDateFormat

class DtPerson2Subsidiary {
    static belongsTo = [DtPerson,DtSubsidiary]

    // References
    DtPerson person
    DtSubsidiary subsidiary

    // Data
    Date startdate
    Date enddate

    static domainKeys = ['person','subsidiary','startdate','enddate']

    static constraints = {
        enddate(nullable:true)
        person(nullable:true)
        subsidiary(nullable:true)
    }

    private static SimpleDateFormat sdf = new SimpleDateFormat('yyyy-MM-dd HH:mm:ss z')


    String toString() {
        "DtPerson2Subsidiary: [$person / $subsidiary], ${sdf.format(startdate)}"
    }
}
