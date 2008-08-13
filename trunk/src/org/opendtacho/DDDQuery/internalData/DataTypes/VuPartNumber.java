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

package org.opendtacho.DDDQuery.internalData.DataTypes;

import org.jdom.Element;
import org.opendtacho.DDDQuery.internalData.DataClass;

/**
 * Part number of the vehicle unit.
 */
public class VuPartNumber extends DataClass {
	/*
	 * VuPartNumber ::= IA5String(SIZE(16)), 16 bytes
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 16;

	private String vuPartNumber;


	/**
	 * Constructor for a VuPartNumber object
	 */
	public VuPartNumber() {
		vuPartNumber = new String();
	}

	/**
	 * Constructor for a VuPartNumber object
	 * 
	 * @param	value	byte array of a VuPartNumber that is used when the VuPartNumber
	 * 					object is created.
	 */
	public VuPartNumber( byte[] value ) {
		vuPartNumber = new String( arrayCopy( value, 0, 16 ) );
	}

	/**
	 * Constructor for a VuPartNumber object
	 * 
	 * @param	vuPartNumber	String that contains the part number of a vehicle unit
	 * 							that is used when the VuPartNumber object is created.
	 */
	public VuPartNumber( String vuPartNumber ) {
		setVuPartNumber( vuPartNumber );
	}

	/**
	 * Returns the part number of the vehicle unit of a VuPartNumber object.
	 * 
	 * @return	the part number of the vehicle unit of the VuPartNumber object
	 */
	public String getVuPartNumber() {
		return vuPartNumber;
	}

	/**
	 * Sets the part number of the vehicle unit of a VuPartNumber object.
	 * 
	 * @param	vuPartNumber		the part number of the vehicle unit
	 * 								to be set for the VuPartNumber object
	 */
	public void setVuPartNumber( String vuPartNumber ) {
		int vuPartNumberLength = vuPartNumber.length(); 

		if ( vuPartNumberLength > 16 ) {
			vuPartNumberLength = 16;
		}

		this.vuPartNumber = vuPartNumber.substring( 0, vuPartNumberLength );
	}

	@Override
	public Element generateXMLElement( String name ) {
		if ( isValidXMLString( vuPartNumber ) == false ) {
			return new Element( name );
		}
		else {
			return new Element( name ).setText( vuPartNumber );
		}
	}
}
