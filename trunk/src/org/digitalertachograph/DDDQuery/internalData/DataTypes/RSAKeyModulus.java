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
 * The modulus of a RSA key pair.
 */
public class RSAKeyModulus extends DataClass {
	/*
	 * RSAKeyModulus ::= OCTET STRING(SIZE(128)), 128 bytes
	 */

	private byte[] rsaKeyModulus;

	/**
	 * Constructor for a RSAKeyModulus object
	 */
	public RSAKeyModulus() {
		rsaKeyModulus = new byte[ 128 ];
	}

	/**
	 * Constructor for a RSAKeyModulus object
	 * 
	 * @param	value	byte array of a RSAKeyModulus that is used
	 * 					when the RSAKeyModulus object is created.
	 */
	public RSAKeyModulus( byte[] value ) {
		setRSAKeyModulus( value );
	}

	/**
	 * Returns the RSA key modulus of a RSAKeyModulus object.
	 * 
	 * @return	the RSA key modulus of the RSAKeyModulus object.
	 */
	public byte[] getRSAKeyModulus() {
		return rsaKeyModulus;
	}

	/**
	 * Sets the RSA key modulus of a RSAKeyModulus object.
	 * 
	 * @param	rsaKeyModulus	the RSA key modulus to be set for the RSAKeyModulus object
	 */
	public void setRSAKeyModulus( byte[] rsaKeyModulus ) {
		this.rsaKeyModulus = arrayCopy( rsaKeyModulus, 0, 128 );
	}

	@Override
	public Element generateXMLElement( String name ) {
		return new Element( name ).setText( convertIntoHexString( rsaKeyModulus ) );
	}
}
