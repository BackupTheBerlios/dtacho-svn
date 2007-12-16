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

public class NoOfCalibrationsSinceDownload extends DataClass {
	/* 
	 * NoOfCalibrationsSinceDownload ::= INTEGER(0..2^16-1)
	 */

	private int noOfCalibrationsSinceDownload;

	
	/**
	 * Constructor for a NoOfCalibrationsSinceDownload object
	 * 
	 * @param	value	byte array of a NoOfCalibrationsSinceDownload structure
	 * 					whose data is used when the NoOfCalibrationsSinceDownload
	 * 					object is created.
	 */
	public NoOfCalibrationsSinceDownload(byte[] value) {
		this.noOfCalibrationsSinceDownload = convertIntoUnsigned2ByteInt(value);
	}

	public int getNoOfCalibrationsSinceDownload() {
		return noOfCalibrationsSinceDownload;
	}

	public void setNoOfCalibrationsSinceDownload(int noOfCalibrationsSinceDownload) {
		this.noOfCalibrationsSinceDownload = noOfCalibrationsSinceDownload;
	}
	
	@Override
	public Element generateXMLElement(String name) {
		Element node = new Element(name);
		node.addContent( new Element("noOfCalibrationsSinceDownload").setText(Integer.toString( noOfCalibrationsSinceDownload )));
		return node;
	}
}
