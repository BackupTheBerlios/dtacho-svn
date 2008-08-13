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

package org.opendtacho.DDDQuery.internalData.Security;

import org.opendtacho.DDDQuery.internalData.DataTypes.*;

import java.math.BigInteger;
import java.security.*;
import java.security.spec.*;

import javax.crypto.*;

/**
 * Deciphers the signature of a certificate and checks if
 * the certificate is valid.
 */
public class SecurityCertificateCheck {

	private RSAKeyModulus rsaPublicKeyModulusByteArray;
	private RSAKeyPublicExponent rsaPublicKeyExponentByteArray;
	private String rsaPublicKeyModulusHexString;
	private String rsaPublicKeyExponentHexString;
	private java.security.PublicKey RSAPublicKey;

	private org.opendtacho.DDDQuery.internalData.DataTypes.Certificate cipheredCertificate;
	private org.opendtacho.DDDQuery.internalData.DataTypes.Signature decipheredSignature;

	private byte[] c_r;
	private byte[] c_n;
	private byte[] c_0;
	private byte[] car;
	private byte[] h_0;

	private SecurityCertificateContent certificateContent;

	/**
	 * Constructor for a CertificateCheck object
	 */
	public SecurityCertificateCheck() {
		rsaPublicKeyModulusByteArray = new RSAKeyModulus();
		rsaPublicKeyExponentByteArray = new RSAKeyPublicExponent();
		rsaPublicKeyModulusHexString = new String();
		rsaPublicKeyExponentHexString = new String();
		RSAPublicKey = null;

		cipheredCertificate = new org.opendtacho.DDDQuery.internalData.DataTypes.Certificate();
		decipheredSignature = new org.opendtacho.DDDQuery.internalData.DataTypes.Signature();

		c_r = new byte[ 106 ];
		c_n = new byte[ 58 ];
		c_0 = new byte[ 106 + 58 ];
		car = new byte[ 8 ];
		h_0 = new byte[ 20 ];

		certificateContent = null;
	}
	
	/**
	 * Returns the RSA public key modulus of a CertificateCheck object as byte array
	 * 
	 * @return	the RSA public key modulus of the CertificateCheck object as byte array
	 */
	public byte[] getRSAPublicKeyModulusByteArray() {
		return rsaPublicKeyModulusByteArray.getRSAKeyModulus();
	}

	/**
	 * Returns the RSA public key modulus of a CertificateCheck object as hex string
	 * 
	 * @return	the RSA public key modulus of the CertificateCheck object as hex string
	 */
	public String getRSAPublicKeyModulusHexString() {
		return rsaPublicKeyModulusHexString;
	}

	/**
	 * Returns the RSA public key exponent of a CertificateCheck object as byte array
	 * 
	 * @return	the RSA public key exponent of the CertificateCheck object as byte array
	 */
	public byte[] getRSAPublicKeyExponentByteArray() {
		return rsaPublicKeyExponentByteArray.getRSAKeyPublicExponent();
	}

	/**
	 * Returns the RSA public key exponent of a CertificateCheck object as hex string
	 * 
	 * @return	the RSA public key exponent of the CertificateCheck object as hex string
	 */
	public String getRSAPublicKeyExponentHexString() {
		return rsaPublicKeyExponentHexString;
	}

