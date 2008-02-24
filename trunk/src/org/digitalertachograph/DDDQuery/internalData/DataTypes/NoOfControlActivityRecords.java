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

import org.digitalertachograph.DDDQuery.internalData.DataClass;
import org.jdom.Element;

/**
 * Number of control activity records, a control card can store.
 */
public class NoOfControlActivityRecords extends DataClass {
	/* 
	 * NoOfControlActivityRecords ::= INTEGER(0..2^16-1), 2 bytes
	 * min.: 230
	 * max.: 520
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 2;

	private int noOfControlActivityRecords;

	
	/**
	 * Constructor for a NoOfControlActivityRecords object
	 */
	public NoOfControlActivityRecords() {
		noOfControlActivityRecords = 0;
	}

	/**
	 * Constructor for a NoOfControlActivityRecords object
	 * 
	 * @param	value	byte array of a NoOfControlActivityRecords structure
	 * 					whose data is used when the NoOfControlActivityRecords
	 * 					object is created.
	 */
	public NoOfControlActivityRecords( byte[] value ) {
		this.noOfControlActivityRecords = convertIntoUnsigned2ByteInt( value );
	}

	/**
	 * Returns the number of control activity records, a control card can store
	 * of a NoOfControlActivityRecords object.
	 * 
	 * @return	the number of control activity records, a control card can store
	 * 			of the NoOfControlActivityRecords object
	 */
	public int getNoOfControlActivityRecords() {
		return noOfControlActivityRecords;
	}

	/**
	 * Sets the number of control activity records, a control card can store
	 * of a NoOfControlActivityRecords object.
	 * 
	 * @param	noOfControlActivityRecords		the number of control activity records, a control card can store
	 * 											to be set for the NoOfControlActivityRecords object
	 */
	public void setNoOfControlActivityRecords( int noOfControlActivityRecords ) {
		this.noOfControlActivityRecords = noOfControlActivityRecords;
	}

	@Override
	public Element generateXMLElement( String name ) {
		return new Element( name ).setText( Integer.toString( noOfControlActivityRecords ) );
	}
}
