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


public class NationNumeric extends DataClass {
	/*
	 * NationNumeric ::= INTEGER(0..255)
	 */

	private short nationNumeric;

	
	/**
	 * Constructor for a NationNumeric object
	 */
	public NationNumeric(){
		nationNumeric = 0;
	}

	/**
	 * Constructor for a NationNumeric object
	 * 
	 * @param	nationNumeric	short value that indicates a numerical refernce to a country.
	 */
	public NationNumeric(short nationNumeric){
		this.nationNumeric = nationNumeric;
	}

	/**
	 * Constructor for a NationNumeric object
	 * 
	 * @param	b	byte that indicates a numerical refernce to a country.
	 */
	public NationNumeric(byte b){
		nationNumeric = convertIntoUnsigned1ByteInt(b);
	}

	public short getNationNumeric() {
		return nationNumeric;
	}

	public void setNationNumeric(short nationNumeric) {
		this.nationNumeric = nationNumeric;
	}
	
	@Override
	public Element generateXMLElement(String name) {
		return new Element(name).setText(Short.toString(nationNumeric));
	}

	/*  TODO toString();
	 * 
- - No information available (00)H,
- - Austria (01)H,
- - Albania (02)H,
- - Andorra (03)H,
- - Armenia (04)H,
- - Azerbaijan (05)H,
- - Belgium (06)H,
- - Bulgaria (07)H,
- - Bosnia and Herzegovina (08)H,
- - Belarus (09)H,
- - Switzerland (0A)H,
- - Cyprus (0B)H,
- - Czech Republic (0C)H,
- - Germany (0D)H,
- - Denmark (0E)H,
- - Spain (0F)H,
- - Estonia (10)H,
- - France (11)H,
- - Finland (12)H,
- - Liechtenstein (13)H,
- - Faeroe Islands (14)H,
- - United Kingdom (15)H,
- - Georgia (16)H,
- - Greece (17)H,
- - Hungary (18)H,
- - Croatia (19)H,
- - Italy (1A)H,
- - Ireland (1B)H,
- - Iceland (1C)H,
- - Kazakhstan (1D)H,
- - Luxembourg (1E)H,
- - Lithuania (1F)H,
- - Latvia (20)H,
- - Malta (21)H,
- - Monaco (22)H,
- - Republic of Moldova (23)H,
- - Macedonia (24)H,
- - Norway (25)H,
- - Netherlands (26)H,
- - Portugal (27)H,
- - Poland (28)H,
- - Romania (29)H,
- - San Marino (2A)H,
- - Russian Federation (2B)H,
- - Sweden (2C)H,
- - Slovakia (2D)H,
- - Slovenia (2E)H,
- - Turkmenistan (2F)H,
- - Turkey (30)H,
- - Ukraine (31)H,
- - Vatican City (32)H,
- - Yugoslavia (33)H,
- - RFU (34..FC)H,
- - European Community (FD)H,
- - Rest of Europe (FE)H,
- - Rest of the world (FF)H
	 */
}
