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
 * Identifier of the Public Key of a Certification Authority
 * (a Member State or the European Certification Authority)
 */
public class CertificationAuthorityKID extends DataClass {
	/*
	 * CertificationAuthorityKID ::= SEQUENCE {
	 * 	nationNumeric NationNumeric, 1 byte
	 * 	nationAlpha NationAlpha, 3 bytes
	 * 	keySerialNumber INTEGER(0..255), 1 byte
	 * 	additionalInfo OCTET STRING(SIZE(2)), 2 bytes
	 * 	caIdentifier OCTET STRING(SIZE(1)), 1 byte
	 * }
	 * ---
	 * NationNumeric ::= INTEGER(0..255)
	 * ---
	 * NationAlpha ::= IA5String(SIZE(3))
	 */

	private NationNumeric nationNumeric;
	private NationAlpha nationAlpha;
	private short keySerialNumber;
	private byte[] additionalInfo;
	private byte caIdentifier;
	
	
	/**
	 * Constructor for a CertificationAuthorityKID object
	 */
	public CertificationAuthorityKID() {
		nationNumeric = new NationNumeric();
		nationAlpha = new NationAlpha();
		keySerialNumber = 0;
		additionalInfo = new byte[ 2 ];
		caIdentifier = 0;
	}

	/**
	 * Constructor for a CertificationAuthorityKID object
	 * 
	 * @param	value	byte array of a CertificationAuthorityKID structure
	 * 					whose data is used when the CertificationAuthorityKID
	 * 					object is created.
	 */
	public CertificationAuthorityKID( byte[] value ) {
		nationNumeric = new NationNumeric( value[ 0 ] );
		nationAlpha = new NationAlpha( arrayCopy( value, 1, 3 ) );
		keySerialNumber = value[ 4 ];
		additionalInfo = arrayCopy( value, 5, 2 );
		caIdentifier = value[ 7 ];
	}

	/**
	 * Returns the numerical nation code of the Certification Authority
	 * of a CertificationAuthorityKID object.
	 * 
	 * @return	the numerical nation code of the Certification Authority
	 * 			of the CertificationAuthorityKID object
	 */
	public NationNumeric getNationNumeric() {
		return nationNumeric;
	}

	/**
	 * Sets the numerical nation code of the Certification Authority
	 * of a CertificationAuthorityKID object.
	 * 
	 * @param	nationNumeric		the numerical nation code of the Certification Authority
	 * 								to be set for the CertificationAuthorityKID object
	 */
	public void setNationNumeric( NationNumeric nationNumeric ) {
		this.nationNumeric = nationNumeric;
	}

	/**
	 * Returns the alphanumerical nation code of the Certification Authority
	 * of a CertificationAuthorityKID object.
	 * 
	 * @return	the alphanumerical nation code of the Certification Authority
	 * 			of the CertificationAuthorityKID object
	 */
	public NationAlpha getNationAlpha() {
		return nationAlpha;
	}

	/**
	 * Sets the alphanumerical nation code of the Certification Authority
	 * of a CertificationAuthorityKID object.
	 * 
	 * @param	nationAlpha		the alphanumerical nation code of the Certification Authority
	 * 							to be set for the CertificationAuthorityKID object
	 */
	public void setNationAlpha( NationAlpha nationAlpha ) {
		this.nationAlpha = nationAlpha;
	}

	/**
	 * Returns the serial number to distinguish the different keys of the
	 * Certification Authority in the case keys are changed of a
	 * CertificationAuthorityKID object.
	 * 
	 * @return	the serial number to distinguish the different keys of the
	 * 			Certification Authority in the case keys are changed
	 * 			of the CertificationAuthorityKID object
	 */
	public short getKeySerialNumber() {
		return keySerialNumber;
	}

	/**
	 * Sets the serial number to distinguish the different keys of the
	 * Certification Authority in the case keys are changed
	 * of a CertificationAuthorityKID object.
	 * 
	 * @param	keySerialNumber		the serial number to distinguish the different keys of the
	 * 									Certification Authority in the case keys are changed
	 * 									to be set for the CertificationAuthorityKID object
	 */
	public void setKeySerialNumber( short keySerialNumber ) {
		this.keySerialNumber = keySerialNumber;
	}

	/**
	 * Returns the additional coding (Certification Authority specific)
	 * CertificationAuthorityKID object.
	 * 
	 * @return	the additional coding (Certification Authority specific)
	 * 			of the CertificationAuthorityKID object
	 */
	public byte[] getAdditionalInfo() {
		return additionalInfo;
	}

	/**
	 * Sets the additional coding (Certification Authority specific)
	 * of a CertificationAuthorityKID object.
	 * 
	 * @param	additionalInfo		the additional coding (Certification Authority specific)
	 * 								to be set for the CertificationAuthorityKID object
	 */
	public void setAdditionalInfo( byte[] additionalInfo ) {
		this.additionalInfo = arrayCopy( additionalInfo, 0, 2 );
	}

	/**
	 * Returns the identifier to distinguish a Certification Authority Key
	 * Identifier from other Key Identifiers of a CertificationAuthorityKID object.
	 * 
	 * @return	the identifier to distinguish a Certification Authority Key
	 * 			Identifier from other Key Identifiers of the CertificationAuthorityKID object
	 */
	public byte getCAIdentifier() {
		return caIdentifier;
	}

	/**
	 * Sets the identifier to distinguish a Certification Authority Key
	 * Identifier from other Key Identifiers of a CertificationAuthorityKID object.
	 * 
	 * @param	caIdentifier		the identifier to distinguish a Certification Authority Key
	 * 								Identifier from other Key Identifiers to be set for the
	 * 								 CertificationAuthorityKID object
	 */
	public void setCAIdentifier( byte caIdentifier ) {
		this.caIdentifier = caIdentifier;
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		node.addContent( nationNumeric.generateXMLElement( "nationNumeric" ) );
		node.addContent( nationAlpha.generateXMLElement( "nationAlpha" ) );
		node.addContent( new Element( "keySerialNumber" ).setText( Short.toString( keySerialNumber ) ) );
		node.addContent( new Element( "additionalInfo" ).setText( convertIntoHexString( additionalInfo ) ) );
		node.addContent( new Element( "caIdentifier" ).setText( convertIntoHexString( caIdentifier ) ) );

		return node;
	}
}
