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
import org.opendtacho.DDDQuery.internalData.DataClass;

/**
 * Code explaining why an event or a fault has been recorded.
 */
public class EventFaultRecordPurpose extends DataClass {
	/*
	 * EventFaultRecordPurpose ::= OCTET STRING(SIZE(1)), 1 byte
	 * ---
	 * Value assignment:
	 * '00'H one of the 10 most recent (or last) events or faults
	 * '01'H the longest event for one of the last 10 days of occurrence
	 * '02'H one of the 5 longest events over the last 365 days
	 * '03'H the last event for one of the last 10 days of occurrence
	 * '04'H the most serious event for one of the last 10 days of occurrence
	 * '05'H one of the 5 most serious events over the last 365 days
	 * '06'H the first event or fault having occurred after the last calibration
	 * '07'H an active/on-going event or fault
	 * '08'H to '7F'H RFU
	 * '80'H to 'FF'H manufacturer specific.
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 1;

	private byte eventFaultRecordPurpose;
	
	
	/**
	 * Constructor for an EventFaultRecordPurpose object
	 */
	public EventFaultRecordPurpose() {
		eventFaultRecordPurpose = 0;
	}

	/**
	 * Constructor for an EventFaultRecordPurpose object
	 * 
	 * @param	value	byte array of a EventFaultRecordPurpose structure
	 * 					whose data is used when the EventFaultRecordPurpose
	 * 					object is created.
	 */
	public EventFaultRecordPurpose( byte[] value ) {
		this.eventFaultRecordPurpose = value[ 0 ];
	}

	/**
	 * Constructor for an EventFaultRecordPurpose object
	 * 
	 * @param	eventFaultRecordPurpose		byte with the code explaining why an event
	 * 										or a fault has been recorded
	 */
	public EventFaultRecordPurpose( byte eventFaultRecordPurpose ) {
		this.eventFaultRecordPurpose = eventFaultRecordPurpose;
	}

	/**
	 * Returns the code explaining why an event or a fault has been recorded
	 * of a EventFaultRecordPurpose object.
	 * 
	 * @return	the code explaining why an event or a fault has been recorded
	 * 			of the EventFaultRecordPurpose object
	 */
	public byte getEventFaultRecordPurpose() {
		return eventFaultRecordPurpose;
	}

	/**
	 * Sets the code explaining why an event or a fault has been recorded
	 * of a EventFaultRecordPurpose object.
	 * 
	 * @param	eventFaultRecordPurpose		the code explaining why an event or a fault has been recorded
	 * 										to be set for the EventFaultRecordPurpose object
	 */
	public void setEventFaultRecordPurpose( byte eventFaultRecordPurpose ) {
		this.eventFaultRecordPurpose = eventFaultRecordPurpose;
	}

	/**
	 * Returns the event fault record purpose as string.
	 * 
	 * @return	the event fault record purpose as string
	 */
	public String toString() {
		if ( eventFaultRecordPurpose == 0x00 ) {
			return "one of the 10 most recent (or last) events or faults";
		}
		if ( eventFaultRecordPurpose == 0x01 ) {
			return "the longest event for one of the last 10 days of occurrence";
		}
		if ( eventFaultRecordPurpose == 0x02 ) {
			return "one of the 5 longest events over the last 365 days";
		}
		if ( eventFaultRecordPurpose == 0x03 ) {
			return "the last event for one of the last 10 days of occurrence";
		}
		if ( eventFaultRecordPurpose == 0x04 ) {
			return "the most serious event for one of the last 10 days of occurrence";
		}
		if ( eventFaultRecordPurpose == 0x05 ) {
			return "one of the 5 most serious events over the last 365 days";
		}
		if ( eventFaultRecordPurpose == 0x06 ) {
			return "the first event or fault having occurred after the last calibration";
		}
		if ( eventFaultRecordPurpose == 0x07 ) {
			return "an active/on-going event or fault";
		}
		if ( ( eventFaultRecordPurpose >= 0x08 ) && ( eventFaultRecordPurpose <= 0x7f ) ) {
			return "RFU";
		}
		if ( ( eventFaultRecordPurpose >= 0x80 ) && ( eventFaultRecordPurpose <= 0xff ) ) {
			return "manufacturer specific";
		}

		return "????";
	}

	@Override
	public Element generateXMLElement( String name ) {
		return new Element( name ).setText( convertIntoHexString( eventFaultRecordPurpose ) );
	}
}
