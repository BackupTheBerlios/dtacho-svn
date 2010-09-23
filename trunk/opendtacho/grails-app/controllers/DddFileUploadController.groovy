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

class DddFileUploadController {

    def readXmlService

    def defaultAction = 'dddUploadForm'

    def dddUploadForm = {
    }

    def upload = {
        Iterator itr = request.getFileNames()

        while (itr.hasNext()) {
            // get item
            def fileitem = itr.next()

            // get filename
            def file = request.getFile( fileitem )

            if (file && !file.empty) {

                println file.getOriginalFilename()
                
                // save file
                // TODO: save file in archive, make base directory configurable

                // get system's temporary directory
                def tempdir = System.getProperty("java.io.tmpdir")

                if ( !(tempdir.endsWith("/") || tempdir.endsWith("\\")) ) {
                    tempdir = tempdir + System.getProperty("file.separator")
                }

                // filename
                def fileName = tempdir + file.getOriginalFilename()

                // dump file to temporary directory
                //file.transferTo( new java.io.File(fileName))

                // convert to XML
                //org.opendtacho.DDDQuery.Controller.main(fileName)

                // instantiate & configure XML converter
                def xmlconverter = new org.opendtacho.DDDQuery.Controller()
                org.opendtacho.DDDQuery.Config.setNoDetailedSpeedData( true )

                print "dddfiletype" + fileitem.substring(7,8) + ": "
                println request.getParameter( "dddfiletype" + fileitem.substring(7,8) )

                def dddfiletype = request.getParameter( "dddfiletype" + fileitem.substring(7,8) )
                def xmlconvertersrctype

                // set source data type accoriding to FORM data
                xmlconvertersrctype = null
                if ( dddfiletype.compareTo( "drivercard" ) == 0 ) {
                    xmlconvertersrctype = org.opendtacho.DDDQuery.DDDDataSource.SRC_TYPE_CARD
                } else if ( dddfiletype.compareTo( "vehicleunit" ) == 0 ) {
                    xmlconvertersrctype = org.opendtacho.DDDQuery.DDDDataSource.SRC_TYPE_VU
                }

                // convert to XML
                String xmldata = xmlconverter.process( file.getBytes(), xmlconvertersrctype )

                // write XML string to temporary file
                def xmlfile = new FileOutputStream( fileName + ".xml" )
                def osw = new OutputStreamWriter( xmlfile, "UTF-8");

                osw.write( xmldata )
                osw.close()

                // load XML to database
                // TODO: handle errors
                readXmlService.loadXmlFile(fileName + ".xml")

                // remove temporary files
                // DDD file
                //def rmfile = new File(fileName)
                //rmfile.delete()
                // XML file
                def rmxmlfile = new File(fileName + ".xml")
                rmxmlfile.delete()

                flash.message = ''
            }

            //else
            //    flash.message = 'Non-existing or empty file!'
        }

        render(view:'dddUploadForm')
    }
}
