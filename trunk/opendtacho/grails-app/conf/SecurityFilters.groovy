import org.opendtacho.domain.DtPerson

class SecurityFilters {

  def authenticateService


  //the filters here are only used to decide whether a user can see something or don't
  //don't help to response the compatible data to the user
  //this is in controller
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
        //admin can everything
        def currentRole = authenticateService.userDomain().getAuthorities().toList()
        if(currentRole[0].getAuthority()=="ROLE_ADMIN") return true

        def currentPersonId = authenticateService.userDomain().getPerson().id
        if(currentPersonId!=params.id.toLong()){
          redirect(controller:"login",action:"denied")
          return false
        }
        return true
      }
    }

    //department filter
    departmentFilter(controller:"dtDepartment",action:"(show|edit)"){
      before={
        //admin can everything
        def currentRole = authenticateService.userDomain().getAuthorities().toList()
        if(currentRole[0].getAuthority()=="ROLE_ADMIN") return true

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

    //subsidiary filter
    subsidiaryFilter(controller:"dtSubsidiary",action:"(show|edit)"){
      before={
        //admin can everything
        def currentRole = authenticateService.userDomain().getAuthorities().toList()
        if(currentRole[0].getAuthority()=="ROLE_ADMIN") return true

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

    //company filter
    companyFilter(controller:"dtCompany",action:"(show|edit)"){
      before={
        //admin can everything
        def currentRole = authenticateService.userDomain().getAuthorities().toList()
        if(currentRole[0].getAuthority()=="ROLE_ADMIN") return true

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
