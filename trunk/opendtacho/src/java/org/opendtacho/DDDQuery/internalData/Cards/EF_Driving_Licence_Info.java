/*
    $Id$

    Copyright (C) 2007-2008, Martin Barth, Gerald Schnabel

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

package org.opendtacho.DDDQuery.internalData.Cards;

import org.jdom.Element;
import org.opendtacho.DDDQuery.internalData.DataClass;
import org.opendtacho.DDDQuery.internalData.DataTypes.CardDrivingLicenceInformation;

/**
 * EF_Driving_Licence_Info,
 * 
 * Council Regulation (EEC) No 3821/85 File ID: 0521
 */
public class EF_Driving_Licence_Info extends DataClass {

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = CardDrivingLicenceInformation.size;

	private CardDrivingLicenceInformation cardDrivingLicenceInformation;


	/**
	 * Constructor for an EF_Driving_Licence_Info object
	 * 
	 * @param	value	byte array of an EF_Driving_Licence_Info structure
	 * 					whose data is used when the EF_Driving_Licence_Info
	 * 					object is created.
	 */
	public EF_Driving_Licence_Info( byte[] value ) {
		cardDrivingLicenceInformation = new CardDrivingLicenceInformation( value );
	}

	public Element generateXMLElement( String name ) {
		// discard name - this.getClass().getSimpleName() is unique!
		Element node = new Element( this.getClass().getSimpleName() );

		Element child = cardDrivingLicenceInformation.generateXMLElement( "cardDrivingLicenceInformation" );
		node.addContent( child );

		return node;
	}
}
