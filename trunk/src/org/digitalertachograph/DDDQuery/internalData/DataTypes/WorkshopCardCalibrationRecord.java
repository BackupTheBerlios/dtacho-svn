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

//import sun.jdbc.odbc.OdbcDef;


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
	 * 
	 * @param	value	byte array of a WorkshopCardCalibrationRecord structure
	 * 					whose data is used when the WorkshopCardCalibrationRecord
	 * 					object is created.
	 */
	public WorkshopCardCalibrationRecord(byte[] value){
		calibrationPurpose = new CalibrationPurpose(value[0]);
		vehicleIdentificationNumber = new VehicleIdentificationNumber( arrayCopy(value, 1, 17));
		vehicleRegistration = new VehicleRegistrationIdentification( arrayCopy(value, 18, 15));
		wVehicleCharacteristicConstant = convertIntoUnsigned2ByteInt( arrayCopy(value, 33, 2));
		kConstantOfRecordingEquipment = convertIntoUnsigned2ByteInt( arrayCopy(value, 35, 2));
		lTyreCircumference = convertIntoUnsigned2ByteInt( arrayCopy(value, 37, 2));
		tyreSize = new TyreSize( arrayCopy(value, 39, 15));
		authorisedSpeed = convertIntoUnsigned1ByteInt( value[54] );
		oldOdometerValue = convertIntoUnsigned3ByteInt( arrayCopy(value, 55, 3));
		newOdometerValue = convertIntoUnsigned3ByteInt( arrayCopy(value, 58, 3));
		oldTimeValue = new TimeReal( arrayCopy(value, 61, 4));
		newTimeValue = new TimeReal( arrayCopy(value, 65, 4));
		vuPartNumber = new VuPartNumber( arrayCopy(value, 69, 16));
		vuSerialNumber = new ExtendedSerialNumber( arrayCopy(value, 85, 8));
		sensorSerialNumber = new ExtendedSerialNumber( arrayCopy(value, 93, 8));
	}

	public short getAuthorisedSpeed() {
		return authorisedSpeed;
	}

	public void setAuthorisedSpeed(short authorisedSpeed) {
		this.authorisedSpeed = authorisedSpeed;
	}

	public CalibrationPurpose getCalibrationPurpose() {
		return calibrationPurpose;
	}

	public void setCalibrationPurpose(CalibrationPurpose calibrationPurpose) {
		this.calibrationPurpose = calibrationPurpose;
	}

	public int getKConstantOfRecordingEquipment() {
		return kConstantOfRecordingEquipment;
	}

	public void setKConstantOfRecordingEquipment(int constantOfRecordingEquipment) {
		kConstantOfRecordingEquipment = constantOfRecordingEquipment;
	}

	public int getLTyreCircumference() {
		return lTyreCircumference;
	}

	public void setLTyreCircumference(int tyreCircumference) {
		lTyreCircumference = tyreCircumference;
	}

	public int getNewOdometerValue() {
		return newOdometerValue;
	}

	public void setNewOdometerValue(int newOdometerValue) {
		this.newOdometerValue = newOdometerValue;
	}

	public TimeReal getNewTimeValue() {
		return newTimeValue;
	}

	public void setNewTimeValue(TimeReal newTimeValue) {
		this.newTimeValue = newTimeValue;
	}

	public TimeReal getNextCalibrationDate() {
		return nextCalibrationDate;
	}

	public void setNextCalibrationDate(TimeReal nextCalibrationDate) {
		this.nextCalibrationDate = nextCalibrationDate;
	}

	public int getOldOdometerValue() {
		return oldOdometerValue;
	}

	public void setOldOdometerValue(int oldOdometerValue) {
		this.oldOdometerValue = oldOdometerValue;
	}

	public TimeReal getOldTimeValue() {
		return oldTimeValue;
	}

	public void setOldTimeValue(TimeReal oldTimeValue) {
		this.oldTimeValue = oldTimeValue;
	}

	public ExtendedSerialNumber getSensorSerialNumber() {
		return sensorSerialNumber;
	}

	public void setSensorSerialNumber(ExtendedSerialNumber sensorSerialNumber) {
		this.sensorSerialNumber = sensorSerialNumber;
	}

	public TyreSize getTyreSize() {
		return tyreSize;
	}

	public void setTyreSize(TyreSize tyreSize) {
		this.tyreSize = tyreSize;
	}

	public VehicleIdentificationNumber getVehicleIdentificationNumber() {
		return vehicleIdentificationNumber;
	}

	public void setVehicleIdentificationNumber(
			VehicleIdentificationNumber vehicleIdentificationNumber) {
		this.vehicleIdentificationNumber = vehicleIdentificationNumber;
	}

	public VehicleRegistrationIdentification getVehicleRegistration() {
		return vehicleRegistration;
	}

	public void setVehicleRegistration(
			VehicleRegistrationIdentification vehicleRegistration) {
		this.vehicleRegistration = vehicleRegistration;
	}

	public VuPartNumber getVuPartNumber() {
		return vuPartNumber;
	}

	public void setVuPartNumber(VuPartNumber vuPartNumber) {
		this.vuPartNumber = vuPartNumber;
	}

	public ExtendedSerialNumber getVuSerialNumber() {
		return vuSerialNumber;
	}

	public void setVuSerialNumber(ExtendedSerialNumber vuSerialNumber) {
		this.vuSerialNumber = vuSerialNumber;
	}

	public int getWVehicleCharacteristicConstant() {
		return wVehicleCharacteristicConstant;
	}

	public void setWVehicleCharacteristicConstant(int vehicleCharacteristicConstant) {
		wVehicleCharacteristicConstant = vehicleCharacteristicConstant;
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
		
		
/*		methode("oldTimeValue");
		public methode(Sting name){
			(name).generateXMLElement(name);
		}
*/
		
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
