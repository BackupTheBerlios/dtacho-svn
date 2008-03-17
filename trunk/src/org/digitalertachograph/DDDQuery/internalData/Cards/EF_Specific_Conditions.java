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

package org.digitalertachograph.DDDQuery.internalData.Cards;

import org.digitalertachograph.DDDQuery.internalData.DataClass;
import org.digitalertachograph.DDDQuery.internalData.DataTypes.EquipmentType;
import org.digitalertachograph.DDDQuery.internalData.DataTypes.SpecificConditionRecord;
import org.jdom.Element;

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
	 */
	public final static int size = SpecificConditionRecord.size;

	private Vector<SpecificConditionRecord> specificConditionRecords;


	/**
	 * Constructor for an EF_Specific_Conditions object
	 * 
	 * @param	value	byte array of an EF_Specific_Conditions structure
	 * 					whose data is used when the EF_Specific_Conditions
	 * 					object is created.
	 */
	public EF_Specific_Conditions( byte[] value, short cardType ) {
		short numberOfSpecificConditionRecords;

		if ( cardType == EquipmentType.DRIVER_CARD ) {
			// driver card
			numberOfSpecificConditionRecords = 56;
		}
		else {
			// workshop card
			numberOfSpecificConditionRecords = 2;
		}

		specificConditionRecords = new Vector<SpecificConditionRecord>( numberOfSpecificConditionRecords );

		for ( int i = 0; i < numberOfSpecificConditionRecords; i++ ) {
			specificConditionRecords.add( new SpecificConditionRecord( arrayCopy( value, i * SpecificConditionRecord.size, SpecificConditionRecord.size ) ) );
		}
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
