package org.opendtacho.domain

import java.text.SimpleDateFormat

class DtDriver2Company {

    static belongsTo = [DtCompany,DtDriver]

    // References
    DtCompany company
    DtDriver driver

    // Data
    Date startdate
    Date enddate

    static domainKeys = ['company','driver','startdate','enddate']

    static constraints = {
        enddate(nullable:true)
    }

    private static SimpleDateFormat sdf = new SimpleDateFormat('yyyy-MM-dd HH:mm:ss z')


    DtDriver2Company() {
    }
}
