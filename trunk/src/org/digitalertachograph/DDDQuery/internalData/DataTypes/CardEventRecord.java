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

import org.digitalertachograph.DDDQuery.DebugLogger;
import org.digitalertachograph.DDDQuery.internalData.DataClass;
import org.jdom.Element;

/**
 * Information, stored in a driver or a workshop card, related to an event associated
 * to the card holder.
 */
public class CardEventRecord extends DataClass {
	/*
	 * CardEventRecord ::= SEQUENCE {
	 * 	eventType EventFaultType, 1 byte
	 * 	eventBeginTime TimeReal, 4 bytes
	 * 	eventEndTime TimeReal, 4 bytes
	 * 	eventVehicleRegistration VehicleRegistrationIdentification, 15 bytes
	 * }
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 24;

	private EventFaultType eventType; 
	private TimeReal eventBeginTime;
	private TimeReal eventEndTime;
	private VehicleRegistrationIdentification eventVehicleRegistration;

	private DebugLogger debugLogger;


	/**
	 * Constructor for a CardEventRecord object
	 */
	public CardEventRecord() {
		eventType = new EventFaultType();
		eventBeginTime = new TimeReal();
		eventEndTime = new TimeReal();
		eventVehicleRegistration = new VehicleRegistrationIdentification();
	}

	/**
	 * Constructor for a CardEventRecord object
	 * 
	 * @param	value	byte array of a CardEventRecord structure
	 * 					whose data is used when the CardEventRecord
	 * 					object is created.
	 */
	public CardEventRecord( byte[] value ) {
		debugLogger = new DebugLogger();

		eventType = new EventFaultType( value[ 0 ] );

		long eventBeginTimeTmp = convertIntoUnsigned4ByteInt( arrayCopy( value, 1, 4 ) );
		if ( eventBeginTimeTmp != 0 ) {
			debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT]" );
			debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Event begin time:" );
		}
		eventBeginTime = new TimeReal( arrayCopy( value, 1, 4 ) );

		long eventEndTimeTmp = convertIntoUnsigned4ByteInt( arrayCopy( value, 5, 4 ) );
		if ( eventEndTimeTmp != 0 ) {
			debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Event end time:" );
		}
		eventEndTime = new TimeReal( arrayCopy( value, 5, 4 ) );

		eventVehicleRegistration = new VehicleRegistrationIdentification( arrayCopy( value, 9, 15 ) );
	}

	/**
	 * Returns the type of the event of a CardEventRecord object.
	 * 
	 * @return	the type of the event of the CardEventRecord object
	 */
	public EventFaultType getEventType() {
		return eventType;
	}

	/**
	 * Sets the type of the event of a CardEventRecord object.
	 * 
	 * @param	eventType		the type of the event to be set for the CardEventRecord object
	 */
	public void setEventType( EventFaultType eventType ) {
		this.eventType = eventType;
	}

	/**
	 * Returns the timestamp (date and time) of beginning of event
	 * of a CardEventRecord object.
	 * 
	 * @return	the timestamp of beginning of event of the CardEventRecord object
	 */
	public TimeReal getEventBeginTime() {
		return eventBeginTime;
	}

	/**
	 * Sets the timestamp (date and time) of beginning of event of
	 * of a CardEventRecord object.
	 * 
	 * @param	eventBeginTime		the timestamp (date and time) of beginning of event
	 * 								to be set for the CardEventRecord object
	 */
	public void setEventBeginTime( TimeReal eventBeginTime ) {
		this.eventBeginTime = eventBeginTime;
	}

	/**
	 * Returns the timestamp (date and time) of end of event
	 * of a CardEventRecord object.
	 * 
	 * @return	the timestamp of end of event of the CardEventRecord object
	 */
	public TimeReal getEventEndTime() {
		return eventEndTime;
	}

	/**
	 * Sets the timestamp (date and time) of end of event of
	 * of a CardEventRecord object.
	 * 
	 * @param	eventEndTime		the timestamp (date and time) of end of event
	 * 								to be set for the CardEventRecord object
	 */
	public void setEventEndTime( TimeReal eventEndTime ) {
		this.eventEndTime = eventEndTime;
	}

	/**
	 * Returns the VRN and registering Member State of vehicle in which the event happened
	 * of a CardEventRecord object.
	 * 
	 * @return	the VRN and registering Member State of vehicle in which the event happened
	 * 			of the CardEventRecord object
	 */
	public VehicleRegistrationIdentification getEventVehicleRegistration() {
		return eventVehicleRegistration;
	}

	/**
	 * Sets the VRN and registering Member State of vehicle in which the event happened
	 * of a CardEventRecord object.
	 * 
	 * @param	eventVehicleRegistration		the VRN and registering Member State of vehicle
	 * 											in which the event happened	to be set for the
	 * 											CardEventRecord object
	 */
	public void setEventVehicleRegistration( VehicleRegistrationIdentification eventVehicleRegistration ) {
		this.eventVehicleRegistration = eventVehicleRegistration;
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		node.addContent( eventType.generateXMLElement( "eventType" ) );
		node.addContent( eventBeginTime.generateXMLElement( "eventBeginTime" ) );
		node.addContent( eventEndTime.generateXMLElement( "eventEndTime" ) );
		node.addContent( eventVehicleRegistration.generateXMLElement( "eventVehicleRegistration" ) );

		return node;
	}
}
