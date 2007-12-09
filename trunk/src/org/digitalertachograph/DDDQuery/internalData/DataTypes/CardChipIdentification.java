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

public class CardChipIdentification extends DataClass {

	/*
	 * CardChipIdentification ::= SEQUENCE {
	 *  icSerialNumber				0x00..0x00	OCTET STRING (SIZE(4)),
	 *  icManufacturingReferences	0x00..0x00	OCTET STRING (SIZE(4))
	 * }
	 */

	private byte[] icSerialNumber;
	private byte[] icManufacturingReferences;
	
	public CardChipIdentification(){
	}
	
	public byte[] getIcManufacturingReferences() {
		return icManufacturingReferences;
	}
	public void setIcManufacturingReferences(byte[] icManufacturingReferences) {
		this.icManufacturingReferences = icManufacturingReferences;
	}
	public byte[] getIcSerialNumber() {
		return icSerialNumber;
	}
	public void setIcSerialNumber(byte[] icSerialNumber) {
		this.icSerialNumber = icSerialNumber;
	}
	
	@Override
	public Element generateXMLElement(String name) {
		Element node = new Element(name);
		node.addContent(new Element("icSerialNumber").setText(convertIntoHexString(icSerialNumber)) );
		node.addContent(new Element("icManufacturingReferences").setText(convertIntoHexString(icManufacturingReferences)) );
		return node;
	}
}
