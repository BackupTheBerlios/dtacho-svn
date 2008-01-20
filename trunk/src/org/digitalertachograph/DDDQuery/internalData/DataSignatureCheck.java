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

package org.digitalertachograph.DDDQuery.internalData;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Checks the integrity of data and its signature.
 */
public class DataSignatureCheck {

	private byte[] DataByteArray;
	private String DataHexString;

	private byte[] DataSHAHash;
	
	private String rsaPublicKeyModulusHexString;
	private String rsaPublicKeyExponentHexString;
	private byte[] rsaPublicKeyModulusByteArray;
	private byte[] rsaPublicKeyExponentByteArray;
	private PublicKey RSAPublicKey;
	
	private byte[] cipheredSignature;
	private byte[] decipheredSignature;
	
	
	/**
	 * Constructor for a DataSignatureCheck object
	 */
	public DataSignatureCheck() {
		super();
	}

	/* data stuff below */

	/**
	 * Returns the data of a DataSignatureCheck object
	 * as byte array.
	 * 
	 * @return	the data of the DataSignatureCheck object as byte array
	 */
	public byte[] getDataByteArray() {
		return DataByteArray;
	}

	/**
	 * Sets the data of a DataSignatureCheck object
	 * 
	 * @param	dataByteArray	the byte array of data to be set for the DataSignatureCheck object.
	 */
	public void setDataByteArray( byte[] dataByteArray ) {
		DataByteArray = dataByteArray;
		DataHexString = new ByteArrayTools().ByteArrayToHexString( dataByteArray );
	}

	/**
	 * Returns the data of a DataSignatureCheck object as hex string.
	 * 
	 * @return	the data of the DataSigatureCheck object as hex string
	 */
	public String getDataHexString() {
		return DataHexString;
	}

	/**
	 * Sets the data of a DataSignatureCheck object
	 * 
	 * @param	dataHexString	the data as hex string to be set for the DataSignatureCheck object.
	 */
	public void setDataHexString( String dataHexString ) {
		DataByteArray = new ByteArrayTools().HexStringToByteArray( dataHexString );
		DataHexString = dataHexString;
	}

	/**
	 * Calculates the SHA1 hash value of the data of a DataSignatureCheck object
	 * supplied by {@link #setDataByteArray(byte[])} and returns it as byte array
	 * 
	 * @return	the calculated SHA1 hash value of the data of the DataSignatureCheck object
	 * 			as byte array
	 */
	public byte[] getDataSHAHashByteArray() {
		try {
			MessageDigest sha = MessageDigest.getInstance( "SHA-1" );
			sha.update( DataByteArray );
			DataSHAHash = sha.digest();
			return DataSHAHash;
		}
		catch ( NoSuchAlgorithmException nsae ) {
			return null;
		}
	}

	/**
	 * Calculates the SHA1 hash value of the data of a DataSignatureCheck object
	 * supplied by {@link #setDataByteArray(byte[])} and returns it as hex string
	 * 
	 * @return	the calculated SHA1 hash value of the data of the DataSignatureCheck object
	 * 			as hex string
	 */
	public String getDataSHAHashHexString() {
			return new ByteArrayTools().ByteArrayToHexString( this.getDataSHAHashByteArray() );
	}

	/* signature stuff below */
	
	/**
	 * Sets the RSA public key of a DataSignatureCheck object which is required for
	 * {@link #decipherSignature(byte[])} by supplying the key modulus and the key
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
		rsaPublicKeyModulusByteArray = new ByteArrayTools().HexStringToByteArray( rsaPublicKeyModulusHexString );
		rsaPublicKeyExponentByteArray = new ByteArrayTools().HexStringToByteArray( rsaPublicKeyExponentHexString );

		RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(new BigInteger( rsaPublicKeyModulusHexString, 16 ), new BigInteger( rsaPublicKeyExponentHexString, 16 ) );

		KeyFactory keyFactory = KeyFactory.getInstance( "RSA" );
		RSAPublicKey = keyFactory.generatePublic( pubKeySpec );
	}

	/**
	 * Sets the RSA public key of a DataSignatureCheck object which is required for
	 * {@link #decipherSignature(byte[])} by supplying the key modulus and the key
	 * exponent as byte array 
	 * 
	 * @param	rsaPublicKeyModulusByteArray	the RSA public key modulus as byte array 
	 * @param	rsaPublicKeyExponentByteArray	the RSA public key exponent as byte array
	 *
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public void setRSAPublicKey( byte[] rsaPublicKeyModulusByteArray, byte[] rsaPublicKeyExponentByteArray ) throws NoSuchAlgorithmException, InvalidKeySpecException {
		setRSAPublicKey( new ByteArrayTools().ByteArrayToHexString( rsaPublicKeyModulusByteArray ), new ByteArrayTools().ByteArrayToHexString( rsaPublicKeyExponentByteArray) );
	}
	
	/**
	 * Returns the RSA public key modulus set by {@link #setRSAPublicKey(byte[], byte[])} or
	 * by {@link #setRSAPublicKey(String, String)} as byte array
	 * 
	 * @return	the RSA public key modulus of the DataSignatureCheck object as byte array
	 */
	public byte[] getRSAPublicKeyModulusByteArray() {
		return rsaPublicKeyModulusByteArray;
	}

