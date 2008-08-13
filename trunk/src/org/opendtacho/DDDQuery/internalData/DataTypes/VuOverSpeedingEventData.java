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
 * Information, stored in a vehicle unit, related to over speeding events.
 */
public class VuOverSpeedingEventData extends DataClass {
	/*
	 * VuOverSpeedingEventData ::= SEQUENCE {
	 * 	noOfOverSpeedingEvents INTEGER(0..255), 1 byte
	 * 	vuOverSpeedingEventRecords SET SIZE (noOfOverSpeedingEvents) OF VuOverSpeedingEventRecord, 0..7905 bytes
	 * }
	 * ---
	 * VuOverSpeedingEventRecord ::= SEQUENCE {
	 * 	eventType EventFaultType, 1 byte
	 * 	eventRecordPurpose EventFaultRecordPurpose, 1 byte
	 * 	eventBeginTime TimeReal, 4 bytes
	 * 	eventEndTime TimeReal, 4 bytes
	 * 	maxSpeedValue SpeedMax, 1 byte
	 * 	averageSpeedValue SpeedAverage, 1 byte
	 * 	cardNumberDriverSlotBegin FullCardNumber, 18 bytes
	 * 	similarEventsNumber SimilarEventsNumber, 1 byte
	 * }
	 */

	/**
	 * Size of structure in bytes.
	 * Only valid after instantiation of the VuOverSpeedingEventData object.
	 */
	public final int size;

	private short noOfOverSpeedingEvents;

	// create min. 20 vectors; will be automatically expanded at run time if required!
	private Vector<VuOverSpeedingEventRecord> vuOverSpeedingEventRecords = new Vector<VuOverSpeedingEventRecord>( 20 );


	/**
	 * Constructor for a VuOverSpeedingEventData object
	 * 
	 * @param	value	byte array of a VuOverSpeedingEventData structure
	 * 					whose data is used when the VuOverSpeedingEventData
	 * 					object is created.
	 */
	public VuOverSpeedingEventData( byte[] value ) {
		noOfOverSpeedingEvents = convertIntoUnsigned1ByteInt( value[ 0 ] );
		size = 1 + noOfOverSpeedingEvents * VuOverSpeedingEventRecord.size;

		if ( noOfOverSpeedingEvents != 0 ) {
			for ( int i = 0; i < noOfOverSpeedingEvents; i++ ) {
				byte[] record = arrayCopy( value, 1 + ( i * VuOverSpeedingEventRecord.size ), VuOverSpeedingEventRecord.size );
				VuOverSpeedingEventRecord voser = new VuOverSpeedingEventRecord( record );
				vuOverSpeedingEventRecords.add( voser );
			}
		}
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		Iterator<VuOverSpeedingEventRecord> iter = vuOverSpeedingEventRecords.iterator();
		while ( iter.hasNext() ) {
			VuOverSpeedingEventRecord voser = (VuOverSpeedingEventRecord)iter.next();
			node.addContent( voser.generateXMLElement( "vuOverSpeedingEventRecord" ) );
		}

		return node;
	}
}
