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
	
	public CompanyCardHolderIdentification(byte[] value){
		companyName = new Name( arrayCopy(value, 0, 36) );
		companyAddress = new Address( arrayCopy(value, 36, 36));
		cardHolderPreferredLanguage = new Language( arrayCopy(value, 72, 2));
	}

	public Language getCardHolderPreferredLanguage() {
		return cardHolderPreferredLanguage;
	}

	public void setCardHolderPreferredLanguage(Language cardHolderPreferredLanguage) {
		this.cardHolderPreferredLanguage = cardHolderPreferredLanguage;
	}

	public Address getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(Address companyAddress) {
		this.companyAddress = companyAddress;
	}

	public Name getCompanyName() {
		return companyName;
	}

	public void setCompanyName(Name companyName) {
		this.companyName = companyName;
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
