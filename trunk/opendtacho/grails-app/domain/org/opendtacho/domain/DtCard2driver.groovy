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
