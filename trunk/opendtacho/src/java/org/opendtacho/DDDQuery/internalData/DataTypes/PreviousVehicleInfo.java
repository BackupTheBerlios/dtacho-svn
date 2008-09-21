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
 * Information related to the vehicle previously used by a driver when
 * inserting his card in a vehicle unit.
 */
public class PreviousVehicleInfo extends DataClass {
	/*
	 * PreviousVehicleInfo ::= SEQUENCE {
	 * 	vehicleRegistrationIdentification VehicleRegistrationIdentification, 15 bytes
	 * 	cardWithdrawalTime TimeReal, 4 bytes
	 * }
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 19;

	private VehicleRegistrationIdentification vehicleRegistrationIdentification;
	private TimeReal cardWithdrawalTime;

	private DebugLogger debugLogger;


	/**
	 * Constructor for a PreviousVehicleInfo object
	 */
	public PreviousVehicleInfo() {
		vehicleRegistrationIdentification = new VehicleRegistrationIdentification();
		cardWithdrawalTime = new TimeReal();
	}

	/**
	 * Constructor for a PreviousVehicleInfo object
	 * 
	 * @param	value	byte array of a PreviousVehicleInfo structure
	 * 					whose data is used when the PreviousVehicleInfo
	 * 					object is created.
	 */
	public PreviousVehicleInfo( byte[] value ) {
		debugLogger = new DebugLogger();

		vehicleRegistrationIdentification = new VehicleRegistrationIdentification( arrayCopy( value, 0, 15 ) );
		debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Vehicle registration:" );
		debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Nation where vehicle is registered: %s\n", vehicleRegistrationIdentification.getVehicleRegistrationNation().toString() );
		debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Vehicle registration number: %s\n", vehicleRegistrationIdentification.getVehicleRegistrationNumber().getVehicleRegNumberString() );

		debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Card withdrawal time:" );
		cardWithdrawalTime = new TimeReal( arrayCopy( value, 15, 4 ) );
	}

	/**
	 * Returns the VRN and the registering Member State of the vehicle
	 * of a PreviousVehicleInfo object.
	 * 
	 * @return	the VRN and the registering Member State of the vehicle of the PreviousVehicleInfo object
	 */
	public VehicleRegistrationIdentification getCVehicleRegistrationIdentification() {
		return vehicleRegistrationIdentification;
	}

	/**
	 * Sets the VRN and the registering Member State of the vehicle
	 * of a PreviousVehicleInfo object.
	 * 
	 * @param	vehicleRegistrationIdentification	the VRN and the registering Member State of the vehicle
	 * 												to be set for the PreviousVehicleInfo object
	 */
	public void setVehicleRegistrationIdentification( VehicleRegistrationIdentification vehicleRegistrationIdentification ) {
		this.vehicleRegistrationIdentification = vehicleRegistrationIdentification;
	}

	/**
	 * Returns the card withdrawal date and time of a PreviousVehicleInfo object.
	 * 
	 * @return	the card withdrawal date and time of the PreviousVehicleInfo object
	 */
	public TimeReal getCardWithdrawalTime() {
		return cardWithdrawalTime;
	}

	/**
	 * Sets the card withdrawal date and time of a PreviousVehicleInfo object.
	 * 
	 * @param	cardWithdrawalTime	the card withdrawal date and time to be set
	 * 								for the PreviousVehicleInfo object
	 */
	public void setCardWithdrawalTime( TimeReal cardWithdrawalTime ) {
		this.cardWithdrawalTime = cardWithdrawalTime;
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		node.addContent( vehicleRegistrationIdentification.generateXMLElement( "vehicleRegistrationIdentification" ) );
		node.addContent( cardWithdrawalTime.generateXMLElement( "cardWithdrawalTime" ) );

		return node;
	}
}
