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

public class VuFaultRecord extends DataClass {
	/*
	 * VuFaultRecord ::= SEQUENCE {
	 * 	faultType EventFaultType, 1 byte
	 * 	faultRecordPurpose EventFaultRecordPurpose, 1 byte
	 * 	faultBeginTime TimeReal, 4 bytes
	 * 	faultEndTime TimeReal, 4 bytes
	 * 	cardNumberDriverSlotBegin FullCardNumber, 18 bytes
	 * 	cardNumberCodriverSlotBegin FullCardNumber, 18 bytes
	 * 	cardNumberDriverSlotEnd FullCardNumber, 18 bytes
	 * 	cardNumberCodriverSlotEnd FullCardNumber, 18 bytes
	 * }
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 82;

	private EventFaultType faultType;
	private EventFaultRecordPurpose faultRecordPurpose;
	private TimeReal faultBeginTime;
	private TimeReal faultEndTime;
	private FullCardNumber cardNumberDriverSlotBegin;
	private FullCardNumber cardNumberCodriverSlotBegin;
	private FullCardNumber cardNumberDriverSlotEnd;
	private FullCardNumber cardNumberCodriverSlotEnd;

	private DebugLogger debugLogger;


	/**
	 * Constructor for a VuFaultRecord object
	 */
	public VuFaultRecord() {
		faultType = new EventFaultType();
		faultRecordPurpose = new EventFaultRecordPurpose();
		faultBeginTime = new TimeReal();
		faultEndTime = new TimeReal();
		cardNumberDriverSlotBegin = new FullCardNumber();
		cardNumberCodriverSlotBegin = new FullCardNumber();
		cardNumberDriverSlotEnd = new FullCardNumber();
		cardNumberCodriverSlotEnd = new FullCardNumber();
	}

	/**
	 * Constructor for a VuFaultRecord object
	 * 
	 * @param	value	byte array of a VuFaultRecord structure
	 * 					whose data is used when the VuFaultRecord
	 * 					object is created.
	 */
	public VuFaultRecord( byte[] value ) {
		debugLogger = new DebugLogger();
		debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] ------------------------------------------------------------" );

		faultType = new EventFaultType( value[ 0 ] );
		debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Type of fault: %02x - %s\n", faultType.getEventFaultType(), faultType.toString() );

		faultRecordPurpose = new EventFaultRecordPurpose( value[ 1 ] );
		debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Record purpose: %s\n", faultRecordPurpose.toString() );

		debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Fault begin time:" );
		faultBeginTime = new TimeReal( arrayCopy( value, 2, 4 ) );

		debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Fault end time:" );
		faultEndTime = new TimeReal( arrayCopy( value, 6, 4 ) );

		cardNumberDriverSlotBegin = new FullCardNumber( arrayCopy( value, 10, 18 ) );
		debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Card in driver slot at beginning of event:" );
		debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Type of card: %02x - %s\n", cardNumberDriverSlotBegin.getCardType().getEquipmentType(), cardNumberDriverSlotBegin.getCardType().toString() );
		debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Card issuing member state: %s\n", cardNumberDriverSlotBegin.getCardIssuingMemberState().toString() );
		if ( cardNumberDriverSlotBegin.getCardType().getEquipmentType() == EquipmentType.DRIVER_CARD ) {
			debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Card number: %s%s%s\n", cardNumberDriverSlotBegin.getCardNumber().getDriverIdentification(), cardNumberDriverSlotBegin.getCardNumber().getCardReplacementIndex().getCardReplacementIndex(), cardNumberDriverSlotBegin.getCardNumber().getCardRenewalIndex().getCardRenewalIndex() );
		}
		else {
			debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Card number: %s%s%s%s\n", cardNumberDriverSlotBegin.getCardNumber().getOwnerIdentification(), cardNumberDriverSlotBegin.getCardNumber().getCardConsecutiveIndex().getCardConsecutiveIndex(), cardNumberDriverSlotBegin.getCardNumber().getCardReplacementIndex().getCardReplacementIndex(), cardNumberDriverSlotBegin.getCardNumber().getCardRenewalIndex().getCardRenewalIndex() );
		}

