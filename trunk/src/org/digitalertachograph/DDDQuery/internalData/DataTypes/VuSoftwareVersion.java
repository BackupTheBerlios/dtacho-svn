/*   Copyright (C) 2008, Gerald Schnabel

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
 * Software version number of the vehicle unit.
 */
public class VuSoftwareVersion extends DataClass {
	/*
	 * VuSoftwareVersion ::= IA5String(SIZE(4)), 4 bytes
	 */

	private String vuSoftwareVersion;


	/**
	 * Constructor for a VuSoftwareVersion object
	 */
	public VuSoftwareVersion() {
		vuSoftwareVersion = new String();
	}

	/**
	 * Constructor for a VuSoftwareVersion object
	 * 
	 * @param	value	byte array of a VuSoftwareVersion that is used when the VuSoftwareVersion
	 * 					object is created.
	 */
	public VuSoftwareVersion( byte[] value ) {
		vuSoftwareVersion = new String( arrayCopy( value, 0, 4 ) );
	}

	/**
	 * Constructor for a VuSoftwareVersion object
	 * 
	 * @param	vuSoftwareVersion	String that contains the part number of a vehicle unit
	 * 								that is used when the VuSoftwareVersion object is created.
	 */
	public VuSoftwareVersion( String vuSoftwareVersion ) {
		setVuSoftwareVersion( vuSoftwareVersion );
	}

	/**
	 * Returns the software version of the vehicle unit of a VuSoftwareVersion object.
	 * 
	 * @return	the software version of the vehicle unit of the VuSoftwareVersion object
	 */
	public String getVuSoftwareVersion() {
		return vuSoftwareVersion;
	}

	/**
	 * Sets the software version of the vehicle unit of a VuSoftwareVersion object.
	 * 
	 * @param	vuSoftwareVersion		the software version of the vehicle unit
	 * 									to be set for the VuSoftwareVersion object
	 */
	public void setVuSoftwareVersion( String vuSoftwareVersion ) {
		int vuSoftwareVersionLength = vuSoftwareVersion.length(); 

		if ( vuSoftwareVersionLength > 4 ) {
			vuSoftwareVersionLength = 4;
		}

		this.vuSoftwareVersion = vuSoftwareVersion.substring( 0, vuSoftwareVersionLength );
	}

	@Override
	public Element generateXMLElement( String name ) {
		return new Element( name ).setText( vuSoftwareVersion );
	}
}
