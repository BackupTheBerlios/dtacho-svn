package org.opendtacho.domain

class DtDepartment {
    static belongsTo = DtSubsidiary
    DtSubsidiary subsidiary

    static hasMany = [members:DtPerson]


    String name
    String description

    static constraints = {
      name(nullable:false)
      description(nullable:true)
    }
}
