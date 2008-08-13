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
 * Counter, stored in a card, identifying sequentially the insertion withdrawal
 * cycles of the card in vehicle units.
 */
public class VuDataBlockCounter extends DataClass {
	/* 
	 * VuDataBlockCounter ::= BCDString(SIZE(2)), 2 bytes
	 * ---
	 * Value assignment:
	 * Consecutive number with max. value 9 999, starting again with 0.
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 2;

	private byte[] vuDataBlockCounter;

	
	/**
	 * Constructor for a VuDataBlockCounter object
	 */
	public VuDataBlockCounter() {
		vuDataBlockCounter = new byte[ 2 ];
	}

	/**
	 * Constructor for a VuDataBlockCounter object
	 * 
	 * @param	value	byte array of a VuDataBlockCounter structure
	 * 					whose data is used when the VuDataBlockCounter
	 * 					object is created.
	 */
	public VuDataBlockCounter( byte[] value ) {
		setVuDataBlockCounter( value );
	}

	/**
	 * Returns the counter, stored in a card, identifying sequentially the insertion withdrawal
	 * cycles of the card in vehicle units of a VuDataBlockCounter object.
	 * 
	 * @return	the counter, stored in a card, identifying sequentially the insertion withdrawal
	 * 			cycles of the card in vehicle units of the VuDataBlockCounter object
	 */
	public byte[] getVuDataBlockCounter() {
		return vuDataBlockCounter;
	}

	/**
	 * Returns the counter, stored in a card, identifying sequentially the insertion withdrawal
	 * cycles of the card in vehicle units of a VuDataBlockCounter object.
	 * 
	 * @return	the counter, stored in a card, identifying sequentially the insertion withdrawal
	 * 			cycles of the card in vehicle units of the VuDataBlockCounter object
	 */
	public String getVuDataBlockCounterString() {
		return convertBCDStringIntoString( vuDataBlockCounter );
	}

	/**
	 * Sets the counter, stored in a card, identifying sequentially the insertion withdrawal
	 * cycles of the card in vehicle units of a VuDataBlockCounter object.
	 * 
	 * @param	vuDataBlockCounter	the counter, stored in a card, identifying sequentially the
	 * 								insertion withdrawal cycles of the card in vehicle units to
	 * 								be set for the VuDataBlockCounter object
	 */
	public void setVuDataBlockCounter( byte[] vuDataBlockCounter ) {
		this.vuDataBlockCounter = arrayCopy( vuDataBlockCounter, 0, 2 );
	}

	@Override
	public Element generateXMLElement( String name ) {
		return new Element( name ).setText( convertBCDStringIntoString( vuDataBlockCounter ) );
	}
}
