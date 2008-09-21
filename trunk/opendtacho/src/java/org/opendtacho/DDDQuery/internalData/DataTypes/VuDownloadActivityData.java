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
import org.opendtacho.DDDQuery.DebugLogger;
import org.opendtacho.DDDQuery.internalData.DataClass;

/**
 * Information, stored in a vehicle unit, related to its last download.
 */
public class VuDownloadActivityData extends DataClass {
	/*
	 * VuDownloadActivityData ::= SEQUENCE {
	 * 	downloadingTime TimeReal, 4 bytes
	 * 	fullCardNumber FullCardNumber, 18 bytes
	 * 	companyOrWorkshopName Name, 18 bytes
	 * }
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 40;

	private TimeReal downloadingTime;
	private FullCardNumber fullCardNumber;
	private Name companyOrWorkshopName;

	private DebugLogger debugLogger;


	/**
	 * Constructor for a VuDownloadActivityData object
	 */
	public VuDownloadActivityData() {
		downloadingTime = new TimeReal();
		fullCardNumber = new FullCardNumber();
		companyOrWorkshopName = new Name();
	}

	/**
	 * Constructor for a VuDownloadActivityData object
	 * 
	 * @param	value	byte array of a VuDownloadActivityData structure
	 * 					whose data is used when the VuDownloadActivityData
	 * 					object is created.
	 */
	public VuDownloadActivityData( byte[] value ) {
		debugLogger = new DebugLogger();

		debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Downloading time:" );
		downloadingTime = new TimeReal( arrayCopy( value, 0, 4 ) );

		fullCardNumber = new FullCardNumber( arrayCopy( value, 4, 18 ) );
		debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Card used to authorize the download: %s\n", fullCardNumber.getCardNumber().getOwnerIdentification() );

		companyOrWorkshopName = new Name( arrayCopy( value, 22, 36 ) );
		debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Company or workshop name: %s\n", companyOrWorkshopName.getNameString() );
	}

	/**
	 * Returns the date and time of downloading of a VuDownloadActivityData object.
	 * 
	 * @return	the date and time of downloading of the VuDownloadActivityData object
	 */
	public TimeReal getDownloadingTime() {
		return downloadingTime;
	}

	/**
	 * Sets the date and time of downloading of a VuDownloadActivityData object.
	 * 
	 * @param	downloadingTime		the date and time of downloading to be set
	 * 								for the VuDownloadActivityData object
	 */
	public void setDownloadingTime( TimeReal downloadingTime ) {
		this.downloadingTime = downloadingTime;
	}

	/**
	 * Returns the identification of the card used to authorise the download
	 * of a VuDownloadActivityData object.
	 * 
	 * @return	the identification of the card used to authorise the download
	 * 			of the VuDownloadActivityData object
	 */
	public FullCardNumber getFullCardNumber() {
		return fullCardNumber;
	}

	/**
	 * Sets the identification of the card used to authorise the download
	 * of a VuDownloadActivityData object.
	 * 
	 * @param	fullCardNumber	the identification of the card used to authorise the download
	 * 							to be set for the VuDownloadActivityData object
	 */
	public void setFullCardNumber( FullCardNumber fullCardNumber ) {
		this.fullCardNumber = fullCardNumber;
	}

	/**
	 * Returns the company or workshop name of a VuDownloadActivityData object.
	 * 
	 * @return	the company or workshop name of the VuDownloadActivityData object
	 */
	public Name getCompanyOrWorkshopName() {
		return companyOrWorkshopName;
	}

	/**
	 * Sets the company or workshop name of a VuDownloadActivityData object.
	 * 
	 * @param	companyOrWorkshopName	the company or workshop name to be set
	 * 									for the VuDownloadActivityData object
	 */
	public void setCompanyOrWorkshopName( Name companyOrWorkshopName ) {
		this.companyOrWorkshopName = companyOrWorkshopName;
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		node.addContent( downloadingTime.generateXMLElement( "downloadingTime" ) );
		node.addContent( fullCardNumber.generateXMLElement( "fullCardNumber" ) );
		node.addContent( companyOrWorkshopName.generateXMLElement( "companyOrWorkshopName" ) );

		return node;
	}
}
