import org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes
import org.springframework.context.ApplicationContext
import org.opendtacho.serviceinterfaces.*
import org.codehaus.groovy.grails.commons.GrailsApplication
import grails.util.GrailsUtil

class BootStrap {

    //def fixtureLoader
    def authenticateService

    def init = {servletContext ->

//         ApplicationContext ctx = servletContext.getAttribute(GrailsApplicationAttributes.APPLICATION_CONTEXT)
//         InsertUpdateService insertUpdateService = (InsertUpdateServiceInt) ctx.getBean("insertUpdateService")
//         ReadXmlService readXmlService = (ReadXmlServiceInt) ctx.getBean("readXmlService")

//         readXmlService.loadXmlFile('C_20080118_1624_A_Bartnik_testdata.xml')
//         readXmlService.loadXmlFile('C_20080118_1624_A_Bartnik_testdata.xml')
//         readXmlService.loadXmlFile('C_20080118_1624_A_Bartnik                            _DF000016247620.DDD.xml')
//         readXmlService.loadXmlFile('C_20080118_1624_A_Bartnik                            _DF000016247620.DDD.xml')

        /*if (GrailsUtil.environment == GrailsApplication.ENV_DEVELOPMENT) {
            fixtureLoader.load("TestData")
        }*/

        //create roles
        def role_admin = new Role (
                      authority:"ROLE_ADMIN",
                      description:"administration access"
                    ).save()
        def role_user = new Role (
                      authority:"ROLE_USER",
                      description:"normal user access"
                    ).save()

        //create users
        def admin = new User (
                      username:"admin",
                      userRealName:"admin",
                      passwd:authenticateService.encodePassword("opendtacho"),
                      enabled:true,
                      email:"me@lehoanganh.de",
                      emailShow:false,
                      description:"admin"
                    ).save()
        def weinert = new User (
                      username:"weinert",
                      userRealName:"Juergen Weinert",
                      passwd:authenticateService.encodePassword("opendtacho"),
                      enabled:true,
                      email:"weinert@hagos.de",
                      emailShow:false,
                      description:"Juergen Weinert"
                    ).save()
        def eberspaecher = new User (
                      username:"eberspaecher",
                      userRealName:"Dr. Markus Eberspaecher",
                      passwd:authenticateService.encodePassword("opendtacho"),
                      enabled:true,
                      email:"me@eberspaecher-it.de",
                      emailShow:false,
                      description:"Dr. Markus Eberspaecher"
                    ).save()
        def lang = new User (
                      username:"lang",
                      userRealName:"Gerald Lang",
                      passwd:authenticateService.encodePassword("opendtacho"),
                      enabled:true,
                      email:"Gerald.Lang@iscl.de",
                      emailShow:false,
                      description:"Gerald Land"
                    ).save()

        //allocate users to role
        role_admin.addToPeople(admin)
        role_admin.addToPeople(weinert)
        role_admin.addToPeople(eberspaecher)
        role_admin.addToPeople(lang)

    }
    def destroy = {
    }
} 