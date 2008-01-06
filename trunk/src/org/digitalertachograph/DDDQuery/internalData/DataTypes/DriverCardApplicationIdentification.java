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

public 	class DriverCardApplicationIdentification extends DataClass{
	/*
	 * DriverCardApplicationIdentification ::= SEQUENCE {
	 *  typeOfTachographCardId EquipmentType, 1 byte,				0x00
	 *  cardStructureVersion CardStructureVersion, 2 bytes			0x0000
	 *  noOfEventsPerType NoOfEventsPerType, 1 byte,				0x00
	 *  noOfFaultsPerType NoOfFaultsPerType, 1 byte,				0x00
	 *  activityStructureLength CardActivityLengthRange, 2 bytes,	0x0000 
	 *  noOfCardVehicleRecords NoOfCardVehicleRecords, 2 bytes,		0x0000
	 *  noOfCardPlaceRecords NoOfCardPlaceRecords, 1 byte,			0x00
	 * }
	 * ---
	 * CardStructureVersion ::= OCTET STRING (SIZE(2))
	 * ---
	 * CardActivityLengthRange ::= INTEGER(0..2^16-1)
	 * min.:  5544 bytes (28 days * 93 activity changes)
	 * max.: 13776 bytes (28 days * 240 activity changes)
	 * ---
	 * NoOfCardVehicleRecords ::= INTEGER(0..2^16-1)
	 * min.:  84
	 * max.: 200
	 * ---
	 * NoOfCardPlaceRecords ::= INTEGER(0..255)
	 * min.:  84
	 * max.: 112
	 */
	
	private EquipmentType typeOfTachographCardId;
	private byte[] cardStructureVersion;
	private short noOfEventsPerType;
	private short noOfFaultsPerType;
	private int activityStructureLength;
	private int noOfCardVehicleRecords;
	private short noOfCardPlaceRecords;
	

	/**
	 * Constructor for a DriverCardApplicationIdentification object
	 */
	public DriverCardApplicationIdentification() {
		typeOfTachographCardId = new EquipmentType();
		cardStructureVersion = new byte[2];
		noOfEventsPerType = 0;
		noOfFaultsPerType = 0;
		activityStructureLength = 0;
		noOfCardVehicleRecords = 0;
		noOfCardPlaceRecords = 0;
	}

	/**
	 * Constructor for a DriverCardApplicationIdentification object
	 * 
	 * @param	value	byte array of a DriverCardApplicationIdentification structure
	 * 					whose data is used when the DriverCardApplicationIdentification
	 * 					object is created.
	 */
	public DriverCardApplicationIdentification(byte[] value) {
		typeOfTachographCardId = new EquipmentType( value[0] );
		cardStructureVersion = arrayCopy(value, 1, 2);
		noOfEventsPerType = convertIntoUnsigned1ByteInt(value[3]);
		noOfFaultsPerType = convertIntoUnsigned1ByteInt(value[4]);
		activityStructureLength = convertIntoUnsigned2ByteInt(arrayCopy(value, 5, 2));
		noOfCardVehicleRecords = convertIntoUnsigned2ByteInt(arrayCopy(value, 7, 2));
		noOfCardPlaceRecords = convertIntoUnsigned1ByteInt(value[9]);
	}

	/**
	 * Returns the implemented type of card of a DriverCardApplicationIdentification object.
	 * 
	 * @return	the implemented type of card of the DriverCardApplicationIdentification object
	 */
	public EquipmentType getTypeOfTachographCardId() {
		return typeOfTachographCardId;
	}

	/**
	 * Sets the implemented type of card of a DriverCardApplicationIdentification object.
	 * 
	 * @param	typeOfTachographCardId	the implemented type of card to be set
	 * 									for the DriverCardApplicationIdentification object
	 */
	public void setTypeOfTachographCardId(EquipmentType typeOfTachographCardId) {
		this.typeOfTachographCardId = typeOfTachographCardId;
	}

	/**
	 * Returns the version of the structure that is implemented in the card
	 * of a DriverCardApplicationIdentification object.
	 * 
	 * @return	the version of the structure that is implemented in the card
	 * 			of the DriverCardApplicationIdentification object
	 */
	public byte[] getCardStructureVersion() {
		return cardStructureVersion;
	}

	/**
	 * Sets the version of the structure that is implemented in the card
	 * of a DriverCardApplicationIdentification object.
	 * 
	 * @param	cardStructureVersion	the version of the structure that is implemented in the card to be set
	 * 									for the DriverCardApplicationIdentification object
	 */
	public void setCardStructureVersion(byte[] cardStructureVersion) {
		this.cardStructureVersion = cardStructureVersion;
	}

