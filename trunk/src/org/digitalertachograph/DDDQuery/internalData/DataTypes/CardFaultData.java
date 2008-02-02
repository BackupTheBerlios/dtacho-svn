/*   Copyright (C) 2007-2008, Martin Barth, Gerald Schnabel

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
	 * zwei Folgen von Datens�tzen, die jeweils "NoOfFaultsPerType"-fach CardFaultRecords enthalten.
	 * -die erste Folge enth�lt St�rungsdatens�tze des Kontrollger�ts
	 * -die zweite Folge enth�lt St�rungsdatens�tze der Karte
	 */

	private static final int sequencesize = 2;
	private Vector<Vector<CardFaultRecord>> cardFaultRecords = new Vector<Vector<CardFaultRecord>>( sequencesize );

	
	/**
	 * Constructor for a CardFaultData object
	 * 
	 * @param	value	byte array of a CardFaultData structure
	 * 					whose data is used when the CardFaultData
	 * 					object is created.
	 */
	public CardFaultData( byte[] value, short nooffaultspertype ) {
		System.out.println("  nooffaultspertype: " + nooffaultspertype );	

		// loops are beautiful. cantaloop... funky, funky...
		for ( int j = 0; j < sequencesize; j++ ) {

			cardFaultRecords.add( new Vector<CardFaultRecord>( 24 ) );

			for ( int i = ( nooffaultspertype * 24 * j ); i < ( nooffaultspertype * 24 * ( j + 1 ) ); i += 24 ) {
				byte[] record = arrayCopy( value, i, 24 );
				CardFaultRecord tmp = new CardFaultRecord( record );

				if ( tmp.getFaultBeginTime().getTimereal() != 0 ) {
					switch( j ) {
						case 0:
							System.out.printf( "  recording eq. fault, event fault type: 0x%02x\n", tmp.getFaultType().getEventFaultType() );
							break;

						case 1:
							System.out.printf( "  card fault, event fault type: 0x%02x\n", tmp.getFaultType().getEventFaultType() );
							break;

						default:
							break;
					}
				}

				cardFaultRecords.get( j ).add( tmp );
			}
		}
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
				Element cfrElement = cfr.generateXMLElement( "cardFaultRecord" );
				recordsnode.addContent( cfrElement );
			}
		}

		return node;
	}
}
