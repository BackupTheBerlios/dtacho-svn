class DtSubsidiary {
    static belongsTo = DtCompany
    static hasMany = [persons:DtPerson]

    // References
    DtCompany company

    // Data
    String zipcode
    String city
    String street
    String housenumber
    String addressSupplement

    static constraints = {
        city(blank:false)
    }

    String toString() {
        "DtSubsidiary: ${(street)?street + ' ' + housenumber + ', ':''}${(zipcode)?zipcode + ' ':''}$city${(addressSupplement)?' '+addressSupplement:''}"
    }
}
