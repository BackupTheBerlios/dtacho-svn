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
        lastName(blank:false)
        email(email:true)
    }

    String toString() {
        "DtPerson: ${(firstName)?firstName+' ':''}$lastName"
    }
}
