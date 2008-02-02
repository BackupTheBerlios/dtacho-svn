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
 * Numerical reference to a region within a specified country.
 */
public class RegionNumeric extends DataClass {
	/*
	 * RegionNumeric ::= OCTET STRING (SIZE(1)), 1 byte
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
	 */
	public RegionNumeric() {
		regionNumeric = 0;
	}

	/**
	 * Constructor for a RegionNumeric object
	 * 
	 * @param	regionNumeric		byte with the numerical reference to a region within a specified country
	 */
	public RegionNumeric( byte regionNumeric ) {
		this.regionNumeric = regionNumeric;
	}

	/**
	 * Returns the numerical reference to a region within a specified country
	 * of a RegionNumeric object.
	 * 
	 * @return	the numerical reference to a region within a specified country
	 * 			of the RegionNumeric object
	 */
	public byte getRegionNumeric() {
		return regionNumeric;
	}

	/**
	 * Sets the numerical reference to a region within a specified country
	 * of a RegionNumeric object.
	 * 
	 * @param	regionNumeric	the numerical reference to a region within a specified country
	 * 							to be set for the RegionNumeric object
	 */
	public void setRegionNumeric( byte regionNumeric ) {
		this.regionNumeric = regionNumeric;
	}

	/**
	 * Returns the region name of a RegionNumeric object.
	 * 
	 * @return	the region name of the RegionNumeric object
	 */
	public String toString() {
		switch( regionNumeric ) {
			case 0x00:
				return "No information available";

			case 0x01:
				return "Andalucía";

			case 0x02:
				return "Aragón";

			case 0x03:
				return "Asturias";

			case 0x04:
				return "Cantabria";

			case 0x05:
				return "Cataluña";

			case 0x06:
				return "Castilla-León";

			case 0x07:
				return "Castilla-La-Mancha";

			case 0x08:
				return "Valencia";

			case 0x09:
				return "Extremadura";

			case 0x0a:
				return "Galicia";

			case 0x0b:
				return "Baleares";

			case 0x0c:
				return "Canarias";

			case 0x0d:
				return "La Rioja";

			case 0x0e:
				return "Madrid";

			case 0x0f:
				return "Murcia";

			case 0x10:
				return "Navarra";

			case 0x011:
				return "País Vasco";

			default:
				return "Invalid";
		}
	}
	
	@Override
	public Element generateXMLElement( String name ) {
		return new Element( name ).setText( Byte.toString( regionNumeric ) );
	}
}
