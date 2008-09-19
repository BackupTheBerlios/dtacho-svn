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
 * Information, stored in a vehicle unit, related to the software installed.
 */
public class VuSoftwareIdentification extends DataClass {
	/*
	 * VuSoftwareIdentification ::= SEQUENCE {
	 * 	vuSoftwareVersion VuSoftwareVersion, 4 bytes
	 * 	vuSoftInstallationDate VuSoftInstallationDate, 4 bytes
	 * }
	 * ---
	 * VuSoftwareVersion ::= IA5String(SIZE(4)), 4 bytes
	 * ---
	 * VuSoftInstallationDate ::= TimeReal, 4 bytes
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 8;

	private VuSoftwareVersion vuSoftwareVersion;
	private VuSoftInstallationDate vuSoftInstallationDate;

	private DebugLogger debugLogger;
	
	
	/**
	 * Constructor for a VuSoftwareIdentification object
	 */
	public VuSoftwareIdentification() {
		vuSoftwareVersion = new VuSoftwareVersion();
		vuSoftInstallationDate = new VuSoftInstallationDate();
	}

	/**
	 * Constructor for a VuSoftwareIdentification object
	 * 
	 * @param	value	byte array of a VuSoftwareIdentification structure
	 * 					whose data is used when the VuSoftwareIdentification
	 * 					object is created.
	 */
	public VuSoftwareIdentification( byte[] value ) {
		debugLogger = new DebugLogger();

		vuSoftwareVersion = new VuSoftwareVersion( arrayCopy( value, 0, 4 ) );
		debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Vehicle unit software version: %s\n", vuSoftwareVersion.getVuSoftwareVersion() );

		debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Software installation date:" );
		vuSoftInstallationDate = new VuSoftInstallationDate( arrayCopy( value, 4, 4 ) );
	}

	/**
	 * Returns the software version number of the Vehicle Unit
	 * of a VuSoftwareIdentification object.
	 * 
	 * @return	the software version number of the Vehicle Unit
	 * 			of the VuSoftwareIdentification object
	 */
	public VuSoftwareVersion getVuSoftwareVersion() {
		return vuSoftwareVersion;
	}

	/**
	 * Sets the software version number of the Vehicle Unit of a VuSoftwareIdentification object.
	 * 
	 * @param	vuSoftwareVersion	the software version number of the Vehicle Unit
	 * 								to be set for the VuSoftwareIdentification object
	 */
	public void setVuSoftwareVersion( VuSoftwareVersion vuSoftwareVersion ) {
		this.vuSoftwareVersion = vuSoftwareVersion;
	}

	/**
	 * Returns the software version installation date of a VuSoftwareIdentification object.
	 * 
	 * @return	the software version installation date of the VuSoftwareIdentification object
	 */
	public VuSoftInstallationDate getVuSoftInstallationDate() {
		return vuSoftInstallationDate;
	}

	/**
	 * Sets the software version installation date of a VuSoftwareIdentification object.
	 * 
	 * @param	vuSoftInstallationDate	the software version installation date
	 * 									to be set for the VuSoftwareIdentification object
	 */
	public void setVuSoftInstallationDate( VuSoftInstallationDate vuSoftInstallationDate ) {
		this.vuSoftInstallationDate = vuSoftInstallationDate;
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		node.addContent( vuSoftwareVersion.generateXMLElement( "vuSoftwareVersion" ) );
		node.addContent( vuSoftInstallationDate.generateXMLElement( "vuSoftInstallationDate" ) );

		return node;
	}
}