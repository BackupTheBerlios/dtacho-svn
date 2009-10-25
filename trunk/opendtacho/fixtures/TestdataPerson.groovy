import org.opendtacho.domain.DtPerson

fixture {
    weinertPerson(DtPerson) {
        firstName = 'Jürgen'
        lastName = 'Weinert'
    }
    eberspPerson(DtPerson) {
        firstName = 'Markus'
        lastName = 'Eberspächer'
    }
    langPerson(DtPerson) {
        firstName = 'Gerald'
        lastName = 'Lang'
    }
    hoangPerson(DtPerson) {
        company = company1          // directly related to company
        firstName = 'Hoang Anh'
        lastName = 'Le'
    }
}
