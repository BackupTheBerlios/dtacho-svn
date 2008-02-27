/*
    $Id:VuPublicKey.java 26 2008-02-25 22:28:27Z deetee $

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
 * The public key of vehicle unit.
 */
public class VuPublicKey extends PublicKey {
	/*
	 * VuPublicKey ::= PublicKey, 136 bytes
	 */


	/**
	 * Constructor for a VuPublicKey object
	 */
	public VuPublicKey() {
		super();
	}

	/**
	 * Constructor for a VuPublicKey object
	 * 
	 * @param	value	byte array of a VuPublicKey that is used
	 * 					when the VuPublicKey object is created.
	 */
	public VuPublicKey( byte[] value ) {
		super( value );
	}
}
