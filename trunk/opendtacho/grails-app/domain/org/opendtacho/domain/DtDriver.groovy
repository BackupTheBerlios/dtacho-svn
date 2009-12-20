package org.opendtacho.domain

class DtDriver {
    static hasMany = [activityChanges:DtActivityChange, card2drivers:DtCard2driver]
    static belongsTo = DtPerson

    // References
    DtPerson person
    
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
        person(nullable:true)                      // relation to person is optional
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
