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
