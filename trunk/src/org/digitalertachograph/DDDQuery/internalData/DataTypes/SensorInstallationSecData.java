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

public class SensorInstallationSecData extends DataClass {
	/*
	 * Information, stored in a workshop card, related to the security data needed for
	 * pairing motion sensors to vehicle units (requirement 214).
	 * 
	 * SensorInstallationSecData ::= TDesSessionKey
	 * 
	 * Value assignment: in accordance with ISO 16844-3.
	 * ----
	 * TDesSessionKey ::= SEQUENCE {
	 * 	tDesKeyA OCTET STRING (SIZE(8)), 8 bytes
	 * 	tDesKeyB OCTET STRING (SIZE(8)), 8 bytes
	 * }
	 */

	private byte[] tDesKeyA;
	private byte[] tDesKeyB;
	

	/**
	 * Constructor for a SensorInstallationSecData object
	 */
	public SensorInstallationSecData(){
		tDesKeyA = new byte[8];
		tDesKeyB = new byte[8];
	}

	/**
	 * Constructor for a SensorInstallationSecData object
	 * 
	 * @param	value	byte array of a SensorInstallationSecData structure
	 * 					whose data is used when the SensorInstallationSecData
	 * 					object is created.
	 */
	public SensorInstallationSecData(byte[] value){
		tDesKeyA = arrayCopy(value, 0, 8);
		tDesKeyB = arrayCopy(value, 8, 8);
	}

	/**
	 * Returns the TDesKeyA of a SensorInstallationSecData object.
	 * 
	 * @return	the TDesKeyA of the SensorInstallationSecData object
	 */
	public byte[] getTDesKeyA() {
		return tDesKeyA;
	}

	/**
	 * Sets the TDesKeyA of a SensorInstallationSecData object.
	 * 
	 * @param	tDesKeyA	the TDesKeyA to be set for the SensorInstallationSecData object
	 */
	public void setTDesKeyA(byte[] tDesKeyA) {
		this.tDesKeyA = tDesKeyA;
	}

	/**
	 * Returns the TDesKeyB of a SensorInstallationSecData object.
	 * 
	 * @return	the TDesKeyB of the SensorInstallationSecData object
	 */
	public byte[] getTDesKeyB() {
		return tDesKeyB;
	}

	/**
	 * Sets the TDesKeyB of a SensorInstallationSecData object.
	 * 
	 * @param	tDesKeyB	the TDesKeyB to be set for the SensorInstallationSecData object
	 */
	public void setTDesKeyB(byte[] tDesKeyB) {
		this.tDesKeyB = tDesKeyB;
	}
	
	@Override
	public Element generateXMLElement(String name) {
		Element node = new Element(name);
		node.addContent( new Element("tDesKeyA").setText( convertIntoHexString( tDesKeyA )));
		node.addContent( new Element("tDesKeyB").setText( convertIntoHexString( tDesKeyB )));
		return node;
	}
}
