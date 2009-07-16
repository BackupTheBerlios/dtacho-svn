class DtCompany {
    static hasMany = [subsidiaries:DtSubsidiary]

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

    static constraints = {
        name1(blank:false)
    }

    String toString() {
        "DtCompany: $name1${(name2)?' '+name2:''}${(name3)?' '+name3:''}"
    }
}
