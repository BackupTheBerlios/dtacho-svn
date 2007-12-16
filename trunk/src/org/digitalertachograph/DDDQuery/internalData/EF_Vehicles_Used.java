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

package org.digitalertachograph.DDDQuery.internalData;

import org.digitalertachograph.DDDQuery.internalData.DataTypes.CardVehiclesUsed;
import org.jdom.Element;


/**
 * EF_Vehicles_Used,
 * 
 * Council Regulation (EEC) No 3821/85 File ID: 0505
 * 
 */
public class EF_Vehicles_Used extends DataClass {
	public int size;
	private CardVehiclesUsed cardVehiclesUsed;

	
	/**
	 * Constructor for an EF_Vehicles_Used object
	 * 
	 * @param	value	byte array of an EF_Vehicles_Used structure
	 * 					whose data is used when the EF_Vehicles_Used
	 * 					object is created.
	 */
	public EF_Vehicles_Used(byte[] value){
		size = value.length;
		cardVehiclesUsed = new CardVehiclesUsed( value);
	}
	
	public Element generateXMLElement(String name){
		// discard name - this.getClass().getSimpleName() is unique!
		Element node = new Element(this.getClass().getSimpleName());
		Element child = cardVehiclesUsed.generateXMLElement("cardVehiclesUsed");
		node.addContent(child);
		return node;
	}
}
