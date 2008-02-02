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
 * Alphabetic reference to a country, in accordance with the conventional coding of
 * countries on car bumper stickers, and/or as used on internationally harmonised
 * vehicle insurance papers (green card).
 */
public class NationAlpha extends DataClass {
	/*
	 * NationAlpha ::= IA5String(SIZE(3)), 3 bytes
	 */

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
		if ( nationAlpha == "   " ) {
			return "No information available";
		}
		else if ( nationAlpha == "A  " ) {
			return "Austria";
		}
		else if ( nationAlpha == "AL " ) {
			return "Albania";
		}
		else if ( nationAlpha == "AND" ) {
			return "Andorra";
		}
		else if ( nationAlpha == "ARM" ) {
			return "Armenia";
		}
		else if ( nationAlpha == "AZ " ) {
			return "Azerbaijan";
		}
		else if ( nationAlpha == "B  " ) {
			return "Belgium";
		}
		else if ( nationAlpha == "BG " ) {
			return "Bulgaria";
		}
		else if ( nationAlpha == "BIH" ) {
			return "Bosnia and Herzegovina";
		}
		else if ( nationAlpha == "BY " ) {
			return "Belarus";
		}
		else if ( nationAlpha == "CH " ) {
			return "Switzerland";
		}
		else if ( nationAlpha == "CY " ) {
			return "Cyprus";
		}
		else if ( nationAlpha == "CZ " ) {
			return "Czech Republic";
		}
		else if ( nationAlpha == "D  " ) {
			return "Germany";
		}
		else if ( nationAlpha == "DK " ) {
			return "Denmark";
		}
		else if ( nationAlpha == "E  " ) {
			return "Spain";
		}
		else if ( nationAlpha == "EST" ) {
			return "Estonia";
		}
		else if ( nationAlpha == "F  " ) {
			return "France";
		}
		else if ( nationAlpha == "FIN" ) {
			return "Finland";
		}
		else if ( nationAlpha == "FL " ) {
			return "Liechtenstein";
		}
		else if ( nationAlpha == "FR " ) {
			return "Faeroe Islands";
		}
		else if ( nationAlpha == "UK " ) {
			return "United Kingdom";
		}
		else if ( nationAlpha == "GE " ) {
			return "Georgia";
		}
		else if ( nationAlpha == "GR " ) {
			return "Greece";
		}
		else if ( nationAlpha == "H  " ) {
			return "Hungary";
		}
		else if ( nationAlpha == "HR " ) {
			return "Croatia";
		}
		else if ( nationAlpha == "I  " ) {
			return "Italy";
		}
		else if ( nationAlpha == "IRL" ) {
			return "Ireland";
		}
		else if ( nationAlpha == "IS " ) {
			return "Iceland";
		}
		else if ( nationAlpha == "KZ " ) {
			return "Kazakhstan";
		}
		else if ( nationAlpha == "L  " ) {
			return "Luxembourg";
		}
		else if ( nationAlpha == "LT " ) {
			return "Lithuania";
		}
		else if ( nationAlpha == "LV " ) {
			return "Latvia";
		}
		else if ( nationAlpha == "M  " ) {
			return "Malta";
		}
		else if ( nationAlpha == "MC " ) {
			return "Monaco";
		}
		else if ( nationAlpha == "MD " ) {
			return "Republic of Moldova";
		}
		else if ( nationAlpha == "MK " ) {
			return "Macedonia";
		}
		else if ( nationAlpha == "N  " ) {
			return "Norway";
		}
		else if ( nationAlpha == "NL " ) {
			return "The Netherlands";
		}
		else if ( nationAlpha == "P  " ) {
			return "Portugal";
		}
		else if ( nationAlpha == "PL " ) {
			return "Poland";
		}
		else if ( nationAlpha == "RO " ) {
			return "Romania";
		}
		else if ( nationAlpha == "RSM" ) {
			return "San Marino";
		}
		else if ( nationAlpha == "RUS" ) {
			return "Russian Federation";
		}
		else if ( nationAlpha == "S  " ) {
			return "Sweden";
		}
		else if ( nationAlpha == "SK " ) {
			return "Slovakia";
		}
		else if ( nationAlpha == "SLO" ) {
			return "Slovenia";
		}
		else if ( nationAlpha == "TM " ) {
			return "Turkmenistan";
		}
		else if ( nationAlpha == "TR " ) {
			return "Turkey";
		}
		else if ( nationAlpha == "UA " ) {
			return "Ukraine";
		}
		else if ( nationAlpha == "V  " ) {
			return "Vatican City";
		}
		else if ( nationAlpha == "YU " ) {
			return "Yugoslavia";
		}
		else if ( nationAlpha == "UNK" ) {
			return "Unknown";
		}
		else if ( nationAlpha == "EC " ) {
			return "European Community";
		}
		else if ( nationAlpha == "EUR" ) {
			return "Rest of Europe";
		}
		else if ( nationAlpha == "WLD" ) {
			return "Rest of the world";
		}
		else {
			return "Invalid";
		}
	}

	@Override
	public Element generateXMLElement( String name ) {
		return new Element( name ).setText( nationAlpha );
	}
}
