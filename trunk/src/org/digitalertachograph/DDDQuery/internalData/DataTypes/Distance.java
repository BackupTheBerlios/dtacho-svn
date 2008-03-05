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
 * A distance travelled (result of the calculation of the difference between
 * two vehicle's odometer value in kilometres).
 */
public class Distance extends DataClass {
	/* 
	 * Distance ::= INTEGER(0..2^16-1), 2 bytes
	 * ---
	 * Value assignment:
	 * Unsigned binary. Value in km in the operation range 0 to 9999 km.
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 2;

	private int distance;


	/**
	 * Constructor for a Distance object
	 */
	public Distance() {
		distance = 0;
	}

	/**
	 * Constructor for a Distance object
	 * 
	 * @param	value	byte array of a Distance structure
	 * 					whose data is used when the Distance
	 * 					object is created.
	 */
	public Distance( byte[] value ) {
		this.distance = convertIntoUnsigned2ByteInt( value );
	}

	/**
	 * Returns the distance travelled of a Distance object.
	 * 
	 * @return	the distance travelled of the Distance object
	 */
	public int getDistance() {
		return distance;
	}

	/**
	 * Sets the distance travelled of a Distance object.
	 * 
	 * @param	distance		the distance travelled to be set for the Distance object
	 */
	public void setDistance( int distance ) {
		this.distance = distance;
	}

	@Override
	public Element generateXMLElement( String name ) {
		return new Element( name ).setText( Integer.toString( distance ) );
	}
}
