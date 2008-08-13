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
 * Information, stored in a company card related to the identification
 * of the application of the card.
 */
public class CompanyCardApplicationIdentification extends DataClass {
	/*
	 * CompanyCardApplicationIdentification ::= SEQUENCE {
	 * 	typeOfTachographCardId EquipmentType, 1 byte
	 * 	cardStructureVersion CardStructureVersion, 2 bytes
	 * 	noOfCompanyActivityRecords NoOfCompanyActivityRecords, 2 bytes
	 * }
	 * ---
	 * EquipmentType ::= INTEGER(0..255) 
	 * ---
	 * CardStructureVersion ::= OCTET STRING (SIZE(2))
	 * ---
	 * NoOfCompanyActivityRecords ::= INTEGER(0..2^16-1)
	 * min.: 230
	 * max.: 520
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 5;

	private EquipmentType typeOfTachographCardId;
	private CardStructureVersion cardStructureVersion;
	private NoOfCompanyActivityRecords noOfCompanyActivityRecords;
	

	/**
	 * Constructor for a CompanyCardApplicationIdentification object
	 */
	public CompanyCardApplicationIdentification() {
		typeOfTachographCardId = new EquipmentType();
		cardStructureVersion = new CardStructureVersion();
		noOfCompanyActivityRecords = new NoOfCompanyActivityRecords();
	}

	/**
	 * Constructor for a CompanyCardApplicationIdentification object
	 * 
	 * @param	value	byte array of a CompanyCardApplicationIdentification structure
	 * 					whose data is used when the CompanyCardApplicationIdentification
	 * 					object is created.
	 */
	public CompanyCardApplicationIdentification( byte[] value ) {
		typeOfTachographCardId = new EquipmentType( value[ 0 ] );
		cardStructureVersion = new CardStructureVersion( arrayCopy( value, 1, 2 ) );
		noOfCompanyActivityRecords = new NoOfCompanyActivityRecords( arrayCopy( value, 3, 2 ) );
	}

	/**
	 * Returns the implemented type of card of a CompanyCardApplicationIdentification object.
	 * 
	 * @return	the implemented type of card of the CompanyCardApplicationIdentification object
	 */
	public EquipmentType getTypeOfTachographCardId() {
		return typeOfTachographCardId;
	}

	/**
	 * Sets the implemented type of card of a CompanyCardApplicationIdentification object.
	 * 
	 * @param	typeOfTachographCardId	the implemented type of card to be set
	 * 									for the CompanyCardApplicationIdentification object
	 */
	public void setTypeOfTachographCardId( EquipmentType typeOfTachographCardId ) {
		this.typeOfTachographCardId = typeOfTachographCardId;
	}

	/**
	 * Returns the version of the structure that is implemented in the card
	 * of a CompanyCardApplicationIdentification object.
	 * 
	 * @return	the version of the structure that is implemented in the card
	 * 			of the CompanyCardApplicationIdentification object
	 */
	public CardStructureVersion getCardStructureVersion() {
		return cardStructureVersion;
	}

	/**
	 * Sets the version of the structure that is implemented in the card
	 * of a CompanyCardApplicationIdentification object.
	 * 
	 * @param	cardStructureVersion	the version of the structure that is implemented in the card
	 * 									to be set for the CompanyCardApplicationIdentification object
	 */
	public void setCardStructureVersion( CardStructureVersion cardStructureVersion ) {
		this.cardStructureVersion = cardStructureVersion;
	}

	/**
	 * Returns the number of company activity records the card can store
	 * of a CompanyCardApplicationIdentification object.
	 * 
	 * @return	the number of company activity records the card can store
	 * 			of the CompanyCardApplicationIdentification object
	 */
	public NoOfCompanyActivityRecords getNoOfCompanyActivityRecords() {
		return noOfCompanyActivityRecords;
	}

	/**
	 * Sets the number of company activity records the card can store
	 * of a CompanyCardApplicationIdentification object.
	 * 
	 * @param	noOfCompanyActivityRecords	the number of company activity records the card can store
	 * 										to be set for the CompanyCardApplicationIdentification object
	 */
	public void setNoOfCompanyActivityRecords( NoOfCompanyActivityRecords noOfCompanyActivityRecords ) {
		this.noOfCompanyActivityRecords = noOfCompanyActivityRecords;
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		node.addContent( typeOfTachographCardId.generateXMLElement( "typeOfTachographCardId" ) );
		node.addContent( cardStructureVersion.generateXMLElement( "cardStructureVersion" ) );
		node.addContent( noOfCompanyActivityRecords.generateXMLElement( "noOfCompanyActivityRecords" ) );

		return node;
	}
}
