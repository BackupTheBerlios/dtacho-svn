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
import org.opendtacho.DDDQuery.internalData.DataTypes.EquipmentType;
import org.opendtacho.DDDQuery.internalData.DataTypes.LastCardDownload;
import org.opendtacho.DDDQuery.internalData.DataTypes.NoOfCalibrationsSinceDownload;

/**
 * EF_Card_Download,
 * 
 * Council Regulation (EEC) No 3821/85 File ID: 050E (driver card), File ID: 0509 (workshop card)
 */
public class EF_Card_Download extends DataClass {

	private int cardType;
	private LastCardDownload lastCardDownload;
	private NoOfCalibrationsSinceDownload noOfCalibrationsSinceDownload;

	/**
	 * Constructor for an EF_Card_Download object
	 * 
	 * @param	value	byte array of an EF_Card_Download structure
	 * 					whose data is used when the EF_Card_Download
	 * 					object is created.
	 */
	public EF_Card_Download( byte[] value, int cardType ) {

		this.cardType = cardType; 

		switch ( cardType ) {
			case EquipmentType.DRIVER_CARD :
				lastCardDownload = new LastCardDownload( value );
				break;
				
			case EquipmentType.WORKSHOP_CARD :
				noOfCalibrationsSinceDownload = new NoOfCalibrationsSinceDownload( value );
				break;

			default :
				break;
		}
	}

	public Element generateXMLElement( String name ) {
		// discard name - this.getClass().getSimpleName() is unique!
		Element node = new Element( this.getClass().getSimpleName() );

		Element child;

		switch ( cardType ) {
			case EquipmentType.DRIVER_CARD :
				child = lastCardDownload.generateXMLElement( "lastCardDownload" );
				break;
			
			case EquipmentType.WORKSHOP_CARD :
				child = noOfCalibrationsSinceDownload.generateXMLElement( "noOfCalibrationsSinceDownload" );
				break;

			default:
				child = new Element( "default" );
				break;
		}

		node.addContent( child );

		return node;
	}
}
