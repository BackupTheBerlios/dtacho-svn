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

public class CompanyCardApplicationIdentification extends DataClass {
	/*
	 * CompanyCardApplicationIdentification ::= SEQUENCE {
	 * 	typeOfTachographCardId EquipmentType, 1 byte
	 * 	cardStructureVersion CardStructureVersion, 2 bytes
	 * 	noOfCompanyActivityRecords NoOfCompanyActivityRecords, 2 bytes
	 * }
	 * ---
	 * noOfCompanyActivityRecords ::= INTEGER(0..2^16-1)
	 * min.: 230
	 * max.: 520
	 */

	private EquipmentType typeOfTachographCardId;
	private byte[] cardStructureVersion;
	private int noOfCompanyActivityRecords;
	

	/**
	 * Constructor for a CompanyCardApplicationIdentification object
	 */
	public CompanyCardApplicationIdentification() {
		typeOfTachographCardId = new EquipmentType();
		cardStructureVersion = new byte[2];
		noOfCompanyActivityRecords = 0;
	}

	/**
	 * Constructor for a CompanyCardApplicationIdentification object
	 * 
	 * @param	value	byte array of a CompanyCardApplicationIdentification structure
	 * 					whose data is used when the CompanyCardApplicationIdentification
	 * 					object is created.
	 */
	public CompanyCardApplicationIdentification(byte[] value) {
		typeOfTachographCardId = new EquipmentType(value[0]);
		cardStructureVersion = arrayCopy(value, 1, 2);
		noOfCompanyActivityRecords = convertIntoUnsigned2ByteInt(arrayCopy(value, 3, 2));
	}

	/**
	 * Returns the implemented type of card of a CompanyCardApplicationIdentification object.
	 * 
	 * @return	the implemented type of card of the CompanyCardApplicationIdentification object
	 */
	public EquipmentType getTypeOfTachographCardId() {
		return typeOfTachographCardId;
	}

	/**
	 * Sets the implemented type of card of a CompanyCardApplicationIdentification object.
	 * 
	 * @param	typeOfTachographCardId	the implemented type of card to be set
	 * 									for the CompanyCardApplicationIdentification object
	 */
	public void setTypeOfTachographCardId(EquipmentType typeOfTachographCardId) {
		this.typeOfTachographCardId = typeOfTachographCardId;
	}

	/**
	 * Returns the version of the structure that is implemented in the card
	 * of a CompanyCardApplicationIdentification object.
	 * 
	 * @return	the version of the structure that is implemented in the card
	 * 			of the CompanyCardApplicationIdentification object
	 */
	public byte[] getCardStructureVersion() {
		return cardStructureVersion;
	}

	/**
	 * Sets the version of the structure that is implemented in the card
	 * of a CompanyCardApplicationIdentification object.
	 * 
	 * @param	cardStructureVersion	the version of the structure that is implemented in the card
	 * 									to be set for the CompanyCardApplicationIdentification object
	 */
	public void setCardStructureVersion(byte[] cardStructureVersion) {
		this.cardStructureVersion = cardStructureVersion;
	}

	/**
	 * Returns the number of company activity records the card can store
	 * of a CompanyCardApplicationIdentification object.
	 * 
	 * @return	the number of company activity records the card can store
	 * 			of the CompanyCardApplicationIdentification object
	 */
	public int getNoOfCompanyActivityRecords() {
		return noOfCompanyActivityRecords;
	}

	/**
	 * Sets the number of company activity records the card can store
	 * of a CompanyCardApplicationIdentification object.
	 * 
	 * @param	noOfCompanyActivityRecords	the number of company activity records the card can store
	 * 										to be set for the CompanyCardApplicationIdentification object
	 */
	public void setNoOfCompanyActivityRecords(int noOfCompanyActivityRecords) {
		this.noOfCompanyActivityRecords = noOfCompanyActivityRecords;
	}

	@Override
	public Element generateXMLElement(String name) {
		Element node = new Element(name);
		node.addContent( typeOfTachographCardId.generateXMLElement("typeOfTachographCardId"));
		node.addContent( new Element("cardStructureVersion").setText( convertIntoHexString(cardStructureVersion)));
		node.addContent( new Element("noOfCompanyActivityRecords").setText(Integer.toString(noOfCompanyActivityRecords)));
		return node;
	}
}
