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

class DtSubsidiary {
    static belongsTo = DtCompany
//    static hasMany = [person2subsidiaries:DtPerson2Subsidiary]

    // References
    DtCompany company

    // Data
    String zipcode
    String city
    String street
    String housenumber
    String addressSupplement

    static constraints = {
        zipcode(nullable:true)
        city(blank:false)
        street(nullable:true)
        housenumber(nullable:true)
        addressSupplement(nullable:true)
    }

    String toString() {
        "DtSubsidiary: ${(street)?street + ' ' + housenumber + ', ':''}${(zipcode)?zipcode + ' ':''}$city${(addressSupplement)?' '+addressSupplement:''}"
    }
}
