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
import org.opendtacho.DDDQuery.internalData.DataClass;

/**
 * Code indicating the type of cards inserted in the two slots of the vehicle unit.
 */
public class CardSlotsStatus extends DataClass {
	/*
	 * CardSlotsStatus ::= OCTET STRING(SIZE(1)), 1 byte
	 * ---
	 * Value assignment:
	 * octet aligned: 'ccccdddd'B
	 * 'cccc'B	Identification of the type of card inserted in the co-driver slot
	 * 'dddd'B	Identification of the type of card inserted in the driver slot
	 * with the following identification codes:
	 * '0000'B	no card is inserted
	 * '0001'B	a driver card is inserted
	 * '0010'B	a workshop card is inserted
	 * '0011'B	a control card is inserted
	 * '0100'B	a company card is inserted
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 1;

	/**
	 * NO_CARD_INSERTED: no card is inserted
	 */
	public static final byte NO_CARD_INSERTED = 0x0;

	/**
	 * DRIVER_INSERTED: driver card is inserted
	 */
	public static final byte DRIVER_CARD_INSERTED = 0x1;

	/**
	 * WORKSHOP_CARD_INSERTED: workshop card is inserted
	 */
	public static final byte WORKSHOP_CARD_INSERTED = 0x2;

	/**
	 * CONTROL_CARD_INSERTED: control card is inserted
	 */
	public static final byte CONTROL_CARD_INSERTED = 0x3;

	/**
	 * COMPANY_CARD_INSERTED: company card is inserted
	 */
	public static final byte COMPANY_CARD_INSERTED = 0x4;

	private byte cardSlotsStatus;


	/**
	 * Constructor for a CardSlotsStatus object
	 */
	public CardSlotsStatus() {
		cardSlotsStatus = 0;
	}

	/**
	 * Constructor for a CardSlotsStatus object
	 * 
	 * @param	value	byte array of a CardSlotsStatus structure
	 * 					whose data is used when the CardSlotsStatus
	 * 					object is created.
	 */
	public CardSlotsStatus( byte[] value ) {
		cardSlotsStatus = value[ 0 ];
	}

	/**
	 * Constructor for a CardSlotsStatus object
	 * 
	 * @param	cardSlotsStatus		byte whose data is used when the CardsSlotStatus
	 * 								object is created.
	 */
	public CardSlotsStatus( byte cardSlotsStatus ) {
		this.cardSlotsStatus = cardSlotsStatus;
	}

	/**
	 * Returns the card slot status of a CardSlotsStatus object.
	 * 
	 * @return	the card slot status of the CardSlotsStatus object
	 */
	public byte getCardSlotsStatus() {
		return cardSlotsStatus;
	}

	/**
	 * Sets the card slot status of a CardSlotsStatus object.
	 * 
	 * @param	cardSlotsStatus		the card slot status to be set
	 * 								for the CardSlotsStatus object
	 */
	public void setCardSlotsStatus( byte cardSlotsStatus ) {
		this.cardSlotsStatus = cardSlotsStatus;
	}

	/**
	 * Returns the co-driver card slot status of a CardSlotsStatus object.
	 * 
	 * @return	the co-driver card slot status of the CardSlotsStatus object
	 */
	public byte getCoDriverCardSlotsStatus() {
		return (byte)( ( cardSlotsStatus >> 4 ) & 0xf );
	}

	/**
	 * Sets the co-driver card slot status of a CardSlotsStatus object.
	 * 
	 * @param	cardSlotsStatus		the co-driver card slot status to be set
	 * 								for the CardSlotsStatus object
	 */
	public void setCoDriverCardSlotsStatus( byte cardSlotsStatus ) {
		this.cardSlotsStatus = (byte)( ( this.cardSlotsStatus & 0xf ) | ( ( cardSlotsStatus & 0xf ) << 4 ) );
	}

	/**
	 * Returns the driver card slot status of a CardSlotsStatus object.
	 * 
	 * @return	the driver card slot status of the CardSlotsStatus object
	 */
	public byte getDriverCardSlotsStatus() {
		return (byte)( cardSlotsStatus & 0xf );
	}

	/**
	 * Sets the driver card slot status of a CardSlotsStatus object.
	 * 
	 * @param	cardSlotsStatus		the driver card slot status to be set
	 * 								for the CardSlotsStatus object
	 */
	public void setDriverCardSlotsStatus( byte cardSlotsStatus ) {
		this.cardSlotsStatus = (byte)( ( (byte)this.cardSlotsStatus & 0xf0 ) | (byte)( cardSlotsStatus & 0xf ) );
	}

	@Override
	public Element generateXMLElement( String name ) {
		return new Element( name ).setText( convertIntoHexString( cardSlotsStatus ) );
	}
}
