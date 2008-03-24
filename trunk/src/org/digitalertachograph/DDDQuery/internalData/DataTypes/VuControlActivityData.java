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
 * Information, stored in a vehicle unit, related to controls performed using this VU.
 */
public class VuControlActivityData extends DataClass {
	/*
	 * VuControlActivityData ::= SEQUENCE {
	 * 	noOfControls INTEGER(0..20), 1 byte
	 * 	vuControlActivityRecords SET SIZE(noOfControls) OF VuControlActivityRecord, 0..620 bytes
	 * ---
	 * VuControlActivityRecord ::= SEQUENCE {
	 * 	controlType ControlType, 1 byte
	 * 	controlTime TimeReal, 4 bytes
	 * 	controlCardNumber FullCardNumber, 18 bytes 
	 * 	downloadPeriodBeginTime TimeReal, 4 bytes
	 * 	downloadPeriodEndTime TimeReal, 4 bytes
	 * }
	 */

	/**
	 * Size of structure in bytes.
	 * Only valid after instantiation of the VuControlActivityData object.
	 */
	public final int size;

	private short noOfControls;
	
	// create min. 20 vectors; will be automatically expanded at run time if required!
	private Vector<VuControlActivityRecord> vuControlActivityRecords = new Vector<VuControlActivityRecord>( 20 );


	/**
	 * Constructor for a VuControlActivityData object
	 * 
	 * @param	value	byte array of a VuControlActivityData structure
	 * 					whose data is used when the VuControlActivityData
	 * 					object is created.
	 */
	public VuControlActivityData( byte[] value ) {
		noOfControls = convertIntoUnsigned1ByteInt( value[ 0 ] );
		size = 1 + noOfControls * VuControlActivityRecord.size;

		if ( noOfControls != 0 ) {
			for ( int i = 0; i < noOfControls; i++ ) {
				byte[] record = arrayCopy( value, 1 + ( i * VuControlActivityRecord.size ), VuControlActivityRecord.size );
				VuControlActivityRecord vcar = new VuControlActivityRecord( record );
				vuControlActivityRecords.add( vcar );
			}
		}
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		Iterator<VuControlActivityRecord> iter = vuControlActivityRecords.iterator();
		while ( iter.hasNext() ) {
			VuControlActivityRecord vcar = (VuControlActivityRecord)iter.next();
			node.addContent( vcar.generateXMLElement( "vuControlActivityRecord" ) );
		}

		return node;
	}
}
