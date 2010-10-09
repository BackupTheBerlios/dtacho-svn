import org.opendtacho.domain.DtDepartment

fixture{
  depHR(DtDepartment){
    subsidiary = subsKarlsruhe
    name = 'HR'
    description = 'Human ressource department'
  }
  depDEV(DtDepartment){
    subsidiary = subsKarlsruhe
    name = 'DEV'
    description = 'Development department'
  }
}