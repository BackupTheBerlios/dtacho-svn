/*
    $Id:VuPrivateKey.java 26 2008-02-25 22:28:27Z deetee $

    Copyright (C) 2008, Gerald Schnabel

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program; if (not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/

package org.digitalertachograph.DDDQuery.internalData.DataTypes;

/**
 * The private key of a vehicle unit.
 */
public class VuPrivateKey extends RSAKeyPrivateExponent {
	/*
	 * VuPrivateKey ::= RSAKeyPrivateExponent, 128 bytes
	 */


	/**
	 * Constructor for a VuPrivateKey object
	 */
	public VuPrivateKey() {
		super();
	}

	/**
	 * Constructor for a VuPrivateKey object
	 * 
	 * @param	value	byte array of a VuPrivateKey that is used
	 * 					when the VuPrivateKey object is created.
	 */
	public VuPrivateKey( byte[] value ) {
		super( value );
	}
}
