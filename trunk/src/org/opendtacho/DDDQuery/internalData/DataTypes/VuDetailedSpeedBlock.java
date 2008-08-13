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

package org.opendtacho.DDDQuery.internalData.DataTypes;

import org.jdom.Element;
import org.opendtacho.DDDQuery.internalData.DataClass;

/**
 * Information, stored in a vehicle unit, related to the vehicle's detailed
 * speed for a minute during which the vehicle has been moving.
 */
public class VuDetailedSpeedBlock extends DataClass {
	/*
	 * VuDetailedSpeedBlock ::= SEQUENCE {
	 * 	speedBlockBeginDate TimeReal, 4 bytes
	 * 	speedsPerSecond SEQUENCE SIZE (60) OF Speed, 60 bytes
	 * }
	 * ---
	 * Speed ::= INTEGER(0..255), 1 byte
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 64;

	private TimeReal speedBlockBeginDate;
	private Speed[] speedsPerSecond;


	/**
	 * Constructor for a VuDetailedSpeedBlock object
	 * 
	 * @param	value	byte array of a VuDetailedSpeedBlock structure
	 * 					whose data is used when the VuDetailedSpeedBlock
	 * 					object is created.
	 */
	public VuDetailedSpeedBlock( byte[] value ) {
		speedBlockBeginDate = new TimeReal( arrayCopy( value, 0, 4 ) );
		speedsPerSecond = new Speed[ 60 ];

		for ( int i = 0; i < 60; i++ ) {
			speedsPerSecond[ i ] = new Speed( value[ 4 + i ] );
		}
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		node.addContent( speedBlockBeginDate.generateXMLElement( "speedBlockBeginDate" ) );
		for ( int i = 0; i < 60; i++ ) {
			node.addContent( speedsPerSecond[ i ].generateXMLElement( "speedsPerSecond" ) );
		}

		return node;
	}
}
