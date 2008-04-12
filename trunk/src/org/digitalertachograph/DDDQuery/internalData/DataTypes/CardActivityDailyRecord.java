/*
    $Id$

    Copyright (C) 2007-2008, Martin Barth, Gerald Schnabel

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

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.TimeZone;
import java.util.Vector;

import org.digitalertachograph.DDDQuery.DebugLogger;
import org.digitalertachograph.DDDQuery.internalData.DataClass;
import org.jdom.Element;

/**
 * Information, stored in a card, related to the driver activities for a
 * particular calendar day.
 */
public class CardActivityDailyRecord extends DataClass {
	/*
	 * CardActivityDailyRecord ::= SEQUENCE {
	 * 	activityPreviousRecordLength		INTEGER(0..CardActivityLengthRange), 2 bytes
	 *  activityRecordLength				INTEGER(0..CardActivityLengthRange), 2 bytes
	 *  activityRecordDate					TimeReal, 4 bytes
	 *  activityDailyPresenceCounter		DailyPresenceCounter, 2 bytes
	 *  activityDayDistance					Distance, 2 bytes
	 *  activityChangeInfo					SET SIZE(1..1440) OF ActivityChangeInfo, 2...2880 bytes
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

	/**
	 * Size of structure in bytes.
	 * Only valid after instantiation of the CardActivityDailyRecord object.
	 */
	public final int size;

	private CardActivityLengthRange activityPreviousRecordLength;
	private CardActivityLengthRange activityRecordLength;
	private TimeReal activityRecordDate;
	private DailyPresenceCounter activityDailyPresenceCounter;
	private Distance activityDayDistance;
	private Vector<ActivityChangeInfo> activityChangeInfo;

	//private boolean complete = false;

	private DebugLogger debugLogger;


	/**
	 * Constructor for a CardActivityDailyRecord object
	 * 
	 * @param	value	byte array of a CardActivityDailyRecord structure
	 * 					whose data is used when the CardActivityDailyRecord
	 * 					object is created
	 */
	public CardActivityDailyRecord( byte[] value ) {
		debugLogger = new DebugLogger();

		// if we get so less bytes we have just a part of an old CardActivityDailyRecord.
		//if ( value.length <= 12 ) {
		//	size = 0;
		//	return;
		//}

		debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] ------------------------------------------------------------" );

		this.activityPreviousRecordLength = new CardActivityLengthRange( arrayCopy( value, 0, 2 ) );
		this.activityRecordLength = new CardActivityLengthRange( arrayCopy( value, 2, 2 ) );
		this.activityRecordDate = new TimeReal( arrayCopy( value, 4, 4 ) );
		this.activityDailyPresenceCounter = new DailyPresenceCounter( arrayCopy( value, 8, 2 ) );
		this.activityDayDistance = new Distance( arrayCopy( value, 10, 2 ) );
		this.activityChangeInfo = new Vector<ActivityChangeInfo>();

		debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  %d activity change(s) in this record\n", ( activityRecordLength.getCardActivityLengthRange() - 12 ) / ActivityChangeInfo.size );

		for ( int length = 12; length < activityRecordLength.getCardActivityLengthRange(); length += ActivityChangeInfo.size ) {
			ActivityChangeInfo aci = new ActivityChangeInfo( arrayCopy( value, length, ActivityChangeInfo.size ) );
			activityChangeInfo.add( aci );

			SimpleDateFormat activityChangeInfoSimpleDate = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss z" ); 
			activityChangeInfoSimpleDate.setTimeZone( TimeZone.getTimeZone( "UTC" ) );

			long activityChangeInfoTimeStamp = this.activityRecordDate.getTimeReal() * 1000 + aci.getTime() * 60 * 1000;

			Date activityChangeInfoDate = new Date( activityChangeInfoTimeStamp );

			debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "   %s, value %02x%02x, activity %02x - %s\n", activityChangeInfoSimpleDate.format( activityChangeInfoDate ), aci.getValue()[ 0 ], aci.getValue()[ 1 ], aci.getActivity(), aci.getActivityString() );
		}

		//this.complete = true;

		size = 12 + activityRecordLength.getCardActivityLengthRange();
	}

	/**
	 * Indicates if the CardActivityDailyRecord object is a complete record.
	 * 
	 * @return			true if the CardActivityDailyRecord object is a complete record
	 */
	//public boolean isComplete() {
	//	return complete;
	//}

	/**
	 * Returns the length of the previous CardActivityDailyRecord
	 * 
	 * @return			length (number of bytes) of the previous CardActivityDailyRecord
	 */
	public CardActivityLengthRange getActivityPreviousRecordLength() {
		return activityPreviousRecordLength;
	}

	/**
	 * Returns the length of this CardActivityDailyRecord
	 * 
	 * @return			length (number of bytes) of this CardActivityDailyRecord
	 */
	public CardActivityLengthRange getActivityRecordLength() {
		return activityRecordLength;
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		node.addContent( activityPreviousRecordLength.generateXMLElement( "activityPreviousRecordLength" ) );
		node.addContent( activityRecordLength.generateXMLElement( "activityRecordLength" ) );
		node.addContent( activityRecordDate.generateXMLElement( "activityRecordDate" ) );
		node.addContent( activityDailyPresenceCounter.generateXMLElement( "activityDailyPresenceCounter" ) );
		node.addContent( activityDayDistance.generateXMLElement( "activityDayDistance" ) );

		Iterator<ActivityChangeInfo> it = activityChangeInfo.iterator();
		Element activityChangeInfoElement = new Element( "activityChangeInfos" );
		while ( it.hasNext() ) {
			ActivityChangeInfo aci = (ActivityChangeInfo)it.next();
			activityChangeInfoElement.addContent( aci.generateXMLElement( "activityChangeInfo" ) );
		}
		node.addContent( activityChangeInfoElement );

		return node;
	}
}
