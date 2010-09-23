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
    //TODO the changing don't take effect directly like I make change with dynamic request map
    //must have to restart application
    def modResults = {

      /*=======================================================================================================*/
      /*DtRequestMap instance for DtActivityChange URL*/
      def dtactivitychange = DtRequestMap.findByUrl("/dtactivitychange/**")

        /*check if radio button for role_user ON and this instance DON'T have role_user*/
        if(params.dtactivitychange_ROLE_USER && !(dtactivitychange.configAttribute.contains('ROLE_USER')))
          dtactivitychange.configAttribute+=",ROLE_USER"

        /*check if radio button for role_user OFF and this instance HAVE role_user*/
        if(!(params.dtactivitychange_ROLE_USER) && dtactivitychange.configAttribute.contains('ROLE_USER'))
          dtactivitychange.configAttribute-=",ROLE_USER"

        /*check if radio button for role_dummy ON and this instance DON'T have role_dummy*/
        if(params.dtactivitychange_ROLE_DUMMY && !(dtactivitychange.configAttribute.contains('ROLE_DUMMY')))
          dtactivitychange.configAttribute+=",ROLE_DUMMY"

        /*check if radio button for role_dummy OFF and this instance HAVE role_dummy*/
        if(!(params.dtactivitychange_ROLE_DUMMY) && dtactivitychange.configAttribute.contains('ROLE_DUMMY'))
          dtactivitychange.configAttribute-=",ROLE_DUMMY"
      /*=======================================================================================================*/


      /*=======================================================================================================*/
      /*DtRequestMap instance for DtDriver URL*/
      def dtdriver = DtRequestMap.findByUrl("/dtdriver/**")

        /*check if radio button for role_user ON and this instance DON'T have role_user*/
        if(params.dtdriver_ROLE_USER && !(dtdriver.configAttribute.contains('ROLE_USER')))
          dtdriver.configAttribute+=",ROLE_USER"

        /*check if radio button for role_user OFF and this instance HAVE role_user*/
        if(!(params.dtdriver_ROLE_USER) && dtdriver.configAttribute.contains('ROLE_USER'))
          dtdriver.configAttribute-=",ROLE_USER"

        /*check if radio button for role_dummy ON and this instance DON'T have role_dummy*/
        if(params.dtdriver_ROLE_DUMMY && !(dtdriver.configAttribute.contains('ROLE_DUMMY')))
          dtdriver.configAttribute+=",ROLE_DUMMY"

        /*check if radio button for role_dummy OFF and this instance HAVE role_dummy*/
        if(!(params.dtdriver_ROLE_DUMMY) && dtdriver.configAttribute.contains('ROLE_DUMMY'))
          dtdriver.configAttribute-=",ROLE_DUMMY"
      /*=======================================================================================================*/


      /*=======================================================================================================*/
      /*DtRequestMap instance for XmlFileUpload URL*/
      def xmlfileupload = DtRequestMap.findByUrl("/xmlfileupload/**")

        /*check if radio button for role_user ON and this instance DON'T have role_user*/
        if(params.xmlfileupload_ROLE_USER && !(xmlfileupload.configAttribute.contains('ROLE_USER')))
        {xmlfileupload.configAttribute+=",ROLE_USER"
          xmlfileupload.save()
        }
        /*check if radio button for role_user OFF and this instance HAVE role_user*/
        if(!(params.xmlfileupload_ROLE_USER) && xmlfileupload.configAttribute.contains('ROLE_USER'))
        {  xmlfileupload.configAttribute-=",ROLE_USER"
          xmlfileupload.save()
        }
        /*check if radio button for role_dummy ON and this instance DON'T have role_dummy*/
        if(params.xmlfileupload_ROLE_DUMMY && !(xmlfileupload.configAttribute.contains('ROLE_DUMMY')))
        {xmlfileupload.configAttribute+=",ROLE_DUMMY"
          xmlfileupload.save()
        }
        /*check if radio button for role_dummy OFF and this instance HAVE role_dummy*/
        if(!(params.xmlfileupload_ROLE_DUMMY) && xmlfileupload.configAttribute.contains('ROLE_DUMMY'))
        {xmlfileupload.configAttribute-=",ROLE_DUMMY"
          xmlfileupload.save()
        }
      /*=======================================================================================================*/


      /*=======================================================================================================*/
      /*DtRequestMap instance for DddFileUpload URL*/
      def dddfileupload = DtRequestMap.findByUrl("/dddfileupload/**")

        /*check if radio button for role_user ON and this instance DON'T have role_user*/
        if(params.dddfileupload_ROLE_USER && !(dddfileupload.configAttribute.contains('ROLE_USER')))
          dddfileupload.configAttribute+=",ROLE_USER"

        /*check if radio button for role_user OFF and this instance HAVE role_user*/
        if(!(params.dddfileupload_ROLE_USER) && dddfileupload.configAttribute.contains('ROLE_USER'))
          dddfileupload.configAttribute-=",ROLE_USER"

        /*check if radio button for role_dummy ON and this instance DON'T have role_dummy*/
        if(params.dddfileupload_ROLE_DUMMY && !(dddfileupload.configAttribute.contains('ROLE_DUMMY')))
          dddfileupload.configAttribute+=",ROLE_DUMMY"

        /*check if radio button for role_dummy OFF and this instance HAVE role_dummy*/
        if(!(params.dddfileupload_ROLE_DUMMY) && dddfileupload.configAttribute.contains('ROLE_DUMMY'))
          dddfileupload.configAttribute-=",ROLE_DUMMY"
      /*=======================================================================================================*/


      /*=======================================================================================================*/
      /*DtRequestMap instance for DtUser URL*/
      def dtuser = DtRequestMap.findByUrl("/dtuser/**")

        /*check if radio button for role_user ON and this instance DON'T have role_user*/
        if(params.dtuser_ROLE_USER && !(dtuser.configAttribute.contains('ROLE_USER')))
          dtuser.configAttribute+=",ROLE_USER"

        /*check if radio button for role_user OFF and this instance HAVE role_user*/
        if(!(params.dtuser_ROLE_USER) && dtuser.configAttribute.contains('ROLE_USER'))
          dtuser.configAttribute-=",ROLE_USER"

        /*check if radio button for role_dummy ON and this instance DON'T have role_dummy*/
        if(params.dtuser_ROLE_DUMMY && !(dtuser.configAttribute.contains('ROLE_DUMMY')))
          dtuser.configAttribute+=",ROLE_DUMMY"

        /*check if radio button for role_dummy OFF and this instance HAVE role_dummy*/
        if(!(params.dtuser_ROLE_DUMMY) && dtuser.configAttribute.contains('ROLE_DUMMY'))
          dtuser.configAttribute-=",ROLE_DUMMY"
      /*=======================================================================================================*/


      /*=======================================================================================================*/
      /*DtRequestMap instance for DtRole URL*/
      def dtrole = DtRequestMap.findByUrl("/dtrole/**")

        /*check if radio button for role_user ON and this instance DON'T have role_user*/
        if(params.dtrole_ROLE_USER && !(dtrole.configAttribute.contains('ROLE_USER')))
          dtrole.configAttribute+=",ROLE_USER"

        /*check if radio button for role_user OFF and this instance HAVE role_user*/
        if(!(params.dtrole_ROLE_USER) && dtrole.configAttribute.contains('ROLE_USER'))
          dtrole.configAttribute-=",ROLE_USER"

        /*check if radio button for role_dummy ON and this instance DON'T have role_dummy*/
        if(params.dtrole_ROLE_DUMMY && !(dtrole.configAttribute.contains('ROLE_DUMMY')))
          dtrole.configAttribute+=",ROLE_DUMMY"

        /*check if radio button for role_dummy OFF and this instance HAVE role_dummy*/
        if(!(params.dtrole_ROLE_DUMMY) && dtrole.configAttribute.contains('ROLE_DUMMY'))
          dtrole.configAttribute-=",ROLE_DUMMY"
      /*=======================================================================================================*/


      /*=======================================================================================================*/
      /*DtRequestMap instance for DtRequestMap URL*/
      def dtrequestmap = DtRequestMap.findByUrl("/dtrequestmap/**")

        /*check if radio button for role_user ON and this instance DON'T have role_user*/
        if(params.dtrequestmap_ROLE_USER && !(dtrequestmap.configAttribute.contains('ROLE_USER')))
          dtrequestmap.configAttribute+=",ROLE_USER"

        /*check if radio button for role_user OFF and this instance HAVE role_user*/
        if(!(params.dtrequestmap_ROLE_USER) && dtrequestmap.configAttribute.contains('ROLE_USER'))
          dtrequestmap.configAttribute-=",ROLE_USER"

        /*check if radio button for role_dummy ON and this instance DON'T have role_dummy*/
        if(params.dtrequestmap_ROLE_DUMMY && !(dtrequestmap.configAttribute.contains('ROLE_DUMMY')))
          dtrequestmap.configAttribute+=",ROLE_DUMMY"

        /*check if radio button for role_dummy OFF and this instance HAVE role_dummy*/
        if(!(params.dtrequestmap_ROLE_DUMMY) && dtrequestmap.configAttribute.contains('ROLE_DUMMY'))
          dtrequestmap.configAttribute-=",ROLE_DUMMY"
      /*=======================================================================================================*/

      return[dtactivitychange:dtactivitychange,
             dtdriver:dtdriver,
             xmlfileupload:xmlfileupload,
             dddfileupload:dddfileupload,
             dtuser:dtuser,
             dtrole:dtrole,
             dtrequestmap:dtrequestmap
              ]

    }
}
