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
 * Alphabetic reference to a country, in accordance with the conventional coding of
 * countries on car bumper stickers, and/or as used on internationally harmonised
 * vehicle insurance papers (green card).
 */
public class NationAlpha extends DataClass {
	/*
	 * NationAlpha ::= IA5String(SIZE(3)), 3 bytes
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 3;

	private String nationAlpha;

	/**
	 * Constructor for a NationAlpha object
	 */
	public NationAlpha() {
		nationAlpha = new String();
	}

	/**
	 * Constructor for a NationAlpha object
	 * 
	 * @param	value	byte array of an alphabetic reference to a country that is used when the NationAlpha
	 * 					object is created.
	 */
	public NationAlpha( byte[] value ) {
		nationAlpha = new String( arrayCopy( value, 0, 3 ) );
	}

	/**
	 * Constructor for a NationAlpha object
	 * 
	 * @param	nationAlpha		String that contains an alphabetic reference to a country that is used
	 * 							when the NationAlpha object is created.
	 */
	public NationAlpha( String nationAlpha ) {
		setNationAlpha( nationAlpha );
	}

	/**
	 * Returns the alphabetic reference to a country of a NationAlpha object.
	 * 
	 * @return	alphabetic reference to a country of the NationAlpha object
	 */
	public String getNationAlpha() {
		return nationAlpha;
	}

	/**
	 * Sets the alphabetic reference to a country of a NationAlpha object.
	 * 
	 * @param	nationAlpha		the alphabetic reference to a country to be set
	 * 							for the NationAlpha object
	 */
	public void setNationAlpha( String nationAlpha ) {
		int nationAlphaLength = nationAlpha.length();

		if ( nationAlphaLength > 3 ) {
			nationAlphaLength = 3;
		}

		this.nationAlpha = nationAlpha.substring( 0, nationAlphaLength );
	}

