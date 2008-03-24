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
 * Slot status at 00.00 and/or a driver status at 00.00 and/or changes of activity
 * and/or changes of driving status and/or changes of card status for a driver or a co-driver.
 */
public class ActivityChangeInfo extends DataClass {
	/*
	 * ActivityChangeInfo ::= OCTET STRING (SIZE(2)), 2 bytes
	 * Value assignment  Octet Aligned: (16 bits)
	 * 'scpaattttttttttt'B
	 */

	/*
	 *	Slot: 's'B
	 *	 DRIVER, '0'B:
	 *	 CO-DRIVER, '1'B:
	 *	 B:1000_0000 = 0x80
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 2;

	private byte[] value;

	private boolean slot;
	private static final byte SLOT_MASK = (byte)0x80;

	/**
	 * Slot: DRIVER
	 */
	public static final boolean DRIVER = false;
	/**
	 * Slot: CO_DRIVER
	 */
	public static final boolean CO_DRIVER = true;

	/*
	 * Depending on the usage of the datatype ActivityChangeInfo (card (driver/workshop) / data memory recordings)
	 * this field has different meanings:
	 *	Driving status / activity status: 'c'B
	 *	 SINGLE / UNKNOWN, '0'B:
	 *	 CREW / KNOWN, '1'B:
	 *	 B:0100_0000 = 0x40
	 */
	private boolean drivingStatus;
	private boolean activityStatus;
	private static final byte DRIVINGSTATUS_MASK = (byte)0x40;
	private static final byte ACTIVITYSTATUS_MASK = (byte)0x40;

	/**
	 * Driving status: SINGLE / activity status UNKNOWN
	 */
	public static final boolean SINGLE = false;
	public static final boolean UNKNOWN = false;
	/**
	 * Driving status: CREW / activity status KNOWN
	 */
	public static final boolean CREW = true;
	public static final boolean KNOWN = true;

	/*
	 *	Driver (or workshop) card status in the relevant slot: 'p'B
	 *	 INSERTED, a card is inserted, '0'B:
	 *	 NOT INSERTED, no card is inserted (or a card is withdrawn), '1'B:
	 *	 B:0010_0000 = 0x20
	 */
	private boolean cardStatus;
	private static final byte CARDSTATUS_MASK = (byte)0x20;

	/**
	 * Driver (or workshop) card status in the relevant slot: INSERTED
	 */
	public static final boolean INSERTED = false;
	/**
	 * Driver (or workshop) card status in the relevant slot: NOT_INSERTED
	 */
	public static final boolean NOT_INSERTED = true;

	/*
	 *	Activity: 'aa'B
	 *	 BREAK/REST, '00'B:
	 *	 AVAILABILITY, '01'B:
	 *	 WORK, '10'B:
	 *	 DRIVING, '11'B:
	 *	 B:0001_1000 = 0x18
	 */
	private byte activity;
	private static final byte ACTIVITY_MASK = (byte)0x18;

	/**
	 * Activity: BREAK/REST
	 */
	public static final byte BREAK = 0;
	/**
	 * Activity: AVAILABILITY
	 */
	public static final byte AVAILABILITY = 1;
	/**
	 * Activity: WORK
	 */
	public static final byte WORK = 2;
	/**
	 * Activity: DRIVING
	 */
	public static final byte DRIVING = 3;

	/*
	 *	Time of the change: Number of minutes since 00h00 on the given day. 'ttttttttttt'B
	 *	 B 0000_0111 1111_1111 = 0x07 0xFF
	 */
	private int time;
	private static final byte TIME_UPPERBYTE_MASK = (byte)0x07;
	private static final byte TIME_LOWERBYTE_MASK = (byte)0xff;


	/**
	 * Constructor for an ActivityChangeInfo object
	 * 
	 * @param	value	byte array of an ActivityChangeInfo structure
	 * 					whose data is used when the ActivityChangeInfo
	 * 					object is created
	 */
	public ActivityChangeInfo( byte[] value ) {
		this.value = value;
		slot = ( ( value[ 0 ] & SLOT_MASK ) == SLOT_MASK );
		drivingStatus = ( (value[ 0 ] & DRIVINGSTATUS_MASK ) == DRIVINGSTATUS_MASK );
		activityStatus = ( (value[ 0 ] & ACTIVITYSTATUS_MASK ) == ACTIVITYSTATUS_MASK );
		cardStatus = ( ( value[ 0 ] & CARDSTATUS_MASK ) == CARDSTATUS_MASK );

		activity = (byte)( value[ 0 ] & ACTIVITY_MASK );
		activity = (byte)( activity >> 3 );

		byte tmp = (byte)( value[ 0 ] & TIME_UPPERBYTE_MASK );
		time = convertIntoUnsigned2ByteInt( new byte[] { tmp, (byte)( value[ 1 ] & TIME_LOWERBYTE_MASK ) } );
	}

