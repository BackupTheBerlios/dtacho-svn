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

package org.digitalertachograph.DDDQuery.internalData.DataTypes;

import org.digitalertachograph.DDDQuery.internalData.DataClass;
import org.jdom.Element;

public class RegionNumeric extends DataClass {
	/*
	 * RegionNumeric ::= OCTET STRING (SIZE(1))
	 * 		Value assignment:
	 * 		'00'H No information available
	 * 		Spain:
	 * 		'01'H Andalucía
	 * 		'02'H Aragón
	 * 		'03'H Asturias
	 * 		'04'H Cantabria
	 * 		'05'H Cataluña
	 * 		'06'H Castilla-León
	 * 		'07'H Castilla-La-Mancha
	 * 		'08'H Valencia
	 * 		'09'H Extremadura
	 * 		'0A'H Galicia
	 * 		'0B'H Baleares
	 * 		'0C'H Canarias
	 *		'0D'H La Rioja
	 *		'0E'H Madrid
	 *		'0F'H Murcia
	 *		'10'H Navarra
	 *		'11'H País Vasco.
	 */

	private byte regionNumeric;

	
	/**
	 * Constructor for a RegionNumeric object
	 * 
	 * @param	regionNumeric		byte that references a region within a specified country.
	 */
	public RegionNumeric(byte regionNumeric){
		this.regionNumeric = regionNumeric;
	}

	public byte getRegionNumeric() {
		return regionNumeric;
	}

	public void setRegionNumeric(byte regionNumeric) {
		this.regionNumeric = regionNumeric;
	}
	
	@Override
	public Element generateXMLElement(String name) {
		Element node = new Element(name);
		node.addContent( new Element("regionNumeric").setText( Byte.toString( regionNumeric )));
		return node;
	}
}
