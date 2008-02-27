/*
    $Id:CardReplacementIndex.java 26 2008-02-25 22:28:27Z deetee $

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
 * A card replacement index.
 */
public class CardReplacementIndex extends DataClass {
	/*
	 * CardReplacementIndex ::= IA5String(SIZE(1)), 1 byte
	 * ---
	 * Value assignment: (see Annex Chapter VII)
	 * '0'	original card
	 * Order for increase: "0..9, A..Z.
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 1;

	private String cardReplacementIndex;

	/**
	 * Constructor for a CardReplacementIndex object
	 */
	public CardReplacementIndex() {
		cardReplacementIndex = new String();
	}

	/**
	 * Constructor for a CardReplacementIndex object
	 * 
	 * @param	value	byte array of a card replacement index that is used when the CardReplacementIndex
	 * 					object is created.
	 */
	public CardReplacementIndex( byte[] value ) {
		cardReplacementIndex = new String( arrayCopy( value, 0, 1 ) );
	}

	/**
	 * Constructor for a CardReplacementIndex object
	 * 
	 * @param	cardReplacementIndex	String that contains a card replacement index that is used
	 * 									when the CardReplacementIndex object is created.
	 */
	public CardReplacementIndex( String cardReplacementIndex ) {
		setCardReplacementIndex( cardReplacementIndex );
	}

	/**
	 * Returns the card replacement index of a CardReplacementIndex object.
	 * 
	 * @return	the card replacement index of the CardReplacementIndex object
	 */
	public String getCardReplacementIndex() {
		return cardReplacementIndex;
	}

	/**
	 * Sets the card replacement index of a CardReplacementIndex object.
	 * 
	 * @param	cardReplacementIndex	the card replacement index to be set
	 * 									for the CardReplacementIndex object
	 */
	public void setCardReplacementIndex( String cardReplacementIndex ) {
		int cardReplacementIndexLength = cardReplacementIndex.length();

		if ( cardReplacementIndexLength > 1 ) {
			cardReplacementIndexLength = 1;
		}

		this.cardReplacementIndex = cardReplacementIndex.substring( 0, cardReplacementIndexLength );
	}

	@Override
	public Element generateXMLElement( String name ) {
		if ( isValidXMLString( cardReplacementIndex ) == false ) {
			return new Element( name );
		}
		else {
			return new Element( name ).setText( cardReplacementIndex );
		}
	}
}
