/*
    $Id$

    Copyright (C) 2007-2008, Martin Barth, Gerald Schnabel

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

package org.opendtacho.DDDQuery.internalData.DataTypes;

import org.jdom.Element;
import org.opendtacho.DDDQuery.internalData.DataClass;

/**
 * Code explaining why a set of calibration parameters was recorded.
 */
public class CalibrationPurpose extends DataClass {
	/*
	 * CalibrationPurpose ::= OCTET STRING (SIZE(1)), 1 byte
	 * 
	 * Value assignment:
	 * "00"H reserved value,
	 * "01"H activation: recording of calibration parameters known, at the moment of the VU activation,	
	 * "02"H first installation: first calibration of the VU after its activation,
	 * "03"H installation: first calibration of the VU in the current vehicle,
	 * "04"H periodic inspection.
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 1;

	private byte calibrationPurpose;


	/**
	 * Constructor for a CalibrationPurpose object
	 */
	public CalibrationPurpose() {
		calibrationPurpose = 0;
	}

	/**
	 * Constructor for a CalibrationPurpose object
	 * 
	 * @param	value	byte array of a CalibrationPurpose structure
	 * 					whose data is used when the CalibrationPurpose
	 * 					object is created
	 */
	public CalibrationPurpose( byte[] value ) {
		calibrationPurpose = value[ 0 ];
	}

	/**
	 * Constructor for a CalibrationPurpose object
	 * 
	 * @param	calibrationPurpose		byte with the code explaining why a set of calibration
	 * 									parameters was recorded
	 */
	public CalibrationPurpose( byte calibrationPurpose ) {
		this.calibrationPurpose = calibrationPurpose;
	}

	/**
	 * Returns the code explaining why a set of calibration parameters were recorded
	 * of a CalibrationPurpose object.
	 * 
	 * @return	the code explaining why a set of calibration parameters were recorded
	 * 			of the CalibrationPurpose object
	 */
	public byte getCalibrationPurpose() {
		return calibrationPurpose;
	}

	/**
	 * Sets the code explaining why a set of calibration parameters were recorded
	 * of a CalibrationPurpose object.
	 * 
	 * @param	calibrationPurpose	the code explaining why a set of calibration parameters were recorded
	 * 								to be set for the CalibrationPurpose object
	 */
	public void setCalibrationPurpose( byte calibrationPurpose ) {
		this.calibrationPurpose = calibrationPurpose;
	}

	/**
	 * Returns the calibration purpose as string.
	 * 
	 * @return	the calibration purpose as string
	 */
	public String toString() {
		if ( calibrationPurpose == 0x00 ) {
			return "reserved value";
		}
		if ( calibrationPurpose == 0x01 ) {
			return "activation: recording of calibration parameters known, at the moment of the VU activation";
		}
		if ( calibrationPurpose == 0x02 ) {
			return "first installation: first calibration of the VU after its activation";
		}
		if ( calibrationPurpose == 0x03 ) {
			return "installation: first calibration of the VU in the current vehicle";
		}
		if ( calibrationPurpose == 0x04 ) {
			return "periodic inspection";
		}

		return "????";
	}

	@Override
	public Element generateXMLElement( String name ) {
		return new Element( name ).setText( convertIntoHexString( calibrationPurpose ) );
	}
}