	/**
	 * Sets the RSA public key of a CertificateCheck object which is required for
	 * {@link #decipherCertificateSignature(byte[])} by supplying the key modulus and the key
	 * exponent as hex string 
	 * 
	 * @param	rsaPublicKeyModulusHexString	the RSA public key modulus as hex string 
	 * @param	rsaPublicKeyExponentHexString	the RSA public key exponent as hex string
	 *
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public void setRSAPublicKey( String rsaPublicKeyModulusHexString, String rsaPublicKeyExponentHexString ) throws NoSuchAlgorithmException, InvalidKeySpecException {
		this.rsaPublicKeyModulusHexString = rsaPublicKeyModulusHexString;
		this.rsaPublicKeyExponentHexString = rsaPublicKeyExponentHexString;
		rsaPublicKeyModulusByteArray = new RSAKeyModulus( new SecurityByteArrayTools().HexStringToByteArray( rsaPublicKeyModulusHexString ) );
		rsaPublicKeyExponentByteArray = new RSAKeyPublicExponent( new SecurityByteArrayTools().HexStringToByteArray( rsaPublicKeyExponentHexString ) );

		RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec( new BigInteger( rsaPublicKeyModulusHexString, 16 ), new BigInteger( rsaPublicKeyExponentHexString, 16 ) );

		KeyFactory keyFactory = KeyFactory.getInstance( "RSA" );
		RSAPublicKey = keyFactory.generatePublic( pubKeySpec );
	}

	/**
	 * Sets the RSA public key of a CertificateCheck object which is required for
	 * {@link #decipherCertificateSignature(byte[])} by supplying the key modulus and the key
	 * exponent as byte array 
	 * 
	 * @param	rsaPublicKeyModulusByteArray	the RSA public key modulus as byte array 
	 * @param	rsaPublicKeyExponentByteArray	the RSA public key exponent as byte array
	 *
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public void setRSAPublicKey( byte[] rsaPublicKeyModulusByteArray, byte[] rsaPublicKeyExponentByteArray ) throws NoSuchAlgorithmException, InvalidKeySpecException {
		setRSAPublicKey( new SecurityByteArrayTools().ByteArrayToHexString( rsaPublicKeyModulusByteArray ), new SecurityByteArrayTools().ByteArrayToHexString( rsaPublicKeyExponentByteArray ) );
	}

	/**
	 * Returns the ciphered certificate set by {@link #setCipheredCertificate(byte[])} or
	 * (indirectly) set by {@link #decipherCertificateSignature(byte[])} of a CertificateCheck object.
	 * 
	 * @return	the ciphered certificate of the CertificateCheck object
	 */
	public byte[] getCipheredCertificate() {
		return cipheredCertificate.getCertificate();
	}

	/**
	 * Sets the supplied <code>cipheredCertificate</code> for a CertificateCheck object.
	 * 
	 * @param 	cipheredCertificate		the ciphered certificate to be set for the
	 * 									CertificateCheck object
	 */
	public void setCipheredCertificate( byte[] cipheredCertificate ) {
		byte[] cipheredCertificateTemp = new byte[ 194 ];
		System.arraycopy( cipheredCertificate, 0, cipheredCertificateTemp, 0, 194 );
		this.cipheredCertificate.setCertificate( cipheredCertificateTemp );
	}

	/**
	 * Deciphers the signature of the ciphered certificate of a CertificateCheck object
	 * set by {@link #setCipheredCertificate(byte[])} with the RSA public key set by
	 * {@link #setRSAPublicKey(byte[], byte[])} or by {@link #setRSAPublicKey(String, String)}.
	 */
	public void decipherCertificateSignature() {
		try {
			Cipher mycipher = Cipher.getInstance( "RSA/ECB/NoPadding" );

			mycipher.init( Cipher.DECRYPT_MODE, RSAPublicKey );

			decipheredSignature.setSignature( mycipher.doFinal( cipheredCertificate.getCertificate(), 0, 128 ) );

			// certificate content, signature part
			// c_r
			System.arraycopy( decipheredSignature.getSignature(), 1, c_r, 0, 106 );

			// certificate content, certificate part
			// c_n
			System.arraycopy( cipheredCertificate.getCertificate(), 128, c_n, 0, 58 );

			// certificate content = signature part + certificate part
			// c_0 = c_r || c_n, 164 = 106 + 58 bytes
			System.arraycopy( c_r, 0, c_0, 0, 106 );
			System.arraycopy( c_n, 0, c_0, 106, 58 );

			// certification authority reference
			// car
			System.arraycopy( cipheredCertificate.getCertificate(), 186, car, 0, 8 );

			// sha1 hash
			// h_0
			System.arraycopy( decipheredSignature.getSignature(), 107, h_0, 0, 20 );
		}
		catch ( InvalidKeyException ike ) {
			return;
		}
		catch ( IllegalBlockSizeException ibse ) {
			return;
		}
		catch ( BadPaddingException bpe ) {
			return;
		}
		catch ( NoSuchAlgorithmException nsae ) {
			return;
		}
		catch ( NoSuchPaddingException nspe ) {
			return;
		}
	}

