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
 * Information, stored in a driver or a workshop card, related to a fault associated
 * to the card holder.
 */
public class CardFaultRecord extends DataClass {
	/*
	 * CardFaultRecord ::= SEQUENCE {
	 * 	faultType EventFaultType, 1 byte
	 * 	faultBeginTime TimeReal, 4 bytes
	 * 	faultEndTime TimeReal, 4 bytes
	 * 	faultVehicleRegistration VehicleRegistrationIdentification, 15 bytes
	 * }
	 */

	private EventFaultType faultType; 
	private TimeReal faultBeginTime;
	private TimeReal faultEndTime;
	private VehicleRegistrationIdentification faultVehicleRegistration;

	
	/**
	 * Constructor for a CardFaultRecord object
	 */
	public CardFaultRecord() {
		faultType = new EventFaultType();
		faultBeginTime = new TimeReal();
		faultEndTime   = new TimeReal();
		faultVehicleRegistration = new VehicleRegistrationIdentification();
	}
	
	/**
	 * Constructor for a CardFaultRecord object
	 * 
	 * @param	value	byte array of a CardFaultRecord structure
	 * 					whose data is used when the CardFaultRecord
	 * 					object is created.
	 */
	public CardFaultRecord( byte[] value ) {
		faultType = new EventFaultType( value[ 0 ] );
		faultBeginTime = new TimeReal( arrayCopy( value, 1, 4 ) );
		faultEndTime   = new TimeReal( arrayCopy( value, 5, 4 ) );
		faultVehicleRegistration = new VehicleRegistrationIdentification( arrayCopy( value, 9, 15 ) );
	}

	/**
	 * Returns the type of the fault of a CardFaultRecord object.
	 * 
	 * @return	the type of the fault of the CardFaultRecord object
	 */
	public EventFaultType getFaultType() {
		return faultType;
	}

	/**
	 * Sets the type of the fault of a CardFaultRecord object.
	 * 
	 * @param	faultType		the type of the fault to be set for the CardFaultRecord object
	 */
	public void setFaultType( EventFaultType faultType ) {
		this.faultType = faultType;
	}

	/**
	 * Returns the timestamp (date and time) of beginning of fault
	 * of a CardFaultRecord object.
	 * 
	 * @return	the timestamp of beginning of fault of the CardFaultRecord object
	 */
	public TimeReal getFaultBeginTime() {
		return faultBeginTime;
	}

	/**
	 * Sets the timestamp (date and time) of beginning of fault of
	 * of a CardFaultRecord object.
	 * 
	 * @param	faultBeginTime		the timestamp (date and time) of beginning of fault
	 * 								to be set for the CardFaultRecord object
	 */
	public void setFaultBeginTime( TimeReal faultBeginTime ) {
		this.faultBeginTime = faultBeginTime;
	}

	/**
	 * Returns the timestamp (date and time) of end of fault
	 * of a CardFaultRecord object.
	 * 
	 * @return	the timestamp of end of fault of the CardFaultRecord object
	 */
	public TimeReal getFaultEndTime() {
		return faultEndTime;
	}

	/**
	 * Sets the timestamp (date and time) of end of fault of
	 * of a CardFaultRecord object.
	 * 
	 * @param	faultEndTime		the timestamp (date and time) of end of fault
	 * 								to be set for the CardFaultRecord object
	 */
	public void setFaultEndTime( TimeReal faultEndTime ) {
		this.faultEndTime = faultEndTime;
	}

	/**
	 * Returns the VRN and registering Member State of vehicle in which the fault happened
	 * of a CardFaultRecord object.
	 * 
	 * @return	the VRN and registering Member State of vehicle in which the fault happened
	 * 			of the CardFaultRecord object
	 */
	public VehicleRegistrationIdentification getFaultVehicleRegistration() {
		return faultVehicleRegistration;
	}

	/**
	 * Sets the VRN and registering Member State of vehicle in which the fault happened
	 * of a CardFaultRecord object.
	 * 
	 * @param	faultVehicleRegistration		the VRN and registering Member State of vehicle
	 * 											in which the fault happened	to be set for the
	 * 											CardFaultRecord object
	 */
	public void setFaultVehicleRegistration( VehicleRegistrationIdentification faultVehicleRegistration ) {
		this.faultVehicleRegistration = faultVehicleRegistration;
	}
	
	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		node.addContent( faultType.generateXMLElement( "faultType" ) );
		node.addContent( faultBeginTime.generateXMLElement( "faultBeginTime" ) );
		node.addContent( faultEndTime.generateXMLElement( "faultEndTime" ) );
		node.addContent( faultVehicleRegistration.generateXMLElement( "faultVehicleRegistration" ) );

		return node;
	}
}
