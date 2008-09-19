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

package org.opendtacho.DDDQuery.internalData.DataTypes;

import org.jdom.Element;
import org.opendtacho.DDDQuery.internalData.DataClass;

/**
 * Number of over speeding events since the last over speeding control.
 */
public class OverspeedNumber extends DataClass {
	/*
	 * OverspeedNumber ::= INTEGER(0..255), 1 byte
	 * Value assignment:
	 * 0 means that no over speeding event has occurred since the last over speeding control,
	 * 1 means that one over speeding event has occurred since the last over speeding control...
	 * 255 means that 255 or more over speeding events have occurred since the last over speeding control.
	 * 
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 1;

	private short overspeedNumber;


	/**
	 * Constructor for an OverspeedNumber object
	 */
	public OverspeedNumber() {
		overspeedNumber = 0;
	}

	/**
	 * Constructor for an OverspeedNumber object
	 * 
	 * @param	b	byte that contains the number of over speeding events since the last over speeding control
	 * 				when the OverspeedNumber object is created.
	 */
	public OverspeedNumber( byte b ) {
		overspeedNumber = convertIntoUnsigned1ByteInt( b );
	}

	/**
	 * Returns the number of over speeding events since the last over speeding control
	 * of an OverspeedNumber object.
	 * 
	 * @return	the number of over speeding events since the last over speeding control
	 * 			of the OverspeedNumber object
	 */
	public short getOverspeedNumber() {
		return overspeedNumber;
	}

	/**
	 * Sets the number of over speeding events since the last over speeding control
	 * of an OverspeedNumber object.
	 * 
	 * @param	overspeedNumber		the number of over speeding events since the last over speeding control
	 * 								to be set for the OverspeedNumber object
	 */
	public void setOverspeedNumber( short overspeedNumber ) {
		this.overspeedNumber = overspeedNumber;
	}

	@Override
	public Element generateXMLElement( String name ) {
		return new Element( name ).setText( Short.toString( overspeedNumber ) );
	}
}
