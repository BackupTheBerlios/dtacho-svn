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

class DtDriver {
    static hasMany = [activityChanges:DtActivityChange, card2drivers:DtCard2driver]
    static belongsTo = [person:DtPerson,company:DtCompany]

    // References
//    DtPerson person
    
    // Data
    // from driverCardHolderIdentification
    String cardHolderName_holderSurname
    String cardHolderName_holderFirstNames
    Date cardHolderBirthDate
    String cardHolderPreferredLanguage
    // from cardDrivingLicenceInformation
    String drivingLicenceIssuingAuthority
    String drivingLicenceIssuingNation
    String drivingLicenceNumber
    
    static domainKeys = ['drivingLicenceIssuingNation','drivingLicenceNumber']

    static constraints = {
        person(nullable:true) // relation to person is optional
        company(blank:false)
    }

    DtDriver() {
    }


    /**
     * Constructor, from XML data
     */
    DtDriver(driverCardHolderIdentification, cardDrivingLicenceInformation) {
        cardHolderName_holderSurname = driverCardHolderIdentification.cardHolderName.holderSurname.name
        cardHolderName_holderFirstNames = driverCardHolderIdentification.cardHolderName.holderFirstNames.name
        cardHolderBirthDate = new Date(
                Integer.parseInt(driverCardHolderIdentification.cardHolderBirthDate.year as String) - 1900,
                Integer.parseInt(driverCardHolderIdentification.cardHolderBirthDate.month as String),
                Integer.parseInt(driverCardHolderIdentification.cardHolderBirthDate.day as String)
                )
        cardHolderPreferredLanguage = driverCardHolderIdentification.cardHolderPreferredLanguage
        
        drivingLicenceIssuingAuthority = cardDrivingLicenceInformation.drivingLicenceIssuingAuthority.name
        drivingLicenceIssuingNation = cardDrivingLicenceInformation.drivingLicenceIssuingNation
        drivingLicenceNumber = cardDrivingLicenceInformation.drivingLicenceNumber
    }


    String toString() {
        "DtDriver: $cardHolderName_holderSurname, $cardHolderName_holderFirstNames (license: $drivingLicenceNumber)"
    }

}
