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

import org.digitalertachograph.DDDQuery.internalData.DataTypes.CardCurrentUse;
import org.jdom.Element;

public class EF_Current_Usage extends DataClass {
	
	public static final int size = 19;
	private CardCurrentUse cardCurrentUse;
	
	public EF_Current_Usage(byte[] value){
		cardCurrentUse = new CardCurrentUse(value);
	}

	public CardCurrentUse getCardCurrentUse() {
		return cardCurrentUse;
	}

	public void setCardCurrentUse(CardCurrentUse cardCurrentUse) {
		this.cardCurrentUse = cardCurrentUse;
	}
	

	public Element generateXMLElement(String name){
		// discard name - this.getClass().getSimpleName() is unique!
		Element node = new Element(this.getClass().getSimpleName());
		Element child = cardCurrentUse.generateXMLElement("cardCurrentUse");
		node.addContent(child);
		return node;
	}
	
}
