/*   Copyright (C) 2007, Martin Barth

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

public class ActivityChangeInfo extends DataClass {
	/*	
	 * ActivityChangeInfo ::= OCTET STRING (SIZE(2))
	 * Value assignment  Octet Aligned:                     (16 bits)
	 * "scpaattttttttttt"B
	 *  For Data Memory recordings (or slot status):
                    Slot: 's'B
                      DRIVER, "0"B:
                      CO-DRIVER, "1"B:
                      B:1000_0000 = 0x80
	 */
	private boolean slot;
	public static final boolean DRIVER = false;
	public static final boolean CO_DRIVER = true;

	/*
                    Driving status: 'c'B
                      SINGLE, "0"B:
                      CREW, "1"B:
                      B:0100_0000 = 0x40
	 */
	private boolean drivingStatus;
	public static final boolean SINGLE = false;
	public static final boolean CREW = true;

	/*
                    Driver (or workshop) card status in the relevant slot: 'p'B
                      INSERTED, a card is inserted, "0"B:
                      NOT INSERTED, no card is inserted (or a card is withdrawn), "1"B:
                      B: 0010_0000 = 0x20
	 */
	private boolean cardStatus;
	public static final boolean INSERTED = false;
	public static final boolean NOT_INSERTED = true;

	/*                     
                    Activity: 'aa'B
                           BREAK/REST, "00"B:
                           AVAILABILITY, "01"B:
                           WORK, "10"B:
                           DRIVING, "11"B:
                           B:0001_1000 = 0x18
	 */
	byte activity;
	public static final byte BREAK = 0;
	public static final byte AVAILABILITY = 1;
	public static final byte WORK = 2;
	public static final byte DRIVING = 3;

	/*
                    Time of the change: Number of minutes since 00h00 on the given day. 'ttttttttttt'B
                    B 0000_0111 1111_1111 = 0x07 0xFF
	 * */
	int time;
	
	private byte[] activityChangeInfo;

	public ActivityChangeInfo(byte[] value) {
		activityChangeInfo = value;
		slot = ((0x80 & value[0]) == 0x80);
		drivingStatus = ((0x40 & value[0]) == 0x40);
		cardStatus = ((0x20 & value[0]) == 0x20);

		activity = (byte) (0x18 & value[0]);
		activity = (byte) (activity >> 3);
		
		byte tmp = (byte) (value[0] & 0x07);
		time = convertIntoUnsigned2ByteInt( new byte[] { tmp, value[1]});
	}

	@Override
	public Element generateXMLElement(String name) {
		Element node = new Element(name);
		node.addContent( new Element("activityChangeInfo").setText( convertIntoHexString(activityChangeInfo)));
		node.addContent( new Element("slot").setText( Boolean.toString( slot )));
		node.addContent( new Element("drivingStatus").setText( Boolean.toString( drivingStatus )));
		node.addContent( new Element("cardStatus").setText( Boolean.toString( cardStatus )));
		node.addContent( new Element("activity").setText( convertIntoHexString( activity )));
		node.addContent( new Element("time").setText(Integer.toString( time )));
		return node;
	}

}
