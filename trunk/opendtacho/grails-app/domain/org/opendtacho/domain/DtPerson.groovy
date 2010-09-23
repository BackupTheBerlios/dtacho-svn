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

class DtPerson {
    static belongsTo = [DtCompany]
//    static hasMany = [person2subsidiaries:DtPerson2Subsidiary, users:DtUser]
    static hasMany = [users:DtUser]

    // References
    DtCompany company

    // Data
    String personnelNumber
    String title
    String salutation
    String firstName
    String lastName
    String privateZipcode
    String privateCity
    String privateStreet
    String privateHousenumber
    String privateAddressSupplement
    String trafficSignalValue

    String email
    String phone
    String fax
    String preferredLanguage

    static domainKeys = ['firstName', 'lastName']

    static constraints = {
        personnelNumber(nullable:true)
        title(nullable:true)
        salutation(nullable:true)
        firstName(nullable:true)
        lastName(blank:false)
        privateZipcode(nullable:true)
        privateCity(nullable:true)
        privateStreet(nullable:true)
        privateHousenumber(nullable:true)
        privateAddressSupplement(nullable:true)
        email(email:true, nullable:true)
        phone(nullable:true)
        fax(nullable:true)
        preferredLanguage(nullable:true)
        company(nullable:true)                      // relation to company is optional
        trafficSignalValue(nullable:true)
    }

    String toString() {
        "DtPerson: ${(firstName)?firstName+' ':''}$lastName"
    }
}
