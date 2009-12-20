package org.opendtacho.domain

import java.text.SimpleDateFormat

class DtCard2Company {

    static belongsTo = [DtCompany,DtCard]

    // References
    DtCompany company
    DtCard card

    // Data
    Date startdate
    Date enddate

    static domainKeys = ['company','card','startdate','enddate']

    static constraints = {
        enddate(nullable:true)
    }

    private static SimpleDateFormat sdf = new SimpleDateFormat('yyyy-MM-dd HH:mm:ss z')


    DtCard2Company() {
    }
}
