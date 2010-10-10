import org.opendtacho.domain.DtPerson
import org.opendtacho.domain.DtSubsidiary
import org.opendtacho.domain.DtDepartment

class SecurityFilters {

  def authenticateService


  //the filters here are only used to decide whether a user is ALLOWED TO SEE something or don't
  //DON'T help to response the COMPATIBLE data to the user
  //this is in CONTROLLER
  def filters = {

    //only admin can create or delete erevything
    adminFilter(controller:"*",action:"(delete|create)"){
      before={
        //methods getAuthorities() (from DtUser) returns a list with Hibernate type
        //org.hibernate.collection.PersistentSet
        //have to convert to groovy list
        def currentRole = authenticateService.userDomain().getAuthorities().toList() //here is a list
        if(currentRole[0].getAuthority()!="ROLE_ADMIN"){                             //take the first and the only one instance in list
          redirect(controller:"login",action:"denied")
          return false
        }
        return true
      }
    }
    
    //user filter is used to check the current user, if the current user don't relate to the data --> denied
    //ATTENTION: controller name is case sensitive, DtUser --> dtUser, dtuser --> don't work
    userFilter(controller:"dtUser",action:"(show|edit)"){
      before = {
        //admin can everything
        def currentRole = authenticateService.userDomain().getAuthorities().toList()
        if(currentRole[0].getAuthority()=="ROLE_ADMIN") return true

        def currentUserId = authenticateService.userDomain().id
        if(currentUserId != params.id.toLong()){//currentUserId has type bigint, therefore params.id.toLong()
          redirect(controller:"login",action:"denied")
          return false
        }
        return true
      }
    }

    //person filter
    personFilter(controller:"dtPerson",action:"(show|edit)"){
      before={
        def currentRole = authenticateService.userDomain().getAuthorities().toList()

        //admin can everything
        if(currentRole[0].getAuthority()=="ROLE_ADMIN"){
          return true
        }

        //company manager can modify all related persons
        if(currentRole[0].getAuthority()=="ROLE_COMPANY_MANAGER"){
          return true
        }

        //subsidiary manager can modify all related persons
        if(currentRole[0].getAuthority()=="ROLE_SUBSIDIARY_MANAGER"){
          return true
        }

        //department manager can modify all related persons
        if(currentRole[0].getAuthority()=="ROLE_DEPARTMENT_MANAGER"){
          return true
        }

        //the logged person can only modify the own profile
        else{
          def currentPersonId = authenticateService.userDomain().getPerson().id
          if(currentPersonId!=params.id.toLong()){
            redirect(controller:"login",action:"denied")
            return false
          }
          return true
        }
      }
    }

    //department filter
    departmentFilter(controller:"dtDepartment",action:"(show|edit)"){
      before={
        def currentRole = authenticateService.userDomain().getAuthorities().toList()
        
        //admin can everything
        if(currentRole[0].getAuthority()=="ROLE_ADMIN"){
          return true
        }

        //company manager can modify all related departments
        if(currentRole[0].getAuthority()=="ROLE_COMPANY_MANAGER"){
          def currentPersonId = authenticateService.userDomain().getPerson().id
          def currentPerson = DtPerson.get(currentPersonId)
          def currentCompany = currentPerson.getCompany()

          def tempSubList = currentCompany.getSubsidiaries()

          def check = false

          tempSubList.each {aSub->
            if(aSub.getDepartments().contains(DtDepartment.get(params.id))) {
              check=true
            }
          }
          if(check) return true
          else {
            redirect(controller:"login",action:"denied")
            return false
          }
        }

        //subsidiary manager can modify all related departments
        if(currentRole[0].getAuthority()=="ROLE_SUBSIDIARY_MANAGER"){
          def currentPersonId = authenticateService.userDomain().getPerson().id
          def currentPerson = DtPerson.get(currentPersonId)
          def currentSubsidiary = currentPerson.getSubsidiary()
          if(currentSubsidiary.getDepartments().contains(DtDepartment.get(params.id))) return true
          else {
            redirect(controller:"login",action:"denied")
            return false
          }
        }

        //department manager can only modife the own department
        if(currentRole[0].getAuthority()=="ROLE_DEPARTMENT_MANAGER"){
          //the method userDomain() returns a detached instance, which means it isn't
          //bound to the underlying Hibernate session
          //therefore we have to do the tricky way below :(
          def currentPersonId = authenticateService.userDomain().getPerson().id
          def currentPerson = DtPerson.get(currentPersonId)
          def currentDepartment = currentPerson.getDepartment()

          if(currentDepartment.id!=params.id.toLong()){
            redirect(controller:"login",action:"denied")
            return false
          }
          return true
        }
      }
    }

    //subsidiary filter
    subsidiaryFilter(controller:"dtSubsidiary",action:"(show|edit)"){
      before={
        def currentRole = authenticateService.userDomain().getAuthorities().toList()

        //admin can everything
        if(currentRole[0].getAuthority()=="ROLE_ADMIN"){
          return true
        }

        //company manager can modify all related subsidiaries
        if(currentRole[0].getAuthority()=="ROLE_COMPANY_MANAGER"){
          def currentPersonId = authenticateService.userDomain().getPerson().id
          def currentPerson = DtPerson.get(currentPersonId)
          def currentCompany = currentPerson.getCompany()
          if(currentCompany.getSubsidiaries().contains(DtSubsidiary.get(params.id))) return true
          else {
            redirect(controller:"login",action:"denied")
            return false
          }
        }

        //subsidiary manager can only modify the own subsidiary
        if(currentRole[0].getAuthority()=="ROLE_SUBSIDIARY_MANAGER"){
          def currentPersonId = authenticateService.userDomain().getPerson().id
          def currentPerson = DtPerson.get(currentPersonId)
          def currentSubsidiary = currentPerson.getSubsidiary()

          if(currentSubsidiary.id!=params.id.toLong()){
            redirect(controller:"login",action:"denied")
            return false
          }
          return true
        }
      }
    }

    //company filter
    companyFilter(controller:"dtCompany",action:"(show|edit)"){
      before={
        def currentRole = authenticateService.userDomain().getAuthorities().toList()

        //admin can everything
        if(currentRole[0].getAuthority()=="ROLE_ADMIN"){
          return true
        }

        //company manager can only modify the own company
        else{
          def currentPersonId = authenticateService.userDomain().getPerson().id
          def currentPerson = DtPerson.get(currentPersonId)
          def currentCompany = currentPerson.getCompany()

          if(currentCompany.id!=params.id.toLong()){
            redirect(controller:"login",action:"denied")
            return false
          }
          return true
        }
      }
    }

  }
    
}
