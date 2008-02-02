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
 * Number of bytes in a driver or a workshop card, available to store driver activity records.
 */
public class CardActivityLengthRange extends DataClass {
	/* 
	 * CardActivityLengthRange ::= INTEGER(0..2^16-1)
	 *
	 * driver card:
	 * min.:  5544 bytes (28 days * 93 activity changes)
	 * max.: 13776 bytes (28 days * 240 activity changes)
	 *
	 * workshop card:
	 * min.:   198 bytes (1 day * 93 activity changes)
	 * max.:   492 bytes (1 day * 240 activity changes)
	 */

	private int cardActivityLengthRange;

	
	/**
	 * Constructor for a CardActivityLengthRange object
	 */
	public CardActivityLengthRange() {
		cardActivityLengthRange = 0;
	}

	/**
	 * Constructor for a CardActivityLengthRange object
	 * 
	 * @param	value	byte array of a CardActivityLengthRange structure
	 * 					whose data is used when the CardActivityLengthRange
	 * 					object is created.
	 */
	public CardActivityLengthRange( byte[] value ) {
		this.cardActivityLengthRange = convertIntoUnsigned2ByteInt( value );
	}

	/**
	 * Returns the number of bytes in a driver or a workshop card, available
	 * to store driver activity records of a CardActivityLengthRange object.
	 * 
	 * @return	the number of bytes in a driver or a workshop card, available to store driver activity records
	 * 			of the CardActivityLengthRange object
	 */
	public int getCardActivityLengthRange() {
		return cardActivityLengthRange;
	}

	/**
	 * Sets the number of bytes in a driver or a workshop card, available to store driver activity records
	 * of a CardActivityLengthRange object.
	 * 
	 * @param	cardActivityLengthRange		the number of bytes in a driver or a workshop card, available
	 * 										to store driver activity records to be set for the
	 * 										CardActivityLengthRange object
	 */
	public void setCardActivityLengthRange( int cardActivityLengthRange ) {
		this.cardActivityLengthRange = cardActivityLengthRange;
	}

	@Override
	public Element generateXMLElement( String name ) {
		return new Element( name ).setText( Integer.toString( cardActivityLengthRange ) );
	}
}
