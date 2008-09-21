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

package org.opendtacho.DDDQuery.internalData.DataTypes;

import org.jdom.Element;
import org.opendtacho.DDDQuery.DebugLogger;
import org.opendtacho.DDDQuery.internalData.DataClass;

/**
 * Information, stored in a vehicle unit, related to over speeding events since
 * the last over speeding control
 */
public class VuOverSpeedingControlData extends DataClass {
	/*
	 * VuOverSpeedingControlData ::= SEQUENCE {
	 * 	lastOverspeedControlTime TimeReal, 4 bytes
	 * 	firstOverspeedSince TimeReal, 4 bytes
	 *  numberOfOverspeedSince OverspeedNumber, 1 byte
	 * }
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 9;

	private TimeReal lastOverspeedControlTime;
	private TimeReal firstOverspeedSince;
	private OverspeedNumber numberOfOverspeedSince;

	private DebugLogger debugLogger;


	/**
	 * Constructor for a VuOverSpeedingControlData object
	 */
	public VuOverSpeedingControlData() {
		lastOverspeedControlTime = new TimeReal();
		firstOverspeedSince = new TimeReal();
		numberOfOverspeedSince = new OverspeedNumber();
	}

	/**
	 * Constructor for a VuOverSpeedingControlData object
	 * 
	 * @param	value	byte array of a VuOverSpeedingControlData structure whose data is used
	 * 					when the VuOverSpeedingControlData object is created.
	 */
	public VuOverSpeedingControlData( byte[] value ) {
		debugLogger = new DebugLogger();

		debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Last overspeed control time:" );
		lastOverspeedControlTime = new TimeReal( arrayCopy( value, 0, 4 ) );

		debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] First overspeed since last control:" );
		firstOverspeedSince = new TimeReal( arrayCopy( value, 4, 4 ) );

		numberOfOverspeedSince = new OverspeedNumber( value[ 8 ] );
		debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Number of overspeeding events since last control: %d\n", numberOfOverspeedSince.getOverspeedNumber() );
	}

	/**
	 * Returns the date and time of the last over speeding control
	 * of a VuOverSpeedingControlData object.
	 * 
	 * @return	the date and time of the last over speeding control
	 * 			of the VuOverSpeedingControlData object
	 */
	public TimeReal getLastOverspeedControlTime() {
		return lastOverspeedControlTime;
	}

	/**
	 * Sets the date and time of the last over speeding control
	 * of a VuOverSpeedingControlData object.
	 * 
	 * @param	lastOverspeedControlTime		the date and time of the last over speeding control
	 * 											of a VuOverSpeedingControlData object
	 */
	public void setLastOverspeedControlTime( TimeReal lastOverspeedControlTime ) {
		this.lastOverspeedControlTime = lastOverspeedControlTime;
	}

	/**
	 * Returns the date and time of the first over speeding following this over speeding control
	 * of a VuOverSpeedingControlData object.
	 * 
	 * @return	the date and time of the first over speeding following this over speeding control
	 * 			of the VuOverSpeedingControlData object
	 */
	public TimeReal getFirstOverspeedSince() {
		return firstOverspeedSince;
	}

	/**
	 * Sets the date and time of the first over speeding following this over speeding control
	 * of a VuOverSpeedingControlData object.
	 * 
	 * @param	firstOverspeedSince		the date and time of the first over speeding following this over speeding control
	 * 									of a VuOverSpeedingControlData object
	 */
	public void setFirstOverspeedSince( TimeReal firstOverspeedSince ) {
		this.firstOverspeedSince = firstOverspeedSince;
	}

	/**
	 * Returns the number of over speeding events since the last over speeding control
	 * of a VuOverSpeedingControlData object.
	 * 
	 * @return	the number of over speeding events since the last over speeding control
	 * 			of the VuOverSpeedingControlData object
	 */
	public OverspeedNumber getNumberOfOverspeedSince() {
		return numberOfOverspeedSince;
	}

	/**
	 * Sets the number of over speeding events since the last over speeding control
	 * of a VuOverSpeedingControlData object.
	 * 
	 * @param	numberOfOverspeedSince		the number of over speeding events since the last over speeding control
	 * 										of a VuOverSpeedingControlData object
	 */
	public void setNumberOfOverspeedSince( OverspeedNumber numberOfOverspeedSince ) {
		this.numberOfOverspeedSince = numberOfOverspeedSince;
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		node.addContent( lastOverspeedControlTime.generateXMLElement( "lastOverspeedControlTime" ) );
		node.addContent( firstOverspeedSince.generateXMLElement( "firstOverspeedSince" ) );
		node.addContent( numberOfOverspeedSince.generateXMLElement( "numberOfOverspeedSince" ) );

		return node;
	}
}
