/*
    $Id:CertificateRequestID.java 26 2008-02-25 22:28:27Z deetee $

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
 * Unique identification of a certificate request. It can also be used as a
 * Vehicle Unit Public Key Identifier if the serial number of the vehicle unit
 * to which the key is intended is not know at certificate generation time.
 */
public class CertificateRequestID extends DataClass {
	/*
	 * CertificateRequestID ::= SEQUENCE {
	 * 	requestSerialNumber INTEGER(0..2^32-1), 4 bytes
	 * 	requestMonthYear BDCString(SIZE(2)), 2 bytes
	 * 	crIdentifier OCTET STRING(SIZE(1)), 1 byte
	 * 	manufacturerCode ManufacturerCode, 1 byte
	 * }
	 * ---
	 * ManufacturerCode ::= INTEGER(0..255)
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 8;

	private long requestSerialNumber;
	private byte[] requestMonthYear;
	private byte crIdentifier;
	private ManufacturerCode manufacturerCode;
	
	
	/**
	 * Constructor for a CertificateRequestID object
	 */
	public CertificateRequestID() {
		requestSerialNumber = 0;
		requestMonthYear = new byte[ 2 ];
		crIdentifier = 0;
		manufacturerCode = new ManufacturerCode();
	}

	/**
	 * Constructor for a CertificateRequestID object
	 * 
	 * @param	value	byte array of a CertificateRequestID structure
	 * 					whose data is used when the CertificateRequestID
	 * 					object is created.
	 */
	public CertificateRequestID( byte[] value ) {
		requestSerialNumber = convertIntoUnsigned4ByteInt( arrayCopy( value, 0, 4 ) );
		requestMonthYear = arrayCopy( value, 4, 2 );
		crIdentifier = value[ 6 ];
		manufacturerCode = new ManufacturerCode( value[ 7 ] );
	}

	/**
	 * Returns the serial number for the certificate request
	 * of a CertificateRequestID object.
	 * 
	 * @return	the serial number for the certificate request
	 * 			of the CertificateRequestID object
	 */
	public long getRequestSerialNumber() {
		return requestSerialNumber;
	}

	/**
	 * Sets the serial number for the certificate request
	 * of a CertificateRequestID object.
	 * 
	 * @param	requestSerialNumber		the serial number for the certificate request
	 * 									to be set for the CertificateRequestID object
	 */
	public void setRequestSerialNumber( long requestSerialNumber ) {
		this.requestSerialNumber = requestSerialNumber;
	}

	/**
	 * Returns the identification of the month and the year of the certificate request
	 * of a CertificateRequestID object.
	 * 
	 * @return	the identification of the month and the year of the certificate request
	 * 			of the CertificateRequestID object
	 */
	public byte[] getRequestMonthYear() {
		return requestMonthYear;
	}

	/**
	 * Sets the identification of the month and the year of the certificate request
	 * of a CertificateRequestID object.
	 * 
	 * @param	requestMonthYear		the identification of the month and the year of the certificate request
	 * 									to be set for the CertificateRequestID object
	 */
	public void setRequestMonthYear( byte[] requestMonthYear ) {
		this.requestMonthYear = arrayCopy( requestMonthYear, 0, 2 );
	}

	/**
	 * Returns the identifier to distinguish a certificate request from an extended serial number
	 * of a CertificateRequestID object.
	 * 
	 * @return	the identifier to distinguish a certificate request from an extended serial number
	 * 			of the CertificateRequestID object
	 */
	public long getcrIdentifier() {
		return crIdentifier;
	}

	/**
	 * Sets the identifier to distinguish a certificate request from an extended serial number
	 * of a CertificateRequestID object.
	 * 
	 * @param	crIdentifier	the identifier to distinguish a certificate request from an extended serial number
	 * 							to be set for the CertificateRequestID object
	 */
	public void setcrIdentifier( byte crIdentifier ) {
		this.crIdentifier = crIdentifier;
	}

	/**
	 * Returns the numerical code of the manufacturer requesting the certificate
	 * of a CertificateRequestID object.
	 * 
	 * @return	the numerical code of the manufacturer requesting the certificate
	 * 			of the CertificateRequestID object
	 */
	public ManufacturerCode getManufacturerCode() {
		return manufacturerCode;
	}

	/**
	 * Sets the numerical code of the manufacturer requesting the certificate
	 * of a CertificateRequestID object.
	 * 
	 * @param	manufacturerCode	the numerical code of the manufacturer requesting the certificate
	 * 								to be set for the CertificateRequestID object
	 */
	public void setManufacturerCode( ManufacturerCode manufacturerCode ) {
		this.manufacturerCode = manufacturerCode;
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		node.addContent( new Element( "requestSerialNumber" ).setText( Long.toString( requestSerialNumber ) ) );
		node.addContent( new Element( "requestMonthYear" ).setText( convertBCDStringIntoString( requestMonthYear ) ) );
		node.addContent( new Element( "crIdentifier" ).setText( convertIntoHexString( crIdentifier ) ) );
		node.addContent( manufacturerCode.generateXMLElement( "manufacturerCode" ) );

		return node;
	}
}
