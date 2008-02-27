/*
    $Id:CertificateContent.java 26 2008-02-25 22:28:27Z deetee $

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
 * The (clear) content of the certificate of a public key.
 */
public class CertificateContent extends DataClass {
	/*
	 * CertificateContent ::= SEQUENCE {
	 * 	certificateProfileIdentifier INTEGER(0..255), 1 byte
	 * 	certificationAuthorityReference KeyIdentifier, 8 bytes
	 * 	certificateHolderAuthorisation CertificateHolderAuthorisation, 7 bytes
	 * 	certificateEndOfValidity TimeReal, 4 bytes
	 *  certificateHolderReference KeyIdentifier, 8 bytes
	 *  publicKey PublicKey, 136 bytes 
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 164;

	private short certificateProfileIdentifier;
	private KeyIdentifier certificationAuthorityReference;
	private CertificateHolderAuthorisation certificateHolderAuthorisation;
	private TimeReal certificateEndOfValidity;
	private KeyIdentifier certificateHolderReference;
	private PublicKey publicKey;
	
	
	/**
	 * Constructor for a CertificateContent object
	 */
	public CertificateContent() {
		certificateProfileIdentifier = 0;
		certificationAuthorityReference = new KeyIdentifier();
		certificateHolderAuthorisation = new CertificateHolderAuthorisation();
		certificateEndOfValidity = new TimeReal();
		certificateHolderReference = new KeyIdentifier();
		publicKey = new PublicKey();
	}

	/**
	 * Constructor for a CertificateContent object
	 * 
	 * @param	value		byte array of a CertificateContent structure
	 * 						whose data is used when the CertificateContent
	 * 						object is created.
	 */
	public CertificateContent( byte[] value, short kidType ) {
		certificateProfileIdentifier = convertIntoUnsigned1ByteInt( value[ 0 ] );
		certificationAuthorityReference = new KeyIdentifier( arrayCopy( value, 1, 8 ), kidType );
		certificateHolderAuthorisation = new CertificateHolderAuthorisation( arrayCopy( value, 9, 7 ) );
		certificateEndOfValidity = new TimeReal( arrayCopy( value, 16, 4 ) );
		certificateHolderReference = new KeyIdentifier( arrayCopy( value, 20, 8 ), kidType );
		publicKey = new PublicKey( arrayCopy( value, 28, 136 ) );
	}

	/**
	 * Returns the version of the corresponding certificate of a CertificateContent object.
	 * 
	 * @return	the version of the corresponding certificate of the CertificateContent object
	 */
	public short getCertificateProfileIdentifier() {
		return certificateProfileIdentifier;
	}

	/**
	 * Sets the version of the corresponding certificate of a CertificateContent object.
	 * 
	 * @param	certificateProfileIdentifier	the version of the corresponding certificate
	 * 											to be set for the CertificateContent object.
	 */
	public void setCertificateProfileIdentifier( short certificateProfileIdentifier ) {
		this.certificateProfileIdentifier = certificateProfileIdentifier;
	}

	/**
	 * Returns the identification of the Certification Authority issuing the certificate which
	 * also references the Public Key of this Certification Authority of a
	 * CertificateContent object.
	 * 
	 * @return		the identification of the Certification Authority issuing the certificate which
	 * 				also references the Public Key of this Certification Authority of the
	 * 				CertificateContent object.
	 */
	public KeyIdentifier getCertificationAuthorityReference() {
		return certificationAuthorityReference;
	}

	/**
	 * Sets the identification of the Certification Authority issuing the certificate which
	 * also references the Public Key of this Certification Authority of a
	 * CertificateContent object.
	 * 
	 * @param	certificationAuthorityReference		the identification of the Certification Authority
	 * 												issuing the certificate which also references the
	 * 												Public Key of this Certification Authority to be set
	 * 												for the CertificateContent object.
	 */
	public void setCertificationAuthorityReference( KeyIdentifier certificationAuthorityReference ) {
		this.certificationAuthorityReference = certificationAuthorityReference;
	}

	/**
	 * Returns the identification of the rights of the certificate holder of a
	 * CertificateContent object.
	 * 
	 * @return		the identification of the rights of the certificate holder of the
	 * 				CertificateContent object.
	 */
	public CertificateHolderAuthorisation getCertificateHolderAuthorisation() {
		return certificateHolderAuthorisation;
	}

	/**
	 * Sets the identification of the rights of the certificate holder of a
	 * CertificateContent object.
	 * 
	 * @param	certificateHolderAuthorisation		the identification of the rights of the certificate
	 * 												holder to be set for the CertificateContent object.
	 */
	public void setCertificateHolderAuthorisation( CertificateHolderAuthorisation certificateHolderAuthorisation ) {
		this.certificateHolderAuthorisation = certificateHolderAuthorisation;
	}

	/**
	 * Returns the date when the certificate expires administratively of a CertificateContent object.
	 * 
	 * @return		the date when the certificate expires administratively of the CertificateContent object.
	 */
	public TimeReal getCertificateEndOfValidity() {
		return certificateEndOfValidity;
	}

	/**
	 * Sets the date when the certificate expires administratively of a CertificateContent object.
	 * 
	 * @param	certificateEndOfValidity	the date when the certificate expires administratively
	 * 										to be set for the CertificateContent object.
	 */
	public void setCertificateEndOfValidity( TimeReal certificateEndOfValidity ) {
		this.certificateEndOfValidity = certificateEndOfValidity;
	}

	/**
	 * Returns the identification of the certificate holder which also references his Public Key
	 * of a CertificateContent object.
	 * 
	 * @return	the identification of the certificate holder which also references his Public Key
	 * 			of the CertificateContent object.
	 */
	public KeyIdentifier getCertificateHolderReference() {
		return certificateHolderReference;
	}

	/**
	 * Sets the identification of the certificate holder which also references his Public Key
	 * of a CertificateContent object.
	 * 
	 * @param	certificateHolderReference	the identification of the certificate holder which
	 * 										also references his Public Key to be set for the
	 * 										CertificateContent object.
	 */
	public void setCertificateHolderReference( KeyIdentifier certificateHolderReference ) {
		this.certificateHolderReference = certificateHolderReference;
	}

	/**
	 * Returns the public key that is certified by this certificate of a CertificateContent object.
	 * 
	 * @return	the public key that is certified by this certificate of the CertificateContent object.
	 */
	public PublicKey getPublicKey() {
		return publicKey;
	}

	/**
	 * Sets the public key that is certified by this certificate of a CertificateContent object.
	 * 
	 * @param	publicKey	the public key that is certified by this certificate to be set for the
	 * 						CertificateContent object.
	 */
	public void setPublicKey( PublicKey publicKey ) {
		this.publicKey = publicKey;
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		node.addContent( new Element( "certificateProfileIdentifier" ).setText( Short.toString( certificateProfileIdentifier ) ) );
		node.addContent( certificationAuthorityReference.generateXMLElement( "certificationAuthorityReference" ) );
		node.addContent( certificateHolderAuthorisation.generateXMLElement( "certificateHolderAuthorisation" ) );
		node.addContent( certificateEndOfValidity.generateXMLElement( "certificateEndOfValidity" ) );
		node.addContent( certificateHolderReference.generateXMLElement( "certificateHolderReference" ) );
		node.addContent( publicKey.generateXMLElement( "publicKey" ) );

		return node;
	}
}
