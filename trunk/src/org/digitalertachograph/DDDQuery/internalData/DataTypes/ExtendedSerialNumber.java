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

public class ExtendedSerialNumber extends DataClass {
	/*
	 * ExtendedSerialNumber ::= SEQUENCE {
	 *  serialNumber INTEGER (0..2^32-1)
	 *  monthYear BCDString (SIZE(2))
	 *  type OCTET STRING (SIZE(1))
	 *  manufacturerCode ManufacturerCode
	 * }
	 */

	private long serialNumber; // 4 bytes int unsigned
	private String monthYear;
	private byte type;
	private short manufacturerCode;

	
	/**
	 * Constructor for an ExtendedSerialNumber object
	 * 
	 * @param	cardExtendedSerialNumber	byte array of an ExtendedSerialNumber structure
	 * 										whose data is used when the ExtendedSerialNumber
	 * 										object is created.
	 */

	public ExtendedSerialNumber(byte[] cardExtendedSerialNumber ){
		serialNumber = convertIntoUnsigned4ByteInt( arrayCopy(cardExtendedSerialNumber, 0 , 4));
		monthYear = convertIntoBCDString( arrayCopy(cardExtendedSerialNumber, 4, 2));
		type = cardExtendedSerialNumber[6]; 
		manufacturerCode = convertIntoUnsigned1ByteInt(cardExtendedSerialNumber[7]); 
	}

	public short getManufacturerCode() {
		return manufacturerCode;
	}

	public void setManufacturerCode(short manufacturerCode) {
		this.manufacturerCode = manufacturerCode;
	}

	public String getMonthYear() {
		return monthYear;
	}

	public void setMonthYear(String monthYear) {
		this.monthYear = monthYear;
	}

	public long getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(long serialNumber) {
		this.serialNumber = serialNumber;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	@Override
	public Element generateXMLElement(String name) {
		Element node = new Element(name);
		node.addContent(new Element("serialNumber").setText(Long.toString(serialNumber)));
		node.addContent(new Element("monthYear").setText(monthYear));
		node.addContent(new Element("manufacturerCode").setText(Short.toString(manufacturerCode)));
		return node;
	}
}
