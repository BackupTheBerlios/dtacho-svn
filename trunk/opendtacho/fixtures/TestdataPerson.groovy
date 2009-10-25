import org.opendtacho.domain.DtPerson

fixture {
    weinertPerson(DtPerson) {
        firstName = 'J�rgen'
        lastName = 'Weinert'
    }
    eberspPerson(DtPerson) {
        firstName = 'Markus'
        lastName = 'Ebersp�cher'
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
