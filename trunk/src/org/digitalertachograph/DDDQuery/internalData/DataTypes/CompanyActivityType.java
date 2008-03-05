/*
    $Id$

    Copyright (C) 2007-2008, Martin Barth, Gerald Schnabel

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
 * Code indicating an activity carried out by a company using its company card.
 */
public class CompanyActivityType extends DataClass {
	/*
	 * CompanyActivityType ::= INTEGER {
	 * 	card downloading (1),
	 * 	VU downloading (2),
	 * 	VU lock-in (3),
	 * 	VU lock-out (4).
	 * }, 1 byte
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 1;

	private short companyActivityType;

	/**
	 * Company activity type: CARD_DOWNLOADING
	 */
	public static final short CARD_DOWNLOADING = 1;
	/**
	 * Company activity type: VU_DOWNLOADING
	 */
	public static final short VU_DOWNLOADING = 2;
	/**
	 * Company activity type: VU_LOCK_IN
	 */
	public static final short VU_LOCK_IN = 3;
	/**
	 * Company activity type: VU_LOCK_OUT
	 */
	public static final short VU_LOCK_OUT = 4;


	/**
	 * Constructor for a CompanyActivityType object
	 */
	public CompanyActivityType() {
		companyActivityType = 0;
	}

	/**
	 * Constructor for a CompanyActivityType object
	 * 
	 * @param	value	byte that indicates an activity carried out by a
	 * 					company using its company card.
	 */
	public CompanyActivityType( byte value ){
		companyActivityType = convertIntoUnsigned1ByteInt( value );
	}

	/**
	 * Returns the activity carried out by a company using its company card
	 * of a CompanyActivityType object.
	 * 
	 * @return	the activity carried out by a company using its company card
	 * 			of the CompanyActivityType object
	 */
	public short getCompanyActivityType() {
		return companyActivityType;
	}

	/**
	 * Sets the activity carried out by a company using its company card
	 * of a CompanyActivityType object.
	 * 
	 * @param	companyActivityType		the activity carried out by a company using its company card
	 * 									to be set for the CompanyActivityType object
	 */
	public void setCompanyActivityType( short companyActivityType ) {
		this.companyActivityType = companyActivityType;
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		node.addContent( new Element( "companyActivityType" ).setText( Short.toString( companyActivityType ) ) );

		return node;
	}
}
