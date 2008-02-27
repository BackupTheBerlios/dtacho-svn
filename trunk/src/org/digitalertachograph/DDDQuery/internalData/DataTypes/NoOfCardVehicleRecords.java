/*
    $Id:NoOfCardVehicleRecords.java 26 2008-02-25 22:28:27Z deetee $

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
 * Number of vehicles used records a driver or workshop card can store.
 */
public class NoOfCardVehicleRecords extends DataClass {
	/* 
	 * NoOfCardVehicleRecords ::= INTEGER(0..2^16-1), 2 bytes
	 * min.:  84
	 * max.: 200
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 2;

	private int noOfCardVehicleRecords;

	
	/**
	 * Constructor for a NoOfCardVehicleRecords object
	 */
	public NoOfCardVehicleRecords() {
		noOfCardVehicleRecords = 0;
	}

	/**
	 * Constructor for a NoOfCardVehicleRecords object
	 * 
	 * @param	value	byte array of a NoOfCardVehicleRecords structure
	 * 					whose data is used when the NoOfCardVehicleRecords
	 * 					object is created.
	 */
	public NoOfCardVehicleRecords( byte[] value ) {
		this.noOfCardVehicleRecords = convertIntoUnsigned2ByteInt( value );
	}

	/**
	 * Returns the number of vehicles used records a driver or workshop card can store
	 * of a NoOfCardVehicleRecords object.
	 * 
	 * @return	the number of vehicles used records a driver or workshop card can store
	 * 			of the NoOfCardVehicleRecords object
	 */
	public int getNoOfCardVehicleRecords() {
		return noOfCardVehicleRecords;
	}

	/**
	 * Sets the number of vehicles used records a driver or workshop card can store
	 * of a NoOfCardVehicleRecords object.
	 * 
	 * @param	noOfCardVehicleRecords		the number of vehicles used records a driver or workshop card can store
	 * 										to be set for the NoOfCardVehicleRecords object
	 */
	public void setNoOfCardVehicleRecords( int noOfCardVehicleRecords ) {
		this.noOfCardVehicleRecords = noOfCardVehicleRecords;
	}

	@Override
	public Element generateXMLElement( String name ) {
		return new Element( name ).setText( Integer.toString( noOfCardVehicleRecords ) );
	}
}
