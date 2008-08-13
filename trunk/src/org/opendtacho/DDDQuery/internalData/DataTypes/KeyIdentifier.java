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

package org.opendtacho.DDDQuery.internalData.DataTypes;

import org.jdom.Element;
import org.opendtacho.DDDQuery.internalData.DataClass;

/**
 * A unique identifier of a Public Key used to reference and select the key.
 * It also identifies the holder of the key.
 */
public class KeyIdentifier extends DataClass {
	/*
	 * KeyIdentifier ::= CHOICE {
	 * 	extendedSerialNumber ExtendedSerialNumber, 8 bytes
	 *  certificateRequestID CertificateRequestID, 8 bytes
	 *  certificationAuthorityKID CertificationAuthorityKID, 8 bytes
	 * }
	 * ---
	 * The first choice is suitable to reference the public key of a Vehicle Unit or
	 * of a tachograph card.
	 * 
	 * The second choice is suitable to reference the public key of a Vehicle Unit (in
	 * the case the serial number of the Vehicle Unit cannot be known at certificate
	 * generation time).
	 * 
	 * The third choice is suitable to reference the public key of a Member State.
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 8;

	private short kidType;

	private ExtendedSerialNumber extendedSerialNumber;
	private CertificateRequestID certificateRequestID;
	private CertificationAuthorityKID certificationAuthorityKID;


	/**
	 * Public key of a Vehicle Unit or of a tachograph card
	 */
	public static final short KIDTYPE_PK_VU_TC = 1;

	/**
	 * Public key of a Vehicle Unit (in the case the serial number of the
	 * Vehicle Unit cannot be known at certificate generation time)
	 */
	public static final short KIDTYPE_PK_VU = 2;

	/**
	 * Public key of a Member State
	 */
	public static final short KIDTYPE_PK_MS = 3;

	/**
	 * Constructor for a KeyIdentifier object
	 */
	public KeyIdentifier() {
		extendedSerialNumber = new ExtendedSerialNumber();
		certificateRequestID = new CertificateRequestID();
		certificationAuthorityKID = new CertificationAuthorityKID();
	}

	/**
	 * Constructor for a KeyIdentifier object
	 * 
	 * @param	value		byte array of a KeyIdentifier structure
	 * 						whose data is used when the KeyIdentifier
	 * 						object is created.
	 * 
	 * @param	kidType		the type of the KeyIdentifier structure
	 */
	public KeyIdentifier( byte[] value, short kidType ) {
		this.kidType = kidType;
		if ( kidType == KIDTYPE_PK_VU_TC ) {
			extendedSerialNumber = new ExtendedSerialNumber( arrayCopy( value, 0, 8 ) );
		}
		else if ( kidType == KIDTYPE_PK_VU ) {
			certificateRequestID = new CertificateRequestID( arrayCopy( value, 0, 8 ) );
		}
		else {
			certificationAuthorityKID = new CertificationAuthorityKID( arrayCopy( value, 0, 8 ) );
		}
	}

	/**
	 * Returns the key identifier type of a KeyIdentifier object.
	 * See {@link #KIDTYPE_PK_VU_TC}, {@link #KIDTYPE_PK_VU}, {@link #KIDTYPE_PK_MS}.
	 * 
	 * @return	the key application identifier type of the KeyIdentifier object
	 */
	public short getKIDType() {
		return kidType;
	}

	/**
	 * Sets the key identifier type of a KeyIdentifier object.
	 * See {@link #KIDTYPE_PK_VU_TC}, {@link #KIDTYPE_PK_VU}, {@link #KIDTYPE_PK_MS}.
	 * 
	 * @param	kidType		key identifier type to be set for the KeyIdentifier object
	 */
	public void setKIDType( short kidType ) {
		this.kidType = kidType;
	}

	/**
	 * Returns the key identifier of a {@link #KIDTYPE_PK_VU_TC} KeyIdentifier object.
	 * 
	 * @return	the key identifier of the {@link #KIDTYPE_PK_VU_TC} KeyIdentifier object.
	 * 
	 * @throws	Exception	An exception is thrown when this method is called and the
	 * 						type of the KeyIdentifier object is not set to {@link #KIDTYPE_PK_VU_TC}.
	 */
	public ExtendedSerialNumber getExtendedSerialNumber() throws Exception {
		if ( kidType == KIDTYPE_PK_VU_TC ) {
			return extendedSerialNumber;
		}
		else {
			throw new Exception();
		}
	}

