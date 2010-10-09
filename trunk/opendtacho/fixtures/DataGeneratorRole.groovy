import org.opendtacho.domain.DtRole
//Attention:
//all roles HAVE TO HAVE ROLE_**** names, otherwise it WON'T work
fixture{
  //administration access
  roleAdmin(DtRole){
    people = [userAdmin]
    authority = 'ROLE_ADMIN'
    description = 'administration access'
  }
  
  //dummy user access
  roleDummy(DtRole){
    people = [userDummy]
    authority = 'ROLE_DUMMY'
    description = 'dummy access'
  }

  //for master data
  roleComMan(DtRole){
    people = [userGerald]
    authority = 'ROLE_COMPANY_MANAGER'
    description = 'company manager access'
  }
  roleSubMan(DtRole){
    people = [userIlya]
    authority = 'ROLE_SUBSIDIARY_MANAGER'
    description = 'subsidiary manager access'
  }
  roleDepMan(DtRole){
    people = [userHoang,userPhilipp]
    authority = 'ROLE_DEPARTMENT_MANAGER'
    description = 'department manager access'
  }

}