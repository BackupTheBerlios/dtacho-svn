import org.opendtacho.domain.DtUser

fixture {
    adminUser(DtUser) {
        person = weinertPerson
        username = 'admin'
        userRealName = 'admin'
        password = '-' // dummy value
        enabled = true
    }
    weinertUser(DtUser) {
        person = weinertPerson
        username = 'weinert'
        userRealName = 'Juergen Weinert'
        password = '-' // dummy value
        enabled = true
    }
    eberspUser(DtUser) {
        person = weinertPerson
        username = 'me'
        userRealName = 'Markus Eberspaecher'
        password = '-' // dummy value
        enabled = true
    }
    langUser(DtUser) {
        person = weinertPerson
        username = 'lang'
        userRealName = 'Gerald Lang'
        password = '-' // dummy value
        enabled = true
    }
    hoangUser(DtUser) {
        person = weinertPerson
        username = 'hal'
        userRealName = 'Hoang Anh Le'
        password = '-' // dummy value
        enabled = true
    }
    normalUser(DtUser) {
        person = weinertPerson
        username = 'user'
        userRealName = 'Normal User'
        password = '-' // dummy value
        enabled = true
    }
}
postProcess {
    // have to set password here, because in fixture no service is available!
    adminUser.password = authenticateService.encodePassword("pw")
    adminUser.save()
    weinertUser.password = authenticateService.encodePassword("pw")
    weinertUser.save()
    eberspUser.password = authenticateService.encodePassword("pw")
    eberspUser.save()
    langUser.password = authenticateService.encodePassword("pw")
    langUser.save()
    hoangUser.password = authenticateService.encodePassword("pw")
    hoangUser.save()
    normalUser.password = authenticateService.encodePassword("pw")
    normalUser.save()
}
