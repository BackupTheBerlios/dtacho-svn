// Opendtacho - a program for analysing and interpreting digital tachograph data
// Copyright (C) 2008  Gerald Lang, ISCL - Internet Security Consulting Lang, http://www.iscl.de
//
// http://www.opendtacho.org
//
// gerald.lang@iscl.de
//
// This program is free software; you can redistribute it and/or
// modify it under the terms of the GNU General Public License
// as published by the Free Software Foundation; either version 2
// of the License, or (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.

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
