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

public class DriverCardHolderIdentification extends DataClass {
	/*
	 * DriverCardHolderIdentification ::= SEQUENCE {
	 * 	cardHolderName HolderName, 72 bytes
	 * 	cardHolderBirthDate Datef, 4 bytes
	 * 	cardHolderPreferredLanguage Language, 2 bytes
	 * }
	 */

	private HolderName cardHolderName;
	private Datef cardHolderBirthDate;
	private Language cardHolderPreferredLanguage;
	

	/**
	 * Constructor for a DriverCardHolderIdentification object
	 * 
	 * @param	value	byte array of a DriverCardHolderIdentification structure
	 * 					whose data is used when the DriverCardHolderIdentification
	 * 					object is created.
	 */
	public DriverCardHolderIdentification(byte[] value){
		cardHolderName = new HolderName( arrayCopy(value, 0, 72));
		cardHolderBirthDate = new Datef( arrayCopy(value, 72, 4));
		cardHolderPreferredLanguage = new Language( arrayCopy(value, 76, 2));
	}
	
	public Datef getCardHolderBirthDate() {
		return cardHolderBirthDate;
	}

	public void setCardHolderBirthDate(Datef cardHolderBirthDate) {
		this.cardHolderBirthDate = cardHolderBirthDate;
	}

	public HolderName getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(HolderName cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public Language getCardHolderPreferredLanguage() {
		return cardHolderPreferredLanguage;
	}

	public void setCardHolderPreferredLanguage(Language cardHolderPreferredLanguage) {
		this.cardHolderPreferredLanguage = cardHolderPreferredLanguage;
	}
	
	@Override
	public Element generateXMLElement(String name) {
		Element node = new Element(name);
		node.addContent( cardHolderName.generateXMLElement("cardHolderName"));
		node.addContent( cardHolderBirthDate.generateXMLElement("cardHolderBirthDate"));
		node.addContent( cardHolderPreferredLanguage.generateXMLElement("cardHolderPreferredLanguage"));
		return node;
	}
}
