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

import java.util.Vector;

import org.digitalertachograph.DDDQuery.internalData.DataClass;
import org.jdom.*;
import java.util.Iterator;

public class WorkshopCardCalibrationData extends DataClass {
	/*
	 * WorkshopCardCalibrationData ::= SEQUENCE {
	 * 	calibrationTotalNumber INTEGER(0..2^16-1), 2 bytes
	 * 	calibrationPointerNewestRecord INTEGER(0..NoOfCalibrationRecords-1), 1 bytes
	 * 	calibrationRecords SET SIZE(NoOfCalibrationRecords) OF WorkshopCardCalibrationRecord, 9240..26775 bytes
	 * }
	 * ---
	 * NoOfCalibrationRecords
	 * min.: 88
	 * max.: 255
	 */

	private int calibrationTotalNumber;
	private int calibrationPointerNewestRecord;
	private Vector<WorkshopCardCalibrationRecord> calibrationRecords = new Vector<WorkshopCardCalibrationRecord>(88); // min. 88; will be automatically expanded at run time if required!

	
	/**
	 * Constructor for a WorkshopCardCalibrationData object
	 * 
	 * @param	value	byte array of a WorkshopCardCalibrationData structure
	 * 					whose data is used when the WorkshopCardCalibrationData
	 * 					object is created.
	 */
	public WorkshopCardCalibrationData(byte[] value){
		calibrationTotalNumber = convertIntoUnsigned2ByteInt( arrayCopy(value, 0, 2));
		calibrationPointerNewestRecord = convertIntoUnsigned1ByteInt(value[2]);
		
		for (int i = 3; i < value.length; i += 105) {
			byte[] record = arrayCopy(value, i, 105);
			WorkshopCardCalibrationRecord tmp = new WorkshopCardCalibrationRecord(record);
			calibrationRecords.add(tmp);
		}
	}

	/**
	 * Returns the total number of calibrations performed with the card
	 * of a WorkshopCardCalibrationData object.
	 * 
	 * @return	the total number of calibrations performed with the card
	 * 			of the WorkshopCardCalibrationData object
	 */
	public int getCalibrationTotalNumber() {
		return calibrationTotalNumber;
	}

	/**
	 * Returns the index of the last updated calibration record
	 * of a WorkshopCardCalibrationData object.
	 * 
	 * @return	the index of the last updated calibration record
	 * 			of the WorkshopCardCalibrationData object
	 */
	public int getCalibrationPointerNewestRecord() {
		return calibrationPointerNewestRecord;
	}

	/**
	 * Returns the set of records containing calibration and/or time adjustment information
	 * of a WorkshopCardCalibrationData object.
	 * 
	 * @return	the set of records containing calibration and/or time adjustment information
	 * 			of the WorkshopCardCalibrationData object
	 */
	public Vector<WorkshopCardCalibrationRecord> getCalibrationRecords() {
		return calibrationRecords;
	}

	public Element generateXMLElement(String name){
		Element node = new Element(name);

		node.addContent( new Element("calibrationTotalNumber").setText(Integer.toString(calibrationTotalNumber)) );
		node.addContent( new Element("calibrationPointerNewestRecord").setText(Integer.toString(calibrationPointerNewestRecord)) );
		
		Iterator<WorkshopCardCalibrationRecord> it = calibrationRecords.iterator();
		while(it.hasNext()){
			WorkshopCardCalibrationRecord wccr = (WorkshopCardCalibrationRecord) it.next();
			Element wccrNode = wccr.generateXMLElement("WorkshopCardCalibrationRecord");
			node.addContent(wccrNode);
		}
		
		return node;
	}
}
