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

public class ControlCardHolderIdentification extends DataClass {
	/*
	 * ControlCardHolderIdentification ::= SEQUENCE {
	 * 	controlBodyName Name, 36 bytes
	 * 	controlBodyAddress Address, 36 bytes
	 * 	cardHolderName HolderName, 72 bytes
	 * 	cardHolderPreferredLanguage Language, 2 bytes
	 * }
	 */
	
	private Name controlBodyName;
	private Address controlBodyAddress;
	private HolderName cardHolderName;
	private Language cardHolderPreferredLanguage;
	

	/**
	 * Constructor for a ControlCardHolderIdentification object
	 * 
	 * @param	value	byte array of a ControlCardHolderIdentification structure
	 * 					whose data is used when the ControlCardHolderIdentification
	 * 					object is created.
	 */
	public ControlCardHolderIdentification(byte[] value){
		controlBodyName = new Name( arrayCopy(value, 0, 36));
		controlBodyAddress = new Address( arrayCopy(value, 36, 36));
		cardHolderName = new HolderName( arrayCopy(value, 72, 72));
		cardHolderPreferredLanguage = new Language(arrayCopy(value, 144, 2));
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

	public Address getControlBodyAddress() {
		return controlBodyAddress;
	}

	public void setControlBodyAddress(Address controlBodyAddress) {
		this.controlBodyAddress = controlBodyAddress;
	}

	public Name getControlBodyName() {
		return controlBodyName;
	}

	public void setControlBodyName(Name controlBodyName) {
		this.controlBodyName = controlBodyName;
	}
	
	@Override
	public Element generateXMLElement(String name) {
		Element node = new Element(name);
		node.addContent( controlBodyName.generateXMLElement("controlBodyName"));
		node.addContent( controlBodyAddress.generateXMLElement("controlBodyAddress"));
		node.addContent( cardHolderName.generateXMLElement("cardHolderName"));
		node.addContent( cardHolderPreferredLanguage.generateXMLElement("cardHolderPreferredLanguage"));
		return node;
	}
}
