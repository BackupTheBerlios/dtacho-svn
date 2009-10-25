package org.opendtacho.domain

import org.opendtacho.domain.DtUser

/**
 * Authority domain class.
 */
class DtRole {

	static hasMany = [people: DtUser]

	/** description */
	String description
	/** ROLE String */
	String authority

	static constraints = {
		authority(blank: false, unique: true)
		description()
	}
}
