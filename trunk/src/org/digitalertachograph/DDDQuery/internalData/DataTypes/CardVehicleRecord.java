/*
    $Id:CardVehicleRecord.java 26 2008-02-25 22:28:27Z deetee $

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

import org.digitalertachograph.DDDQuery.DebugLogger;
import org.digitalertachograph.DDDQuery.internalData.DataClass;
import org.jdom.Element;

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

	private int vehicleOdometerBegin;
	private int vehicleOdometerEnd;
	private TimeReal vehicleFirstUse;
	private TimeReal vehicleLastUse;
	private VehicleRegistrationIdentification vehicleRegistration;
	private VuDataBlockCounter vuDataBlockCounter;

	private DebugLogger debugLogger;


	/**
	 * Constructor for a CardVehicleRecord object
	 */
	public CardVehicleRecord() {
		vehicleOdometerBegin = 0;
		vehicleOdometerEnd = 0;
		vehicleFirstUse = new TimeReal();
		vehicleLastUse = new TimeReal();
		vehicleRegistration = new VehicleRegistrationIdentification();
		vuDataBlockCounter = new VuDataBlockCounter();

		debugLogger = new DebugLogger();
	}

	/**
	 * Constructor for a CardVehicleRecord object
	 * 
	 * @param	value	byte array of a CardVehicleRecord structure
	 * 					whose data is used when the CardVehicleRecord
	 * 					object is created.
	 */
	public CardVehicleRecord( byte[] value ) {
		vehicleOdometerBegin = convertIntoUnsigned3ByteInt( arrayCopy( value, 0, 3 ) );
		vehicleOdometerEnd = convertIntoUnsigned3ByteInt( arrayCopy( value, 3, 3 ) );
		vehicleFirstUse = new TimeReal( arrayCopy( value, 6, 4 ) );
		vehicleLastUse = new TimeReal( arrayCopy( value, 10, 4) );
		vehicleRegistration = new VehicleRegistrationIdentification( arrayCopy( value, 14, 15 ) );
		vuDataBlockCounter = new VuDataBlockCounter( arrayCopy( value, 29, 2 ) );

		debugLogger = new DebugLogger();

		if ( vehicleFirstUse.getTimereal() != 0 ) {
			debugLogger.print( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  vehicle registration number: " );

			byte[] vr = vehicleRegistration.getVehicleRegistrationNumber().getVehicleRegNumber();

			for ( int i = 0; i < vr.length; i++ ) {
				debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "%c", vr[ i ] );
			}

			debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED );
		}
	}

	/**
	 * Returns the vehicle odometer value at the beginning of the period of use of the vehicle
	 * of a CardVehicleRecord object.
	 * 
	 * @return	the vehicle odometer value at the beginning of the period of use of the vehicle
	 * 			of the CardVehicleRecord object
	 */
	public int getVehicleOdometerBegin() {
		return vehicleOdometerBegin;
	}

	/**
	 * Sets the vehicle odometer value at the beginning of the period of use of the vehicle
	 * of a CardVehicleRecord object.
	 * 
	 * @param	vehicleOdometerBegin	the vehicle odometer value at the beginning of the period
	 * 									of use of the vehicle to be set for the CardVehicleRecord object
	 */
	public void setVehicleOdometerBegin( int vehicleOdometerBegin ) {
		this.vehicleOdometerBegin = vehicleOdometerBegin;
	}

	/**
	 * Returns the vehicle odometer value at the end of the period of use of the vehicle
	 * of a CardVehicleRecord object.
	 * 
	 * @return	the vehicle odometer value at the end of the period of use of the vehicle
	 * 			of the CardVehicleRecord object
	 */
	public int getVehicleOdometerEnd() {
		return vehicleOdometerEnd;
	}

	/**
	 * Sets the vehicle odometer value at the end of the period of use of the vehicle
	 * of a CardVehicleRecord object.
	 * 
	 * @param	vehicleOdometerEnd	the vehicle odometer value at the end of the period
	 * 								of use of the vehicle to be set for the CardVehicleRecord object
	 */
	public void setVehicleOdometerEnd( int vehicleOdometerEnd ) {
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

		node.addContent( new Element( "vehicleOdometerBegin" ).setText( Integer.toString( vehicleOdometerBegin ) ) );
		node.addContent( new Element( "vehicleOdometerEnd" ).setText( Integer.toString( vehicleOdometerEnd ) ) );
		node.addContent( vehicleFirstUse.generateXMLElement( "vehicleFirstUse" ) );
		node.addContent( vehicleLastUse.generateXMLElement( "vehicleLastUse" ) );
		node.addContent( vehicleRegistration.generateXMLElement( "vehicleRegistration" ) );
		node.addContent( vuDataBlockCounter.generateXMLElement( "vuDataBlockCounter" ) );
		
		return node;
	}
}
