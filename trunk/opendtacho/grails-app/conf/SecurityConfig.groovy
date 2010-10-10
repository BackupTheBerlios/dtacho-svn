security {

	// see DefaultSecurityConfig.groovy for all settable/overridable properties

	active = true

	loginUserDomainClass = "org.opendtacho.domain.DtUser"
	authorityDomainClass = "org.opendtacho.domain.DtRole"
	requestMapClass = "org.opendtacho.domain.DtRequestMap"
    
    userName = 'username'
    password = 'password'
    enabled = 'enabled'

    useRequestMapDomainClass = true

    basicProcessingFilter = true

}
