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

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.digitalertachograph.DDDQuery.internalData.DataClass;
import org.jdom.*;

public class TimeReal extends DataClass{
	/*
	 * Code for a combined date and time field, where the date and time 
	 * are expressed as seconds past 00h.00m.00s. on 1 January 1970 GMT.
	 * 
	 * TimeReal{INTEGER:TimeRealRange} ::= INTEGER(0..TimeRealRange)
	 * 
	 * TimeRealRange ::= 2^32-1
	 */
	
	private long timereal = 0;
	
	public TimeReal(){
	
	}
	

	/**
	 * Constructor for a TimeReal object
	 * 
	 * @param	value	byte array of a TimeReal structure
	 * 					whose data is used when the TimeReal
	 * 					object is created.
	 */
	public TimeReal(byte[] value){
		this( convertIntoUnsigned4ByteInt(value));
	}
	
	public TimeReal(long i){
		this.timereal = i;
		if(i != 0){
		System.out.print(" timestamp: " + i);
		Date d = new Date(i * 1000);
		System.out.println(" - " + DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.GERMANY).format(d) );
		}
	}

	public long getTimereal() {
		return timereal;
	}

	public void setTimereal(long timereal) {
		this.timereal = timereal;
	}
	
	@Override
	public Element generateXMLElement(String name) {
		return new Element(name).setText(Long.toString(timereal));
	}
}
