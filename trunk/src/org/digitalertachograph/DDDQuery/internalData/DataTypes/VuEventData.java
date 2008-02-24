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
 * Information, stored in a vehicle unit, related to events.
 */
public class VuEventData extends DataClass {
	/*
	 * VuEventData ::= SEQUENCE {
	 * 	noOfVuEvents INTEGER(0..255), 1 byte
	 * 	vuEventRecords SET SIZE (noOfVuEvents) OF VuEventRecord, 0..21165 bytes
	 * }
	 * ---
	 * VuEventRecord ::= SEQUENCE {
	 * 	eventType EventFaultType, 1 byte
	 * 	eventRecordPurpose EventFaultRecordPurpose, 1 byte
	 * 	eventBeginTime TimeReal, 4 bytes
	 * 	eventEndTime TimeReal, 4 bytes
	 * 	cardNumberDriverSlotBegin FullCardNumber, 18 bytes
	 * 	cardNumberCodriverSlotBegin FullCardNumber, 18 bytes
	 * 	cardNumberDriverSlotEnd FullCardNumber, 18 bytes
	 * 	cardNumberCodriverSlotEnd FullCardNumber, 18 bytes
	 * 	similarEventsNumber SimilarEventsNumber, 1 bytes
	 * }
	 */

	/**
	 * Size of structure in bytes.
	 * Only valid after instantiation of the VuEventData object.
	 */
	public final int size;

	private short noOfVuEvents;

	// create min. 20 vectors; will be automatically expanded at run time if required!
	private Vector<VuEventRecord> vuEventRecords = new Vector<VuEventRecord>( 20 );


	/**
	 * Constructor for a VuEventData object
	 * 
	 * @param	value	byte array of a VuEventData structure
	 * 					whose data is used when the VuEventData
	 * 					object is created.
	 */
	public VuEventData( byte[] value ) {
		noOfVuEvents = convertIntoUnsigned1ByteInt( value[ 0 ] );
		size = 1 + noOfVuEvents * VuEventRecord.size;

		if ( noOfVuEvents != 0 ) {
			for ( int i = 0; i < noOfVuEvents; i++ ) {
				byte[] record = arrayCopy( value, 1 + ( i * VuEventRecord.size ), VuEventRecord.size );
				VuEventRecord tmp = new VuEventRecord( record );
				vuEventRecords.add( tmp );
			}
		}
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		Iterator<VuEventRecord> iter = vuEventRecords.iterator();
		while ( iter.hasNext() ) {
			VuEventRecord ver = (VuEventRecord)iter.next();
			node.addContent( ver.generateXMLElement( "vuEventRecord" ) );
		}

		return node;
	}
}
