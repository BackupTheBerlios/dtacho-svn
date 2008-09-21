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
 * Information, stored in a vehicle unit, related to the identification of the
 * motion sensor paired with the vehicle unit.
 */
public class SensorPaired extends DataClass {
	/*
	 * SensorPaired ::= SEQUENCE {
	 * 	sensorSerialNumber SensorSerialNumber, 8 bytes
	 * 	sensorApprovalNumber SensorApprovalNumber, 8 bytes
	 * 	sensorPairingDateFirst SensorPairingDate, 4 bytes
	 * }
	 * ---
	 * SensorSerialNumber ::= ExtendedSerialNumber, 8 bytes
	 * ---
	 * SensorApprovalNumber ::= IA5String(SIZE(8)), 8 bytes
	 * ---
	 * SensorPairingDate ::= TimeReal, 4 bytes
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 20;

	private SensorSerialNumber sensorSerialNumber;
	private SensorApprovalNumber sensorApprovalNumber;
	private SensorPairingDate sensorPairingDateFirst;

	private DebugLogger debugLogger;


	/**
	 * Constructor for a SensorPaired object
	 */
	public SensorPaired() {
		sensorSerialNumber = new SensorSerialNumber();
		sensorApprovalNumber = new SensorApprovalNumber();
		sensorPairingDateFirst = new SensorPairingDate();
	}

	/**
	 * Constructor for a SensorPaired object
	 * 
	 * @param	value	byte array of a SensorPaired structure that is used
	 * 					when the SensorPaired object is created.
	 */
	public SensorPaired( byte[] value ) {
		debugLogger = new DebugLogger();

		sensorSerialNumber = new SensorSerialNumber( arrayCopy( value, 0, 8 ) );
		debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Sensor serial number:" );
		debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Serial number: %d\n", sensorSerialNumber.getSerialNumber() );
		debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Month and year of manufacturing: %s\n", sensorSerialNumber.getMonthYearString() );
		debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Type of equipment: %02x\n", sensorSerialNumber.getType() );
		debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Manufacturer: %02x - %s\n", sensorSerialNumber.getManufacturerCode().getManufacturerCode(), sensorSerialNumber.getManufacturerCode().toString() );

		sensorApprovalNumber = new SensorApprovalNumber( arrayCopy( value, 8, 8 ) );
		debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Sensor approval number: %s\n", sensorApprovalNumber.getSensorApprovalNumber() );

		debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Date of first pairing with a vehicle unit:" );
		sensorPairingDateFirst = new SensorPairingDate( arrayCopy( value, 16, 4 ) );
	}

	/**
	 * Returns the serial number of the motion sensor currently paired with
	 * the vehicle unit of a SensorPaired object.
	 * 
	 * @return	the serial number of the motion sensor currently paired with
	 * 			the vehicle unit of the SensorPaired object
	 */
	public SensorSerialNumber getSensorSerialNumber() {
		return sensorSerialNumber;
	}

	/**
	 * Sets the serial number of the motion sensor currently paired with
	 * the vehicle unit of a SensorPaired object.
	 * 
	 * @param	sensorSerialNumber	the serial number of the motion sensor currently paired with
	 * 								the vehicle unit to be set for the SensorPaired object
	 */
	public void setCodePage( SensorSerialNumber sensorSerialNumber ) {
		this.sensorSerialNumber = sensorSerialNumber;
	}

	/**
	 * Returns the approval number of the motion sensor currently paired
	 * with the vehicle unit of a SensorPaired object.
	 * 
	 * @return	the approval number of the motion sensor currently paired
	 * 			with the vehicle unit of the SensorPaired object
	 */
	public SensorApprovalNumber getSensorApprovalNumber() {
		return sensorApprovalNumber;
	}

	/**
	 * Sets the approval number of the motion sensor currently paired
	 * with the vehicle unit of a SensorPaired object.
	 * 
	 * @param	sensorApprovalNumber	the approval number of the motion sensor currently paired
	 * 									with the vehicle unit to be set for the SensorPaired object
	 */
	public void setSensorApprovalNumber( SensorApprovalNumber sensorApprovalNumber ) {
		this.sensorApprovalNumber = sensorApprovalNumber;
	}

	/**
	 * Returns the date of the first pairing with a vehicle unit of the motion sensor
	 * currently paired with the vehicle unit of a SensorPaired object.
	 * 
	 * @return	the date of the first pairing with a vehicle unit of the motion sensor
	 * 			currently paired with the vehicle unit of the SensorPaired object
	 */
	public SensorPairingDate getSensorPairingDateFirst() {
		return sensorPairingDateFirst;
	}

	/**
	 * Sets the date of the first pairing with a vehicle unit of the motion sensor
	 * currently paired with the vehicle unit of a SensorPaired object.
	 * 
	 * @param	sensorPairingDateFirst	the date of the first pairing with a vehicle
	 * 									unit of the motion sensor currently paired
	 * 									with the vehicle unit to be set for the SensorPaired object
	 */
	public void setSensorPairingDateFirst( SensorPairingDate sensorPairingDateFirst ) {
		this.sensorPairingDateFirst = sensorPairingDateFirst;
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		node.addContent( sensorSerialNumber.generateXMLElement( "sensorSerialNumber" ) );
		node.addContent( sensorApprovalNumber.generateXMLElement( "sensorApprovalNumber" ) );
		node.addContent( sensorPairingDateFirst.generateXMLElement( "sensorPairingDateFirst" ) );

		return node;
	}
}