	/**
	 * Sets the supplied ciphered certificate for a CertificateCheck object
	 * and deciphers the signature of the ciphered certificate with the RSA public
	 * key set by {@link #setRSAPublicKey(byte[], byte[])} or by
	 * {@link #setRSAPublicKey(String, String)}.
	 * 
	 * @param 	cipheredCertificate		the ciphered certificate to be set for the
	 * 									CertificateCheck object and whose signature
	 * 									is to be deciphered
	 */
	public void decipherCertificateSignature( byte[] cipheredCertificate ) {
		this.setCipheredCertificate( cipheredCertificate );
		this.decipherCertificateSignature();
	}

	/**
	 * Indicates if the deciphered signature of a certificate of a CertificateCheck object is valid.
	 * 
	 * @return	true if the deciphered signature of a certificate of the CertificateCheck object is valid
	 */
	public boolean isValidSignature() {
		if ( ( decipheredSignature.getSignature()[ 0 ] == (byte)0x6a ) && ( decipheredSignature.getSignature()[ 127 ] == (byte)0xbc ) ) {
			certificateContent = new SecurityCertificateContent( c_0 );
			return true;
		}
		else
			return false;
	}

	/**
	 * Indicates if a certificate after deciphering the signature of a certificate is genuine.
	 * 
	 * @return	true if the certificate after deciphering the signature of the certificate is genuine 
	 */
	public boolean isGenuineCertificate() {
		if ( getC_0SHAHashHexString().compareTo(getH_0HexString()) == 0 ) {
			return true;
		}
		else
			return false;
	}

	/**
	 * Returns the deciphered signature the certificate of a CertificateCheck object as
	 * byte array.
	 * 
	 * @return	the deciphered signature of the certificate of the CertificateCheck object
	 * 			as byte array
	 */
	public byte[] getDecipheredSignatureByteArray() {
		return decipheredSignature.getSignature();
	}

	/**
	 * Returns the deciphered signature the certificate of a CertificateCheck object as
	 * hex string.
	 * 
	 * @return	the deciphered signature of the certificate of the CertificateCheck object
	 * 			as hex string
	 */
	public String getDecipheredSignatureHexString() {
		return new SecurityByteArrayTools().ByteArrayToHexString( decipheredSignature.getSignature() );
	}

	/**
	 * Returns the unwrapped certificate content after deciphering the certificate's signature
	 * with {@link #decipherCertificateSignature()} or {@link #decipherCertificateSignature(byte[])}.
	 * <br>
	 * Note:	this method returns a valid certificate content only if the method {@link #isValidSignature()}
	 * 			is called before and returned true.
	 * 
	 * @return	the unwrapped certificate content
	 */
	public SecurityCertificateContent getCertificateContent() {
		return certificateContent;
	}

	/**
	 * Calculates the SHA1 hash value of the certificate content C' (C_0) of a CertificateCheck object
	 * and returns it as byte array.
	 * 
	 * @return	the calculated SHA1 hash value of the certificate content C' (C_0) of the CertificateCheck object
	 * 			as byte array
	 */
	public byte[] getC_0SHAHashByteArray() {
		try {
			MessageDigest md = MessageDigest.getInstance( "SHA-1" );
			md.update( c_0 );
			return md.digest();
		}
		catch ( NoSuchAlgorithmException nsae ) {
			return null;
		}
	}

	/**
	 * Calculates the SHA1 hash value of the certificate content C' (C_0) of a CertificateCheck object
	 * and returns it as hex string.
	 * 
	 * @return	the calculated SHA1 hash value of the certificate content C' (C_0) of the CertificateCheck object
	 * 			as hex string
	 */
	public String getC_0SHAHashHexString() {
		try {
			MessageDigest md = MessageDigest.getInstance( "SHA-1" );
			md.update( c_0 );
			return new SecurityByteArrayTools().ByteArrayToHexString( md.digest() );
		}
		catch ( NoSuchAlgorithmException nsae ) {
			return null;
		}
	}

