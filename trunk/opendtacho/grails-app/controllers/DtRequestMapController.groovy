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

import org.opendtacho.domain.DtRequestMap

import org.springframework.util.StringUtils

/**
 * Requestmap controller.
 */
class DtRequestMapController {

	def authenticateService

	// the delete, save and update actions only accept POST requests
	static Map allowedMethods = [delete: 'POST', save: 'POST', update: 'POST']

	def index = {
		redirect action: list, params: params
	}

	def list = {
		if (!params.max) {
			params.max = 10
		}
		[requestmapList: DtRequestMap.list(params)]
	}

	def show = {
		def requestmap = DtRequestMap.get(params.id)
		if (!requestmap) {
			flash.message = "DtRequestMap not found with id $params.id"
			redirect action:list
			return
		}
		[requestmap: requestmap]
	}

	def delete = {
		def requestmap = DtRequestMap.get(params.id)
		if (!requestmap) {
			flash.message = "DtRequestMap not found with id $params.id"
			redirect action:list
			return
		}

		requestmap.delete()

		authenticateService.clearCachedRequestmaps()

		flash.message = "DtRequestMap $params.id deleted."
		redirect(action: list)
	}

	def edit = {
		def requestmap = DtRequestMap.get(params.id)
		if (!requestmap) {
			flash.message = "DtRequestMap not found with id $params.id"
			redirect(action: list)
			return
		}

		[requestmap: requestmap]
	}

	/**
	 * Update action, called when an existing Requestmap is updated.
	 */
	def update = {

		def requestmap = DtRequestMap.get(params.id)
		if (!requestmap) {
			flash.message = "DtRequestMap not found with id $params.id"
			redirect(action: edit, id :params.id)
			return
		}

		long version = params.version.toLong()
		if (requestmap.version > version) {
			requestmap.errors.rejectValue 'version', "requestmap.optimistic.locking.failure",
				"Another user has updated this DtRequestMap while you were editing."
			render view: 'edit', model: [requestmap: requestmap]
			return
		}

		requestmap.properties = params
		if (requestmap.save()) {
			authenticateService.clearCachedRequestmaps()
			redirect action: show, id: requestmap.id
		}
		else {
			render view: 'edit', model: [requestmap: requestmap]
		}
	}

	def create = {
		[requestmap: new DtRequestMap(params)]
	}

	/**
	 * Save action, called when a new Requestmap is created.
	 */
	def save = {
		def requestmap = new DtRequestMap(params)
		if (requestmap.save()) {
			authenticateService.clearCachedRequestmaps()
			redirect action: show, id: requestmap.id
		}
		else {
			render view: 'create', model: [requestmap: requestmap]
		}
	}

    /*empty action, just forward to mod.gsp*/
    def mod = {

    }

