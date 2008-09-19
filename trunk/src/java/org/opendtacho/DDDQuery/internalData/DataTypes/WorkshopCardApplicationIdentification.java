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
 * Information, stored in a workshop card related to the identification
 * of the application of the card.
 */
public class WorkshopCardApplicationIdentification extends DataClass {
	/*
	 * WorkshopCardApplicationIdentification ::= SEQUENCE {
	 * 	typeOfTachographCardId EquipmentType, 1 byte
	 * 	cardStructureVersion CardStructureVersion, 2 bytes
	 * 	noOfEventsPerType NoOfEventsPerType, 1 byte
	 * 	noOfFaultsPerType NoOfFaultsPerType, 1 byte
	 * 	activityStructureLength CardActivityLengthRange, 2 bytes
	 * 	noOfCardVehicleRecords NoOfCardVehicleRecords, 2 bytes
	 * 	noOfCardPlaceRecords NoOfCardPlaceRecords, 1 byte
	 * 	noOfCalibrationRecords NoOfCalibrationRecords, 1 byte
	 * }
	 * ---
	 * EquipmentType ::= INTEGER(0..255) 
	 * ---
	 * CardStructureVersion ::= OCTET STRING (SIZE(2))
	 * ---
	 * NoOfEventsPerType ::=  INTEGER(0..255)
	 * min.: 3
	 * max.: 3
	 * ---
	 * NoOfFaultsPerType ::= INTEGER(0..255)
	 * min.: 6
	 * max.: 6
	 * ---
	 * CardActivityLengthRange ::= INTEGER(0..2^16-1)
	 * min.: 198 bytes (1 day * 93 activity changes)
	 * max.: 492 bytes (1 day * 240 activity changes)
	 * ---
	 * NoOfCardVehicleRecords ::= INTEGER(0..2^16-1)
	 * min.: 4
	 * max.: 8
	 * ---
	 * NoOfCardPlaceRecords ::= INTEGER(0..255)
	 * min.: 6
	 * max.: 8
	 * ---
	 * NoOfCalibrationsRecords ::= INTEGER(0..255)
	 * min.:  88
	 * max.: 255
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 11;

	private EquipmentType typeOfTachographCardId;
	private CardStructureVersion cardStructureVersion;
	private NoOfEventsPerType noOfEventsPerType;
	private NoOfFaultsPerType noOfFaultsPerType;
	private CardActivityLengthRange activityStructureLength;
	private NoOfCardVehicleRecords noOfCardVehicleRecords;
	private NoOfCardPlaceRecords noOfCardPlaceRecords;
	private NoOfCalibrationRecords noOfCalibrationRecords;
	

	/**
	 * Constructor for a WorkshopCardApplicationIdentification object
	 */
	public WorkshopCardApplicationIdentification() {
		typeOfTachographCardId = new EquipmentType();
		cardStructureVersion = new CardStructureVersion();
		noOfEventsPerType = new NoOfEventsPerType();
		noOfFaultsPerType = new NoOfFaultsPerType();
		activityStructureLength = new CardActivityLengthRange();
		noOfCardVehicleRecords = new NoOfCardVehicleRecords();
		noOfCardPlaceRecords = new NoOfCardPlaceRecords();
		noOfCalibrationRecords = new NoOfCalibrationRecords();
	}

	/**
	 * Constructor for a WorkshopCardApplicationIdentification object
	 * 
	 * @param	value	byte array of a WorkshopCardApplicationIdentification structure
	 * 					whose data is used when the WorkshopCardApplicationIdentification
	 * 					object is created.
	 */
	public WorkshopCardApplicationIdentification( byte[] value ){
		typeOfTachographCardId = new EquipmentType( value[ 0 ] );
		cardStructureVersion = new CardStructureVersion( arrayCopy( value, 1, 2 ) );
		noOfEventsPerType = new NoOfEventsPerType( value[ 3 ] );
		noOfFaultsPerType = new NoOfFaultsPerType( value[ 4 ] );
		activityStructureLength = new CardActivityLengthRange( arrayCopy( value, 5, 2 ) );
		noOfCardVehicleRecords = new NoOfCardVehicleRecords( arrayCopy( value, 7, 2 ) );
		noOfCardPlaceRecords = new NoOfCardPlaceRecords( value[ 9 ] );
		noOfCalibrationRecords = new NoOfCalibrationRecords( value[ 10 ] );
	}

