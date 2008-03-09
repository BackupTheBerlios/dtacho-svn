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

package org.digitalertachograph.DDDQuery.internalData.DataTypes;

import org.digitalertachograph.DDDQuery.internalData.DataClass;
import org.jdom.Element;
import org.digitalertachograph.DDDQuery.Controller;

/**
 * Registration number of the vehicle (VRN). The registration number is assigned by the
 * vehicle licensing authority.
 */
public class VehicleRegistrationNumber extends DataClass {
	/*
	 * VehicleRegistrationNumber ::= SEQUENCE {
	 * 	codePage INTEGER (0..255), 1 byte
	 * 	vehicleRegNumber OCTET STRING (SIZE(13)), 13 bytes
	 * }
	 * ---
	 * codePage specifies the part of the ISO/IEC 8859 used to code the vehicleRegNumber
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 14;

	private short codePage;
	private byte[] vehicleRegNumber;
	

	/**
	 * Constructor for a VehicleRegistrationNumber object
	 */
	public VehicleRegistrationNumber() {
		codePage = 0;
		vehicleRegNumber = new byte[ 13 ];
	}

	/**
	 * Constructor for a VehicleRegistrationNumber object
	 * 
	 * @param	value	byte array of a VehicleRegistrationNumber structure
	 * 					whose data is used when the VehicleRegistrationNumber
	 * 					object is created.
	 */
	public VehicleRegistrationNumber( byte[] value ) {
		this( value[ 0 ], arrayCopy( value, 1, value.length - 1 ) );
	}

	/**
	 * Constructor for a VehicleRegistrationNumber object
	 * 
	 * @param	codePage			byte that specifies the part of the
	 * 								ISO/IEC 8859 used to code the address
	 * @param	vehicleRegNumber	byte array that contains the number of the vehicle (VRN)
	 */
	public VehicleRegistrationNumber( byte codePage, byte[] vehicleRegNumber ) {
		this.codePage = convertIntoUnsigned1ByteInt( codePage );
		this.vehicleRegNumber = arrayCopy( vehicleRegNumber, 0, 13 );
	}

	/**
	 * Returns the codepage of a VehicleRegistrationNumber object.
	 * 
	 * @return	the codepage of the VehicleRegistrationNumber object
	 */
	public short getCodePage() {
		return codePage;
	}

	/**
	 * Sets the codepage of a VehicleRegistrationNumber object.
	 * 
	 * @param	codePage	the codepage to be set for the VehicleRegistrationNumber object
	 */
	public void setCodePage( short codePage ) {
		this.codePage = codePage;
	}

	/**
	 * Sets the codepage of a VehicleRegistrationNumber object.
	 * 
	 * @param	codePage	the codepage to be set for the VehicleRegistrationNumber object
	 */
	public void setCodePage( byte codePage ) {
		this.codePage = (short)( codePage & 0xff );
	}

	/**
	 * Returns the number of the vehicle (VRN) of a VehicleRegistrationNumber object.
	 * 
	 * @return	the number of the vehicle (VRN) of the VehicleRegistrationNumber object
	 */
	public byte[] getVehicleRegNumber() {
		return vehicleRegNumber;
	}

	/**
	 * Returns the number of the vehicle (VRN) of a VehicleRegistrationNumber object.
	 * 
	 * @return	the number of the vehicle (VRN) of the VehicleRegistrationNumber object
	 */
	public String getVehicleRegNumberString() {
		return new String( vehicleRegNumber );
	}

	/**
	 * Sets the number of the vehicle (VRN) of a VehicleRegistrationNumber object.
	 * 
	 * @param	vehicleRegNumber	the number of the vehicle (VRN) to be set
	 * 								for the VehicleRegistrationNumber object
	 */
	public void setVehicleRegNumber( byte[] vehicleRegNumber ) {
		this.vehicleRegNumber = arrayCopy( vehicleRegNumber, 0, 13 );
	}

	@Override
	public Element generateXMLElement( String name ) {
		Controller c = Controller.getInstance();

		Element node = new Element( name );
		node.addContent( new Element( "codePage" ).setText( Short.toString( codePage ) ) );

		// anonymize data if required
		if ( c.isAnonymized() ) {
			byte[] tmp = new byte[ 13 ];
			for ( int i = 0; i < 13; i++ )
				tmp[ i ] = 'A';
			
			node.addContent( new Element( "vehicleRegNumber" ).setText( convertIntoHexString( tmp ) ) );
		}
		else {
			node.addContent( new Element( "vehicleRegNumber" ).setText( convertIntoHexString( vehicleRegNumber ) ) );
		}
		return node;
	}
}
