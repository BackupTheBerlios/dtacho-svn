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
import org.jdom.*;

public class WorkshopCardCalibrationRecord extends DataClass {
	/*
	 * WorkshopCardCalibrationRecord ::= SEQUENCE {
	 * 	calibrationPurpose CalibrationPurpose, 1 byte
	 * 	vehicleIdentificationNumber VehicleIdentificationNumber, 17 bytes
	 * 	vehicleRegistration VehicleRegistrationIdentification, 15 bytes
	 * 	wVehicleCharacteristicConstant W-VehicleCharacteristicConstant, 2 bytes
	 * 	kConstantOfRecordingEquipment K-ConstantOfRecordingEquipment, 2 bytes
	 * 	lTyreCircumference L-TyreCircumference, 2 bytes
	 * 	tyreSize TyreSize, 15 bytes
	 * 	authorisedSpeed SpeedAuthorised, 1 byte
	 * 	oldOdometerValue OdometerShort, 3 bytes
	 * 	newOdometerValue OdometerShort, 3 bytes
	 * 	oldTimeValue TimeReal, 4 bytes
	 * 	newTimeValue TimeReal, 4 bytes
	 * 	nextCalibrationDate TimeReal, 4 bytes
	 * 	vuPartNumber VuPartNumber, 16 bytes
	 * 	vuSerialNumber VuSerialNumber, 8 bytes
	 * 	sensorSerialNumber SensorSerialNumber, 8 bytes
	 * }
	 * ---
	 * W-VehicleCharacteristicConstant ::= INTEGER(0..2^16-1)
	 * ---
	 * K-ConstantOfRecordingEquipment ::= INTEGER(0..2^16-1)
	 * ---
	 * L-TyreCircumference ::= INTEGER(0..2^16-1)
	 * ---
	 * Speed ::= INTEGER(0..255)
	 * ---
	 * SpeedAuthorized ::= Speed
	 * ---
	 * OdometerShort ::= INTEGER(0..2^24-1)
	 * ---
	 * VuSerialNumber ::= ExtendedSerialNumber
	 * ---
	 * SensorSerialNumber ::= ExtendedSerialNumber:
	 */
	private CalibrationPurpose calibrationPurpose;
	private VehicleIdentificationNumber vehicleIdentificationNumber;
	private VehicleRegistrationIdentification vehicleRegistration;
	private int wVehicleCharacteristicConstant;
	private int kConstantOfRecordingEquipment;
	private int lTyreCircumference;
	private TyreSize tyreSize;
	private short authorisedSpeed;
	private int oldOdometerValue;
	private int newOdometerValue;
	private TimeReal oldTimeValue;
	private TimeReal newTimeValue;
	private TimeReal nextCalibrationDate;
	private VuPartNumber vuPartNumber;
	private ExtendedSerialNumber vuSerialNumber;
	private ExtendedSerialNumber sensorSerialNumber;

	
	/**
	 * Constructor for a WorkshopCardCalibrationRecord object
	 */
	public WorkshopCardCalibrationRecord() {
		calibrationPurpose = new CalibrationPurpose();
		vehicleIdentificationNumber = new VehicleIdentificationNumber();
		vehicleRegistration = new VehicleRegistrationIdentification();
		wVehicleCharacteristicConstant = 0;
		kConstantOfRecordingEquipment = 0;
		lTyreCircumference = 0;
		tyreSize = new TyreSize();
		authorisedSpeed = 0;
		oldOdometerValue = 0;
		newOdometerValue = 0;
		oldTimeValue = new TimeReal();
		newTimeValue = new TimeReal();
		vuPartNumber = new VuPartNumber();
		vuSerialNumber = new ExtendedSerialNumber();
		sensorSerialNumber = new ExtendedSerialNumber();
	}

