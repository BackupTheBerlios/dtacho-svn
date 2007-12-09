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

import java.util.Vector;
import java.util.Iterator;
import org.digitalertachograph.DDDQuery.internalData.DataClass;
import org.jdom.Element;

public class CardEventData extends DataClass{
	/*
	 * CardEventData ::= SEQUENCE SIZE(6) OF {
	 * 	cardEventRecords SET SIZE(NoOfEventsPerType) OF CardEventRecord
	 * }
	 */

	private Vector<CardEventRecord> cardEventRecords[] = new Vector[6]; // 6 Stück.

	public CardEventData(byte[] value){
		for (int i = 0; i < cardEventRecords.length; i++)
			cardEventRecords[i] = new Vector<CardEventRecord>();

		for (int i = 0; i < value.length; i += 24) {
			byte[] record = arrayCopy(value, i, 24);
			CardEventRecord tmp = new CardEventRecord(record);
			// TODO bin mir nicht sicher, ob das wirklich so ist.
			cardEventRecords[ tmp.getEventType().getCategory() ].add(tmp);
		}
	}
	
	@Override
	public Element generateXMLElement(String name) {
		Element node = new Element(name);
		for (int i = 0; i < cardEventRecords.length; i++) {
			Iterator<CardEventRecord> it = cardEventRecords[i].iterator();
			while(it.hasNext()){
				CardEventRecord ced = (CardEventRecord) it.next();
				Element cedElement = ced.generateXMLElement("CardEventRecord");
				node.addContent(cedElement);
			}
		}
		
		return node;
	}
}
