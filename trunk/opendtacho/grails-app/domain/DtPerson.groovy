class DtPerson {
    static belongsTo = DtSubsidiary
    static hasMany = [users:DtUser]

    // References
    DtSubsidiary subsidiary

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
    }

    String toString() {
        "DtPerson: ${(firstName)?firstName+' ':''}$lastName"
    }
}
