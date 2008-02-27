/*
    $Id:NoOfCalibrationRecords.java 26 2008-02-25 22:28:27Z deetee $

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
 * Number of calibration records a workshop card can store.
 */
public class NoOfCalibrationRecords extends DataClass {
	/*
	 * NoOfCalibrationRecords ::=  INTEGER(0..255), 1 byte
	 * min.:  88
	 * max.: 255
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 1;

	private short noOfCalibrationRecords;


	/**
	 * Constructor for a NoOfCalibrationRecords object
	 */
	public NoOfCalibrationRecords() {
		noOfCalibrationRecords = 0;
	}

	/**
	 * Constructor for a NoOfCalibrationRecords object
	 * 
	 * @param	b	byte that contains the number of calibration records a workshop card can store
	 * 				when the NoOfCalibrationRecords object is created.
	 */
	public NoOfCalibrationRecords( byte b ) {
		noOfCalibrationRecords = convertIntoUnsigned1ByteInt( b );
	}

	/**
	 * Returns number of calibration records a workshop card can store
	 * of a NoOfCalibrationRecords object.
	 * 
	 * @return	the number of calibration records a workshop card can store
	 * 			of the NoOfCalibrationRecords object
	 */
	public short getNoOfCalibrationRecords() {
		return noOfCalibrationRecords;
	}

	/**
	 * Sets the number of calibration records a workshop card can store
	 * of a NoOfCalibrationRecords object.
	 * 
	 * @param	noOfCalibrationRecords	the number of calibration records a workshop card can store
	 * 									to be set for the NoOfCardPlaceRecords object
	 */
	public void setNoOfCalibrationRecords( short noOfCalibrationRecords ) {
		this.noOfCalibrationRecords = noOfCalibrationRecords;
	}

	@Override
	public Element generateXMLElement( String name ) {
		return new Element( name ).setText( Short.toString( noOfCalibrationRecords ) );
	}
}
