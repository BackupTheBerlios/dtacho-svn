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
 * Information, stored in a driver or a workshop card, related to a period of use
 * of a vehicle during a calendar day.
 */
public class CardVehicleRecord extends DataClass {
	/*
	 * CardVehicleRecord ::= SEQUENCE {
	 * 	vehicleOdometerBegin OdometerShort, 3 bytes
	 * 	vehicleOdometerEnd OdometerShort, 3 bytes
	 * 	vehicleFirstUse TimeReal, 4 bytes
	 * 	vehicleLastUse TimeReal, 4 bytes
	 * 	vehicleRegistration VehicleRegistrationIdentification, 15 bytes
	 * 	vuDataBlockCounter VuDataBlockCounter, 2 bytes
	 * }
	 * ---
	 * OdometerShort ::= INTEGER(0..2^24-1)
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 31;

	private OdometerShort vehicleOdometerBegin;
	private OdometerShort vehicleOdometerEnd;
	private TimeReal vehicleFirstUse;
	private TimeReal vehicleLastUse;
	private VehicleRegistrationIdentification vehicleRegistration;
	private VuDataBlockCounter vuDataBlockCounter;

	private DebugLogger debugLogger;


	/**
	 * Constructor for a CardVehicleRecord object
	 */
	public CardVehicleRecord() {
		vehicleOdometerBegin = new OdometerShort();
		vehicleOdometerEnd = new OdometerShort();
		vehicleFirstUse = new TimeReal();
		vehicleLastUse = new TimeReal();
		vehicleRegistration = new VehicleRegistrationIdentification();
		vuDataBlockCounter = new VuDataBlockCounter();
	}

	/**
	 * Constructor for a CardVehicleRecord object
	 * 
	 * @param	value	byte array of a CardVehicleRecord structure
	 * 					whose data is used when the CardVehicleRecord
	 * 					object is created.
	 */
	public CardVehicleRecord( byte[] value ) {
		debugLogger = new DebugLogger();
		debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] ------------------------------------------------------------" );

		vehicleOdometerBegin = new OdometerShort( arrayCopy( value, 0, 3 ) );
		debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Odometer value at the beginning of use of the vehicle: %s\n", vehicleOdometerBegin.getOdometerShort() );

		vehicleOdometerEnd = new OdometerShort( arrayCopy( value, 3, 3 ) );
		debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Odometer value at the end of use of the vehicle: %d\n", vehicleOdometerEnd.getOdometerShort() );

		debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Beginning of use of the vehicle:" );
		vehicleFirstUse = new TimeReal( arrayCopy( value, 6, 4 ) );

		debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] End of use of the vehicle:" );
		vehicleLastUse = new TimeReal( arrayCopy( value, 10, 4) );

		vehicleRegistration = new VehicleRegistrationIdentification( arrayCopy( value, 14, 15 ) );
		debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Nation where vehicle is registered: %s\n", vehicleRegistration.getVehicleRegistrationNation().toString() );
		debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Vehicle Registration Number: %s\n", vehicleRegistration.getVehicleRegistrationNumber().getVehicleRegNumberString() );

		vuDataBlockCounter = new VuDataBlockCounter( arrayCopy( value, 29, 2 ) );
		debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] VuDataBlockCounter: %s\n", vuDataBlockCounter.getVuDataBlockCounterString() );
	}

	/**
	 * Returns the vehicle odometer value at the beginning of the period of use of the vehicle
	 * of a CardVehicleRecord object.
	 * 
	 * @return	the vehicle odometer value at the beginning of the period of use of the vehicle
	 * 			of the CardVehicleRecord object
	 */
	public OdometerShort getVehicleOdometerBegin() {
		return vehicleOdometerBegin;
	}

	/**
	 * Sets the vehicle odometer value at the beginning of the period of use of the vehicle
	 * of a CardVehicleRecord object.
	 * 
	 * @param	vehicleOdometerBegin	the vehicle odometer value at the beginning of the period
	 * 									of use of the vehicle to be set for the CardVehicleRecord object
	 */
	public void setVehicleOdometerBegin( OdometerShort vehicleOdometerBegin ) {
		this.vehicleOdometerBegin = vehicleOdometerBegin;
	}

	/**
	 * Returns the vehicle odometer value at the end of the period of use of the vehicle
	 * of a CardVehicleRecord object.
	 * 
	 * @return	the vehicle odometer value at the end of the period of use of the vehicle
	 * 			of the CardVehicleRecord object
	 */
	public OdometerShort getVehicleOdometerEnd() {
		return vehicleOdometerEnd;
	}

	/**
	 * Sets the vehicle odometer value at the end of the period of use of the vehicle
	 * of a CardVehicleRecord object.
	 * 
	 * @param	vehicleOdometerEnd	the vehicle odometer value at the end of the period
	 * 								of use of the vehicle to be set for the CardVehicleRecord object
	 */
	public void setVehicleOdometerEnd( OdometerShort vehicleOdometerEnd ) {
		this.vehicleOdometerEnd = vehicleOdometerEnd;
	}

	/**
	 * Returns the date and time of the beginning of the period of use of the vehicle
	 * of a CardVehicleRecord object.
	 * 
	 * @return	the date and time of the beginning of the period of use of the vehicle
	 * 			of the CardVehicleRecord object
	 */
	public TimeReal getVehicleFirstUse() {
		return vehicleFirstUse;
	}

	/**
	 * Sets the date and time of the beginning of the period of use of the vehicle
	 * of a CardVehicleRecord object.
	 * 
	 * @param	vehicleFirstUse		the date and time of the beginning of the period of use of the vehicle
	 * 								to be set for the CardVehicleRecord object
	 */
	public void setVehicleFirstUse( TimeReal vehicleFirstUse ) {
		this.vehicleFirstUse = vehicleFirstUse;
	}

	/**
	 * Returns the date and time of the end of the period of use of the vehicle
	 * of a CardVehicleRecord object.
	 * 
	 * @return	the date and time of the end of the period of use of the vehicle
	 * 			of the CardVehicleRecord object
	 */
	public TimeReal getVehicleLastUse() {
		return vehicleLastUse;
	}

	/**
	 * Sets the date and time of the end of the period of use of the vehicle
	 * of a CardVehicleRecord object.
	 * 
	 * @param	vehicleLastUse	the date and time of the end of the period of use of the vehicle
	 * 							to be set for the CardVehicleRecord object
	 */
	public void setVehicleLastUse( TimeReal vehicleLastUse ) {
		this.vehicleLastUse = vehicleLastUse;
	}

	/**
	 * Returns the VRN and the registering Member State of the vehicle
	 * of a CardVehicleRecord object.
	 * 
	 * @return	the VRN and the registering Member State of the vehicle
	 * 			of the CardVehicleRecord object
	 */
	public VehicleRegistrationIdentification getVehicleRegistration() {
		return vehicleRegistration;
	}

	/**
	 * Sets the VRN and the registering Member State of the vehicle
	 * of a CardVehicleRecord object.
	 * 
	 * @param	vehicleRegistration		the VRN and the registering Member State of the vehicle
	 * 									to be set for the CardVehicleRecord object
	 */
	public void setVehicleRegistration( VehicleRegistrationIdentification vehicleRegistration ) {
		this.vehicleRegistration = vehicleRegistration;
	}

	/**
	 * Returns the value of the VuDataBlockCounter at last extraction of the period of use of the vehicle
	 * of a CardVehicleRecord object.
	 * 
	 * @return	the value of the VuDataBlockCounter at last extraction of the period of use of the vehicle
	 * 			of the CardVehicleRecord object
	 */
	public byte[] getVuDataBlockCounter() {
		return vuDataBlockCounter.getVuDataBlockCounter();
	}

	/**
	 * Sets the value of the VuDataBlockCounter at last extraction of the period of use of the vehicle
	 * of a CardVehicleRecord object.
	 * 
	 * @param	vuDataBlockCounter	the value of the VuDataBlockCounter at last extraction of the period
	 * 								of use of the vehicle to be set for the CardVehicleRecord object
	 */
	public void setVuDataBlockCounter( byte[] vuDataBlockCounter ) {
		this.vuDataBlockCounter.setVuDataBlockCounter( vuDataBlockCounter );
	}
	
	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		node.addContent( vehicleOdometerBegin.generateXMLElement( "vehicleOdometerBegin" ) );
		node.addContent( vehicleOdometerEnd.generateXMLElement( "vehicleOdometerEnd" ) );
		node.addContent( vehicleFirstUse.generateXMLElement( "vehicleFirstUse" ) );
		node.addContent( vehicleLastUse.generateXMLElement( "vehicleLastUse" ) );
		node.addContent( vehicleRegistration.generateXMLElement( "vehicleRegistration" ) );
		node.addContent( vuDataBlockCounter.generateXMLElement( "vuDataBlockCounter" ) );
		
		return node;
	}
}
