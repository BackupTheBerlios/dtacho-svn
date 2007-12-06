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

public class EntryTypeDailyWorkPeriod extends DataClass {
	/*
	 * EntryTypeDailyWorkPeriod ::= INTEGER
	 * Begin, related time = card insertion time or time of entry (0),
	 * End, related time = card withdrawal time or time of entry (1),
	 * Begin, related time manually entered (start time) (2),
	 * End, related time manually entered (end of work period) (3),
	 * Begin, related time assumed by VU (4),
	 * End, related time assumed by VU (5)
	 */
	
	private byte entryTypeDailyWorkPeriod;
	
	public EntryTypeDailyWorkPeriod(byte value){
		entryTypeDailyWorkPeriod = value;
	}

	public byte getEntryTypeDailyWorkPeriod() {
		return entryTypeDailyWorkPeriod;
	}

	public void setEntryTypeDailyWorkPeriod(byte entryTypeDailyWorkPeriod) {
		this.entryTypeDailyWorkPeriod = entryTypeDailyWorkPeriod;
	}
	
	@Override
	public Element generateXMLElement(String name) {
		Element node = new Element(name);
		node.addContent( new Element("entryTypeDailyWorkPeriod").setText( Byte.toString( entryTypeDailyWorkPeriod)));
		return node;
	}

	
}
