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

import java.util.Vector;
import java.util.Iterator;

import org.digitalertachograph.DDDQuery.DebugLogger;
import org.digitalertachograph.DDDQuery.internalData.DataClass;
import org.jdom.Element;

/**
 * Information, stored in a driver or workshop card, related to the events associated
 * with the card holder.
 */
public class CardEventData extends DataClass{
	/*
	 * CardEventData ::= SEQUENCE SIZE(6) OF {
	 * 	cardEventRecords SET SIZE(NoOfEventsPerType) OF CardEventRecord
	 * }
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
	 * sechs Folgen von Datensätzen (cardEventRecords[0..5]), die jeweils
	 * "NoOfEventsPerType"-fach CardEventRecords der "EventFaultTypes" oben enthalten
	 * (req. 204 & 223).
	 */

	/**
	 * Size of structure in bytes.
	 * Only valid after instantiation of the CardEventData object.
	 */
	public final int size;

	private static final int sequencesize = 6;
	private Vector<Vector<CardEventRecord>> cardEventRecords = new Vector<Vector<CardEventRecord>>( sequencesize );

	private DebugLogger debugLogger;


	/**
	 * Constructor for a CardEventData object
	 * 
	 * @param	value	byte array of a CardEventData structure
	 * 					whose data is used when the CardEventData
	 * 					object is created.
	 */
	public CardEventData( byte[] value, short noOfEventsPerType ) {
		debugLogger = new DebugLogger();

		debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] no of events per type: " + noOfEventsPerType );

		size = 6 * noOfEventsPerType * CardEventRecord.size;

		// loops are beautiful. cantaloop... funky, funky...
		for ( int j = 0; j < sequencesize; j++ ) {

			// max. NoOfEventsPerType = 12
			cardEventRecords.add( new Vector<CardEventRecord>( 12 ) );

			debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] ------------------------------------------------------------" );

			switch( j ) {
				case 0:
					debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] time overlap events:" );
					break;

				case 1:
					debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] card insertion while driving events:" );
					break;

				case 2:
					debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] last card session not correctly closed events:" );
					break;

				case 3:
					debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] power supply interruption events:" );
					break;

				case 4:
					debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] motion data error events:" );
					break;

				case 5:
					debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] security breach attempt events:" );
					break;

				default:
					break;
			}

			for ( int i = ( noOfEventsPerType * CardEventRecord.size * j ); i < ( noOfEventsPerType * CardEventRecord.size * ( j + 1 ) ); i += CardEventRecord.size ) {
				byte[] record = arrayCopy( value, i, CardEventRecord.size );
				CardEventRecord cer = new CardEventRecord( record );

				if ( cer.getEventBeginTime().getTimereal() != 0 ) {
					switch( j ) {
						case 0:
							debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] event fault type: 0x%02x, %s\n", cer.getEventType().getEventFaultType(), cer.getEventType().toString() );
							break;

						case 1:
							debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] event fault type: 0x%02x, %s\n", cer.getEventType().getEventFaultType(), cer.getEventType().toString() );
							break;

						case 2:
							debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] event fault type: 0x%02x, %s\n", cer.getEventType().getEventFaultType(), cer.getEventType().toString() );
							break;

						case 3:
							debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] event fault type: 0x%02x, %s\n", cer.getEventType().getEventFaultType(), cer.getEventType().toString() );
							break;

						case 4:
							debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] event fault type: 0x%02x, %s\n", cer.getEventType().getEventFaultType(), cer.getEventType().toString() );
							break;

						case 5:
							debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] event fault type: 0x%02x, %s\n", cer.getEventType().getEventFaultType(), cer.getEventType().toString() );
							break;

						default:
							break;
					}
				}

				cardEventRecords.get( j ).add( cer );
			}
		}
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		for ( int i = 0; i < sequencesize; i++ ) {
			Element recordsnode = new Element( "cardEventRecords" );
			node.addContent( recordsnode );

			Iterator<CardEventRecord> it = cardEventRecords.get( i ).iterator();
			while ( it.hasNext() ) {
				CardEventRecord cer = (CardEventRecord)it.next();
				recordsnode.addContent( cer.generateXMLElement( "cardEventRecord" ) );
			}
		}

		return node;
	}
}
