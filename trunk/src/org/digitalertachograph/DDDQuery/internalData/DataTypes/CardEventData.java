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
	 *
	 * ---
	 * NoOfEventsPerType ::= 6..12 for driver card
	 * NoOfEventsPerType ::= 3     for workshop card
	 * ---
	 * according to requirement 204:
	 * sequence				description								eventfaulttype
	 * cardEventRecords[0]:	time overlap							'03'H
	 * cardEventRecords[1]:	card insertion while driving			'05'H
	 * cardEventRecords[2]:	last card session not correctly closed	'06'H
	 * cardEventRecords[3]:	power supply interruption				'08'H
	 * cardEventRecords[4]:	motion data error						'09'H
	 * cardEventRecords[5]:	security breach attempts,
	 * 						vehicle unit related & sensor related	'10'H...'2F'H
	 * ---
	 * sechs Folgen von Datens�tzen (cardEventRecords[0..5]), die jeweils
	 * "NoOfEventsPerType"-fach CardEventRecords der "EventFaultTypes" oben enthalten.
	 */


	private static final int sequencesize = 6;
	private Vector<Vector<CardEventRecord>> cardEventRecords = new Vector<Vector<CardEventRecord>>();

	
	/**
	 * Constructor for a CardEventData object
	 * 
	 * @param	value	byte array of a CardEventData structure
	 * 					whose data is used when the CardEventData
	 * 					object is created.
	 */
	public CardEventData(byte[] value) {

		// we assume NoOfEventsPerType is constant for all sets of event records
		// within the cardeventdata sequence
		int noofeventspertype = value.length / (sequencesize * 24);

		System.out.println("  noofeventspertype: " + noofeventspertype);	

		// loops are beautiful. cantaloop... funky, funky...
		for (int j = 0; j < sequencesize; j++ ) {

			cardEventRecords.add(new Vector<CardEventRecord>());

			for (int i = (noofeventspertype * 24 * j); i < (noofeventspertype * 24 * (j + 1)); i += 24) {
				byte[] record = arrayCopy(value, i, 24);
				CardEventRecord tmp = new CardEventRecord(record);

				if (tmp.getEventBeginTime().getTimereal() != 0) {
					switch(j) {
					case 0:
						System.out.printf("  time overlap, event fault type: 0x%02x\n", tmp.getEventType().getEventFaultType());
						break;

					case 1:
						System.out.printf("  card insertion while driving, event fault type: 0x%02x\n", tmp.getEventType().getEventFaultType());
						break;

					case 2:
						System.out.printf("  last card session not correctly closed, event fault type: 0x%02x\n", tmp.getEventType().getEventFaultType());
						break;

					case 3:
						System.out.printf("  power supply interruption, event fault type: 0x%02x\n", tmp.getEventType().getEventFaultType());
						break;

					case 4:
						System.out.printf("  motion data error, event fault type: 0x%02x\n", tmp.getEventType().getEventFaultType());
						break;

					case 5:
						System.out.printf("  security breach attempt, event fault type: 0x%02x\n", tmp.getEventType().getEventFaultType());
						break;

					default:
						break;
					}
				}

				cardEventRecords.get(j).add(tmp);
			}
		}
	}

	
	@Override
	public Element generateXMLElement(String name) {
		Element node = new Element(name);
		for (int i = 0; i < sequencesize; i++) {
			Iterator<CardEventRecord> it = cardEventRecords.get(i).iterator();
			while(it.hasNext()){
				CardEventRecord cer = (CardEventRecord) it.next();
				Element cerElement = cer.generateXMLElement("cardEventRecord");
				node.addContent(cerElement);
			}
		}

		return node;
	}
}
