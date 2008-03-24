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
 * Information, stored in a vehicle unit, related to insertion and withdrawal cycles
 * of driver cards or of workshop cards in the vehicle unit.
 */
public class VuCardIWData extends DataClass {
	/*
	 * VuCardIWData ::= SEQUENCE {
	 * 	noOfIWRecords INTEGER SIZE(0..2^16-1), 2 bytes
	 *  vuCardIWRecords SET SIZE(noOfIWRecords) OF VuCardIWRecord, 0..8454015 bytes
	 * }
	 * ---
	 * VuCardIWRecord ::= SEQUENCE {
	 *	cardHolderName HolderName, 72 bytes
	 *	fullCardNumber FullCardNumber, 18 bytes
	 *	cardExpiryDate TimeReal, 4 bytes
	 *	cardInsertionTime TimeReal, 4 bytes
	 *	vehicleOdometerValueAtInsertion OdometerShort, 3 bytes
	 *	cardSlotNumber CardSlotNumber, 1 byte
	 *	cardWithdrawalTime TimeReal, 4 bytes
	 *	vehicleOdometerValueAtWithdrawal OdometerShort, 3 bytes
	 *	previousVehicleInfo PreviousVehicleInfo, 19 bytes
	 *	manualInputFlag ManualInputFlag, 1 byte
	 * }
	 */

	/**
	 * Size of structure in bytes.
	 * Only valid after instantiation of the VuCardIWData object.
	 */
	public final int size;

	private int noOfIWRecords;

	// create min. 20 vectors; will be automatically expanded at run time if required!
	private Vector<VuCardIWRecord> vuCardIWRecords = new Vector<VuCardIWRecord>( 20 );


	/**
	 * Constructor for a VuCardIWData object
	 * 
	 * @param	value	byte array of a VuCardIWData structure
	 * 					whose data is used when the VuCardIWData
	 * 					object is created.
	 */
	public VuCardIWData( byte[] value ) {
		noOfIWRecords = convertIntoUnsigned2ByteInt( arrayCopy( value, 0, 2 ) );
		size = 2 + noOfIWRecords * VuCardIWRecord.size;

		if ( noOfIWRecords != 0 ) {
			for ( int i = 0; i < noOfIWRecords; i++ ) {
				byte[] record = arrayCopy( value, 2 + ( i * VuCardIWRecord.size ), VuCardIWRecord.size );
				VuCardIWRecord vciwr = new VuCardIWRecord( record );
				vuCardIWRecords.add( vciwr );
			}
		}
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		Iterator<VuCardIWRecord> iter = vuCardIWRecords.iterator();
		while ( iter.hasNext() ) {
			VuCardIWRecord vciwr = (VuCardIWRecord)iter.next();
			node.addContent( vciwr.generateXMLElement( "vuCardIWRecord" ) );
		}

		return node;
	}
}
