/*   Copyright (C) 2007, Martin Barth, Gerald Schnabel

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
	 */
	private EquipmentType typeOfTachographCardId;
	private byte[] cardStructureVersion = new byte[2];
	private short noOfEventsPerType;
	private short noOfFaultsPerType;
	private int activityStructureLength;
	private int noOfCardVehicleRecords;
	private short noOfCardPlaceRecords;
	private short noOfCalibrationRecords;
	

	/**
	 * Constructor for a WorkshopCardApplicationIdentification object
	 */
	public WorkshopCardApplicationIdentification(){

	}

	/**
	 * Constructor for a WorkshopCardApplicationIdentification object
	 * 
	 * @param	value	byte array of a WorkshopCardApplicationIdentification structure
	 * 					whose data is used when the WorkshopCardApplicationIdentification
	 * 					object is created.
	 */
	public WorkshopCardApplicationIdentification(byte[] value){
		typeOfTachographCardId = new EquipmentType( value[0] );
		cardStructureVersion = arrayCopy(value, 1, 2);
		noOfEventsPerType = convertIntoUnsigned1ByteInt(value[3]);
		noOfFaultsPerType = convertIntoUnsigned1ByteInt(value[4]);
		activityStructureLength = convertIntoUnsigned2ByteInt(arrayCopy(value, 5, 2));
		noOfCardVehicleRecords = convertIntoUnsigned2ByteInt(arrayCopy(value, 7, 2));
		noOfCardPlaceRecords = convertIntoUnsigned1ByteInt( value[9] );
		noOfCalibrationRecords = convertIntoUnsigned1ByteInt( value[10] );
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
	public void setTypeOfTachographCardId(EquipmentType typeOfTachographCardId) {
		this.typeOfTachographCardId = typeOfTachographCardId;
	}

	/**
	 * Returns the version of the structure that is implemented in the card
	 * of a WorkshopCardApplicationIdentification object.
	 * 
	 * @return	the version of the structure that is implemented in the card
	 * 			of the WorkshopCardApplicationIdentification object
	 */
	public byte[] getCardStructureVersion() {
		return cardStructureVersion;
	}

	/**
	 * Sets the version of the structure that is implemented in the card
	 * of a WorkshopCardApplicationIdentification object.
	 * 
	 * @param	cardStructureVersion	the version of the structure that is implemented in the card
	 * 									to be set for the WorkshopCardApplicationIdentification object
	 */
	public void setCardStructureVersion(byte[] cardStructureVersion) {
		this.cardStructureVersion = cardStructureVersion;
	}

	/**
	 * Returns the number of events per type of event the card can record
	 * of a WorkshopCardApplicationIdentification object.
	 * 
	 * @return	the number of events per type of event the card can record
	 * 			of the WorkshopCardApplicationIdentification object
	 */
	public short getNoOfEventsPerType() {
		return noOfEventsPerType;
	}

	/**
	 * Sets the number of events per type of event the card can record
	 * of a WorkshopCardApplicationIdentification object.
	 * 
	 * @param	noOfEventsPerType	the number of events per type of event the card can record to be set
	 * 								for the WorkshopCardApplicationIdentification object
	 */
	public void setNoOfEventsPerType(short noOfEventsPerType) {
		this.noOfEventsPerType = noOfEventsPerType;
	}

	/**
	 * Returns the number of faults per type of fault the card can record
	 * of a WorkshopCardApplicationIdentification object.
	 * 
	 * @return	the number of faults per type of fault the card can record
	 * 			of the WorkshopCardApplicationIdentification object
	 */
	public short getNoOfFaultsPerType() {
		return noOfFaultsPerType;
	}

	/**
	 * Sets the number of faults per type of fault the card can record
	 * of a WorkshopCardApplicationIdentification object.
	 * 
	 * @param	noOfFaultsPerType	the number of faults per type of fault the card can record to be set
	 * 								for the WorkshopCardApplicationIdentification object
	 */
	public void setNoOfFaultsPerType(short noOfFaultsPerType) {
		this.noOfFaultsPerType = noOfFaultsPerType;
	}

	/**
	 * Returns the number of bytes available for storing activity records
	 * of a WorkshopCardApplicationIdentification object.
	 * 
	 * @return	the number of bytes available for storing activity records
	 * 			of the WorkshopCardApplicationIdentification object
	 */
	public int getActivityStructureLength() {
		return activityStructureLength;
	}

	/**
	 * Sets the number of bytes available for storing activity records
	 * of a WorkshopCardApplicationIdentification object.
	 * 
	 * @param	activityStructureLength		the number of bytes available for storing activity records to be set
	 * 										for the WorkshopCardApplicationIdentification object
	 */
	public void setActivityStructureLength(int activityStructureLength) {
		this.activityStructureLength = activityStructureLength;
	}

	/**
	 * Returns the number of vehicle records the card can contain
	 * of a WorkshopCardApplicationIdentification object.
	 * 
	 * @return	the number of vehicle records the card can contain
	 * 			of the WorkshopCardApplicationIdentification object
	 */
	public int getNoOfCardVehicleRecords() {
		return noOfCardVehicleRecords;
	}

	/**
	 * Sets the number of vehicle records the card can contain
	 * of a WorkshopCardApplicationIdentification object.
	 * 
	 * @param	noOfCardVehicleRecords		the number of vehicle records the card can contain to be set
	 * 										for the WorkshopCardApplicationIdentification object
	 */
	public void setNoOfCardVehicleRecords(int noOfCardVehicleRecords) {
		this.noOfCardVehicleRecords = noOfCardVehicleRecords;
	}

	/**
	 * Returns the number of places the card can record
	 * of a WorkshopCardApplicationIdentification object.
	 * 
	 * @return	the number of places the card can record
	 * 			of the WorkshopCardApplicationIdentification object
	 */
	public short getNoOfCardPlaceRecords() {
		return noOfCardPlaceRecords;
	}

	/**
	 * Sets the number of places the card can record
	 * of a WorkshopCardApplicationIdentification object.
	 * 
	 * @param	noOfCardPlaceRecords	the number of places the card can record to be set
	 * 									for the WorkshopCardApplicationIdentification object
	 */
	public void setNoOfCardPlaceRecords(short noOfCardPlaceRecords) {
		this.noOfCardPlaceRecords = noOfCardPlaceRecords;
	}

	/**
	 * Returns the number of calibration records the card can store
	 * of a WorkshopCardApplicationIdentification object.
	 * 
	 * @return	the number of calibration records the card can store
	 * 			of the WorkshopCardApplicationIdentification object
	 */
	public short getNoOfCalibrationRecords() {
		return noOfCalibrationRecords;
	}

	/**
	 * Sets the number of calibration records the card can store
	 * of a WorkshopCardApplicationIdentification object.
	 * 
	 * @param	noOfCalibrationRecords	the number of calibration records the card can store to be set
	 * 									for the WorkshopCardApplicationIdentification object
	 */
	public void setNoOfCalibrationRecords(short noOfCalibrationRecords) {
		this.noOfCalibrationRecords = noOfCalibrationRecords;
	}

	@Override
	public Element generateXMLElement(String name) {
		Element node = new Element(name);
		
		node.addContent( typeOfTachographCardId.generateXMLElement( "typeOfTachographCardId" ));
		
		Element cardStructureVersionElement = new Element("cardStructureVersion");
		cardStructureVersionElement.setText( convertIntoHexString( cardStructureVersion ));
		node.addContent(cardStructureVersionElement);
		
		Element noOfEventsPerTypElement = new Element("noOfEventsPerType");
		noOfEventsPerTypElement.setText( Short.toString( noOfEventsPerType ));
		node.addContent( noOfEventsPerTypElement );
		
		Element noOfFaultsPerTypElement = new Element("noOfFaultsPerType");
		noOfFaultsPerTypElement.setText( Short.toString( noOfFaultsPerType));
		node.addContent( noOfFaultsPerTypElement );
		
		node.addContent( new Element("activityStructureLength").setText( Integer.toString(activityStructureLength)));
		node.addContent( new Element("noOfCardVehicleRecords").setText( Integer.toString(noOfCardVehicleRecords)));
		node.addContent( new Element("noOfCardPlaceRecords").setText( Short.toString(noOfCardPlaceRecords)));
		node.addContent( new Element("noOfCalibrationRecords").setText( Short.toString(noOfCalibrationRecords)));
		
		return node;
	}
}
