import org.opendtacho.domain.DtRole

fixture {
    adminRole(DtRole) {
        people = [adminUser,weinertUser,eberspUser,langUser,hoangUser]
        authority = 'GROUP_ADMIN'
        description = 'administration access'
    }

    userRole(DtRole) {
        people = [normalUser]
        authority = 'GROUP_USER'
        description = 'normal user access'
    }

    comManRole(DtRole){
        people = [langUser]
    }

    subManRole(DtRole){
        people = [philipp]
    }



}