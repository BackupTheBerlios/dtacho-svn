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

public class CardDriverActivity extends DataClass {
	/*
	 * CardDriverActivity ::= SEQUENCE {
	 * 	activityPointerOldestDayRecord INTEGER(0..CardActivityLengthRange-1), 2byte
	 * 	activityPointerNewestRecord INTEGER(0..CardActivityLengthRange-1), 2byte;
	 * 	activityDailyRecords OCTET STRING (SIZE(CardActivityLengthRange))
	 * }
	 * --
	 * CardActivityLengthRange ::= INTEGER(0..2^16-1)
	 * 
	 * activityDailyRecords is the space available to store the driver activity data 
	 * (data structure: CardActivityDailyRecord) for each calendar day where the card
	 *  has been used.
	 */
	private int activityPointerOldestDayRecord;
	private int activityPointerNewestRecord;
	private Vector<CardActivityDailyRecord> activityDailyRecords;
	private byte[] lastPartOfActivityDailyRecords;
	
	
	public CardDriverActivity(byte[] value){
		activityPointerOldestDayRecord = convertIntoUnsigned2ByteInt( arrayCopy(value, 0, 2));
		activityPointerNewestRecord = convertIntoUnsigned2ByteInt( arrayCopy(value, 2, 2));
		activityDailyRecords = new Vector<CardActivityDailyRecord>();
		
		// reorganize ringbuffer (=records) to start with a complete CardActivityDailyRecord
		// -> buff
		byte[] records = arrayCopy(value, 4, value.length - 4);
		byte[] buff = new byte[ records.length ];

		int lengthToEnd = (records.length - activityPointerOldestDayRecord);
		int shiftedActivityPointerNewestRecord = activityPointerNewestRecord - activityPointerOldestDayRecord;
		shiftedActivityPointerNewestRecord %= records.length;
		System.arraycopy(records, activityPointerOldestDayRecord, buff, 0, lengthToEnd);
		System.arraycopy(records, 0, buff, lengthToEnd, activityPointerOldestDayRecord);
		
		int beginning = 0;

		while(beginning <= shiftedActivityPointerNewestRecord){
//			System.out.println("-----");
//			System.out.println("beginning = " + beginning);
//			System.out.println("buff.length = " + buff.length);
			System.out.flush();
			System.err.flush();
			byte[] tmp = arrayCopy(buff, beginning, buff.length - beginning); 
//			System.out.println("tmp.length = " + tmp.length);
			CardActivityDailyRecord cadr = new CardActivityDailyRecord( tmp );
			beginning += cadr.getSize(); // next 
			activityDailyRecords.add(cadr);
		}
		lastPartOfActivityDailyRecords = arrayCopy(buff, beginning, buff.length - beginning); 

	}


	public int getActivityPointerNewestRecord() {
		return activityPointerNewestRecord;
	}


	public void setActivityPointerNewestRecord(int activityPointerNewestRecord) {
		this.activityPointerNewestRecord = activityPointerNewestRecord;
	}


	public int getActivityPointerOldestDayRecord() {
		return activityPointerOldestDayRecord;
	}


	public void setActivityPointerOldestDayRecord(int activityPointerOldestDayRecord) {
		this.activityPointerOldestDayRecord = activityPointerOldestDayRecord;
	}

	@Override
	public Element generateXMLElement(String name) {
		Element node = new Element(name);
		
		Element activityPointerOldestDayRecordElement = new Element("activityPointerOldestDayRecord");
		activityPointerOldestDayRecordElement.setText(Integer.toString(activityPointerOldestDayRecord));
		node.addContent(activityPointerOldestDayRecordElement);
		
		Element activityPointerNewestRecordElement = new Element("activityPointerNewestRecord");
		activityPointerNewestRecordElement.setText(Integer.toString(activityPointerNewestRecord));
		node.addContent(activityPointerNewestRecordElement);
		
		Iterator it = activityDailyRecords.iterator();
		Element cardActivityDailyRecordsElement = new Element("cardActivityDailyRecords");
		while(it.hasNext()){
			CardActivityDailyRecord cadr = (CardActivityDailyRecord) it.next();
			Element cadrElement = cadr.generateXMLElement("cardActivityDailyRecord");
			cardActivityDailyRecordsElement.addContent( cadrElement );
		}
		
		node.addContent( cardActivityDailyRecordsElement );
		
		Element lastPartOfActivityDailyRecordsElement = new Element("lastPartOfActivityDailyRecords");
		lastPartOfActivityDailyRecordsElement.setText( convertIntoHexString(lastPartOfActivityDailyRecords) );
		node.addContent(lastPartOfActivityDailyRecordsElement);
		
		return node;
	}
	
}
