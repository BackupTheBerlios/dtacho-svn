class XmlFileUploadController {

    def readXmlService

    def defaultAction = 'xmlUploadForm'

    def xmlUploadForm = {
    }

    def upload = {
        def file = request.getFile('xmlFile')
        if(file && !file.empty) {

            // TODO: avoid saving file in temp directory, this requires a change in the interface of ReadXmlService
            def fileName = "C:\\temp\\tmpFileUploadDir\\${file.getOriginalFilename()}"
            file.transferTo( new java.io.File(fileName))

            // load XML to database
            // TODO: handle errors
            readXmlService.loadXmlFile(fileName)

            flash.message = ''
        }
        else
            flash.message = 'Non-existing or empty file!'

        render(view:'xmlUploadForm')
    }
}
