import org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes
import org.springframework.context.ApplicationContext
import org.opendtacho.serviceinterfaces.*
import org.codehaus.groovy.grails.commons.GrailsApplication
import grails.util.GrailsUtil
import org.opendtacho.domain.DtRole
import org.opendtacho.domain.DtUser

class BootStrap {

    def fixtureLoader


    def init = {servletContext ->

        //we use database, don't need fixtures
      // please use this fixture JUST ONE TIME AT BEGINNING to create the database we need
      // and NEXT TIME we boot this app please INACTIVE IT before, just make it to comments
      /*if (GrailsUtil.environment == GrailsApplication.ENV_DEVELOPMENT) {
            fixtureLoader.load("Testdata")
        }*/

        

    }
    def destroy = {
    }
} 