	/**
	 * Constructor for a WorkshopCardCalibrationRecord object
	 * 
	 * @param	value	byte array of a WorkshopCardCalibrationRecord structure
	 * 					whose data is used when the WorkshopCardCalibrationRecord
	 * 					object is created.
	 */
	public WorkshopCardCalibrationRecord(byte[] value) {
		calibrationPurpose = new CalibrationPurpose(value[0]);
		vehicleIdentificationNumber = new VehicleIdentificationNumber(arrayCopy(value, 1, 17));
		vehicleRegistration = new VehicleRegistrationIdentification(arrayCopy(value, 18, 15));
		wVehicleCharacteristicConstant = convertIntoUnsigned2ByteInt(arrayCopy(value, 33, 2));
		kConstantOfRecordingEquipment = convertIntoUnsigned2ByteInt(arrayCopy(value, 35, 2));
		lTyreCircumference = convertIntoUnsigned2ByteInt( arrayCopy(value, 37, 2));
		tyreSize = new TyreSize( arrayCopy(value, 39, 15));
		authorisedSpeed = convertIntoUnsigned1ByteInt( value[54] );
		oldOdometerValue = convertIntoUnsigned3ByteInt(arrayCopy(value, 55, 3));
		newOdometerValue = convertIntoUnsigned3ByteInt(arrayCopy(value, 58, 3));
		oldTimeValue = new TimeReal(arrayCopy(value, 61, 4));
		newTimeValue = new TimeReal(arrayCopy(value, 65, 4));
		vuPartNumber = new VuPartNumber(arrayCopy(value, 69, 16));
		vuSerialNumber = new ExtendedSerialNumber(arrayCopy(value, 85, 8));
		sensorSerialNumber = new ExtendedSerialNumber(arrayCopy(value, 93, 8));
	}

	/**
	 * Returns the code explaining why a set of calibration parameters were recorded
	 * of a WorkshopCardCalibrationRecord object.
	 * 
	 * @return	the code explaining why a set of calibration parameters were recorded
	 * 			of the WorkshopCardCalibrationRecord object
	 */
	public CalibrationPurpose getCalibrationPurpose() {
		return calibrationPurpose;
	}

	/**
	 * Sets the code explaining why a set of calibration parameters were recorded
	 * of a WorkshopCardCalibrationRecord object.
	 * 
	 * @param	calibrationPurpose	the code explaining why a set of calibration parameters were recorded
	 * 								to be set for the WorkshopCardCalibrationRecord object
	 */
	public void setCalibrationPurpose(CalibrationPurpose calibrationPurpose) {
		this.calibrationPurpose = calibrationPurpose;
	}

	/**
	 * Returns the Vehicle Information Number (VIN) referring to the vehicle as a whole, normally chassis
	 * serial number or frame number of a WorkshopCardCalibrationRecord object.
	 * 
	 * @return	the Vehicle Information Number (VIN) referring to the vehicle as a whole, normally chassis
	 * 			serial number or frame number of the WorkshopCardCalibrationRecord object
	 */
	public VehicleIdentificationNumber getVehicleIdentificationNumber() {
		return vehicleIdentificationNumber;
	}

	/**
	 * Sets the Vehicle Information Number (VIN) referring to the vehicle as a whole, normally chassis
	 * serial number or frame number of a WorkshopCardCalibrationRecord object.
	 * 
	 * @param	vehicleIdentificationNumber		the Vehicle Information Number (VIN) referring to the vehicle as a whole,
	 * 											normally chassis serial number or frame number to be set for the
	 * 											WorkshopCardCalibrationRecord object
	 */
	public void setVehicleIdentificationNumber(VehicleIdentificationNumber vehicleIdentificationNumber) {
		this.vehicleIdentificationNumber = vehicleIdentificationNumber;
	}

	/**
	 * Returns the Vehicle Registration Number (VRN) and registering Member State
	 * of a WorkshopCardCalibrationRecord object.
	 * 
	 * @return	the Vehicle Registration Number (VRN) and registering Member State
	 * 			of the WorkshopCardCalibrationRecord object
	 */
	public VehicleRegistrationIdentification getVehicleRegistration() {
		return vehicleRegistration;
	}

	/**
	 * Sets the Vehicle Registration Number (VRN) and registering Member State
	 * of a WorkshopCardCalibrationRecord object.
	 * 
	 * @param	vehicleRegistration		the Vehicle Registration Number (VRN) and registering Member State
	 * 									to be set for the WorkshopCardCalibrationRecord object
	 */
	public void setVehicleRegistration(VehicleRegistrationIdentification vehicleRegistration) {
		this.vehicleRegistration = vehicleRegistration;
	}

