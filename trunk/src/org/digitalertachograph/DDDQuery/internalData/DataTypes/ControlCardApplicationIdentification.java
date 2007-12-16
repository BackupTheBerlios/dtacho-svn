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

public class ControlCardApplicationIdentification extends DataClass {
	/*
	 * ControlCardApplicationIdentification ::= SEQUENCE {
	 * 	typeOfTachographCardId EquipmentType,
	 * 	cardStructureVersion CardStructureVersion,
	 * 	noOfControlActivityRecords NoOfControlActivityRecords
	 * }
	 * --
	 * CardStructureVersion ::= OCTET STRING (SIZE(2))
	 * --
	 * NoOfControlActivityRecords ::= INTEGER(0..2^16-1)
	 */

	private EquipmentType typeOfTachographCardId;
	private byte[] cardStructureVersion;
	private int noOfControlActivityRecords;
	
	
	/**
	 * Constructor for a ControlCardApplicationIdentification object
	 * 
	 * @param	value	byte array of a ControlCardApplicationIdentification structure
	 * 					whose data is used when the ControlCardApplicationIdentification
	 * 					object is created.
	 */
	public ControlCardApplicationIdentification(byte[] value){
		typeOfTachographCardId = new EquipmentType(value[0]);
		cardStructureVersion = arrayCopy(value, 1, 2);
		noOfControlActivityRecords = convertIntoUnsigned2ByteInt( arrayCopy(value, 3, 2));
	}

	public byte[] getCardStructureVersion() {
		return cardStructureVersion;
	}

	public void setCardStructureVersion(byte[] cardStructureVersion) {
		this.cardStructureVersion = cardStructureVersion;
	}

	public int getNoOfControlActivityRecords() {
		return noOfControlActivityRecords;
	}

	public void setNoOfControlActivityRecords(int noOfControlActivityRecords) {
		this.noOfControlActivityRecords = noOfControlActivityRecords;
	}

	public EquipmentType getTypeOfTachographCardId() {
		return typeOfTachographCardId;
	}

	public void setTypeOfTachographCardId(EquipmentType typeOfTachographCardId) {
		this.typeOfTachographCardId = typeOfTachographCardId;
	}
	
	@Override
	public Element generateXMLElement(String name) {
		Element node = new Element(name);
		node.addContent( typeOfTachographCardId.generateXMLElement("typeOfTachographCardId"));
		node.addContent( new Element("cardStructureVersion").setText( convertIntoBCDString(cardStructureVersion)));
		node.addContent( new Element("noOfControlActivityRecords").setText( Integer.toString(noOfControlActivityRecords)));
		return node;
	}
}