	/**
	 * Returns the full country name of a NationAlpha object.
	 * 
	 * @return	the full country name of a NationAlpha object
	 */
	public String toString() {
		if ( nationAlpha.compareTo( "   " ) == 0 ) {
			return "No information available";
		}
		else if ( nationAlpha.compareTo( "A  " ) == 0 ) {
			return "Austria";
		}
		else if ( nationAlpha.compareTo( "AL " ) == 0 ) {
			return "Albania";
		}
		else if ( nationAlpha.compareTo( "AND" ) == 0 ) {
			return "Andorra";
		}
		else if ( nationAlpha.compareTo( "ARM" ) == 0 ) {
			return "Armenia";
		}
		else if ( nationAlpha.compareTo( "AZ " ) == 0 ) {
			return "Azerbaijan";
		}
		else if ( nationAlpha.compareTo( "B  " ) == 0 ) {
			return "Belgium";
		}
		else if ( nationAlpha.compareTo( "BG " ) == 0 ) {
			return "Bulgaria";
		}
		else if ( nationAlpha.compareTo( "BIH" ) == 0 ) {
			return "Bosnia and Herzegovina";
		}
		else if ( nationAlpha.compareTo( "BY " ) == 0 ) {
			return "Belarus";
		}
		else if ( nationAlpha.compareTo( "CH " ) == 0 ) {
			return "Switzerland";
		}
		else if ( nationAlpha.compareTo( "CY " ) == 0 ) {
			return "Cyprus";
		}
		else if ( nationAlpha.compareTo( "CZ " ) == 0 ) {
			return "Czech Republic";
		}
		else if ( nationAlpha.compareTo( "D  " ) == 0 ) {
			return "Germany";
		}
		else if ( nationAlpha.compareTo( "DK " ) == 0 ) {
			return "Denmark";
		}
		else if ( nationAlpha.compareTo( "E  " ) == 0 ) {
			return "Spain";
		}
		else if ( nationAlpha.compareTo( "EST" ) == 0 ) {
			return "Estonia";
		}
		else if ( nationAlpha.compareTo( "F  " ) == 0 ) {
			return "France";
		}
		else if ( nationAlpha.compareTo( "FIN" ) == 0 ) {
			return "Finland";
		}
		else if ( nationAlpha.compareTo( "FL " ) == 0 ) {
			return "Liechtenstein";
		}
		else if ( nationAlpha.compareTo( "FR " ) == 0 ) {
			return "Faeroe Islands";
		}
		else if ( nationAlpha.compareTo( "UK " ) == 0 ) {
			return "United Kingdom";
		}
		else if ( nationAlpha.compareTo( "GE " ) == 0 ) {
			return "Georgia";
		}
		else if ( nationAlpha.compareTo( "GR " ) == 0 ) {
			return "Greece";
		}
		else if ( nationAlpha.compareTo( "H  " ) == 0 ) {
			return "Hungary";
		}
		else if ( nationAlpha.compareTo( "HR " ) == 0 ) {
			return "Croatia";
		}
		else if ( nationAlpha.compareTo( "I  " ) == 0 ) {
			return "Italy";
		}
		else if ( nationAlpha.compareTo( "IRL" ) == 0 ) {
			return "Ireland";
		}
		else if ( nationAlpha.compareTo( "IS " ) == 0 ) {
			return "Iceland";
		}
		else if ( nationAlpha.compareTo( "KZ " ) == 0 ) {
			return "Kazakhstan";
		}
		else if ( nationAlpha.compareTo( "L  " ) == 0 ) {
			return "Luxembourg";
		}
		else if ( nationAlpha.compareTo( "LT " ) == 0 ) {
			return "Lithuania";
		}
		else if ( nationAlpha.compareTo( "LV " ) == 0 ) {
			return "Latvia";
		}
		else if ( nationAlpha.compareTo( "M  " ) == 0 ) {
			return "Malta";
		}
		else if ( nationAlpha.compareTo( "MC " ) == 0 ) {
			return "Monaco";
		}
		else if ( nationAlpha.compareTo( "MD " ) == 0 ) {
			return "Republic of Moldova";
		}
		else if ( nationAlpha.compareTo( "MK " ) == 0 ) {
			return "Macedonia";
		}
		else if ( nationAlpha.compareTo( "N  " ) == 0 ) {
			return "Norway";
		}
		else if ( nationAlpha.compareTo( "NL " ) == 0 ) {
			return "The Netherlands";
		}
		else if ( nationAlpha.compareTo( "P  " ) == 0 ) {
			return "Portugal";
		}
		else if ( nationAlpha.compareTo( "PL " ) == 0 ) {
			return "Poland";
		}
		else if ( nationAlpha.compareTo( "RO " ) == 0 ) {
			return "Romania";
		}
		else if ( nationAlpha.compareTo( "RSM" ) == 0 ) {
			return "San Marino";
		}
		else if ( nationAlpha.compareTo( "RUS" ) == 0 ) {
			return "Russian Federation";
		}
		else if ( nationAlpha.compareTo( "S  " ) == 0 ) {
			return "Sweden";
		}
		else if ( nationAlpha.compareTo( "SK " ) == 0 ) {
			return "Slovakia";
		}
		else if ( nationAlpha.compareTo( "SLO" ) == 0 ) {
			return "Slovenia";
		}
		else if ( nationAlpha.compareTo( "TM " ) == 0 ) {
			return "Turkmenistan";
		}
		else if ( nationAlpha.compareTo( "TR " ) == 0 ) {
			return "Turkey";
		}
		else if ( nationAlpha.compareTo( "UA " ) == 0 ) {
			return "Ukraine";
		}
		else if ( nationAlpha.compareTo( "V  " ) == 0 ) {
			return "Vatican City";
		}
		else if ( nationAlpha.compareTo( "YU " ) == 0 ) {
			return "Yugoslavia";
		}
		else if ( nationAlpha.compareTo( "UNK" ) == 0 ) {
			return "Unknown";
		}
		else if ( nationAlpha.compareTo( "EC " ) == 0 ) {
			return "European Community";
		}
		else if ( nationAlpha.compareTo( "EUR" ) == 0 ) {
			return "Rest of Europe";
		}
		else if ( nationAlpha.compareTo( "WLD" ) == 0 ) {
			return "Rest of the world";
		}
		else {
			return "Invalid";
		}
	}

	@Override
	public Element generateXMLElement( String name ) {
		if ( isValidXMLString( nationAlpha ) == false ) {
			return new Element( name );
		}
		else {
			return new Element( name ).setText( nationAlpha );
		}
	}
}
