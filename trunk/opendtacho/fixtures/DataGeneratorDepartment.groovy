import org.opendtacho.domain.DtDepartment

fixture{
  //for ISCL
  depHR(DtDepartment){
    subsidiary = subLinkenheim
    name = 'HR'
    description = 'Human Ressource Department'
  }
  depDEV(DtDepartment){
    subsidiary = subLinkenheim
    name = 'DEV'
    description = 'Development Department'
  }
  depPR(DtDepartment){
    subsidiary = subKarlsruhe
    name = 'PR'
    description = 'Public Relationship Department'
  }

}