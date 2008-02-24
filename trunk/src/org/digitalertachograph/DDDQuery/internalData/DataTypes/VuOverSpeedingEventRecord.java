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
 * Information, stored in a vehicle unit, related to a fault.
 */
public class VuOverSpeedingEventRecord extends DataClass {
	/*
	 * VuOverSpeedingEventRecord ::= SEQUENCE {
	 * 	eventType EventFaultType, 1 byte
	 * 	eventRecordPurpose EventFaultRecordPurpose, 1 byte
	 * 	eventBeginTime TimeReal, 4 bytes
	 * 	eventEndTime TimeReal, 4 bytes
	 * 	maxSpeedValue SpeedMax, 1 byte
	 * 	averageSpeedValue SpeedAverage, 1 byte
	 * 	cardNumberDriverSlotBegin FullCardNumber, 18 bytes
	 * 	similarEventsNumber SimilarEventsNumber, 1 byte
	 * }
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 31;

	private EventFaultType eventType;
	private EventFaultRecordPurpose eventRecordPurpose;
	private TimeReal eventBeginTime;
	private TimeReal eventEndTime;
	private SpeedMax maxSpeedValue;
	private SpeedAverage averageSpeedValue;
	private FullCardNumber cardNumberDriverSlotBegin;
	private SimilarEventsNumber similarEventsNumber;


	/**
	 * Constructor for a VuOverSpeedingEventRecord object
	 */
	public VuOverSpeedingEventRecord() {
		eventType = new EventFaultType();
		eventRecordPurpose = new EventFaultRecordPurpose();
		eventBeginTime = new TimeReal();
		eventEndTime = new TimeReal();
		maxSpeedValue = new SpeedMax();
		averageSpeedValue = new SpeedAverage();
		cardNumberDriverSlotBegin = new FullCardNumber();
		similarEventsNumber = new SimilarEventsNumber();
	}

	/**
	 * Constructor for a VuOverSpeedingEventRecord object
	 * 
	 * @param	value	byte array of a VuOverSpeedingEventRecord structure
	 * 					whose data is used when the VuOverSpeedingEventRecord
	 * 					object is created.
	 */
	public VuOverSpeedingEventRecord( byte[] value ) {
		eventType = new EventFaultType( value[ 0 ] );
		eventRecordPurpose = new EventFaultRecordPurpose( value[ 1 ] );
		eventBeginTime = new TimeReal( arrayCopy( value, 2, 4 ) );
		eventEndTime = new TimeReal( arrayCopy( value, 6, 4 ) );
		maxSpeedValue = new SpeedMax( value[ 10 ] );
		averageSpeedValue = new SpeedAverage( value[ 11 ] );
		cardNumberDriverSlotBegin = new FullCardNumber( arrayCopy( value, 12, 18 ) );
		similarEventsNumber = new SimilarEventsNumber( value[ 30 ] );
	}

	/**
	 * Returns the type of the event of a VuOverSpeedingEventRecord object.
	 * 
	 * @return	the type of the event of the VuOverSpeedingEventRecord object
	 */
	public EventFaultType getEventType() {
		return eventType;
	}

	/**
	 * Sets the type of the event of a VuOverSpeedingEventRecord object.
	 * 
	 * @param	eventType		the type of the event
	 * 							to be set for the VuOverSpeedingEventRecord object
	 */
	public void setEventType( EventFaultType eventType ) {
		this.eventType = eventType;
	}

	/**
	 * Returns the purpose for which this event has been recorded of a VuOverSpeedingEventRecord object.
	 * 
	 * @return	the purpose for which this event has been recorded of the VuOverSpeedingEventRecord object
	 */
	public EventFaultRecordPurpose getEventRecordPurpose() {
		return eventRecordPurpose;
	}

	/**
	 * Sets the purpose for which this event has been recorded of a VuOverSpeedingEventRecord object.
	 * 
	 * @param	eventRecordPurpose		the purpose for which this event has been recorded
	 * 									to be set for the VuOverSpeedingEventRecord object
	 */
	public void setEventRecordPurpose( EventFaultRecordPurpose eventRecordPurpose ) {
		this.eventRecordPurpose = eventRecordPurpose;
	}

	/**
	 * Returns the timestamp (date and time) of beginning of event
	 * of a VuOverSpeedingEventRecord object.
	 * 
	 * @return	the timestamp of beginning of event of the VuOverSpeedingEventRecord object
	 */
	public TimeReal getEventBeginTime() {
		return eventBeginTime;
	}

	/**
	 * Sets the timestamp (date and time) of beginning of event
	 * of a VuOverSpeedingEventRecord object.
	 * 
	 * @param	eventBeginTime		the timestamp (date and time) of beginning of event
	 * 								to be set for the VuOverSpeedingEventRecord object
	 */
	public void setEventBeginTime( TimeReal eventBeginTime ) {
		this.eventBeginTime = eventBeginTime;
	}

