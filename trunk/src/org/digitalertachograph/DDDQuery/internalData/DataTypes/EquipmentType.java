/*   Copyright (C) 2007, Martin Barth

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

public class EquipmentType extends DataClass {
	private byte equipmentType;
	
	/*
	 * EquipmentType ::= INTEGER(0..255)
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
	
	public final static int RESERVED = 0;
	public final static int DRIVER_CARD = 1;
	public final static int WORKSHOP_CARD = 2;
	public final static int CONTROL_CARD = 3;
	public final static int COMPANY_CARD = 4;
	public final static int MANUFACTURING_CARD = 5;
	public final static int VEHICLE_UNIT = 6;
	public final static int MOTION_SENSOR = 7;
	
	
	public EquipmentType(byte value){
		equipmentType = value;
	}

	public byte getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(byte equipmentType) {
		this.equipmentType = equipmentType;
	}
	
	@Override
	public Element generateXMLElement(String name) {
		Element node = new Element(name);
		node.addContent( new Element("equipmentType").setText( Byte.toString(equipmentType)));
		return node;
	}
}