	/**
	 * Returns the implemented type of card of a WorkshopCardApplicationIdentification object.
	 * 
	 * @return	the implemented type of card of the WorkshopCardApplicationIdentification object
	 */
	public EquipmentType getTypeOfTachographCardId() {
		return typeOfTachographCardId;
	}

	/**
	 * Sets the implemented type of card of a WorkshopCardApplicationIdentification object.
	 * 
	 * @param	typeOfTachographCardId	the implemented type of card to be set for the
	 * 									WorkshopCardApplicationIdentification object
	 */
	public void setTypeOfTachographCardId( EquipmentType typeOfTachographCardId ) {
		this.typeOfTachographCardId = typeOfTachographCardId;
	}

	/**
	 * Returns the version of the structure that is implemented in the card
	 * of a WorkshopCardApplicationIdentification object.
	 * 
	 * @return	the version of the structure that is implemented in the card
	 * 			of the WorkshopCardApplicationIdentification object
	 */
	public CardStructureVersion getCardStructureVersion() {
		return cardStructureVersion;
	}

	/**
	 * Sets the version of the structure that is implemented in the card
	 * of a WorkshopCardApplicationIdentification object.
	 * 
	 * @param	cardStructureVersion	the version of the structure that is implemented in the card
	 * 									to be set for the WorkshopCardApplicationIdentification object
	 */
	public void setCardStructureVersion( CardStructureVersion cardStructureVersion ) {
		this.cardStructureVersion = cardStructureVersion;
	}

	/**
	 * Returns the number of events per type of event the card can record
	 * of a WorkshopCardApplicationIdentification object.
	 * 
	 * @return	the number of events per type of event the card can record
	 * 			of the WorkshopCardApplicationIdentification object
	 */
	public NoOfEventsPerType getNoOfEventsPerType() {
		return noOfEventsPerType;
	}

	/**
	 * Sets the number of events per type of event the card can record
	 * of a WorkshopCardApplicationIdentification object.
	 * 
	 * @param	noOfEventsPerType	the number of events per type of event the card can record to be set
	 * 								for the WorkshopCardApplicationIdentification object
	 */
	public void setNoOfEventsPerType( NoOfEventsPerType noOfEventsPerType ) {
		this.noOfEventsPerType = noOfEventsPerType;
	}

	/**
	 * Returns the number of faults per type of fault the card can record
	 * of a WorkshopCardApplicationIdentification object.
	 * 
	 * @return	the number of faults per type of fault the card can record
	 * 			of the WorkshopCardApplicationIdentification object
	 */
	public NoOfFaultsPerType getNoOfFaultsPerType() {
		return noOfFaultsPerType;
	}

	/**
	 * Sets the number of faults per type of fault the card can record
	 * of a WorkshopCardApplicationIdentification object.
	 * 
	 * @param	noOfFaultsPerType	the number of faults per type of fault the card can record to be set
	 * 								for the WorkshopCardApplicationIdentification object
	 */
	public void setNoOfFaultsPerType( NoOfFaultsPerType noOfFaultsPerType ) {
		this.noOfFaultsPerType = noOfFaultsPerType;
	}

	/**
	 * Returns the number of bytes available for storing activity records
	 * of a WorkshopCardApplicationIdentification object.
	 * 
	 * @return	the number of bytes available for storing activity records
	 * 			of the WorkshopCardApplicationIdentification object
	 */
	public CardActivityLengthRange getActivityStructureLength() {
		return activityStructureLength;
	}

