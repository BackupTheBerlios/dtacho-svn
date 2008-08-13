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
 * Information, stored in a vehicle unit, related to a fault.
 */
public class VuEventRecord extends DataClass {
	/*
	 * VuEventRecord ::= SEQUENCE {
	 * 	eventType EventFaultType, 1 byte
	 * 	eventRecordPurpose EventFaultRecordPurpose, 1 byte
	 * 	eventBeginTime TimeReal, 4 bytes
	 * 	eventEndTime TimeReal, 4 bytes
	 * 	cardNumberDriverSlotBegin FullCardNumber, 18 bytes
	 * 	cardNumberCodriverSlotBegin FullCardNumber, 18 bytes
	 * 	cardNumberDriverSlotEnd FullCardNumber, 18 bytes
	 * 	cardNumberCodriverSlotEnd FullCardNumber, 18 bytes
	 * 	similarEventsNumber SimilarEventsNumber, 1 bytes
	 * }
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 83;

	private EventFaultType eventType;
	private EventFaultRecordPurpose eventRecordPurpose;
	private TimeReal eventBeginTime;
	private TimeReal eventEndTime;
	private FullCardNumber cardNumberDriverSlotBegin;
	private FullCardNumber cardNumberCodriverSlotBegin;
	private FullCardNumber cardNumberDriverSlotEnd;
	private FullCardNumber cardNumberCodriverSlotEnd;
	private SimilarEventsNumber similarEventsNumber;

	private DebugLogger debugLogger;


	/**
	 * Constructor for a VuEventRecord object
	 */
	public VuEventRecord() {
		eventType = new EventFaultType();
		eventRecordPurpose = new EventFaultRecordPurpose();
		eventBeginTime = new TimeReal();
		eventEndTime = new TimeReal();
		cardNumberDriverSlotBegin = new FullCardNumber();
		cardNumberCodriverSlotBegin = new FullCardNumber();
		cardNumberDriverSlotEnd = new FullCardNumber();
		cardNumberCodriverSlotEnd = new FullCardNumber();
		similarEventsNumber = new SimilarEventsNumber();
	}

	/**
	 * Constructor for a VuEventRecord object
	 * 
	 * @param	value	byte array of a VuEventRecord structure
	 * 					whose data is used when the VuEventRecord
	 * 					object is created.
	 */
	public VuEventRecord( byte[] value ) {
		debugLogger = new DebugLogger();
		debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] ------------------------------------------------------------" );

		eventType = new EventFaultType( value[ 0 ] );
		debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Type of event: %02x - %s\n", eventType.getEventFaultType(), eventType.toString() );

		eventRecordPurpose = new EventFaultRecordPurpose( value[ 1 ] );
		debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Record purpose: %s\n", eventRecordPurpose.toString() );

		debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Event begin time:" );
		eventBeginTime = new TimeReal( arrayCopy( value, 2, 4 ) );

		debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Event end time:" );
		eventEndTime = new TimeReal( arrayCopy( value, 6, 4 ) );

		cardNumberDriverSlotBegin = new FullCardNumber( arrayCopy( value, 10, 18 ) );
		debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Card in driver slot at beginning of event:" );
		debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Type of card: %02x - %s\n", cardNumberDriverSlotBegin.getCardType().getEquipmentType(), cardNumberDriverSlotBegin.getCardType().toString() );
		debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Card issuing member state: %s\n", cardNumberDriverSlotBegin.getCardIssuingMemberState().toString() );
		if ( cardNumberDriverSlotBegin.getCardType().getEquipmentType() == EquipmentType.DRIVER_CARD ) {
			debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Card number: %s%s%s\n", cardNumberDriverSlotBegin.getCardNumber().getDriverIdentification(), cardNumberDriverSlotBegin.getCardNumber().getCardReplacementIndex().getCardReplacementIndex(), cardNumberDriverSlotBegin.getCardNumber().getCardRenewalIndex().getCardRenewalIndex() );
		}
		else {
			debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Card number: %s%s%s%s\n", cardNumberDriverSlotBegin.getCardNumber().getOwnerIdentification(), cardNumberDriverSlotBegin.getCardNumber().getCardConsecutiveIndex().getCardConsecutiveIndex(), cardNumberDriverSlotBegin.getCardNumber().getCardReplacementIndex().getCardReplacementIndex(), cardNumberDriverSlotBegin.getCardNumber().getCardRenewalIndex().getCardRenewalIndex() );
		}

