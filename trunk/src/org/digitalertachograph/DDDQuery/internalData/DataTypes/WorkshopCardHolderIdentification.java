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

public class WorkshopCardHolderIdentification extends DataClass {
	/*
	 * WorkshopCardHolderIdentification ::= SEQUENCE {
	 * 	workshopName Name, 36 bytes
	 * 	workshopAddress Address, 36 bytes
	 * 	cardHolderName HolderName, 72 bytes
	 * 	cardHolderPreferredLanguage Language 2 bytes
	 * }
	 */

	private Name workshopName;
	private Address workshopAddress;
	private HolderName cardHolderName;
	private Language cardHolderPreferredLanguage;
	
	
	/**
	 * Constructor for a WorkshopCardHolderIdentification object
	 * 
	 * @param	value	byte array of a WorkshopCardHolderIdentification structure
	 * 					whose data is used when the WorkshopCardHolderIdentification
	 * 					object is created.
	 */

	public WorkshopCardHolderIdentification(byte[] value){
		workshopName = new Name( arrayCopy(value, 0, 36));
		workshopAddress = new Address( arrayCopy(value, 36, 36));
		cardHolderName = new HolderName( arrayCopy(value, 72, 72));
		cardHolderPreferredLanguage = new Language( arrayCopy(value, 142, 2));
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

	public Address getWorkshopAddress() {
		return workshopAddress;
	}

	public void setWorkshopAddress(Address workshopAddress) {
		this.workshopAddress = workshopAddress;
	}

	public Name getWorkshopName() {
		return workshopName;
	}

	public void setWorkshopName(Name workshopName) {
		this.workshopName = workshopName;
	}

	@Override
	public Element generateXMLElement(String name) {
		Element node = new Element(name);
		node.addContent( workshopName.generateXMLElement("workshopName") );
		node.addContent( workshopAddress.generateXMLElement("workshopAddress"));
		node.addContent( cardHolderName.generateXMLElement("cardHolderName"));
		node.addContent( cardHolderPreferredLanguage.generateXMLElement("cardHolderPreferredLanguage"));
		return node;
	}
}
