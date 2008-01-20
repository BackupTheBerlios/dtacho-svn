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

//import org.digitalertachograph.DDDQuery.internalData.DataClass;

/**
 * Date and time, stored on a driver card, of last card download (for other purposes
 * than control). This date is updateable by a VU or any card reader.
 */
public class LastCardDownload extends TimeReal {
	
	/**
	 * Constructor for a LastDownload object
	 */
	public LastCardDownload() {
		super();
	}

	/**
	 * Constructor for a LastCardDownload object
	 * 
	 * @param	value	byte array of a LastCardDownload structure
	 * 					whose data is used when the LastCardDownload
	 * 					object is created.
	 */
	public LastCardDownload( byte[] value ) {
		super( value );
	}

	/**
	 * Constructor for a LastCardDownload object
	 * 
	 * @param	i		the code for a combined date and time field, where the date and time
	 * 					are expressed as seconds past 00h.00m.00s on January 1970 GMT.
	 */
	public LastCardDownload( long i ) {
		super( i );
	}
}
