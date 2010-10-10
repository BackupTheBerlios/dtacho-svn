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
import org.opendtacho.domain.DtDepartment
import org.opendtacho.domain.DtPerson

class DtDepartmentController {
  def authenticateService

  def scaffold = DtDepartment

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
            return [ dtDepartmentInstanceList: DtDepartment.list( params ), dtDepartmentInstanceTotal: DtDepartment.count() ]
        }

        //see all departments to the company
        if(currentRole=="ROLE_COMPANY_MANAGER"){
            def currentCompany = currentPerson.getCompany()

            def tempList = []

            def tempSubList = currentCompany.getSubsidiaries()

            tempSubList.each{aSub->
                tempList += aSub.getDepartments()
            }

            return [ dtDepartmentInstanceList: tempList, dtDepartmentInstanceTotal: tempList.count() ]
        }

        //see all departments to the subsidiary
        if(currentRole=="ROLE_SUBSIDIARY_MANAGER"){
            def currentSubsidiary = currentPerson.getSubsidiary()

            def tempList = currentSubsidiary.getDepartments()

            return [ dtDepartmentInstanceList: tempList, dtDepartmentInstanceTotal: tempList.count() ]
        }

        //see only your department
        if(currentRole=="ROLE_DEPARTMENT_MANAGER"){
            def currentDepartment = currentPerson.getDepartment()

            def tempList = DtDepartment.get(currentDepartment.id)

            return [ dtDepartmentInstanceList: tempList, dtDepartmentInstanceTotal: tempList.count() ]
        }

    }

    def show = {
        def dtDepartmentInstance = DtDepartment.get( params.id )

        if(!dtDepartmentInstance) {
            flash.message = "DtDepartment not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ dtDepartmentInstance : dtDepartmentInstance ] }
    }

    def delete = {
        def dtDepartmentInstance = DtDepartment.get( params.id )
        if(dtDepartmentInstance) {
            try {
                dtDepartmentInstance.delete(flush:true)
                flash.message = "DtDepartment ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "DtDepartment ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "DtDepartment not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def dtDepartmentInstance = DtDepartment.get( params.id )

        if(!dtDepartmentInstance) {
            flash.message = "DtDepartment not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ dtDepartmentInstance : dtDepartmentInstance ]
        }
    }

    def update = {
        def dtDepartmentInstance = DtDepartment.get( params.id )
        if(dtDepartmentInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(dtDepartmentInstance.version > version) {

                    dtDepartmentInstance.errors.rejectValue("version", "dtDepartment.optimistic.locking.failure", "Another user has updated this DtDepartment while you were editing.")
                    render(view:'edit',model:[dtDepartmentInstance:dtDepartmentInstance])
                    return
                }
            }
            dtDepartmentInstance.properties = params
            if(!dtDepartmentInstance.hasErrors() && dtDepartmentInstance.save()) {
                flash.message = "DtDepartment ${params.id} updated"
                redirect(action:show,id:dtDepartmentInstance.id)
            }
            else {
                render(view:'edit',model:[dtDepartmentInstance:dtDepartmentInstance])
            }
        }
        else {
            flash.message = "DtDepartment not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def dtDepartmentInstance = new DtDepartment()
        dtDepartmentInstance.properties = params
        return ['dtDepartmentInstance':dtDepartmentInstance]
    }

    def save = {
        def dtDepartmentInstance = new DtDepartment(params)
        if(!dtDepartmentInstance.hasErrors() && dtDepartmentInstance.save()) {
            flash.message = "DtDepartment ${dtDepartmentInstance.id} created"
            redirect(action:show,id:dtDepartmentInstance.id)
        }
        else {
            render(view:'create',model:[dtDepartmentInstance:dtDepartmentInstance])
        }
    }
}
