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

import org.digitalertachograph.DDDQuery.internalData.DataClass;
import org.jdom.Element;

/**
 * Characteristic coefficient of the vehicle.
 */
public class W_VehicleCharacteristicConstant extends DataClass {
	/* 
	 * WVehicleCharacteristicConstant ::= INTEGER(0..2^16-1), 2 bytes
	 *
	 * value assignment:
	 * Impulses per kilometre in the operating range 0 to 64 255 pulses/km.
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 2;

	private int wVehicleCharacteristicConstant;


	/**
	 * Constructor for a WVehicleCharacteristicConstant object
	 */
	public W_VehicleCharacteristicConstant() {
		wVehicleCharacteristicConstant = 0;
	}

	/**
	 * Constructor for a WVehicleCharacteristicConstant object
	 * 
	 * @param	value	byte array of a WVehicleCharacteristicConstant structure
	 * 					whose data is used when the WVehicleCharacteristicConstant
	 * 					object is created.
	 */
	public W_VehicleCharacteristicConstant( byte[] value ) {
		this.wVehicleCharacteristicConstant = convertIntoUnsigned2ByteInt( value );
	}

	/**
	 * Returns the characteristic coefficient of the vehicle
	 * of a WVehicleCharacteristicConstant object.
	 * 
	 * @return	the characteristic coefficient of the vehicle
	 * 			of the WVehicleCharacteristicConstant object
	 */
	public int getWVehicleCharacteristicConstant() {
		return wVehicleCharacteristicConstant;
	}

	/**
	 * Sets the characteristic coefficient of the vehicle
	 * of a WVehicleCharacteristicConstant object.
	 * 
	 * @param	wVehicleCharacteristicConstant		the characteristic coefficient of the vehicle to be set for the
	 * 												WVehicleCharacteristicConstant object
	 */
	public void setWVehicleCharacteristicConstant( int wVehicleCharacteristicConstant ) {
		this.wVehicleCharacteristicConstant = wVehicleCharacteristicConstant;
	}

	@Override
	public Element generateXMLElement( String name ) {
		return new Element( name ).setText( Integer.toString( wVehicleCharacteristicConstant ) );
	}
}
