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

import org.opendtacho.domain.DtSubsidiary
import org.opendtacho.domain.DtPerson

class DtSubsidiaryController {
    def authenticateService

    def scaffold = DtSubsidiary

    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        def currentPersonId = authenticateService.userDomain().getPerson().id
        def currentPerson = DtPerson.get(currentPersonId)

        def currentRoleMap = authenticateService.userDomain().getAuthorities().toList()
        def currentRole = currentRoleMap[0].getAuthority()

        //see everything
        if(currentRole=="ROLE_ADMIN"){
            return [ dtSubsidiaryInstanceList: DtSubsidiary.list( params ), dtSubsidiaryInstanceTotal: DtSubsidiary.count() ]
        }

        //see all subsidiaries to the company
        if(currentRole=="ROLE_COMPANY_MANAGER"){
            def currentCompany = currentPerson.getCompany()

            def tempList = currentCompany.getSubsidiaries()

            return [ dtSubsidiaryInstanceList: tempList, dtSubsidiaryInstanceTotal: tempList.count() ]
        }

        //see only your subsidiary
        if(currentRole=="ROLE_SUBSIDIARY_MANAGER"){
            def currentSubsidiary = currentPerson.getSubsidiary()

            def tempList = DtSubsidiary.findAllById(currentSubsidiary.id)

            return [ dtSubsidiaryInstanceList: tempList, dtSubsidiaryInstanceTotal: tempList.count() ]
        }
    }

    def show = {
        def dtSubsidiaryInstance = DtSubsidiary.get( params.id )

        if(!dtSubsidiaryInstance) {
            flash.message = "DtSubsidiary not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ dtSubsidiaryInstance : dtSubsidiaryInstance ] }
    }

    def delete = {
        def dtSubsidiaryInstance = DtSubsidiary.get( params.id )
        if(dtSubsidiaryInstance) {
            try {
                dtSubsidiaryInstance.delete(flush:true)
                flash.message = "DtSubsidiary ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "DtSubsidiary ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "DtSubsidiary not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def dtSubsidiaryInstance = DtSubsidiary.get( params.id )

        if(!dtSubsidiaryInstance) {
            flash.message = "DtSubsidiary not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ dtSubsidiaryInstance : dtSubsidiaryInstance ]
        }
    }

    def update = {
        def dtSubsidiaryInstance = DtSubsidiary.get( params.id )
        if(dtSubsidiaryInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(dtSubsidiaryInstance.version > version) {

                    dtSubsidiaryInstance.errors.rejectValue("version", "dtSubsidiary.optimistic.locking.failure", "Another user has updated this DtSubsidiary while you were editing.")
                    render(view:'edit',model:[dtSubsidiaryInstance:dtSubsidiaryInstance])
                    return
                }
            }
            dtSubsidiaryInstance.properties = params
            if(!dtSubsidiaryInstance.hasErrors() && dtSubsidiaryInstance.save()) {
                flash.message = "DtSubsidiary ${params.id} updated"
                redirect(action:show,id:dtSubsidiaryInstance.id)
            }
            else {
                render(view:'edit',model:[dtSubsidiaryInstance:dtSubsidiaryInstance])
            }
        }
        else {
            flash.message = "DtSubsidiary not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def dtSubsidiaryInstance = new DtSubsidiary()
        dtSubsidiaryInstance.properties = params
        return ['dtSubsidiaryInstance':dtSubsidiaryInstance]
    }

    def save = {
        def dtSubsidiaryInstance = new DtSubsidiary(params)
        if(!dtSubsidiaryInstance.hasErrors() && dtSubsidiaryInstance.save()) {
            flash.message = "DtSubsidiary ${dtSubsidiaryInstance.id} created"
            redirect(action:show,id:dtSubsidiaryInstance.id)
        }
        else {
            render(view:'create',model:[dtSubsidiaryInstance:dtSubsidiaryInstance])
        }
    }
}
