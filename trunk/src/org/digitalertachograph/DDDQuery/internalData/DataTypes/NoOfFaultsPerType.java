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
 * Number of faults per type of fault a card can store.
 */
public class NoOfFaultsPerType extends DataClass {
	/*
	 * NoOfFaultsPerType ::=  INTEGER(0..255)
	 * min.: 12
	 * max.: 24
	 */

	private short noOfFaultsPerType;


	/**
	 * Constructor for a NoOfFaultsPerType object
	 */
	public NoOfFaultsPerType() {
		noOfFaultsPerType = 0;
	}

	/**
	 * Constructor for a NoOfFaultsPerType object
	 * 
	 * @param	b	byte that contains the number of faults per type of fault a card can store
	 * 				when the NoOfFaultsPerType object is created.
	 */
	public NoOfFaultsPerType( byte b ) {
		noOfFaultsPerType = convertIntoUnsigned1ByteInt( b );
	}

	/**
	 * Returns the number of faults per type of fault a card can store
	 * of a NoOfFaultsPerType object.
	 * 
	 * @return	the number of faults per type of fault a card can store
	 * 			of the NoOfFaultsPerType object
	 */
	public short getNoOfFaultsPerType() {
		return noOfFaultsPerType;
	}

	/**
	 * Sets the number of fault per type of fault a card can store of a NoOfFaultsPerType object.
	 * 
	 * @param	noOfFaultsPerType		the number of faults per type of fault a card can store
	 * 									to be set for the NoOfFaultsPerType object
	 */
	public void setNoOfFaultsPerType( short noOfFaultsPerType ) {
		this.noOfFaultsPerType = noOfFaultsPerType;
	}

	@Override
	public Element generateXMLElement( String name ) {
		return new Element( name ).setText( Short.toString( noOfFaultsPerType ) );
	}
}
