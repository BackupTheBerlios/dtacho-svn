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
 * Information, stored in a vehicle unit, related to specific conditions.
 */
public class VuSpecificConditionData extends DataClass {
	/*
	 * VuSpecificConditionData ::= SEQUENCE {
	 * 	noOfSpecificConditionRecords INTEGER(0..2^16-1), 2 bytes
	 *  specificConditionRecords SET SIZE(noOfSpecificConditionRecords) OF SpecificConditionRecord, 0..327675 bytes
	 * }
	 * ---
	 * SpecificConditionRecord ::= SEQUENCE {
	 * 	entryTime TimeReal, 4 bytes
	 * 	SpecificConditionType, 1 byte
	 * }
	 */

	/**
	 * Size of structure in bytes.
	 * Only valid after instantiation of the VuSpecificConditionData object.
	 */
	public final int size;

	private int noOfSpecificConditionRecords;
	
	// create min. 20 vectors; will be automatically expanded at run time if required!
	private Vector<SpecificConditionRecord> specificConditionRecords = new Vector<SpecificConditionRecord>( 20 );


	/**
	 * Constructor for a VuSpecificConditionData object
	 * 
	 * @param	value	byte array of a VuSpecificConditionData structure
	 * 					whose data is used when the VuSpecificConditionData
	 * 					object is created.
	 */
	public VuSpecificConditionData( byte[] value ) {
		noOfSpecificConditionRecords = convertIntoUnsigned2ByteInt( arrayCopy( value, 0, 2 ) );
		size = 2 + noOfSpecificConditionRecords * SpecificConditionRecord.size;

		if ( noOfSpecificConditionRecords != 0 ) {
			for ( int i = 0; i < noOfSpecificConditionRecords; i++ ) {
				byte[] record = arrayCopy( value, 2 + ( i * SpecificConditionRecord.size ), SpecificConditionRecord.size );
				SpecificConditionRecord scr = new SpecificConditionRecord( record );
				specificConditionRecords.add( scr );
			}
		}
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		Iterator<SpecificConditionRecord> iter = specificConditionRecords.iterator();
		while ( iter.hasNext() ) {
			SpecificConditionRecord scr = (SpecificConditionRecord)iter.next();
			node.addContent( scr.generateXMLElement( "specificConditionRecord" ) );
		}

		return node;
	}
}
