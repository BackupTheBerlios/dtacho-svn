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

package org.digitalertachograph.DDDQuery.internalData;

import org.digitalertachograph.DDDQuery.internalData.DataTypes.CardControlActivityDataRecord;
//import org.digitalertachograph.DDDQuery.internalData.DataTypes.ControlCardControlActivityData;
import org.jdom.Element;

public class EF_Control_Activity_Data extends DataClass {
	public static int size;
	private CardControlActivityDataRecord cardControlActivityDataRecord;
	
	public EF_Control_Activity_Data(byte[] value){
		size = value.length;
		cardControlActivityDataRecord = new CardControlActivityDataRecord(value);
	}
	
	public Element generateXMLElement(String name){
		Element node = new Element(name);
		Element child = cardControlActivityDataRecord.generateXMLElement("cardControlActivityDataRecord");
		node.addContent(child);
		return node;
	}
}
