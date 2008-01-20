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
 * Key identifier of a certification authority. 
 */
public class CertificationAuthority {

	private byte[] authorityIdentification;
	private byte keySerialNumber;
	private byte[] additionalInfo;
	private byte identifier;

	/**
	 * Constructor for a CertificationAuthority object
	 *
	 * @param	certificationAuthority	byte array of a CertificationAuthority structure
	 * 									whose data is used when the CertificationAuthority
	 * 									object is created.
	 */
	public CertificationAuthority( byte[] certificationAuthority ) {
		authorityIdentification = new byte[ 4 ];
		additionalInfo = new byte[ 2 ];

		System.arraycopy( certificationAuthority, 0, authorityIdentification, 0, 4 );
		keySerialNumber = certificationAuthority[ 4 ];
		System.arraycopy( certificationAuthority, 5, additionalInfo, 0, 2 );
		identifier = certificationAuthority[ 7 ];
	}

	/**
	 * Returns the certification authority structure of a CertificationAuthority object.
	 * 
	 * @return	the certification authority structure of the CertificationAuthority object.
	 */
	public byte[] getAuthorityIdentification() {
		return authorityIdentification;
	}

	/**
	 * Returns the nation numerical code of the authority information
	 * of a CertificationAuthority object.
	 * 
	 * @return	the nation numerical code of the CertificationAuthority object.
	 */
	public byte getNationNumericalCode() {
		return authorityIdentification[ 0 ];
	}

	/**
	 * Returns the nation alphanumerical code of the authority information
	 * of a CertificationAuthority object.
	 * 
	 * @return	the nation alphanumerical code of the CertificationAuthority object.
	 */
	public byte[] getNationAlphanumericalCode() {
		byte[] NationAlphanumericalCode = new byte[ 3 ];

		System.arraycopy( authorityIdentification, 1, NationAlphanumericalCode, 0, 3 );
		return NationAlphanumericalCode;
	}

	/**
	 * Returns the key serial number of a CertificationAuthority object.
	 * 
	 * @return	the key serial number of the CertificationAuthority object.
	 */
	public byte getKeySerialNumber() {
		return keySerialNumber;
	}

	/**
	 * Returns the additional info of a CertificationAuthority object.
	 * 
	 * @return	the additional info of the CertificationAuthority object.
	 */
	public byte[] getAdditionalInfo () {
		return additionalInfo ;
	}

	/**
	 * Returns the identifier of a CertificationAuthority object.
	 * 
	 * @return	the identifier of the CertificationAuthority object.
	 */
	public byte getIdentifier() {
		return identifier;
	}
}
