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

class DtCard {
    static hasMany = [events:DtEvent,vehicleUses:DtVehicleUse,card2drivers:DtCard2driver]
    
    // References

    // Data
    String cardIssuingMemberState
    String cardNumber_driverIdentification
    String cardNumber_cardReplacementIndex
    String cardNumber_cardRenewalIndex
    String cardIssuingAuthorityName
    Date cardIssueDate 
    Date cardValidityBegin
    Date cardExpiryDate

    static domainKeys =
        ['cardIssuingMemberState','cardNumber_driverIdentification','cardNumber_cardReplacementIndex','cardNumber_cardRenewalIndex']

    static constraints = {
        cardIssuingMemberState(blank:false)
        cardNumber_driverIdentification(blank:false)
        cardNumber_cardReplacementIndex(blank:false)
        cardNumber_cardRenewalIndex(blank:false)
        cardIssuingAuthorityName(blank:false)
        cardIssueDate(nullable:false)
        cardValidityBegin(nullable:false)
        cardExpiryDate(nullable:false)
    }

    private static SimpleDateFormat sdf = new SimpleDateFormat('yyyy-MM-dd HH:mm:ss z')


    DtCard() {
    }


    /**
     * Constructor, from XML data
     */
    DtCard(cardIdentification) {
        cardIssuingMemberState = cardIdentification.cardIssuingMemberState
        cardNumber_driverIdentification = cardIdentification.cardNumber.driverIdentification
        cardNumber_cardReplacementIndex = cardIdentification.cardNumber.cardReplacementIndex
        cardNumber_cardRenewalIndex = cardIdentification.cardNumber.cardRenewalIndex
        cardIssuingAuthorityName = cardIdentification.cardIssuingAuthorityName.name
        cardIssueDate = sdf.parse(cardIdentification.cardIssueDate as String)
        cardValidityBegin = sdf.parse(cardIdentification.cardValidityBegin as String)
        cardExpiryDate = sdf.parse(cardIdentification.cardExpiryDate as String)
    }


    String toString() {
        "DtCard: $cardNumber_driverIdentification ($cardNumber_cardReplacementIndex, $cardNumber_cardRenewalIndex, $cardIssuingMemberState)"
    }
}
