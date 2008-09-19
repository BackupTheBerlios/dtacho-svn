class XmlFileUploadTest extends grails.util.WebTest {

    // TODO: setup, teardown, ...
    
    // Unlike unit tests, functional tests are often sequence dependent.
    // Specify that sequence here.
    void suite() {
        testXmlFileUpload()
        // add tests for more operations here
    }

    def testXmlFileUpload() {
        webtest('XmlFileUpload basic operations: upload file') {
            
            invoke      'xmlFileUpload/xmlUploadForm'
            verifyText  'Home'

            group(description:'First case: error because of non-existent file') {
                setFileField(name:'xmlFile', fileName:'F:\\DigitalerTachograph\\Grails\\DatabaseTest\\non_existend_file.xml')
                clickButton(name:'uploadFile')
                verifyText 'Non-existing or empty file!'
            }

            group(description:'Second case: should work') {
                 setFileField(name:'xmlFile', fileName:'F:\\DigitalerTachograph\\Grails\\DatabaseTest\\C_20080118_1624_A_Bartnik                            _DF000016247620.DDD.xml')
                 clickButton(name:'uploadFile')
            }

            group(description:'Second case again: should work again and be faster') {
                 setFileField(name:'xmlFile', fileName:'F:\\DigitalerTachograph\\Grails\\DatabaseTest\\C_20080118_1624_A_Bartnik                            _DF000016247620.DDD.xml')
                 clickButton(name:'uploadFile')
            }
         }
    }

}