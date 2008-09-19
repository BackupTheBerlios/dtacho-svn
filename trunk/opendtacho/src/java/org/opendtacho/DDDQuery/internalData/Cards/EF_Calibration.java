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

package org.opendtacho.DDDQuery.internalData.Cards;

import org.jdom.*;
import org.opendtacho.DDDQuery.internalData.DataClass;
import org.opendtacho.DDDQuery.internalData.DataTypes.WorkshopCardCalibrationData;

/**
 * EF_Calibration,
 * 
 * Council Regulation (EEC) No 3821/85 File ID: 050A
 */
public class EF_Calibration extends DataClass {

	/**
	 * Size of structure in bytes.
	 * Only valid after instantiation of the EF_Calibration object.
	 */
	public final int size;

	private WorkshopCardCalibrationData workshopCardCalibrationData;


	/**
	 * Constructor for an EF_Calibration object
	 * 
	 * @param	value	byte array of an EF_Calibration structure
	 * 					whose data is used when the EF_Calibration
	 * 					object is created.
	 */
	public EF_Calibration( byte[] value, short noOfCalibrationRecords ) {
		workshopCardCalibrationData = new WorkshopCardCalibrationData( value, noOfCalibrationRecords );
		size = workshopCardCalibrationData.size;
	}

	public Element generateXMLElement( String name ) {
		// discard name - this.getClass().getSimpleName() is unique!
		Element node = new Element( this.getClass().getSimpleName() );

		Element child = workshopCardCalibrationData.generateXMLElement( "workshopCardCalibrationData" );
		node.addContent( child );

		return node;
	}
}
