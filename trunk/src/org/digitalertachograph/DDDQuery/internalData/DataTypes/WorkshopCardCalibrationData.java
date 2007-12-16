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
	 * 	calibrationRecords SET SIZE(NoOfCalibrationRecords) OF WorkshopCardCalibrationRecord 
	 * } 
	 */

	private int calibrationTotalNumber;
	private int calibrationPointerNewestRecord;
	private Vector<WorkshopCardCalibrationRecord> calibrationRecords = new Vector<WorkshopCardCalibrationRecord>();

	
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

	public int getCalibrationPointerNewestRecord() {
		return calibrationPointerNewestRecord;
	}

	public void setCalibrationPointerNewestRecord(int calibrationPointerNewestRecord) {
		this.calibrationPointerNewestRecord = calibrationPointerNewestRecord;
	}

	public Vector<WorkshopCardCalibrationRecord> getCalibrationRecords() {
		return calibrationRecords;
	}

	public void setCalibrationRecords(
			Vector<WorkshopCardCalibrationRecord> calibrationRecords) {
		this.calibrationRecords = calibrationRecords;
	}

	public int getCalibrationTotalNumber() {
		return calibrationTotalNumber;
	}

	public void setCalibrationTotalNumber(int calibrationTotalNumber) {
		this.calibrationTotalNumber = calibrationTotalNumber;
	}
	
	public Element generateXMLElement(String name){
				Element node = new Element(name);
		
		Element ctnNode = new Element("calibrationTotalNumber");
		ctnNode.setText(Integer.toString(calibrationTotalNumber)); 
		Element cpnrNode = new Element("calibrationPointerNewestRecord");
		cpnrNode.setText(Integer.toString(calibrationPointerNewestRecord));
		
		node.addContent( ctnNode );
		node.addContent( cpnrNode );
		
		Iterator<WorkshopCardCalibrationRecord> it = calibrationRecords.iterator();
		while(it.hasNext()){
			WorkshopCardCalibrationRecord wccr = (WorkshopCardCalibrationRecord) it.next();
			Element wccrNode = wccr.generateXMLElement("WorkshopCardCalibrationRecord");
			node.addContent(wccrNode);
		}
		
		return node;
	}
}
