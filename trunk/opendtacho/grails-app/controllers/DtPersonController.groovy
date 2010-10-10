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

import org.opendtacho.domain.DtPerson

class DtPersonController {
    def authenticateService

    def scaffold = DtPerson

    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    //modified list actions returns the COMPATIBLE data for request
    def list = {
          def currentPersonId = authenticateService.userDomain().getPerson().id
          def currentPerson = DtPerson.get(currentPersonId)

          def currentRoleMap = authenticateService.userDomain().getAuthorities().toList()
          def currentRole = currentRoleMap[0].getAuthority()

          //see everything
          if(currentRole=="ROLE_ADMIN"){
              return [ dtPersonInstanceList: DtPerson.list( params ), dtPersonInstanceTotal: DtPerson.count() ]
          }

          //see all persons to the company
          if(currentRole=="ROLE_COMPANY_MANAGER"){
              def currentCompany = currentPerson.getCompany()

              def tempList = currentCompany.getPersons()

              return [ dtPersonInstanceList: tempList, dtPersonInstanceTotal: tempList.count() ]
          }

          //see all persons to subsidiary
          if(currentRole=="ROLE_SUBSIDIARY_MANAGER"){
              def currentSubsidiary = currentPerson.getSubsidiary()

              def tempList = currentSubsidiary.getPersons()

              return [ dtPersonInstanceList: tempList, dtPersonInstanceTotal: tempList.count() ]
          }

          //see all persons to department
          if(currentRole=="ROLE_DEPARTMENT_MANAGER"){
              def currentDepartment = currentPerson.getDepartment()

              def tempList = currentDepartment.getPersons()

              return [ dtPersonInstanceList: tempList, dtPersonInstanceTotal: tempList.count() ]
          }

          //user can only the own profile
          if(currentRole=="ROLE_DUMMY"){
              def tempList = DtPerson.findAllById(currentPersonId)

              return [ dtPersonInstanceList: tempList, dtPersonInstanceTotal: tempList.count() ]
          }
      }

    def show = {
        def dtPersonInstance = DtPerson.get( params.id )

        if(!dtPersonInstance) {
            flash.message = "DtPerson not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ dtPersonInstance : dtPersonInstance ] }
    }

    def delete = {
        def dtPersonInstance = DtPerson.get( params.id )
        if(dtPersonInstance) {
            try {
                dtPersonInstance.delete(flush:true)
                flash.message = "DtPerson ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "DtPerson ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "DtPerson not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def dtPersonInstance = DtPerson.get( params.id )

        if(!dtPersonInstance) {
            flash.message = "DtPerson not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ dtPersonInstance : dtPersonInstance ]
        }
    }

    def update = {
        def dtPersonInstance = DtPerson.get( params.id )
        if(dtPersonInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(dtPersonInstance.version > version) {

                    dtPersonInstance.errors.rejectValue("version", "dtPerson.optimistic.locking.failure", "Another user has updated this DtPerson while you were editing.")
                    render(view:'edit',model:[dtPersonInstance:dtPersonInstance])
                    return
                }
            }
            dtPersonInstance.properties = params
            if(!dtPersonInstance.hasErrors() && dtPersonInstance.save()) {
                flash.message = "DtPerson ${params.id} updated"
                redirect(action:show,id:dtPersonInstance.id)
            }
            else {
                render(view:'edit',model:[dtPersonInstance:dtPersonInstance])
            }
        }
        else {
            flash.message = "DtPerson not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def dtPersonInstance = new DtPerson()
        dtPersonInstance.properties = params
        return ['dtPersonInstance':dtPersonInstance]
    }

    def save = {
        def dtPersonInstance = new DtPerson(params)
        if(!dtPersonInstance.hasErrors() && dtPersonInstance.save()) {
            flash.message = "DtPerson ${dtPersonInstance.id} created"
            redirect(action:show,id:dtPersonInstance.id)
        }
        else {
            render(view:'create',model:[dtPersonInstance:dtPersonInstance])
        }
    }
}