	/**
	 * Sets the number of bytes available for storing activity records
	 * of a WorkshopCardApplicationIdentification object.
	 * 
	 * @param	activityStructureLength		the number of bytes available for storing activity records to be set
	 * 										for the WorkshopCardApplicationIdentification object
	 */
	public void setActivityStructureLength( CardActivityLengthRange activityStructureLength ) {
		this.activityStructureLength = activityStructureLength;
	}

	/**
	 * Returns the number of vehicle records the card can contain
	 * of a WorkshopCardApplicationIdentification object.
	 * 
	 * @return	the number of vehicle records the card can contain
	 * 			of the WorkshopCardApplicationIdentification object
	 */
	public NoOfCardVehicleRecords getNoOfCardVehicleRecords() {
		return noOfCardVehicleRecords;
	}

	/**
	 * Sets the number of vehicle records the card can contain
	 * of a WorkshopCardApplicationIdentification object.
	 * 
	 * @param	noOfCardVehicleRecords		the number of vehicle records the card can contain to be set
	 * 										for the WorkshopCardApplicationIdentification object
	 */
	public void setNoOfCardVehicleRecords( NoOfCardVehicleRecords noOfCardVehicleRecords ) {
		this.noOfCardVehicleRecords = noOfCardVehicleRecords;
	}

	/**
	 * Returns the number of places the card can record
	 * of a WorkshopCardApplicationIdentification object.
	 * 
	 * @return	the number of places the card can record
	 * 			of the WorkshopCardApplicationIdentification object
	 */
	public NoOfCardPlaceRecords getNoOfCardPlaceRecords() {
		return noOfCardPlaceRecords;
	}

	/**
	 * Sets the number of places the card can record
	 * of a WorkshopCardApplicationIdentification object.
	 * 
	 * @param	noOfCardPlaceRecords	the number of places the card can record to be set
	 * 									for the WorkshopCardApplicationIdentification object
	 */
	public void setNoOfCardPlaceRecords( NoOfCardPlaceRecords noOfCardPlaceRecords ) {
		this.noOfCardPlaceRecords = noOfCardPlaceRecords;
	}

	/**
	 * Returns the number of calibration records the card can store
	 * of a WorkshopCardApplicationIdentification object.
	 * 
	 * @return	the number of calibration records the card can store
	 * 			of the WorkshopCardApplicationIdentification object
	 */
	public NoOfCalibrationRecords getNoOfCalibrationRecords() {
		return noOfCalibrationRecords;
	}

	/**
	 * Sets the number of calibration records the card can store
	 * of a WorkshopCardApplicationIdentification object.
	 * 
	 * @param	noOfCalibrationRecords	the number of calibration records the card can store to be set
	 * 									for the WorkshopCardApplicationIdentification object
	 */
	public void setNoOfCalibrationRecords( NoOfCalibrationRecords noOfCalibrationRecords ) {
		this.noOfCalibrationRecords = noOfCalibrationRecords;
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		node.addContent( typeOfTachographCardId.generateXMLElement( "typeOfTachographCardId" ) );
		node.addContent( cardStructureVersion.generateXMLElement( "cardStructureVersion" ) );
		node.addContent( noOfEventsPerType.generateXMLElement( "noOfEventsPerType" ) );
		node.addContent( noOfFaultsPerType.generateXMLElement( "noOfFaultsPerType" ) );
		node.addContent( activityStructureLength.generateXMLElement( "activityStructureLength" ) );
		node.addContent( noOfCardVehicleRecords.generateXMLElement( "noOfCardVehicleRecords" ) );
		node.addContent( noOfCardPlaceRecords.generateXMLElement( "noOfCardPlaceRecords" ) );
		node.addContent( noOfCalibrationRecords.generateXMLElement( "noOfCalibrationRecords" ) );

		return node;
	}
}
