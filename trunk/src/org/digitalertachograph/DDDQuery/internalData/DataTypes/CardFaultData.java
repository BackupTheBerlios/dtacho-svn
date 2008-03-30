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
 * Information, stored in a driver or a workshop card, related to the faults associated
 * to the card holder.
 */
public class CardFaultData extends DataClass {
	/*
	 * CardFaultData ::= SEQUENCE SIZE(2) OF {
	 * 	 cardFaultRecords SET SIZE(NoOfFaultsPerType) OF CardFaultRecord
	 * }
	 * ---
	 * NoOfFaultsPerType ::= 12..24 for driver card
	 * NoOfFaultsPerType ::= 6      for workshop card
	 * ---
	 * zwei Folgen von Datensätzen, die jeweils "NoOfFaultsPerType"-fach CardFaultRecords enthalten.
	 * -die erste Folge enthält Störungsdatensätze des Kontrollgeräts
	 * -die zweite Folge enthält Störungsdatensätze der Karte
	 * (req. 207 & 233).
	 */

	/**
	 * Size of structure in bytes.
	 * Only valid after instantiation of the CardFaultData object.
	 */
	public final int size;

	private static final int sequencesize = 2;
	private Vector<Vector<CardFaultRecord>> cardFaultRecords = new Vector<Vector<CardFaultRecord>>( sequencesize );

	private DebugLogger debugLogger;


	/**
	 * Constructor for a CardFaultData object
	 * 
	 * @param	value	byte array of a CardFaultData structure
	 * 					whose data is used when the CardFaultData
	 * 					object is created.
	 */
	public CardFaultData( byte[] value, short noOfFaultsPerType ) {
		debugLogger = new DebugLogger();

		debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] no of faults per type: " + noOfFaultsPerType );

		int noOfValidFaultRecords = 0;

		// loops are beautiful. cantaloop... funky, funky...
		for ( int j = 0; j < sequencesize; j++ ) {

			// max. NoOfFaultsPerType = 24
			cardFaultRecords.add( new Vector<CardFaultRecord>( 24 ) );

			debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] ------------------------------------------------------------" );

			switch( j ) {
				case 0:
					debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] recording eq. faults:" );
					break;

				case 1:
					debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] card faults:" );
					break;

				default:
					break;
			}

			for ( int i = ( noOfFaultsPerType * CardFaultRecord.size * j ); i < ( noOfFaultsPerType * CardFaultRecord.size * ( j + 1 ) ); i += CardFaultRecord.size ) {
				byte[] record = arrayCopy( value, i, CardFaultRecord.size );
				CardFaultRecord cfr = new CardFaultRecord( record );

				// only add entries with non-default values, i.e. skip empty entries
				if ( cfr.getFaultBeginTime().getTimeReal() != 0 ) {
					switch( j ) {
						case 0:
							debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] event fault type: 0x%02x, %s\n", cfr.getFaultType().getEventFaultType(), cfr.getFaultType().toString() );
							break;

						case 1:
							debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] event fault type: 0x%02x, %s\n", cfr.getFaultType().getEventFaultType(), cfr.getFaultType().toString() );
							break;

						default:
							break;
					}

					cardFaultRecords.get( j ).add( cfr );

					noOfValidFaultRecords += 1;
				}
			}
		}

		size = noOfValidFaultRecords * CardFaultRecord.size;
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		for ( int i = 0; i < sequencesize; i++ ) {
			Element recordsnode = new Element( "cardFaultRecords" );
			node.addContent( recordsnode );

			Iterator<CardFaultRecord> it = cardFaultRecords.get( i ).iterator();
			while ( it.hasNext() ) {
				CardFaultRecord cfr = (CardFaultRecord)it.next();
				recordsnode.addContent( cfr.generateXMLElement( "cardFaultRecord" ) );
			}
		}

		return node;
	}
}
