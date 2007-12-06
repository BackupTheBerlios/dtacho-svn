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

public class CalibrationPurpose extends DataClass {
	/*
	 * CalibrationPurpose ::= OCTET STRING (SIZE(1)).
	 * 
	 * Value assignment:
	 * �00�H reserved value,
	 * �01�H activation: recording of calibration parameters known, at the moment of the VU activation,	
	 * �02�H first installation: first calibration of the VU after its activation,
	 * �03�H installation: first calibration of the VU in the current vehicle,
	 * �04�H periodic inspection.
	 * TODO konstanten?
	 */
	private byte calibrationPurpose;
	
	public CalibrationPurpose(byte value){
		calibrationPurpose = value;
	}
	
	@Override
	public Element generateXMLElement(String name) {
		Element node = new Element(name);
		node.addContent(new Element("calibrationPurpose").setText(Byte.toString(calibrationPurpose) ));
		return node;
	}
}
