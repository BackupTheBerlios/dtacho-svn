package org.opendtacho.domain

import java.text.SimpleDateFormat

class DtEvent {
    static belongsTo = [DtVehicle, DtCard]
    
    // References
    DtVehicle vehicle
    DtCard card

    // Data
    String eventType
    Date eventBeginTime
    Date eventEndTime

    static domainKeys = ['vehicle','card','eventBeginTime']

    private static SimpleDateFormat sdf = new SimpleDateFormat('yyyy-MM-dd HH:mm:ss z')


    DtEvent() {
    }


    /**
     * Constructor, from XML data
     */
    DtEvent(dtCard, dtVehicle, cardEventRecord) {
        card = dtCard
        vehicle = dtVehicle
        eventType = cardEventRecord.eventType
        eventBeginTime = sdf.parse(cardEventRecord.eventBeginTime as String)
        eventEndTime = sdf.parse(cardEventRecord.eventEndTime as String)
    }


    String toString() {
        "DtEvent: [$vehicle / $card], ${sdf.format(eventBeginTime)}"
    }
}
