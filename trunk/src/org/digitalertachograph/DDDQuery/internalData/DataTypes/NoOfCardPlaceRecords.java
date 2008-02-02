/*   Copyright (C) 2008, Gerald Schnabel

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
 * Number of place records a driver or workshop card can store.
 */
public class NoOfCardPlaceRecords extends DataClass {
	/*
	 * NoOfCardPlaceRecords ::=  INTEGER(0..255)
	 * min.:  84
	 * max.: 112
	 */

	private short noOfCardPlaceRecords;


	/**
	 * Constructor for a NoOfCardPlaceRecords object
	 */
	public NoOfCardPlaceRecords() {
		noOfCardPlaceRecords = 0;
	}

	/**
	 * Constructor for a NoOfCardPlaceRecords object
	 * 
	 * @param	b	byte that contains the number of place records a driver or workshop card can store
	 * 				when the NoOfCardPlaceRecords object is created.
	 */
	public NoOfCardPlaceRecords( byte b ) {
		noOfCardPlaceRecords = convertIntoUnsigned1ByteInt( b );
	}

	/**
	 * Returns number of place records a driver or workshop card can store
	 * of a NoOfCardPlaceRecords object.
	 * 
	 * @return	the number of place records a driver or workshop card can store
	 * 			of the NoOfCardPlaceRecords object
	 */
	public short getNoOfCardPlaceRecords() {
		return noOfCardPlaceRecords;
	}

	/**
	 * Sets the number of place records a driver or workshop card can store
	 * of a NoOfCardPlaceRecords object.
	 * 
	 * @param	noOfCardPlaceRecords	the number of place records a driver or workshop card can store
	 * 									to be set for the NoOfCardPlaceRecords object
	 */
	public void setNoOfCardPlaceRecords( short noOfCardPlaceRecords ) {
		this.noOfCardPlaceRecords = noOfCardPlaceRecords;
	}

	@Override
	public Element generateXMLElement( String name ) {
		return new Element( name ).setText( Short.toString( noOfCardPlaceRecords ) );
	}
}
