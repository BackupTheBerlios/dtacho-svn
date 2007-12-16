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

import org.digitalertachograph.DDDQuery.internalData.*;
import org.jdom.Element;

public class CardIdentification extends DataClass {
	/*
	 * CardIdentification ::= SEQUENCE {
	 * 	cardIssuingMemberState NationNumeric, 1 byte
	 * 	cardNumber CardNumber, 16 bytes
	 * 	cardIssuingAuthorityName Name, 36 bytes
	 * 	cardIssueDate TimeReal, 4 bytes
	 * 	cardValidityBegin TimeReal, 4 bytes
	 * 	cardExpiryDate TimeReal, 4 bytes
	 * }
	 */
	
	private NationNumeric cardIssuingMemberState;
	private CardNumber cardNumber; // size 16bytes 
	private Name cardIssuingAuthorityName;
	private TimeReal cardIssueDate;
	private TimeReal cardValidityBegin;
	private TimeReal cardExpiryDate;
	
	
	/**
	 * Constructor for a CardIdentification object
	 * 
	 * @param	value	byte array of a CardIdentification structure
	 * 					whose data is used when the CardIdentification
	 * 					object is created.
	 */
	public CardIdentification(byte[] value, int cardType){
		cardIssuingMemberState = new NationNumeric(value[0]);
		cardNumber = new CardNumber( arrayCopy(value, 1, 16), cardType );
		cardIssuingAuthorityName = new Name( arrayCopy(value, 17, 36));
		cardIssueDate = new TimeReal( arrayCopy(value, 53, 4));
		cardValidityBegin = new TimeReal( arrayCopy(value, 57, 4));
		cardExpiryDate = new TimeReal( arrayCopy(value, 61, 4));
	}
	
	public TimeReal getCardExpiryDate() {
		return cardExpiryDate;
	}
	public void setCardExpiryDate(TimeReal cardExpiryDate) {
		this.cardExpiryDate = cardExpiryDate;
	}
	public TimeReal getCardIssueDate() {
		return cardIssueDate;
	}
	public void setCardIssueDate(TimeReal cardIssueDate) {
		this.cardIssueDate = cardIssueDate;
	}
	public Name getCardIssuingAuthorityName() {
		return cardIssuingAuthorityName;
	}
	public void setCardIssuingAuthorityName(Name cardIssuingAuthorityName) {
		this.cardIssuingAuthorityName = cardIssuingAuthorityName;
	}
	public NationNumeric getCardIssuingMemberState() {
		return cardIssuingMemberState;
	}
	public void setCardIssuingMemberState(NationNumeric cardIssuingMemberState) {
		this.cardIssuingMemberState = cardIssuingMemberState;
	}
	public void setCardIssuingMemberState(short cardIssuingMemberState) {
		this.cardIssuingMemberState.setNationNumeric( cardIssuingMemberState);
	}
	public CardNumber getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(CardNumber cardNumber) {
		this.cardNumber = cardNumber;
	}
	public TimeReal getCardValidityBegin() {
		return cardValidityBegin;
	}
	public void setCardValidityBegin(TimeReal cardValidityBegin) {
		this.cardValidityBegin = cardValidityBegin;
	}
	
	
	@Override
	public Element generateXMLElement(String name) {
		Element node = new Element(name);
		node.addContent( cardIssuingMemberState.generateXMLElement("cardIssuingMemberState"));
		node.addContent( cardNumber.generateXMLElement("cardNumber"));
		node.addContent( cardIssuingAuthorityName.generateXMLElement("cardIssuingAuthorityName"));
		node.addContent( cardIssueDate.generateXMLElement("cardIssueDate"));
		node.addContent( cardValidityBegin.generateXMLElement("cardValidityBegin"));
		node.addContent( cardExpiryDate.generateXMLElement("cardExpiryDate"));
		
		return node;
	}
}