	/**
	 * Returns the number of events per type of event the card can record
	 * of a DriverCardApplicationIdentification object.
	 * 
	 * @return	the number of events per type of event the card can record
	 * 			of the DriverCardApplicationIdentification object
	 */
	public short getNoOfEventsPerType() {
		return noOfEventsPerType;
	}

	/**
	 * Sets the number of events per type of event the card can record
	 * of a DriverCardApplicationIdentification object.
	 * 
	 * @param	noOfEventsPerType	the number of events per type of event the card can record to be set
	 * 								for the DriverCardApplicationIdentification object
	 */
	public void setNoOfEventsPerType(short noOfEventsPerType) {
		this.noOfEventsPerType = noOfEventsPerType;
	}

	/**
	 * Returns the number of faults per type of fault the card can record
	 * of a DriverCardApplicationIdentification object.
	 * 
	 * @return	the number of faults per type of fault the card can record
	 * 			of the DriverCardApplicationIdentification object
	 */
	public short getNoOfFaultsPerType() {
		return noOfFaultsPerType;
	}

	/**
	 * Sets the number of faults per type of fault the card can record
	 * of a DriverCardApplicationIdentification object.
	 * 
	 * @param	noOfFaultsPerType	the number of faults per type of fault the card can record to be set
	 * 								for the DriverCardApplicationIdentification object
	 */
	public void setNoOfFaultsPerType(short noOfFaultsPerType) {
		this.noOfFaultsPerType = noOfFaultsPerType;
	}

	/**
	 * Returns the number of bytes available for storing activity records
	 * of a DriverCardApplicationIdentification object.
	 * 
	 * @return	the number of bytes available for storing activity records
	 * 			of the DriverCardApplicationIdentification object
	 */
	public int getActivityStructureLength() {
		return activityStructureLength;
	}

	/**
	 * Sets the number of bytes available for storing activity records
	 * of a DriverCardApplicationIdentification object.
	 * 
	 * @param	activityStructureLength		the number of bytes available for storing activity records to be set
	 * 										for the DriverCardApplicationIdentification object
	 */
	public void setActivityStructureLength(int activityStructureLength) {
		this.activityStructureLength = activityStructureLength;
	}

	/**
	 * Returns the number of vehicle records the card can contain
	 * of a DriverCardApplicationIdentification object.
	 * 
	 * @return	the number of vehicle records the card can contain
	 * 			of the DriverCardApplicationIdentification object
	 */
	public int getNoOfCardVehicleRecords() {
		return noOfCardVehicleRecords;
	}

	/**
	 * Sets the number of vehicle records the card can contain
	 * of a DriverCardApplicationIdentification object.
	 * 
	 * @param	noOfCardVehicleRecords		the number of vehicle records the card can contain to be set
	 * 										for the DriverCardApplicationIdentification object
	 */
	public void setNoOfCardVehicleRecords(int noOfCardVehicleRecords) {
		this.noOfCardVehicleRecords = noOfCardVehicleRecords;
	}

	/**
	 * Returns the number of places the card can record
	 * of a DriverCardApplicationIdentification object.
	 * 
	 * @return	the number of places the card can record
	 * 			of the DriverCardApplicationIdentification object
	 */
	public short getNoOfCardPlaceRecords() {
		return noOfCardPlaceRecords;
	}

	/**
	 * Sets the number of places the card can record
	 * of a DriverCardApplicationIdentification object.
	 * 
	 * @param	noOfCardPlaceRecords	the number of places the card can record to be set
	 * 									for the DriverCardApplicationIdentification object
	 */
	public void setNoOfCardPlaceRecords(short noOfCardPlaceRecords) {
		this.noOfCardPlaceRecords = noOfCardPlaceRecords;
	}
	
	@Override
	public Element generateXMLElement(String name) {
		Element node = new Element(name);
		node.addContent( typeOfTachographCardId.generateXMLElement("typeOfTachographCardId"));
		node.addContent( new Element("cardStructureVersion").setText( convertIntoHexString(cardStructureVersion)));
		node.addContent( new Element("noOfEventsPerType").setText( Short.toString( noOfEventsPerType)));
		node.addContent( new Element("noOfFaultsPerType").setText( Short.toString( noOfFaultsPerType)));
		node.addContent( new Element("activityStructureLength").setText( Integer.toString( activityStructureLength)));
		node.addContent( new Element("noOfCardVehicleRecords").setText( Integer.toString(noOfCardVehicleRecords)));
		node.addContent( new Element("noOfCardPlaceRecords").setText( Short.toString(noOfCardPlaceRecords)));
		return node;
	}
}
