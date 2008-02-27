/*
    $Id:CardConsecutiveIndex.java 26 2008-02-25 22:28:27Z deetee $

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
 * A card consecutive index.
 */
public class CardConsecutiveIndex extends DataClass {
	/*
	 * CardConsecutiveIndex ::= IA5String(SIZE(1)), 1 byte
	 * ---
	 * Value assignment: (see Annex Chapter VII)
	 * Order for increase: "0..9, A..Z, a..z"
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 1;

	private String cardConsecutiveIndex;

	/**
	 * Constructor for a CardConsecutiveIndex object
	 */
	public CardConsecutiveIndex() {
		cardConsecutiveIndex = new String();
	}

	/**
	 * Constructor for a CardConsecutiveIndex object
	 * 
	 * @param	value	byte array of a card consecutive index that is used when the CardConsecutiveIndex
	 * 					object is created.
	 */
	public CardConsecutiveIndex( byte[] value ) {
		cardConsecutiveIndex = new String( arrayCopy( value, 0, 1 ) );
	}

	/**
	 * Constructor for a CardConsecutiveIndex object
	 * 
	 * @param	cardConsecutiveIndex	String that contains a card consecutive index that is used
	 * 									when the CardConsecutiveIndex object is created.
	 */
	public CardConsecutiveIndex( String cardConsecutiveIndex ) {
		setCardConsecutiveIndex( cardConsecutiveIndex );
	}

	/**
	 * Returns the card consecutive index of a CardConsecutiveIndex object.
	 * 
	 * @return	the card consecutive index of the CardConsecutiveIndex object
	 */
	public String getCardConsecutiveIndex() {
		return cardConsecutiveIndex;
	}

	/**
	 * Sets the card consecutive index of a CardConsecutiveIndex object.
	 * 
	 * @param	cardConsecutiveIndex	the card consecutive index to be set
	 * 									for the CardConsecutiveIndex object
	 */
	public void setCardConsecutiveIndex( String cardConsecutiveIndex ) {
		int cardConsecutiveIndexLength = cardConsecutiveIndex.length();

		if ( cardConsecutiveIndexLength > 1 ) {
			cardConsecutiveIndexLength = 1;
		}

		this.cardConsecutiveIndex = cardConsecutiveIndex.substring( 0, cardConsecutiveIndexLength );
	}

	@Override
	public Element generateXMLElement( String name ) {
		if ( isValidXMLString( cardConsecutiveIndex ) == false ) {
			return new Element( name );
		}
		else {
			return new Element( name ).setText( cardConsecutiveIndex );
		}
	}
}
