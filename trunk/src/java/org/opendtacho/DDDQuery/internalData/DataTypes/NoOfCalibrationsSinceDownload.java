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
 * Counter indicating the number of calibrations performed with a workshop card
 * since its last download.
 */
public class NoOfCalibrationsSinceDownload extends DataClass {
	/* 
	 * NoOfCalibrationsSinceDownload ::= INTEGER(0..2^16-1), 2 bytes
	 * min.: n/a
	 * max.: n/a
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 2;

	private int noOfCalibrationsSinceDownload;

	
	/**
	 * Constructor for a NoOfCalibrationsSinceDownload object
	 */
	public NoOfCalibrationsSinceDownload() {
		noOfCalibrationsSinceDownload = 0;
	}

	/**
	 * Constructor for a NoOfCalibrationsSinceDownload object
	 * 
	 * @param	value	byte array of a NoOfCalibrationsSinceDownload structure
	 * 					whose data is used when the NoOfCalibrationsSinceDownload
	 * 					object is created.
	 */
	public NoOfCalibrationsSinceDownload( byte[] value ) {
		this.noOfCalibrationsSinceDownload = convertIntoUnsigned2ByteInt( value );
	}

	/**
	 * Returns the number of calibration records a workshop card can store
	 * of a NoOfCalibrationsSinceDownload object.
	 * 
	 * @return	the number of calibration records a workshop card can store
	 * 			of the NoOfCalibrationsSinceDownload object
	 */
	public int getNoOfCalibrationsSinceDownload() {
		return noOfCalibrationsSinceDownload;
	}

	/**
	 * Sets the number of calibration records a workshop card can store
	 * of a NoOfCalibrationsSinceDownload object.
	 * 
	 * @param	noOfCalibrationsSinceDownload	the number of calibration records a workshop card can store
	 * 											to be set for the NoOfCalibrationsSinceDownload object
	 */
	public void setNoOfCalibrationsSinceDownload( int noOfCalibrationsSinceDownload ) {
		this.noOfCalibrationsSinceDownload = noOfCalibrationsSinceDownload;
	}
	
	@Override
	public Element generateXMLElement( String name ) {
		return new Element( name ).setText( Integer.toString( noOfCalibrationsSinceDownload ) );
	}
}
