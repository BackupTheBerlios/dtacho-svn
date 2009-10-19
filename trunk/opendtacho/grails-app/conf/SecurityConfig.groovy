security {

	// see DefaultSecurityConfig.groovy for all settable/overridable properties

	active = true

	loginUserDomainClass = "User"
	authorityDomainClass = "Role"
    useRequestMapDomainClass = false

    requestMapString = """
      CONVERT_URL_TO_LOWERCASE_BEFORE_COMPARISON
      PATTERN_TYPE_APACHE_ANT

      /dtactivitychange/**=ROLE_ADMIN
      /dtdriver/**=ROLE_ADMIN
      /xmlfileupload/**=ROLE_ADMIN
      /dddfileupload/**=ROLE_ADMIN
      /user/**=ROLE_ADMIN
      /role/**=ROLE_ADMIN

    """

}
