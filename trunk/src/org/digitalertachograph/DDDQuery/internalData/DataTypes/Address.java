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

public class Address extends DataClass {
	/*
	 * address ::= SEQUENCE {
	 * 	codePage INTEGER (0..255),
	 * 	address OCTET STRING (SIZE(35))
	 * }
	 */
	
	private short codePage;
	private byte[] address;
	
	//public Address(){
		
	//}


	/**
	 * Constructor for an Address object
	 * 
	 * @param	value	byte array of an Address structure whose data
	 * 					is used when the Address object is created.
	 */
	public Address(byte[] value) {
		this(value[0], arrayCopy(value, 1, 35));
	}
	
	/**
	 * Constructor for an Address object
	 * 
	 * @param	codePage	byte that specifies the part of the
	 * 						ISO/IEC 8859 used to code the address
	 * 
	 * @param	address		byte array that contains an address
	 */
	public Address(byte codePage, byte[] address) {
		this.codePage = codePage;
		this.address = address;
	}

	/**
	 * TODO
	 * 
	 * @return	empty string
	 */
	public String toString(){
		// TODO write me!!
		return "";
	}

	/**
	 * Returns the address of an Address object.
	 * 
	 * @return	byte array that contains the address of the Address object
	 */
	public byte[] getAddress() {
		return address;
	}

	/**
	 * Sets the address of an Address object.
	 * 
	 * @param	address	byte array that contains an Address object
	 */
	public void setAddress(byte[] address) {
		this.address = address;
	}

	/**
	 * Returns the codepage of an Address object.
	 * 
	 * @return	codepage of the Address object
	 */
	public short getCodePage() {
		return codePage;
	}

	/**
	 * Sets the codepage of an Address object.
	 * 
	 * @param codePage	codepage of the Address object
	 */
	public void setCodePage(short codePage) {
		this.codePage = codePage;
	}
	
	@Override
	public Element generateXMLElement(String name) {
		Element node = new Element(name);
		node.addContent(new Element("codePage").setText( Integer.toString(codePage) ));
		node.addContent(new Element("address").setText( convertIntoHexString(address) ));
		
		return node;
	}
}
