/*   Copyright (C) 2008, Gerald Schnabel

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
 * Serial number of the motion sensor.
 */
public class SensorSerialNumber extends ExtendedSerialNumber {
	/*
	 * SensorSerialNumber ::= ExtendedSerialNumber, 8 bytes
	 */

	/**
	 * Constructor for a SensorSerialNumber object
	 */
	public SensorSerialNumber() {
		super();
	}

	/**
	 * Constructor for a SensorSerialNumber object
	 * 
	 * @param	value	byte array of a SensorSerialNumber structure
	 * 					whose data is used when the SensorSerialNumber
	 * 					object is created.
	 */
	public SensorSerialNumber( byte[] value ) {
		super( value );
	}
}