	/**
	 * Returns the timestamp (date and time) of end of event
	 * of a VuOverSpeedingEventRecord object.
	 * 
	 * @return	the timestamp of end of event of the VuOverSpeedingEventRecord object
	 */
	public TimeReal getEventEndTime() {
		return eventEndTime;
	}

	/**
	 * Sets the timestamp (date and time) of end of event
	 * of a VuOverSpeedingEventRecord object.
	 * 
	 * @param	eventEndTime		the timestamp (date and time) of end of event
	 * 								to be set for the VuOverSpeedingEventRecord object
	 */
	public void setEventEndTime( TimeReal eventEndTime ) {
		this.eventEndTime = eventEndTime;
	}



	/**
	 * Returns the maximum speed measured during the event of a VuOverSpeedingEventRecord object.
	 * 
	 * @return	the maximum speed measured during the event of the VuOverSpeedingEventRecord object
	 */
	public SpeedMax getSpeedMax() {
		return maxSpeedValue;
	}

	/**
	 * Sets the maximum speed measured during the event of a VuOverSpeedingEventRecord object.
	 * 
	 * @param	maxSpeedValue		the maximum speed measured during the event
	 * 								to be set for the VuOverSpeedingEventRecord object
	 */
	public void setSpeedMax( SpeedMax maxSpeedValue ) {
		this.maxSpeedValue = maxSpeedValue;
	}

	/**
	 * Returns the arithmetic average speed measured during the event a VuOverSpeedingEventRecord object.
	 * 
	 * @return	the arithmetic average speed measured during the event of the VuOverSpeedingEventRecord object
	 */
	public SpeedAverage getSpeedAverage() {
		return averageSpeedValue;
	}

	/**
	 * Sets the arithmetic average speed measured during the event of a VuOverSpeedingEventRecord object.
	 * 
	 * @param	averageSpeedValue		the arithmetic average speed measured during the event
	 * 								to be set for the VuOverSpeedingEventRecord object
	 */
	public void setSpeedAverage( SpeedAverage averageSpeedValue ) {
		this.averageSpeedValue = averageSpeedValue;
	}

	/**
	 * Returns the identification of the card inserted in the driver slot
	 * at the beginning of the event of a VuOverSpeedingEventRecord object.
	 * 
	 * @return	the identification of the card inserted in the driver slot
	 * 			at the beginning of the event of the VuOverSpeedingEventRecord object
	 */
	public FullCardNumber getCardNumberDriverSlotBegin() {
		return cardNumberDriverSlotBegin;
	}

	/**
	 * Sets the identification of the card inserted in the driver slot
	 * at the beginning of the event of a VuOverSpeedingEventRecord object.
	 * 
	 * @param	cardNumberDriverSlotBegin		the identification of the card inserted in the driver slot
	 * 											at the beginning of the event to be set for the VuOverSpeedingEventRecord object
	 */
	public void setCardNumberDriverSlotBegin( FullCardNumber cardNumberDriverSlotBegin ) {
		this.cardNumberDriverSlotBegin = cardNumberDriverSlotBegin;
	}

	/**
	 * Returns the number of similar events that day
	 * of a VuOverSpeedingEventRecord object.
	 * 
	 * @return	the number of similar events that day
	 * 			of the VuOverSpeedingEventRecord object
	 */
	public SimilarEventsNumber getSimilarEventsNumber() {
		return similarEventsNumber;
	}

	/**
	 * Sets the number of similar events that day
	 * of a VuOverSpeedingEventRecord object.
	 * 
	 * @param	similarEventsNumber		the number of similar events that day
	 * 									to be set for the VuOverSpeedingEventRecord object
	 */
	public void setSimilarEventsNumber( SimilarEventsNumber similarEventsNumber ) {
		this.similarEventsNumber = similarEventsNumber;
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		node.addContent( eventType.generateXMLElement( "eventType" ) );
		node.addContent( eventRecordPurpose.generateXMLElement( "eventRecordPurpose" ) );
		node.addContent( eventBeginTime.generateXMLElement( "eventBeginTime" ) );
		node.addContent( eventEndTime.generateXMLElement( "eventEndTime" ) );
		node.addContent( maxSpeedValue.generateXMLElement( "maxSpeedValue" ) );
		node.addContent( averageSpeedValue.generateXMLElement( "averageSpeedValue" ) );
		node.addContent( cardNumberDriverSlotBegin.generateXMLElement( "cardNumberDriverSlotBegin" ) );
		node.addContent( similarEventsNumber.generateXMLElement( "similarEventsNumber" ) );

		return node;
	}
}
