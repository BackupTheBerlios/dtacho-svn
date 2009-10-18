import org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes
import org.springframework.context.ApplicationContext
import org.opendtacho.serviceinterfaces.*
import org.codehaus.groovy.grails.commons.GrailsApplication
import grails.util.GrailsUtil

class BootStrap {

    def fixtureLoader

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

    }
    def destroy = {
    }
} 