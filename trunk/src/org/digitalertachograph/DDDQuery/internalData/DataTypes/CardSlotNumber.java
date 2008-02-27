/*
    $Id:CardSlotNumber.java 26 2008-02-25 22:28:27Z deetee $

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
 * Code to distinguish between two slots of a vehicle unit.
 */
public class CardSlotNumber extends DataClass {
	/*
	 * CardSlotNumber ::= INTEGER {
	 * 	driverSlot (0),
	 * 	co-driverSlot (1)
	 * }, 1 byte
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 1;

	private short cardSlotNumber;


	/**
	 * Card slot number: DRIVERSLOT
	 */
	public final static short DRIVER_SLOT = 0;
	/**
	 * Equipment type: CO_DRIVER_SLOT
	 */
	public final static short CO_DRIVER_SLOT = 1;

	/**
	 * Constructor for a CardSlotNumber object
	 */
	public CardSlotNumber() {
		cardSlotNumber = DRIVER_SLOT;
	}

	/**
	 * Constructor for a CardSlotNumber object
	 * 
	 * @param	value	byte array of a CardSlotNumber structure
	 * 					whose data is used when the CardSlotNumber
	 * 					object is created.
	 */
	public CardSlotNumber( byte[] value ) {
		cardSlotNumber = convertIntoUnsigned1ByteInt( value[ 0 ] );
	}

	/**
	 * Constructor for a CardSlotNumber object
	 * 
	 * @param	value	byte that indicates the slot of a vehicle unit
	 */
	public CardSlotNumber( byte value ) {
		cardSlotNumber = convertIntoUnsigned1ByteInt( value );
	}

	/**
	 * Returns the code to distinguish between two slots of a vehicle unit
	 * of a CardSlotNumber object.
	 * 
	 * @return	the code to distinguish between two slots of a vehicle unit
	 * 			of the CardSlotNumber object
	 */
	public short getCardSlotNumber() {
		return cardSlotNumber;
	}

	/**
	 * Sets the code to distinguish between two slots of a vehicle unit
	 * of a CardSlotNumber object.
	 * 
	 * @param	cardSlotNumber	the code to distinguish between two slots of a vehicle unit
	 * 							to be set for the CardSlotNumber object
	 */
	public void setCardSlotNumber( short cardSlotNumber ) {
		this.cardSlotNumber = cardSlotNumber;
	}

	@Override
	public Element generateXMLElement( String name ) {
		return new Element( name ).setText( Short.toString( cardSlotNumber ) );
	}
}
