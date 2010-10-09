import org.opendtacho.domain.DtDepartment

fixture{
  depHR(DtDepartment){
    subsidiary = subLinkenheim
    name = 'HR'
    description = 'Human Ressource department'
  }
  depDEV(DtDepartment){
    subsidiary = subLinkenheim
    name = 'DEV'
    description = 'Development department'
  }
}