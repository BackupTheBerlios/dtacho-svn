/*
    $Id$

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

package org.opendtacho.DDDQuery.internalData.Cards;

import org.jdom.Element;
import org.opendtacho.DDDQuery.internalData.DataClass;
import org.opendtacho.DDDQuery.internalData.DataTypes.EquipmentType;
import org.opendtacho.DDDQuery.internalData.DataTypes.SpecificConditionRecord;

import java.util.Iterator;
import java.util.Vector;

/**
 * EF_Specific_Conditions,
 * 
 * Council Regulation (EEC) No 3821/85 File ID: 0522
 */
public class EF_Specific_Conditions extends DataClass {

	/**
	 * Size of structure in bytes.
	 * Only valid after instantiation of the CardFaultData object.
	 */
	public final int size;

	private Vector<SpecificConditionRecord> specificConditionRecords;


	/**
	 * Constructor for an EF_Specific_Conditions object
	 * 
	 * @param	value	byte array of an EF_Specific_Conditions structure
	 * 					whose data is used when the EF_Specific_Conditions
	 * 					object is created.
	 */
	public EF_Specific_Conditions( byte[] value, short cardType ) {
		int noOfSpecificConditionRecords;
		int noOfValidSpecificConditionRecords = 0;

		if ( cardType == EquipmentType.DRIVER_CARD ) {
			// driver card
			noOfSpecificConditionRecords = 56;
		}
		else {
			// workshop card
			noOfSpecificConditionRecords = 2;
		}

		specificConditionRecords = new Vector<SpecificConditionRecord>( noOfSpecificConditionRecords );

		for ( int i = 0; i < noOfSpecificConditionRecords; i++ ) {
			SpecificConditionRecord scr = new SpecificConditionRecord( arrayCopy( value, i * SpecificConditionRecord.size, SpecificConditionRecord.size ) );

			if ( scr.getEntryTime().getTimeReal() != 0 ) {
				specificConditionRecords.add( scr );

				noOfValidSpecificConditionRecords += 1;
			}
		}

		size = noOfValidSpecificConditionRecords * SpecificConditionRecord.size;
	}

	public Element generateXMLElement( String name ) {
		// discard name - this.getClass().getSimpleName() is unique!
		Element node = new Element( this.getClass().getSimpleName() );

		Iterator<SpecificConditionRecord> iter = specificConditionRecords.iterator();
		while ( iter.hasNext() ) {
			SpecificConditionRecord scr = (SpecificConditionRecord)iter.next();
			node.addContent( scr.generateXMLElement( "specificConditionRecord" ) );
		}

		return node;
	}
}
