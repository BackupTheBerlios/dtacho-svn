/*   Copyright (C) 2007-2008, Martin Barth, Gerald Schnabel

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
 * Vehicle Identification Number (VIN) referring to the vehicle as a whole,
 * normally chassis serial number or frame number.
 */
public class VehicleIdentificationNumber extends DataClass {
	/*
	 * VehicleIdentificationNumber ::= IA5String(SIZE(17)), 17 bytes
	 * 
	 * Value assignment: As defined in ISO 3779.
	 */
	
	private String vehicleIdentificationNumber;
	

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
	public VehicleIdentificationNumber( byte[]value ){
		vehicleIdentificationNumber = new String( value );
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
		this.vehicleIdentificationNumber = vehicleIdentificationNumber;
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );
		node.addContent( new Element( "vehicleIdentificationNumber" ).setText( vehicleIdentificationNumber ) );

		return node;
	}
}
