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

public class CardVehicleRecord extends DataClass {
	/*
	 * CardVehicleRecord ::= SEQUENCE {
	 * 	vehicleOdometerBegin OdometerShort,	3 bytes
	 * 	vehicleOdometerEnd OdometerShort,	3 bytes
	 * 	vehicleFirstUse TimeReal,			4 bytes
	 * 	vehicleLastUse TimeReal,			4 bytes
	 * 	vehicleRegistration VehicleRegistrationIdentification,	15 bytes
	 * 	vuDataBlockCounter VuDataBlockCounter, 2 bytes
	 * }
	 */
	
	private int vehicleOdometerBegin;
	private int vehicleOdometerEnd;
	private TimeReal vehicleFirstUse;
	private TimeReal vehicleLastUse;
	private VehicleRegistrationIdentification vehicleRegistration;
	private String vuDataBlockCounter;
	
	
	/**
	 * Constructor for a CardVehicleRecord object
	 * 
	 * @param	value	byte array of a CardVehicleRecord structure
	 * 					whose data is used when the CardVehicleRecord
	 * 					object is created.
	 */
	public CardVehicleRecord(byte[] value){
		vehicleOdometerBegin = convertIntoUnsigned3ByteInt( arrayCopy(value, 0, 3));
		vehicleOdometerEnd = convertIntoUnsigned3ByteInt( arrayCopy(value, 3, 3));
		vehicleFirstUse = new TimeReal( arrayCopy(value, 6, 4));
		vehicleLastUse = new TimeReal( arrayCopy(value, 10, 4));
		vehicleRegistration = new VehicleRegistrationIdentification( arrayCopy(value, 14, 15));
		vuDataBlockCounter = convertIntoBCDString( arrayCopy(value, 29, 2));
	}


	public TimeReal getVehicleFirstUse() {
		return vehicleFirstUse;
	}


	public void setVehicleFirstUse(TimeReal vehicleFirstUse) {
		this.vehicleFirstUse = vehicleFirstUse;
	}


	public TimeReal getVehicleLastUse() {
		return vehicleLastUse;
	}


	public void setVehicleLastUse(TimeReal vehicleLastUse) {
		this.vehicleLastUse = vehicleLastUse;
	}


	public int getVehicleOdometerBegin() {
		return vehicleOdometerBegin;
	}


	public void setVehicleOdometerBegin(int vehicleOdometerBegin) {
		this.vehicleOdometerBegin = vehicleOdometerBegin;
	}


	public int getVehicleOdometerEnd() {
		return vehicleOdometerEnd;
	}


	public void setVehicleOdometerEnd(int vehicleOdometerEnd) {
		this.vehicleOdometerEnd = vehicleOdometerEnd;
	}


	public VehicleRegistrationIdentification getVehicleRegistration() {
		return vehicleRegistration;
	}


	public void setVehicleRegistration(
			VehicleRegistrationIdentification vehicleRegistration) {
		this.vehicleRegistration = vehicleRegistration;
	}


	public String getVuDataBlockCounter() {
		return vuDataBlockCounter;
	}


	public void setVuDataBlockCounter(String vuDataBlockCounter) {
		this.vuDataBlockCounter = vuDataBlockCounter;
	}
	
	@Override
	public Element generateXMLElement(String name) {
		
		Element node = new Element(name);
		node.addContent( new Element("vehicleOdometerBegin").setText(Integer.toString(vehicleOdometerBegin)));
		node.addContent( new Element("vehicleOdometerEnd").setText(Integer.toString(vehicleOdometerEnd)));
		node.addContent( vehicleFirstUse.generateXMLElement("vehicleFirstUse"));
		node.addContent( vehicleLastUse.generateXMLElement("vehicleLastUse"));
		node.addContent( vehicleRegistration.generateXMLElement("vehicleRegistration"));
		node.addContent( new Element("vuDataBlockCounter").setText(vuDataBlockCounter));
		
		return node;
	}

}
