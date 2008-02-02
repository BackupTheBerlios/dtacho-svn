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

package org.digitalertachograph.DDDQuery.internalData.Security;

import org.digitalertachograph.DDDQuery.internalData.DataTypes.*;

/**
 * Public Key of a certification authority.
 */
public class SecurityCAPublicKey {

	private CertificationAuthorityKID certificationAuthority;
	private RSAKeyModulus publicKeyModulus;
	private RSAKeyPublicExponent publicKeyExponent;

	
	/**
	 * Constructor for a CAPublicKey object
	 * 
	 * @param	caPublicKey		byte array of a CAPublicKey structure
	 * 							whose data is used when the CAPublicKey
	 * 							object is created.
	 */
	public SecurityCAPublicKey( byte[] caPublicKey ) {
		byte[] CertificationAuthorityTemp = new byte[ 8 ];
		System.arraycopy( caPublicKey, 0, CertificationAuthorityTemp, 0, 8 );
		certificationAuthority = new CertificationAuthorityKID( CertificationAuthorityTemp );
		
		byte[] publicKeyModulusTemp = new byte[ 128 ];
		System.arraycopy( caPublicKey, 8, publicKeyModulusTemp, 0, 128 );
		publicKeyModulus = new RSAKeyModulus( publicKeyModulusTemp );

		byte[] publicKeyExponentTemp = new byte[ 8 ];
		System.arraycopy( caPublicKey, 136, publicKeyExponentTemp, 0, 8 );
		publicKeyExponent = new RSAKeyPublicExponent( publicKeyExponentTemp );
	}

	/**
	 * Returns the certification authority key identifier of a CAPublicKey object.
	 * 
	 * @return	the certification authority key identifier of the CAPublicKey object.
	 */
	public CertificationAuthorityKID getCertificationAuthority() {
		return certificationAuthority;
	}
	
	/**
	 * Returns the RSA public key modulus of a CAPublicKey object as byte array
	 * 
	 * @return	the RSA public key modulus of the CAPublicKey object as byte array
	 */
	public byte[] getPublicKeyModulusByteArray() {
		return publicKeyModulus.getRSAKeyModulus();
	}

	/**
	 * Returns the RSA public key modulus of a CAPublicKey object as hex string
	 * 
	 * @return	the RSA public key modulus of the CAPublicKey object as hex string
	 */
	public String getPublicKeyModulusHexString() {
		return new SecurityByteArrayTools().ByteArrayToHexString( publicKeyModulus.getRSAKeyModulus() );
	}

	/**
	 * Returns the RSA public key exponent of a CAPublicKey object as byte array
	 * 
	 * @return	the RSA public key exponent of the CAPublicKey object as byte array
	 */
	public byte[] getPublicKeyExponentByteArray() {
		return publicKeyExponent.getRSAKeyPublicExponent();
	}

	/**
	 * Returns the RSA public key exponent of a CAPublicKey object as hex string
	 * 
	 * @return	the RSA public key exponent of the CAPublicKey object as hex string
	 */
	public String getPublicKeyExponentHexString() {
		return new SecurityByteArrayTools().ByteArrayToHexString( publicKeyExponent.getRSAKeyPublicExponent() );
	}
}
