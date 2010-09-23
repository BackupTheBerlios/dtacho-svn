// Opendtacho - a program for analysing and interpreting digital tachograph data
// Copyright (C) 2008  Gerald Lang, ISCL - Internet Security Consulting Lang, http://www.iscl.de
//
// http://www.opendtacho.org
//
// gerald.lang@iscl.de
//
// This program is free software; you can redistribute it and/or
// modify it under the terms of the GNU General Public License
// as published by the Free Software Foundation; either version 2
// of the License, or (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.

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

    // References
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
        person(nullable:true)
	}
}
