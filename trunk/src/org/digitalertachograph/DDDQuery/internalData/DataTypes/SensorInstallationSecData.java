/*
    $Id:SensorInstallationSecData.java 26 2008-02-25 22:28:27Z deetee $

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

package org.digitalertachograph.DDDQuery.internalData.DataTypes;

/**
 * Information stored in a workshop card, related to the security data needed for
 * pairing motion sensors to vehicle units.
 */
public class SensorInstallationSecData extends TDesSessionKey {
	/*
	 * Information, stored in a workshop card, related to the security data needed for
	 * pairing motion sensors to vehicle units (requirement 214).
	 * 
	 * SensorInstallationSecData ::= TDesSessionKey
	 * 
	 * Value assignment: in accordance with ISO 16844-3.
	 * ---
	 * TDesSessionKey ::= SEQUENCE {
	 * 	tDesKeyA OCTET STRING (SIZE(8)), 8 bytes
	 * 	tDesKeyB OCTET STRING (SIZE(8)), 8 bytes
	 * }
	 */


	/**
	 * Constructor for a SensorInstallationSecData object
	 */
	public SensorInstallationSecData() {
		super();
	}

	/**
	 * Constructor for a SensorInstallationSecData object
	 * 
	 * @param	value	byte array of a SensorInstallationSecData structure
	 * 					whose data is used when the SensorInstallationSecData
	 * 					object is created.
	 */
	public SensorInstallationSecData( byte[] value ) {
		super( value );
	}
}
