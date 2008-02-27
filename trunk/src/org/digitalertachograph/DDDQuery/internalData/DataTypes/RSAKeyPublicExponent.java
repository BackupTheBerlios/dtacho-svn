/*
    $Id:RSAKeyPublicExponent.java 26 2008-02-25 22:28:27Z deetee $

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
 * The public exponent of a RSA key pair.
 */
public class RSAKeyPublicExponent extends DataClass {
	/*
	 * RSAKeyPublicExponent ::= OCTET STRING(SIZE(8)), 8 bytes
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 8;

	private byte[] rsaKeyPublicExponent;

	/**
	 * Constructor for a RSAKeyPublicExponent object
	 */
	public RSAKeyPublicExponent() {
		rsaKeyPublicExponent = new byte[ 8 ];
	}

	/**
	 * Constructor for a RSAKeyPublicExponent object
	 * 
	 * @param	value	byte array of a RSAKeyPublicExponent that is used
	 * 					when the RSAKeyPublicExponent object is created.
	 */
	public RSAKeyPublicExponent( byte[] value ) {
		setRSAKeyPublicExponent( value );
	}

	/**
	 * Returns the RSA key public exponent of a RSAKeyPublicExponent object.
	 * 
	 * @return	the RSA key public exponent of the RSAKeyPublicExponent object.
	 */
	public byte[] getRSAKeyPublicExponent() {
		return rsaKeyPublicExponent;
	}

	/**
	 * Sets the RSA key public exponent of a RSAKeyPublicExponent object.
	 * 
	 * @param	rsaKeyPublicExponent	the RSA key public exponent to be set
	 * 									for the RSAKeyPublicExponent object
	 */
	public void setRSAKeyPublicExponent( byte[] rsaKeyPublicExponent ) {
		this.rsaKeyPublicExponent = arrayCopy( rsaKeyPublicExponent, 0, 8 );
	}

	@Override
	public Element generateXMLElement( String name ) {
		return new Element( name ).setText( convertIntoHexString( rsaKeyPublicExponent ) );
	}
}
