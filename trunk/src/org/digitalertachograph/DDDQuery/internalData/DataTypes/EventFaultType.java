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

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 1;

	private byte eventFaultType;


	/**
	 * Constructor for an EventFaultType object
	 */
	public EventFaultType() {
		eventFaultType = 0;
	}

	/**
	 * Constructor for an EventFaultType object
	 * 
	 * @param	value	byte array of a EventFaultType structure
	 * 					whose data is used when the EventFaultType
	 * 					object is created.
	 */
	public EventFaultType( byte[] value ) {
		this.eventFaultType = value[ 0 ];
	}

	/**
	 * Constructor for an EventFaultType object
	 * 
	 * @param	eventFaultType		byte that qualifies an event or fault
	 */
	public EventFaultType( byte eventFaultType ) {
		this.eventFaultType = eventFaultType;
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

	/**
	 * Returns the event fault type as string of an EventFaultType object.
	 * 
	 * @return	the event fault type as string of the EventFaultType object
	 */
	public String toString() {
		// invalid event fault types
		if ( eventFaultType > 0x4f ) {
			return "????";
		}

		// valid event fault types
		// general events
		if ( eventFaultType == 0x00 ) {
			return "no further details";
		}
		if ( eventFaultType == 0x01 ) {
			return "insertion of a non-valid card";
		}
		if ( eventFaultType == 0x02 ) {
			return "card conflict";
		}
		if ( eventFaultType == 0x03 ) {
			return "time overlap";
		}
		if ( eventFaultType == 0x04 ) {
			return "driving without an appropriate card";
		}
		if ( eventFaultType == 0x05 ) {
			return "card insertion while driving";
		}
		if ( eventFaultType == 0x06 ) {
			return "last card session not correctly closed";
		}
		if ( eventFaultType == 0x07 ) {
			return "over speeding";
		}
		if ( eventFaultType == 0x08 ) {
			return "power supply interruption";
		}
		if ( eventFaultType == 0x09 ) {
			return "motion data error";
		}

		// vehicle unit related security breach attempt events
		if ( eventFaultType == 0x10 ) {
			return "no further details";
		}
		if ( eventFaultType == 0x11 ) {
			return "motion sensor authentication failure";
		}
		if ( eventFaultType == 0x12 ) {
			return "tachograph card authentication failure";
		}
		if ( eventFaultType == 0x13 ) {
			return "unauthorised change of motion sensor";
		}
		if ( eventFaultType == 0x14 ) {
			return "card data input integrity error";
		}
		if ( eventFaultType == 0x15 ) {
			return "stored user data integrity error";
		}
		if ( eventFaultType == 0x16 ) {
			return "internal data transfer error";
		}
		if ( eventFaultType == 0x17 ) {
			return "unauthorised case opening";
		}
		if ( eventFaultType == 0x18 ) {
			return "hardware sabotage";
		}

		// sensor related security breach attempt events
		if ( eventFaultType == 0x20 ) {
			return "no further details";
		}
		if ( eventFaultType == 0x21 ) {
			return "authentication failure";
		}
		if ( eventFaultType == 0x22 ) {
			return "stored data integrity error";
		}
		if ( eventFaultType == 0x23 ) {
			return "internal data transfer error";
		}
		if ( eventFaultType == 0x24 ) {
			return "unauthorised case opening";
		}
		if ( eventFaultType == 0x25 ) {
			return "hardware sabotage";
		}

		// recording equipment faults
		if ( eventFaultType == 0x30 ) {
			return "no further details";
		}
		if ( eventFaultType == 0x31 ) {
			return "VU internal fault";
		}
		if ( eventFaultType == 0x32 ) {
			return "printer fault";
		}
		if ( eventFaultType == 0x33 ) {
			return "display fault";
		}
		if ( eventFaultType == 0x34 ) {
			return "downloading fault";
		}
		if ( eventFaultType == 0x35 ) {
			return "sensor fault";
		}

		// card faults
		if ( eventFaultType == 0x40 ) {
			return "no further details";
		}

		// unknown event fault types
		// RFU = Reserved for future use
		return "RFU";
	}

	@Override
	public Element generateXMLElement( String name ) {
		return new Element( name ).setText( convertIntoHexString( eventFaultType ) );
	}
}
