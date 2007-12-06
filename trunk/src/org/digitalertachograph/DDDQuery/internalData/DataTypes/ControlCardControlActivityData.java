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

import java.util.Iterator;
import java.util.Vector;

import org.digitalertachograph.DDDQuery.internalData.DataClass;
import org.jdom.Element;

public class ControlCardControlActivityData extends DataClass {
	
	/*
	 * ControlCardControlActivityData ::= SEQUENCE {	
	 * 	controlPointerNewestRecord INTEGER(0..NoOfControlActivityRecords-1), 2byte
	 * 	controlActivityRecords SET SIZE(NoOfControlActivityRecords) OF controlActivityRecord SEQUENCE {
	 * 			controlType ControlType,
	 * 			controlTime TimeReal,
	 * 			controlledCardNumber FullCardNumber,
	 * 			controlledVehicleRegistration VehicleRegistrationIdentification,
	 * 			controlDownloadPeriodBegin TimeReal,
	 * 			controlDownloadPeriodEnd TimeReal
	 * 	} // which is a CardControlActiviyDataRecord
	 * }
	 */
	
	private int controlPointerNewestRecord;
	private Vector<CardControlActivityDataRecord> controlActivityRecords = new Vector();

	public ControlCardControlActivityData(byte[] value){
		controlPointerNewestRecord = convertIntoUnsigned2ByteInt( arrayCopy(value, 0, 2));
		for (int i = 2; i < value.length; i += 46) {
			byte[] record = arrayCopy(value, i, 46);
			CardControlActivityDataRecord tmp = new CardControlActivityDataRecord(record);
			controlActivityRecords.add(tmp);
		}
	}

	public Vector<CardControlActivityDataRecord> getControlActivityRecords() {
		return controlActivityRecords;
	}

	public void setControlActivityRecords(
			Vector<CardControlActivityDataRecord> controlActivityRecords) {
		this.controlActivityRecords = controlActivityRecords;
	}

	public int getControlPointerNewestRecord() {
		return controlPointerNewestRecord;
	}

	public void setControlPointerNewestRecord(int controlPointerNewestRecord) {
		this.controlPointerNewestRecord = controlPointerNewestRecord;
	}
	
	@Override
	public Element generateXMLElement(String name) {
		Element node = new Element(name);
		node.addContent( new Element("controlActivityRecords").setText( Integer.toString( controlPointerNewestRecord)));
		
		Iterator it = controlActivityRecords.iterator();
		while (it.hasNext()) {
			CardControlActivityDataRecord ccadr = (CardControlActivityDataRecord) it.next();
			Element ccadrElement = ccadr.generateXMLElement("controlActivityRecord");
			node.addContent(ccadrElement);
		}
		return node;
	}
}
