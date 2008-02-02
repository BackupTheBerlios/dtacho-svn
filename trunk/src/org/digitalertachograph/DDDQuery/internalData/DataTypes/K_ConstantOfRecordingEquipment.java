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
 * Characteristic coefficient of the recording equipment.
 */
public class K_ConstantOfRecordingEquipment extends DataClass {
	/* 
	 * KConstantOfRecordingEquipment ::= INTEGER(0..2^16-1)
	 *
	 * value assignment:
	 * Impulses per kilometre in the operating range 0 to 64 255 pulses/km.
	 */

	private int kConstantOfRecordingEquipment;

	
	/**
	 * Constructor for a KConstantOfRecordingEquipment object
	 */
	public K_ConstantOfRecordingEquipment() {
		kConstantOfRecordingEquipment = 0;
	}

	/**
	 * Constructor for a KConstantOfRecordingEquipment object
	 * 
	 * @param	value	byte array of a KConstantOfRecordingEquipment structure
	 * 					whose data is used when the KConstantOfRecordingEquipment
	 * 					object is created.
	 */
	public K_ConstantOfRecordingEquipment( byte[] value ) {
		this.kConstantOfRecordingEquipment = convertIntoUnsigned2ByteInt( value );
	}

	/**
	 * Returns the characteristic coefficient of the recording equipment
	 * of a KConstantOfRecordingEquipment object.
	 * 
	 * @return	the characteristic coefficient of the recording equipment
	 * 			of the KConstantOfRecordingEquipment object
	 */
	public int getKConstantOfRecordingEquipment() {
		return kConstantOfRecordingEquipment;
	}

	/**
	 * Sets the characteristic coefficient of the recording equipment
	 * of a KConstantOfRecordingEquipment object.
	 * 
	 * @param	kConstantOfRecordingEquipment		the characteristic coefficient of the recording equipment
	 * 												to be set for the KConstantOfRecordingEquipment object
	 */
	public void setKConstantOfRecordingEquipment( int kConstantOfRecordingEquipment ) {
		this.kConstantOfRecordingEquipment = kConstantOfRecordingEquipment;
	}

	@Override
	public Element generateXMLElement( String name ) {
		return new Element( name ).setText( Integer.toString( kConstantOfRecordingEquipment ) );
	}
}
