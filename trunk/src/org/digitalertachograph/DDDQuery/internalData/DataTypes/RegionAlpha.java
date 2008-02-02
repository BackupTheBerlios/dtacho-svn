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
 * Alphabetic reference to a region within a specified country.
 */
public class RegionAlpha extends DataClass {
	/*
	 * RegionAlpha ::= IA5String(SIZE(3)), 3 bytes
	 */

	private String regionAlpha;

	/**
	 * Constructor for a RegionAlpha object
	 */
	public RegionAlpha() {
		regionAlpha = new String();
	}

	/**
	 * Constructor for a RegionAlpha object
	 * 
	 * @param	value	byte array of an alphabetic reference to a region within a specified country
	 * 					that is used when the RegionAlpha object is created.
	 */
	public RegionAlpha( byte[] value ) {
		regionAlpha = new String( arrayCopy( value, 0, 3 ) );
	}

	/**
	 * Constructor for a RegionAlpha object
	 * 
	 * @param	regionAlpha		String that contains an alphabetic reference to a region within
	 * 							a specified country that is used when the RegionAlpha
	 * 							object is created.
	 */
	public RegionAlpha( String regionAlpha ) {
		setRegionAlpha( regionAlpha );
	}

	/**
	 * Returns the alphabetic reference to a region within a specified country
	 * of a RegionAlpha object.
	 * 
	 * @return	the alphabetic reference to a region within a specified country
	 * 			of the RegionAlpha object
	 */
	public String getRegionAlpha() {
		return regionAlpha;
	}

	/**
	 * Sets the alphabetic reference to a region within a specified country
	 * of a RegionAlpha object.
	 * 
	 * @param	regionAlpha		the alphabetic reference to a region within a specified country
	 * 							to be set for the RegionAlpha object
	 */
	public void setRegionAlpha( String regionAlpha ) {
		int regionAlphaLength = regionAlpha.length();

		if ( regionAlphaLength > 3 ) {
			regionAlphaLength = 3;
		}

		this.regionAlpha = regionAlpha.substring( 0, regionAlphaLength );
	}

	/**
	 * Returns the alphabetic reference to a region within a specified country of a RegionAlpha object.
	 * 
	 * @return	the alphabetic reference to a region within a specified country of a RegionAlpha object
	 */
	public String toString() {
		if ( regionAlpha == "   " ) {
			return "No information available";
		}
		else if ( regionAlpha == "AN " ) {
			return "Andalucía";
		}
		else if ( regionAlpha == "AR " ) {
			return "Aragón";
			}
		else if ( regionAlpha == "AST" ) {
			return "Asturias";
		}
		else if ( regionAlpha == "C  " ) {
			return "Cantabria";
		}
		else if ( regionAlpha == "CAT" ) {
			return "Cataluña";
		}
		else if ( regionAlpha == "CL " ) {
			return "Castilla-León";
		}
		else if ( regionAlpha == "CM " ) {
			return "Castilla-La-Mancha";
		}
		else if ( regionAlpha == "CV " ) {
			return "Valencia";
		}
		else if ( regionAlpha == "EXT" ) {
			return "Extremadura";
		}
		else if ( regionAlpha == "G  " ) {
			return "Galicia";
		}
		else if ( regionAlpha == "IB " ) {
			return "Baleares";
		}
		else if ( regionAlpha == "IC " ) {
			return "Canarias";
		}
		else if ( regionAlpha == "LR " ) {
			return "La Rioja";
		}
		else if ( regionAlpha == "M  " ) {
			return "Madrid";
		}
		else if ( regionAlpha == "MU " ) {
			return "Murcia";
		}
		else if ( regionAlpha == "NA " ) {
			return "Navarra";
		}
		else if ( regionAlpha == "PV " ) {
			return "País Vasco";
		}
		else {
			return "Invalid";
		}
	}

	@Override
	public Element generateXMLElement( String name ) {
		return new Element( name ).setText( regionAlpha );
	}
}
