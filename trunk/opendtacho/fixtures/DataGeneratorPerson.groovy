import org.opendtacho.domain.DtPerson

fixture {
  //admin
  personAdmin(DtPerson) {
    personnelNumber = 1
    lastName = 'Admin'
    firstName = 'Admin'

    company = null  //admin don't have any direct relationship with company
    subsidiary = null
    department = null
  }

  //dummy user
  personDummy(DtPerson) {
    personnelNumber = 2
    lastName = 'Dummy'
    firstName = 'Dummy'

    company = null  
    subsidiary = null
    department = null
  }

  //for master data
  personGerald(DtPerson) {
    personnelNumber = 3
    lastName = 'Lang'
    firstName = 'Gerald'

    company = comISCL
    subsidiary = null
    department = null
  }
  personIlya(DtPerson) {
    personnelNumber = 4
    lastName = 'Grygoryev'
    firstName = 'Ilya'

    company = comISCL
    subsidiary = subLinkenheim
    department = null
  }
  personHoang(DtPerson) {
    personnelNumber = 5
    lastName = 'Le'
    firstName = 'Hoang Anh'

    company = comISCL
    subsidiary = subLinkenheim
    department = depDEV
  }
  personPhilipp(DtPerson) {
    personnelNumber = 6
    lastName = 'Naegele'
    firstName = 'Philipp'

    company = comISCL
    subsidiary = subLinkenheim
    department = depDEV
  }
}