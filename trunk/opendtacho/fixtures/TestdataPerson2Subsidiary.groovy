import org.opendtacho.domain.DtPerson2Subsidiary

fixture {
    person2subsidiary1(DtPerson2Subsidiary) {
        person = weinertPerson
        subsidiary = subsStuttgart
        startdate = new Date(0)
    }
    person2subsidiary2(DtPerson2Subsidiary) {
        person = eberspPerson
        subsidiary = subsStuttgart
        startdate = new Date(0)
    }
    person2subsidiary3(DtPerson2Subsidiary) {
        person = langPerson
        subsidiary = subsKarlsruhe
        startdate = new Date(0)
    }
    person2subsidiary4(DtPerson2Subsidiary) {
        person = langPerson
        subsidiary = subsStuttgart
        startdate = new Date(0)
    }
}
