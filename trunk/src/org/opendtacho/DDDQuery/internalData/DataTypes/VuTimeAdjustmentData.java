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

package org.opendtacho.DDDQuery.internalData.DataTypes;

import java.util.Iterator;
import java.util.Vector;

import org.jdom.Element;
import org.opendtacho.DDDQuery.internalData.DataClass;

/**
 * Information, stored in a vehicle unit, related to time adjustments performed outside the
 * frame of regular calibration.
 */
public class VuTimeAdjustmentData extends DataClass {
	/*
	 * VuTimeAdjustmentData ::= SEQUENCE {
	 * 	noOfVuTimeAdjRecords INTEGER(0..6), 1 byte
	 * 	vuTimeAdjustmentRecords SET SIZE (noOfVuTimeAdjRecords) OF VuTimeAdjustmentRecord, 0..588 bytes
	 * }
	 * ---
	 * VuTimeAdjustmentRecord ::= SEQUENCE {
	 * 	oldTimeValue TimeReal, 4 bytes
	 * 	newTimeValue TimeReal, 4 bytes
	 * 	workshopName Name, 36 bytes
	 * 	workshopAddress Address, 36 bytes
	 * 	workshopCardNumber FullCardNumber, 18 bytes
	 * }
	 */

	/**
	 * Size of structure in bytes.
	 * Only valid after instantiation of the VuTimeAdjustmentData object.
	 */
	public final int size;

	private short noOfVuTimeAdjRecords;

	// create min. 6 vectors; will be automatically expanded at run time if required!
	private Vector<VuTimeAdjustmentRecord> vuTimeAdjustmentRecords = new Vector<VuTimeAdjustmentRecord>( 6 );


	/**
	 * Constructor for a VuTimeAdjustmentData object
	 * 
	 * @param	value	byte array of a VuTimeAdjustmentData structure
	 * 					whose data is used when the VuTimeAdjustmentData
	 * 					object is created.
	 */
	public VuTimeAdjustmentData( byte[] value ) {
		noOfVuTimeAdjRecords = convertIntoUnsigned1ByteInt( value[ 0 ] );
		size = 1 + noOfVuTimeAdjRecords * VuTimeAdjustmentRecord.size;

		if ( noOfVuTimeAdjRecords != 0 ) {
			for ( int i = 0; i < noOfVuTimeAdjRecords; i++ ) {
				byte[] record = arrayCopy( value, 1 + ( i * VuTimeAdjustmentRecord.size ), VuTimeAdjustmentRecord.size );
				VuTimeAdjustmentRecord vtar = new VuTimeAdjustmentRecord( record );
				vuTimeAdjustmentRecords.add( vtar );
			}
		}
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		Iterator<VuTimeAdjustmentRecord> iter = vuTimeAdjustmentRecords.iterator();
		while ( iter.hasNext() ) {
			VuTimeAdjustmentRecord vtar = (VuTimeAdjustmentRecord)iter.next();
			node.addContent( vtar.generateXMLElement( "vuTimeAdjustmentRecord" ) );
		}

		return node;
	}
}
