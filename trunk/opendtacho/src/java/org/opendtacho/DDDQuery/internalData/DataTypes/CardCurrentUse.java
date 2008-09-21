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

package org.opendtacho.DDDQuery.internalData.DataTypes;

import org.jdom.Element;
import org.opendtacho.DDDQuery.DebugLogger;
import org.opendtacho.DDDQuery.internalData.DataClass;

/**
 * Information about the actual usage of the card.
 */
public class CardCurrentUse extends DataClass{
	/*
	 * CardCurrentUse ::= SEQUENCE {
	 * 	sessionOpenTime TimeReal, 4 bytes
	 * 	sessionOpenVehicle VehicleRegistrationIdentification, 15 bytes
	 * }
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 19;

	private TimeReal sessionOpenTime;
	private VehicleRegistrationIdentification sessionOpenVehicle;

	private DebugLogger debugLogger;


	/**
	 * Constructor for a CardCurrentUse object
	 */
	public CardCurrentUse() {
		sessionOpenTime = new TimeReal();
		sessionOpenVehicle = new VehicleRegistrationIdentification();
	}

	/**
	 * Constructor for a CardCurrentUse object
	 * 
	 * @param	value	byte array of a CardCurrentUse structure
	 * 					whose data is used when the CardCurrentUse
	 * 					object is created.
	 */
	public CardCurrentUse( byte[] value ) {
		debugLogger = new DebugLogger();

		debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Card insertion time:" );
		sessionOpenTime = new TimeReal( arrayCopy( value, 0, 4 ) );
		sessionOpenVehicle = new VehicleRegistrationIdentification( arrayCopy( value, 4, 15 ) );
	}

	/**
	 * Returns the timestamp when the card is inserted for the current usage
	 * of a CardCurrentUse object.
	 * 
	 * @return	the timestamp when the card is inserted for the current usage
	 * 			of the CardCurrentUse object
	 */
	public TimeReal getSessionOpenTime() {
		return sessionOpenTime;
	}

	/**
	 * Sets the timestamp when the card is inserted for the current usage
	 * of a CardCurrentUse object.
	 * 
	 * @param	sessionOpenTime		the timestamp when the card is inserted for the current usage
	 * 								to be set for the CardCurrentUse object
	 */
	public void setSessionOpenTime( TimeReal sessionOpenTime ) {
		this.sessionOpenTime = sessionOpenTime;
	}

	/**
	 * Returns the identification of the current used vehicle, set at card insertion
	 * of a CardCurrentUse object.
	 * 
	 * @return	the identification of the current used vehicle, set at card insertion
	 * 			of the CardCurrentUse object
	 */
	public VehicleRegistrationIdentification getSessionOpenVehicle() {
		return sessionOpenVehicle;
	}

	/**
	 * Sets the identification of the current used vehicle, set at card insertion
	 * of a CardCurrentUse object.
	 * 
	 * @param	sessionOpenVehicle	the identification of the current used vehicle, set at card insertion
	 * 								to be set for the CardCurrentUse object
	 */
	public void setSessionOpenVehicle( VehicleRegistrationIdentification sessionOpenVehicle ) {
		this.sessionOpenVehicle = sessionOpenVehicle;
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		if ( sessionOpenTime.getTimeReal() != 0 ) {
			node.addContent( sessionOpenTime.generateXMLElement( "sessionOpenTime" ) );
			node.addContent( sessionOpenVehicle.generateXMLElement( "sessionOpenVehicle" ) );

			return node;
		}
		else {
			return null;
		}
	}
}
