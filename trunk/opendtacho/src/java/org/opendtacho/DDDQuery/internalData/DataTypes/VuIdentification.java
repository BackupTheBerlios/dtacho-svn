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

import org.jdom.Element;
import org.opendtacho.DDDQuery.DebugLogger;
import org.opendtacho.DDDQuery.internalData.DataClass;

/**
 * Information, stored in a vehicle unit, related to the identification of the vehicle unit.
 */
public class VuIdentification extends DataClass {
	/*
	 * VuIdentification ::= SEQUENCE {
	 * 	vuManufacturerName VuManufacturerName, 36 bytes
	 * 	vuManufacturerAddress VuManufacturerAddress, 36 bytes
	 * 	vuPartNumber VuPartNumber, 16 bytes
	 * 	vuSerialNumber VuSerialNumber, 8 bytes
	 * 	vuSoftwareIdentification VuSoftwareIdentification, 8 bytes
	 * 	vuManufacturingDate VuManufacturingDate, 4 bytes
	 * 	vuApprovalNumber VuApprovalNumber, 8 bytes
	 * }
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 116;

	private VuManufacturerName vuManufacturerName; 
	private VuManufacturerAddress vuManufacturerAddress;
	private VuPartNumber vuPartNumber; 
	private VuSerialNumber vuSerialNumber;
	private VuSoftwareIdentification vuSoftwareIdentification;
	private VuManufacturingDate vuManufacturingDate;
	private VuApprovalNumber vuApprovalNumber;

	private DebugLogger debugLogger;


	/**
	 * Constructor for a VuIdentification object
	 */
	public VuIdentification() {
		vuManufacturerName = new VuManufacturerName();
		vuManufacturerAddress = new VuManufacturerAddress();
		vuPartNumber = new VuPartNumber();
		vuSerialNumber = new VuSerialNumber();
		vuSoftwareIdentification = new VuSoftwareIdentification();
		vuManufacturingDate = new VuManufacturingDate();
		vuApprovalNumber = new VuApprovalNumber();
	}

	/**
	 * Constructor for a VuIdentification object
	 * 
	 * @param	value	byte array of a VuIdentification structure
	 * 					whose data is used when the VuIdentification
	 * 					object is created.
	 */
	public VuIdentification( byte[] value ) {
		debugLogger = new DebugLogger();

		vuManufacturerName = new VuManufacturerName( arrayCopy( value, 0, 36 ) );
		debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Vehicle unit manufacturer name: %s\n", vuManufacturerName.getNameString() );

		vuManufacturerAddress = new VuManufacturerAddress( arrayCopy( value, 36, 36 ) );
		debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Vehicle unit manufacturer address: %s\n", vuManufacturerAddress.getAddressString() );

		vuPartNumber = new VuPartNumber( arrayCopy( value, 72, 16 ) );
		debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Vehicle unit part number: %s\n", vuPartNumber.getVuPartNumber() );

		vuSerialNumber = new VuSerialNumber( arrayCopy( value, 88, 8 ) );
		debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Vehicle unit serial number: %d\n", vuSerialNumber.getSerialNumber() );
		debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Vehicle unit month and year of manufacturing: %s\n", vuSerialNumber.getMonthYearString() );
		debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Vehicle unit equipment type: %d\n", vuSerialNumber.getType() );
		debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Vehicle unit manufacturer code: %02x - %s\n", vuSerialNumber.getManufacturerCode().getManufacturerCode(), vuSerialNumber.getManufacturerCode().toString() );

		vuSoftwareIdentification = new VuSoftwareIdentification( arrayCopy( value, 96, 8 ) );

		debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Vehicle unit manufacturing date:" );
		vuManufacturingDate = new VuManufacturingDate( arrayCopy( value, 104, 4 ) );

		vuApprovalNumber = new VuApprovalNumber( arrayCopy( value, 108, 8 ) );
		debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Vehicle unit approval number: %s\n", vuApprovalNumber.getVuApprovalNumber() );
	}

	/**
	 * Returns the name of the manufacturer of the vehicle unit
	 * of a VuIdentification object.
	 * 
	 * @return	the name of the manufacturer of the vehicle unit
	 * 			of the VuIdentification object
	 */
	public VuManufacturerName getVuManufacturerName() {
		return vuManufacturerName;
	}

	/**
	 * Sets the name of the manufacturer of the vehicle unit
	 * of a VuIdentification object.
	 * 
	 * @param	vuManufacturerName		the name of the manufacturer of the vehicle unit
	 * 								to be set for the VuIdentification object
	 */
	public void setVuManufacturerName( VuManufacturerName vuManufacturerName ) {
		this.vuManufacturerName = vuManufacturerName;
	}

