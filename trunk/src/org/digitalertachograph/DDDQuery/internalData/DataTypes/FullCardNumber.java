/*   Copyright (C) 2007, Martin Barth, Gerald Schnabel

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

public class FullCardNumber extends DataClass {
	
	/*
	 * FullCardNumber ::= SEQUENCE {
	 * 	cardType EquipmentType, 1 byte
	 * 	cardIssuingMemberState NationNumeric, 1 byte
	 * 	cardNumber CardNumber, 16 bytes
	 * }
	 */

	private EquipmentType cardType;
	private NationNumeric cardIssuingMemberState;
	private CardNumber cardNumber;
	public FullCardNumber(byte[] value){
		cardType = new EquipmentType(value[0]);
		cardIssuingMemberState = new NationNumeric(value[1]);
		// TODO not sure bout this solution.
		cardNumber = new CardNumber( arrayCopy(value, 2, 16), cardType.getEquipmentType() );
	}
	public NationNumeric getCardIssuingMemberState() {
		return cardIssuingMemberState;
	}
	public void setCardIssuingMemberState(NationNumeric cardIssuingMemberState) {
		this.cardIssuingMemberState = cardIssuingMemberState;
	}
	public CardNumber getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(CardNumber cardNumber) {
		this.cardNumber = cardNumber;
	}
	public EquipmentType getCardType() {
		return cardType;
	}
	public void setCardType(EquipmentType cardType) {
		this.cardType = cardType;
	}
	@Override
	public Element generateXMLElement(String name) {
		Element node = new Element(name);
		node.addContent( cardType.generateXMLElement("cardType"));
		node.addContent( cardIssuingMemberState.generateXMLElement("cardIssuingMemberState"));
		node.addContent( cardNumber.generateXMLElement("cardNumber"));
		return node;
	}
}
