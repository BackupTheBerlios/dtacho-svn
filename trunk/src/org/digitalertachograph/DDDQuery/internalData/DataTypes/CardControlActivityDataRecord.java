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


	/**
	 * Constructor for a CardControlActivityDataRecord object
	 */
	public CardControlActivityDataRecord() {
		controlType = new ControlType();
		controlTime = new TimeReal();
		controlCardNumber = new FullCardNumber();
		controlVehicleRegistration = new VehicleRegistrationIdentification();
		controlDownloadPeriodBegin = new TimeReal();
		controlDownloadPeriodEnd = new TimeReal();
	}

	/**
	 * Constructor for a CardControlActivityDataRecord object
	 * 
	 * @param	value	byte array of a CardControlActivityDataRecord structure
	 * 					whose data is used when the CardControlActivityDataRecord
	 * 					object is created.
	 */
	public CardControlActivityDataRecord(byte[] value) {
		controlType = new ControlType(value[0]);
		controlTime = new TimeReal(arrayCopy(value, 1, 4));
		controlCardNumber = new FullCardNumber(arrayCopy(value, 5, 18));
		controlVehicleRegistration = new VehicleRegistrationIdentification(arrayCopy(value, 23, 15));
		controlDownloadPeriodBegin = new TimeReal(arrayCopy(value, 38, 4));
		controlDownloadPeriodEnd = new TimeReal(arrayCopy(value, 42, 4));
	}

	/**
	 * Returns the type of a control of a CardControlActivityDataRecord object.
	 * 
	 * @return	the type of the control of the CardControlActivityDataRecord object
	 */
	public ControlType getControlType() {
		return controlType;
	}

	/**
	 * Sets the type of a control of a CardControlActivityDataRecord object.
	 * 
	 * @param	controlType		the type of the control to be set for the
	 * 							CardControlActivityDataRecord object
	 */
	public void setControlType(ControlType controlType) {
		this.controlType = controlType;
	}

	/**
	 * Returns the timestamp (date and time) of a control of a CardControlActivityDataRecord object.
	 * 
	 * @return	the timestamp of the control of the CardControlActivityDataRecord object
	 */
	public TimeReal getControlTime() {
		return controlTime;
	}

	/**
	 * Sets the timestamp (date and time) of a control of a CardControlActivityDataRecord object.
	 * 
	 * @param	controlTime		timestamp of the control to be set for the
	 * 							CardControlActivityDataRecord object
	 */
	public void setControlTime(TimeReal controlTime) {
		this.controlTime = controlTime;
	}

	/**
	 * Returns the control card number of the control officer having performed the control
	 * of a CardControlActivityDataRecord object.
	 * 
	 * @return	the control card number of the control officer having performed the control
	 * 			of the CardControlActivityDataRecord object
	 */
	public FullCardNumber getControlCardNumber() {
		return controlCardNumber;
	}

	/**
	 * Sets the control card number of the control officer having performed the control
	 * of a CardControlActivityDataRecord object.
	 * 
	 * @param	controlCardNumber	control card number to be set for the
	 * 								CardControlActivityDataRecord object
	 */
	public void setControlCardNumber(FullCardNumber controlCardNumber) {
		this.controlCardNumber = controlCardNumber;
	}

	/**
	 * Returns the VRN and registering Member State of the vehicle in which the control
	 * happened of a CardControlActivityDataRecord object.
	 * 
	 * @return	the VRN and registering Member State of the vehicle in which the control
	 * 			happened of the CardControlActivityDataRecord object
	 */
	public VehicleRegistrationIdentification getControlVehicleRegistration() {
		return controlVehicleRegistration;
	}

	/**
	 * Sets the VRN and registering Member State of the vehicle in which the control
	 * happened of a CardControlActivityDataRecord object.
	 * 
	 * @param	controlVehicleRegistration		the VRN and registering Member State of the vehicle
	 *				 							in which the control happened to be set for the
	 * 											CardControlActivityDataRecord object
	 */
	public void setControlVehicleRegistration(VehicleRegistrationIdentification controlVehicleRegistration) {
		this.controlVehicleRegistration = controlVehicleRegistration;
	}

	/**
	 * Returns the timestamp (date and time) of a download period beginning
	 * of a CardControlActivityDataRecord object.
	 * 
	 * @return	the timestamp of the download period beginning of the CardControlActivityDataRecord object
	 */
	public TimeReal getControlDownloadPeriodBegin() {
		return controlDownloadPeriodBegin;
	}

	/**
	 * Sets the timestamp (date and time) of a download period beginning
	 * of a CardControlActivityDataRecord object.
	 * 
	 * @param	controlDownloadPeriodBegin	timestamp of the download period beginning to be set for the
	 * 										CardControlActivityDataRecord object
	 */
	public void setControlDownloadPeriodBegin(TimeReal controlDownloadPeriodBegin) {
		this.controlDownloadPeriodBegin = controlDownloadPeriodBegin;
	}

	/**
	 * Returns the timestamp (date and time) of a download period end
	 * of a CardControlActivityDataRecord object.
	 * 
	 * @return	the timestamp of the download period end of the CardControlActivityDataRecord object
	 */
	public TimeReal getControlDownloadPeriodEnd() {
		return controlDownloadPeriodEnd;
	}

	/**
	 * Sets the timestamp (date and time) of a download period end
	 * of a CardControlActivityDataRecord object.
	 * 
	 * @param	controlDownloadPeriodEnd	timestamp of the download period end to be set for the
	 * 										CardControlActivityDataRecord object
	 */
	public void setControlDownloadPeriodEnd(TimeReal controlDownloadPeriodEnd) {
		this.controlDownloadPeriodEnd = controlDownloadPeriodEnd;
	}

	@Override
	public Element generateXMLElement(String name) {
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
