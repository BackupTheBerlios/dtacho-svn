/*
    $Id:PublicKey.java 26 2008-02-25 22:28:27Z deetee $

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
 * A public RSA key.
 */
public class PublicKey extends DataClass {
	/*
	 * PublicKey ::= SEQUENCE {
	 * 	rsaKeyModulus RSAKeyModulus, 128 bytes
	 * 	rsaKeyPublicExponent RSAKeyPublicExponent, 8 bytes
	 * }
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 136;

	private RSAKeyModulus rsaKeyModulus;
	private RSAKeyPublicExponent rsaKeyPublicExponent;


	/**
	 * Constructor for a PublicKey object
	 */
	public PublicKey() {
		rsaKeyModulus = new RSAKeyModulus();
		rsaKeyPublicExponent = new RSAKeyPublicExponent();
	}

	/**
	 * Constructor for a PublicKey object
	 * 
	 * @param	value	byte array of a PublicKey structure that is used
	 * 					when the PublicKey object is created.
	 */
	public PublicKey( byte[] value ) {
		rsaKeyModulus = new RSAKeyModulus( arrayCopy( value, 0, 128 ) );
		rsaKeyPublicExponent = new RSAKeyPublicExponent( arrayCopy( value, 128, 8 ) );
		
	}

	/**
	 * Returns the modulus of the key pair of a PublicKey object.
	 * 
	 * @return	the modulus of the key pair of the PublicKey object.
	 */
	public RSAKeyModulus getRSAKeyModulus() {
		return rsaKeyModulus;
	}

	/**
	 * Sets the modulus of the key pair of a PublicKey object.
	 * 
	 * @param	rsaKeyModulus	the modulus of the key pair to be set
	 * 							for the PublicKey object
	 */
	public void setRSAKeyModulus( RSAKeyModulus rsaKeyModulus ) {
		this.rsaKeyModulus = rsaKeyModulus;
	}

	/**
	 * Returns the public exponent of the key pair of a PublicKey object.
	 * 
	 * @return	the public exponent of the key pair of the PublicKey object.
	 */
	public RSAKeyPublicExponent getRSAKeyPublicExponent() {
		return rsaKeyPublicExponent;
	}

	/**
	 * Sets the public exponent of the key pair of a PublicKey object.
	 * 
	 * @param	rsaKeyPublicExponent	the public exponent of the key pair to be set
	 * 									for the PublicKey object
	 */
	public void setRSAKeyPublicExponent( RSAKeyPublicExponent rsaKeyPublicExponent ) {
		this.rsaKeyPublicExponent = rsaKeyPublicExponent;
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		node.addContent( rsaKeyModulus.generateXMLElement( "rsaKeyModulus" ) );
		node.addContent( rsaKeyPublicExponent.generateXMLElement( "rsaKeyPublicExponent" ) );

		return node;
	}
}