		cardNumberCodriverSlotBegin = new FullCardNumber( arrayCopy( value, 28, 18 ) );
		debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Card in co-driver slot at beginning of event:" );
		debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Type of card: %02x - %s\n", cardNumberCodriverSlotBegin.getCardType().getEquipmentType(), cardNumberCodriverSlotBegin.getCardType().toString() );
		debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Card issuing member state: %s\n", cardNumberCodriverSlotBegin.getCardIssuingMemberState().toString() );
		if ( cardNumberCodriverSlotBegin.getCardType().getEquipmentType() == EquipmentType.DRIVER_CARD ) {
			debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Card number: %s%s%s\n", cardNumberCodriverSlotBegin.getCardNumber().getDriverIdentification(), cardNumberCodriverSlotBegin.getCardNumber().getCardReplacementIndex().getCardReplacementIndex(), cardNumberCodriverSlotBegin.getCardNumber().getCardRenewalIndex().getCardRenewalIndex() );
		}
		else {
			debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Card number: %s%s%s%s\n", cardNumberCodriverSlotBegin.getCardNumber().getOwnerIdentification(), cardNumberCodriverSlotBegin.getCardNumber().getCardConsecutiveIndex().getCardConsecutiveIndex(), cardNumberCodriverSlotBegin.getCardNumber().getCardReplacementIndex().getCardReplacementIndex(), cardNumberCodriverSlotBegin.getCardNumber().getCardRenewalIndex().getCardRenewalIndex() );
		}

		cardNumberDriverSlotEnd = new FullCardNumber( arrayCopy( value, 46, 18 ) );
		debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Card in driver slot at end of event:" );
		debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Type of card: %02x - %s\n", cardNumberDriverSlotEnd.getCardType().getEquipmentType(), cardNumberDriverSlotEnd.getCardType().toString() );
		debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Card issuing member state: %s\n", cardNumberDriverSlotEnd.getCardIssuingMemberState().toString() );
		if ( cardNumberDriverSlotEnd.getCardType().getEquipmentType() == EquipmentType.DRIVER_CARD ) {
			debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Card number: %s%s%s\n", cardNumberDriverSlotEnd.getCardNumber().getDriverIdentification(), cardNumberDriverSlotEnd.getCardNumber().getCardReplacementIndex().getCardReplacementIndex(), cardNumberDriverSlotEnd.getCardNumber().getCardRenewalIndex().getCardRenewalIndex() );
		}
		else {
			debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Card number: %s%s%s%s\n", cardNumberDriverSlotEnd.getCardNumber().getOwnerIdentification(), cardNumberDriverSlotEnd.getCardNumber().getCardConsecutiveIndex().getCardConsecutiveIndex(), cardNumberDriverSlotEnd.getCardNumber().getCardReplacementIndex().getCardReplacementIndex(), cardNumberDriverSlotEnd.getCardNumber().getCardRenewalIndex().getCardRenewalIndex() );
		}