	/**
	 * Returns the certificate content signature part C'r (C_R) of a CertificateCheck object
	 * as byte array. The signature of the certificate must have been deciphered before
	 * calling this method.
	 * 
	 * @return	the certificate content signature part C'r (C_R) of the CertificateCheck object
	 * 			as byte array
	 */
	public byte[] getC_RByteArray() {
		return c_r;
	}

	/**
	 * Returns the certificate content signature part C'r (C_R) of a CertificateCheck object
	 * as hex string. The signature of the certificate must have been deciphered before
	 * calling this method.
	 * 
	 * @return	the certificate content signature part C'r (C_R) of the CertificateCheck object
	 * 			as hex string.
	 */
	public String getC_RHexString() {
		return new SecurityByteArrayTools().ByteArrayToHexString( c_r );
	}

	/**
	 * Returns the certificate content certificate part C'n (C_N) of a CertificateCheck object
	 * as byte array. The signature of the certificate must have been deciphered before
	 * calling this method.
	 * 
	 * @return	the certificate content certificate part C'n (C_N) of the CertificateCheck object
	 * 			as byte array
	 */
	public byte[] getC_NByteArray() {
		return c_n;
	}

	/**
	 * Returns the certificate content certificate part C'n (C_N) of a CertificateCheck object
	 * as hex string. The signature of the certificate must have been deciphered before
	 * calling this method.
	 * 
	 * @return	the certificate content certificate part C'n (C_N) of the CertificateCheck object
	 * 			as hex string
	 */
	public String getC_NHexString() {
		return new SecurityByteArrayTools().ByteArrayToHexString( c_n );
	}

	/**
	 * Returns the certificate content c_0 of a CertificateCheck object
	 * as byte array. The signature of the certificate must have been deciphered before
	 * calling this method.
	 * 
	 * @return	the certificate content c_0 of the CertificateCheck object
	 * 			as byte array
	 */
	public byte[] getC_0ByteArray() {
		return c_0;
	}

	/**
	 * Returns the certificate content C' (C_0) of a CertificateCheck object
	 * as hex string. The signature of the certificate must have been deciphered before
	 * calling this method.
	 * 
	 * @return	the certificate content C' (C_0) of the CertificateCheck object
	 * 			as hex string
	 */
	public String getC_0HexString() {
		return new SecurityByteArrayTools().ByteArrayToHexString( c_0 );
	}

	/**
	 * Returns the certificate authority reference CAR of a CertificateCheck object
	 * as byte array. The signature of the certificate must have been deciphered before
	 * calling this method.
	 * 
	 * @return	the certificate authority reference CAR of the CertificateCheck object
	 * 			as byte array
	 */
	public byte[] getCARByteArray() {
		return car;
	}

	/**
	 * Returns the certificate authority reference CAR of a CertificateCheck object
	 * as hex string. The signature of the certificate must have been deciphered before
	 * calling this method.
	 * 
	 * @return	the certificate authority reference CAR of the CertificateCheck object
	 * 			as hex string
	 */
	public String getCARHexString() {
		return new SecurityByteArrayTools().ByteArrayToHexString( car );
	}

	/**
	 * Returns the sha1 hash H' (H_0) of a CertificateCheck object as byte array.
	 * The signature of the certificate must have been deciphered before
	 * calling this method.
	 * 
	 * @return	the sha1 hash H' (H_0) of the CertificateCheck object as byte array
	 */
	public byte[] getH_0ByteArray() {
		return h_0;
	}

	/**
	 * Returns the sha1 hash H' (H_0) of a CertificateCheck object as hex string.
	 * The signature of the certificate must have been deciphered before
	 * calling this method.
	 * 
	 * @return	the sha1 hash H' (H_0) of the CertificateCheck object as hex string
	 */
	public String getH_0HexString() {
		return new SecurityByteArrayTools().ByteArrayToHexString( h_0 );
	}
}
