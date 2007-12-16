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

package org.digitalertachograph.DDDQuery.internalData;

import org.digitalertachograph.DDDQuery.internalData.DataTypes.*;
import org.jdom.Element;


/**
 * EF_CA_Certificate,
 * 
 * Council Regulation (EEC) No 3821/85 File ID: C108
 * 
 */
public class EF_CA_Certificate extends DataClass {
	public static final int size = 194;
	private MemberStateCertificate cardCertificate;
	

	/**
	 * Constructor for an EF_CA_Certificate object
	 * 
	 * @param	value	byte array of an EF_CA_Certificate structure
	 * 					whose data is used when the EF_CA_Certificate
	 * 					object is created.
	 */
	public EF_CA_Certificate(byte [] value){
		cardCertificate = new MemberStateCertificate();
		cardCertificate.setCertificate(value);
	}
	
	public Element generateXMLElement(String name){
		// discard name - this.getClass().getSimpleName() is unique!
		Element node = new Element(this.getClass().getSimpleName());
		Element child = cardCertificate.generateXMLElement("workshopCardCalibrationData");
		node.addContent(child);
		return node;
	}
}
