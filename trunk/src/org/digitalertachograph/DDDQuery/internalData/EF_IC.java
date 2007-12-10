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

package org.digitalertachograph.DDDQuery.internalData;

import org.digitalertachograph.DDDQuery.internalData.DataTypes.*;
import org.jdom.Element;

public class EF_IC extends DataClass {
	static final public int size = 8;
	private CardChipIdentification cardChipIdentification;
	
	public EF_IC(byte[] value){
		cardChipIdentification = new CardChipIdentification();
		cardChipIdentification.setIcSerialNumber( arrayCopy(value, 0 , 4));
		cardChipIdentification.setIcManufacturingReferences( arrayCopy(value, 4, 4));
	}
	
	public Element generateXMLElement(String name){
		// discard name - this.getClass().getSimpleName() is unique!
		Element node = new Element(this.getClass().getSimpleName());
		Element child = cardChipIdentification.generateXMLElement("cardChipIdentification");
		node.addContent(child);
		return node;
	}
}