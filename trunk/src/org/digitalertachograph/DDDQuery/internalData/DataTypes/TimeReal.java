/*
    $Id$

    Copyright (C) 2007-2008, Martin Barth, Gerald Schnabel

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

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.digitalertachograph.DDDQuery.DebugLogger;
import org.digitalertachograph.DDDQuery.internalData.DataClass;
import org.jdom.*;

/**
 * Code for a combined date and time field, where the date and time 
 * are expressed as seconds past 00h.00m.00s. on 1 January 1970 GMT.
 */
public class TimeReal extends DataClass {
	/*
	 * Code for a combined date and time field, where the date and time 
	 * are expressed as seconds past 00h.00m.00s. on 1 January 1970 GMT.
	 * 
	 * TimeReal{INTEGER:TimeRealRange} ::= INTEGER(0..TimeRealRange), 4 bytes
	 * ---
	 * TimeRealRange ::= 2^32-1
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 4;

	private long timereal;
	private DebugLogger debugLogger;

	/**
	 * Constructor for a TimeReal object
	 */
	public TimeReal() {
		timereal = 0;
		debugLogger = new DebugLogger();
	}

	/**
	 * Constructor for a TimeReal object
	 * 
	 * @param	value	byte array of a TimeReal structure
	 * 					whose data is used when the TimeReal
	 * 					object is created.
	 */
	public TimeReal( byte[] value ) {
		this( convertIntoUnsigned4ByteInt( value ) );
	}

	/**
	 * Constructor for a TimeReal object
	 * 
	 * @param	i		the code for a combined date and time field, where the date and time
	 * 					are expressed as seconds past 00h.00m.00s on January 1970 GMT.
	 */
	public TimeReal( long i ) {
		this.timereal = i;
		debugLogger = new DebugLogger();

		if ( i != 0 ) {
			debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, " timestamp: %04x / %d", i, i );

			SimpleDateFormat timeRealSimpleDate = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss z" ); 
			timeRealSimpleDate.setTimeZone( TimeZone.getTimeZone( "UTC" ) );

			long timeRealTimeStamp = timereal * 1000;

			Date timeRealDate = new Date( timeRealTimeStamp );

			debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " - " + timeRealSimpleDate.format( timeRealDate ) );
		}
	}

	/**
	 * Returns the code for a combined date and time field, where the date and time
	 * are expressed as seconds past 00h.00m.00s on January 1970 GMT of a TimeReal object.
	 * 
	 * @return	the code for a combined date and time field, where the date and time
	 * 			are expressed as seconds past 00h.00m.00s on January 1970 GMT of the TimeReal object
	 */
	public long getTimeReal() {
		return timereal;
	}

	/**
	 * Sets the code for a combined date and time field, where the date and time
	 * are expressed as seconds past 00h.00m.00s on January 1970 GMT of a TimeReal object.
	 * 
	 * @param	timereal		the code for a combined date and time field, where the date and time
	 * 							are expressed as seconds past 00h.00m.00s on January 1970 GMT of a TimeReal object
	 */
	public void setTimeReal( long timereal ) {
		this.timereal = timereal;
	}

	@Override
	public Element generateXMLElement( String name ) {
		SimpleDateFormat timeRealSimpleDate = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss z" );
		timeRealSimpleDate.setTimeZone( TimeZone.getTimeZone( "UTC" ) );

		long timeRealTimeStamp = timereal * 1000;

		Date timeRealDate = new Date( timeRealTimeStamp );

		return new Element( name ).setText( timeRealSimpleDate.format( timeRealDate ) );
	}
}
