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
