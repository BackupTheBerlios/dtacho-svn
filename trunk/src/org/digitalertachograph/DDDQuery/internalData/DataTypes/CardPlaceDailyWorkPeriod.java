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

import java.util.Iterator;
import java.util.Vector;

import org.digitalertachograph.DDDQuery.internalData.DataClass;
import org.jdom.Element;

public class CardPlaceDailyWorkPeriod extends DataClass {
	/*
	 * CardPlaceDailyWorkPeriod ::= SEQUENCE {
	 * 	placePointerNewestRecord INTEGER(0..NoOfCardPlaceRecords-1), 1 byte
	 * 	placeRecords SET SIZE(NoOfCardPlaceRecords) OF PlaceRecord, 840..1120 bytes
	 * }
	 * ---
	 * NoOfCardPlaceRecords ::= INTEGER(0..255), 1 byte
	 * min.:  84
	 * max.: 112
	 */
	
	private short placePointerNewestRecord;
	private Vector<PlaceRecord> placeRecords = new Vector<PlaceRecord>(84); // min. 84; will be automatically expanded at run time if required!
	

	/**
	 * Constructor for a CardPlaceDailyWorkPeriod object
	 * 
	 * @param	value	byte array of a CardPlaceDailyWorkPeriod structure
	 * 					whose data is used when the CardPlaceDailyWorkPeriod
	 * 					object is created.
	 */
	public CardPlaceDailyWorkPeriod(byte[] value) {
		placePointerNewestRecord = convertIntoUnsigned1ByteInt(value[0]);
		for (int i = 1; i < value.length; i+=10) {
			byte[] record = arrayCopy(value, i, 10);
			PlaceRecord tmp = new PlaceRecord(record);
			placeRecords.add( tmp );
			
		}
	}
	
	@Override
	public Element generateXMLElement(String name) {
		Element node = new Element(name);
		node.addContent( new Element("placePointerNewestRecord").setText( Short.toString(placePointerNewestRecord)));

		Iterator<PlaceRecord> it = placeRecords.iterator();
		while(it.hasNext()){
			PlaceRecord pr = (PlaceRecord) it.next();
			Element prElement = pr.generateXMLElement("placeRecord");
			node.addContent( prElement );
		}
		return node;
	}

}
