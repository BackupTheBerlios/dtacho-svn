security {

	active = true

	loginUserDomainClass = "org.opendtacho.domain.DtUser"
	authorityDomainClass = "org.opendtacho.domain.DtRole"
	requestMapClass = "org.opendtacho.domain.DtRequestMap"
    
    userName = 'username'
    password = 'password'
    enabled = 'enabled'

    useRequestMapDomainClass = true

    basicProcessingFilter = true

    //the user management principles are based on 3 techniques
    //1. MAPPING roles to url (url-based)
    //2. FILTER FOR REQUEST, a user can't see data of another user (data-based)
    //3. MODIFIED LIST ACTION, controllers return only compatible data (data-based)          
}
