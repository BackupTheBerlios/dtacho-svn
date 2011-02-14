// Opendtacho - a program for analysing and interpreting digital tachograph data
// Copyright (C) 2008  Gerald Lang, ISCL - Internet Security Consulting Lang, http://www.iscl.de
//
// http://www.opendtacho.org
//
// gerald.lang@iscl.de
//
// This program is free software; you can redistribute it and/or
// modify it under the terms of the GNU General Public License
// as published by the Free Software Foundation; either version 2
// of the License, or (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.


import org.opendtacho.domain.DtUser
import org.opendtacho.domain.DtRole
import org.opendtacho.domain.DtPerson

/**
 * User controller.
 */
class DtUserController {

	def authenticateService

    def scaffold = DtUser

	// the delete, save and update actions only accept POST requests
	static Map allowedMethods = [delete: 'POST', save: 'POST', update: 'POST']

	def index = {
		redirect action: list, params: params
	}

    /*
      MODIFIED list action
      returns the COMPATIBLE data for request
    */
	def list = {
//		if (!params.max) {
//			params.max = 10
//		}
//		[personList: DtUser.list(params)]
        def currentPersonId = authenticateService.userDomain().getPerson().id
        def currentPerson = DtPerson.get(currentPersonId)

        def currentRoleMap = authenticateService.userDomain().getAuthorities().toList()
        def currentRole = currentRoleMap[0].getAuthority()

        //see everything
        if(currentRole=="ROLE_ADMIN"){
            return [ personList: DtUser.list( params )/*, personInstanceTotal: DtUser.count() */]
        }

        //see only users related persons in company
        if(currentRole=="ROLE_COMPANY_MANAGER"){
            def currentCompany = currentPerson.getCompany()

            def tempPersonList = currentCompany.getPersons()

            def tempList = []

            tempPersonList.each{aPerson->
                tempList+=aPerson.getUsers()
            }
            println "${tempList}"
            return [ personList: tempList/*, personTotal: tempList.count() */]
        }

        //see only users related persons in subsidiary
        if(currentRole=="ROLE_SUBSIDIARY_MANAGER"){
            def currentSubsidiary = currentPerson.getSubsidiary()

            def tempPersonList = currentSubsidiary.getPersons()

            def tempList = []


            tempPersonList.each{aPerson->
                tempList+=aPerson.getUsers()
            }
            println "${tempList}"
            return [ personList: tempList/*, personTotal: tempList.count() */]
        }

        //see only users related persons in department
        if(currentRole=="ROLE_DEPARTMENT_MANAGER"){
            def currentDepartment = currentPerson.getDepartment()

            def tempPersonList = currentDepartment.getPersons()

            def tempList = []


            tempPersonList.each{aPerson->
                tempList+=aPerson.getUsers()
            }
            println "${tempList}"
            return [ personList: tempList/*, dtUserInstanceTotal: tempList.count() */]
        }

        //user can only see the own profile
        if(currentRole=="ROLE_DUMMY"){
            def currentUserId = authenticateService.userDomain().id

            def tempList = DtUser.findAllById(currentUserId)
          
            return [ personList: tempList/*, dtUserInstanceTotal: tempList.count() */]
        }

	}

    def show = {

        //get the user in context with help of his id
        def user = DtUser.get(params.id)

        //if this user don't exist
		if (!user) {
			flash.message = "DtUser not found with id $params.id"
			redirect action: list
			return
		}

        //gets the roles of this user
		List roleNames = []
		for (role in user.authorities) {
			roleNames << role.authority
		}
		roleNames.sort { n1, n2 ->
			n1 <=> n2
		}

        //forward to SHOW.GSP to show
        //user: this user instance in context
        //roleNames: a list of all roles of this user
        [user: user, roleNames: roleNames]
	}

