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

public class CompanyCardHolderIdentification extends DataClass {
	/*
	 * CompanyCardHolderIdentification ::= SEQUENCE {
	 * 	companyName Name, 36 bytes
	 * 	companyAddress Address, 36 bytes
	 * 	cardHolderPreferredLanguage Language, 2 bytes
	 * }
	 */
	private Name companyName;
	private Address companyAddress;
	private Language cardHolderPreferredLanguage;

	
	/**
	 * Constructor for a CompanyCardHolderIdentification object
	 */
	public CompanyCardHolderIdentification() {
		companyName = new Name();
		companyAddress = new Address();
		cardHolderPreferredLanguage = new Language();
	}

	/**
	 * Constructor for a CompanyCardHolderIdentification object
	 * 
	 * @param	value	byte array of a CompanyCardHolderIdentification structure
	 * 					whose data is used when the CompanyCardHolderIdentification
	 * 					object is created.
	 */
	public CompanyCardHolderIdentification(byte[] value) {
		companyName = new Name(arrayCopy(value, 0, 36));
		companyAddress = new Address(arrayCopy(value, 36, 36));
		cardHolderPreferredLanguage = new Language(arrayCopy(value, 72, 2));
	}

	/**
	 * Returns the name of the holder company of a CompanyCardHolderIdentification object.
	 * 
	 * @return	the name of the holder company of the CompanyCardHolderIdentification object
	 */
	public Name getCompanyName() {
		return companyName;
	}

	/**
	 * Sets the name of the holder company of a CompanyCardHolderIdentification object.
	 * 
	 * @param	companyName		the name of the holder companyto be set
	 * 							for the CompanyCardHolderIdentification object
	 */
	public void setCompanyName(Name companyName) {
		this.companyName = companyName;
	}

	/**
	 * Returns the address of the holder company of a CompanyCardHolderIdentification object.
	 * 
	 * @return	the address of the holder company of the CompanyCardHolderIdentification object
	 */
	public Address getCompanyAddress() {
		return companyAddress;
	}

	/**
	 * Sets the address of the holder company of a CompanyCardHolderIdentification object.
	 * 
	 * @param	companyAddress	the address of the holder company to be set
	 * 							for the CompanyCardHolderIdentification object
	 */
	public void setCompanyAddress(Address companyAddress) {
		this.companyAddress = companyAddress;
	}

	/**
	 * Returns the preferred language of the card holder of a CompanyCardHolderIdentification object.
	 * 
	 * @return	the preferred language of the card holder of the CompanyCardHolderIdentification object
	 */
	public Language getCardHolderPreferredLanguage() {
		return cardHolderPreferredLanguage;
	}

	/**
	 * Sets the preferred language of the card holder of a CompanyCardHolderIdentification object.
	 * 
	 * @param	cardHolderPreferredLanguage		the preferred language of the card holder to be set
	 * 											for the CompanyCardHolderIdentification object
	 */
	public void setCardHolderPreferredLanguage(Language cardHolderPreferredLanguage) {
		this.cardHolderPreferredLanguage = cardHolderPreferredLanguage;
	}

	@Override
	public Element generateXMLElement(String name) {
		Element node = new Element(name);
		node.addContent( companyName.generateXMLElement("companyName"));
		node.addContent( companyAddress.generateXMLElement("companyAddress"));
		node.addContent( cardHolderPreferredLanguage.generateXMLElement("cardHolderPreferredLanguage"));
		return node;
	}
}
