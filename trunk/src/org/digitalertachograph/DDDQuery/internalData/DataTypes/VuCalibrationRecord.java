/*
    $Id$

    Copyright (C) 2008, Gerald Schnabel

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

/**
 * Information, stored in a vehicle unit, related to a calibration of the recording equipment.
 */
public class VuCalibrationRecord extends DataClass {
	/*
	 * VuCalibrationRecord ::= SEQUENCE {
	 * 	calibrationPurpose CalibrationPurpose, 1 byte
	 * 	workshopName Name, 36 bytes
	 * 	workshopAddress Address, 36 bytes
	 * 	workshopCardNumber FullCardNumber, 18 bytes
	 * 	workshopCardExpiryDate TimeReal, 4 bytes
	 * 	vehicleIdentificationNumber VehicleIdentificationNumber, 17 bytes
	 * 	vehicleRegistrationIdentification VehicleRegistrationIdentification, 15 bytes
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
	 * }
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 167;

	private CalibrationPurpose calibrationPurpose;
	private Name workshopName;
	private Address workshopAddress;
	private FullCardNumber workshopCardNumber;
	private TimeReal workshopCardExpiryDate;
	private VehicleIdentificationNumber vehicleIdentificationNumber;
	private VehicleRegistrationIdentification vehicleRegistrationIdentification;
	private W_VehicleCharacteristicConstant wVehicleCharacteristicConstant;
	private K_ConstantOfRecordingEquipment kConstantOfRecordingEquipment;
	private L_TyreCircumference lTyreCircumference;
	private TyreSize tyreSize;
	private SpeedAuthorised authorisedSpeed;
	private OdometerShort oldOdometerValue;
	private OdometerShort newOdometerValue;
	private TimeReal oldTimeValue;
	private TimeReal newTimeValue;
	private TimeReal nextCalibrationDate;


	/**
	 * Constructor for a VuCalibrationRecord object
	 */
	public VuCalibrationRecord() {
		calibrationPurpose = new CalibrationPurpose();
		workshopName = new Name();
		workshopAddress = new Address();
		workshopCardNumber = new FullCardNumber();
		workshopCardExpiryDate = new TimeReal();
		vehicleIdentificationNumber = new VehicleIdentificationNumber();
		vehicleRegistrationIdentification = new VehicleRegistrationIdentification();
		wVehicleCharacteristicConstant = new W_VehicleCharacteristicConstant();
		kConstantOfRecordingEquipment = new K_ConstantOfRecordingEquipment();
		lTyreCircumference = new L_TyreCircumference();
		tyreSize = new TyreSize();
		authorisedSpeed = new SpeedAuthorised();
		oldOdometerValue = new OdometerShort();
		newOdometerValue = new OdometerShort();
		oldTimeValue = new TimeReal();
		newTimeValue = new TimeReal();
		nextCalibrationDate = new TimeReal();
	}

	/**
	 * Constructor for a VuCalibrationRecord object
	 * 
	 * @param	value	byte array of a VuCalibrationRecord structure
	 * 					whose data is used when the VuCalibrationRecord
	 * 					object is created.
	 */
	public VuCalibrationRecord( byte[] value ) {
		calibrationPurpose = new CalibrationPurpose( value[ 0 ] );
		workshopName = new Name( arrayCopy( value, 1, 36 ) );
		workshopAddress = new Address( arrayCopy( value, 37, 36 ) );
		workshopCardNumber = new FullCardNumber( arrayCopy( value, 73, 18 ) );
		workshopCardExpiryDate = new TimeReal( arrayCopy( value, 91, 4 ) );
		vehicleIdentificationNumber = new VehicleIdentificationNumber( arrayCopy( value, 95, 17 ) );
		vehicleRegistrationIdentification = new VehicleRegistrationIdentification( arrayCopy( value, 112, 15 ) );
		wVehicleCharacteristicConstant = new W_VehicleCharacteristicConstant( arrayCopy( value, 127, 2 ) );
		kConstantOfRecordingEquipment = new K_ConstantOfRecordingEquipment( arrayCopy( value, 129, 2 ) );
		lTyreCircumference = new L_TyreCircumference( arrayCopy( value, 131, 2 ) );
		tyreSize = new TyreSize( arrayCopy( value, 133, 15 ) );
		authorisedSpeed = new SpeedAuthorised( value[ 148 ] );
		oldOdometerValue = new OdometerShort( arrayCopy( value, 149, 3 ) );
		newOdometerValue = new OdometerShort( arrayCopy( value, 152, 3 ) );
		oldTimeValue = new TimeReal( arrayCopy( value, 155, 4 ) );
		newTimeValue = new TimeReal( arrayCopy( value, 159, 4 ) );
		nextCalibrationDate = new TimeReal( arrayCopy( value, 163, 4 ) );
	}

