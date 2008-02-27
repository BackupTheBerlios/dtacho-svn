/*
    $Id:VuApprovalNumber.java 26 2008-02-25 22:28:27Z deetee $

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
 * Type approval number of the vehicle unit.
 */
public class VuApprovalNumber extends DataClass {
	/*
	 * VuApprovalNumber ::= IA5String(SIZE(8)), 8 bytes
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 8;

	private String vuApprovalNumber;


	/**
	 * Constructor for a VuApprovalNumber object
	 */
	public VuApprovalNumber() {
		vuApprovalNumber = new String();
	}

	/**
	 * Constructor for a VuApprovalNumber object
	 * 
	 * @param	value	byte array of a VuApprovalNumber structure whose data is used
	 * 					when the VuApprovalNumber object is created.
	 */
	public VuApprovalNumber( byte[] value ) {
		vuApprovalNumber = new String( arrayCopy( value, 0, 8 ) );
	}

	/**
	 * Constructor for a VuApprovalNumber object
	 * 
	 * @param	vuApprovalNumber	byte array of a VuApprovalNumber structure
	 * 								whose data is used when the VuApprovalNumber
	 * 								object is created.
	 */
	public VuApprovalNumber( String vuApprovalNumber ) {
		setVuApprovalNumber( vuApprovalNumber );
	}

	/**
	 * Returns the type approval number of the vehicle unit of a VuApprovalNumber object.
	 * 
	 * @return	the type approval number of the vehicle unit of the VuApprovalNumber object
	 */
	public String getVuApprovalNumber() {
		return vuApprovalNumber;
	}

	/**
	 * Sets the type approval number of the vehicle unit of a VuApprovalNumber object.
	 * 
	 * @param	vuApprovalNumber	the type approval number of the vehicle unit of a
	 * 								VuApprovalNumber object
	 */
	public void setVuApprovalNumber( String vuApprovalNumber ) {
		int vuApprovalNumberLength = vuApprovalNumber.length();

		if ( vuApprovalNumberLength > 8 ) {
			vuApprovalNumberLength = 8;
		}

		this.vuApprovalNumber = vuApprovalNumber.substring( 0, vuApprovalNumberLength );
	}

	@Override
	public Element generateXMLElement( String name ) {
		if ( isValidXMLString( vuApprovalNumber ) == false ) {
			return new Element( name );
		}
		else {
			return new Element( name ).setText( vuApprovalNumber );
		}
	}
}
