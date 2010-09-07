import org.opendtacho.domain.DtRole

fixture {
    adminRole(DtRole) {
        people = [adminUser,weinertUser,eberspUser,langUser,hoangUser]
        authority = 'ROLE_ADMIN'
        description = 'administration access'
    }
    userRole(DtRole) {
        people = [normalUser]
        authority = 'ROLE_USER'
        description = 'normal user access'
    }
    dummyRole(DtRole) {
        people = []
        authority = 'ROLE_DUMMY'
        description = 'dummy access'
    }
}