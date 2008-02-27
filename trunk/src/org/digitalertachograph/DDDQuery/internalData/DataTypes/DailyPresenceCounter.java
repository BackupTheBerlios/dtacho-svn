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
 * Counter, stored in a driver or workshop card, increased by one for each calendar day
 * the card has been inserted in a VU.
 */
public class DailyPresenceCounter extends DataClass {
	/* 
	 * DailyPresenceCounter ::= BCDString(SIZE(2)), 2 bytes
	 * ---
	 * Value assignment:
	 * Consecutive number with maximum value 9 999, starting again with 0.
	 * At the first time of first issuing of the card the number is set to 0.
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 2;

	private byte[] dailyPresenceCounter;

	
	/**
	 * Constructor for a DailyPresenceCounter object
	 */
	public DailyPresenceCounter() {
		dailyPresenceCounter = new byte[ 2 ];
	}

	/**
	 * Constructor for a DailyPresenceCounter object
	 * 
	 * @param	value	byte array of a DailyPresenceCounter structure
	 * 					whose data is used when the DailyPresenceCounter
	 * 					object is created.
	 */
	public DailyPresenceCounter( byte[] value ) {
		setDailyPresenceCounter( value );
	}

	/**
	 * Returns the counter, stored in a driver or workshop card, increased by one
	 * for each calendar day the card has been inserted in a VU
	 * of a DailyPresenceCounter object.
	 * 
	 * @return	the counter, stored in a driver or workshop card, increased by one
	 * 			for each calendar day the card has been inserted in a VU
	 * 			of the DailyPresenceCounter object
	 */
	public byte[] getDailyPresenceCounter() {
		return dailyPresenceCounter;
	}

	/**
	 * Sets the counter, stored in a driver or workshop card, increased by one for each calendar day
	 * the card has been inserted in a VU of a DailyPresenceCounter object.
	 * 
	 * @param	dailyPresenceCounter	the counter, stored in a driver or workshop card, increased
	 * 									by one for each calendar day the card has been inserted in a VU
	 * 									to be set for the DailyPresenceCounter object
	 */
	public void setDailyPresenceCounter( byte[] dailyPresenceCounter ) {
		this.dailyPresenceCounter = arrayCopy( dailyPresenceCounter, 0, 2 );
	}

	@Override
	public Element generateXMLElement( String name ) {
		return new Element( name ).setText( convertBCDStringIntoString( dailyPresenceCounter ) );
	}
}
