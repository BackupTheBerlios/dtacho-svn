/*   Copyright (C) 2007-2008, Martin Barth, Gerald Schnabel

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
 * Information, stored in a driver card, related to the card holder driver licence data.
 */
public class CardDrivingLicenceInformation extends DataClass {
	/*
	 * CardDrivingLicenceInformation ::= SEQUENCE {
	 * 	drivingLicenceIssuingAuthority Name, 36 bytes
	 * 	drivingLicenceIssuingNation NationNumeric, 1 byte 
	 * 	drivingLicenceNumber IA5String(SIZE(16)), 16 bytes
	 * }
	 * ---
	 * NationNumeric ::= INTEGER(0.255)
	 */
	
	private Name drivingLicenceIssuingAuthority;
	private NationNumeric drivingLicenceIssuingNation;
	private String drivingLicenceNumber;

	/**
	 * Constructor for a CardDrivingLicenceInformation object
	 */
	public CardDrivingLicenceInformation() {
		drivingLicenceIssuingAuthority = new Name();
		drivingLicenceIssuingNation = new NationNumeric();
		drivingLicenceNumber = new String();
	}

	/**
	 * Constructor for a CardDrivingLicenceInformation object
	 * 
	 * @param	value	byte array of a CardDrivingLicenceInformation structure
	 * 					whose data is used when the CardDrivingLicenceInformation
	 * 					object is created.
	 */
	public CardDrivingLicenceInformation( byte[] value ) {
		drivingLicenceIssuingAuthority = new Name( arrayCopy( value, 0, 36 ) );
		drivingLicenceIssuingNation = new NationNumeric( value[ 36 ] );
		drivingLicenceNumber = new String( arrayCopy( value, 37, 16 ) );
	}

	/**
	 * Returns the authority responsible for issuing the driving licence
	 * of a CardDrivingLicenceInformation object.
	 * 
	 * @return	the authority responsible for issuing the driving licence
	 * 			of the CardDrivingLicenceInformation object
	 */
	public Name getDrivingLicenceIssuingAuthority() {
		return drivingLicenceIssuingAuthority;
	}

	/**
	 * Sets the authority responsible for issuing the driving licence
	 * of a CardDrivingLicenceInformation object.
	 * 
	 * @param	drivingLicenceIssuingAuthority		the authority responsible for issuing
	 * 												the driving licence to be set for the
	 * 												CardDrivingLicenceInformation object
	 */
	public void setDrivingLicenceIssuingAuthority( Name drivingLicenceIssuingAuthority ) {
		this.drivingLicenceIssuingAuthority = drivingLicenceIssuingAuthority;
	}

	/**
	 * Returns the nationality of the authority that issued the driving licence
	 * of a CardDrivingLicenceInformation object.
	 * 
	 * @return	the nationality of the authority that issued the driving licence
	 * 			of the CardDrivingLicenceInformation object
	 */
	public NationNumeric getDrivingLicenceIssuingNation() {
		return drivingLicenceIssuingNation;
	}

	/**
	 * Sets the nationality of the authority that issued the driving licence
	 * of a CardDrivingLicenceInformation object.
	 * 
	 * @param	drivingLicenceIssuingNation		the nationality of the authority that
	 * 											issued the driving licence to be set for the
	 * 											CardDrivingLicenceInformation object
	 */
	public void setDrivingLicenceIssuingNation( NationNumeric drivingLicenceIssuingNation ) {
		this.drivingLicenceIssuingNation = drivingLicenceIssuingNation;
	}

	/**
	 * Returns the number of the driving licence of a CardDrivingLicenceInformation object.
	 * 
	 * @return	the number of the driving licence of the CardDrivingLicenceInformation object
	 */
	public String getDrivingLicenceNumber() {
		return drivingLicenceNumber;
	}

	/**
	 * Sets number of the driving licence  of a CardDrivingLicenceInformation object.
	 * 
	 * @param	drivingLicenceNumber		number of the driving licence to be set for the
	 * 										CardDrivingLicenceInformation object
	 */
	public void setDrivingLicenceNumber( String drivingLicenceNumber ) {
		this.drivingLicenceNumber = drivingLicenceNumber;
	}
	
	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );
		node.addContent( drivingLicenceIssuingAuthority.generateXMLElement( "drivingLicenceIssuingAuthority" ) );
		node.addContent( drivingLicenceIssuingNation.generateXMLElement( "drivingLicenceIssuingNation" ) );

		Element drivingLicenceNumberElement = new Element( "drivingLicenceNumber" );
		drivingLicenceNumberElement.setText( drivingLicenceNumber );
		node.addContent( drivingLicenceNumberElement );
		
		return node;
	}
}
