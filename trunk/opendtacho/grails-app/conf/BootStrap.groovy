import org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes
import org.springframework.context.ApplicationContext
import org.opendtacho.serviceinterfaces.*
import org.codehaus.groovy.grails.commons.GrailsApplication
import grails.util.GrailsUtil

class BootStrap {

    def fixtureLoader

    def init = {servletContext ->


	// Die folgenden 3 Zeilen müssen nach dem Erststart auskommentiert oder gelöscht werden!  
     
	if (GrailsUtil.environment == GrailsApplication.ENV_DEVELOPMENT) {
	  fixtureLoader.load("DataGenerator")
	}

    }

    def destroy = {
    }
} 
