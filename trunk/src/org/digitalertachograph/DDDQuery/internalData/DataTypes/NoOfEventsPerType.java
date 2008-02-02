/*   Copyright (C) 2008, Gerald Schnabel

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

/**
 * Number of events per type of event a card can store.
 */
public class NoOfEventsPerType extends DataClass {
	/*
	 * NoOfEventsPerType ::=  INTEGER(0..255)
	 * min.:  6
	 * max.: 12
	 */

	private short noOfEventsPerType;


	/**
	 * Constructor for a NoOfEventsPerType object
	 */
	public NoOfEventsPerType() {
		noOfEventsPerType = 0;
	}

	/**
	 * Constructor for a NoOfEventsPerType object
	 * 
	 * @param	b	byte that contains the number of events per type of event a card can store
	 * 				when the NoOfEventsPerType object is created.
	 */
	public NoOfEventsPerType( byte b ) {
		noOfEventsPerType = convertIntoUnsigned1ByteInt( b );
	}

	/**
	 * Returns the number of events per type of event a card can store
	 * of a NoOfEventsPerType object.
	 * 
	 * @return	the number of events per type of event a card can store
	 * 			of the NoOfEventsPerType object
	 */
	public short getNoOfEventsPerType() {
		return noOfEventsPerType;
	}

	/**
	 * Sets the number of events per type of event a card can store of a NoOfEventsPerType object.
	 * 
	 * @param	noOfEventsPerType		the number of events per type of event a card can store
	 * 									to be set for the NoOfEventsPerType object
	 */
	public void setNoOfEventsPerType( short noOfEventsPerType ) {
		this.noOfEventsPerType = noOfEventsPerType;
	}

	@Override
	public Element generateXMLElement( String name ) {
		return new Element( name ).setText( Short.toString( noOfEventsPerType ) );
	}
}
