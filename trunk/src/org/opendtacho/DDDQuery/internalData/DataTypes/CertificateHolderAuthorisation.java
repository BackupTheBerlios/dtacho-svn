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
 * Identification of the rights of a certificate holder.
 */
public class CertificateHolderAuthorisation extends DataClass {
	/*
	 * CertificationHolderAuthorisation ::= SEQUENCE {
	 * 	tachographApplicationID OCTET STRING(SIZE(6)), 6 bytes
	 * 	equipmentType EquipmentType, 1 byte
	 * }
	 * ---
	 * EquipmentType ::= INTEGER(0..255), 1 byte
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 7;

	private byte[] tachographApplicationID;
	private EquipmentType equipmentType;

	/**
	 * Constructor for a CertificateHolderAuthorisation object
	 */
	public CertificateHolderAuthorisation() {
		tachographApplicationID = new byte[ 6 ];
		equipmentType = new EquipmentType();
	}

	/**
	 * Constructor for a CertificateHolderAuthorisation object
	 * 
	 * @param	value	byte array of a CertificateHolderAuthorisation structure
	 * 					whose data is used when the CertificateHolderAuthorisation
	 * 					object is created.
	 */
	public CertificateHolderAuthorisation( byte[] value ) {
		tachographApplicationID = arrayCopy( value, 0, 6 );
		equipmentType = new EquipmentType( value[ 6 ] );
	}

	/**
	 * Returns the application identifier for the tachograph application
	 * of a CertificateHolderAuthorisation object as byte array.
	 * 
	 * @return	the application identifier for the tachograph application
	 * 			of the CertificateHolderAuthorisation object
	 */
	public byte[] getTachographApplicationID() {
		return tachographApplicationID;
	}

	/**
	 * Sets the application identifier for the tachograph application
	 * of a CertificateHolderAuthorisation object.
	 * 
	 * @param	value	the application identifier for the tachograph application
	 * 						to be set for the CertificateHolderAuthorisation object
	 */
	public void setTachographApplicationID( byte[] value ) {
		tachographApplicationID = arrayCopy( value, 0, 6 );
	}

	/**
	 * Returns the identification of the type of equipment to which the
	 * certificate is intended of a CertificateHolderAuthorisation object.
	 * 
	 * @return	the identification of the type of equipment to which the certificate
	 * 			is intended of the CertificateHolderAuthorisation object
	 */
	public EquipmentType getEquipmentType() {
		return equipmentType;
	}

	/**
	 * Sets the identification of the type of equipment to which the
	 * certificate is intended of a CertificateHolderAuthorisation object.
	 * 
	 * @param	equipmentType	the identification of the type of equipment to which the certificate
	 * 							is intended to be set for the CertificateHolderAuthorisation object
	 */
	public void setEquipmentType( EquipmentType equipmentType ) {
		this.equipmentType = equipmentType;
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		node.addContent( new Element( "tachographApplicationID" ).setText( convertIntoHexString( tachographApplicationID ) ) );
		node.addContent( equipmentType.generateXMLElement( "equipmentType" ) );

		return node;
	}
}
