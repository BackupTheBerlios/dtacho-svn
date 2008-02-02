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

/**
 * Maximum speed measured in a previously defined duration.
 */
public class SpeedMax extends Speed {
	/*
	 * SpeedMax ::= Speed, 1 byte
	 */


	/**
	 * Constructor for a SpeedMax object
	 */
	public SpeedMax() {
		super();
	}

	/**
	 * Constructor for a SpeedMax object
	 * 
	 * @param	b	byte that contains the maximum speed measured in a previously defined duration
	 * 				when the SpeedMax object is created.
	 */
	public SpeedMax( byte b ) {
		super( b );
	}
}
