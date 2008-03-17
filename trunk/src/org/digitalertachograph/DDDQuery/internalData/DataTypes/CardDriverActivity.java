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

import java.util.Iterator;
import java.util.Vector;

import org.digitalertachograph.DDDQuery.DebugLogger;
import org.digitalertachograph.DDDQuery.internalData.DataClass;
import org.jdom.Element;

/**
 * Information, stored in a driver or a workshop card, related to the activities
 * of the driver.
 */
public class CardDriverActivity extends DataClass {
	/*
	 * CardDriverActivity ::= SEQUENCE {
	 * 	activityPointerOldestDayRecord INTEGER(0..CardActivityLengthRange-1), 2 bytes
	 * 	activityPointerNewestRecord INTEGER(0..CardActivityLengthRange-1), 2 bytes
	 * 	activityDailyRecords OCTET STRING (SIZE(CardActivityLengthRange)), 5544..13776 bytes
	 * }
	 * ---
	 * CardActivityLengthRange ::= INTEGER(0..2^16-1)
	 * min.:  5544 bytes (28 days * 93 activity changes)
	 * max.: 13776 bytes (28 days * 240 activity changes)
	 * ---
	 * activityDailyRecords is the space available to store the driver activity data 
	 * (data structure: CardActivityDailyRecord) for each calendar day where the card
	 * has been used.
	 */

	/**
	 * Size of structure in bytes.
	 * Only valid after instantiation of the CardEventData object.
	 */
	public final int size;

	private int activityPointerOldestDayRecord;
	private int activityPointerNewestRecord;
	private Vector<CardActivityDailyRecord> activityDailyRecords;

	private DebugLogger debugLogger;


	/**
	 * Constructor for a CardDriverActivity object
	 * 
	 * @param	value						byte array of a CardDriverActivity structure
	 * 										whose data is used when the CardDriverActivity
	 * 										object is created.
	 * @param	activityStructureLength		length of activity structure
	 */
	public CardDriverActivity( byte[] value, int activityStructureLength ) {
		debugLogger = new DebugLogger();

		size = 2 + 2 + activityStructureLength;

		activityPointerOldestDayRecord = convertIntoUnsigned2ByteInt( arrayCopy( value, 0, 2 ) ); // = first CardActivityDailyRecord
		activityPointerNewestRecord = convertIntoUnsigned2ByteInt( arrayCopy( value, 2, 2 ) ); // = last CardActivityDailyRecord
		activityDailyRecords = new Vector<CardActivityDailyRecord>();

		debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, " activities offsets: %d, %d\n", activityPointerOldestDayRecord, activityPointerNewestRecord );

		// reorganize ringbuffer (=records)
		// copy the (shifted) CardActivityDailyRecord array to a new array where the
		// records can be accessed linearly from the oldest record (at the beginning
		// of the array) to the newest record.

		// length of destination CardActivityDailyRecord array (records) is CardDriverActivity length
		// without offsets for oldest/newest CardActivityDailyRecord (2 * 2 bytes)
		byte[] records = new byte[ activityStructureLength ];

		int lengthToEnd = records.length - activityPointerOldestDayRecord;
		System.arraycopy( value, 4 + activityPointerOldestDayRecord, records, 0, lengthToEnd );

		if ( activityPointerOldestDayRecord != 0 ) {
			System.arraycopy( value, 4, records, lengthToEnd, activityPointerOldestDayRecord );
		}

		debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " records.length: " + records.length );

		int activityPointerLastRecordOffset;

		// offset of newest/last CardActivityDailyRecord
		if ( activityPointerNewestRecord >= activityPointerOldestDayRecord ) {
			activityPointerLastRecordOffset = activityPointerNewestRecord - activityPointerOldestDayRecord;
		}
		else {
			activityPointerLastRecordOffset = records.length - activityPointerOldestDayRecord + activityPointerNewestRecord;
		}

		// process the CardActivityDailyRecord array
		int cardActivityDailyRecordsOffset = 0;

		int cadrActivityPreviousRecordLength = 0;
		int cadrActivityRecordLength = 0;

		int cadrIntegrityCheckActivityPreviousRecordLength = 0;

		while ( cardActivityDailyRecordsOffset <= activityPointerLastRecordOffset ) {
			CardActivityDailyRecord cadr = new CardActivityDailyRecord( arrayCopy( records, cardActivityDailyRecordsOffset, convertIntoUnsigned2ByteInt( arrayCopy( records, cardActivityDailyRecordsOffset + 2, 2 ) ) ) );

			cadrActivityPreviousRecordLength = cadr.getActivityPreviousRecordLength().getCardActivityLengthRange();
			cadrActivityRecordLength = cadr.getActivityRecordLength().getCardActivityLengthRange();

			// break loop when empty CardActivityDailyRecord is found
			// = when default values for structure is used
			if ( cadrActivityRecordLength == 0 ) {
				break;
			}

			// do some integrity checks
			if ( cardActivityDailyRecordsOffset == 0 ) {
				if ( cadrActivityPreviousRecordLength == 0 ) {
					debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, "   [INFO_EXT] this is the first record" );
				}
				else {
					// indent output if log level >= LOGLEVEL_INFO_EXTENDED
					if ( DebugLogger.logLevel >= DebugLogger.LOGLEVEL_INFO_EXTENDED ) {
						debugLogger.print( DebugLogger.LOGLEVEL_ERROR, "   " );
					}
					debugLogger.println( DebugLogger.LOGLEVEL_ERROR, "[ERROR] this should be the first record but previous length is not 0(?!)" );
				}
			}
			else {
				if ( cadrActivityPreviousRecordLength == cadrIntegrityCheckActivityPreviousRecordLength ) {
					debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, "   [INFO_EXT] integrity check ok: ActivityPreviousRecordLength matches" );
				}
				else {
					// indent output if log level >= LOGLEVEL_INFO_EXTENDED
					if ( DebugLogger.logLevel > DebugLogger.LOGLEVEL_INFO_EXTENDED ) {
						debugLogger.print( DebugLogger.LOGLEVEL_ERROR, "   " );
					}
					debugLogger.println( DebugLogger.LOGLEVEL_ERROR, "[ERROR] integrity check failed: ActivityPreviousRecordLength does NOT match" );
				}
			}

			cardActivityDailyRecordsOffset += cadrActivityRecordLength; // next CardActivityDailyRecord

			cadrIntegrityCheckActivityPreviousRecordLength = cadrActivityRecordLength; // save record length for integrity check

			activityDailyRecords.add( cadr );
		}
	}

	/**
	 * Returns the begin of the storage place (number of bytes from the beginning
	 * of the activityDailyRecords string) of the most recent complete day record
	 * of a CardDriverActivity object.
	 * 
	 * @return	the begin of the storage place of the most recent complete day record
	 * 			of the CardDriverActivity object
	 */
	public int getActivityPointerOldestDayRecord() {
		return activityPointerOldestDayRecord;
	}

	/**
	 * Returns the begin of the storage place (number of bytes from the beginning
	 * of the activityDailyRecords string) of the oldest complete day record
	 * of a CardDriverActivity object.
	 * 
	 * @return	the begin of the storage place of the oldest complete day record
	 * 			of the CardDriverActivity object
	 */
	public int getActivityPointerNewestRecord() {
		return activityPointerNewestRecord;
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		Element cardActivityDailyRecordsElement = new Element( "cardActivityDailyRecords" );
		node.addContent( cardActivityDailyRecordsElement );

		Iterator<CardActivityDailyRecord> it = activityDailyRecords.iterator();
		while ( it.hasNext() ) {
			CardActivityDailyRecord cadr = (CardActivityDailyRecord)it.next();
			cardActivityDailyRecordsElement.addContent( cadr.generateXMLElement( "cardActivityDailyRecord" ) );
		}

		return node;
	}
}