	/**
	 * Returns the RSA public key modulus set by {@link #setRSAPublicKey(byte[], byte[])} or
	 * by {@link #setRSAPublicKey(String, String)} as hex string
	 * 
	 * @return	the RSA public key modulus of the DataSignatureCheck object as hex string
	 */
	public String getRSAPublicKeyModulusHexString() {
		return rsaPublicKeyModulusHexString;
	}

	/**
	 * Returns the RSA public key exponent set by {@link #setRSAPublicKey(byte[], byte[])} or
	 * by {@link #setRSAPublicKey(String, String)} as byte array
	 * 
	 * @return	the RSA public key exponent of the DataSignatureCheck object as byte array
	 */
	public byte[] getRSAPublicKeyExponentByteArray() {
		return rsaPublicKeyExponentByteArray;
	}
	
	/**
	 * Returns the RSA public key exponent set by {@link #setRSAPublicKey(byte[], byte[])} or
	 * by {@link #setRSAPublicKey(String, String)} as hex string
	 * 
	 * @return	the RSA public key exponent of the DataSignatureCheck object as hex string
	 */
	public String getRSAPublicKeyExponentHexString() {
		return rsaPublicKeyExponentHexString;
	}

	/**
	 * Returns the ciphered signature set by {@link #setCipheredSignature(byte[])} or
	 * (indirectly) set by {@link #decipherSignature(byte[])} of a DataSignatureCheck object.
	 * 
	 * @return	the ciphered signature of the DataSignatureCheck object
	 */
	public byte[] getCipheredSignature() {
		return cipheredSignature;
	}

	/**
	 * Sets the supplied <code>cipheredSignature</code> for a DataSignatureCheck object.
	 * 
	 * @param 	cipheredSignature	the ciphered signature to be set for the
	 * 								DataSignatureCheck object
	 */
	public void setCipheredSignature( byte[] cipheredSignature ) {
		this.cipheredSignature = cipheredSignature;
	}

	/**
	 * Deciphers the ciphered signature of a DataSignature object set by
	 * {@link #setCipheredSignature(byte[])} with the RSA public
	 * key set by {@link #setRSAPublicKey(byte[], byte[])} or by
	 * {@link #setRSAPublicKey(String, String)}.
	 */
	public void decipherSignature() {
		try {
			Cipher mycipher = Cipher.getInstance( "RSA/ECB/NoPadding" );

			mycipher.init( Cipher.DECRYPT_MODE, RSAPublicKey );

			decipheredSignature = mycipher.doFinal( cipheredSignature, 0, 128 );
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
	 * Sets the supplied <code>cipheredSignature</code> for a DataSignatureCheck object
	 * and deciphers the signature with the RSA public
	 * key set by {@link #setRSAPublicKey(byte[], byte[])} or by
	 * {@link #setRSAPublicKey(String, String)}.
	 * 
	 * @param 	cipheredSignature	the ciphered signature to be set for the
	 * 								DataSignatureCheck object and to be deciphered
	 */
	public void decipherSignature( byte[] cipheredSignature ) {
		this.setCipheredSignature( cipheredSignature );
		this.decipherSignature();
	}

	/**
	 * Returns the deciphered signature that was deciphered by {@link #decipherSignature()}
	 * or {@link #decipherSignature(byte[])} as byte array.
	 * 
	 * @return	the deciphered signature of the DataSignatureCheck object as byte array
	 */
	public byte[] getDecipheredSignatureByteArray() {
		return decipheredSignature;
	}

	/**
	 * Returns the deciphered signature that was deciphered by {@link #decipherSignature()}
	 * or {@link #decipherSignature(byte[])} as hex string.
	 * 
	 * @return	the deciphered signature of the DataSignatureCheck object as hex string
	 */
	public String getDecipheredSignatureHexString() {
		return new ByteArrayTools().ByteArrayToHexString( decipheredSignature );
	}

	/**
	 * Indicates if the data integrity check was successful and the data
	 * is valid.
	 * <br>
	 * The data integrity check is done internally by comparing the data's
	 * SHA1 hash with the SHA1 hash supplied by the deciphered signature.
	 *
	 * @return	true if the data's integrity could be successfully verified
	 * 			and the validity of the data is proved.
	 */
	public boolean isValidData() {
		if ( getDataSHAHashHexString().compareTo( getDecipheredSignatureHexString().substring( 216, 256 ) ) == 0 ) {
			return true;
		}
		else {
			return false;
		}
	}
}
