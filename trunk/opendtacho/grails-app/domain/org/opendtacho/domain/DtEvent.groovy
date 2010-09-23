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
