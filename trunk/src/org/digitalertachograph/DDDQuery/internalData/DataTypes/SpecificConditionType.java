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

package org.digitalertachograph.DDDQuery.internalData.DataTypes;

import org.digitalertachograph.DDDQuery.internalData.DataClass;
import org.jdom.Element;

/**
 * Code identifying a specific condition.
 */
public class SpecificConditionType extends DataClass {
	/*
	 * SpecificConditionType ::= INTEGER(0..255), 1 byte
	 * 
	 * Value assignment:
	 * '00'H RFU
	 * '01'H Out of scope - Begin
	 * '02'H Out of scope - End
	 * '03'H Ferry/Train crossing
	 * '04'H .. 'FF'H RFU.
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 1;

	private byte specificConditionType;

	
	/**
	 * Constructor for an SpecificConditionType object
	 */
	public SpecificConditionType() {
		specificConditionType = 0;
	}

	/**
	 * Constructor for an SpecificConditionType object
	 * 
	 * @param	specificConditionType	byte that identifies a specific condition.
	 */
	public SpecificConditionType( byte specificConditionType ) {
		this.specificConditionType = specificConditionType;
	}

	/**
	 * Returns the code identifying the specific condition of a SpecificConditionRecord object.
	 * 
	 * @return	the code identifying the specific condition of the SpecificConditionRecord object
	 */
	public byte getSpecificConditionType() {
		return specificConditionType;
	}

	/**
	 * Sets the code identifying the specific condition of a SpecificConditionRecord object.
	 * 
	 * @param	specificConditionType	the code identifying the specific condition to be set
	 * 									for the SpecificConditionRecord object
	 */
	public void setSpecificConditionType( byte specificConditionType ) {
		this.specificConditionType = specificConditionType;
	}
	
	@Override
	public Element generateXMLElement( String name ) {
		return new Element( name ).setText( Byte.toString( specificConditionType ) );
	}
}
