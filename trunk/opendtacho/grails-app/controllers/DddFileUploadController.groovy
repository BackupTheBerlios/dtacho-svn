class DddFileUploadController {

    def readXmlService

    def defaultAction = 'dddUploadForm'

    def dddUploadForm = {
    }

    def upload = {
        def file = request.getFile('binFile')
        if(file && !file.empty) {

            // save file

            // TODO: save file in archive, make base directory configurable
            def tempdir = System.getProperty("java.io.tmpdir");

            if ( !(tempdir.endsWith("/") || tempdir.endsWith("\\")) )
                tempdir = tempdir + System.getProperty("file.separator");

            def fileName = tempdir + file.getOriginalFilename()
            file.transferTo( new java.io.File(fileName))

            // convert to XML
            // TODO: convert to XML

            // load XML to database
            // TODO: handle errors
            readXmlService.loadXmlFile(fileName)

            flash.message = ''
        }
        else
            flash.message = 'Non-existing or empty file!'

        render(view:'dddUploadForm')
    }
}
