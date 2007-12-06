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

public class CardActivityDailyRecord extends DataClass {
	/*
	 * CardActivityDailyRecord ::= SEQUENCE {
   	 * 	activityPreviousRecordLength        INTEGER(0..CardActivityLengthRange),
   	 *  activityRecordLength				INTEGER(0..CardActivityLengthRange),
   	 *  activityRecordDate                  TimeReal,
   	 *  activityDailyPresenceCounter        DailyPresenceCounter,
   	 *  activityDayDistance                 Distance,
   	 *  activityChangeInfo                  SET SIZE(1..1440) OF ActivityChangeInfo
   	 *  }
   	 *  ----
   	 *  Distance ::= INTEGER(0..2^16-1)
   	 *  ---
   	 *  DailyPresenceCounter ::= BCDString(SIZE(2))
   	 *  ---
   	 *  CardActivityLengthRange ::= INTEGER(0..2^16-1)
	 */
	
	private int activityPreviousRecordLength;
	private int activityRecordLength;
	private TimeReal activityRecordDate;
	private String activityDailyPresenceCounter;
	private int activityDayDistance;
	private Vector<ActivityChangeInfo> activityChangeInfo;
	
	private boolean complete = false;
	private int size;
	
	public CardActivityDailyRecord(byte[] value) {

		// if we get so less bytes we just a part of an old CardAcitivityDailyRecord.
		if(value.length <= 12)
			return;
		
		this.activityPreviousRecordLength = convertIntoUnsigned2ByteInt(arrayCopy(value, 0, 2));
		this.activityRecordLength = convertIntoUnsigned2ByteInt(arrayCopy(value, 2, 2));
		this.activityRecordDate = new TimeReal(arrayCopy(value, 4, 4));
		this.activityDailyPresenceCounter = new String(convertIntoBCDString( arrayCopy(value, 8, 2)));
		this.activityDayDistance = convertIntoUnsigned2ByteInt(arrayCopy(value, 10, 2));
		this.activityChangeInfo = new Vector();
		
		// TODO wie haben wir length in den anderen klassen genannt?!?
		int length = 12; // we have 12byte so far
		for(;length < activityRecordLength; length += 2){
			// ActivityChangeInfo <- 2byte groß
			ActivityChangeInfo aci = new ActivityChangeInfo( arrayCopy(value, length, 2) );
			activityChangeInfo.add( aci );
		}
		this.size = length;
		this.complete = true;
	}
	

	@Override
	public Element generateXMLElement(String name) {
		Element node = new Element(name);
		
		node.addContent(
				new Element("activityPreviousRecordLength").setText( 
						Integer.toString(activityPreviousRecordLength)
				)
		);
		
		node.addContent(
				new Element("activityRecordLength").setText(
						Integer.toString(activityRecordLength)
				)
		);
		
		node.addContent( activityRecordDate.generateXMLElement("activityRecordDate"));
		
		node.addContent(
				new Element("activityDailyPresenceCounter").setText(activityDailyPresenceCounter)
		);
		
		node.addContent(
				new Element("activityDayDistance").setText(
						Integer.toString(activityDayDistance)
				)
		);

		Iterator it = activityChangeInfo.iterator();
		Element activityChangeInfoElement = new Element("activityChangeInfo");
		while (it.hasNext()) {
			ActivityChangeInfo aci = (ActivityChangeInfo) it.next();
			activityChangeInfoElement.addContent( aci.generateXMLElement("activityChangeInfo"));
			
			
		}
		node.addContent( activityChangeInfoElement );

		return node;
	}
	
	
	public boolean isComplete() {
		return complete;
	}


	public int getSize(){
		return size;
	}

}
