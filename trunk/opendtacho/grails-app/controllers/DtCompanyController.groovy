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

import org.opendtacho.domain.DtCompany
import org.opendtacho.domain.DtPerson

class DtCompanyController {
    def authenticateService

    def scaffold = DtCompany

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
          return [ dtCompanyInstanceList: DtCompany.list( params ), dtCompanyInstanceTotal: DtCompany.count() ]
        }

        //see only your company
        if(currentRole=="ROLE_COMPANY_MANAGER"){

          def currentCompany = currentPerson.getCompany()
          def tempList = DtCompany.findAllById(currentCompany.id)

          return [ dtCompanyInstanceList: tempList, dtCompanyInstanceTotal: tempList.count() ]
        }
    }

    def show = {
        def dtCompanyInstance = DtCompany.get( params.id )

        if(!dtCompanyInstance) {
            flash.message = "DtCompany not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ dtCompanyInstance : dtCompanyInstance ] }
    }

    def delete = {
        def dtCompanyInstance = DtCompany.get( params.id )
        if(dtCompanyInstance) {
            try {
                dtCompanyInstance.delete(flush:true)
                flash.message = "DtCompany ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "DtCompany ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "DtCompany not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def dtCompanyInstance = DtCompany.get( params.id )

        if(!dtCompanyInstance) {
            flash.message = "DtCompany not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ dtCompanyInstance : dtCompanyInstance ]
        }
    }

    def update = {
        def dtCompanyInstance = DtCompany.get( params.id )
        if(dtCompanyInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(dtCompanyInstance.version > version) {

                    dtCompanyInstance.errors.rejectValue("version", "dtCompany.optimistic.locking.failure", "Another user has updated this DtCompany while you were editing.")
                    render(view:'edit',model:[dtCompanyInstance:dtCompanyInstance])
                    return
                }
            }
            dtCompanyInstance.properties = params
            if(!dtCompanyInstance.hasErrors() && dtCompanyInstance.save()) {
                flash.message = "DtCompany ${params.id} updated"
                redirect(action:show,id:dtCompanyInstance.id)
            }
            else {
                render(view:'edit',model:[dtCompanyInstance:dtCompanyInstance])
            }
        }
        else {
            flash.message = "DtCompany not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def dtCompanyInstance = new DtCompany()
        dtCompanyInstance.properties = params
        return ['dtCompanyInstance':dtCompanyInstance]
    }

    def save = {
        def dtCompanyInstance = new DtCompany(params)
        if(!dtCompanyInstance.hasErrors() && dtCompanyInstance.save()) {
            flash.message = "DtCompany ${dtCompanyInstance.id} created"
            redirect(action:show,id:dtCompanyInstance.id)
        }
        else {
            render(view:'create',model:[dtCompanyInstance:dtCompanyInstance])
        }
    }
}
