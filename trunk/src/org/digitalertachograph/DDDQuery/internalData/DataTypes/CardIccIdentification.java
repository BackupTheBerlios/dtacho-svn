/*   Copyright (C) 2007, Martin Barth, Gerald Schnabel

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

public class CardIccIdentification extends DataClass {
	/* name, 						size, default,	comment
	 * clockStop,					1,	0x00		OCTET STRING (SIZE(1)), 1 byte
	 * cardExtendedSerialNumber,	8,	0x00..0x00	ExtendedSerialNumber, 8 bytes
	 * cardApprovalNumber,			8,	0x20..0x20	CardApprovalNumber, 8 bytes
	 * cardPersonaliserID			1,	0x00		OCTET STRING (SIZE(1)), 1 byte
	 * embedderIcAssemblerID		5,	0x00..0x00	OCTET STRING (SIZE(5)), 5 bytes
	 * icIdentifier					2,	0x00..0x00	OCTET STRING (SIZE(2)), 2 bytes
	 * 
	 * ---
	 * CardApprovalNumber ::= IA5String(SIZE(8))
	 * ---
	 * ExtendedSerialNumber ::= SEQUENCE {
	 * 		serialNumber INTEGER(0..2^32-1), 4 bytes
	 * 		monthYear BCDString(SIZE(2)), 2 bytes
	 * 		type OCTET STRING(SIZE(1)), 1 byte
	 * 		manufacturerCode ManufacturerCode, 1 byte
	 * }
	 * ---
	 * ManufacturerCode ::= INTEGER(0..255), 1 byte
	 */
	
	private byte clockStop;
	private ExtendedSerialNumber cardExtendedSerialNumber;
	private String cardApprovalNumber;
	private byte[] cardPersonaliserID;
	private byte[] embedderIcAssemblerID;
	private byte[] icIdentifier;

	
	/**
	 * Constructor for a CardIccIdentification object
	 * 
	 * @param	value	byte array of a CardIccIdentification structure
	 * 					whose data is used when the CardIccIdentification
	 * 					object is created.
	 */
	public CardIccIdentification(byte[] value){
		clockStop = value[0];
		cardExtendedSerialNumber = new ExtendedSerialNumber( arrayCopy(value, 1, 8));
		cardApprovalNumber = new String(arrayCopy(value, 9, 8));
		cardPersonaliserID = arrayCopy(value, 17, 1);
		embedderIcAssemblerID = arrayCopy(value, 18, 5);
		icIdentifier = arrayCopy(value,23, 2);
	}
	
	public String getCardApprovalNumber() {
		return cardApprovalNumber;
	}

	public ExtendedSerialNumber getCardExtendedSerialNumber() {
		return cardExtendedSerialNumber;
	}

	public void setCardExtendedSerialNumber(
			ExtendedSerialNumber cardExtendedSerialNumber) {
		this.cardExtendedSerialNumber = cardExtendedSerialNumber;
	}

	public byte[] getCardPersonaliserID() {
		return cardPersonaliserID;
	}

	public void setCardPersonaliserID(byte[] cardPersonaliserID) {
		this.cardPersonaliserID = cardPersonaliserID;
	}

	public byte getClockStop() {
		return clockStop;
	}

	public void setClockStop(byte clockStop) {
		this.clockStop = clockStop;
	}

	public byte[] getEmbedderIcAssemblerID() {
		return embedderIcAssemblerID;
	}

	public void setEmbedderIcAssemblerID(byte[] embedderIcAssemblerID) {
		this.embedderIcAssemblerID = embedderIcAssemblerID;
	}

	public byte[] getIcIdentifier() {
		return icIdentifier;
	}

	public void setIcIdentifier(byte[] icIdentifier) {
		this.icIdentifier = icIdentifier;
	}

	public void setCardApprovalNumber(String cardApprovalNumber) {
		this.cardApprovalNumber = cardApprovalNumber;
	}

	@Override
	public Element generateXMLElement(String name) {
		Element node = new Element(name);
		node.addContent( new Element("clockStop").setText( Byte.toString(clockStop) ));
		node.addContent( cardExtendedSerialNumber.generateXMLElement("cardExtendedSerialNumber"));
		node.addContent( new Element("cardApprovalNumber").setText(cardApprovalNumber));
		node.addContent( new Element("cardPersonaliserID").setText( convertIntoHexString(cardPersonaliserID)));
		node.addContent( new Element("embedderIcAssemblerID").setText( convertIntoHexString(embedderIcAssemblerID)));
		node.addContent( new Element("icIdentifier").setText( convertIntoHexString(icIdentifier)));
		return node;
	}
}
