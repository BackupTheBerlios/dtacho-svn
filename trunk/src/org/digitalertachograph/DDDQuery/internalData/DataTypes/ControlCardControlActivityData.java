/*
    $Id:ControlCardControlActivityData.java 26 2008-02-25 22:28:27Z deetee $

    Copyright (C) 2007-2008, Martin Barth, Gerald Schnabel

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
 * Information, stored in a control card, related to the control activity performed with the card.
 */
public class ControlCardControlActivityData extends DataClass {
	/*
	 * ControlCardControlActivityData ::= SEQUENCE {	
	 * 	controlPointerNewestRecord INTEGER(0..NoOfControlActivityRecords-1), 2 bytes
	 * 	controlActivityRecords SET SIZE(NoOfControlActivityRecords) OF controlActivityRecord SEQUENCE {
	 * 			controlType ControlType, 1 byte
	 * 			controlTime TimeReal, 4 bytes
	 * 			controlledCardNumber FullCardNumber, 18 bytes
	 * 			controlledVehicleRegistration VehicleRegistrationIdentification, 15 bytes
	 * 			controlDownloadPeriodBegin TimeReal, 4 bytes
	 * 			controlDownloadPeriodEnd TimeReal, 4 bytes
	 * 	} // which is a CardControlActivityDataRecord
	 * }
	 * ---
	 * NoOfControlActivityRecords ::= INTEGER(0..2^16-1)
	 * min.: 230
	 * max.: 520
	 */

	/**
	 * Size of structure in bytes.
	 * Only valid after instantiation of the ControlCardControlActivityData object.
	 */
	public final int size;

	// create min. 230 vectors; will be automatically expanded at run time if required!
	private Vector<CardControlActivityDataRecord> controlActivityRecords = new Vector<CardControlActivityDataRecord>( 230 );


	/**
	 * Constructor for a ControlCardControlActivityData object
	 * 
	 * @param	value	byte array of a ControlCardControlActivityData structure
	 * 					whose data is used when the ControlCardControlActivityData
	 * 					object is created.
	 */
	public ControlCardControlActivityData( byte[] value, int noOfControlActivityRecords ) {
		for ( int i = 0; i < noOfControlActivityRecords; i += 1 ) {
			byte[] record = arrayCopy( value, 2 + ( i * CardControlActivityDataRecord.size ), CardControlActivityDataRecord.size );
			CardControlActivityDataRecord tmp = new CardControlActivityDataRecord( record );
			controlActivityRecords.add( tmp );
		}

		size = 2 + noOfControlActivityRecords * CardControlActivityDataRecord.size;
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name);

		Iterator<CardControlActivityDataRecord> it = controlActivityRecords.iterator();
		while ( it.hasNext() ) {
			CardControlActivityDataRecord ccadr = (CardControlActivityDataRecord)it.next();
			node.addContent( ccadr.generateXMLElement( "controlActivityRecord" ) );
		}

		return node;
	}
}
