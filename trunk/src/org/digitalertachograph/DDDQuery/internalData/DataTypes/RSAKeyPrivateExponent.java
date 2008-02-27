/*
    $Id:RSAKeyPrivateExponent.java 26 2008-02-25 22:28:27Z deetee $

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
 * The private exponent of a RSA key pair.
 */
public class RSAKeyPrivateExponent extends DataClass {
	/*
	 * RSAKeyPrivateExponent ::= OCTET STRING(SIZE(128)), 128 bytes
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 128;

	private byte[] rsaKeyPrivateExponent;

	/**
	 * Constructor for a RSAKeyPrivateExponent object
	 */
	public RSAKeyPrivateExponent() {
		rsaKeyPrivateExponent = new byte[ 128 ];
	}

	/**
	 * Constructor for a RSAKeyPrivateExponent object
	 * 
	 * @param	value	byte array of a RSAKeyPrivateExponent that is used
	 * 					when the RSAKeyPrivateExponent object is created.
	 */
	public RSAKeyPrivateExponent( byte[] value ) {
		setRSAKeyPrivateExponent( value );
	}

	/**
	 * Returns the RSA key private exponent of a RSAKeyPrivateExponent object.
	 * 
	 * @return	the RSA key private exponent of the RSAKeyPrivateExponent object.
	 */
	public byte[] getRSAKeyPrivateExponent() {
		return rsaKeyPrivateExponent;
	}

	/**
	 * Sets the RSA key private exponent of a RSAKeyPrivateExponent object.
	 * 
	 * @param	rsaKeyPrivateExponent	the RSA key private exponent to be set
	 * 									for the RSAKeyPrivateExponent object
	 */
	public void setRSAKeyPrivateExponent( byte[] rsaKeyPrivateExponent ) {
		this.rsaKeyPrivateExponent = arrayCopy( rsaKeyPrivateExponent, 0, 128 );
	}

	@Override
	public Element generateXMLElement( String name ) {
		return new Element( name ).setText( convertIntoHexString( rsaKeyPrivateExponent ) );
	}
}