	/**
	 * Returns the purpose of the calibration of a VuCalibrationRecord object.
	 * 
	 * @return	the purpose of the calibration
	 * 			of the VuCalibrationRecord object
	 */
	public CalibrationPurpose getCalibrationPurpose() {
		return calibrationPurpose;
	}

	/**
	 * Sets the purpose of the calibration of a VuCalibrationRecord object.
	 * 
	 * @param	calibrationPurpose		the purpose of the calibration
	 * 									to be set for the VuCalibrationRecord object
	 */
	public void setCalibrationPurpose( CalibrationPurpose calibrationPurpose ) {
		this.calibrationPurpose = calibrationPurpose;
	}

	/**
	 * Returns the workshop name of a VuCalibrationRecord object.
	 * 
	 * @return	the workshop name
	 * 			of the VuCalibrationRecord object
	 */
	public Name getWorkshopName() {
		return workshopName;
	}

	/**
	 * Sets the workshop name of a VuCalibrationRecord object.
	 * 
	 * @param	workshopName		the workshop name
	 * 								to be set for the VuCalibrationRecord object
	 */
	public void setWorkshopName( Name workshopName ) {
		this.workshopName = workshopName;
	}

	/**
	 * Returns the workshop address of a VuCalibrationRecord object.
	 * 
	 * @return	the workshop address
	 * 			of the VuCalibrationRecord object
	 */
	public Address getWorkshopAddress() {
		return workshopAddress;
	}

	/**
	 * Sets the workshop address of a VuCalibrationRecord object.
	 * 
	 * @param	workshopAddress		the workshop address
	 * 								to be set for the VuCalibrationRecord object
	 */
	public void setWorkshopAddress( Address workshopAddress ) {
		this.workshopAddress = workshopAddress;
	}

	/**
	 * Returns the workshop card used during the calibration
	 * of a VuCalibrationRecord object.
	 * 
	 * @return	the workshop card used during the calibration
	 * 			of the VuCalibrationRecord object
	 */
	public FullCardNumber getWorkshopCardNumber() {
		return workshopCardNumber;
	}

	/**
	 * Sets the workshop card used during the calibration of a VuCalibrationRecord object.
	 * 
	 * @param	workshopCardNumber		the workshop card used during the calibration
	 * 									to be set for the VuCalibrationRecord object
	 */
	public void setWorkshopCardNumber( FullCardNumber workshopCardNumber ) {
		this.workshopCardNumber = workshopCardNumber;
	}

	/**
	 * Returns the workshop card expiry date
	 * of a VuCalibrationRecord object.
	 * 
	 * @return	the workshop card expiry date
	 * 			of the VuCalibrationRecord object
	 */
	public TimeReal getWorkshopCardExpiryDate() {
		return workshopCardExpiryDate;
	}

	/**
	 * Sets the workshop card expiry date of a VuCalibrationRecord object.
	 * 
	 * @param	workshopCardExpiryDate		the workshop card expiry date
	 * 										to be set for the VuCalibrationRecord object
	 */
	public void setWorkshopCardExpiryDate( TimeReal workshopCardExpiryDate ) {
		this.workshopCardExpiryDate = workshopCardExpiryDate;
	}

	/**
	 * Returns the VIN of a VuCalibrationRecord object.
	 * 
	 * @return	the VIN of the VuCalibrationRecord object
	 */
	public VehicleIdentificationNumber getVehicleIdentificationNumber() {
		return vehicleIdentificationNumber;
	}

	/**
	 * Sets the VIN of a VuCalibrationRecord object.
	 * 
	 * @param	vehicleIdentificationNumber		the VIN to be set for the VuCalibrationRecord object
	 */
	public void setVehicleIdentificationNumber( VehicleIdentificationNumber vehicleIdentificationNumber ) {
		this.vehicleIdentificationNumber = vehicleIdentificationNumber;
	}

	/**
	 * Returns the VRN and registering Member State of a VuCalibrationRecord object.
	 * 
	 * @return	the VRN and registering Member State of the VuCalibrationRecord object
	 */
	public VehicleRegistrationIdentification getVehicleRegistrationIdentification() {
		return vehicleRegistrationIdentification;
	}