	/**
	 * Returns the characteristic coefficient of the vehicle
	 * of a WorkshopCardCalibrationRecord object.
	 * 
	 * @return	the characteristic coefficient of the vehicle
	 * 			of the WorkshopCardCalibrationRecord object
	 */
	public int getWVehicleCharacteristicConstant() {
		return wVehicleCharacteristicConstant;
	}

	/**
	 * Sets the characteristic coefficient of the vehicle
	 * of a WorkshopCardCalibrationRecord object.
	 * 
	 * @param	vehicleCharacteristicConstant	the characteristic coefficient of the vehicle
	 * 											to be set for the WorkshopCardCalibrationRecord object
	 */
	public void setWVehicleCharacteristicConstant(int vehicleCharacteristicConstant) {
		wVehicleCharacteristicConstant = vehicleCharacteristicConstant;
	}

	/**
	 * Returns the constant of the recording equipment
	 * of a WorkshopCardCalibrationRecord object.
	 * 
	 * @return	the constant of the recording equipment
	 * 			of the WorkshopCardCalibrationRecord object
	 */
	public int getKConstantOfRecordingEquipment() {
		return kConstantOfRecordingEquipment;
	}

	/**
	 * Sets the constant of the recording equipment
	 * of a WorkshopCardCalibrationRecord object.
	 * 
	 * @param	constantOfRecordingEquipment	the constant of the recording equipment
	 * 											to be set for the WorkshopCardCalibrationRecord object
	 */
	public void setKConstantOfRecordingEquipment(int constantOfRecordingEquipment) {
		kConstantOfRecordingEquipment = constantOfRecordingEquipment;
	}

	/**
	 * Returns the effective circumference of the wheel tyres
	 * of a WorkshopCardCalibrationRecord object.
	 * 
	 * @return	the effective circumference of the wheel tyres
	 * 			of the WorkshopCardCalibrationRecord object
	 */
	public int getLTyreCircumference() {
		return lTyreCircumference;
	}

	/**
	 * Sets the effective circumference of the wheel tyres
	 * of a WorkshopCardCalibrationRecord object.
	 * 
	 * @param	tyreCircumference		the effective circumference of the wheel tyres
	 * 									to be set for the WorkshopCardCalibrationRecord object
	 */
	public void setLTyreCircumference(int tyreCircumference) {
		lTyreCircumference = tyreCircumference;
	}

	/**
	 * Returns the designation of the dimensions of the tyres mounted on the vehicle
	 * of a WorkshopCardCalibrationRecord object.
	 * 
	 * @return	the designation of the dimensions of the tyres mounted on the vehicle
	 * 			of the WorkshopCardCalibrationRecord object
	 */
	public TyreSize getTyreSize() {
		return tyreSize;
	}

	/**
	 * Sets the designation of the dimensions of the tyres mounted on the vehicle
	 * of a WorkshopCardCalibrationRecord object.
	 * 
	 * @param	tyreSize	the designation of the dimensions of the tyres mounted on the vehicle
	 * 						to be set for the WorkshopCardCalibrationRecord object
	 */
	public void setTyreSize(TyreSize tyreSize) {
		this.tyreSize = tyreSize;
	}

	/**
	 * Returns the maximum authorised speed of the vehicle
	 * of a WorkshopCardCalibrationRecord object.
	 * 
	 * @return	the maximum authorised speed of the vehicle
	 * 			of the WorkshopCardCalibrationRecord object
	 */
	public short getAuthorisedSpeed() {
		return authorisedSpeed;
	}

	/**
	 * Sets the maximum authorised speed of the vehicle
	 * of a WorkshopCardCalibrationRecord object.
	 * 
	 * @param	authorisedSpeed		the maximum authorised speed of the vehicle
	 * 								to be set for the WorkshopCardCalibrationRecord object
	 */
	public void setAuthorisedSpeed(short authorisedSpeed) {
		this.authorisedSpeed = authorisedSpeed;
	}

