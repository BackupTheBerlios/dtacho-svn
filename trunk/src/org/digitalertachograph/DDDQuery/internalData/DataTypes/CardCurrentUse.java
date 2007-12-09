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

public class CardCurrentUse extends DataClass{
	/*
	 * CardCurrentUse ::= SEQUENCE {
	 * 	sessionOpenTime TimeReal, 4 bytes
	 * 	sessionOpenVehicle VehicleRegistrationIdentification, 15 bytes
	 * }
	 */
	private TimeReal sessionOpenTime;
	private VehicleRegistrationIdentification sessionOpenVehicle;
	
	public CardCurrentUse(byte[] value){
		sessionOpenTime = new TimeReal( arrayCopy(value, 0, 4));
		sessionOpenVehicle = new VehicleRegistrationIdentification( arrayCopy(value, 4, 15));
	}

	public TimeReal getSessionOpenTime() {
		return sessionOpenTime;
	}

	public void setSessionOpenTime(TimeReal sessionOpenTime) {
		this.sessionOpenTime = sessionOpenTime;
	}

	public VehicleRegistrationIdentification getSessionOpenVehicle() {
		return sessionOpenVehicle;
	}

	public void setSessionOpenVehicle(
			VehicleRegistrationIdentification sessionOpenVehicle) {
		this.sessionOpenVehicle = sessionOpenVehicle;
	}
	
	@Override
	public Element generateXMLElement(String name) {
		Element node = new Element(name);
		node.addContent(sessionOpenTime.generateXMLElement("sessionOpenTime") );
		node.addContent(sessionOpenVehicle.generateXMLElement("sessionOpenVehicle") );
		return node;
	}

}