		cardNumberCodriverSlotBegin = new FullCardNumber( arrayCopy( value, 28, 18 ) );
		debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Card in co-driver slot at beginning of event:" );
		debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Type of card: %02x - %s\n", cardNumberCodriverSlotBegin.getCardType().getEquipmentType(), cardNumberCodriverSlotBegin.getCardType().toString() );
		debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Card issuing member state: %s\n", cardNumberCodriverSlotBegin.getCardIssuingMemberState().toString() );
		if ( cardNumberCodriverSlotBegin.getCardType().getEquipmentType() == EquipmentType.DRIVER_CARD ) {
			debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Card number: %s%s%s\n", cardNumberCodriverSlotBegin.getCardNumber().getDriverIdentification(), cardNumberCodriverSlotBegin.getCardNumber().getCardReplacementIndex().getCardReplacementIndex(), cardNumberCodriverSlotBegin.getCardNumber().getCardRenewalIndex().getCardRenewalIndex() );
		}
		else {
			debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Card number: %s%s%s%s\n", cardNumberCodriverSlotBegin.getCardNumber().getOwnerIdentification(), cardNumberCodriverSlotBegin.getCardNumber().getCardConsecutiveIndex().getCardConsecutiveIndex(), cardNumberCodriverSlotBegin.getCardNumber().getCardReplacementIndex().getCardReplacementIndex(), cardNumberCodriverSlotBegin.getCardNumber().getCardRenewalIndex().getCardRenewalIndex() );
		}

		cardNumberDriverSlotEnd = new FullCardNumber( arrayCopy( value, 46, 18 ) );
		debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Card in driver slot at end of event:" );
		debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Type of card: %02x - %s\n", cardNumberDriverSlotEnd.getCardType().getEquipmentType(), cardNumberDriverSlotEnd.getCardType().toString() );
		debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Card issuing member state: %s\n", cardNumberDriverSlotEnd.getCardIssuingMemberState().toString() );
		if ( cardNumberDriverSlotEnd.getCardType().getEquipmentType() == EquipmentType.DRIVER_CARD ) {
			debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Card number: %s%s%s\n", cardNumberDriverSlotEnd.getCardNumber().getDriverIdentification(), cardNumberDriverSlotEnd.getCardNumber().getCardReplacementIndex().getCardReplacementIndex(), cardNumberDriverSlotEnd.getCardNumber().getCardRenewalIndex().getCardRenewalIndex() );
		}
		else {
			debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Card number: %s%s%s%s\n", cardNumberDriverSlotEnd.getCardNumber().getOwnerIdentification(), cardNumberDriverSlotEnd.getCardNumber().getCardConsecutiveIndex().getCardConsecutiveIndex(), cardNumberDriverSlotEnd.getCardNumber().getCardReplacementIndex().getCardReplacementIndex(), cardNumberDriverSlotEnd.getCardNumber().getCardRenewalIndex().getCardRenewalIndex() );
		}