	/**
	 * Person delete action. Before removing an existing person,
	 * he should be removed from those authorities which he is involved.
	 */
	def delete = {

		def user = DtUser.get(params.id)
		if (user) {
			def authPrincipal = authenticateService.principal()
			//avoid self-delete if the logged-in user is an admin
			if (!(authPrincipal instanceof String) && authPrincipal.username == user.username) {
				flash.message = "You can not delete yourself, please login as another admin and try again"
			}
			else {
				//first, delete this person from People_Authorities table.
				DtRole.findAll().each { it.removeFromPeople(user) }
				user.delete()
				flash.message = "DtUser $params.id deleted."
			}
		}
		else {
			flash.message = "DtUser not found with id $params.id"
		}

		redirect action: list
	}

	def edit = {

        //get the user in context with help of his id
		def user = DtUser.get(params.id)

        //if this user don't exist
		if (!user) {
			flash.message = "DtUser not found with id $params.id"
			redirect action: list
			return
		}


		return buildPersonModel(user)
	}

	/**
	 * Person update action.
     * MODIFIED
     *
     * update with relation with person
	 */
	def update = {

		def user = DtUser.get(params.id)
		if (!user) {
			flash.message = "DtUser not found with id $params.id"
			redirect action: edit, id: params.id
			return
		}

		long version = params.version.toLong()
		if (user.version > version) {
			user.errors.rejectValue 'version', "person.optimistic.locking.failure",
				"Another user has updated this DtUser while you were editing."
				render view: 'edit', model: buildPersonModel(user)
			return
		}

		def oldPassword = user.password
		user.properties = params
		if (!params.password.equals(oldPassword)) {
			user.password = authenticateService.encodePassword(params.password)
		}
		if (user.save()) {
			DtRole.findAll().each { it.removeFromPeople(user) }

            //invokes the addRoles() function, assign a new role or many new roles to the new created user
            addRoles(user)

            //invokes the addPerson() function, assgin a new person to the new created user
            //ATTENTION: just only one person for a user, but many users can assgin to a person
            addPerson(user)

			redirect action: show, id: user.id
		}
		else {
			render view: 'edit', model: buildPersonModel(user)
		}
	}

	def create = {
		[user: new DtUser(params), authorityList: DtRole.list()]
	}

	/**
	 * Person save action.
	 */
	def save = {

		def user = new DtUser()
		user.properties = params
		user.password = authenticateService.encodePassword(params.password)
		if (user.save()) {

            //invokes the addRoles() function, assign a new role or many new roles to the new created user
            addRoles(user)

            //invokes the addPerson() function, assgin a new person to the new created user
            //ATTENTION: just only one person for a user, but many users can assgin to a person
            addPerson(user)

            redirect action: show, id: user.id
		}
		else {
			render view: 'create', model: [authorityList: DtRole.list(), user: user]
		}
	}


    /*
      take properties from the "create form" (create.gsp),
      create a blank person and add the properties to this person
      if the user choose a role then add the role to this person
      help method for CREATE action
    */
	private void addRoles(user) {
		for (String key in params.keySet()) {
			if (key.contains('ROLE') && 'on' == params.get(key)) {
				DtRole.findByAuthority(key).addToPeople(user)
			}
		}
	}


    /*
      MODIFIED
      assign the new created user to the related person
      help method for CREATE action
    */
    private void addPerson(user){

      //takes the selected person by invoking the get method with params.person=id of this selected person
      def person = DtPerson.get(params.person);

      //adds this person to the new created user
      user.person = person
    }

	private Map buildPersonModel(user) {

        //gets all available roles, and sort this list
		List roles = DtRole.list()
		roles.sort { r1, r2 ->
			r1.authority <=> r2.authority
		}

        Set userRoleNames = []
		for (role in user.authorities) {
			userRoleNames << role.authority
		}
		LinkedHashMap<DtRole, Boolean> roleMap = [:]
		for (role in roles) {
			roleMap[(role)] = userRoleNames.contains(role.authority)
		}

		return [user: user, roleMap: roleMap]
	}
}
