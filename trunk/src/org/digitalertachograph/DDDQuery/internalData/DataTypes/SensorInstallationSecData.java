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

public class SensorInstallationSecData extends DataClass {
	/*
	 * Information, stored in a workshop card, related to the security data needed for
	 * pairing motion sensors to vehicle units (requirement 214).
	 * SensorInstallationSecData ::= TDesSessionKey
	 * Value assignment: in accordance with ISO 16844-3.
	 * ----
	 * TDesSessionKey ::= SEQUENCE {
	 * 	tDesKeyA OCTET STRING (SIZE(8))	
	 * 	tDesKeyB OCTET STRING (SIZE(8))
	 * }
	 */

	private byte[] tDesKeyA;
	private byte[] tDesKeyB;
	
	public SensorInstallationSecData(byte[] value){
		tDesKeyA = arrayCopy(value, 0, 8);
		tDesKeyB = arrayCopy(value, 8, 8);
	}

	public byte[] getTDesKeyA() {
		return tDesKeyA;
	}

	public void setTDesKeyA(byte[] desKeyA) {
		tDesKeyA = desKeyA;
	}

	public byte[] getTDesKeyB() {
		return tDesKeyB;
	}

	public void setTDesKeyB(byte[] desKeyB) {
		tDesKeyB = desKeyB;
	}
	
	@Override
	public Element generateXMLElement(String name) {
		Element node = new Element(name);
		node.addContent( new Element("tDesKeyA").setText( convertIntoHexString( tDesKeyA )));
		node.addContent( new Element("tDesKeyB").setText( convertIntoHexString( tDesKeyB )));
		return node;
	}
	
}
