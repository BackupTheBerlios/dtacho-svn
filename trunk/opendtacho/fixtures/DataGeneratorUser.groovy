import org.opendtacho.domain.DtUser

fixture{
  //admin
  userAdmin(DtUser){
    person = personAdmin
    username = 'admin'
    userRealName = 'admin'
    password = '-' // dummy value
    enabled = true
  }

  //dummy user
  userDummy(DtUser){
    person = personDummy
    username = 'dummy'
    userRealName = 'dummy'
    password = '-' // dummy value
    enabled = true
  }

  //for master data
  userGerald(DtUser){
    person = personGerald
    username = 'gerald'
    userRealName = 'Gerald Lang'
    password = '-' // dummy value
    enabled = true
  }
  userIlya(DtUser){
    person = personIlya
    username = 'ilya'
    userRealName = 'Ilya Grygoryev'
    password = '-' // dummy value
    enabled = true
  }
  userHoang(DtUser){
    person = personHoang
    username = 'hoang'
    userRealName = 'Hoang Anh Le'
    password = '-' // dummy value
    enabled = true
  }
  userPhilipp(DtUser){
    person = personPhilipp
    username = 'philipp'
    userRealName = 'Philipp Naegele'
    password = '-' // dummy value
    enabled = true
  }

}

post {
  // have to set password here, because in fixture no service is available!
  userAdmin.password = authenticateService.encodePassword("pw")
  userAdmin.save()
  userDummy.password = authenticateService.encodePassword("pw")
  userDummy.save()
  userGerald.password = authenticateService.encodePassword("pw")
  userGerald.save()
  userHoang.password = authenticateService.encodePassword("pw")
  userHoang.save()
  userPhilipp.password = authenticateService.encodePassword("pw")
  userPhilipp.save()
  userIlya.password = authenticateService.encodePassword("pw")
  userIlya.save()
}