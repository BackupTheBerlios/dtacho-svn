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

public class SpecificConditionType extends DataClass {
	/*
	 * SpecificConditionType ::= INTEGER(0..255)
	 * 
	 * Value assignment:
	 * '00'H RFU
	 * '01'H Out of scope - Begin
	 * '02'H Out of scope - End
	 * '03'H Ferry/Train crossing
	 * '04'H .. 'FF'H RFU.
	 */
	private byte specificConditionType;

	public SpecificConditionType(byte specificConditionType) {
		this.specificConditionType = specificConditionType;
	}

	public byte getSpecificConditionType() {
		return specificConditionType;
	}

	public void setSpecificConditionType(byte specificConditionType) {
		this.specificConditionType = specificConditionType;
	}
	
	@Override
	public Element generateXMLElement(String name) {
		Element node = new Element(name);
		node.addContent( new Element("specificConditionType").setText( Byte.toString( specificConditionType )));
		return node;
	}
	
}
