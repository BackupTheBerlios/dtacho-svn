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
