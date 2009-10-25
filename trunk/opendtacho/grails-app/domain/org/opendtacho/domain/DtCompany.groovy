package org.opendtacho.domain
class DtCompany {
//    static hasMany = [subsidiaries:DtSubsidiary]

    // References

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
        "DtCompany: $name1${(name2)?' '+name2:''}${(name3)?' '+name3:''}"
    }
}
