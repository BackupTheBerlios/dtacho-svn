/*
    $Id$

    Copyright (C) 2008, Gerald Schnabel

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
 * Information, stored in a vehicle unit, related to places where drivers begin
 * or end a daily work periods.
 */
public class VuPlaceDailyWorkPeriodData extends DataClass {
	/*
	 * VuPlaceDailyWorkPeriodData ::= SEQUENCE {
	 * 	noOfPlaceRecords INTEGER SIZE(0..255), 1 byte
	 *  vuPlaceDailyWorkPeriodRecords SET SIZE(noOfPlaceRecords) OF VuPlaceDailyWorkPeriodRecord, 0..7140 bytes
	 * }
	 * ---
	 * VuPlaceDailyWorkPeriodRecord ::= SEQUENCE {
	 * 	fullCardNumber FullCardNumber, 18 bytes
	 * 	placeRecord PlaceRecord, 10 bytes
	 * }
	 */

	/**
	 * Size of structure in bytes.
	 * Only valid after instantiation of the VuPlaceDailyWorkPeriodData object.
	 */
	public final int size;

	private short noOfPlaceRecords;

	// create min. 20 vectors; will be automatically expanded at run time if required!
	private Vector<VuPlaceDailyWorkPeriodRecord> vuPlaceDailyWorkPeriodRecords = new Vector<VuPlaceDailyWorkPeriodRecord>( 20 );


	/**
	 * Constructor for a VuPlaceDailyWorkPeriodData object
	 * 
	 * @param	value	byte array of a VuPlaceDailyWorkPeriodData structure
	 * 					whose data is used when the VuPlaceDailyWorkPeriodData
	 * 					object is created.
	 */
	public VuPlaceDailyWorkPeriodData( byte[] value ) {
		noOfPlaceRecords = convertIntoUnsigned1ByteInt( value[ 0 ] );
		size = 1 + noOfPlaceRecords * VuPlaceDailyWorkPeriodRecord.size;

		if ( noOfPlaceRecords != 0 ) {
			for ( int i = 0; i < noOfPlaceRecords; i++ ) {
				byte[] record = arrayCopy( value, 1 + ( i * VuPlaceDailyWorkPeriodRecord.size ), VuPlaceDailyWorkPeriodRecord.size );
				VuPlaceDailyWorkPeriodRecord vpdwpr = new VuPlaceDailyWorkPeriodRecord( record );
				vuPlaceDailyWorkPeriodRecords.add( vpdwpr );
			}
		}
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		Iterator<VuPlaceDailyWorkPeriodRecord> iter = vuPlaceDailyWorkPeriodRecords.iterator();
		while ( iter.hasNext() ) {
			VuPlaceDailyWorkPeriodRecord vpdwpr = (VuPlaceDailyWorkPeriodRecord)iter.next();
			node.addContent( vpdwpr.generateXMLElement( "vuPlaceDailyWorkPeriodRecord" ) );
		}

		return node;
	}
}
