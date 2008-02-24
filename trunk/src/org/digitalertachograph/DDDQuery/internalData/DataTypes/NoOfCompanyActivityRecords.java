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
 * Number of company activity records, a company card can store.
 */
public class NoOfCompanyActivityRecords extends DataClass {
	/* 
	 * NoOfCompanyActivityRecords ::= INTEGER(0..2^16-1), 2 bytes
	 * min.: 230
	 * max.: 520
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 2;

	private int noOfCompanyActivityRecords;

	
	/**
	 * Constructor for a NoOfCompanyActivityRecords object
	 */
	public NoOfCompanyActivityRecords() {
		noOfCompanyActivityRecords = 0;
	}

	/**
	 * Constructor for a NoOfCompanyActivityRecords object
	 * 
	 * @param	value	byte array of a NoOfCompanyActivityRecords structure
	 * 					whose data is used when the NoOfCompanyActivityRecords
	 * 					object is created.
	 */
	public NoOfCompanyActivityRecords( byte[] value ) {
		this.noOfCompanyActivityRecords = convertIntoUnsigned2ByteInt( value );
	}

	/**
	 * Returns the number of company activity records, a company card can store
	 * of a NoOfCompanyActivityRecords object.
	 * 
	 * @return	the number of company activity records, a company card can store
	 * 			of the NoOfCompanyActivityRecords object
	 */
	public int getNoOfCompanyActivityRecords() {
		return noOfCompanyActivityRecords;
	}

	/**
	 * Sets the number of company activity records, a company card can store
	 * of a NoOfCompanyActivityRecords object.
	 * 
	 * @param	noOfCompanyActivityRecords		the number of company activity records, a company card can store
	 * 											to be set for the NoOfCardVehicleRecords object
	 */
	public void setNoOfCompanyActivityRecords( int noOfCompanyActivityRecords ) {
		this.noOfCompanyActivityRecords = noOfCompanyActivityRecords;
	}

	@Override
	public Element generateXMLElement( String name ) {
		return new Element( name ).setText( Integer.toString( noOfCompanyActivityRecords ) );
	}
}
