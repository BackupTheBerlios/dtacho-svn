import org.opendtacho.domain.DtRole

fixture{
  roleAdmin(DtRole){
    people = [userAdmin]
    authority = 'ROLE_ADMIN'
    description = 'administration access'
  }
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