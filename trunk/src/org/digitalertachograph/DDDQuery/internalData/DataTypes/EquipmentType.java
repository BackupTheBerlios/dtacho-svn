/*   Copyright (C) 2007-2008, Martin Barth, Gerald Schnabel

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
 * Code to distinguish different types of equipment for the tachograph application.
 */
public class EquipmentType extends DataClass {
	/*
	 * EquipmentType ::= INTEGER(0..255), 1 byte
	 * ---
	 * - - Reserved (0),
	 * - - Driver Card (1),
	 * - - Workshop Card (2),
	 * - - Control Card (3),
	 * - - Company Card (4),
	 * - - Manufacturing Card (5),
	 * - - Vehicle Unit (6),
	 * - - Motion Sensor (7),
	 * - - RFU (8..255)
	 */

	private short equipmentType;


	/**
	 * Equipment type: RESERVED
	 */
	public final static short RESERVED = 0;
	/**
	 * Equipment type: DRIVER_CARD
	 */
	public final static short DRIVER_CARD = 1;
	/**
	 * Equipment type: WORKSHOP_CARD
	 */
	public final static short WORKSHOP_CARD = 2;
	/**
	 * Equipment type: CONTROL_CARD
	 */
	public final static short CONTROL_CARD = 3;
	/**
	 * Equipment type: COMPANY_CARD
	 */
	public final static short COMPANY_CARD = 4;
	/**
	 * Equipment type: MANUFACTURING_CARD
	 */
	public final static short MANUFACTURING_CARD = 5;
	/**
	 * Equipment type: VEHICLE_UNIT
	 */
	public final static short VEHICLE_UNIT = 6;
	/**
	 * Equipment type: MOTION_SENSOR
	 */
	public final static short MOTION_SENSOR = 7;


	/**
	 * Constructor for an EquipmentType object
	 */
	public EquipmentType() {
		equipmentType = RESERVED;
	}

	/**
	 * Constructor for an EquipmentType object
	 * 
	 * @param	value	byte that indicates the type of equipment for the
	 * 					tachograph application
	 */
	public EquipmentType( byte value ) {
		equipmentType = convertIntoUnsigned1ByteInt( value );
	}

	/**
	 * Returns the equipment type of an EquipmentType object.
	 * 
	 * @return	equipment type of the EquipmentType object ({@link #RESERVED}, {@link #DRIVER_CARD},
	 * 			{@link #WORKSHOP_CARD}, {@link #CONTROL_CARD}, {@link #COMPANY_CARD}, {@link #MANUFACTURING_CARD},
	 * 			{@link #VEHICLE_UNIT}, {@link #MOTION_SENSOR})
	 */
	public short getEquipmentType() {
		return equipmentType;
	}

	/**
	 * Sets the equipment type of an EquipmentType object.
	 * 
	 * @param	equipmentType		byte that contains the equipment type to be set for
	 * 								the EquipmentType object
	 */
	public void setEquipmentType( short equipmentType ) {
		this.equipmentType = equipmentType;
	}

	@Override
	public Element generateXMLElement( String name ) {
		return new Element( name ).setText( Short.toString( equipmentType ) );
	}
}
