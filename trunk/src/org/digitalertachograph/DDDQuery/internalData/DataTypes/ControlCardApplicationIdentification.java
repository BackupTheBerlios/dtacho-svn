/*   Copyright (C) 2007-2008, Martin Barth, Gerald Schnabel

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
 * Information, stored in a control card, related to the identification of the
 * application of the card.
 */
public class ControlCardApplicationIdentification extends DataClass {
	/*
	 * ControlCardApplicationIdentification ::= SEQUENCE {
	 * 	typeOfTachographCardId EquipmentType, 1 byte
	 * 	cardStructureVersion CardStructureVersion, 2 bytes
	 * 	noOfControlActivityRecords NoOfControlActivityRecords, 2 bytes
	 * }
	 * ---
	 * EquipmentType ::= INTEGER(0..255) 
	 * ---
	 * CardStructureVersion ::= OCTET STRING (SIZE(2))
	 * ---
	 * NoOfControlActivityRecords ::= INTEGER(0..2^16-1)
	 * min.: 230
	 * max.: 520
	 */

	private EquipmentType typeOfTachographCardId;
	private byte[] cardStructureVersion;
	private int noOfControlActivityRecords;
	
	
	/**
	 * Constructor for a ControlCardApplicationIdentification object
	 */
	public ControlCardApplicationIdentification() {
		typeOfTachographCardId = new EquipmentType();
		cardStructureVersion = new byte[ 2 ];
		noOfControlActivityRecords = 0;
	}

	/**
	 * Constructor for a ControlCardApplicationIdentification object
	 * 
	 * @param	value	byte array of a ControlCardApplicationIdentification structure
	 * 					whose data is used when the ControlCardApplicationIdentification
	 * 					object is created.
	 */
	public ControlCardApplicationIdentification( byte[] value ) {
		typeOfTachographCardId = new EquipmentType( value[ 0 ] );
		cardStructureVersion = arrayCopy( value, 1, 2 );
		noOfControlActivityRecords = convertIntoUnsigned2ByteInt( arrayCopy( value, 3, 2 ) );
	}

	/**
	 * Returns the implemented type of card of a ControlCardApplicationIdentification object.
	 * 
	 * @return	the implemented type of card of the ControlCardApplicationIdentification object
	 */
	public EquipmentType getTypeOfTachographCardId() {
		return typeOfTachographCardId;
	}

	/**
	 * Sets the implemented type of card of a ControlCardApplicationIdentification object.
	 * 
	 * @param	typeOfTachographCardId	the implemented type of card to be set for the
	 * 									ControlCardApplicationIdentification object
	 */
	public void setTypeOfTachographCardId( EquipmentType typeOfTachographCardId ) {
		this.typeOfTachographCardId = typeOfTachographCardId;
	}
	
	/**
	 * Returns the version of the structure that is implemented in the card
	 * of a ControlCardApplicationIdentification object.
	 * 
	 * @return	the version of the structure that is implemented in the card
	 * 			of the ControlCardApplicationIdentification object
	 */
	public byte[] getCardStructureVersion() {
		return cardStructureVersion;
	}

	/**
	 * Sets the version of the structure that is implemented in the card
	 * of a ControlCardApplicationIdentification object.
	 * 
	 * @param	cardStructureVersion	the version of the structure that is implemented in the card
	 * 									to be set for the ControlCardApplicationIdentification object
	 */
	public void setCardStructureVersion( byte[] cardStructureVersion ) {
		this.cardStructureVersion = arrayCopy( cardStructureVersion, 0, 2 );
	}

	/**
	 * Returns the number of company activity records the card can store
	 * of a ControlCardApplicationIdentification object.
	 * 
	 * @return	the number of company activity records the card can store
	 * 			of the ControlCardApplicationIdentification object
	 */
	public int getNoOfControlActivityRecords() {
		return noOfControlActivityRecords;
	}

	/**
	 * Sets the number of company activity records the card can store
	 * of a ControlCardApplicationIdentification object.
	 * 
	 * @param	noOfControlActivityRecords	the number of company activity records the card can store
	 * 										to be set for the ControlCardApplicationIdentification object
	 */
	public void setNoOfControlActivityRecords( int noOfControlActivityRecords ) {
		this.noOfControlActivityRecords = noOfControlActivityRecords;
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		node.addContent( typeOfTachographCardId.generateXMLElement( "typeOfTachographCardId" ) );
		node.addContent( new Element( "cardStructureVersion" ).setText( new String( cardStructureVersion ) ) );
		node.addContent( new Element( "noOfControlActivityRecords" ).setText( Integer.toString( noOfControlActivityRecords ) ) );

		return node;
	}
}
