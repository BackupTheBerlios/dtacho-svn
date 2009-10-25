import org.opendtacho.domain.DtRequestMap

fixture {
    rm1(DtRequestMap) {
        url = '/dtactivitychange/**'
        configAttribute = 'ROLE_ADMIN'
    }
    rm2(DtRequestMap) {
        url = '/dtdriver/**'
        configAttribute = 'ROLE_ADMIN'
    }
    rm3(DtRequestMap) {
        url = '/xmlfileupload/**'
        configAttribute = 'ROLE_ADMIN'
    }
    rm4(DtRequestMap) {
        url = '/dddfileupload/**'
        configAttribute = 'ROLE_ADMIN'
    }
    rm5(DtRequestMap) {
        url = '/user/**'
        configAttribute = 'ROLE_ADMIN'
    }
    rm6(DtRequestMap) {
        url = '/role/**'
        configAttribute = 'ROLE_ADMIN'
    }
}
