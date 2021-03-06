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
 * Code to distinguish between begin and end for an entry of a daily work period
 * place and condition of the entry.
 */
public class EntryTypeDailyWorkPeriod extends DataClass {
	/*
	 * EntryTypeDailyWorkPeriod ::= INTEGER, 1 byte
	 *
	 * Begin, related time = card insertion time or time of entry (0),
	 * End, related time = card withdrawal time or time of entry (1),
	 * Begin, related time manually entered (start time) (2),
	 * End, related time manually entered (end of work period) (3),
	 * Begin, related time assumed by VU (4),
	 * End, related time assumed by VU (5)
	 */

	/**
	 * EntryTypeDailyWorkPeriod type: CARD_INSERTION_TIME_OR_TIME_OF_ENTRY
	 */
	public final static short CARD_INSERTION_TIME_OR_TIME_OF_ENTRY = 0;
	/**
	 * EntryTypeDailyWorkPeriod type: CARD_WITHDRAWAL_TIME_OR_TIME_OF_ENTRY
	 */
	public final static short CARD_WITHDRAWAL_TIME_OR_TIME_OF_ENTRY = 1;
	/**
	 * EntryTypeDailyWorkPeriod type: MANUALLY_ENTERED_START_TIME
	 */
	public final static short MANUALLY_ENTERED_START_TIME = 2;
	/**
	 * EntryTypeDailyWorkPeriod type: MANUALLY_ENTERED_END_TIME
	 */
	public final static short MANUALLY_ENTERED_END_TIME = 3;
	/**
	 * EntryTypeDailyWorkPeriod type: START_TIME_ASSUMED_BY_VU
	 */
	public final static short START_TIME_ASSUMED_BY_VU = 4;
	/**
	 * EntryTypeDailyWorkPeriod type: END_TIME_ASSUMED_BY_VU
	 */
	public final static short END_TIME_ASSUMED_BY_VU = 5;

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 1;

	private short entryTypeDailyWorkPeriod;


	/**
	 * Constructor for an EntryTypeDailyWorkPeriod object
	 */
	public EntryTypeDailyWorkPeriod() {
		entryTypeDailyWorkPeriod = 0;
	}

	/**
	 * Constructor for an EntryTypeDailyWorkPeriod object
	 * 
	 * @param	value	byte array of an EntryTypeDailyWorkPeriod structure
	 * 					whose data is used when the EntryTypeDailyWorkPeriod
	 * 					object is created.
	 */
	public EntryTypeDailyWorkPeriod( byte value ) {
		entryTypeDailyWorkPeriod = convertIntoUnsigned1ByteInt( value );
	}

	/**
	 * Returns the code to distinguish between begin and end for an entry of a daily
	 * work period place and condition of the entry of an EntryTypeDailyWorkPeriod object.
	 * 
	 * @return	the code to distinguish between begin and end for an entry of a daily
	 * 			work period place and condition of the entry of the EntryTypeDailyWorkPeriod object
	 */
	public short getEntryTypeDailyWorkPeriod() {
		return entryTypeDailyWorkPeriod;
	}

	/**
	 * Sets the code to distinguish between begin and end for an entry of a daily
	 * work period place and condition of the entry of an EntryTypeDailyWorkPeriod object.
	 * 
	 * @param	entryTypeDailyWorkPeriod	the code to distinguish between begin and end for an entry of a daily
	 * 										work period place and condition of the entry to be set for the
	 * 										EntryTypeDailyWorkPeriod object
	 */
	public void setEntryTypeDailyWorkPeriod( short entryTypeDailyWorkPeriod ) {
		this.entryTypeDailyWorkPeriod = entryTypeDailyWorkPeriod;
	}

	@Override
	public Element generateXMLElement( String name ) {
		return new Element( name ).setText( Short.toString( entryTypeDailyWorkPeriod ) );
	}
}
