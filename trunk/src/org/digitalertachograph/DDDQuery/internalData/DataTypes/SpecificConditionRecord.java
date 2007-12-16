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

public class SpecificConditionRecord extends DataClass {
	/*
	 * SpecificConditionRecord ::= SEQUENCE {
	 * 	entryTime TimeReal, 4 bytes
	 * 	SpecificConditionType, 1 byte
	 * }
	 */
	private TimeReal entryTime;
	private SpecificConditionType specificConditionType;

	
	/**
	 * Constructor for a SpecificConditionRecord object
	 * 
	 * @param	value	byte array of a SpecificConditionRecord structure
	 * 					whose data is used when the SpecificConditionRecord
	 * 					object is created.
	 */
	public SpecificConditionRecord(byte[] value){
		entryTime = new TimeReal( arrayCopy(value, 0, 4));
		specificConditionType = new SpecificConditionType(value[4]);
	}

	public TimeReal getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(TimeReal entryTime) {
		this.entryTime = entryTime;
	}

	public SpecificConditionType getSpecificConditionType() {
		return specificConditionType;
	}

	public void setSpecificConditionType(SpecificConditionType specificConditionType) {
		this.specificConditionType = specificConditionType;
	}
	
	@Override
	public Element generateXMLElement(String name) {
		Element node = new Element(name);
		node.addContent( entryTime.generateXMLElement("entryTime"));
		node.addContent( specificConditionType.generateXMLElement("specificConditionType"));
		return node;
	}
}
