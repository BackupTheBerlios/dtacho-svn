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

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 3;

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
		if ( regionAlpha.compareTo( "   " ) == 0 ) {
			return "No information available";
		}
		else if ( regionAlpha.compareTo( "AN " ) == 0 ) {
			return "Andalucía";
		}
		else if ( regionAlpha.compareTo( "AR " ) == 0 ) {
			return "Aragón";
			}
		else if ( regionAlpha.compareTo( "AST" ) == 0 ) {
			return "Asturias";
		}
		else if ( regionAlpha.compareTo( "C  " ) == 0 ) {
			return "Cantabria";
		}
		else if ( regionAlpha.compareTo( "CAT" ) == 0 ) {
			return "Cataluña";
		}
		else if ( regionAlpha.compareTo( "CL " ) == 0 ) {
			return "Castilla-León";
		}
		else if ( regionAlpha.compareTo( "CM " ) == 0 ) {
			return "Castilla-La-Mancha";
		}
		else if ( regionAlpha.compareTo( "CV " ) == 0 ) {
			return "Valencia";
		}
		else if ( regionAlpha.compareTo( "EXT" ) == 0 ) {
			return "Extremadura";
		}
		else if ( regionAlpha.compareTo( "G  " ) == 0 ) {
			return "Galicia";
		}
		else if ( regionAlpha.compareTo( "IB " ) == 0 ) {
			return "Baleares";
		}
		else if ( regionAlpha.compareTo( "IC " ) == 0 ) {
			return "Canarias";
		}
		else if ( regionAlpha.compareTo( "LR " ) == 0 ) {
			return "La Rioja";
		}
		else if ( regionAlpha.compareTo( "M  " ) == 0 ) {
			return "Madrid";
		}
		else if ( regionAlpha.compareTo( "MU " ) == 0 ) {
			return "Murcia";
		}
		else if ( regionAlpha.compareTo( "NA " ) == 0 ) {
			return "Navarra";
		}
		else if ( regionAlpha.compareTo( "PV " ) == 0 ) {
			return "País Vasco";
		}
		else {
			return "Invalid";
		}
	}

	@Override
	public Element generateXMLElement( String name ) {
		if ( isValidXMLString( regionAlpha ) == false ) {
			return new Element( name );
		}
		else {
			return new Element( name ).setText( regionAlpha );
		}
	}
}
