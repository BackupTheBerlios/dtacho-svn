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
import org.jdom.Attribute;
import org.jdom.Element;

public class ControlType extends DataClass {
	/*
	 * ControlType ::= OCTET STRING (SIZE(1))
	 * 
	 * Value assignment  Octet aligned: �cvpdxxxx�B (8 bits)
	 * "c"B card downloading:
	 * 		"0"B: card not downloaded during this control activity,
	 * 		"1"B: card downloaded during this control activity
	 * "v"B VU downloading:
	 * 		"0"B: VU not downloaded during this control activity,
	 * 		"1"B: VU downloaded during this control activity
	 * "p"B printing:
	 * 		"0"B: no printing done during this control activity,
	 * 		"1"B: printing done during this control activity
	 * "d"B display:
	 * 		"0"B: no display used during this control activity,
	 * 		"1"B: display used during this control activity
	 * "xxxx"B Not used.
	 * 
	 * 10000000 = 0x80
	 * 01000000 = 0x40
	 * 00100000 = 0x20
	 * 00010000 = 0x10
	 */

	private byte controlType;
	
	private boolean card_downloading;
	private boolean vu_downloading;
	private boolean printing;
	private boolean display;
		
	public ControlType(byte value){
		this.setControlType(value);
	}

	public byte getControlType() {
		return controlType;
	}

	public void setControlType(byte controlType) {
		this.controlType = controlType;
		
		card_downloading = ((controlType & 0x80) == 0x80);
		vu_downloading = ((controlType & 0x40) == 0x40);
		printing = ((controlType & 0x20) == 0x20);
		display = ((controlType & 0x10) == 0x10);
	}

	public boolean isCard_downloading() {
		return card_downloading;
	}

	public boolean isDisplay() {
		return display;
	}

	public boolean isPrinting() {
		return printing;
	}

	public boolean isVu_downloading() {
		return vu_downloading;
	}
	
	@Override
	public Element generateXMLElement(String name) {
		Element node = new Element(name);
		node.setAttribute("card_downloading", Boolean.toString(card_downloading));
		node.setAttribute("display", Boolean.toString(display));
		node.setAttribute("printing", Boolean.toString(printing));
		node.setAttribute("vu_downloading", Boolean.toString(vu_downloading));
		return node;
	}
	
}
