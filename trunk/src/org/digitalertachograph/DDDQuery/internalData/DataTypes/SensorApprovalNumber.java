/*
    $Id:SensorApprovalNumber.java 26 2008-02-25 22:28:27Z deetee $

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
 * Type approval number of the sensor.
 */
public class SensorApprovalNumber extends DataClass {
	/*
	 * SensorApprovalNumber ::= IA5String(SIZE(8)), 8 bytes
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 8;

	private String sensorApprovalNumber;


	/**
	 * Constructor for a SensorApprovalNumber object
	 */
	public SensorApprovalNumber() {
		sensorApprovalNumber = new String();
	}

	/**
	 * Constructor for a SensorApprovalNumber object
	 * 
	 * @param	value	byte array of a SensorApprovalNumber structure whose data is used
	 * 					when the SensorApprovalNumber object is created.
	 */
	public SensorApprovalNumber( byte[] value ) {
		sensorApprovalNumber = new String( arrayCopy( value, 0, 8 ) );
	}

	/**
	 * Constructor for a SensorApprovalNumber object
	 * 
	 * @param	sensorApprovalNumber	byte array of a SensorApprovalNumber structure
	 * 									whose data is used when the SensorApprovalNumber
	 * 									object is created.
	 */
	public SensorApprovalNumber( String sensorApprovalNumber ) {
		setSensorApprovalNumber( sensorApprovalNumber );
	}

	/**
	 * Returns the type approval number of the sensor of a SensorApprovalNumber object.
	 * 
	 * @return	the type approval number of the sensor of the SensorApprovalNumber object
	 */
	public String getSensorApprovalNumber() {
		return sensorApprovalNumber;
	}

	/**
	 * Sets the type approval number of the sensor of a SensorApprovalNumber object.
	 * 
	 * @param	sensorApprovalNumber	the type approval number of the sensor of a
	 * 									SensorApprovalNumber object
	 */
	public void setSensorApprovalNumber( String sensorApprovalNumber ) {
		int sensorApprovalNumberLength = sensorApprovalNumber.length();

		if ( sensorApprovalNumberLength > 8 ) {
			sensorApprovalNumberLength = 8;
		}

		this.sensorApprovalNumber = sensorApprovalNumber.substring( 0, sensorApprovalNumberLength );
	}

	@Override
	public Element generateXMLElement( String name ) {
		if ( isValidXMLString( sensorApprovalNumber ) == false ) {
			return new Element( name );
		}
		else {
			return new Element( name ).setText( sensorApprovalNumber );
		}
	}
}
