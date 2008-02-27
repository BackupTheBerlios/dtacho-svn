/*
    $Id:CardChipIdentification.java 26 2008-02-25 22:28:27Z deetee $

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
 * Information, stored in a card, related to the identification of the card's
 * Integrated Circuit (IC).
 */
public class CardChipIdentification extends DataClass {

	/*
	 * CardChipIdentification ::= SEQUENCE {
	 *  icSerialNumber				0x00..0x00	OCTET STRING (SIZE(4)), 4 bytes
	 *  icManufacturingReferences	0x00..0x00	OCTET STRING (SIZE(4)), 4 bytes
	 * }
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 8;

	private byte[] icSerialNumber;
	private byte[] icManufacturingReferences;


	/**
	 * Constructor for a CardChipIdentification object
	 */
	public CardChipIdentification() {
		icSerialNumber = new byte[ 4 ];
		icManufacturingReferences = new byte[ 4 ];
	}

	/**
	 * Constructor for a CardChipIdentification object
	 */
	public CardChipIdentification( byte[] value ) {
		icSerialNumber = arrayCopy( value, 0, 4 );
		icManufacturingReferences = arrayCopy( value, 4, 4 );
	}

	/**
	 * Returns the IC serial number of a CardChipIdentification object.
	 * 
	 * @return	byte array that contains the IC serial number of the CardChipIdentification object
	 * 			as defined in EN 726-3
	 */
	public byte[] getIcSerialNumber() {
		return icSerialNumber;
	}

	/**
	 * Sets the IC serial number of a CardChipIdentification object.
	 * 
	 * @param	icSerialNumber		byte array that contains the IC serial number to be set for the
	 * 								CardChipIdentification object
	 */
	public void setIcSerialNumber( byte[] icSerialNumber ) {
		this.icSerialNumber = arrayCopy( icSerialNumber, 0, 4 );
	}

	/**
	 * Returns the IC manufacturer identifier and fabrication elements of a CardChipIdentification object.
	 * 
	 * @return	byte array that contains the IC manufacturer identifier and fabrication elements
	 * 			of the CardChipIdentification object as defined in EN 726-3
	 */
	public byte[] getIcManufacturingReferences() {
		return icManufacturingReferences;
	}

	/**
	 * Sets the IC manufacturer identifier and fabrication elements of a CardChipIdentification object.
	 * 
	 * @param	icManufacturingReferences	byte array that contains the IC manufacturer identifier
	 * 										and fabrication elements to be set for the
	 * 										CardChipIdentification object
	 */
	public void setIcManufacturingReferences( byte[] icManufacturingReferences ) {
		this.icManufacturingReferences = arrayCopy( icManufacturingReferences, 0, 4 );
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		node.addContent( new Element( "icSerialNumber" ).setText( convertIntoHexString( icSerialNumber ) ) );
		node.addContent( new Element( "icManufacturingReferences" ).setText( convertIntoHexString( icManufacturingReferences ) ) );

		return node;
	}
}
