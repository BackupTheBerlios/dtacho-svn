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
 * Code qualifying an event or a fault.
 */
public class EventFaultType extends DataClass {
	/*
	 * EventFaultType ::= OCTET STRING (SIZE(1)), 1 byte
	 * 
	 * Value assignment:
	 * 
	 * '0x' General events,
	 *  '00'H No further details,
	 *  '01'H Insertion of a non-valid card,
	 *  '02'H Card conflict,
	 *  '03'H Time overlap,
	 *  '04'H Driving without an appropriate card,
	 *  '05'H Card insertion while driving,
	 *  '06'H Last card session not correctly closed,
	 *  '07'H Over speeding,
	 *  '08'H Power supply interruption,
	 *  '09'H Motion data error,
	 *  '0A'H to '0F'H RFU,
	 * 
	 * '1x'H Vehicle unit related security breach attempt events,
	 *  '10'H No further details,
	 *  '11'H Motion sensor authentication failure,
	 *  '12'H Tachograph card authentication failure,
	 *  '13'H Unauthorised change of motion sensor,
	 *  '14'H Card data input integrity error
	 *  '15'H Stored user data integrity error,
	 *  '16'H Internal data transfer error,
	 *  '17'H Unauthorised case opening,
	 *  '18'H Hardware sabotage,
	 *  '19'H to '1F'H RFU,
	 * 
	 * '2x'H Sensor related security breach attempt events,
	 *  '20'H No further details,
	 *  '21'H Authentication failure,
	 *  '22'H Stored data integrity error,
	 *  '23'H Internal data transfer error,
	 *  '24'H Unauthorised case opening,
	 *  '25'H Hardware sabotage,
	 *  '26'H to '2F'H RFU,
	 * 
	 * '3x'H Recording equipment faults,
	 *  '30'H No further details,
	 *  '31'H VU internal fault,
	 *  '32'H Printer fault,
	 *  '33'H Display fault,
	 *  '34'H Downloading fault,
	 *  '35'H Sensor fault,
	 *  '36'H to '3F'H RFU
	 * 
	 * '4x'H Card faults,
	 *  '40'H No further details,
	 *  '41'H to '4F'H RFU
	 * 
	 * '50'H to '7F'H RFU,
	 *
	 * '80'H to 'FF'H Manufacturer specific.
	 */
	
	private byte eventFaultType;
	private byte category;

	
	/**
	 * EventFaultType: GENERAL_EVENT
	 */
	public final byte GENERAL_EVENT = 0;
	/**
	 * EventFaultType: VEHICLE_UNIT_RELATED_SECURITY_BREACH_ATTEMPT_EVENT
	 */
	public final byte VEHICLE_UNIT_RELATED_SECURITY_BREACH_ATTEMPT_EVENT = 1;
	/**
	 * EventFaultType: SENSOR_RELATED_SECURITY_BREACH_ATTEMPT_EVENT
	 */
	public final byte SENSOR_RELATED_SECURITY_BREACH_ATTEMPT_EVENT = 2;
	/**
	 * EventFaultType: RECORDING_EQUIPMENT_FAULT
	 */
	public final byte RECORDING_EQUIPMENT_FAULT = 3;
	/**
	 * EventFaultType: CARD_FAULT
	 */
	public final byte CARD_FAULT = 4;
	/**
	 * EventFaultType: RFU
	 */
	public final byte RFU = 5;
	/**
	 * EventFaultType: MANUFACTURER_SPECIFIC
	 */
	public final byte MANUFACTURER_SPECIFIC = 6;
	
	
	/**
	 * Constructor for an EventFaultType object
	 */
	public EventFaultType() {
		eventFaultType = 0;
		category = 0;
	}

	/**
	 * Constructor for an EventFaultType object
	 * 
	 * @param	eventFaultType		byte that qualifies an event or fault
	 */
	public EventFaultType( byte eventFaultType ) {
		this.eventFaultType = eventFaultType;
		category = (byte)( ( eventFaultType & 0xf0 ) >> 4 );

		if ( ( category >= 5 ) && ( category < 8) ) {
			// 0x50 ... 0x7F
			category = RFU;
		}
		else if ( category >= 8 ) {
			// 0x80 ... 0xFF
			category = MANUFACTURER_SPECIFIC;
		}
	}

	/**
	 * Returns the category of the code qualifying an event or a fault
	 * of a EventFaultType object.
	 * 
	 * @return	the category of the code qualifying an event or a fault
	 * 			of the EventFaultType object
	 */
	public byte getCategory() {
		return category;
	}

	/**
	 * Sets the category of the code qualifying an event or a fault
	 * of a EventFaultType object.
	 * 
	 * @param	category	the category of the code qualifying an event or a fault
	 * 						to be set for the EventFaultType object
	 */
	public void setCategory( byte category ) {
		this.category = category;
	}

	/**
	 * Returns the code qualifying an event or a fault
	 * of a EventFaultType object.
	 * 
	 * @return	the code qualifying an event or a fault
	 * 			of the EventFaultType object
	 */
	public byte getEventFaultType() {
		return eventFaultType;
	}

	/**
	 * Sets the code qualifying an event or a fault
	 * of a EventFaultType object.
	 * 
	 * @param	eventFaultType		the code qualifying an event or a fault
	 * 								to be set for the EventFaultType object
	 */
	public void setEventFaultType( byte eventFaultType ) {
		this.eventFaultType = eventFaultType;
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );
		node.addContent( new Element( "category" ).setText( Byte.toString( category ) ) );
		node.addContent( new Element( "eventFaultType" ).setText( Byte.toString( eventFaultType ) ) );

		return node;
	}
}
