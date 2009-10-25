package org.opendtacho.domain

import java.text.SimpleDateFormat

class DtVehicleUse {
    static belongsTo = [DtVehicle, DtCard]

    // References
    DtVehicle vehicle
    DtCard card

    // Data
    int vehicleOdometerBegin
    int vehicleOdometerEnd
    Date vehicleFirstUse
    Date vehicleLastUse
    int vuDataBlockCounter

    static domainKeys = ['vehicle','card','vuDataBlockCounter']

//    static indexes = {
//        domainIndex('vehicle','card','vuDataBlockCounter')
//    }

    private static SimpleDateFormat sdf = new SimpleDateFormat('yyyy-MM-dd HH:mm:ss z')


    DtVehicleUse() {
    }


    /**
     * Constructor, from XML data
     */
    DtVehicleUse(dtCard, dtVehicle, cardVehicleRecord) {
        card = dtCard
        vehicle = dtVehicle
        vehicleOdometerBegin = Integer.parseInt(cardVehicleRecord.vehicleOdometerBegin as String)
        vehicleOdometerEnd = Integer.parseInt(cardVehicleRecord.vehicleOdometerEnd as String)
        vehicleFirstUse = sdf.parse(cardVehicleRecord.vehicleFirstUse as String)
        vehicleLastUse = sdf.parse(cardVehicleRecord.vehicleLastUse as String)
        vuDataBlockCounter = Integer.parseInt(cardVehicleRecord.vuDataBlockCounter as String)
    }


    String toString() {
        "DtVehicleUse: [$vehicle / $card], $vuDataBlockCounter"
    }
    
}
