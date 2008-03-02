/*
    $Id:CardEventData.java 26 2008-02-25 22:28:27Z deetee $

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

		debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  no of events per type: " + noOfEventsPerType );	

		size = 6 * noOfEventsPerType * CardEventRecord.size;

		// loops are beautiful. cantaloop... funky, funky...
		for ( int j = 0; j < sequencesize; j++ ) {

			// max. NoOfEventsPerType = 12
			cardEventRecords.add( new Vector<CardEventRecord>( 12 ) );

			for ( int i = ( noOfEventsPerType * CardEventRecord.size * j ); i < ( noOfEventsPerType * CardEventRecord.size * ( j + 1 ) ); i += CardEventRecord.size ) {
				byte[] record = arrayCopy( value, i, CardEventRecord.size );
				CardEventRecord tmp = new CardEventRecord( record );

				if ( tmp.getEventBeginTime().getTimereal() != 0 ) {
					switch( j ) {
						case 0:
							debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  time overlap, event fault type: 0x%02x\n", tmp.getEventType().getEventFaultType() );
							break;

						case 1:
							debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  card insertion while driving, event fault type: 0x%02x\n", tmp.getEventType().getEventFaultType() );
							break;

						case 2:
							debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  last card session not correctly closed, event fault type: 0x%02x\n", tmp.getEventType().getEventFaultType() );
							break;

						case 3:
							debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  power supply interruption, event fault type: 0x%02x\n", tmp.getEventType().getEventFaultType() );
							break;

						case 4:
							debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  motion data error, event fault type: 0x%02x\n", tmp.getEventType().getEventFaultType() );
							break;

						case 5:
							debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  security breach attempt, event fault type: 0x%02x\n", tmp.getEventType().getEventFaultType() );
							break;

						default:
							break;
					}
				}

				cardEventRecords.get( j ).add( tmp );
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
