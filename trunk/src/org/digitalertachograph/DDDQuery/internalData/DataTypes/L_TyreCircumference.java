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
 * Effective circumference of the wheel tyres.
 */
public class L_TyreCircumference extends DataClass {
	/* 
	 * LTyreCircumference ::= INTEGER(0..2^16-1), 2 bytes
	 *
	 * value assignment:
	 * Unsigned binary, value in 1/8 mm in the operating range 0 to 8031 mm.
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 2;

	private int lTyreCircumference;

	
	/**
	 * Constructor for a LTyreCircumference object
	 */
	public L_TyreCircumference() {
		lTyreCircumference = 0;
	}

	/**
	 * Constructor for a LTyreCircumference object
	 * 
	 * @param	value	byte array of a LTyreCircumference structure
	 * 					whose data is used when the LTyreCircumference
	 * 					object is created.
	 */
	public L_TyreCircumference( byte[] value ) {
		this.lTyreCircumference = convertIntoUnsigned2ByteInt( value );
	}

	/**
	 * Returns the effective circumference of the wheel tyres
	 * of a LTyreCircumference object.
	 * 
	 * @return	the effective circumference of the wheel tyres
	 * 			of the LTyreCircumference object
	 */
	public int getLTyreCircumference() {
		return lTyreCircumference;
	}

	/**
	 * Sets the effective circumference of the wheel tyres
	 * of a LTyreCircumference object.
	 * 
	 * @param	lTyreCircumference		the effective circumference of the wheel tyres
	 * 									to be set for the LTyreCircumference object
	 */
	public void setLTyreCircumference( int lTyreCircumference ) {
		this.lTyreCircumference = lTyreCircumference;
	}

	@Override
	public Element generateXMLElement( String name ) {
		return new Element( name ).setText( Integer.toString( lTyreCircumference ) );
	}
}
