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

public class CardControlActivityDataRecord extends DataClass {
	/*
	 * CardControlActivityDataRecord ::= SEQUENCE {
	 * 	controlType ControlType, 1 byte
	 * 	controlTime TimeReal, 4 bytes
	 * 	controlCardNumber FullCardNumber, 18 bytes
	 * 	controlVehicleRegistration VehicleRegistrationIdentification, 15 bytes
	 * 	controlDownloadPeriodBegin TimeReal, 4 bytes
	 * 	controlDownloadPeriodEnd TimeReal, 4 bytes
	 * }
	 */

	private ControlType controlType;
	private TimeReal controlTime;
	private FullCardNumber controlCardNumber;
	private VehicleRegistrationIdentification controlVehicleRegistration;
	private TimeReal controlDownloadPeriodBegin;
	private TimeReal controlDownloadPeriodEnd;

	public CardControlActivityDataRecord(byte[] value){
		controlType = new ControlType(value[0]);
		controlTime = new TimeReal( arrayCopy(value, 1, 4));
		controlCardNumber = new FullCardNumber( arrayCopy(value, 5, 18));
		controlVehicleRegistration = new VehicleRegistrationIdentification( arrayCopy(value, 23, 15));
		controlDownloadPeriodBegin = new TimeReal( arrayCopy(value, 38, 4));
		controlDownloadPeriodEnd = new TimeReal( arrayCopy(value, 42, 4));
	}

	public FullCardNumber getControlCardNumber() {
		return controlCardNumber;
	}

	public void setControlCardNumber(FullCardNumber controlCardNumber) {
		this.controlCardNumber = controlCardNumber;
	}

	public TimeReal getControlDownloadPeriodBegin() {
		return controlDownloadPeriodBegin;
	}

	public void setControlDownloadPeriodBegin(TimeReal controlDownloadPeriodBegin) {
		this.controlDownloadPeriodBegin = controlDownloadPeriodBegin;
	}

	public TimeReal getControlDownloadPeriodEnd() {
		return controlDownloadPeriodEnd;
	}

	public void setControlDownloadPeriodEnd(TimeReal controlDownloadPeriodEnd) {
		this.controlDownloadPeriodEnd = controlDownloadPeriodEnd;
	}

	public TimeReal getControlTime() {
		return controlTime;
	}

	public void setControlTime(TimeReal controlTime) {
		this.controlTime = controlTime;
	}

	public ControlType getControlType() {
		return controlType;
	}

	public void setControlType(ControlType controlType) {
		this.controlType = controlType;
	}

	public VehicleRegistrationIdentification getControlVehicleRegistration() {
		return controlVehicleRegistration;
	}

	public void setControlVehicleRegistration(
			VehicleRegistrationIdentification controlVehicleRegistration) {
		this.controlVehicleRegistration = controlVehicleRegistration;
	}

	@Override
	public Element generateXMLElement(String name) {
		/*
		 * CardControlActivityDataRecord ::= SEQUENCE {
		 * 	controlType ControlType, 1byte
		 * 	controlTime TimeReal, 4byte;
		 * 	controlCardNumber FullCardNumber, 18byte;
		 * 	controlVehicleRegistration VehicleRegistrationIdentification, 15byte,
		 * 	controlDownloadPeriodBegin TimeReal, 4byte
		 * 	controlDownloadPeriodEnd TimeReal, 4byte
		 * }
		 */

		Element node = new Element(name);
		node.addContent( controlType.generateXMLElement("controlType") );
		node.addContent( controlTime.generateXMLElement("controlTime") );
		node.addContent( controlCardNumber.generateXMLElement("controlCardNumber") );
		node.addContent( controlVehicleRegistration.generateXMLElement("controlVehicleRegistration") );
		node.addContent( controlDownloadPeriodBegin.generateXMLElement("controlDownloadPeriodBegin") );
		node.addContent( controlDownloadPeriodEnd.generateXMLElement("controlDownloadPeriodEnd") );

		return node;
	}	
}
