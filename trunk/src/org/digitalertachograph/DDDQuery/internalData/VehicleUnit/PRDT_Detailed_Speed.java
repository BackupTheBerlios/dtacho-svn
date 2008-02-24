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
 * Appendix 7, 2.2.6.4: Positive response transfer data detailed speed
 * SID 76H, TREP 04H
 */
public class PRDT_Detailed_Speed extends DataClass {

	/**
	 * Size of structure in bytes.
	 * Only valid after instantiation of the PRDT_Detailed_Speed object.
	 */
	public final int size;

	private VuDetailedSpeedData vuDetailedSpeedData;


	/**
	 * Constructor for a PRDT_Detailed_Speed object
	 * 
	 * @param	value	byte array of a PRDT_Detailed_Speed structure
	 * 					whose data is used when the PRDT_Detailed_Speed
	 * 					object is created.
	 */
	public PRDT_Detailed_Speed( byte[] value ) {
		int offset1 = convertIntoUnsigned2ByteInt( arrayCopy( value, 0, 2 ) ) * VuDetailedSpeedBlock.size;
		vuDetailedSpeedData = new VuDetailedSpeedData( value );

		size = offset1;
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( this.getClass().getSimpleName() );

		node.addContent( vuDetailedSpeedData.generateXMLElement( "vuDetailedSpeedData" ) );

		return node;
	}
}
