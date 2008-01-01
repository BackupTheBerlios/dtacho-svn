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

import java.text.DateFormat;
import java.util.Iterator;
import java.util.Locale;
import java.util.Vector;

import java.util.Date;

import org.digitalertachograph.DDDQuery.internalData.DataClass;
import org.jdom.Element;

public class CardActivityDailyRecord extends DataClass {
	/*
	 * CardActivityDailyRecord ::= SEQUENCE {
   	 * 	activityPreviousRecordLength        INTEGER(0..CardActivityLengthRange), 2 bytes
   	 *  activityRecordLength				INTEGER(0..CardActivityLengthRange), 2 bytes
   	 *  activityRecordDate                  TimeReal, 4 bytes
   	 *  activityDailyPresenceCounter        DailyPresenceCounter, 2 bytes
   	 *  activityDayDistance                 Distance, 2 bytes
   	 *  activityChangeInfo                  SET SIZE(1..1440) OF ActivityChangeInfo, 2...2880 bytes
   	 *  }
   	 * ---
   	 * CardActivityLengthRange ::= INTEGER(0..2^16-1)
	 * min.:  5544 bytes (28 days * 93 activity changes)
	 * max.: 13776 bytes (28 days * 240 activity changes)
   	 * ---
   	 * DailyPresenceCounter ::= BCDString(SIZE(2))
   	 * ---
   	 * Distance ::= INTEGER(0..2^16-1)
   	 * ---
   	 * ActivityChangeInfo ::= OCTET STRING (SIZE(2))
   	 * ---
	 */
	
	private int activityPreviousRecordLength;
	private int activityRecordLength;
	private TimeReal activityRecordDate;
	private byte[] activityDailyPresenceCounter = new byte[2];
	private int activityDayDistance;
	private Vector<ActivityChangeInfo> activityChangeInfo;
	
	private boolean complete = false;


	/**
	 * Constructor for a CardActivityDailyRecord object
	 * 
	 * @param	value	byte array of a CardActivityDailyRecord structure
	 * 					whose data is used when the CardActivityDailyRecord
	 * 					object is created
	 */
	public CardActivityDailyRecord(byte[] value) {

		// if we get so less bytes we have just a part of an old CardActivityDailyRecord.
		if (value.length <= 12)
			return;
		
		this.activityPreviousRecordLength = convertIntoUnsigned2ByteInt(arrayCopy(value, 0, 2));
		this.activityRecordLength = convertIntoUnsigned2ByteInt(arrayCopy(value, 2, 2));
		this.activityRecordDate = new TimeReal(arrayCopy(value, 4, 4));
		this.activityDailyPresenceCounter = arrayCopy(value, 8, 2);
		this.activityDayDistance = convertIntoUnsigned2ByteInt(arrayCopy(value, 10, 2));
		this.activityChangeInfo = new Vector<ActivityChangeInfo>();

		System.out.printf("  %d activity change(s) in this record\n", ( activityRecordLength - 12 ) / 2 );
		
		// TODO wie haben wir length in den anderen Klassen genannt?!?
		int length = 12; // we have 12 bytes so far
		for(;length < activityRecordLength; length += 2){
			// ActivityChangeInfo <- 2 bytes
			ActivityChangeInfo aci = new ActivityChangeInfo( arrayCopy(value, length, 2) );
			activityChangeInfo.add( aci );

			Date d = new Date( this.activityRecordDate.getTimereal() * 1000 + aci.getTime() * 60 * 1000 );
			System.out.printf( "   %s, activity %02x\n", DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.GERMANY).format(d), aci.getActivity() );
		}
		this.complete = true;
	}
	
	/**
	 * Indicates if the CardActivityDailyRecord object is a complete record.
	 * 
	 * @return			true if the CardActivityDailyRecord object is a complete record
	 */
	public boolean isComplete() {
		return complete;
	}
	
	/**
	 * Returns the length of the previous CardActivityDailyRecord
	 * 
	 * @return			length (number of bytes) of the previous CardActivityDailyRecord
	 */
	public int getActivityPreviousRecordLength() {
		return activityPreviousRecordLength;
	}

	/**
	 * Returns the length of this CardActivityDailyRecord
	 * 
	 * @return			length (number of bytes) of this CardActivityDailyRecord
	 */
	public int getActivityRecordLength() {
		return activityRecordLength;
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
				new Element("activityDailyPresenceCounter").setText(convertBCDStringIntoString(activityDailyPresenceCounter))
		);
		
		node.addContent(
				new Element("activityDayDistance").setText(
						Integer.toString(activityDayDistance)
				)
		);

		Iterator<ActivityChangeInfo> it = activityChangeInfo.iterator();
		Element activityChangeInfoElement = new Element("activityChangeInfoSet");
		while (it.hasNext()) {
			ActivityChangeInfo aci = (ActivityChangeInfo) it.next();
			activityChangeInfoElement.addContent( aci.generateXMLElement("activityChangeInfo"));
		}
		node.addContent( activityChangeInfoElement );

		return node;
	}
}
