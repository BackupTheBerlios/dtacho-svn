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
 * Information, stored in a vehicle unit, related to the detailed speed of the vehicle.
 */
public class VuDetailedSpeedData extends DataClass {
	/*
	 * VuDetailedSpeedData ::= SEQUENCE {
	 * 	noOfSpeedBlocks INTEGER(0..2^16-1), 2 bytes
	 * 	vuDetailedSpeedBlocks SET SIZE (noOfSpeedBlocks) OF VuDetailedSpeedBlock, 0..4194240 bytes
	 * }
	 * ---
	 * VuDetailedSpeedBlock ::= SEQUENCE {
	 * 	speedBlockBeginDate TimeReal, 4 bytes
	 * 	speedsPerSecond SEQUENCE SIZE (60) OF Speed, 60 bytes
	 * }
	 */

	/**
	 * Size of structure in bytes.
	 * Only valid after instantiation of the VuDetailedSpeedData object.
	 */
	public final int size;

	private int noOfSpeedBlocks;

	// create min. 1440 vectors; will be automatically expanded at run time if required!
	private Vector<VuDetailedSpeedBlock> vuDetailedSpeedBlocks = new Vector<VuDetailedSpeedBlock>( 1440 );


	/**
	 * Constructor for a VuDetailedSpeedData object
	 * 
	 * @param	value	byte array of a VuDetailedSpeedData structure
	 * 					whose data is used when the VuDetailedSpeedData
	 * 					object is created.
	 */
	public VuDetailedSpeedData( byte[] value ) {
		noOfSpeedBlocks = convertIntoUnsigned2ByteInt( arrayCopy( value, 0, 2 ) );
		size = 2 + noOfSpeedBlocks * VuDetailedSpeedBlock.size;

		if ( noOfSpeedBlocks != 0 ) {
			for ( int i = 0; i < noOfSpeedBlocks; i++ ) {
				byte[] record = arrayCopy( value, 2 + ( i * VuDetailedSpeedBlock.size ), VuDetailedSpeedBlock.size );
				VuDetailedSpeedBlock tmp = new VuDetailedSpeedBlock( record );
				vuDetailedSpeedBlocks.add( tmp );
			}
		}
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		Iterator<VuDetailedSpeedBlock> iter = vuDetailedSpeedBlocks.iterator();
		while ( iter.hasNext() ) {
			VuDetailedSpeedBlock vdsb = (VuDetailedSpeedBlock)iter.next();
			node.addContent( vdsb.generateXMLElement( "vuDetailedSpeedBlock" ) );
		}

		return node;
	}
}