		cardNumberCodriverSlotEnd = new FullCardNumber( arrayCopy( value, 64, 18 ) );
		debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Card in co-driver slot at end of event:" );
		debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Type of card: %02x - %s\n", cardNumberCodriverSlotEnd.getCardType().getEquipmentType(), cardNumberCodriverSlotEnd.getCardType().toString() );
		debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Card issuing member state: %s\n", cardNumberCodriverSlotEnd.getCardIssuingMemberState().toString() );
		if ( cardNumberCodriverSlotEnd.getCardType().getEquipmentType() == EquipmentType.DRIVER_CARD ) {
			debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Card number: %s%s%s\n", cardNumberCodriverSlotEnd.getCardNumber().getDriverIdentification(), cardNumberCodriverSlotEnd.getCardNumber().getCardReplacementIndex().getCardReplacementIndex(), cardNumberCodriverSlotEnd.getCardNumber().getCardRenewalIndex().getCardRenewalIndex() );
		}
		else {
			debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Card number: %s%s%s%s\n", cardNumberCodriverSlotEnd.getCardNumber().getOwnerIdentification(), cardNumberCodriverSlotEnd.getCardNumber().getCardConsecutiveIndex().getCardConsecutiveIndex(), cardNumberCodriverSlotEnd.getCardNumber().getCardReplacementIndex().getCardReplacementIndex(), cardNumberCodriverSlotEnd.getCardNumber().getCardRenewalIndex().getCardRenewalIndex() );
		}
	}

	/**
	 * Returns the type of the fault of a VuFaultRecord object.
	 * 
	 * @return	the type of the fault of the VuFaultRecord object
	 */
	public EventFaultType getFaultType() {
		return faultType;
	}

	/**
	 * Sets the type of the fault of a VuFaultRecord object.
	 * 
	 * @param	faultType		the type of the fault
	 * 							to be set for the VuFaultRecord object
	 */
	public void setFaultType( EventFaultType faultType ) {
		this.faultType = faultType;
	}

	/**
	 * Returns the purpose for which this fault has been recorded of a VuFaultRecord object.
	 * 
	 * @return	the purpose for which this fault has been recorded of the VuFaultRecord object
	 */
	public EventFaultRecordPurpose getFaultRecordPurpose() {
		return faultRecordPurpose;
	}

	/**
	 * Sets the purpose for which this fault has been recorded of a VuFaultRecord object.
	 * 
	 * @param	faultRecordPurpose		the purpose for which this fault has been recorded
	 * 									to be set for the VuFaultRecord object
	 */
	public void setFaultRecordPurpose( EventFaultRecordPurpose faultRecordPurpose ) {
		this.faultRecordPurpose = faultRecordPurpose;
	}

	/**
	 * Returns the timestamp (date and time) of beginning of fault
	 * of a VuFaultRecord object.
	 * 
	 * @return	the timestamp of beginning of fault of the VuFaultRecord object
	 */
	public TimeReal getFaultBeginTime() {
		return faultBeginTime;
	}

	/**
	 * Sets the timestamp (date and time) of beginning of fault
	 * of a VuFaultRecord object.
	 * 
	 * @param	faultBeginTime		the timestamp (date and time) of beginning of fault
	 * 								to be set for the VuFaultRecord object
	 */
	public void setFaultBeginTime( TimeReal faultBeginTime ) {
		this.faultBeginTime = faultBeginTime;
	}

	/**
	 * Returns the timestamp (date and time) of end of fault
	 * of a VuFaultRecord object.
	 * 
	 * @return	the timestamp of end of fault of the VuFaultRecord object
	 */
	public TimeReal getFaultEndTime() {
		return faultEndTime;
	}

	/**
	 * Sets the timestamp (date and time) of end of fault
	 * of a VuFaultRecord object.
	 * 
	 * @param	faultEndTime		the timestamp (date and time) of end of fault
	 * 								to be set for the VuFaultRecord object
	 */
	public void setFaultEndTime( TimeReal faultEndTime ) {
		this.faultEndTime = faultEndTime;
	}

	/**
	 * Returns the identification of the card inserted in the driver slot
	 * at the beginning of the fault of a VuFaultRecord object.
	 * 
	 * @return	the identification of the card inserted in the driver slot
	 * 			at the beginning of the fault of the VuFaultRecord object
	 */
	public FullCardNumber getCardNumberDriverSlotBegin() {
		return cardNumberDriverSlotBegin;
	}

	/**
	 * Sets the identification of the card inserted in the driver slot
	 * at the beginning of the fault of a VuFaultRecord object.
	 * 
	 * @param	cardNumberDriverSlotBegin		the identification of the card inserted in the driver slot
	 * 											at the beginning of the fault to be set for the VuFaultRecord object
	 */
	public void setCardNumberDriverSlotBegin( FullCardNumber cardNumberDriverSlotBegin ) {
		this.cardNumberDriverSlotBegin = cardNumberDriverSlotBegin;
	}

	/**
	 * Returns the identification of the card inserted in the co-driver slot
	 * at the beginning of the fault of a VuFaultRecord object.
	 * 
	 * @return	the identification of the card inserted in the co-driver slot
	 * 			at the beginning of the fault of the VuFaultRecord object
	 */
	public FullCardNumber getCardNumberCodriverSlotBegin() {
		return cardNumberCodriverSlotBegin;
	}

	/**
	 * Sets the identification of the card inserted in the co-driver slot
	 * at the beginning of the fault of a VuFaultRecord object.
	 * 
	 * @param	cardNumberCodriverSlotBegin		the identification of the card inserted in the co-driver slot
	 * 											at the beginning of the fault to be set for the VuFaultRecord object
	 */
	public void setCardNumberCodriverSlotBegin( FullCardNumber cardNumberCodriverSlotBegin ) {
		this.cardNumberCodriverSlotBegin = cardNumberCodriverSlotBegin;
	}

	/**
	 * Returns the identification of the card inserted in the driver slot
	 * at the end of the fault of a VuFaultRecord object.
	 * 
	 * @return	the identification of the card inserted in the driver slot
	 * 			at the end of the fault of the VuFaultRecord object
	 */
	public FullCardNumber getCardNumberDriverSlotEnd() {
		return cardNumberDriverSlotEnd;
	}

	/**
	 * Sets the identification of the card inserted in the driver slot
	 * at the end of the fault of a VuFaultRecord object.
	 * 
	 * @param	cardNumberDriverSlotEnd		the identification of the card inserted in the driver slot
	 * 										at the end of the fault to be set for the VuFaultRecord object
	 */
	public void setCardNumberDriverSlotEnd( FullCardNumber cardNumberDriverSlotEnd ) {
		this.cardNumberDriverSlotEnd = cardNumberDriverSlotEnd;
	}

	/**
	 * Returns the identification of the card inserted in the co-driver slot
	 * at the end of the fault of a VuFaultRecord object.
	 * 
	 * @return	the identification of the card inserted in the co-driver slot
	 * 			at the end of the fault of the VuFaultRecord object
	 */
	public FullCardNumber getCardNumberCodriverSlotEnd() {
		return cardNumberCodriverSlotEnd;
	}

	/**
	 * Sets the identification of the card inserted in the co-driver slot
	 * at the end of the fault of a VuFaultRecord object.
	 * 
	 * @param	cardNumberCodriverSlotEnd		the identification of the card inserted in the co-driver slot
	 * 											at the end of the fault to be set for the VuFaultRecord object
	 */
	public void setCardNumberCodriverSlotEnd( FullCardNumber cardNumberCodriverSlotEnd ) {
		this.cardNumberCodriverSlotEnd = cardNumberCodriverSlotEnd;
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		node.addContent( faultType.generateXMLElement( "faultType" ) );
		node.addContent( faultRecordPurpose.generateXMLElement( "faultRecordPurpose" ) );
		node.addContent( faultBeginTime.generateXMLElement( "faultBeginTime" ) );
		node.addContent( faultEndTime.generateXMLElement( "faultEndTime" ) );
		node.addContent( cardNumberDriverSlotBegin.generateXMLElement( "cardNumberDriverSlotBegin" ) );
		node.addContent( cardNumberCodriverSlotBegin.generateXMLElement( "cardNumberCodriverSlotBegin" ) );
		node.addContent( cardNumberDriverSlotEnd.generateXMLElement( "cardNumberDriverSlotEnd" ) );
		node.addContent( cardNumberCodriverSlotEnd.generateXMLElement( "cardNumberCodriverSlotEnd" ) );

		return node;
	}
}