	/**
	 * Returns the old value of the odometer
	 * of a WorkshopCardCalibrationRecord object.
	 * 
	 * @return	the old value of the odometer
	 * 			of the WorkshopCardCalibrationRecord object
	 */
	public int getOldOdometerValue() {
		return oldOdometerValue;
	}

	/**
	 * Sets the old value of the odometer
	 * of a WorkshopCardCalibrationRecord object.
	 * 
	 * @param	oldOdometerValue	the old value of the odometer
	 * 								to be set for the WorkshopCardCalibrationRecord object
	 */
	public void setOldOdometerValue(int oldOdometerValue) {
		this.oldOdometerValue = oldOdometerValue;
	}

	/**
	 * Returns the new value of the odometer
	 * of a WorkshopCardCalibrationRecord object.
	 * 
	 * @return	the new value of the odometer
	 * 			of the WorkshopCardCalibrationRecord object
	 */
	public int getNewOdometerValue() {
		return newOdometerValue;
	}

	/**
	 * Sets the new value of the odometer
	 * of a WorkshopCardCalibrationRecord object.
	 * 
	 * @param	newOdometerValue	the new value of the odometer
	 * 								to be set for the WorkshopCardCalibrationRecord object
	 */
	public void setNewOdometerValue(int newOdometerValue) {
		this.newOdometerValue = newOdometerValue;
	}

	/**
	 * Returns the old value of date and time
	 * of a WorkshopCardCalibrationRecord object.
	 * 
	 * @return	the old value of date and time
	 * 			of the WorkshopCardCalibrationRecord object
	 */
	public TimeReal getOldTimeValue() {
		return oldTimeValue;
	}

	/**
	 * Sets the old value of date and time
	 * of a WorkshopCardCalibrationRecord object.
	 * 
	 * @param	oldTimeValue		the old value of date and time
	 * 								to be set for the WorkshopCardCalibrationRecord object
	 */
	public void setOldTimeValue(TimeReal oldTimeValue) {
		this.oldTimeValue = oldTimeValue;
	}

	/**
	 * Returns the new value of date and time
	 * of a WorkshopCardCalibrationRecord object.
	 * 
	 * @return	the new value of date and time
	 * 			of the WorkshopCardCalibrationRecord object
	 */
	public TimeReal getNewTimeValue() {
		return newTimeValue;
	}

	/**
	 * Sets the new value of date and time
	 * of a WorkshopCardCalibrationRecord object.
	 * 
	 * @param	newTimeValue		the new value of date and time
	 * 								to be set for the WorkshopCardCalibrationRecord object
	 */
	public void setNewTimeValue(TimeReal newTimeValue) {
		this.newTimeValue = newTimeValue;
	}

	/**
	 * Returns the date of the next calibration of the type specified in CalibrationPurpose
	 * to be carried out by the authorised inspection authority of a WorkshopCardCalibrationRecord object.
	 * 
	 * @return	the date of the next calibration of the type specified in CalibrationPurpose
	 * 			to be carried out by the authorised inspection authority of the WorkshopCardCalibrationRecord object
	 */
	public TimeReal getNextCalibrationDate() {
		return nextCalibrationDate;
	}

	/**
	 * Sets the date of the next calibration of the type specified in CalibrationPurpose
	 * to be carried out by the authorised inspection authority of a WorkshopCardCalibrationRecord object.
	 * 
	 * @param	nextCalibrationDate		the date of the next calibration of the type specified in CalibrationPurpose
	 * 									to be carried out by the authorised inspection authority 
	 * 									to be set for the WorkshopCardCalibrationRecord object
	 */
	public void setNextCalibrationDate(TimeReal nextCalibrationDate) {
		this.nextCalibrationDate = nextCalibrationDate;
	}

	/**
	 * Returns the vehicle unit's part number
	 * of a WorkshopCardCalibrationRecord object.
	 * 
	 * @return	the vehicle unit's part number
	 * 			of the WorkshopCardCalibrationRecord object
	 */
	public VuPartNumber getVuPartNumber() {
		return vuPartNumber;
	}

