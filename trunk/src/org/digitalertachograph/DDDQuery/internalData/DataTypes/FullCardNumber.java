/*
    $Id:FullCardNumber.java 26 2008-02-25 22:28:27Z deetee $

    Copyright (C) 2007-2008, Martin Barth, Gerald Schnabel

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
 * Code fully identifying a tachograph card.
 */
public class FullCardNumber extends DataClass {
	/*
	 * FullCardNumber ::= SEQUENCE {
	 * 	cardType EquipmentType, 1 byte
	 * 	cardIssuingMemberState NationNumeric, 1 byte
	 * 	cardNumber CardNumber, 16 bytes
	 * }
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 18;

	private EquipmentType cardType;
	private NationNumeric cardIssuingMemberState;
	private CardNumber cardNumber;


	/**
	 * Constructor for a FullCardNumber object
	 */
	public FullCardNumber() {
		cardType = new EquipmentType();
		cardIssuingMemberState = new NationNumeric();
		cardNumber = new CardNumber( null, EquipmentType.DRIVER_CARD );
	}

	/**
	 * Constructor for a FullCardNumber object
	 * 
	 * @param	value	byte array of a FullCardNumber structure
	 * 					whose data is used when the FullCardNumber
	 * 					object is created.
	 */
	public FullCardNumber( byte[] value ) {
		cardType = new EquipmentType( value[ 0 ] );
		cardIssuingMemberState = new NationNumeric( value[ 1 ] );
		cardNumber = new CardNumber( arrayCopy( value, 2, 16 ), cardType.getEquipmentType() );
	}

	/**
	 * Returns the type of the tachograph card of a FullCardNumber object.
	 * 
	 * @return	the type of the tachograph card of the FullCardNumber object
	 */
	public EquipmentType getCardType() {
		return cardType;
	}

	/**
	 * Sets the type of the tachograph card of a FullCardNumber object.
	 * 
	 * @param	cardType	the type of the tachograph card	to be set for
	 * 						the FullCardNumber object
	 */
	public void setCardType( EquipmentType cardType ) {
		this.cardType = cardType;
	}

	/**
	 * Returns the code of the Member State having issued the card of a FullCardNumber object.
	 * 
	 * @return	the code of the Member State having issued the card of the FullCardNumber object
	 */
	public NationNumeric getCardIssuingMemberState() {
		return cardIssuingMemberState;
	}

	/**
	 * Sets the code of the Member State having issued the card of a FullCardNumber object.
	 * 
	 * @param	cardIssuingMemberState		the code of the Member State having issued the card to be set for
	 * 										the FullCardNumber object
	 */
	public void setCardIssuingMemberState( NationNumeric cardIssuingMemberState ) {
		this.cardIssuingMemberState = cardIssuingMemberState;
	}

	/**
	 * Returns the card number of a FullCardNumber object.
	 * 
	 * @return	the card number of the FullCardNumber object
	 */
	public CardNumber getCardNumber() {
		return cardNumber;
	}

	/**
	 * Sets the card number of a FullCardNumber object.
	 * 
	 * @param	cardNumber		the card number to be set for the FullCardNumber object
	 */
	public void setCardNumber( CardNumber cardNumber ) {
		this.cardNumber = cardNumber;
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		node.addContent( cardType.generateXMLElement( "cardType" ) );
		node.addContent( cardIssuingMemberState.generateXMLElement( "cardIssuingMemberState" ) );
		node.addContent( cardNumber.generateXMLElement( "cardNumber" ) );

		return node;
	}
}
