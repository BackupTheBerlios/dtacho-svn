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
 * Information, stored in a vehicle unit, related to a time adjustment performed
 * outside the frame of a regular calibration.
 */
public class VuTimeAdjustmentRecord extends DataClass {
	/*
	 * VuTimeAdjustmentRecord ::= SEQUENCE {
	 * 	oldTimeValue TimeReal, 4 bytes
	 * 	newTimeValue TimeReal, 4 bytes
	 * 	workshopName Name, 36 bytes
	 * 	workshopAddress Address, 36 bytes
	 * 	workshopCardNumber FullCardNumber, 18 bytes
	 * }
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 98;

	private TimeReal oldTimeValue;
	private TimeReal newTimeValue;
	private Name workshopName;
	private Address workshopAddress;
	private FullCardNumber workshopCardNumber;

	private DebugLogger debugLogger;


	/**
	 * Constructor for a VuTimeAdjustmentRecord object
	 */
	public VuTimeAdjustmentRecord() {
		oldTimeValue = new TimeReal();
		newTimeValue = new TimeReal();
		workshopName = new Name();
		workshopAddress = new Address();
		workshopCardNumber = new FullCardNumber();
	}

	/**
	 * Constructor for a VuTimeAdjustmentRecord object
	 * 
	 * @param	value	byte array of a VuTimeAdjustmentRecord structure
	 * 					whose data is used when the VuTimeAdjustmentRecord
	 * 					object is created.
	 */
	public VuTimeAdjustmentRecord( byte[] value ) {
		debugLogger = new DebugLogger();

		debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Old time value:" );
		oldTimeValue = new TimeReal( arrayCopy( value, 0, 4 ) );

		debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] New time value:" );
		newTimeValue = new TimeReal( arrayCopy( value, 4, 4 ) );

		workshopName = new Name( arrayCopy( value, 8, 36 ) );
		debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Workshop name: %s\n", workshopName.getNameString() );

		workshopAddress = new Address( arrayCopy( value, 44, 36 ) );
		debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Workshop address: %s\n", workshopAddress.getAddressString() );

		workshopCardNumber = new FullCardNumber( arrayCopy( value, 80, 18 ) );
		debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Type of card: %02x - %s\n", workshopCardNumber.getCardType().getEquipmentType(), workshopCardNumber.getCardType().toString() );
		debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Card issuing member state: %s\n", workshopCardNumber.getCardIssuingMemberState().toString() );
		if ( workshopCardNumber.getCardType().getEquipmentType() == EquipmentType.WORKSHOP_CARD ) {
			debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Card number: %s%s%s%s\n", workshopCardNumber.getCardNumber().getOwnerIdentification(), workshopCardNumber.getCardNumber().getCardConsecutiveIndex().getCardConsecutiveIndex(), workshopCardNumber.getCardNumber().getCardReplacementIndex().getCardReplacementIndex(), workshopCardNumber.getCardNumber().getCardRenewalIndex().getCardRenewalIndex() );
		}
		else {
			debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Wrong card type. Expected a workshop card." );
		}
	}

	/**
	 * Returns the old value of date and time
	 * of a VuTimeAdjustmentRecord object.
	 * 
	 * @return	the old value of date and time of the VuTimeAdjustmentRecord object
	 */
	public TimeReal getOldTimeValue() {
		return oldTimeValue;
	}

	/**
	 * Sets the old value of date and time
	 * of a VuTimeAdjustmentRecord object.
	 * 
	 * @param	oldTimeValue		the old value of date and time
	 * 								to be set for the VuTimeAdjustmentRecord object
	 */
	public void setOldTimeValue( TimeReal oldTimeValue ) {
		this.oldTimeValue = oldTimeValue;
	}

	/**
	 * Returns the new value of date and time
	 * of a VuTimeAdjustmentRecord object.
	 * 
	 * @return	the new value of date and time of the VuTimeAdjustmentRecord object
	 */
	public TimeReal getNewTimeValue() {
		return newTimeValue;
	}

	/**
	 * Sets the new value of date and time
	 * of a VuTimeAdjustmentRecord object.
	 * 
	 * @param	newTimeValue		the new value of date and time
	 * 								to be set for the VuTimeAdjustmentRecord object
	 */
	public void setNewTimeValue( TimeReal newTimeValue ) {
		this.newTimeValue = newTimeValue;
	}

	/**
	 * Returns the name of the workshop of the card holder
	 * of a VuTimeAdjustmentRecord object.
	 * 
	 * @return	the name of the workshop of the card holder
	 * 			of the VuTimeAdjustmentRecord object
	 */
	public Name getWorkshopName() {
		return workshopName;
	}

	/**
	 * Sets the name of the workshop of the card holder
	 * of a VuTimeAdjustmentRecord object.
	 * 
	 * @param	workshopName		the name of the workshop of the card holder
	 * 								to be set for the VuTimeAdjustmentRecord object
	 */
	public void setWorkshopName( Name workshopName ) {
		this.workshopName = workshopName;
	}

	/**
	 * Returns the address of the workshop of the card holder
	 * of a VuTimeAdjustmentRecord object.
	 * 
	 * @return	the address of the workshop of the card holder
	 * 			of the VuTimeAdjustmentRecord object
	 */
	public Address getWorkshopAddress() {
		return workshopAddress;
	}

	/**
	 * Sets the address of the workshop of the card holder
	 * of a VuTimeAdjustmentRecord object.
	 * 
	 * @param	workshopAddress		the address of the workshop of the card holder
	 * 								to be set for the VuTimeAdjustmentRecord object
	 */
	public void setWorkshopAddress( Address workshopAddress ) {
		this.workshopAddress = workshopAddress;
	}

	/**
	 * Returns the identification of the workshop card used to perform the time adjustment
	 * of a VuTimeAdjustmentRecord object.
	 * 
	 * @return	the identification of the workshop card used to perform the time adjustment
	 * 			of the VuTimeAdjustmentRecord object
	 */
	public FullCardNumber getWorkshopCardNumber() {
		return workshopCardNumber;
	}

	/**
	 * Sets the identification of the workshop card used to perform the time adjustment
	 * of a VuTimeAdjustmentRecord object.
	 * 
	 * @param	workshopCardNumber		the identification of the workshop card used to perform the time adjustment
	 * 									to be set for the VuTimeAdjustmentRecord object
	 */
	public void setWorkshopCardNumber( FullCardNumber workshopCardNumber ) {
		this.workshopCardNumber = workshopCardNumber;
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		node.addContent( oldTimeValue.generateXMLElement( "oldTimeValue" ) );
		node.addContent( newTimeValue.generateXMLElement( "newTimeValue" ) );
		node.addContent( workshopName.generateXMLElement( "workshopName" ) );
		node.addContent( workshopAddress.generateXMLElement( "workshopAddress" ) );
		node.addContent( workshopCardNumber.generateXMLElement( "workshopCardNumber" ) );

		return node;
	}
}
