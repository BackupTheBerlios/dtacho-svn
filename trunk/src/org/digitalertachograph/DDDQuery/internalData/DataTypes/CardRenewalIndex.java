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
 * A card renewal index.
 */
public class CardRenewalIndex extends DataClass {
	/*
	 * CardRenewalIndex ::= IA5String(SIZE(1)), 1 byte
	 * ---
	 * Value assignment: (see Annex Chapter VII)
	 * '0'	first issue
	 * Order for increase: "0..9, A..Z.
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 1;

	private String cardRenewalIndex;

	/**
	 * Constructor for a CardRenewalIndex object
	 */
	public CardRenewalIndex() {
		cardRenewalIndex = new String();
	}

	/**
	 * Constructor for a CardRenewalIndex object
	 * 
	 * @param	value	byte array of a card renewal index that is used when the CardRenewalIndex
	 * 					object is created.
	 */
	public CardRenewalIndex( byte[] value ) {
		cardRenewalIndex = new String( arrayCopy( value, 0, 1 ) );
	}

	/**
	 * Constructor for a CardRenewalIndex object
	 * 
	 * @param	cardRenewalIndex	String that contains a card renewal index that is used
	 * 								when the CardRenewalIndex object is created.
	 */
	public CardRenewalIndex( String cardRenewalIndex ) {
		setCardRenewalIndex( cardRenewalIndex );
	}

	/**
	 * Returns the card renewal index of a CardRenewalIndex object.
	 * 
	 * @return	the card renewal index of the CardRenewalIndex object
	 */
	public String getCardRenewalIndex() {
		return cardRenewalIndex;
	}

	/**
	 * Sets the card renewal index of a CardRenewalIndex object.
	 * 
	 * @param	cardRenewalIndex	the card renewal index to be set
	 * 								for the CardRenewalIndex object
	 */
	public void setCardRenewalIndex( String cardRenewalIndex ) {
		int cardRenewalIndexLength = cardRenewalIndex.length();

		if ( cardRenewalIndexLength > 1 ) {
			cardRenewalIndexLength = 1;
		}

		this.cardRenewalIndex = cardRenewalIndex.substring( 0, cardRenewalIndexLength );
	}

	@Override
	public Element generateXMLElement( String name ) {
		if ( isValidXMLString( cardRenewalIndex ) == false ) {
			return new Element( name );
		}
		else {	
			return new Element( name ).setText( cardRenewalIndex );
		}
	}
}
