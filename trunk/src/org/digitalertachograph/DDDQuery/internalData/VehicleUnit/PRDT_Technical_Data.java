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

package org.digitalertachograph.DDDQuery.internalData.VehicleUnit;

import org.digitalertachograph.DDDQuery.internalData.DataClass;
import org.digitalertachograph.DDDQuery.internalData.DataTypes.*;
import org.jdom.Element;

/**
 * Appendix 7, 2.2.6.5: Positive response transfer data technical data
 * SID 76H, TREP 05H
 */
public class PRDT_Technical_Data extends DataClass {

	/**
	 * Size of structure in bytes.
	 * Only valid after instantiation of the PRDT_Technical_Data object.
	 */
	public final int size;

	private VuIdentification vuIdentification;
	private SensorPaired sensorPaired;
	private VuCalibrationData vuCalibrationData;

	/**
	 * Constructor for a PRDT_Technical_Data object
	 * 
	 * @param	value	byte array of a PRDT_Technical_Data structure
	 * 					whose data is used when the PRDT_Technical_Data
	 * 					object is created.
	 */
	public PRDT_Technical_Data( byte[] value ) {
		int offset1 = 116 + 20;
		vuIdentification = new VuIdentification( arrayCopy( value, 0, 116 ) );
		sensorPaired = new SensorPaired( arrayCopy( value, 116, 20 ) );

		int offset2 = 1 + convertIntoUnsigned1ByteInt( value[ 136 ] ) * VuCalibrationRecord.size;
		vuCalibrationData = new VuCalibrationData( arrayCopy( value, 136, offset2 ) );

		size = offset1 + offset2;
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( this.getClass().getSimpleName() );

		node.addContent( vuIdentification.generateXMLElement( "vuIdentification" ) );
		node.addContent( sensorPaired.generateXMLElement( "sensorPaired" ) );
		node.addContent( vuCalibrationData.generateXMLElement( "vuCalibrationData" ) );

		return node;
	}
}
