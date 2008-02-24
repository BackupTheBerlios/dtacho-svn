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
 * Code identifying whether a cardholder has manually entered driver activities
 * at card insertion or not.
 */
public class ManualInputFlag extends DataClass {
	/*
	 * ManualInputFlag ::= INTEGER {
	 *	noEntry (0)
	 *	ManualEntries (1)
	 * }, 1 byte
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 1;

	private static final byte ENTRY_MASK = (byte)0x01;
	/**
	 * Indicates that the cardholder has not manually entered driver activities
	 */
	public static final boolean NO_ENTRY = false;
	/**
	 * Indicates that the cardholder has manually entered driver activities
	 */
	public static final boolean MANUAL_ENTRY = true;


	private boolean manualInputFlag;


	/**
	 * Constructor for a ManualInputFlag object
	 */
	public ManualInputFlag() {
		manualInputFlag = false;
	}

	/**
	 * Constructor for a ManualInputFlag object
	 * 
	 * @param	value	byte array of a ManualInputFlag structure
	 * 					whose data is used when the ManualInputFlag
	 * 					object is created.
	 */
	public ManualInputFlag( byte[] value ) {
		manualInputFlag = ( ( value[ 0 ] & ENTRY_MASK ) == ENTRY_MASK );
	}

	/**
	 * Constructor for a ManualInputFlag object
	 * 
	 * @param	manualInputFlag		byte that contains the value that is
	 * 								used when the ManualInputFlag
	 * 								object is created.
	 */
	public ManualInputFlag( byte manualInputFlag ) {
		this.manualInputFlag = ( ( manualInputFlag & ENTRY_MASK ) == ENTRY_MASK );
	}

	/**
	 * Returns the code identifying whether a cardholder has manually entered driver activities
	 * at card insertion or not of a ManualInputFlag object.
	 * 
	 * @return	the code identifying whether a cardholder has manually entered driver activities
	 * 			at card insertion of the ManualInputFlag object
	 */
	public boolean getManualInputFlag() {
		return manualInputFlag;
	}

	/**
	 * Sets the code identifying whether a cardholder has manually entered driver activities
	 * at card insertion of a ManualInputFlag object.
	 * 
	 * @param	manualInputFlag		the code identifying whether a cardholder has manually
	 * 								entered driver activities at card insertion
	 * 								to be set for the ManualInputFlag object
	 */
	public void setManualInputFlag( boolean manualInputFlag ) {
		this.manualInputFlag = manualInputFlag;
	}

	@Override
	public Element generateXMLElement( String name ) {
		return new Element( name ).setText( Boolean.toString( manualInputFlag ) );
	}
}
