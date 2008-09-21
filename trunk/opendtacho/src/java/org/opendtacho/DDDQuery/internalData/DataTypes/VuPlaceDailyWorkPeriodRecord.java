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
 * Information, stored in a vehicle unit, related to a place where a driver
 * begins or ends a daily work period..
 */
public class VuPlaceDailyWorkPeriodRecord extends DataClass {
	/*
	 * VuPlaceDailyWorkPeriodRecord ::= SEQUENCE {
	 * 	fullCardNumber FullCardNumber, 18 bytes
	 * 	placeRecord PlaceRecord, 10 bytes
	 * }
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 28;

	private FullCardNumber fullCardNumber; 
	private PlaceRecord placeRecord;

	private DebugLogger debugLogger;


	/**
	 * Constructor for a VuPlaceDailyWorkPeriodRecord object
	 */
	public VuPlaceDailyWorkPeriodRecord() {
		fullCardNumber = new FullCardNumber(); 
		placeRecord = new PlaceRecord();
	}

	/**
	 * Constructor for a VuPlaceDailyWorkPeriodRecord object
	 * 
	 * @param	value	byte array of a VuPlaceDailyWorkPeriodRecord structure
	 * 					whose data is used when the VuPlaceDailyWorkPeriodRecord
	 * 					object is created.
	 */
	public VuPlaceDailyWorkPeriodRecord( byte[] value ) {
		debugLogger = new DebugLogger();
		debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] ------------------------------------------------------------" );

		fullCardNumber = new FullCardNumber( arrayCopy( value, 0, 18 ) ); 
		debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Type of card: %02x - %s\n", fullCardNumber.getCardType().getEquipmentType(), fullCardNumber.getCardType().toString() );
		debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Card issuing member state: %s\n", fullCardNumber.getCardIssuingMemberState().toString() );
		if ( fullCardNumber.getCardType().getEquipmentType() == EquipmentType.DRIVER_CARD ) {
			debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Card number: %s%s%s\n", fullCardNumber.getCardNumber().getDriverIdentification(), fullCardNumber.getCardNumber().getCardReplacementIndex().getCardReplacementIndex(), fullCardNumber.getCardNumber().getCardRenewalIndex().getCardRenewalIndex() );
		}
		else {
			debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Card number: %s%s%s%s\n", fullCardNumber.getCardNumber().getOwnerIdentification(), fullCardNumber.getCardNumber().getCardConsecutiveIndex().getCardConsecutiveIndex(), fullCardNumber.getCardNumber().getCardReplacementIndex().getCardReplacementIndex(), fullCardNumber.getCardNumber().getCardRenewalIndex().getCardRenewalIndex() );
		}

		placeRecord = new PlaceRecord( arrayCopy( value, 18, 10 ) );
	}

	/**
	 * Returns the driver's card type, card issuing Member State and card number
	 * of a VuPlaceDailyWorkPeriodRecord object.
	 * 
	 * @return	the driver's card type, card issuing Member State and card number
	 * 			of the VuPlaceDailyWorkPeriodRecord object
	 */
	public FullCardNumber getFullCardNumber() {
		return fullCardNumber;
	}

	/**
	 * Sets the driver's card type, card issuing Member State and card number
	 * of a VuPlaceDailyWorkPeriodRecord object.
	 * 
	 * @param	fullCardNumber		the driver's card type, card issuing Member State and card number
	 * 								of a VuPlaceDailyWorkPeriodRecord object
	 */
	public void setFullCardNumber( FullCardNumber fullCardNumber ) {
		this.fullCardNumber = fullCardNumber;
	}

	/**
	 * Returns the information related to the place entered
	 * of a VuPlaceDailyWorkPeriodRecord object.
	 * 
	 * @return	the information related to the place entered
	 * 			of the VuPlaceDailyWorkPeriodRecord object
	 */
	public PlaceRecord getPlaceRecord() {
		return placeRecord;
	}

	/**
	 * Sets the information related to the place entered
	 * of a VuPlaceDailyWorkPeriodRecord object.
	 * 
	 * @param	placeRecord		the information related to the place entered
	 * 							of a VuPlaceDailyWorkPeriodRecord object
	 */
	public void setPlaceRecord( PlaceRecord placeRecord ) {
		this.placeRecord = placeRecord;
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		node.addContent( fullCardNumber.generateXMLElement( "fullCardNumber" ) );
		node.addContent( placeRecord.generateXMLElement( "placeRecord" ) );

		return node;
	}
}
