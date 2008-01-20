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

/**
 * The content of a certificate.
 */
public class CertificateContent {

	private byte cpi;
	private byte[] car;
	private byte[] cha;
	private byte[] eov;
	private byte[] chr;
	private byte[] n;
	private byte[] e;


	/**
	 * Constructor for a CertificateContent object.
	 *
	 * @param	certificateContentByteArray		byte array of a CertificateContent structure
	 * 											whose data is used when the CertificateContent
	 * 											object is created.
	 */
	public CertificateContent( byte[] certificateContentByteArray ) {
		car = new byte[ 8 ];
		cha = new byte[ 7 ];
		eov = new byte[ 4 ];
		chr = new byte[ 8 ];
		n = new byte[ 128 ];
		e = new byte[ 8 ];

		cpi = certificateContentByteArray[ 0 ];
		System.arraycopy( certificateContentByteArray, 1, car, 0, 8 );
		System.arraycopy( certificateContentByteArray, 9, cha, 0, 7 );
		System.arraycopy( certificateContentByteArray, 16, eov, 0, 4 );
		System.arraycopy( certificateContentByteArray, 20, chr, 0, 8 );
		System.arraycopy( certificateContentByteArray, 28, n, 0, 128 );
		System.arraycopy( certificateContentByteArray, 156, e, 0, 8 );
	}

	/**
	 * Constructor for a CertificateContent object.
	 * 
	 * @param	certificateContentHexString		hex string of a CertificateContent structure
	 * 											whose data is used when the CertificateContent
	 * 											object is created.
	 */
	public CertificateContent( String certificateContentHexString ) {
		 this( new ByteArrayTools().HexStringToByteArray( certificateContentHexString ) );
	}

	/**
	 * Returns the certificate profile identifier CPI of a CertificateContent object
	 * as byte array.
	 * 
	 * @return	the certificate profile identifier CPI of the CertificateContent object
	 * 			as byte array
	 */
	public byte[] getCPIByteArray() {
		return new byte[]{ cpi };
	}

	/**
	 * Returns the certificate profile identifier CPI of a CertificateContent object
	 * as hex string.
	 * 
	 * @return	the certificate profile identifier CPI of the CertificateContent object
	 * 			as hex string
	 */
	public String getCPIHexString() {
		return new ByteArrayTools().ByteArrayToHexString( new byte[]{ cpi } );
	}

	/**
	 * Returns the certification authority reference CAR of a CertificateContent object
	 * as byte array.
	 * 
	 * @return	the certification authority reference CAR of the CertificateContent object
	 * 			as byte array
	 */
	public byte[] getCARByteArray() {
		return car;
	}

	/**
	 * Returns the certification authority reference CAR of a CertificateContent object
	 * as hex string.
	 * 
	 * @return	the certification authority reference CAR of the CertificateContent object
	 * 			as hex string
	 */
	public String getCARHexString() {
		return new ByteArrayTools().ByteArrayToHexString( car );
	}

	/**
	 * Returns the certification holder authorisation CHA of a CertificateContent object
	 * as byte array.
	 * 
	 * @return	the certification holder authorisation CHA of the CertificateContent object
	 * 			as byte array
	 */
	public byte[] getCHAByteArray() {
		return cha;
	}

	/**
	 * Returns the certification holder authorisation CHA of a CertificateContent object
	 * as hex string.
	 * 
	 * @return	the certification holder authorisation CHA of the CertificateContent object
	 * 			as hex string
	 */
	public String getCHAHexString() {
		return new ByteArrayTools().ByteArrayToHexString( cha );
	}

	/**
	 * Returns the certificate end of validity EOV of a CertificateContent object
	 * as byte array.
	 * 
	 * @return	the certificate end of validity EOV of the CertificateContent object
	 * 			as byte array
	 */
	public byte[] getEOVByteArray() {
		return eov;
	}

	/**
	 * Returns the certificate end of validity EOV of a CertificateContent object
	 * as hex string.
	 * 
	 * @return	the certificate end of validity EOV of the CertificateContent object
	 * 			as hex string
	 */
	public String getEOVHexString() {
		return new ByteArrayTools().ByteArrayToHexString( eov );
	}

	/**
	 * Returns the certificate holder reference CHR of a CertificateContent object
	 * as byte array.
	 * 
	 * @return	the certificate holder reference CHR of the CertificateContent object
	 * 			as byte array
	 */
	public byte[] getCHRByteArray() {
		return chr;
	}

	/**
	 * Returns the certificate holder reference CHR of a CertificateContent object
	 * as hex string.
	 * 
	 * @return	the certificate holder reference CHR of the CertificateContent object
	 * 			as hex string
	 */
	public String getCHRHexString() {
		return new ByteArrayTools().ByteArrayToHexString( chr );
	}

	/**
	 * Returns the public key modulus n a CertificateContent object
	 * as byte array.
	 * 
	 * @return	the public key modulus n of the CertificateContent object
	 * 			as byte array
	 */
	public byte[] getNByteArray() {
		return n;
	}

	/**
	 * Returns the public key modulus n a CertificateContent object
	 * as hex string.
	 * 
	 * @return	the public key modulus n of the CertificateContent object
	 * 			as hex string
	 */
	public String getNHexString() {
		return new ByteArrayTools().ByteArrayToHexString( n );
	}

	/**
	 * Returns the public key exponent e a CertificateContent object
	 * as byte array.
	 * 
	 * @return	the public key exponent e of the CertificateContent object
	 * 			as byte array
	 */
	public byte[] getEByteArray() {
		return e;
	}

	/**
	 * Returns the public key exponent e a CertificateContent object
	 * as hex string.
	 * 
	 * @return	the public key exponent e of the CertificateContent object
	 * 			as hex string
	 */
	public String getEHexString() {
		return new ByteArrayTools().ByteArrayToHexString( e );
	}
}
