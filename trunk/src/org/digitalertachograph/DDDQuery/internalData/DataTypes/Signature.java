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
 * A digital signature.
 */
public class Signature extends DataClass {
	/*
	 * Signature ::= OCTET STRING(SIZE(128)), 128 bytes
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 128;

	private byte[] signature;

	/**
	 * Constructor for a Signature object
	 */
	public Signature() {
		signature = new byte[ 128 ];
	}

	/**
	 * Constructor for a Signature object
	 * 
	 * @param	value	byte array of a Signature that is used
	 * 					when the Signature object is created.
	 */
	public Signature( byte[] value ) {
		setSignature( value );
	}

	/**
	 * Returns the signature of a Signature object.
	 * 
	 * @return	the signature of the Signature object.
	 */
	public byte[] getSignature() {
		return signature;
	}

	/**
	 * Sets the signature of a Signature object.
	 * 
	 * @param	signature	the signature to be set for the Signature object
	 */
	public void setSignature( byte[] signature ) {
		this.signature = arrayCopy( signature, 0, 128 );
	}

	@Override
	public Element generateXMLElement( String name ) {
		return new Element( name ).setText( convertIntoHexString( signature ) );
	}
}
