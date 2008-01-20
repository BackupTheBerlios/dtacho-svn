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
 * Identification of a vehicle, unique for Europe (VRN and Member State).
 */
public class VehicleRegistrationIdentification extends DataClass {
	/*
	 * VehicleRegistrationIdentification ::= SEQUENCE {
	 * 	vehicleRegistrationNation NationNumeric, 1 byte
	 * 	vehicleRegistrationNumber VehicleRegistrationNumber, 14 bytes
	 * }
	 * ---
	 * NationNumeric ::= INTEGER(0..255), 1 byte
	 * ---
	 * VehicleRegistrationNumber ::= SEQUENCE {
	 * 	codePage INTEGER (0..255), 1 byte
	 * 	vehicleRegNumber OCTET STRING (SIZE(13)), 13 bytes
	 * }
	 * ---
	 * codePage specifies the part of the ISO/IEC 8859 used to code the vehicleRegNumber
	 */

	private NationNumeric vehicleRegistrationNation;
	private VehicleRegistrationNumber vehicleRegistrationNumber;

	
	/**
	 * Constructor for a VehicleRegistrationIdentification object
	 */
	public VehicleRegistrationIdentification() {
		vehicleRegistrationNation = new NationNumeric();
		vehicleRegistrationNumber = new VehicleRegistrationNumber();
	}

	/**
	 * Constructor for a VehicleRegistrationIdentification object
	 * 
	 * @param	value	byte array of a VehicleRegistrationIdentification structure
	 * 					whose data is used when the VehicleRegistrationIdentification
	 * 					object is created.
	 */
	public VehicleRegistrationIdentification( byte [] value ) {
		vehicleRegistrationNation = new NationNumeric( value[ 0 ] );
		vehicleRegistrationNumber = new VehicleRegistrationNumber( arrayCopy( value, 1, 14 ) );
	}

	/**
	 * Returns the nation where the vehicle is registered of a VehicleRegistrationIdentification object.
	 * 
	 * @return	the nation where the vehicle is registered 
	 * 			of the VehicleRegistrationIdentification object
	 */
	public NationNumeric getVehicleRegistrationNation() {
		return vehicleRegistrationNation;
	}

	/**
	 * Sets the nation where the vehicle is registered
	 * of a VehicleRegistrationIdentification object.
	 * 
	 * @param	vehicleRegistrationNation	the nation where the vehicle is registered
	 * 										to be set for the VehicleRegistrationIdentification object
	 */
	public void setVehicleRegistrationNation( NationNumeric vehicleRegistrationNation ) {
		this.vehicleRegistrationNation = vehicleRegistrationNation;
	}

	/**
	 * Returns the registration number of the vehicle (VRN) of a VehicleRegistrationIdentification object.
	 * 
	 * @return	the registration number of the vehicle (VRN)
	 * 			of the VehicleRegistrationIdentification object
	 */
	public VehicleRegistrationNumber getVehicleRegistrationNumber() {
		return vehicleRegistrationNumber;
	}

	/**
	 * Sets the registration number of the vehicle (VRN)
	 * of a VehicleRegistrationIdentification object.
	 * 
	 * @param	vehicleRegistrationNumber	the registration number of the vehicle (VRN)
	 * 										to be set for the VehicleRegistrationIdentification object
	 */
	public void setVehicleRegistrationNumber( VehicleRegistrationNumber vehicleRegistrationNumber ) {
		this.vehicleRegistrationNumber = vehicleRegistrationNumber;
	}
	
	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );
		node.addContent( vehicleRegistrationNation.generateXMLElement( "vehicleRegistrationNation" ) );
		node.addContent( vehicleRegistrationNumber.generateXMLElement( "vehicleRegistrationNumber" ) );
		
		return node;
	}
}
