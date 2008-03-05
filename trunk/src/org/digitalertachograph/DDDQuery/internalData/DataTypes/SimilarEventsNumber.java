/*
    $Id$

    Copyright (C) 2008, Gerald Schnabel

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
 * The number of similar events for one given day.
 */
public class SimilarEventsNumber extends DataClass {
	/*
	 * SimilarEventsNumber ::= INTEGER(0..255), 1 byte
	 * Value assignment:
	 * 0 is not used,
	 * 1 means that only one event of that type has occurred and has been stored on that day,
	 * 2 means that 2 events of that type have occurred on that day (only one has been stored)...
	 * 255 means that 255 or more events have occurred on that day.
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 1;

	private short similarEventsNumber;


	/**
	 * Constructor for a SimilarEventsNumber object
	 */
	public SimilarEventsNumber() {
		similarEventsNumber = 0;
	}

	/**
	 * Constructor for a SimilarEventsNumber object
	 * 
	 * @param	b	byte that contains the number of similar events for one given day
	 * 				when the SimilarEventsNumber object is created.
	 */
	public SimilarEventsNumber( byte b ) {
		similarEventsNumber = convertIntoUnsigned1ByteInt( b );
	}

	/**
	 * Returns the number of similar events for one given day
	 * of a SimilarEventsNumber object.
	 * 
	 * @return	the number of similar events for one given day
	 * 			of the SimilarEventsNumber object
	 */
	public short getSimilarEventsNumber() {
		return similarEventsNumber;
	}

	/**
	 * Sets the number of similar events for one given day
	 * of a SimilarEventsNumber object.
	 * 
	 * @param	similarEventsNumber		the number of similar events for one given day
	 * 									to be set for the SimilarEventsNumber object
	 */
	public void setSimilarEventsNumber( short similarEventsNumber ) {
		this.similarEventsNumber = similarEventsNumber;
	}

	@Override
	public Element generateXMLElement( String name ) {
		return new Element( name ).setText( Short.toString( similarEventsNumber ) );
	}
}
