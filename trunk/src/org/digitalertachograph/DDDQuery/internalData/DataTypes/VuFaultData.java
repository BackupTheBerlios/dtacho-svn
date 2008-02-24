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
 * Information, stored in a vehicle unit, related to faults.
 */
public class VuFaultData extends DataClass {
	/*
	 * VuFaultData ::= SEQUENCE {
	 * 	noOfVuFaults INTEGER(0..255), 1 byte
	 * 	vuFaultRecords SET SIZE (noOfVuFaults) OF VuFaultRecord, 0..20910 bytes
	 * }
	 * ---
	 * VuFaultRecord ::= SEQUENCE {
	 * 	faultType EventFaultType, 1 byte
	 * 	faultRecordPurpose EventFaultRecordPurpose, 1 byte
	 * 	faultBeginTime TimeReal, 4 bytes
	 * 	faultEndTime TimeReal, 4 bytes
	 * 	cardNumberDriverSlotBegin FullCardNumber, 18 bytes
	 * 	cardNumberCodriverSlotBegin FullCardNumber, 18 bytes
	 * 	cardNumberDriverSlotEnd FullCardNumber, 18 bytes
	 * 	cardNumberCodriverSlotEnd FullCardNumber, 18 bytes
	 * }
	 */

	/**
	 * Size of structure in bytes.
	 * Only valid after instantiation of the VuFaultData object.
	 */
	public final int size;

	private short noOfVuFaults;

	// create min. 20 vectors; will be automatically expanded at run time if required!
	private Vector<VuFaultRecord> vuFaultRecords = new Vector<VuFaultRecord>( 20 );


	/**
	 * Constructor for a VuFaultData object
	 * 
	 * @param	value	byte array of a VuFaultData structure
	 * 					whose data is used when the VuFaultData
	 * 					object is created.
	 */
	public VuFaultData( byte[] value ) {
		noOfVuFaults = convertIntoUnsigned1ByteInt( value[ 0 ] );
		size = 1 + noOfVuFaults * VuFaultRecord.size;

		if ( noOfVuFaults != 0 ) {
			for ( int i = 0; i < noOfVuFaults; i++ ) {
				byte[] record = arrayCopy( value, 1 + ( i * VuFaultRecord.size ), VuFaultRecord.size );
				VuFaultRecord tmp = new VuFaultRecord( record );
				vuFaultRecords.add( tmp );
			}
		}
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		Iterator<VuFaultRecord> iter = vuFaultRecords.iterator();
		while ( iter.hasNext() ) {
			VuFaultRecord vfr = (VuFaultRecord)iter.next();
			node.addContent( vfr.generateXMLElement( "vuFaultRecord" ) );
		}

		return node;
	}
}
