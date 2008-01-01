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
	 *  serialNumber INTEGER (0..2^32-1), 4 bytes
	 *  monthYear BCDString (SIZE(2)), 2 bytes
	 *  type OCTET STRING (SIZE(1)), 1 byte
	 *  manufacturerCode ManufacturerCode, 1 byte
	 * }
	 */

	private long serialNumber; // 4 bytes int unsigned
	private byte[] monthYear = new byte[2];
	private byte type;
	private short manufacturerCode;

	
	/**
	 * Constructor for an ExtendedSerialNumber object
	 */
	public ExtendedSerialNumber() {

	}

	/**
	 * Constructor for an ExtendedSerialNumber object
	 * 
	 * @param	cardExtendedSerialNumber	byte array of an ExtendedSerialNumber structure
	 * 										whose data is used when the ExtendedSerialNumber
	 * 										object is created.
	 */
	public ExtendedSerialNumber(byte[] cardExtendedSerialNumber ) {
		serialNumber = convertIntoUnsigned4ByteInt( arrayCopy(cardExtendedSerialNumber, 0 , 4));
		monthYear = arrayCopy(cardExtendedSerialNumber, 4, 2);
		type = cardExtendedSerialNumber[6]; 
		manufacturerCode = convertIntoUnsigned1ByteInt(cardExtendedSerialNumber[7]); 
	}

	/**
	 * Returns the serial number for the equipment, unique for the manufacturer,
	 * the equipment's type and the month below of an ExtendedSerialNumber object.
	 * 
	 * @return	the serial number for the equipment, unique for the manufacturer,
	 * 			the equipment's type and the month below of the ExtendedSerialNumber object
	 */
	public long getSerialNumber() {
		return serialNumber;
	}

	/**
	 * Sets the serial number for the equipment, unique for the manufacturer,
	 * the equipment's type and the month below of an ExtendedSerialNumber object.
	 * 
	 * @param	serialNumber	the serial number for the equipment, unique for the manufacturer,
	 * 							the equipment's type and the month below to be set for the
	 * 							ExtendedSerialNumber object
	 */
	public void setSerialNumber(long serialNumber) {
		this.serialNumber = serialNumber;
	}

	/**
	 * Returns the identification of the month and the year of manufacturing (or of serial
	 * assignment) of an ExtendedSerialNumber object.
	 * 
	 * @return	the identification of the month and the year of manufacturing (or of serial
	 * 			assignment) of the ExtendedSerialNumber object
	 */
	public byte[] getMonthYear() {
		return monthYear;
	}

	/**
	 * Sets the identification of the month and the year of manufacturing (or of serial
	 * assignment) of an ExtendedSerialNumber object.
	 * 
	 * @param	monthYear	the identification of the month and the year of manufacturing (or of serial
	 * 						assignment) to be set for the ExtendedSerialNumber object
	 */
	public void setMonthYear(byte[] monthYear) {
		this.monthYear = monthYear;
	}

	/**
	 * Returns the identifier of the type of equipment of an ExtendedSerialNumber object.
	 * 
	 * @return	the identifier of the type of equipment of the ExtendedSerialNumber object
	 */
	public byte getType() {
		return type;
	}

	/**
	 * Sets the identifier of the type of equipment of an ExtendedSerialNumber object.
	 * 
	 * @param	type	the identifier of the type of equipment to be set for the ExtendedSerialNumber object.
	 */
	public void setType(byte type) {
		this.type = type;
	}

	/**
	 * Returns the numerical code of the manufacturer of the equipment of an ExtendedSerialNumber object.
	 * 
	 * @return	the numerical code of the manufacturer of the equipment of the ExtendedSerialNumber object
	 */
	public short getManufacturerCode() {
		return manufacturerCode;
	}

	/**
	 * Sets the numerical code of the manufacturer of the equipment of an ExtendedSerialNumber object.
	 * 
	 * @param	manufacturerCode	the numerical code of the manufacturer of the equipment to be set
	 * 								for the ExtendedSerialNumber object.
	 */
	public void setManufacturerCode(short manufacturerCode) {
		this.manufacturerCode = manufacturerCode;
	}

	@Override
	public Element generateXMLElement(String name) {
		Element node = new Element(name);
		node.addContent(new Element("serialNumber").setText(Long.toString(serialNumber)));
		node.addContent(new Element("monthYear").setText(convertBCDStringIntoString(monthYear)));
		node.addContent(new Element("manufacturerCode").setText(Short.toString(manufacturerCode)));
		return node;
	}
}
