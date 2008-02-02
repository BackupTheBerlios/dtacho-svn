/*   Copyright (C) 2008, Gerald Schnabel

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
 * Type approval number of the card.
 */
public class CardApprovalNumber extends DataClass {
	/*
	 * CardApprovalNumber ::= IA5String(SIZE(8)), 8 bytes
	 */

	private String cardApprovalNumber;

	
	/**
	 * Constructor for a CardApprovalNumber object
	 */
	public CardApprovalNumber() {
		cardApprovalNumber = new String();
	}

	/**
	 * Constructor for a CardApprovalNumber object
	 * 
	 * @param	value	byte array of a type approval number that is used when the CardApprovalNumber
	 * 					object is created.
	 */
	public CardApprovalNumber( byte[] value ) {
		cardApprovalNumber = new String( arrayCopy( value, 0, 8 ) );
	}

	/**
	 * Constructor for a CardApprovalNumber object
	 * 
	 * @param	cardApprovalNumber	String that contains a type approval number that is used
	 * 								when the CardApprovalNumber object is created.
	 */
	public CardApprovalNumber( String cardApprovalNumber ) {
		setCardApprovalNumber( cardApprovalNumber );
	}

	/**
	 * Returns the type approval number of the card of a CardApprovalNumber object.
	 * 
	 * @return	the type approval number of the card of the CardApprovalNumber object
	 */
	public String getCardApprovalNumber() {
		return cardApprovalNumber;
	}

	/**
	 * Sets the type approval number of the card of a CardApprovalNumber object.
	 * 
	 * @param	cardApprovalNumber	the type approval number of the card to be set
	 * 								for the CardApprovalNumber object
	 */
	public void setCardApprovalNumber( String cardApprovalNumber ) {
		int cardApprovalNumberLength = cardApprovalNumber.length(); 

		if ( cardApprovalNumberLength > 8 ) {
			cardApprovalNumberLength = 8;
		}

		this.cardApprovalNumber = cardApprovalNumber.substring( 0, cardApprovalNumberLength );
	}

	@Override
	public Element generateXMLElement( String name ) {
		return new Element( name ).setText( cardApprovalNumber );
	}
}
