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
