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

public class HolderName extends DataClass {
	/*
	 * HolderName ::= SEQUENCE {
	 * 	holderSurname Name,
	 * 	holderFirstNames Name
	 * }
	 */
	private Name holderSurname;
	private Name holderFirstNames;
	
	public HolderName(byte[] value){
		// 2x 36byte
		holderSurname = new Name( arrayCopy(value, 0, 36));
		holderFirstNames = new Name( arrayCopy(value, 36, 36));
	}
	
	public Name getHolderFirstNames() {
		return holderFirstNames;
	}
	public void setHolderFirstNames(Name holderFirstNames) {
		this.holderFirstNames = holderFirstNames;
	}
	public Name getHolderSurname() {
		return holderSurname;
	}
	public void setHolderSurname(Name holderSurname) {
		this.holderSurname = holderSurname;
	}
	
	@Override
	public Element generateXMLElement(String name) {
		Element node = new Element(name);
		node.addContent( holderSurname.generateXMLElement("holderSurname"));
		node.addContent( holderFirstNames.generateXMLElement("holderFirstNames"));
		return node;
	}
}
