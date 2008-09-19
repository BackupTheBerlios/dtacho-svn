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

package org.opendtacho.DDDQuery.internalData.DataTypes;

/**
 * Date of manufacture of the vehicle unit.
 */
public class VuManufacturingDate extends TimeReal {
	/*
	 * VuManufacturingDate ::= TimeReal, 4 bytes
	 */


	/**
	 * Constructor for a VuManufacturingDate object
	 */
	public VuManufacturingDate() {
		super();
	}

	/**
	 * Constructor for a VuManufacturingDate object
	 * 
	 * @param	value	byte array of a VuManufacturingDate that is used
	 * 					when the VuManufacturingDate object is created.
	 */
	public VuManufacturingDate( byte[] value ) {
		super( value );
	}
}