		cardNumberCodriverSlotEnd = new FullCardNumber( arrayCopy( value, 64, 18 ) );
		debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Card in co-driver slot at end of event:" );
		debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Type of card: %02x - %s\n", cardNumberCodriverSlotEnd.getCardType().getEquipmentType(), cardNumberCodriverSlotEnd.getCardType().toString() );
		debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Card issuing member state: %s\n", cardNumberCodriverSlotEnd.getCardIssuingMemberState().toString() );
		if ( cardNumberCodriverSlotEnd.getCardType().getEquipmentType() == EquipmentType.DRIVER_CARD ) {
			debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Card number: %s%s%s\n", cardNumberCodriverSlotEnd.getCardNumber().getDriverIdentification(), cardNumberCodriverSlotEnd.getCardNumber().getCardReplacementIndex().getCardReplacementIndex(), cardNumberCodriverSlotEnd.getCardNumber().getCardRenewalIndex().getCardRenewalIndex() );
		}
		else {
			debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Card number: %s%s%s%s\n", cardNumberCodriverSlotEnd.getCardNumber().getOwnerIdentification(), cardNumberCodriverSlotEnd.getCardNumber().getCardConsecutiveIndex().getCardConsecutiveIndex(), cardNumberCodriverSlotEnd.getCardNumber().getCardReplacementIndex().getCardReplacementIndex(), cardNumberCodriverSlotEnd.getCardNumber().getCardRenewalIndex().getCardRenewalIndex() );
		}

