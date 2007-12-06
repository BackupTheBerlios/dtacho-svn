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

public class CardICCIdentification extends DataClass {
	/* name, 						size, default,	comment
	 * clockStop,					1,	0x00		OCTET STRING (SIZE(1)),
	 * cardExtendedSerialNumber,	8,	0x00..0x00	ExtendedSerialNumber
	 * cardApprovalNumber,			8,	0x20..0x20	CardApprovalNumber
	 * cardPersonaliserID			1,	0x00		OCTET STRING (SIZE(1))
	 * embedderIcAssemblerID		5,	0x00..0x00	OCTET STRING (SIZE(5))
	 * icIdentifier					2,	0x00..0x00	OCTET STRING (SIZE(2))
	 * 
	 * ---
	 * CardApprovalNumber ::= IA5String(SIZE(8)) (seite 57)
	 * ---
	 * ExtendedSerialNumber ::= SEQUENCE {
	 * 		serialNumber INTEGER(0..2^32-1) <- 4 byte
	 * 		monthYear BCDString(SIZE(2))
	 * 		type OCTET STRING(SIZE(1))
	 * 		manufacturerCode ManufacturerCode <- 1byte
	 * } (seite 72)
	 * ---
	 * ManufacturerCode ::= INTEGER(0..255) (seite 74)
	 */
	
	private byte clockStop;
	private ExtendedSerialNumber cardExtendedSearialNumber;
	private String cardApprovalNumber;
	private byte[] cardPersonaliserID;
	private byte[] embedderIcAssemberID;
	private byte[] icIdentfier;

	public CardICCIdentification(byte[] value){
		clockStop = value[0];
		cardExtendedSearialNumber = new ExtendedSerialNumber( arrayCopy(value, 1, 8));
		cardApprovalNumber = new String(arrayCopy(value, 9, 8));
		cardPersonaliserID = arrayCopy(value, 17, 1);
		embedderIcAssemberID = arrayCopy(value, 18, 5);
		icIdentfier = arrayCopy(value,23, 2);
	}
	
	public String getCardApprovalNumber() {
		return cardApprovalNumber;
	}

	public ExtendedSerialNumber getCardExtendedSearialNumber() {
		return cardExtendedSearialNumber;
	}

	public void setCardExtendedSearialNumber(
			ExtendedSerialNumber cardExtendedSearialNumber) {
		this.cardExtendedSearialNumber = cardExtendedSearialNumber;
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

	public byte[] getEmbedderIcAssemberID() {
		return embedderIcAssemberID;
	}

	public void setEmbedderIcAssemberID(byte[] embedderIcAssemberID) {
		this.embedderIcAssemberID = embedderIcAssemberID;
	}

	public byte[] getIcIdentfier() {
		return icIdentfier;
	}

	public void setIcIdentfier(byte[] icIdentfier) {
		this.icIdentfier = icIdentfier;
	}

	public void setCardApprovalNumber(String cardApprovalNumber) {
		this.cardApprovalNumber = cardApprovalNumber;
	}

	@Override
	public Element generateXMLElement(String name) {
		Element node = new Element(name);
		node.addContent( new Element("clockStop").setText( Byte.toString(clockStop) ));
		node.addContent( cardExtendedSearialNumber.generateXMLElement("cardExtendedSerialNumber"));
		node.addContent( new Element("cardApprovalNumber").setText(cardApprovalNumber));
		node.addContent( new Element("cardPersonaliserID").setText( convertIntoHexString(cardPersonaliserID)));
		node.addContent( new Element("embedderIcAssemberID").setText( convertIntoHexString(embedderIcAssemberID)));
		node.addContent( new Element("icIdentfier").setText( convertIntoHexString(icIdentfier)));
		return node;
	}
	
}