	/**
	 * Returns the value of an ActivityChangeInfo object.
	 * 
	 * @return	the value of the ActivityChangeInfo object ({@link #DRIVER}, {@link #CO_DRIVER})
	 */
	public byte[] getValue() {
		return value;
	}

	/**
	 * Returns the slot of an ActivityChangeInfo object.
	 * 
	 * @return	the slot of the ActivityChangeInfo object ({@link #DRIVER}, {@link #CO_DRIVER})
	 */
	public boolean getSlot() {
		return slot;
	}

	/**
	 * Returns the slot as string of an ActivityChangeInfo object.
	 * 
	 * @return	the slot as string of the ActivityChangeInfo object ({@link #DRIVER}, {@link #CO_DRIVER})
	 */
	public String getSlotString() {
		if ( slot == false ) {
			return "driver";
		}
		else {
			return "co-driver";
		}
	}

	/**
	 * Returns the driving status of an ActivityChangeInfo object.
	 * 
	 * @return	the driving status of the ActivityChangeInfo object ({@link #SINGLE}, {@link #CREW})
	 */
	public boolean getDrivingStatus() {
		return drivingStatus;
	}

	/**
	 * Returns the driving status as string of an ActivityChangeInfo object.
	 * 
	 * @return	the driving status as string of the ActivityChangeInfo object ({@link #SINGLE}, {@link #CREW})
	 */
	public String getDrivingStatusString() {
		if ( drivingStatus == false ) {
			return "single";
		}
		else {
			return "crew";
		}
	}

	/**
	 * Returns the activity status of an ActivityChangeInfo object.
	 * 
	 * @return	the activity status of the ActivityChangeInfo object ({@link #UNKNOWN}, {@link #KNOWN})
	 */
	public boolean getActivityStatus() {
		return activityStatus;
	}

	/**
	 * Returns the activity status as string of an ActivityChangeInfo object.
	 * 
	 * @return	the activity status as string of the ActivityChangeInfo object ({@link #UNKNOWN}, {@link #KNOWN})
	 */
	public String getActivityStatusString() {
		if ( activityStatus == false ) {
			return "unknown";
		}
		else {
			return "known";
		}
	}

	/**
	 * Returns the card status of an ActivityChangeInfo object.
	 * 
	 * @return	the card status of the ActivityChangeInfo object ({@link #INSERTED}, {@link #NOT_INSERTED})
	 */
	public boolean getCardStatus() {
		return cardStatus;
	}

	/**
	 * Returns the card status as string of an ActivityChangeInfo object.
	 * 
	 * @return	the card status as string of the ActivityChangeInfo object ({@link #INSERTED}, {@link #NOT_INSERTED})
	 */
	public String getCardStatusString() {
		if ( cardStatus == false ) {
			return "inserted";
		}
		else {
			return "not inserted";
		}
	}

	/**
	 * Returns the activity of an ActivityChangeInfo object.
	 * 
	 * @return	the activity of the ActivityChangeInfo object ({@link #BREAK}, {@link #AVAILABILITY},
	 * 			{@link #WORK}, {@link #DRIVING})
	 */
	public byte getActivity() {
		return activity;
	}

	/**
	 * Returns the activity as string of an ActivityChangeInfo object.
	 * 
	 * @return	the activity as string of the ActivityChangeInfo object ({@link #BREAK}, {@link #AVAILABILITY},
	 * 			{@link #WORK}, {@link #DRIVING})
	 */
	public String getActivityString() {
		switch ( activity ) {
			case ActivityChangeInfo.BREAK:
				return "break";

			case ActivityChangeInfo.AVAILABILITY:
				return "availability";

			case ActivityChangeInfo.WORK:
				return "work";

			case ActivityChangeInfo.DRIVING:
				return "driving";

			default:
				return "????";
		}
	}

	/**
	 * Returns the time of an ActivityChangeInfo object.
	 * 
	 * @return	the time of the ActivityChangeInfo object in number of minutes
	 *			since 00h00 on the given day
	 */
	public int getTime() {
		return time;
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		node.addContent( new Element( "value" ).setText( convertIntoHexString( value ) ) );
		node.addContent( new Element( "slot" ).setText( Boolean.toString( slot ) ) );
		node.addContent( new Element( "drivingStatus" ).setText( Boolean.toString( drivingStatus ) ) );
		node.addContent( new Element( "cardStatus" ).setText( Boolean.toString( cardStatus ) ) );
		node.addContent( new Element( "activity" ).setText( convertIntoHexString( activity ) ) );
		node.addContent( new Element( "time" ).setText( Integer.toString( time ) ) );

		return node;
	}
}
