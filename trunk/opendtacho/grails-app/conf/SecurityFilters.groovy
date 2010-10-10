import org.opendtacho.domain.DtPerson

class SecurityFilters {

  def authenticateService

  def filters = {

    //user filter is used to check the current user, if the current user don't relate to
    //the data --> denied
    //attention: controller name is case sensitive, DtUser --> dtUser, dtuser --> don't work
    userFilter(controller:"dtUser",action:"(show|edit)"){
      before = {
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
        def currentPersonId = authenticateService.userDomain().id
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
        def currentPersonId = authenticateService.userDomain().id
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
