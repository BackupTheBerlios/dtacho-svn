import org.opendtacho.domain.DtRequestMap

fixture{
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
    url = '/dtuser/**'
    configAttribute = 'ROLE_ADMIN'
  }
  rm6(DtRequestMap) {
    url = '/dtrole/**'
    configAttribute = 'ROLE_ADMIN'
  }
  rm7(DtRequestMap) {
    url = '/dtrequestmap/**'
    configAttribute = 'ROLE_ADMIN'
  }
  rm8(DtRequestMap) {
    url = '/dtcompany/**'
    configAttribute = 'ROLE_ADMIN'
  }
  rm9(DtRequestMap) {
    url = '/dtsubsidiary/**'
    configAttribute = 'ROLE_ADMIN'
  }
  rm10(DtRequestMap) {
    url = '/dtdepartment/**'
    configAttribute = 'ROLE_ADMIN'
  }
  rm11(DtRequestMap) {
    url = '/dtperson/**'
    configAttribute = 'ROLE_ADMIN'
  }
}