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

//import java.sql.Time;

//import javax.swing.event.DocumentEvent.EventType;

import org.digitalertachograph.DDDQuery.internalData.DataClass;
import org.jdom.Element;

public class CardEventRecord extends DataClass {
	/*
	 * CardEventRecord ::= SEQUENCE {
	 * 	eventType EventFaultType, 1 bytes
	 * 	eventBeginTime TimeReal, 4 bytes
	 * 	eventEndTime TimeReal, 4 bytes
	 * 	eventVehicleRegistration VehicleRegistrationIdentification, 15 bytes
	 * }
	 * -- 
	 * 
	 */

	private EventFaultType eventType; 
	private TimeReal eventBeginTime;
	private TimeReal eventEndTime;
	private VehicleRegistrationIdentification eventVehicleRegistration;
	
	public CardEventRecord(byte[] value){
		eventType = new EventFaultType(value[0]);
		eventBeginTime = new TimeReal(arrayCopy(value, 1, 4));
		eventEndTime   = new TimeReal(arrayCopy(value, 5, 4));
		eventVehicleRegistration = new VehicleRegistrationIdentification( arrayCopy(value, 9, 15));
	}

	public TimeReal getEventBeginTime() {
		return eventBeginTime;
	}

	public void setEventBeginTime(TimeReal eventBeginTime) {
		this.eventBeginTime = eventBeginTime;
	}

	public TimeReal getEventEndTime() {
		return eventEndTime;
	}

	public void setEventEndTime(TimeReal eventEndTime) {
		this.eventEndTime = eventEndTime;
	}

	public EventFaultType getEventType() {
		return eventType;
	}

	public void setEventFaultType(EventFaultType eventType) {
		this.eventType = eventType;
	}

	public VehicleRegistrationIdentification getEventVehicleRegistration() {
		return eventVehicleRegistration;
	}

	public void setEventVehicleRegistration(
			VehicleRegistrationIdentification eventVehicleRegistration) {
		this.eventVehicleRegistration = eventVehicleRegistration;
	}
	
	@Override
	public Element generateXMLElement(String name) {
		Element node = new Element(name);
		node.addContent( eventType.generateXMLElement("eventType") );
		node.addContent( eventBeginTime.generateXMLElement("eventBeginTime"));
		node.addContent( eventEndTime.generateXMLElement("eventEndTime"));
		node.addContent( eventVehicleRegistration.generateXMLElement("eventVehicleRegistration"));
		return node;
	}
}
