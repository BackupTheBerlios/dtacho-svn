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
	 *  typeOfTachographCardId EquipmentType, 				1,	0x00,	1 byte
	 *  cardStructureVersion CardStructureVersion, 			2,	0x0000,	2 bytes
	 *  noOfEventsPerType NoOfEventsPerType,				1,	0x00,	1 byte
	 *  noOfFaultsPerType NoOfFaultsPerType,				1,	0x00,	1 byte
	 *  activityStructureLength CardActivityLengthRange,	2,	0x0000,	2 bytes 
	 *  noOfCardVehicleRecords NoOfCardVehicleRecords,		2,	0x0000,	2 bytes
	 *  noOfCardPlaceRecords NoOfCardPlaceRecords,			1,	0x00,	1 byte
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
	 * 
	 * @param	value	byte array of a DriverCardApplicationIdentification structure
	 * 					whose data is used when the DriverCardApplicationIdentification
	 * 					object is created.
	 */
	public DriverCardApplicationIdentification(byte[] value){
		typeOfTachographCardId = new EquipmentType( value[0] );
		cardStructureVersion = arrayCopy(value, 1, 2);
		noOfEventsPerType = convertIntoUnsigned1ByteInt(value[3]);
		noOfFaultsPerType = convertIntoUnsigned1ByteInt(value[4]);
		activityStructureLength = convertIntoUnsigned2ByteInt( arrayCopy(value, 5, 2));
		noOfCardVehicleRecords = convertIntoUnsigned2ByteInt( arrayCopy(value, 7, 2));
		noOfCardPlaceRecords = convertIntoUnsigned1ByteInt(value[9]);
	}

	public int getActivityStructureLength() {
		return activityStructureLength;
	}

	public void setActivityStructureLength(int activityStructureLength) {
		this.activityStructureLength = activityStructureLength;
	}

	public byte[] getCardStructureVersion() {
		return cardStructureVersion;
	}

	public void setCardStructureVersion(byte[] cardStructureVersion) {
		this.cardStructureVersion = cardStructureVersion;
	}

	public short getNoOfCardPlaceRecords() {
		return noOfCardPlaceRecords;
	}

	public void setNoOfCardPlaceRecords(short noOfCardPlaceRecords) {
		this.noOfCardPlaceRecords = noOfCardPlaceRecords;
	}

	public int getNoOfCardVehicleRecords() {
		return noOfCardVehicleRecords;
	}

	public void setNoOfCardVehicleRecords(int noOfCardVehicleRecords) {
		this.noOfCardVehicleRecords = noOfCardVehicleRecords;
	}

	public short getNoOfEventsPerType() {
		return noOfEventsPerType;
	}

	public void setNoOfEventsPerType(short noOfEventsPerType) {
		this.noOfEventsPerType = noOfEventsPerType;
	}

	public short getNoOfFaultsPerType() {
		return noOfFaultsPerType;
	}

	public void setNoOfFaultsPerType(short noOfFaultsPerType) {
		this.noOfFaultsPerType = noOfFaultsPerType;
	}

	public EquipmentType getTypeOfTachographCardId() {
		return typeOfTachographCardId;
	}

	public void setTypeOfTachographCardId(EquipmentType typeOfTachographCardId) {
		this.typeOfTachographCardId = typeOfTachographCardId;
	}
	

//	private EquipmentType typeOfTachographCardId;
//	private byte[] cardStructureVersion;
//	private short noOfEventsPerType;
//	private short noOfFaultsPerType;
//	private int activityStructureLength;
//	private int noOfCardVehicleRecords;
//	private short noOfCardPlaceRecords;
	
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
