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
 * Designation of tyre dimensions.
 */
public class TyreSize extends DataClass {
	/*
	 * TyreSize ::= IA5String(SIZE(15)), 15 bytes
	 * 
	 * Value assignment: in accordance with Directive 92/23 (EEC) 31.3.1992,
	 * OJ L129, p. 95
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 15;

	private String tyreSize;


	/**
	 * Constructor for a TyreSize object
	 */
	public TyreSize() {
		tyreSize = new String();
	}

	/**
	 * Constructor for a TyreSize object
	 * 
	 * @param	value	byte array of a TyreSize structure whose data is used when the TyreSize
	 * 					object is created.
	 */
	public TyreSize( byte[] value ) {
		tyreSize = new String( arrayCopy( value, 0, 15 ) );
	}

	/**
	 * Constructor for a TyreSize object
	 * 
	 * @param	tyreSize	byte array of a TyreSize structure whose data is used when the TyreSize
	 * 						object is created.
	 */
	public TyreSize( String tyreSize ) {
		setTyreSize( tyreSize );
	}

	/**
	 * Returns the designation of tyre dimensions of a TyreSize object.
	 * 
	 * @return	the designation of tyre dimensions of the TyreSize object
	 */
	public String getTyreSize() {
		return tyreSize;
	}

	/**
	 * Sets the designation of tyre dimensions of a TyreSize object.
	 * 
	 * @param	tyreSize		the designation of tyre dimensions of a TyreSize object
	 */
	public void setTyreSize( String tyreSize ) {
		int tyreSizeLength = tyreSize.length();

		if ( tyreSizeLength > 15 ) {
			tyreSizeLength = 15;
		}

		this.tyreSize = tyreSize.substring( 0, tyreSizeLength );
	}

	@Override
	public Element generateXMLElement( String name ) {
		if ( isValidXMLString( tyreSize ) == false ) {
			return new Element( name );
		}
		else {
			return new Element( name ).setText( tyreSize );
		}
	}
}
