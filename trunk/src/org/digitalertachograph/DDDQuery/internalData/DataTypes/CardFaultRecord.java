/*   Copyright (C) 2007, Martin Barth

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

public class CardFaultRecord extends DataClass {
	/*
	 * CardFaultRecord ::= SEQUENCE {
	 * 	faultType EventFaultType,
	 * 	faultBeginTime TimeReal,
	 * 	faultEndTime TimeReal,
	 * 	faultVehicleRegistration VehicleRegistrationIdentification
	 * }
	 */

	private EventFaultType faultType; 
	private TimeReal faultBeginTime;
	private TimeReal faultEndTime;
	private VehicleRegistrationIdentification faultVehicleRegistration;
	
	public CardFaultRecord(byte[] value){
		faultType = new EventFaultType(value[0]);
		faultBeginTime = new TimeReal(arrayCopy(value, 1, 4));
		faultEndTime   = new TimeReal(arrayCopy(value, 5, 4));
		faultVehicleRegistration = new VehicleRegistrationIdentification( arrayCopy(value, 9, 15));
	}

	public TimeReal getFaultBeginTime() {
		return faultBeginTime;
	}

	public void setFaultBeginTime(TimeReal faultBeginTime) {
		this.faultBeginTime = faultBeginTime;
	}

	public TimeReal getFaultEndTime() {
		return faultEndTime;
	}

	public void setFaultEndTime(TimeReal faultEndTime) {
		this.faultEndTime = faultEndTime;
	}

	public EventFaultType getFaultType() {
		return faultType;
	}

	public void setFaultType(EventFaultType faultType) {
		this.faultType = faultType;
	}

	public VehicleRegistrationIdentification getFaultVehicleRegistration() {
		return faultVehicleRegistration;
	}

	public void setFaultVehicleRegistration(
			VehicleRegistrationIdentification faultVehicleRegistration) {
		this.faultVehicleRegistration = faultVehicleRegistration;
	}
	
	@Override
	public Element generateXMLElement(String name) {
		Element node = new Element(name);

		node.addContent( faultType.generateXMLElement("faultType") );
		node.addContent( faultBeginTime.generateXMLElement("faultBeginTime") );
		node.addContent( faultEndTime.generateXMLElement("faultEndTime") );
		node.addContent( faultVehicleRegistration.generateXMLElement("faultVehicleRegistration") );
		return node;
	}
}
