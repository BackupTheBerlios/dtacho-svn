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
 * Speed of the vehicle (km/h) in the operational range 0 to 220 km/h.
 */
public class Speed extends DataClass {
	/*
	 * Speed ::= INTEGER(0..255), 1 byte
	 * ---
	 * Value assignment:
	 * kilometre per hour in the operational range 0 to 220 km/h
	 */

	private short speed;


	/**
	 * Constructor for a Speed object
	 */
	public Speed() {
		speed = 0;
	}

	/**
	 * Constructor for a Speed object
	 * 
	 * @param	b	byte that contains the speed of a vehicle
	 * 				when the Speed object is created.
	 */
	public Speed( byte b ) {
		speed = convertIntoUnsigned1ByteInt( b );
	}

	/**
	 * Returns the speed of a vehicle of a Speed object.
	 * 
	 * @return	the speed of a vehicle of the Speed object
	 */
	public short getSpeed() {
		return speed;
	}

	/**
	 * Sets the speed of a vehicle of a Speed object.
	 * 
	 * @param	speed		the speed of a vehicle to be set for the Speed object
	 */
	public void setSpeed( short speed ) {
		this.speed = speed;
	}

	@Override
	public Element generateXMLElement( String name ) {
		return new Element( name ).setText( Short.toString( speed ) );
	}
}
