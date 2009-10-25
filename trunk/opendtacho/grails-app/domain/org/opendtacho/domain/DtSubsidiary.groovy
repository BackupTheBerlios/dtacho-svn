package org.opendtacho.domain

class DtSubsidiary {
    static belongsTo = DtCompany
//    static hasMany = [person2subsidiaries:DtPerson2Subsidiary]

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
