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

import java.util.Vector;
import java.util.Iterator;
import org.digitalertachograph.DDDQuery.internalData.DataClass;
import org.jdom.Element;

public class CardFaultData extends DataClass {
	/*
	 * CardFaultData ::= SEQUENCE SIZE(2) OF {
	 * 	 cardFaultRecords SET SIZE(NoOfFaultsPerType) OF CardFaultRecord }
	 * --
	 * zwei sets, die jeweils "noOfFaultsPerType"-fach CardFaultRecords enhalten.
	 */
	private Vector<CardFaultRecord>[] cardFaultRecords = new Vector[2];

	public CardFaultData(byte[] value) {
		for (int i = 0; i < cardFaultRecords.length; i++)
			cardFaultRecords[i] = new Vector<CardFaultRecord>();

		for (int i = 0; i < value.length; i += 24) {
			byte[] record = arrayCopy(value, i, 24);
			CardFaultRecord tmp = new CardFaultRecord(record);
			// TODO bin mir nicht sicher, ob das wirklich so ist.
			cardFaultRecords[tmp.getFaultType().getCategory()].add(tmp);
		}
	}
	
	@Override
	public Element generateXMLElement(String name) {
		Element node = new Element(name);
		for (int i = 0; i < cardFaultRecords.length; i++) {
			Iterator it = cardFaultRecords[i].iterator();
			while(it.hasNext()){
				CardFaultRecord cfr = (CardFaultRecord) it.next();
				Element cfrElement = cfr.generateXMLElement("cardFaultRecord");
				node.addContent(cfrElement);
			}
		}
		
		return node;
	}
}
