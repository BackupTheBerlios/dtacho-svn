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

public class Datef extends DataClass {
	/*
	 * Datef ::= SEQUENCE {
	 * 	year BCDString(SIZE(2)),
	 * 	month BCDString(SIZE(1)),
	 * 	day BCDString(SIZE(1))
	 * }
	 */
	private String year;
	private String month;
	private String day;

	
	// public Datef(){
	//	
	//}

	/**
	 * Constructor for a Datef object
	 * 
	 * @param	value	byte array of a Datef structure
	 * 					whose data is used when the Datef
	 * 					object is created.
	 */
	public Datef(byte[] value){
		year = convertIntoBCDString( arrayCopy(value, 0, 2));
		month = convertIntoBCDString( arrayCopy(value, 2, 1));
		day = convertIntoBCDString( arrayCopy(value, 3, 1));
	}

	public void setDay(byte [] day){
		this.day = convertIntoBCDString(day); 
	}

	public void setMonth(byte[] month) {
		this.month = convertIntoBCDString(month);
	}
	
	public void setYear(byte[] year) {
		this.year = convertIntoBCDString(year);
	}
	
	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	@Override
	public Element generateXMLElement(String name) {
		Element node = new Element(name);
		node.addContent(new Element("year").setText(year));
		node.addContent(new Element("month").setText(month));
		node.addContent(new Element("day").setText(day));
		return node;
	}
}
