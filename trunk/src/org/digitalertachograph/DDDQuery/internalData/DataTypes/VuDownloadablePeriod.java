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

import org.digitalertachograph.DDDQuery.DebugLogger;
import org.digitalertachograph.DDDQuery.internalData.DataClass;
import org.jdom.Element;

/**
 * Oldest and latest dates for which a vehicle unit holds data related to drivers activities.
 */
public class VuDownloadablePeriod extends DataClass {
	/*
	 * VuDownloadablePeriod ::= SEQUENCE {
	 * 	minDownloadableTime TimeReal, 4 bytes
	 * 	maxDownloadableTime TimeReal, 4 bytes
	 * }
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 8;

	private TimeReal minDownloadableTime;
	private TimeReal maxDownloadableTime;

	private DebugLogger debugLogger;


	/**
	 * Constructor for a VuDownloadablePeriod object
	 */
	public VuDownloadablePeriod() {
		minDownloadableTime = new TimeReal();
		maxDownloadableTime = new TimeReal();
	}

	/**
	 * Constructor for a VuDownloadablePeriod object
	 * 
	 * @param	value	byte array of a VuDownloadablePeriod structure
	 * 					whose data is used when the VuDownloadablePeriod
	 * 					object is created.
	 */
	public VuDownloadablePeriod( byte[] value ) {
		debugLogger = new DebugLogger();

		debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Oldest card insertion date and time:" );
		minDownloadableTime = new TimeReal( arrayCopy( value, 0, 4 ) );

		debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Latest card withdrawal date and time:" );
		maxDownloadableTime = new TimeReal( arrayCopy( value, 4, 4 ) );
	}

	/**
	 * Returns the oldest card insertion or activity change or place entry date
	 * and time stored in the VU of a VuDownloadablePeriod object.
	 * 
	 * @return	the oldest card insertion or activity change or place entry date
	 * 			and time stored in the VU of the VuDownloadablePeriod object
	 */
	public TimeReal getMinDownloadableTime() {
		return minDownloadableTime;
	}

	/**
	 * Sets the oldest card insertion or activity change or place entry date
	 * and time stored in the VU of a VuDownloadablePeriod object.
	 * 
	 * @param	minDownloadableTime		the oldest card insertion or activity change
	 * 									or place entry date and time stored in the VU
	 * 									to be set for the VuDownloadablePeriod object
	 */
	public void setMinDownloadableTime( TimeReal minDownloadableTime ) {
		this.minDownloadableTime = minDownloadableTime;
	}

	/**
	 * Returns the latest card withdrawal or activity change or place entry date
	 * and time stored in the VU of a VuDownloadablePeriod object.
	 * 
	 * @return	the latest card withdrawal or activity change or place entry date
	 * 			and time stored in the VU of the VuDownloadablePeriod object
	 */
	public TimeReal getMaxDownloadableTime() {
		return maxDownloadableTime;
	}

	/**
	 * Sets the latest card withdrawal or activity change or place entry date
	 * and time stored in the VU of a VuDownloadablePeriod object.
	 * 
	 * @param	maxDownloadableTime		the latest card withdrawal or activity change
	 * 									or place entry date and time stored in the VU
	 * 									to be set for the VuDownloadablePeriod object
	 */
	public void setMaxDownloadableTime( TimeReal maxDownloadableTime ) {
		this.maxDownloadableTime = maxDownloadableTime;
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		node.addContent( minDownloadableTime.generateXMLElement( "minDownloadableTime" ) );
		node.addContent( maxDownloadableTime.generateXMLElement( "maxDownloadableTime" ) );

		return node;
	}
}
