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
	 * 	typeOfTachographCardId EquipmentType,
	 * 	cardStructureVersion CardStructureVersion,
	 * 	noOfEventsPerType NoOfEventsPerType,
	 * 	noOfFaultsPerType NoOfFaultsPerType,
	 * 	activityStructureLength CardActivityLengthRange,
	 * 	noOfCardVehicleRecords NoOfCardVehicleRecords,
	 * 	noOfCardPlaceRecords NoOfCardPlaceRecords,
	 * 	noOfCalibrationRecords NoOfCalibrationRecords
	 * }
	 */
	private EquipmentType typeOfTachographCardId;
	private byte[] cardStructureVersion;
	private short noOfEventsPerType;
	private short noOfFaultsPerType;
	private int activityStructureLength;
	private int noOfCardVehicleRecords;
	private short noOfCardPlaceRecords;
	private short noOfCalibrationRecords;
	

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
		activityStructureLength = convertIntoUnsigned2ByteInt( arrayCopy(value, 5, 2));
		noOfCardVehicleRecords = convertIntoUnsigned2ByteInt( arrayCopy(value, 7, 2));
		noOfCardPlaceRecords = convertIntoUnsigned1ByteInt(value[9]);
		noOfCalibrationRecords = convertIntoUnsigned1ByteInt( value[10] );
		
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

	public short getNoOfCalibrationRecords() {
		return noOfCalibrationRecords;
	}

	public void setNoOfCalibrationRecords(short noOfCalibrationRecords) {
		this.noOfCalibrationRecords = noOfCalibrationRecords;
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

	@Override
	public Element generateXMLElement(String name) {
		Element node = new Element(name);

//		private int activityStructureLength;
//		private int noOfCardVehicleRecords;
//		private short noOfCardPlaceRecords;
//		private short noOfCalibrationRecords;
		
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