	/**
	 * Sets the VRN and registering Member State of a VuCalibrationRecord object.
	 * 
	 * @param	vehicleRegistrationIdentification		the VRN and registering Member State
	 * 													to be set for the VuCalibrationRecord object
	 */
	public void setVehicleRegistrationIdentification( VehicleRegistrationIdentification vehicleRegistrationIdentification ) {
		this.vehicleRegistrationIdentification = vehicleRegistrationIdentification;
	}

	/**
	 * Returns the characteristic coefficient of the vehicle of a VuCalibrationRecord object.
	 * 
	 * @return	the characteristic coefficient of the vehicle of the VuCalibrationRecord object
	 */
	public W_VehicleCharacteristicConstant getWVehicleCharacteristicConstant() {
		return wVehicleCharacteristicConstant;
	}

	/**
	 * Sets the characteristic coefficient of the vehicle of a VuCalibrationRecord object.
	 * 
	 * @param	wVehicleCharacteristicConstant		the characteristic coefficient of the vehicle
	 * 												to be set for the VuCalibrationRecord object
	 */
	public void setWVehicleCharacteristicConstant( W_VehicleCharacteristicConstant wVehicleCharacteristicConstant ) {
		this.wVehicleCharacteristicConstant = wVehicleCharacteristicConstant;
	}

	/**
	 * Returns the constant of the recording equipment of a VuCalibrationRecord object.
	 * 
	 * @return	the constant of the recording equipment of the VuCalibrationRecord object
	 */
	public K_ConstantOfRecordingEquipment getKConstantOfRecordingEquipment() {
		return kConstantOfRecordingEquipment;
	}

	/**
	 * Sets the constant of the recording equipment of a VuCalibrationRecord object.
	 * 
	 * @param	kConstantOfRecordingEquipment		the constant of the recording equipment
	 * 												to be set for the VuCalibrationRecord object
	 */
	public void setKConstantOfRecordingEquipment( K_ConstantOfRecordingEquipment kConstantOfRecordingEquipment ) {
		this.kConstantOfRecordingEquipment = kConstantOfRecordingEquipment;
	}

	/**
	 * Returns the effective circumference of the wheel tyres of a VuCalibrationRecord object.
	 * 
	 * @return	the effective circumference of the wheel tyres of the VuCalibrationRecord object
	 */
	public L_TyreCircumference getLTyreCircumference() {
		return lTyreCircumference;
	}

	/**
	 * Sets the effective circumference of the wheel tyres of a VuCalibrationRecord object.
	 * 
	 * @param	lTyreCircumference		the effective circumference of the wheel tyres
	 * 									to be set for the VuCalibrationRecord object
	 */
	public void setLTyreCircumference( L_TyreCircumference lTyreCircumference ) {
		this.lTyreCircumference = lTyreCircumference;
	}

	/**
	 * Returns the designation of the dimension of the tyres mounted on the vehicle
	 * of a VuCalibrationRecord object.
	 * 
	 * @return	the designation of the dimension of the tyres mounted on the vehicle
	 * 			of the VuCalibrationRecord object
	 */
	public TyreSize getTyreSize() {
		return tyreSize;
	}

	/**
	 * Sets the designation of the dimension of the tyres mounted on the vehicle of a VuCalibrationRecord object.
	 * 
	 * @param	tyreSize		the designation of the dimension of the tyres mounted on the vehicle
	 * 							to be set for the VuCalibrationRecord object
	 */
	public void setTyreSize( TyreSize tyreSize ) {
		this.tyreSize = tyreSize;
	}

	/**
	 * Returns the authorised speed of the vehicle of a VuCalibrationRecord object.
	 * 
	 * @return	the authorised speed of the vehicle of the VuCalibrationRecord object
	 */
	public SpeedAuthorised getAuthorisedSpeed() {
		return authorisedSpeed;
	}

	/**
	 * Sets the authorised speed of the vehicle of a VuCalibrationRecord object.
	 * 
	 * @param	authorisedSpeed		the authorised speed of the vehicle
	 * 									to be set for the VuCalibrationRecord object
	 */
	public void setAuthorisedSpeed( SpeedAuthorised authorisedSpeed ) {
		this.authorisedSpeed = authorisedSpeed;
	}

	/**
	 * Returns the old value of the odometer of a VuCalibrationRecord object.
	 * 
	 * @return	the old value of the odometer of the VuCalibrationRecord object
	 */
	public OdometerShort getOldOdometerValue() {
		return oldOdometerValue;
	}

	/**
	 * Sets the old value of the odometer of a VuCalibrationRecord object.
	 * 
	 * @param	oldOdometerValue		the old value of the odometer
	 * 									to be set for the VuCalibrationRecord object
	 */
	public void setOldOdometerValue( OdometerShort oldOdometerValue ) {
		this.oldOdometerValue = oldOdometerValue;
	}