	/**
	 * Returns the address of the manufacturer of the vehicle unit
	 * of a VuIdentification object.
	 * 
	 * @return	the address of the manufacturer of the vehicle unit
	 * 			of the VuIdentification object
	 */
	public VuManufacturerAddress getVuManufacturerAddress() {
		return vuManufacturerAddress;
	}

	/**
	 * Sets the address of the manufacturer of the vehicle unit
	 * of a VuIdentification object.
	 * 
	 * @param	vuManufacturerAddress		the address of the manufacturer of the vehicle unit
	 * 										to be set for the VuIdentification object
	 */
	public void setVuManufacturerAddress( VuManufacturerAddress vuManufacturerAddress ) {
		this.vuManufacturerAddress = vuManufacturerAddress;
	}

	/**
	 * Returns the part number of the vehicle unit
	 * of a VuIdentification object.
	 * 
	 * @return	the part number of the vehicle unit
	 * 			of the VuIdentification object
	 */
	public VuPartNumber getVuPartNumber() {
		return vuPartNumber;
	}

	/**
	 * Sets the part number of the vehicle unit
	 * of a VuIdentification object.
	 * 
	 * @param	vuPartNumber		the part number of the vehicle unit
	 * 								to be set for the VuIdentification object
	 */
	public void setVuPartNumber( VuPartNumber vuPartNumber ) {
		this.vuPartNumber = vuPartNumber;
	}

	/**
	 * Returns the serial number of the vehicle unit
	 * of a VuIdentification object.
	 * 
	 * @return	the serial number of the vehicle unit
	 * 			of the VuIdentification object
	 */
	public VuSerialNumber getVuSerialNumber() {
		return vuSerialNumber;
	}

	/**
	 * Sets the serial number of the vehicle unit
	 * of a VuIdentification object.
	 * 
	 * @param	vuSerialNumber		the serial number of the vehicle unit
	 * 								to be set for the VuIdentification object
	 */
	public void setVuSerialNumber( VuSerialNumber vuSerialNumber ) {
		this.vuSerialNumber = vuSerialNumber;
	}

	/**
	 * Returns the identification of the software implemented in the vehicle unit
	 * of a VuIdentification object.
	 * 
	 * @return	the identification of the software implemented in the vehicle unit
	 * 			of the VuIdentification object
	 */
	public VuSoftwareIdentification getVuSoftwareIdentification() {
		return vuSoftwareIdentification;
	}

	/**
	 * Sets the identification of the software implemented in the vehicle unit
	 * of a VuIdentification object.
	 * 
	 * @param	vuSoftwareIdentification		the identification of the software implemented in the vehicle unit
	 * 											to be set for the VuIdentification object
	 */
	public void setVuSoftwareIdentification( VuSoftwareIdentification vuSoftwareIdentification ) {
		this.vuSoftwareIdentification = vuSoftwareIdentification;
	}

	/**
	 * Returns the manufacturing date of the vehicle unit
	 * of a VuIdentification object.
	 * 
	 * @return	the manufacturing date of the vehicle unit
	 * 			of the VuIdentification object
	 */
	public VuManufacturingDate getVuManufacturingDate() {
		return vuManufacturingDate;
	}

	/**
	 * Sets the manufacturing date of the vehicle unit
	 * of a VuIdentification object.
	 * 
	 * @param	vuManufacturingDate		the manufacturing date of the vehicle unit
	 * 									to be set for the VuIdentification object
	 */
	public void setVuManufacturingDate( VuManufacturingDate vuManufacturingDate ) {
		this.vuManufacturingDate = vuManufacturingDate;
	}

	/**
	 * Returns the type approval number of the vehicle unit
	 * of a VuIdentification object.
	 * 
	 * @return	the type approval number of the vehicle unit
	 * 			of the VuIdentification object
	 */
	public VuApprovalNumber getVuApprovalNumber() {
		return vuApprovalNumber;
	}

	/**
	 * Sets the type approval number of the vehicle unit
	 * of a VuIdentification object.
	 * 
	 * @param	vuApprovalNumber		the type approval number of the vehicle unit
	 * 									to be set for the VuIdentification object
	 */
	public void setVuApprovalNumber( VuApprovalNumber vuApprovalNumber ) {
		this.vuApprovalNumber = vuApprovalNumber;
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		node.addContent( vuManufacturerName.generateXMLElement( "vuManufacturerName" ) );
		node.addContent( vuManufacturerAddress.generateXMLElement( "vuManufacturerAddress" ) );
		node.addContent( vuPartNumber.generateXMLElement( "vuPartNumber" ) );
		node.addContent( vuSerialNumber.generateXMLElement( "vuSerialNumber" ) );
		node.addContent( vuSoftwareIdentification.generateXMLElement( "vuSoftwareIdentification" ) );
		node.addContent( vuManufacturingDate.generateXMLElement( "vuManufacturingDate" ) );
		node.addContent( vuApprovalNumber.generateXMLElement( "vuApprovalNumber" ) );

		return node;
	}
}