	/**
	 * Sets the key identifier of a {@link #KIDTYPE_PK_VU_TC} KeyIdentifier object.
	 * 
	 * @param	extendedSerialNumber	the key identifier of a {@link #KIDTYPE_PK_VU_TC}
	 * 									to be set for the KeyIdentifier object which is
	 * 									an {@link ExtendedSerialNumber}.
	 * 
	 * @throws	Exception	An exception is thrown when this method is called and the
	 * 						type of the KeyIdentifier object is not set to {@link #KIDTYPE_PK_VU_TC}.
	 */
	public void setExtendedSerialNumber( ExtendedSerialNumber extendedSerialNumber ) throws Exception {
		if ( kidType == KIDTYPE_PK_VU_TC ) {
			this.extendedSerialNumber = extendedSerialNumber;
		}
		else {
			throw new Exception();
		}
	}

	/**
	 * Returns the key identifier of a {@link #KIDTYPE_PK_VU} KeyIdentifier object.
	 * 
	 * @return	the key identifier of the {@link #KIDTYPE_PK_VU} KeyIdentifier object.
	 * 
	 * @throws	Exception	An exception is thrown when this method is called and the
	 * 						type of the KeyIdentifier object is not set to {@link #KIDTYPE_PK_VU}.
	 */
	public CertificateRequestID getCertificateRequestID() throws Exception {
		if ( kidType == KIDTYPE_PK_VU ) {
			return certificateRequestID;
		}
		else {
			throw new Exception();
		}
	}

	/**
	 * Sets the key identifier of a {@link #KIDTYPE_PK_VU} KeyIdentifier object.
	 * 
	 * @param	certificateRequestID	the key identifier of a {@link #KIDTYPE_PK_VU}
	 * 									to be set for the KeyIdentifier object which is
	 * 									a {@link CertificateRequestID}.
	 * 
	 * @throws	Exception	An exception is thrown when this method is called and the
	 * 						type of the KeyIdentifier object is not set to {@link #KIDTYPE_PK_VU}.
	 */
	public void setCertificateRequestID( CertificateRequestID certificateRequestID ) throws Exception {
		if ( kidType == KIDTYPE_PK_VU ) {
			this.certificateRequestID = certificateRequestID;
		}
		else {
			throw new Exception();
		}
	}

	/**
	 * Returns the key identifier of a {@link #KIDTYPE_PK_MS} KeyIdentifier object.
	 * 
	 * @return	the key identifier of the {@link #KIDTYPE_PK_MS} KeyIdentifier object.
	 * 
	 * @throws	Exception	An exception is thrown when this method is called and the
	 * 						type of the KeyIdentifier object is not set to {@link #KIDTYPE_PK_MS}.
	 */
	public CertificationAuthorityKID getCertificationAuthorityKID() throws Exception {
		if ( kidType == KIDTYPE_PK_MS ) {
			return certificationAuthorityKID;
		}
		else {
			throw new Exception();
		}
	}

	/**
	 * Sets the key identifier of a {@link #KIDTYPE_PK_MS} KeyIdentifier object.
	 * 
	 * @param	certificationAuthorityKID	the key identifier of a {@link #KIDTYPE_PK_MS}
	 * 										to be set for the KeyIdentifier object which is
	 * 										a {@link CertificationAuthorityKID}.
	 * 
	 * @throws	Exception	An exception is thrown when this method is called and the
	 * 						type of the KeyIdentifier object is not set to {@link #KIDTYPE_PK_MS}.
	 */
	public void setCertificationAuthorityKID( CertificationAuthorityKID certificationAuthorityKID ) throws Exception {
		if ( kidType == KIDTYPE_PK_MS ) {
			this.certificationAuthorityKID = certificationAuthorityKID;
		}
		else {
			throw new Exception();
		}
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		if ( kidType == KIDTYPE_PK_VU_TC ) {
			node.addContent( extendedSerialNumber.generateXMLElement( "extendedSerialNumber" ) );
		}
		else if ( kidType == KIDTYPE_PK_VU ) {
			node.addContent( certificateRequestID.generateXMLElement( "certificateRequestID" ) );
		}
		else if ( kidType == KIDTYPE_PK_MS ) {
			node.addContent( certificationAuthorityKID.generateXMLElement( "certificationAuthorityKID" ) );
		}

		return node;
	}
}
