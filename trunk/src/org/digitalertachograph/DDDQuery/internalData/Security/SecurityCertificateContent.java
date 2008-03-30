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

package org.digitalertachograph.DDDQuery.internalData.Security;

import org.digitalertachograph.DDDQuery.internalData.DataTypes.CertificateContent;
import org.digitalertachograph.DDDQuery.internalData.DataTypes.KeyIdentifier;

/**
 * The content of a certificate.
 */
public class SecurityCertificateContent {

	private CertificateContent certificateContent;

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
	public SecurityCertificateContent( byte[] certificateContentByteArray ) {
		certificateContent = new CertificateContent( certificateContentByteArray, KeyIdentifier.KIDTYPE_PK_MS );
		
		// cpi, certificate profile identifier
		cpi = (byte)( certificateContent.getCertificateProfileIdentifier() & 0x00ff );

		// car, certification authority reference
		car = new byte[ 8 ];
		try {
			System.arraycopy( new byte[] {
				(byte)( certificateContent.getCertificationAuthorityReference().getCertificationAuthorityKID().getNationNumeric().getNationNumeric() & 0x00ff ),
				(byte)( certificateContent.getCertificationAuthorityReference().getCertificationAuthorityKID().getNationAlpha().getNationAlpha().charAt( 0 ) ),
				(byte)( certificateContent.getCertificationAuthorityReference().getCertificationAuthorityKID().getNationAlpha().getNationAlpha().charAt( 1 ) ),
				(byte)( certificateContent.getCertificationAuthorityReference().getCertificationAuthorityKID().getNationAlpha().getNationAlpha().charAt( 2 ) ),
				(byte)( certificateContent.getCertificationAuthorityReference().getCertificationAuthorityKID().getKeySerialNumber() & 0x00ff ),
				(byte)( certificateContent.getCertificationAuthorityReference().getCertificationAuthorityKID().getAdditionalInfo()[ 0 ] ),
				(byte)( certificateContent.getCertificationAuthorityReference().getCertificationAuthorityKID().getAdditionalInfo()[ 1 ] ),
				(byte)( certificateContent.getCertificationAuthorityReference().getCertificationAuthorityKID().getCAIdentifier() )
				}, 0, car, 0, 8 );
		}
		catch ( Exception e ) {

		}

		// cha, certificate holder authorisation
		cha = new byte[ 7 ];
		System.arraycopy( certificateContent.getCertificateHolderAuthorisation().getTachographApplicationID(), 0, cha, 0, 6 );
		System.arraycopy( new byte[] { (byte)( certificateContent.getCertificateHolderAuthorisation().getEquipmentType().getEquipmentType() & 0xff ) }, 0, cha, 6 , 1 );

		// eov, certificate end of validity
		eov = new byte[ 4 ];
		System.arraycopy( new byte[] {
				(byte)( ( certificateContent.getCertificateEndOfValidity().getTimeReal() >> 24 ) & 0x000000ff ),
				(byte)( ( certificateContent.getCertificateEndOfValidity().getTimeReal() >> 16 ) & 0x000000ff ),
				(byte)( ( certificateContent.getCertificateEndOfValidity().getTimeReal() >> 8  ) & 0x000000ff ),
				(byte)(   certificateContent.getCertificateEndOfValidity().getTimeReal()         & 0x000000ff )
			}, 0, eov, 0, 4 );

		// chr, certificate holder reference
		chr = new byte[ 8 ];
		try {
			System.arraycopy( new byte[] {
				(byte)( certificateContent.getCertificateHolderReference().getCertificationAuthorityKID().getNationNumeric().getNationNumeric() & 0x00ff ),
				(byte)( certificateContent.getCertificateHolderReference().getCertificationAuthorityKID().getNationAlpha().getNationAlpha().charAt( 0 ) ),
				(byte)( certificateContent.getCertificateHolderReference().getCertificationAuthorityKID().getNationAlpha().getNationAlpha().charAt( 1 ) ),
				(byte)( certificateContent.getCertificateHolderReference().getCertificationAuthorityKID().getNationAlpha().getNationAlpha().charAt( 2 ) ),
				(byte)( certificateContent.getCertificateHolderReference().getCertificationAuthorityKID().getKeySerialNumber() & 0x00ff ),
				(byte)( certificateContent.getCertificateHolderReference().getCertificationAuthorityKID().getAdditionalInfo()[ 0 ] ),
				(byte)( certificateContent.getCertificateHolderReference().getCertificationAuthorityKID().getAdditionalInfo()[ 1 ] ),
				(byte)( certificateContent.getCertificateHolderReference().getCertificationAuthorityKID().getCAIdentifier() )
				}, 0, chr, 0, 8 );
		}
		catch ( Exception e ) {

		}

		// n, public key modulus
		n = new byte[ 128 ];
		n = certificateContent.getPublicKey().getRSAKeyModulus().getRSAKeyModulus();

		// e, public key exponent
		e = new byte[ 8 ];
		e = certificateContent.getPublicKey().getRSAKeyPublicExponent().getRSAKeyPublicExponent();
	}

	/**
	 * Constructor for a CertificateContent object.
	 * 
	 * @param	certificateContentHexString		hex string of a CertificateContent structure
	 * 											whose data is used when the CertificateContent
	 * 											object is created.
	 */
	public SecurityCertificateContent( String certificateContentHexString ) {
		 this( new SecurityByteArrayTools().HexStringToByteArray( certificateContentHexString ) );
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
		return new SecurityByteArrayTools().ByteArrayToHexString( new byte[]{ cpi } );
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
		return new SecurityByteArrayTools().ByteArrayToHexString( car );
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
		return new SecurityByteArrayTools().ByteArrayToHexString( cha );
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
		return new SecurityByteArrayTools().ByteArrayToHexString( eov );
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
		return new SecurityByteArrayTools().ByteArrayToHexString( chr );
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
		return new SecurityByteArrayTools().ByteArrayToHexString( n );
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
		return new SecurityByteArrayTools().ByteArrayToHexString( e );
	}
}
