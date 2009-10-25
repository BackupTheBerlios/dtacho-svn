package org.opendtacho.domain

/**
 * Request Map domain class.
 */
class DtRequestMap {

	String url
	String configAttribute

	static constraints = {
		url(blank: false, unique: true)
		configAttribute(blank: false)
	}
}
