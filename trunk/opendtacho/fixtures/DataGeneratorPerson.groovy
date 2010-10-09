import org.opendtacho.domain.DtPerson

fixture {
  personAdmin(DtPerson) {
    personnelNumber = 1
    lastName = 'Bond'
    firstName = 'James'

    company = null  //admin don't have any direct relationship with company
    subsidiary = null
    department = null
  }
  personGerald(DtPerson) {
    personnelNumber = 2
    lastName = 'Lang'
    firstName = 'Gerald'

    company = comISCL
    subsidiary = null
    department = null
  }
  personIlya(DtPerson) {
    personnelNumber = 3
    lastName = 'Grygoryev'
    firstName = 'Ilya'

    company = comISCL
    subsidiary = subLinkenheim
    department = null
  }
  personHoang(DtPerson) {
    personnelNumber = 4
    lastName = 'Le'
    firstName = 'Hoang Anh'

    company = comISCL
    subsidiary = subLinkenheim
    department = depDEV
  }
  personPhilipp(DtPerson) {
    personnelNumber = 5
    lastName = 'Naegele'
    firstName = 'Philipp'

    company = comISCL
    subsidiary = subLinkenheim
    department = depDEV
  }
}