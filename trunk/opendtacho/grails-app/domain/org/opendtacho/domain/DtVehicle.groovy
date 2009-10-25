package org.opendtacho.domain
class DtVehicle {
    static hasMany = [events:DtEvent,vehicleUses:DtVehicleUse,activityChanges:DtActivityChange]

    // References

    // Data
    // from vehicleRegistration
    String vehicleRegistrationNation
    String vehicleRegistrationNumber
    // from ##
    String vehicleIdentificationNumber

    static domainKeys = ['vehicleRegistrationNation','vehicleRegistrationNumber']

    static constraints = {
        vehicleRegistrationNation(blank:false)
        vehicleRegistrationNumber(blank:false)
        vehicleIdentificationNumber(nullable:true)
    }


    DtVehicle() {
    }


    /**
     * Constructor, from XML data
     */
    DtVehicle(vehicleRegistration) {
        vehicleRegistrationNation = vehicleRegistration.vehicleRegistrationNation
        vehicleRegistrationNumber = vehicleRegistration.vehicleRegistrationNumber.vehicleRegNumber

    }


    String toString() {
        "DtVehicle: $vehicleRegistrationNumber (nation: $vehicleRegistrationNation)"
    }
}