	/**
	 * Sets the vehicle unit's part number
	 * of a WorkshopCardCalibrationRecord object.
	 * 
	 * @param	vuPartNumber		the vehicle unit's part number
	 * 								to be set for the WorkshopCardCalibrationRecord object
	 */
	public void setVuPartNumber(VuPartNumber vuPartNumber) {
		this.vuPartNumber = vuPartNumber;
	}

	/**
	 * Returns the vehicle unit's serial number
	 * of a WorkshopCardCalibrationRecord object.
	 * 
	 * @return	the vehicle unit's serial number
	 * 			of the WorkshopCardCalibrationRecord object
	 */
	public ExtendedSerialNumber getVuSerialNumber() {
		return vuSerialNumber;
	}

	/**
	 * Sets the vehicle unit's serial number
	 * of a WorkshopCardCalibrationRecord object.
	 * 
	 * @param	vuSerialNumber		the vehicle unit's serial number
	 * 								to be set for the WorkshopCardCalibrationRecord object
	 */
	public void setVuSerialNumber(ExtendedSerialNumber vuSerialNumber) {
		this.vuSerialNumber = vuSerialNumber;
	}

	/**
	 * Returns the sensor's serial number
	 * of a WorkshopCardCalibrationRecord object.
	 * 
	 * @return	the sensor's serial number
	 * 			of the WorkshopCardCalibrationRecord object
	 */
	public ExtendedSerialNumber getSensorSerialNumber() {
		return sensorSerialNumber;
	}

	/**
	 * Sets the sensor's serial number
	 * of a WorkshopCardCalibrationRecord object.
	 * 
	 * @param	sensorSerialNumber		the sensor's serial number
	 * 									to be set for the WorkshopCardCalibrationRecord object
	 */
	public void setSensorSerialNumber(ExtendedSerialNumber sensorSerialNumber) {
		this.sensorSerialNumber = sensorSerialNumber;
	}

	public Element generateXMLElement(String name){
		Element node = new Element( name );
		
		Element cpNode = calibrationPurpose.generateXMLElement("calibrationPurpose");
		node.addContent(cpNode);
		
		Element vinNode = vehicleIdentificationNumber.generateXMLElement("vehicleIdentificationNumber");
		node.addContent(vinNode);
		
		Element vrNode = vehicleRegistration.generateXMLElement("vehicleRegistration");
		node.addContent(vrNode);
		
		Element wvccNode = new Element("wVehicleCharacteristicConstant");
		wvccNode.setText(Integer.toString(wVehicleCharacteristicConstant));
		node.addContent(wvccNode);
		
		Element kcoreNode = new Element("kConstantOfRecordingEquipment");
		kcoreNode.setText(Integer.toString(kConstantOfRecordingEquipment));
		node.addContent(kcoreNode);
		
		Element ltcNode = new Element("lTyreCircumference");
		ltcNode.setText(Integer.toString(lTyreCircumference));
		node.addContent(ltcNode);
		
		Element tsNode = tyreSize.generateXMLElement("tyreSize");
		node.addContent(tsNode);
		
		Element asNode = new Element("authorisedSpeed");
		asNode.setText(Short.toString(authorisedSpeed));
		node.addContent(asNode);
		
		Element oovNode = new Element("oldOdometerValue");
		oovNode.setText(Integer.toString(oldOdometerValue));
		node.addContent(oovNode);
		
		Element novNode = new Element("newOdometerValue");
		novNode.setText(Integer.toString(newOdometerValue));
		node.addContent(novNode);
		
		Element otvNode = oldTimeValue.generateXMLElement("oldTimeValue");
		node.addContent(otvNode);
		
		Element ntvNode = newTimeValue.generateXMLElement("newTimeValue");
		node.addContent(ntvNode);
		
		Element ncdNode = nextCalibrationDate.generateXMLElement("nextCalibrationDate");
		node.addContent(ncdNode);

		Element vupnNode = vuPartNumber.generateXMLElement("vuPartNumber");
		node.addContent(vupnNode);
		
		Element vusnNode = vuSerialNumber.generateXMLElement("vuSerialNumber");
		node.addContent(vusnNode);
		
		Element ssnNode = sensorSerialNumber.generateXMLElement("sensorSerialNumber");
		node.addContent(ssnNode);
		
		return node;
	}
}
