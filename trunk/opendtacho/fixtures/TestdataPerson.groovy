import org.opendtacho.domain.DtPerson

fixture {
    weinertPerson(DtPerson) {
        firstName = 'J�rgen'
        lastName = 'Weinert'
        personnelNumber = 1
        subsidiary = subsKarlsruhe
      company = company0
    }
    eberspPerson(DtPerson) {
        firstName = 'Markus'
        lastName = 'Ebersp�cher'
      personnelNumber = 2
      subsidiary = subsKarlsruhe
      company = company0
    }
    langPerson(DtPerson) {
        firstName = 'Gerald'
        lastName = 'Lang'
      personnelNumber = 3
      subsidiary = subsKarlsruhe
      company = company0
    }
    hoangPerson(DtPerson) {
        company = company1          // directly related to company
        firstName = 'Hoang Anh'
        lastName = 'Le'
      personnelNumber =  4
      subsidiary = subsKarlsruhe
      company = company0
    }
}
