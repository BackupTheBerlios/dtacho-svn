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
import org.digitalertachograph.DDDQuery.Controller;
import org.jdom.Element;

public class Name extends DataClass{
	/*
	 * Name ::= SEQUENCE {
	 * 	codePage INTEGER (0..255), 1 byte
	 * 	name OCTET STRING (SIZE(35)), 35 bytes
	 * }
	 * ---
	 * codePage specifies the part of the ISO/IEC 8859 used to code the vehicleRegNumber
	 */
	
	private short codePage;
	private byte[] name;
	
	/**
	 * Constructor for a Name object
	 */
	public Name(){

	}
	

	/**
	 * Constructor for a Name object
	 * 
	 * @param	value	byte array of a Name structure
	 * 					whose data is used when the Name
	 * 					object is created.
	 */
	public Name(byte[] value){
		this(value[0], arrayCopy(value, 1, value.length - 1));
	}
	
	public Name(byte codePage, byte[] name){
		this.codePage = convertIntoUnsigned1ByteInt(codePage);
		this.name = name;
	}
	
	public String toString(){
		// TODO write me!!
		return "";
	}

	public short getCodePage() {
		return codePage;
	}

	public void setCodePage(short codePage) {
		this.codePage = codePage;
	}

	public void setCodePage(byte codePage) {
		this.codePage = (short) (codePage & 0xff);
	}
	
	public byte[] getName() {
		return name;
	}

	public void setName(byte[] name) {
		this.name = name;
	}
	
	@Override
	public Element generateXMLElement(String name) {
		Controller c = Controller.getInstance();
		
		Element node = new Element(name);
		node.addContent( new Element("codePage").setText( Short.toString(codePage)));
		Element nameElement;
		if(c.isAnonymized()){
			byte [] tmp = new byte[35];
			for(int i = 0; i < 35; i++)
				tmp[i] = 0x41; // 'A'
			nameElement = new Element("name").setText( convertIntoHexString( tmp ));
		}else{
			nameElement = new Element("name").setText( convertIntoHexString( this.name ));
		}
		node.addContent( nameElement );
		return node;
	}
}
