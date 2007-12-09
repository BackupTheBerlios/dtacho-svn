/*   Copyright (C) 2007, Martin Barth

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
	 * 	typeOfTachographCardId EquipmentType,
	 * 	cardStructureVersion CardStructureVersion,
	 * 	noOfCompanyActivityRecords NoOfCompanyActivityRecords
	 * }
	 */

	private EquipmentType typeOfTachographCardId;
	private byte[] cardStructureVersion;
	private int noOfCompanyActivityRecords;
	
	public CompanyCardApplicationIdentification(byte[] value){
		typeOfTachographCardId = new EquipmentType(value[0]);
		cardStructureVersion = arrayCopy(value, 1, 2);
		noOfCompanyActivityRecords = convertIntoUnsigned2ByteInt( arrayCopy(value, 3, 2));
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