    /*process the authentication change*/
    //using authenticateService.clearCachedRequestmaps() brings the direct effect
    //the codes below maybe long, but easy to understand
    //i had another way, briefly but complex, so i decided to choose the long way
    def modResults = {

      //DRIVER DATA
      /*=======================================================================================================*/
      /*DtRequestMap instance for DtActivityChange URL*/
      def dtactivitychange = DtRequestMap.findByUrl("/dtactivitychange/**")

        /*check if radio button for ROLE_DUMMY ON and this instance DON'T have ROLE_DUMMY*/
        if(params.dtactivitychange_ROLE_DUMMY && !(dtactivitychange.configAttribute.contains('ROLE_DUMMY')))
          dtactivitychange.configAttribute+=",ROLE_DUMMY"
          dtactivitychange.save()
          authenticateService.clearCachedRequestmaps()
        /*check if radio button for ROLE_DUMMY OFF and this instance HAVE ROLE_DUMMY*/
        if(!(params.dtactivitychange_ROLE_DUMMY) && dtactivitychange.configAttribute.contains('ROLE_DUMMY'))
          dtactivitychange.configAttribute-=",ROLE_DUMMY"
          dtactivitychange.save()
          authenticateService.clearCachedRequestmaps()

        /*check if radio button for ROLE_COMPANY_MANAGER ON and this instance DON'T have ROLE_COMPANY_MANAGER*/
        if(params.dtactivitychange_ROLE_COMPANY_MANAGER && !(dtactivitychange.configAttribute.contains('ROLE_COMPANY_MANAGER')))
          dtactivitychange.configAttribute+=",ROLE_COMPANY_MANAGER"
          dtactivitychange.save()
          authenticateService.clearCachedRequestmaps()
        /*check if radio button for ROLE_COMPANY_MANAGER OFF and this instance HAVE ROLE_COMPANY_MANAGER*/
        if(!(params.dtactivitychange_ROLE_COMPANY_MANAGER) && dtactivitychange.configAttribute.contains('ROLE_COMPANY_MANAGER'))
          dtactivitychange.configAttribute-=",ROLE_COMPANY_MANAGER"
          dtactivitychange.save()
          authenticateService.clearCachedRequestmaps()

        /*check if radio button for ROLE_SUBSIDIARY_MANAGER ON and this instance DON'T have ROLE_SUBSIDIARY_MANAGER*/
        if(params.dtactivitychange_ROLE_SUBSIDIARY_MANAGER && !(dtactivitychange.configAttribute.contains('ROLE_SUBSIDIARY_MANAGER')))
          dtactivitychange.configAttribute+=",ROLE_SUBSIDIARY_MANAGER"
          dtactivitychange.save()
          authenticateService.clearCachedRequestmaps()
        /*check if radio button for ROLE_SUBSIDIARY_MANAGER OFF and this instance HAVE ROLE_SUBSIDIARY_MANAGER*/
        if(!(params.dtactivitychange_ROLE_SUBSIDIARY_MANAGER) && dtactivitychange.configAttribute.contains('ROLE_SUBSIDIARY_MANAGER'))
          dtactivitychange.configAttribute-=",ROLE_SUBSIDIARY_MANAGER"
          dtactivitychange.save()
          authenticateService.clearCachedRequestmaps()

        /*check if radio button for ROLE_DEPARTMENT_MANAGER ON and this instance DON'T have ROLE_DEPARTMENT_MANAGER*/
        if(params.dtactivitychange_ROLE_DEPARTMENT_MANAGER && !(dtactivitychange.configAttribute.contains('ROLE_DEPARTMENT_MANAGER')))
          dtactivitychange.configAttribute+=",ROLE_DEPARTMENT_MANAGER"
          dtactivitychange.save()
          authenticateService.clearCachedRequestmaps()
        /*check if radio button for ROLE_DEPARTMENT_MANAGER OFF and this instance HAVE ROLE_DEPARTMENT_MANAGER*/
        if(!(params.dtactivitychange_ROLE_DEPARTMENT_MANAGER) && dtactivitychange.configAttribute.contains('ROLE_DEPARTMENT_MANAGER'))
          dtactivitychange.configAttribute-=",ROLE_DEPARTMENT_MANAGER"
          dtactivitychange.save()
          authenticateService.clearCachedRequestmaps()
      /*=======================================================================================================*/
      /*=======================================================================================================*/
      /*DtRequestMap instance for DtDriver URL*/
      def dtdriver = DtRequestMap.findByUrl("/dtdriver/**")

        /*check if radio button for ROLE_DUMMY ON and this instance DON'T have ROLE_DUMMY*/
        if(params.dtdriver_ROLE_DUMMY && !(dtdriver.configAttribute.contains('ROLE_DUMMY')))
          dtdriver.configAttribute+=",ROLE_DUMMY"
          dtdriver.save()
          authenticateService.clearCachedRequestmaps()
        /*check if radio button for ROLE_DUMMY OFF and this instance HAVE ROLE_DUMMY*/
        if(!(params.dtdriver_ROLE_DUMMY) && dtdriver.configAttribute.contains('ROLE_DUMMY'))
          dtdriver.configAttribute-=",ROLE_DUMMY"
          dtdriver.save()
          authenticateService.clearCachedRequestmaps()

        /*check if radio button for ROLE_COMPANY_MANAGER ON and this instance DON'T have ROLE_COMPANY_MANAGER*/
        if(params.dtdriver_ROLE_COMPANY_MANAGER && !(dtdriver.configAttribute.contains('ROLE_COMPANY_MANAGER')))
          dtdriver.configAttribute+=",ROLE_COMPANY_MANAGER"
          dtdriver.save()
          authenticateService.clearCachedRequestmaps()
        /*check if radio button for ROLE_COMPANY_MANAGER OFF and this instance HAVE ROLE_COMPANY_MANAGER*/
        if(!(params.dtdriver_ROLE_COMPANY_MANAGER) && dtdriver.configAttribute.contains('ROLE_COMPANY_MANAGER'))
          dtdriver.configAttribute-=",ROLE_COMPANY_MANAGER"
          dtdriver.save()
          authenticateService.clearCachedRequestmaps()

        /*check if radio button for ROLE_SUBSIDIARY_MANAGER ON and this instance DON'T have ROLE_SUBSIDIARY_MANAGER*/
        if(params.dtdriver_ROLE_SUBSIDIARY_MANAGER && !(dtdriver.configAttribute.contains('ROLE_SUBSIDIARY_MANAGER')))
          dtdriver.configAttribute+=",ROLE_SUBSIDIARY_MANAGER"
          dtdriver.save()
          authenticateService.clearCachedRequestmaps()
        /*check if radio button for ROLE_SUBSIDIARY_MANAGER OFF and this instance HAVE ROLE_SUBSIDIARY_MANAGER*/
        if(!(params.dtdriver_ROLE_SUBSIDIARY_MANAGER) && dtdriver.configAttribute.contains('ROLE_SUBSIDIARY_MANAGER'))
          dtdriver.configAttribute-=",ROLE_SUBSIDIARY_MANAGER"
          dtdriver.save()
          authenticateService.clearCachedRequestmaps()

        /*check if radio button for ROLE_DEPARTMENT_MANAGER ON and this instance DON'T have ROLE_DEPARTMENT_MANAGER*/
        if(params.dtdriver_ROLE_DEPARTMENT_MANAGER && !(dtdriver.configAttribute.contains('ROLE_DEPARTMENT_MANAGER')))
          dtdriver.configAttribute+=",ROLE_DEPARTMENT_MANAGER"
          dtdriver.save()
          authenticateService.clearCachedRequestmaps()
        /*check if radio button for ROLE_DEPARTMENT_MANAGER OFF and this instance HAVE ROLE_DEPARTMENT_MANAGER*/
        if(!(params.dtdriver_ROLE_DEPARTMENT_MANAGER) && dtdriver.configAttribute.contains('ROLE_DEPARTMENT_MANAGER'))
          dtdriver.configAttribute-=",ROLE_DEPARTMENT_MANAGER"
          dtdriver.save()
          authenticateService.clearCachedRequestmaps()
      /*=======================================================================================================*/

      //FILE UPLOAD
      /*=======================================================================================================*/
      /*DtRequestMap instance for XmlFileUpload URL*/
      def xmlfileupload = DtRequestMap.findByUrl("/xmlfileupload/**")

        /*check if radio button for ROLE_DUMMY ON and this instance DON'T have ROLE_DUMMY*/
        if(params.xmlfileupload_ROLE_DUMMY && !(xmlfileupload.configAttribute.contains('ROLE_DUMMY')))
          xmlfileupload.configAttribute+=",ROLE_DUMMY"
          xmlfileupload.save()
          authenticateService.clearCachedRequestmaps()
        /*check if radio button for ROLE_DUMMY OFF and this instance HAVE ROLE_DUMMY*/
        if(!(params.xmlfileupload_ROLE_DUMMY) && xmlfileupload.configAttribute.contains('ROLE_DUMMY'))
          xmlfileupload.configAttribute-=",ROLE_DUMMY"
          xmlfileupload.save()
          authenticateService.clearCachedRequestmaps()

        /*check if radio button for ROLE_COMPANY_MANAGER ON and this instance DON'T have ROLE_COMPANY_MANAGER*/
        if(params.xmlfileupload_ROLE_COMPANY_MANAGER && !(xmlfileupload.configAttribute.contains('ROLE_COMPANY_MANAGER')))
          xmlfileupload.configAttribute+=",ROLE_COMPANY_MANAGER"
          xmlfileupload.save()
          authenticateService.clearCachedRequestmaps()
        /*check if radio button for ROLE_COMPANY_MANAGER OFF and this instance HAVE ROLE_COMPANY_MANAGER*/
        if(!(params.xmlfileupload_ROLE_COMPANY_MANAGER) && xmlfileupload.configAttribute.contains('ROLE_COMPANY_MANAGER'))
          xmlfileupload.configAttribute-=",ROLE_COMPANY_MANAGER"
          xmlfileupload.save()
          authenticateService.clearCachedRequestmaps()

        /*check if radio button for ROLE_SUBSIDIARY_MANAGER ON and this instance DON'T have ROLE_SUBSIDIARY_MANAGER*/
        if(params.xmlfileupload_ROLE_SUBSIDIARY_MANAGER && !(xmlfileupload.configAttribute.contains('ROLE_SUBSIDIARY_MANAGER')))
          xmlfileupload.configAttribute+=",ROLE_SUBSIDIARY_MANAGER"
          xmlfileupload.save()
          authenticateService.clearCachedRequestmaps()
        /*check if radio button for ROLE_SUBSIDIARY_MANAGER OFF and this instance HAVE ROLE_SUBSIDIARY_MANAGER*/
        if(!(params.xmlfileupload_ROLE_SUBSIDIARY_MANAGER) && xmlfileupload.configAttribute.contains('ROLE_SUBSIDIARY_MANAGER'))
          xmlfileupload.configAttribute-=",ROLE_SUBSIDIARY_MANAGER"
          xmlfileupload.save()
          authenticateService.clearCachedRequestmaps()

        /*check if radio button for ROLE_DEPARTMENT_MANAGER ON and this instance DON'T have ROLE_DEPARTMENT_MANAGER*/
        if(params.xmlfileupload_ROLE_DEPARTMENT_MANAGER && !(xmlfileupload.configAttribute.contains('ROLE_DEPARTMENT_MANAGER')))
          xmlfileupload.configAttribute+=",ROLE_DEPARTMENT_MANAGER"
          xmlfileupload.save()
          authenticateService.clearCachedRequestmaps()
        /*check if radio button for ROLE_DEPARTMENT_MANAGER OFF and this instance HAVE ROLE_DEPARTMENT_MANAGER*/
        if(!(params.xmlfileupload_ROLE_DEPARTMENT_MANAGER) && xmlfileupload.configAttribute.contains('ROLE_DEPARTMENT_MANAGER'))
          xmlfileupload.configAttribute-=",ROLE_DEPARTMENT_MANAGER"
          xmlfileupload.save()
          authenticateService.clearCachedRequestmaps()
      /*=======================================================================================================*/
      /*=======================================================================================================*/
      /*DtRequestMap instance for DddFileUpload URL*/
      def dddfileupload = DtRequestMap.findByUrl("/dddfileupload/**")

        /*check if radio button for ROLE_DUMMY ON and this instance DON'T have ROLE_DUMMY*/
        if(params.dddfileupload_ROLE_DUMMY && !(dddfileupload.configAttribute.contains('ROLE_DUMMY')))
          dddfileupload.configAttribute+=",ROLE_DUMMY"
          dddfileupload.save()
          authenticateService.clearCachedRequestmaps()
        /*check if radio button for ROLE_DUMMY OFF and this instance HAVE ROLE_DUMMY*/
        if(!(params.dddfileupload_ROLE_DUMMY) && dddfileupload.configAttribute.contains('ROLE_DUMMY'))
          dddfileupload.configAttribute-=",ROLE_DUMMY"
          dddfileupload.save()
          authenticateService.clearCachedRequestmaps()

        /*check if radio button for ROLE_COMPANY_MANAGER ON and this instance DON'T have ROLE_COMPANY_MANAGER*/
        if(params.dddfileupload_ROLE_COMPANY_MANAGER && !(dddfileupload.configAttribute.contains('ROLE_COMPANY_MANAGER')))
          dddfileupload.configAttribute+=",ROLE_COMPANY_MANAGER"
          dddfileupload.save()
          authenticateService.clearCachedRequestmaps()
        /*check if radio button for ROLE_COMPANY_MANAGER OFF and this instance HAVE ROLE_COMPANY_MANAGER*/
        if(!(params.dddfileupload_ROLE_COMPANY_MANAGER) && dddfileupload.configAttribute.contains('ROLE_COMPANY_MANAGER'))
          dddfileupload.configAttribute-=",ROLE_COMPANY_MANAGER"
          dddfileupload.save()
          authenticateService.clearCachedRequestmaps()

        /*check if radio button for ROLE_SUBSIDIARY_MANAGER ON and this instance DON'T have ROLE_SUBSIDIARY_MANAGER*/
        if(params.dddfileupload_ROLE_SUBSIDIARY_MANAGER && !(dddfileupload.configAttribute.contains('ROLE_SUBSIDIARY_MANAGER')))
          dddfileupload.configAttribute+=",ROLE_SUBSIDIARY_MANAGER"
          dddfileupload.save()
          authenticateService.clearCachedRequestmaps()
        /*check if radio button for ROLE_SUBSIDIARY_MANAGER OFF and this instance HAVE ROLE_SUBSIDIARY_MANAGER*/
        if(!(params.dddfileupload_ROLE_SUBSIDIARY_MANAGER) && dddfileupload.configAttribute.contains('ROLE_SUBSIDIARY_MANAGER'))
          dddfileupload.configAttribute-=",ROLE_SUBSIDIARY_MANAGER"
          dddfileupload.save()
          authenticateService.clearCachedRequestmaps()

        /*check if radio button for ROLE_DEPARTMENT_MANAGER ON and this instance DON'T have ROLE_DEPARTMENT_MANAGER*/
        if(params.dddfileupload_ROLE_DEPARTMENT_MANAGER && !(dddfileupload.configAttribute.contains('ROLE_DEPARTMENT_MANAGER')))
          dddfileupload.configAttribute+=",ROLE_DEPARTMENT_MANAGER"
          dddfileupload.save()
          authenticateService.clearCachedRequestmaps()
        /*check if radio button for ROLE_DEPARTMENT_MANAGER OFF and this instance HAVE ROLE_DEPARTMENT_MANAGER*/
        if(!(params.dddfileupload_ROLE_DEPARTMENT_MANAGER) && dddfileupload.configAttribute.contains('ROLE_DEPARTMENT_MANAGER'))
          dddfileupload.configAttribute-=",ROLE_DEPARTMENT_MANAGER"
          dddfileupload.save()
          authenticateService.clearCachedRequestmaps()
      /*=======================================================================================================*/

      //USER MANAGEMENT
      /*=======================================================================================================*/
      /*DtRequestMap instance for DtUser URL*/
      def dtuser = DtRequestMap.findByUrl("/dtuser/**")

        /*check if radio button for ROLE_DUMMY ON and this instance DON'T have ROLE_DUMMY*/
        if(params.dtuser_ROLE_DUMMY && !(dtuser.configAttribute.contains('ROLE_DUMMY')))
          dtuser.configAttribute+=",ROLE_DUMMY"
          dtuser.save()
          authenticateService.clearCachedRequestmaps()
        /*check if radio button for ROLE_DUMMY OFF and this instance HAVE ROLE_DUMMY*/
        if(!(params.dtuser_ROLE_DUMMY) && dtuser.configAttribute.contains('ROLE_DUMMY'))
          dtuser.configAttribute-=",ROLE_DUMMY"
          dtuser.save()
          authenticateService.clearCachedRequestmaps()

        /*check if radio button for ROLE_COMPANY_MANAGER ON and this instance DON'T have ROLE_COMPANY_MANAGER*/
        if(params.dtuser_ROLE_COMPANY_MANAGER && !(dtuser.configAttribute.contains('ROLE_COMPANY_MANAGER')))
          dtuser.configAttribute+=",ROLE_COMPANY_MANAGER"
          dtuser.save()
          authenticateService.clearCachedRequestmaps()
        /*check if radio button for ROLE_COMPANY_MANAGER OFF and this instance HAVE ROLE_COMPANY_MANAGER*/
        if(!(params.dtuser_ROLE_COMPANY_MANAGER) && dtuser.configAttribute.contains('ROLE_COMPANY_MANAGER'))
          dtuser.configAttribute-=",ROLE_COMPANY_MANAGER"
          dtuser.save()
          authenticateService.clearCachedRequestmaps()

        /*check if radio button for ROLE_SUBSIDIARY_MANAGER ON and this instance DON'T have ROLE_SUBSIDIARY_MANAGER*/
        if(params.dtuser_ROLE_SUBSIDIARY_MANAGER && !(dtuser.configAttribute.contains('ROLE_SUBSIDIARY_MANAGER')))
          dtuser.configAttribute+=",ROLE_SUBSIDIARY_MANAGER"
          dtuser.save()
          authenticateService.clearCachedRequestmaps()
        /*check if radio button for ROLE_SUBSIDIARY_MANAGER OFF and this instance HAVE ROLE_SUBSIDIARY_MANAGER*/
        if(!(params.dtuser_ROLE_SUBSIDIARY_MANAGER) && dtuser.configAttribute.contains('ROLE_SUBSIDIARY_MANAGER'))
          dtuser.configAttribute-=",ROLE_SUBSIDIARY_MANAGER"
          dtuser.save()
          authenticateService.clearCachedRequestmaps()

        /*check if radio button for ROLE_DEPARTMENT_MANAGER ON and this instance DON'T have ROLE_DEPARTMENT_MANAGER*/
        if(params.dtuser_ROLE_DEPARTMENT_MANAGER && !(dtuser.configAttribute.contains('ROLE_DEPARTMENT_MANAGER')))
          dtuser.configAttribute+=",ROLE_DEPARTMENT_MANAGER"
          dtuser.save()
          authenticateService.clearCachedRequestmaps()
        /*check if radio button for ROLE_DEPARTMENT_MANAGER OFF and this instance HAVE ROLE_DEPARTMENT_MANAGER*/
        if(!(params.dtuser_ROLE_DEPARTMENT_MANAGER) && dtuser.configAttribute.contains('ROLE_DEPARTMENT_MANAGER'))
          dtuser.configAttribute-=",ROLE_DEPARTMENT_MANAGER"
          dtuser.save()
          authenticateService.clearCachedRequestmaps()
      /*=======================================================================================================*/
      /*=======================================================================================================*/
      /*DtRequestMap instance for DtRole URL*/
      def dtrole = DtRequestMap.findByUrl("/dtrole/**")

        /*check if radio button for ROLE_DUMMY ON and this instance DON'T have ROLE_DUMMY*/
        if(params.dtrole_ROLE_DUMMY && !(dtrole.configAttribute.contains('ROLE_DUMMY')))
          dtrole.configAttribute+=",ROLE_DUMMY"
          dtrole.save()
          authenticateService.clearCachedRequestmaps()
        /*check if radio button for ROLE_DUMMY OFF and this instance HAVE ROLE_DUMMY*/
        if(!(params.dtrole_ROLE_DUMMY) && dtrole.configAttribute.contains('ROLE_DUMMY'))
          dtrole.configAttribute-=",ROLE_DUMMY"
          dtrole.save()
          authenticateService.clearCachedRequestmaps()

        /*check if radio button for ROLE_COMPANY_MANAGER ON and this instance DON'T have ROLE_COMPANY_MANAGER*/
        if(params.dtrole_ROLE_COMPANY_MANAGER && !(dtrole.configAttribute.contains('ROLE_COMPANY_MANAGER')))
          dtrole.configAttribute+=",ROLE_COMPANY_MANAGER"
          dtrole.save()
          authenticateService.clearCachedRequestmaps()
        /*check if radio button for ROLE_COMPANY_MANAGER OFF and this instance HAVE ROLE_COMPANY_MANAGER*/
        if(!(params.dtrole_ROLE_COMPANY_MANAGER) && dtrole.configAttribute.contains('ROLE_COMPANY_MANAGER'))
          dtrole.configAttribute-=",ROLE_COMPANY_MANAGER"
          dtrole.save()
          authenticateService.clearCachedRequestmaps()

        /*check if radio button for ROLE_SUBSIDIARY_MANAGER ON and this instance DON'T have ROLE_SUBSIDIARY_MANAGER*/
        if(params.dtrole_ROLE_SUBSIDIARY_MANAGER && !(dtrole.configAttribute.contains('ROLE_SUBSIDIARY_MANAGER')))
          dtrole.configAttribute+=",ROLE_SUBSIDIARY_MANAGER"
          dtrole.save()
          authenticateService.clearCachedRequestmaps()
        /*check if radio button for ROLE_SUBSIDIARY_MANAGER OFF and this instance HAVE ROLE_SUBSIDIARY_MANAGER*/
        if(!(params.dtrole_ROLE_SUBSIDIARY_MANAGER) && dtrole.configAttribute.contains('ROLE_SUBSIDIARY_MANAGER'))
          dtrole.configAttribute-=",ROLE_SUBSIDIARY_MANAGER"
          dtrole.save()
          authenticateService.clearCachedRequestmaps()

        /*check if radio button for ROLE_DEPARTMENT_MANAGER ON and this instance DON'T have ROLE_DEPARTMENT_MANAGER*/
        if(params.dtrole_ROLE_DEPARTMENT_MANAGER && !(dtrole.configAttribute.contains('ROLE_DEPARTMENT_MANAGER')))
          dtrole.configAttribute+=",ROLE_DEPARTMENT_MANAGER"
          dtrole.save()
          authenticateService.clearCachedRequestmaps()
        /*check if radio button for ROLE_DEPARTMENT_MANAGER OFF and this instance HAVE ROLE_DEPARTMENT_MANAGER*/
        if(!(params.dtrole_ROLE_DEPARTMENT_MANAGER) && dtrole.configAttribute.contains('ROLE_DEPARTMENT_MANAGER'))
          dtrole.configAttribute-=",ROLE_DEPARTMENT_MANAGER"
          dtrole.save()
          authenticateService.clearCachedRequestmaps()
      /*=======================================================================================================*/
      /*=======================================================================================================*/
      /*DtRequestMap instance for DtRequestMap URL*/
      def dtrequestmap = DtRequestMap.findByUrl("/dtrequestmap/**")

        /*check if radio button for ROLE_DUMMY ON and this instance DON'T have ROLE_DUMMY*/
        if(params.dtrequestmap_ROLE_DUMMY && !(dtrequestmap.configAttribute.contains('ROLE_DUMMY')))
          dtrequestmap.configAttribute+=",ROLE_DUMMY"
          dtrequestmap.save()
          authenticateService.clearCachedRequestmaps()
        /*check if radio button for ROLE_DUMMY OFF and this instance HAVE ROLE_DUMMY*/
        if(!(params.dtrequestmap_ROLE_DUMMY) && dtrequestmap.configAttribute.contains('ROLE_DUMMY'))
          dtrequestmap.configAttribute-=",ROLE_DUMMY"
          dtrequestmap.save()
          authenticateService.clearCachedRequestmaps()

        /*check if radio button for ROLE_COMPANY_MANAGER ON and this instance DON'T have ROLE_COMPANY_MANAGER*/
        if(params.dtrequestmap_ROLE_COMPANY_MANAGER && !(dtrequestmap.configAttribute.contains('ROLE_COMPANY_MANAGER')))
          dtrequestmap.configAttribute+=",ROLE_COMPANY_MANAGER"
          dtrequestmap.save()
          authenticateService.clearCachedRequestmaps()
        /*check if radio button for ROLE_COMPANY_MANAGER OFF and this instance HAVE ROLE_COMPANY_MANAGER*/
        if(!(params.dtrequestmap_ROLE_COMPANY_MANAGER) && dtrequestmap.configAttribute.contains('ROLE_COMPANY_MANAGER'))
          dtrequestmap.configAttribute-=",ROLE_COMPANY_MANAGER"
          dtrequestmap.save()
          authenticateService.clearCachedRequestmaps()

        /*check if radio button for ROLE_SUBSIDIARY_MANAGER ON and this instance DON'T have ROLE_SUBSIDIARY_MANAGER*/
        if(params.dtrequestmap_ROLE_SUBSIDIARY_MANAGER && !(dtrequestmap.configAttribute.contains('ROLE_SUBSIDIARY_MANAGER')))
          dtrequestmap.configAttribute+=",ROLE_SUBSIDIARY_MANAGER"
          dtrequestmap.save()
          authenticateService.clearCachedRequestmaps()
        /*check if radio button for ROLE_SUBSIDIARY_MANAGER OFF and this instance HAVE ROLE_SUBSIDIARY_MANAGER*/
        if(!(params.dtrequestmap_ROLE_SUBSIDIARY_MANAGER) && dtrequestmap.configAttribute.contains('ROLE_SUBSIDIARY_MANAGER'))
          dtrequestmap.configAttribute-=",ROLE_SUBSIDIARY_MANAGER"
          dtrequestmap.save()
          authenticateService.clearCachedRequestmaps()

        /*check if radio button for ROLE_DEPARTMENT_MANAGER ON and this instance DON'T have ROLE_DEPARTMENT_MANAGER*/
        if(params.dtrequestmap_ROLE_DEPARTMENT_MANAGER && !(dtrequestmap.configAttribute.contains('ROLE_DEPARTMENT_MANAGER')))
          dtrequestmap.configAttribute+=",ROLE_DEPARTMENT_MANAGER"
          dtrequestmap.save()
          authenticateService.clearCachedRequestmaps()
        /*check if radio button for ROLE_DEPARTMENT_MANAGER OFF and this instance HAVE ROLE_DEPARTMENT_MANAGER*/
        if(!(params.dtrequestmap_ROLE_DEPARTMENT_MANAGER) && dtrequestmap.configAttribute.contains('ROLE_DEPARTMENT_MANAGER'))
          dtrequestmap.configAttribute-=",ROLE_DEPARTMENT_MANAGER"
          dtrequestmap.save()
          authenticateService.clearCachedRequestmaps()
      /*=======================================================================================================*/

      //MASTER DATA
      /*=======================================================================================================*/
      /*DtRequestMap instance for DtCompany URL*/
      def dtcompany = DtRequestMap.findByUrl("/dtcompany/**")

        /*check if radio button for ROLE_DUMMY ON and this instance DON'T have ROLE_DUMMY*/
        if(params.dtcompany_ROLE_DUMMY && !(dtcompany.configAttribute.contains('ROLE_DUMMY')))
          dtcompany.configAttribute+=",ROLE_DUMMY"
          dtcompany.save()
          authenticateService.clearCachedRequestmaps()
        /*check if radio button for ROLE_DUMMY OFF and this instance HAVE ROLE_DUMMY*/
        if(!(params.dtcompany_ROLE_DUMMY) && dtcompany.configAttribute.contains('ROLE_DUMMY'))
          dtcompany.configAttribute-=",ROLE_DUMMY"
          dtcompany.save()
          authenticateService.clearCachedRequestmaps()

        /*check if radio button for ROLE_COMPANY_MANAGER ON and this instance DON'T have ROLE_COMPANY_MANAGER*/
        if(params.dtcompany_ROLE_COMPANY_MANAGER && !(dtcompany.configAttribute.contains('ROLE_COMPANY_MANAGER')))
          dtcompany.configAttribute+=",ROLE_COMPANY_MANAGER"
          dtcompany.save()
          authenticateService.clearCachedRequestmaps()
        /*check if radio button for ROLE_COMPANY_MANAGER OFF and this instance HAVE ROLE_COMPANY_MANAGER*/
        if(!(params.dtcompany_ROLE_COMPANY_MANAGER) && dtcompany.configAttribute.contains('ROLE_COMPANY_MANAGER'))
          dtcompany.configAttribute-=",ROLE_COMPANY_MANAGER"
          dtcompany.save()
          authenticateService.clearCachedRequestmaps()

        /*check if radio button for ROLE_SUBSIDIARY_MANAGER ON and this instance DON'T have ROLE_SUBSIDIARY_MANAGER*/
        if(params.dtcompany_ROLE_SUBSIDIARY_MANAGER && !(dtcompany.configAttribute.contains('ROLE_SUBSIDIARY_MANAGER')))
          dtcompany.configAttribute+=",ROLE_SUBSIDIARY_MANAGER"
          dtcompany.save()
          authenticateService.clearCachedRequestmaps()
        /*check if radio button for ROLE_SUBSIDIARY_MANAGER OFF and this instance HAVE ROLE_SUBSIDIARY_MANAGER*/
        if(!(params.dtcompany_ROLE_SUBSIDIARY_MANAGER) && dtcompany.configAttribute.contains('ROLE_SUBSIDIARY_MANAGER'))
          dtcompany.configAttribute-=",ROLE_SUBSIDIARY_MANAGER"
          dtcompany.save()
          authenticateService.clearCachedRequestmaps()

        /*check if radio button for ROLE_DEPARTMENT_MANAGER ON and this instance DON'T have ROLE_DEPARTMENT_MANAGER*/
        if(params.dtcompany_ROLE_DEPARTMENT_MANAGER && !(dtcompany.configAttribute.contains('ROLE_DEPARTMENT_MANAGER')))
          dtcompany.configAttribute+=",ROLE_DEPARTMENT_MANAGER"
          dtcompany.save()
          authenticateService.clearCachedRequestmaps()
        /*check if radio button for ROLE_DEPARTMENT_MANAGER OFF and this instance HAVE ROLE_DEPARTMENT_MANAGER*/
        if(!(params.dtcompany_ROLE_DEPARTMENT_MANAGER) && dtcompany.configAttribute.contains('ROLE_DEPARTMENT_MANAGER'))
          dtcompany.configAttribute-=",ROLE_DEPARTMENT_MANAGER"
          dtcompany.save()
          authenticateService.clearCachedRequestmaps()
      /*=======================================================================================================*/
      /*=======================================================================================================*/
      /*DtRequestMap instance for DtSubsidiary URL*/
      def dtsubsidiary = DtRequestMap.findByUrl("/dtsubsidiary/**")

        /*check if radio button for ROLE_DUMMY ON and this instance DON'T have ROLE_DUMMY*/
        if(params.dtsubsidiary_ROLE_DUMMY && !(dtsubsidiary.configAttribute.contains('ROLE_DUMMY')))
          dtsubsidiary.configAttribute+=",ROLE_DUMMY"
          dtsubsidiary.save()
          authenticateService.clearCachedRequestmaps()
        /*check if radio button for ROLE_DUMMY OFF and this instance HAVE ROLE_DUMMY*/
        if(!(params.dtsubsidiary_ROLE_DUMMY) && dtsubsidiary.configAttribute.contains('ROLE_DUMMY'))
          dtsubsidiary.configAttribute-=",ROLE_DUMMY"
          dtsubsidiary.save()
          authenticateService.clearCachedRequestmaps()

        /*check if radio button for ROLE_COMPANY_MANAGER ON and this instance DON'T have ROLE_COMPANY_MANAGER*/
        if(params.dtsubsidiary_ROLE_COMPANY_MANAGER && !(dtsubsidiary.configAttribute.contains('ROLE_COMPANY_MANAGER')))
          dtsubsidiary.configAttribute+=",ROLE_COMPANY_MANAGER"
          dtsubsidiary.save()
          authenticateService.clearCachedRequestmaps()
        /*check if radio button for ROLE_COMPANY_MANAGER OFF and this instance HAVE ROLE_COMPANY_MANAGER*/
        if(!(params.dtsubsidiary_ROLE_COMPANY_MANAGER) && dtsubsidiary.configAttribute.contains('ROLE_COMPANY_MANAGER'))
          dtsubsidiary.configAttribute-=",ROLE_COMPANY_MANAGER"
          dtsubsidiary.save()
          authenticateService.clearCachedRequestmaps()

        /*check if radio button for ROLE_SUBSIDIARY_MANAGER ON and this instance DON'T have ROLE_SUBSIDIARY_MANAGER*/
        if(params.dtsubsidiary_ROLE_SUBSIDIARY_MANAGER && !(dtsubsidiary.configAttribute.contains('ROLE_SUBSIDIARY_MANAGER')))
          dtsubsidiary.configAttribute+=",ROLE_SUBSIDIARY_MANAGER"
          dtsubsidiary.save()
          authenticateService.clearCachedRequestmaps()
        /*check if radio button for ROLE_SUBSIDIARY_MANAGER OFF and this instance HAVE ROLE_SUBSIDIARY_MANAGER*/
        if(!(params.dtsubsidiary_ROLE_SUBSIDIARY_MANAGER) && dtsubsidiary.configAttribute.contains('ROLE_SUBSIDIARY_MANAGER'))
          dtsubsidiary.configAttribute-=",ROLE_SUBSIDIARY_MANAGER"
          dtsubsidiary.save()
          authenticateService.clearCachedRequestmaps()

        /*check if radio button for ROLE_DEPARTMENT_MANAGER ON and this instance DON'T have ROLE_DEPARTMENT_MANAGER*/
        if(params.dtsubsidiary_ROLE_DEPARTMENT_MANAGER && !(dtsubsidiary.configAttribute.contains('ROLE_DEPARTMENT_MANAGER')))
          dtsubsidiary.configAttribute+=",ROLE_DEPARTMENT_MANAGER"
          dtsubsidiary.save()
          authenticateService.clearCachedRequestmaps()
        /*check if radio button for ROLE_DEPARTMENT_MANAGER OFF and this instance HAVE ROLE_DEPARTMENT_MANAGER*/
        if(!(params.dtsubsidiary_ROLE_DEPARTMENT_MANAGER) && dtsubsidiary.configAttribute.contains('ROLE_DEPARTMENT_MANAGER'))
          dtsubsidiary.configAttribute-=",ROLE_DEPARTMENT_MANAGER"
          dtsubsidiary.save()
          authenticateService.clearCachedRequestmaps()
      /*=======================================================================================================*/
      /*=======================================================================================================*/
      /*DtRequestMap instance for DtDepartment URL*/
      def dtdepartment = DtRequestMap.findByUrl("/dtdepartment/**")

        /*check if radio button for ROLE_DUMMY ON and this instance DON'T have ROLE_DUMMY*/
        if(params.dtdepartment_ROLE_DUMMY && !(dtdepartment.configAttribute.contains('ROLE_DUMMY')))
          dtdepartment.configAttribute+=",ROLE_DUMMY"
          dtdepartment.save()
          authenticateService.clearCachedRequestmaps()
        /*check if radio button for ROLE_DUMMY OFF and this instance HAVE ROLE_DUMMY*/
        if(!(params.dtdepartment_ROLE_DUMMY) && dtdepartment.configAttribute.contains('ROLE_DUMMY'))
          dtdepartment.configAttribute-=",ROLE_DUMMY"
          dtdepartment.save()
          authenticateService.clearCachedRequestmaps()

        /*check if radio button for ROLE_COMPANY_MANAGER ON and this instance DON'T have ROLE_COMPANY_MANAGER*/
        if(params.dtdepartment_ROLE_COMPANY_MANAGER && !(dtdepartment.configAttribute.contains('ROLE_COMPANY_MANAGER')))
          dtdepartment.configAttribute+=",ROLE_COMPANY_MANAGER"
          dtdepartment.save()
          authenticateService.clearCachedRequestmaps()
        /*check if radio button for ROLE_COMPANY_MANAGER OFF and this instance HAVE ROLE_COMPANY_MANAGER*/
        if(!(params.dtdepartment_ROLE_COMPANY_MANAGER) && dtdepartment.configAttribute.contains('ROLE_COMPANY_MANAGER'))
          dtdepartment.configAttribute-=",ROLE_COMPANY_MANAGER"
          dtdepartment.save()
          authenticateService.clearCachedRequestmaps()

        /*check if radio button for ROLE_SUBSIDIARY_MANAGER ON and this instance DON'T have ROLE_SUBSIDIARY_MANAGER*/
        if(params.dtdepartment_ROLE_SUBSIDIARY_MANAGER && !(dtdepartment.configAttribute.contains('ROLE_SUBSIDIARY_MANAGER')))
          dtdepartment.configAttribute+=",ROLE_SUBSIDIARY_MANAGER"
          dtdepartment.save()
          authenticateService.clearCachedRequestmaps()
        /*check if radio button for ROLE_SUBSIDIARY_MANAGER OFF and this instance HAVE ROLE_SUBSIDIARY_MANAGER*/
        if(!(params.dtdepartment_ROLE_SUBSIDIARY_MANAGER) && dtdepartment.configAttribute.contains('ROLE_SUBSIDIARY_MANAGER'))
          dtdepartment.configAttribute-=",ROLE_SUBSIDIARY_MANAGER"
          dtdepartment.save()
          authenticateService.clearCachedRequestmaps()

        /*check if radio button for ROLE_DEPARTMENT_MANAGER ON and this instance DON'T have ROLE_DEPARTMENT_MANAGER*/
        if(params.dtdepartment_ROLE_DEPARTMENT_MANAGER && !(dtdepartment.configAttribute.contains('ROLE_DEPARTMENT_MANAGER')))
          dtdepartment.configAttribute+=",ROLE_DEPARTMENT_MANAGER"
          dtdepartment.save()
          authenticateService.clearCachedRequestmaps()
        /*check if radio button for ROLE_DEPARTMENT_MANAGER OFF and this instance HAVE ROLE_DEPARTMENT_MANAGER*/
        if(!(params.dtdepartment_ROLE_DEPARTMENT_MANAGER) && dtdepartment.configAttribute.contains('ROLE_DEPARTMENT_MANAGER'))
          dtdepartment.configAttribute-=",ROLE_DEPARTMENT_MANAGER"
          dtdepartment.save()
          authenticateService.clearCachedRequestmaps()
      /*=======================================================================================================*/
      /*=======================================================================================================*/
      /*DtRequestMap instance for DtPerson URL*/
      def dtperson = DtRequestMap.findByUrl("/dtperson/**")

        /*check if radio button for ROLE_DUMMY ON and this instance DON'T have ROLE_DUMMY*/
        if(params.dtperson_ROLE_DUMMY && !(dtperson.configAttribute.contains('ROLE_DUMMY')))
          dtperson.configAttribute+=",ROLE_DUMMY"
          dtperson.save()
          authenticateService.clearCachedRequestmaps()
        /*check if radio button for ROLE_DUMMY OFF and this instance HAVE ROLE_DUMMY*/
        if(!(params.dtperson_ROLE_DUMMY) && dtperson.configAttribute.contains('ROLE_DUMMY'))
          dtperson.configAttribute-=",ROLE_DUMMY"
          dtperson.save()
          authenticateService.clearCachedRequestmaps()

        /*check if radio button for ROLE_COMPANY_MANAGER ON and this instance DON'T have ROLE_COMPANY_MANAGER*/
        if(params.dtperson_ROLE_COMPANY_MANAGER && !(dtperson.configAttribute.contains('ROLE_COMPANY_MANAGER')))
          dtperson.configAttribute+=",ROLE_COMPANY_MANAGER"
          dtperson.save()
          authenticateService.clearCachedRequestmaps()
        /*check if radio button for ROLE_COMPANY_MANAGER OFF and this instance HAVE ROLE_COMPANY_MANAGER*/
        if(!(params.dtperson_ROLE_COMPANY_MANAGER) && dtperson.configAttribute.contains('ROLE_COMPANY_MANAGER'))
          dtperson.configAttribute-=",ROLE_COMPANY_MANAGER"
          dtperson.save()
          authenticateService.clearCachedRequestmaps()

        /*check if radio button for ROLE_SUBSIDIARY_MANAGER ON and this instance DON'T have ROLE_SUBSIDIARY_MANAGER*/
        if(params.dtperson_ROLE_SUBSIDIARY_MANAGER && !(dtperson.configAttribute.contains('ROLE_SUBSIDIARY_MANAGER')))
          dtperson.configAttribute+=",ROLE_SUBSIDIARY_MANAGER"
          dtperson.save()
          authenticateService.clearCachedRequestmaps()
        /*check if radio button for ROLE_SUBSIDIARY_MANAGER OFF and this instance HAVE ROLE_SUBSIDIARY_MANAGER*/
        if(!(params.dtperson_ROLE_SUBSIDIARY_MANAGER) && dtperson.configAttribute.contains('ROLE_SUBSIDIARY_MANAGER'))
          dtperson.configAttribute-=",ROLE_SUBSIDIARY_MANAGER"
          dtperson.save()
          authenticateService.clearCachedRequestmaps()

        /*check if radio button for ROLE_DEPARTMENT_MANAGER ON and this instance DON'T have ROLE_DEPARTMENT_MANAGER*/
        if(params.dtperson_ROLE_DEPARTMENT_MANAGER && !(dtperson.configAttribute.contains('ROLE_DEPARTMENT_MANAGER')))
          dtperson.configAttribute+=",ROLE_DEPARTMENT_MANAGER"
          dtperson.save()
          authenticateService.clearCachedRequestmaps()
        /*check if radio button for ROLE_DEPARTMENT_MANAGER OFF and this instance HAVE ROLE_DEPARTMENT_MANAGER*/
        if(!(params.dtperson_ROLE_DEPARTMENT_MANAGER) && dtperson.configAttribute.contains('ROLE_DEPARTMENT_MANAGER'))
          dtperson.configAttribute-=",ROLE_DEPARTMENT_MANAGER"
          dtperson.save()
          authenticateService.clearCachedRequestmaps()
      /*=======================================================================================================*/

      return[
//              dtactivitychange:dtactivitychange,
//             dtdriver:dtdriver,
//             xmlfileupload:xmlfileupload,
//             dddfileupload:dddfileupload,
//             dtuser:dtuser,
//             dtrole:dtrole,
//             dtrequestmap:dtrequestmap
              ]

    }
}
