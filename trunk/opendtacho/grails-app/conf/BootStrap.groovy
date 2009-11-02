import org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes
import org.springframework.context.ApplicationContext
import org.opendtacho.serviceinterfaces.*
import org.codehaus.groovy.grails.commons.GrailsApplication
import grails.util.GrailsUtil
import org.opendtacho.domain.DtRole
import org.opendtacho.domain.DtUser

class BootStrap {

    def fixtureLoader
    //def authenticateService

    def init = {servletContext ->

//         ApplicationContext ctx = servletContext.getAttribute(GrailsApplicationAttributes.APPLICATION_CONTEXT)
//         InsertUpdateService insertUpdateService = (InsertUpdateServiceInt) ctx.getBean("insertUpdateService")
//         ReadXmlService readXmlService = (ReadXmlServiceInt) ctx.getBean("readXmlService")

//         readXmlService.loadXmlFile('C_20080118_1624_A_Bartnik_testdata.xml')
//         readXmlService.loadXmlFile('C_20080118_1624_A_Bartnik_testdata.xml')
//         readXmlService.loadXmlFile('C_20080118_1624_A_Bartnik                            _DF000016247620.DDD.xml')
//         readXmlService.loadXmlFile('C_20080118_1624_A_Bartnik                            _DF000016247620.DDD.xml')

        if (GrailsUtil.environment == GrailsApplication.ENV_DEVELOPMENT) {
            fixtureLoader.load("TestData")
        }

        //create roles
//        def role_admin = new DtRole (
//                      authority:"ROLE_ADMIN",
//                      description:"administration access"
//                    ).save()
//        def role_user = new DtRole (
//                      authority:"ROLE_USER",
//                      description:"normal user access"
//                    ).save()
//
//        //create users
//        def admin = new DtUser (
//                      username:"admin",
//                      userRealName:"admin",
//                      password:authenticateService.encodePassword("opendtacho"),
//                      enabled:true/*,
//                      email:"me@lehoanganh.de",
//                      emailShow:false,
//                      description:"admin"*/
//                    ).save()
//        def weinert = new DtUser (
//                      username:"weinert",
//                      userRealName:"Juergen Weinert",
//                      password:authenticateService.encodePassword("opendtacho"),
//                      enabled:true/*,
//                      email:"weinert@hagos.de",
//                      emailShow:false,
//                      description:"Juergen Weinert"*/
//                    ).save()
//        def eberspaecher = new DtUser (
//                      username:"eberspaecher",
//                      userRealName:"Dr. Markus Eberspaecher",
//                      password:authenticateService.encodePassword("opendtacho"),
//                      enabled:true/*,
//                      email:"me@eberspaecher-it.de",
//                      emailShow:false,
//                      description:"Dr. Markus Eberspaecher"*/
//                    ).save()
//        def lang = new DtUser (
//                      username:"lang",
//                      userRealName:"Gerald Lang",
//                      password:authenticateService.encodePassword("opendtacho"),
//                      enabled:true/*,
//                      email:"Gerald.Lang@iscl.de",
//                      emailShow:false,
//                      description:"Gerald Land"*/
//                    ).save()
//
//        //allocate users to role
//        role_admin.addToPeople(admin)
//        role_admin.addToPeople(weinert)
//        role_admin.addToPeople(eberspaecher)
//        role_admin.addToPeople(lang)

    }
    def destroy = {
    }
} 