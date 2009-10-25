package org.opendtacho.domain

import java.text.SimpleDateFormat

class DtCard2driver {
    static belongsTo = [DtCard,DtDriver]

    // References
    DtCard card
    DtDriver driver

    // Data
    Date startdate
    Date enddate

    static domainKeys = ['card','driver','startdate','enddate']

    static constraints = {
        enddate(nullable:true)
    }

    private static SimpleDateFormat sdf = new SimpleDateFormat('yyyy-MM-dd HH:mm:ss z')


    DtCard2driver() {
    }


    /**
     * Constructor, from XML data
     */
    DtCard2driver(dtCard, dtDriver, startdate_, enddate_) {
        card = dtCard
        driver = dtDriver
        startdate = startdate_
        enddate = enddate_
    }


    String toString() {
        "DtCard2driver: [$card / $driver], ${sdf.format(startdate)}"
    }
}
