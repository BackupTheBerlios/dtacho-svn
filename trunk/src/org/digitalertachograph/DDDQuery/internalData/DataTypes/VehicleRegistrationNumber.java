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
import org.digitalertachograph.DDDQuery.Controller;

public class VehicleRegistrationNumber extends DataClass {
	/*
	 * VehicleRegistrationNumber ::= SEQUENCE {
	 * 	codePage INTEGER (0..255),
	 * 	vehicleRegNumber OCTET STRING (SIZE(13))
	 * }
	 */
	
	private short codePage;
	private byte[] vehicleRegNumber;
	
	public VehicleRegistrationNumber(){
		
	}
	
	public VehicleRegistrationNumber(byte[] value){
		this(value[0], arrayCopy(value, 1, value.length - 1));
	}
	
	public VehicleRegistrationNumber(byte codePage, byte[] vehicleRegNumber){
		this.codePage = convertIntoUnsigned1ByteInt(codePage);
		this.vehicleRegNumber = vehicleRegNumber;
	}
	
//	public String toString(){
		// TODO write me
//		return "";
//	}

	public short getCodePage() {
		return codePage;
	}

	public void setCodePage(short codePage) {
		this.codePage = codePage;
	}

	public void setCodePage(byte codePage) {
		this.codePage = (short) (codePage & 0xFF);
	}

	public byte[] getVehicleRegNumber() {
		return vehicleRegNumber;
	}

	public void setVehicleRegNumber(byte[] vehicleRegNumber) {
		this.vehicleRegNumber = vehicleRegNumber;
	}

	@Override
	public Element generateXMLElement(String name) {
		Controller c = Controller.getInstance();
		

		Element node = new Element(name);
		node.addContent(new Element("codePage").setText(Short.toString(codePage)));
		if(c.isAnonymized()){
			byte [] tmp = new byte[13];
			for(int i = 0; i < 12; i++)
				tmp[i] = 0x41;
			
			node.addContent(new Element("vehicleRegNumber").setText( convertIntoHexString( tmp )));
		}else{
			node.addContent(new Element("vehicleRegNumber").setText( convertIntoHexString(vehicleRegNumber)));
		}
		return node;
	}	

}
