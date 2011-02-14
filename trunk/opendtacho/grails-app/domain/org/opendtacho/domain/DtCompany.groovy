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
class DtCompany {

  static hasMany = [subsidiaries:DtSubsidiary,persons:DtPerson,drivers:DtDriver]

    // Data
    String companyName     // from import company card, display only
    String companyAddress  // from import company card, display only
    
    String name1
    String name2
    String name3
    String zipcode
    String city
    String street
    String housenumber
    String addressSupplement

    static domainKeys = ['companyName', 'companyAddress']

    static constraints = {
        companyName(blank:false)
        companyAddress(blank:false)
        name1(blank:false)
        name2(nullable:true)
        name3(nullable:true)
        zipcode(nullable:true)
        city(nullable:true)
        street(nullable:true)
        housenumber(nullable:true)
        addressSupplement(nullable:true)
    }

    String toString() {
        //"DtCompany: $name1${(name2)?' '+name2:''}${(name3)?' '+name3:''}"
        "Company: ${companyName}"
    }
}
