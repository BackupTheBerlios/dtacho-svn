/*
    $Id$

    Copyright (C) 2007-2008, Martin Barth, Gerald Schnabel

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

import org.jdom.Element;
import org.opendtacho.DDDQuery.DebugLogger;
import org.opendtacho.DDDQuery.internalData.DataClass;

/**
 * Vehicle Identification Number (VIN) referring to the vehicle as a whole,
 * normally chassis serial number or frame number.
 */
public class VehicleIdentificationNumber extends DataClass {
	/*
	 * VehicleIdentificationNumber ::= IA5String(SIZE(17)), 17 bytes
	 * 
	 * Value assignment: As defined in ISO 3779.
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 17;

	private String vehicleIdentificationNumber;

	private DebugLogger debugLogger;


	/**
	 * Constructor for a VehicleIdentificationNumber object
	 */
	public VehicleIdentificationNumber() {
		vehicleIdentificationNumber = new String();
	}

	/**
	 * Constructor for a VehicleIdentificationNumber object
	 * 
	 * @param	value	byte array of a VehicleIdentificationNumber structure
	 * 					whose data is used when the VehicleIdentificationNumber
	 * 					object is created.
	 */
	public VehicleIdentificationNumber( byte[] value ) {
		debugLogger = new DebugLogger();

		vehicleIdentificationNumber = new String( arrayCopy( value, 0, 17 ) );
		debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Vehicle identification number: %s\n", vehicleIdentificationNumber );
	}

	/**
	 * Constructor for a VehicleIdentificationNumber object
	 * 
	 * @param	vehicleIdentificationNumber		String that contains a Vehicle Identification Number (VIN)
	 * 											that is used when the VehicleIdentificationNumber
	 * 											object is created.
	 */
	public VehicleIdentificationNumber( String vehicleIdentificationNumber ) {
		setVehicleIdentificationNumber( vehicleIdentificationNumber );
	}

	/**
	 * Returns the Vehicle Information Number (VIN) referring to the vehicle as a whole, normally chassis
	 * serial number or frame number of a VehicleIdentificationNumber object.
	 * 
	 * @return	the Vehicle Information Number (VIN) referring to the vehicle as a whole, normally chassis
	 * 			serial number or frame number of the VehicleIdentificationNumber object
	 */
	public String getVehicleIdentificationNumber() {
		return vehicleIdentificationNumber;
	}

	/**
	 * Sets the Vehicle Information Number (VIN) referring to the vehicle as a whole, normally chassis
	 * serial number or frame number of a VehicleIdentificationNumber object.
	 * 
	 * @param	vehicleIdentificationNumber		the Vehicle Information Number (VIN) referring to the vehicle as a whole,
	 * 											normally chassis serial number or frame number to be set for the
	 * 											VehicleIdentificationNumber object
	 */
	public void setVehicleIdentificationNumber( String vehicleIdentificationNumber ) {
		int vehicleIdentificationNumberLength = vehicleIdentificationNumber.length();

		if ( vehicleIdentificationNumberLength > 17 ) {
			vehicleIdentificationNumberLength = 17;
		}

		this.vehicleIdentificationNumber = vehicleIdentificationNumber.substring( 0, vehicleIdentificationNumberLength );
	}

	@Override
	public Element generateXMLElement( String name ) {
		if ( isValidXMLString( vehicleIdentificationNumber ) == false ) {
			return new Element( name );
		}
		else {
			return new Element( name ).setText( vehicleIdentificationNumber );
		}
	}
}
