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

public class VehicleRegistrationIdentification extends DataClass {
	/*
	 * VehicleRegistrationIdentification ::= SEQUENCE {
	 * 	vehicleRegistrationNation NationNumeric, 1 byte
	 * 	vehicleRegistrationNumber VehicleRegistrationNumber, 14 bytes
	 * }
	 */

	private NationNumeric nationNumeric;
	private VehicleRegistrationNumber vehicleRegistrationNumber;

	
	/**
	 * Constructor for a VehicleRegistrationIdentification object
	 * 
	 * @param	value	byte array of a VehicleRegistrationIdentification structure
	 * 					whose data is used when the VehicleRegistrationIdentification
	 * 					object is created.
	 */
	public VehicleRegistrationIdentification(byte [] value){
		nationNumeric = new NationNumeric(value[0]);
		vehicleRegistrationNumber = new VehicleRegistrationNumber( arrayCopy(value, 1, 14));
	}

	public NationNumeric getNationNumeric() {
		return nationNumeric;
	}

	public void setNationNumeric(NationNumeric nationNumeric) {
		this.nationNumeric = nationNumeric;
	}

	public VehicleRegistrationNumber getVehicleRegistrationNumber() {
		return vehicleRegistrationNumber;
	}

	public void setVehicleRegistrationNumber(
			VehicleRegistrationNumber vehicleRegistrationNumber) {
		this.vehicleRegistrationNumber = vehicleRegistrationNumber;
	}
	
	@Override
	public Element generateXMLElement(String name) {
		Element node = new Element(name);
		node.addContent( nationNumeric.generateXMLElement("nationNumeric") );
		node.addContent( vehicleRegistrationNumber.generateXMLElement("vehicleRegistrationNumber"));
		
		return node;
	}
}
