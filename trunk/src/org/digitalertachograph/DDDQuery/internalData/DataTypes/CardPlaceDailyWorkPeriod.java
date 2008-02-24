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

import org.digitalertachograph.DDDQuery.internalData.DataClass;
import org.jdom.Element;

/**
 * Information, stored in a driver or a workshop card, related to the places where
 * daily work periods begin and/or end.
 */
public class CardPlaceDailyWorkPeriod extends DataClass {
	/*
	 * CardPlaceDailyWorkPeriod ::= SEQUENCE {
	 * 	placePointerNewestRecord INTEGER(0..NoOfCardPlaceRecords-1), 1 byte
	 * 	placeRecords SET SIZE(NoOfCardPlaceRecords) OF PlaceRecord, 840..1120 bytes
	 * }
	 * ---
	 * NoOfCardPlaceRecords ::= INTEGER(0..255), 1 byte
	 * min.:  84
	 * max.: 112
	 */

	/**
	 * Size of structure in bytes.
	 * Only valid after instantiation of the CardPlaceDailyWorkPeriod object.
	 */
	public final int size;

	// create min. 84 vectors; will be automatically expanded at run time if required!
	private Vector<PlaceRecord> placeRecords = new Vector<PlaceRecord>( 84 );


	/**
	 * Constructor for a CardPlaceDailyWorkPeriod object
	 * 
	 * @param	value	byte array of a CardPlaceDailyWorkPeriod structure
	 * 					whose data is used when the CardPlaceDailyWorkPeriod
	 * 					object is created.
	 */
	public CardPlaceDailyWorkPeriod( byte[] value, short noOfCardPlaceRecords ) {
		for ( int i = 0; i < noOfCardPlaceRecords; i += 1 ) {
			byte[] record = arrayCopy( value, 1 + ( i * PlaceRecord.size ), PlaceRecord.size );

			PlaceRecord tmp = new PlaceRecord( record );

			placeRecords.add( tmp );
		}

		size = 1 + noOfCardPlaceRecords * PlaceRecord.size;
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		Element recordsnode = new Element( "placeRecords" );
		node.addContent( recordsnode );

		Iterator<PlaceRecord> it = placeRecords.iterator();
		while ( it.hasNext() ) {
			PlaceRecord pr = (PlaceRecord)it.next();
			recordsnode.addContent( pr.generateXMLElement( "placeRecord" ) );
		}

		return node;
	}
}
