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
 * Code indicating the version of the implemented structure in a tachograph card.
 */
public class CardStructureVersion extends DataClass {
	/*
	 * CardStructureVersion ::= OCTET STRING(SIZE(2)), 2 bytes
	 * ---
	 * Value assignment: 'aabb'H
	 * 'aa'H	Index for changes of the structure, '00h' for this version.
	 * 'bb'H	Index for changes concerning the use of the data elements defined
	 * 			for the strucure given by the high byte, '00h' for this version.
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 2;

	private byte[] cardStructureVersion;

	/**
	 * Constructor for a CardStructureVersion object
	 */
	public CardStructureVersion() {
		cardStructureVersion = new byte[ 2 ];
	}

	/**
	 * Constructor for a CardStructureVersion object
	 * 
	 * @param	value	byte array of a the code indicating the version of the
	 * 					implemented structure in a tachograph card that is used
	 * 					when the CardStructureVersion object is created.
	 */
	public CardStructureVersion( byte[] value ) {
		setCardStructureVersion( value );
	}

	/**
	 * Returns the code indicating the version of the implemented structure
	 * in a tachograph card of a CardStructureVersion object.
	 * 
	 * @return	the code indicating the version of the implemented structure in a tachograph card
	 * 			of the CardStructureVersion object.
	 */
	public byte[] getCardStructureVersion() {
		return cardStructureVersion;
	}

	/**
	 * Sets the code indicating the version of the implemented structure
	 * in a tachograph card of a CardStructureVersion object.
	 * 
	 * @param	cardStructureVersion	the code indicating the version of the implemented structure
	 * 									in a tachograph card to be set for the CardStructureVersion object
	 */
	public void setCardStructureVersion( byte[] cardStructureVersion ) {
		this.cardStructureVersion = arrayCopy( cardStructureVersion, 0, 2 );
	}

	@Override
	public Element generateXMLElement( String name ) {
		return new Element( name ).setText( convertIntoHexString( cardStructureVersion ) );
	}
}
