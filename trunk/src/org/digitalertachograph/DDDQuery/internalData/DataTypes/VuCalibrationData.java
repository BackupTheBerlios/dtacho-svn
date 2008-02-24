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

import java.util.Iterator;
import java.util.Vector;

import org.digitalertachograph.DDDQuery.internalData.DataClass;
import org.jdom.Element;

/**
 * Information, stored in a vehicle unit, related to the calibrations of the recording equipment.
 */
public class VuCalibrationData extends DataClass {
	/*
	 * VuCalibrationData ::= SEQUENCE {
	 * 	noOfVuCalibrationRecords INTEGER(0..255), 1 byte
	 *  vuCalibrationRecords SET SIZE(noOfVuCalibrationRecords) OF VuCalibrationRecord, 0..42585 bytes
	 * }
	 * ---
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
	 * Only valid after instantiation of the VuCalibrationData object.
	 */
	public final int size;

	private short noOfVuCalibrationRecords;

	// create min. 20 vectors; will be automatically expanded at run time if required!
	private Vector<VuCalibrationRecord> vuCalibrationRecords = new Vector<VuCalibrationRecord>( 20 );


	/**
	 * Constructor for a VuCalibrationData object
	 * 
	 * @param	value	byte array of a VuCalibrationData structure
	 * 					whose data is used when the VuCalibrationData
	 * 					object is created.
	 */
	public VuCalibrationData( byte[] value ) {
		noOfVuCalibrationRecords = convertIntoUnsigned1ByteInt( value[ 0 ] );
		size = 1 + noOfVuCalibrationRecords * VuCalibrationRecord.size;

		if ( noOfVuCalibrationRecords != 0 ) {
			for ( int i = 0; i < noOfVuCalibrationRecords; i++ ) {
				byte[] record = arrayCopy( value, 1 + ( i * VuCalibrationRecord.size ), VuCalibrationRecord.size );
				VuCalibrationRecord tmp = new VuCalibrationRecord( record );
				vuCalibrationRecords.add( tmp );
			}
		}
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		Iterator<VuCalibrationRecord> iter = vuCalibrationRecords.iterator();
		while ( iter.hasNext() ) {
			VuCalibrationRecord vcr = (VuCalibrationRecord)iter.next();
			node.addContent( vcr.generateXMLElement( "vuCalibrationRecord" ) );
		}

		return node;
	}
}
