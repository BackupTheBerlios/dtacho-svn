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
 * Odometer value of the vehicle in short form.
 */
public class OdometerShort extends DataClass {
	/* 
	 * OdometerShort ::= INTEGER(0..2^24-1), 3 bytes
	 * Value assignment:
	 * Unsigned binary. Value in km in the operating range 0 to 9 999 999 km.
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 3;

	private int odometerShort;

	
	/**
	 * Constructor for an OdometerShort object
	 */
	public OdometerShort() {
		odometerShort = 0;
	}

	/**
	 * Constructor for an OdometerShort object
	 * 
	 * @param	value	byte array of an OdometerShort structure
	 * 					whose data is used when the OdometerShort
	 * 					object is created.
	 */
	public OdometerShort( byte[] value ) {
		this.odometerShort = convertIntoUnsigned3ByteInt( value );
	}

	/**
	 * Returns the odometer value of the vehicle in short form
	 * of an OdometerShort object.
	 * 
	 * @return	the odometer value of the vehicle in short form
	 * 			of the OdometerShort object
	 */
	public int getOdometerShort() {
		return odometerShort;
	}

	/**
	 * Sets the odometer value of the vehicle in short form
	 * of an OdometerShort object.
	 * 
	 * @param	odometerShort		the odometer value of the vehicle in short form
	 * 								to be set for the OdometerShort object
	 */
	public void setOdometerShort( int odometerShort ) {
		this.odometerShort = odometerShort & 0x00ffffff;
	}

	@Override
	public Element generateXMLElement( String name ) {
		return new Element( name ).setText( Integer.toString( odometerShort ) );
	}
}
