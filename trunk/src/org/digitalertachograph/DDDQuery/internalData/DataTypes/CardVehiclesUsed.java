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

public class CardVehiclesUsed extends DataClass {
	/*
	 * CardVehiclesUsed := SEQUENCE {
	 * 	vehiclePointerNewestRecord INTEGER(0..NoOfCardVehicleRecords-1), 2 bytes
	 * 	cardVehicleRecords SET SIZE(NoOfCardVehicleRecords) OF CardVehicleRecord, 2604..6200 bytes
	 * }
	 * --
	 * NoOfCardVehicleRecords ::= INTEGER(0..2^16-1), 2 bytes
	 * min.:  84
	 * max.: 200
	 */
	
	private Vector<CardVehicleRecord> cardVehicleRecords = new Vector<CardVehicleRecord>(84); // min. 84; will be automatically expanded at run time if required!

	
	/**
	 * Constructor for a CardVehiclesUsed object
	 * 
	 * @param	value	byte array of a CardVehiclesUsed structure
	 * 					whose data is used when the CardVehiclesUsed
	 * 					object is created.
	 */
	public CardVehiclesUsed(byte[] value){
		for (int i = 2; i < value.length; i += 31) {
			byte[] record = arrayCopy(value, i, 31);
			CardVehicleRecord tmp = new CardVehicleRecord(record);

			cardVehicleRecords.add( tmp );
		}
	}
	
	@Override
	public Element generateXMLElement(String name) {
		Element node = new Element(name);

		Element recordsnode = new Element("cardVehicleRecords");
		node.addContent(recordsnode);
		
		Iterator<CardVehicleRecord> iter = cardVehicleRecords.iterator();
		while (iter.hasNext()) {
			CardVehicleRecord cvr = (CardVehicleRecord) iter.next();
			recordsnode.addContent( cvr.generateXMLElement("cardVehicleRecord"));
		}
		return node;
	}
}
