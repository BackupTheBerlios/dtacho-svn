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

class XmlFileUploadController {

    def readXmlService

    def defaultAction = 'xmlUploadForm'

    def xmlUploadForm = {
    }

    def upload = {
        def file = request.getFile('xmlFile')
        if(file && !file.empty) {

            // TODO: avoid saving file in temp directory, this requires a change in the interface of ReadXmlService
            def tempdir = System.getProperty("java.io.tmpdir");

            if ( !(tempdir.endsWith("/") || tempdir.endsWith("\\")) )
                tempdir = tempdir + System.getProperty("file.separator");

            def fileName = tempdir + file.getOriginalFilename()
            file.transferTo( new java.io.File(fileName))

            // load XML to database
            // TODO: handle errors
            readXmlService.loadXmlFile(fileName)

            // remove temporary file
            def rmfile = new File(fileName)
            rmfile.delete()

            flash.message = ''
        }
        else
            flash.message = 'Non-existing or empty file!'

        render(view:'xmlUploadForm')
    }
}
