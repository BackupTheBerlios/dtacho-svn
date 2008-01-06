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

public class Certificate extends DataClass {
	/*
	 * Certificate :== OCTET STRING(SIZE(194)), 194 bytes
	 */
	private byte[] certificate;

	
	/**
	 * Constructor for a Certificate object
	 */
	public Certificate(){
		certificate = new byte[194];
	}

	/**
	 * Constructor for a Certificate object
	 * 
	 * @param	certificate		byte array of a Certificate structure
	 * 							whose data is used when the Certificate
	 * 							object is created.
	 */
	public Certificate(byte[] certificate) {
		this.certificate = certificate;
	}
	
	/**
	 * Returns the certificate of a Certificate object.
	 * 
	 * @return	byte array that contains the certificate of the Certificate object
	 */
	public byte[] getCertificate() {
		return certificate;
	}

	/**
	 * Sets the certificate of a Certificate object.
	 * 
	 * @param	certificate		byte array that contains the certificate
	 *							to be set for the Certificate object
	 */
	public void setCertificate(byte[] certificate) {
		this.certificate = certificate;
	}
	
	@Override
	public Element generateXMLElement(String name) {
		Element node = new Element(name);
		node.addContent(convertIntoHexString(certificate));
		return node;
	}
}
