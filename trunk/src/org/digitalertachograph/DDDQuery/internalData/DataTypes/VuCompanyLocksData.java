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
 * Information, stored in a vehicle unit, related to company locks.
 */
public class VuCompanyLocksData extends DataClass {
	/*
	 * VuCompanyLocksData ::= SEQUENCE {
	 * 	noOfLocks INTEGER(0..20), 1 byte
	 * 	vuCompanyLocksRecords SET SIZE(noOfLocks) OF VuCompanyLocksRecord, 0..1960 bytes
	 * ---
	 * VuCompanyLocksRecord ::= SEQUENCE {
	 * 	lockInTime TimeReal, 4 bytes
	 * 	lockOutTime TimeReal, 4 bytes
	 * 	companyName Name, 36 bytes
	 * 	companyAddress Address, 36 bytes
	 * 	companyCardNumber FullCardNumber, 18 bytes
	 * }
	 */

	/**
	 * Size of structure in bytes.
	 * Only valid after instantiation of the VuCompanyLocksData object.
	 */
	public final int size;

	private short noOfLocks;
	
	// create min. 20 vectors; will be automatically expanded at run time if required!
	private Vector<VuCompanyLocksRecord> vuCompanyLocksRecords = new Vector<VuCompanyLocksRecord>( 20 );


	/**
	 * Constructor for a VuCompanyLocksData object
	 * 
	 * @param	value	byte array of a VuCompanyLocksData structure
	 * 					whose data is used when the VuCompanyLocksData
	 * 					object is created.
	 */
	public VuCompanyLocksData( byte[] value ) {
		noOfLocks = convertIntoUnsigned1ByteInt( value[ 0 ] );
		size = 1 + noOfLocks * VuCompanyLocksRecord.size;

		if ( noOfLocks != 0 ) {
			for ( int i = 0; i < noOfLocks; i++ ) {
				byte[] record = arrayCopy( value, 1 + ( i * VuCompanyLocksRecord.size ), VuCompanyLocksRecord.size );
				VuCompanyLocksRecord vclr = new VuCompanyLocksRecord( record );
				vuCompanyLocksRecords.add( vclr );
			}
		}
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		Iterator<VuCompanyLocksRecord> iter = vuCompanyLocksRecords.iterator();
		while ( iter.hasNext() ) {
			VuCompanyLocksRecord vclr = (VuCompanyLocksRecord)iter.next();
			node.addContent( vclr.generateXMLElement( "vuCompanyLocksRecord" ) );
		}

		return node;
	}
}