	/**
	 * Returns the new value of the odometer of a VuCalibrationRecord object.
	 * 
	 * @return	the new value of the odometer of the VuCalibrationRecord object
	 */
	public OdometerShort getNewOdometerValue() {
		return newOdometerValue;
	}

	/**
	 * Sets the new value of the odometer of a VuCalibrationRecord object.
	 * 
	 * @param	newOdometerValue		the new value of the odometer
	 * 									to be set for the VuCalibrationRecord object
	 */
	public void setNewOdometerValue( OdometerShort newOdometerValue ) {
		this.newOdometerValue = newOdometerValue;
	}

	/**
	 * Returns the old value of date and time of a VuCalibrationRecord object.
	 * 
	 * @return	the old value of date and time of the VuCalibrationRecord object
	 */
	public TimeReal getOldTimeValue() {
		return oldTimeValue;
	}

	/**
	 * Sets the old value of date and time of a VuCalibrationRecord object.
	 * 
	 * @param	oldTimeValue		the old value of date and time
	 * 								to be set for the VuCalibrationRecord object
	 */
	public void setOldTimeValue( TimeReal oldTimeValue ) {
		this.oldTimeValue = oldTimeValue;
	}

	/**
	 * Returns the new value of date and time of a VuCalibrationRecord object.
	 * 
	 * @return	the new value of date and time of the VuCalibrationRecord object
	 */
	public TimeReal getNewTimeValue() {
		return newTimeValue;
	}

	/**
	 * Sets the new value of date and time of a VuCalibrationRecord object.
	 * 
	 * @param	newTimeValue		the new value of date and time
	 * 									to be set for the VuCalibrationRecord object
	 */
	public void setNewTimeValue( TimeReal newTimeValue ) {
		this.newTimeValue = newTimeValue;
	}

	/**
	 * Returns the date of the next calibration of the type specified in
	 * CalibrationPurpose to be carried out by the authorised inspection authority
	 * of a VuCalibrationRecord object.
	 * 
	 * @return	the date of the next calibration of the type specified in
	 * 			CalibrationPurpose to be carried out by the authorised
	 * 			inspection authority of the VuCalibrationRecord object
	 */
	public TimeReal getNextCalibrationDate() {
		return nextCalibrationDate;
	}

	/**
	 * Sets the date of the next calibration of the type specified in
	 * CalibrationPurpose to be carried out by the authorised
	 * inspection authority of a VuCalibrationRecord object.
	 * 
	 * @param	nextCalibrationDate		the date of the next calibration of the type specified in CalibrationPurpose
	 * 									to be carried out by the authorised inspection authority
	 * 									to be set for the VuCalibrationRecord object
	 */
	public void setNextCalibrationDate( TimeReal nextCalibrationDate ) {
		this.nextCalibrationDate = nextCalibrationDate;
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		node.addContent( calibrationPurpose.generateXMLElement( "calibrationPurpose" ) );
		node.addContent( workshopName.generateXMLElement( "workshopName" ) );
		node.addContent( workshopAddress.generateXMLElement( "workshopAddress" ) );
		node.addContent( workshopCardNumber.generateXMLElement( "workshopCardNumber" ) );
		node.addContent( workshopCardExpiryDate.generateXMLElement( "workshopCardExpiryDate" ) );
		node.addContent( vehicleIdentificationNumber.generateXMLElement( "vehicleIdentificationNumber" ) );
		node.addContent( vehicleRegistrationIdentification.generateXMLElement( "vehicleRegistrationIdentification" ) );
		node.addContent( wVehicleCharacteristicConstant.generateXMLElement( "wVehicleCharacteristicConstant" ) );
		node.addContent( kConstantOfRecordingEquipment.generateXMLElement( "kConstantOfRecordingEquipment" ) );
		node.addContent( lTyreCircumference.generateXMLElement( "lTyreCircumference" ) );
		node.addContent( tyreSize.generateXMLElement( "tyreSize" ) );
		node.addContent( authorisedSpeed.generateXMLElement( "authorisedSpeed" ) );
		node.addContent( oldOdometerValue.generateXMLElement( "oldOdometerValue" ) );
		node.addContent( newOdometerValue.generateXMLElement( "newOdometerValue" ) );
		node.addContent( oldTimeValue.generateXMLElement( "oldTimeValue" ) );
		node.addContent( newTimeValue.generateXMLElement( "newTimeValue" ) );
		node.addContent( nextCalibrationDate.generateXMLElement( "nextCalibrationDate" ) );

		return node;
	}
}
