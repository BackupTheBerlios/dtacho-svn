/*
    $Id$

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
 * Serial number of the vehicle unit.
 */
public class VuSerialNumber extends ExtendedSerialNumber {
	/*
	 * VuSerialNumber ::= ExtendedSerialNumber, 8 bytes
	 */

	/**
	 * Constructor for a VuSerialNumber object
	 */
	public VuSerialNumber() {
		super();
	}

	/**
	 * Constructor for a VuSerialNumber object
	 * 
	 * @param	value	byte array of a VuSerialNumber structure
	 * 					whose data is used when the VuSerialNumber
	 * 					object is created.
	 */
	public VuSerialNumber( byte[] value ) {
		super( value );
	}
}
