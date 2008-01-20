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
 * Data expressed in a readily printable numeric format.
 */
public class Datef extends DataClass {
	/*
	 * Datef ::= SEQUENCE {
	 * 	year BCDString(SIZE(2)), 2 bytes
	 * 	month BCDString(SIZE(1)), 1 byte
	 * 	day BCDString(SIZE(1)), 1 byte
	 * }
	 */
	private byte[] year;
	private byte month;
	private byte day;

	
	/**
	 * Constructor for a Datef object
	 */
	public Datef() {
		year = new byte[ 2 ];
		month = 0;
		day = 0;
	}

	/**
	 * Constructor for a Datef object
	 * 
	 * @param	value	byte array of a Datef structure
	 * 					whose data is used when the Datef
	 * 					object is created.
	 */
	public Datef( byte[] value ) {
		year = arrayCopy( value, 0, 2 );
		month = value[ 2 ];
		day = value[ 3 ];
	}

	/**
	 * Returns the year of a Datef object.
	 * 
	 * @return	the year of the Datef object as BCD
	 */
	public byte[] getYear() {
		return year;
	}

	/**
	 * Returns the year of a Datef object.
	 * 
	 * @return	the year of the Datef object
	 */
	public String getYearString() {
		return convertBCDStringIntoString( year );
	}
	
	/**
	 * Sets the year of a Datef object.
	 * 
	 * @param	year	the year as BCD to be set for the Datef object
	 */
	public void setYear( byte[] year ) {
		this.year = year;
	}
	
	/**
	 * Returns the month of a Datef object.
	 * 
	 * @return	the month of the Datef object as BCD
	 */
	public byte getMonth() {
		return month;
	}

	/**
	 * Returns the month of a Datef object.
	 * 
	 * @return	the month of the Datef object
	 */
	public String getMonthString() {
		return convertBCDStringIntoString( new byte[]{ month } );
	}
	
	/**
	 * Sets the month of a Datef object.
	 * 
	 * @param	month	the month as BCD to be set for the Datef object
	 */
	public void setMonth( byte month ) {
		this.month = month;
	}

	/**
	 * Returns the day of a Datef object.
	 * 
	 * @return	the day of the Datef object as BCD
	 */
	public byte getDay() {
		return day;
	}

	/**
	 * Returns the day of a Datef object.
	 * 
	 * @return	the day of the Datef object
	 */
	public String getDayString() {
		return convertBCDStringIntoString( new byte[]{ day } );
	}
	
	/**
	 * Sets the day of a Datef object.
	 * 
	 * @param	day		the day as BCD to be set for the Datef object
	 */
	public void setDay( byte day ) {
		this.day = day; 
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );
		node.addContent( new Element( "year" ).setText( getYearString() ) );
		node.addContent( new Element( "month" ).setText( getMonthString() ) );
		node.addContent( new Element( "day" ).setText( getDayString() ) );

		return node;
	}
}