		similarEventsNumber = new SimilarEventsNumber( value[ 82 ] );
		debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Number of similar events on that day: %d\n", similarEventsNumber.getSimilarEventsNumber() );
	}

	/**
	 * Returns the type of the event of a VuEventRecord object.
	 * 
	 * @return	the type of the event of the VuEventRecord object
	 */
	public EventFaultType getEventType() {
		return eventType;
	}

	/**
	 * Sets the type of the event of a VuEventRecord object.
	 * 
	 * @param	eventType		the type of the event
	 * 							to be set for the VuEventRecord object
	 */
	public void setEventType( EventFaultType eventType ) {
		this.eventType = eventType;
	}

	/**
	 * Returns the purpose for which this event has been recorded of a VuEventRecord object.
	 * 
	 * @return	the purpose for which this event has been recorded of the VuEventRecord object
	 */
	public EventFaultRecordPurpose getEventRecordPurpose() {
		return eventRecordPurpose;
	}

	/**
	 * Sets the purpose for which this event has been recorded of a VuEventRecord object.
	 * 
	 * @param	eventRecordPurpose		the purpose for which this event has been recorded
	 * 									to be set for the VuEventRecord object
	 */
	public void setEventRecordPurpose( EventFaultRecordPurpose eventRecordPurpose ) {
		this.eventRecordPurpose = eventRecordPurpose;
	}

	/**
	 * Returns the timestamp (date and time) of beginning of event
	 * of a VuEventRecord object.
	 * 
	 * @return	the timestamp of beginning of event of the VuEventRecord object
	 */
	public TimeReal getEventBeginTime() {
		return eventBeginTime;
	}

	/**
	 * Sets the timestamp (date and time) of beginning of event
	 * of a VuEventRecord object.
	 * 
	 * @param	eventBeginTime		the timestamp (date and time) of beginning of event
	 * 								to be set for the VuEventRecord object
	 */
	public void setEventBeginTime( TimeReal eventBeginTime ) {
		this.eventBeginTime = eventBeginTime;
	}

	/**
	 * Returns the timestamp (date and time) of end of event
	 * of a VuEventRecord object.
	 * 
	 * @return	the timestamp of end of event of the VuEventRecord object
	 */
	public TimeReal getEventEndTime() {
		return eventEndTime;
	}

	/**
	 * Sets the timestamp (date and time) of end of event
	 * of a VuEventRecord object.
	 * 
	 * @param	eventEndTime		the timestamp (date and time) of end of event
	 * 								to be set for the VuEventRecord object
	 */
	public void setEventEndTime( TimeReal eventEndTime ) {
		this.eventEndTime = eventEndTime;
	}

	/**
	 * Returns the identification of the card inserted in the driver slot
	 * at the beginning of the event of a VuEventRecord object.
	 * 
	 * @return	the identification of the card inserted in the driver slot
	 * 			at the beginning of the event of the VuEventRecord object
	 */
	public FullCardNumber getCardNumberDriverSlotBegin() {
		return cardNumberDriverSlotBegin;
	}

	/**
	 * Sets the identification of the card inserted in the driver slot
	 * at the beginning of the event of a VuEventRecord object.
	 * 
	 * @param	cardNumberDriverSlotBegin		the identification of the card inserted in the driver slot
	 * 											at the beginning of the event to be set for the VuEventRecord object
	 */
	public void setCardNumberDriverSlotBegin( FullCardNumber cardNumberDriverSlotBegin ) {
		this.cardNumberDriverSlotBegin = cardNumberDriverSlotBegin;
	}

	/**
	 * Returns the identification of the card inserted in the co-driver slot
	 * at the beginning of the event of a VuEventRecord object.
	 * 
	 * @return	the identification of the card inserted in the co-driver slot
	 * 			at the beginning of the event of the VuEventRecord object
	 */
	public FullCardNumber getCardNumberCodriverSlotBegin() {
		return cardNumberCodriverSlotBegin;
	}

	/**
	 * Sets the identification of the card inserted in the co-driver slot
	 * at the beginning of the event of a VuEventRecord object.
	 * 
	 * @param	cardNumberCodriverSlotBegin		the identification of the card inserted in the co-driver slot
	 * 											at the beginning of the event to be set for the VuEventRecord object
	 */
	public void setCardNumberCodriverSlotBegin( FullCardNumber cardNumberCodriverSlotBegin ) {
		this.cardNumberCodriverSlotBegin = cardNumberCodriverSlotBegin;
	}

	/**
	 * Returns the identification of the card inserted in the driver slot
	 * at the end of the event of a VuEventRecord object.
	 * 
	 * @return	the identification of the card inserted in the driver slot
	 * 			at the end of the event of the VuEventRecord object
	 */
	public FullCardNumber getCardNumberDriverSlotEnd() {
		return cardNumberDriverSlotEnd;
	}

	/**
	 * Sets the identification of the card inserted in the driver slot
	 * at the end of the event of a VuEventRecord object.
	 * 
	 * @param	cardNumberDriverSlotEnd		the identification of the card inserted in the driver slot
	 * 										at the end of the event to be set for the VuEventRecord object
	 */
	public void setCardNumberDriverSlotEnd( FullCardNumber cardNumberDriverSlotEnd ) {
		this.cardNumberDriverSlotEnd = cardNumberDriverSlotEnd;
	}

	/**
	 * Returns the identification of the card inserted in the co-driver slot
	 * at the end of the event of a VuEventRecord object.
	 * 
	 * @return	the identification of the card inserted in the co-driver slot
	 * 			at the end of the event of the VuEventRecord object
	 */
	public FullCardNumber getCardNumberCodriverSlotEnd() {
		return cardNumberCodriverSlotEnd;
	}

	/**
	 * Sets the identification of the card inserted in the co-driver slot
	 * at the end of the event of a VuEventRecord object.
	 * 
	 * @param	cardNumberCodriverSlotEnd		the identification of the card inserted in the co-driver slot
	 * 											at the end of the event to be set for the VuEventRecord object
	 */
	public void setCardNumberCodriverSlotEnd( FullCardNumber cardNumberCodriverSlotEnd ) {
		this.cardNumberCodriverSlotEnd = cardNumberCodriverSlotEnd;
	}

	/**
	 * Returns the number of similar events that day
	 * of a VuEventRecord object.
	 * 
	 * @return	the number of similar events that day
	 * 			of the VuEventRecord object
	 */
	public SimilarEventsNumber getSimilarEventsNumber() {
		return similarEventsNumber;
	}

	/**
	 * Sets the number of similar events that day
	 * of a VuEventRecord object.
	 * 
	 * @param	similarEventsNumber		the number of similar events that day
	 * 									to be set for the VuEventRecord object
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
		node.addContent( cardNumberDriverSlotBegin.generateXMLElement( "cardNumberDriverSlotBegin" ) );
		node.addContent( cardNumberCodriverSlotBegin.generateXMLElement( "cardNumberCodriverSlotBegin" ) );
		node.addContent( cardNumberDriverSlotEnd.generateXMLElement( "cardNumberDriverSlotEnd" ) );
		node.addContent( cardNumberCodriverSlotEnd.generateXMLElement( "cardNumberCodriverSlotEnd" ) );
		node.addContent( similarEventsNumber.generateXMLElement( "similarEventsNumber" ) );

		return node;
	}
}
