package org.opendtacho.domain

import org.opendtacho.domain.DtRole
import org.opendtacho.domain.DtPerson

/**
 * User domain class.
 */
class DtUser {
	static transients = ['pass']
	static hasMany = [authorities: DtRole]
	static belongsTo = DtRole

    DtPerson person
    
	/** Username */
	String username
	/** User Real Name*/
	String userRealName
	/** MD5 Password */
	String password
	/** enabled */
	boolean enabled

//	String email
//	boolean emailShow

	/** description */
	String description = ''

	/** plain password to create a MD5 password */
	String pass = '[secret]'

	static constraints = {
		username(blank: false, unique: true)
		userRealName(blank: false)
		password(blank: false)
		enabled()
	}
}
