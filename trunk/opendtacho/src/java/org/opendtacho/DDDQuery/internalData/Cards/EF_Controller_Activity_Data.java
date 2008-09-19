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
import org.opendtacho.DDDQuery.internalData.DataTypes.ControlCardControlActivityData;

/**
 * EF_Controller_Activity_Data,
 * 
 * Council Regulation (EEC) No 3821/85 File ID: 050C
 */
public class EF_Controller_Activity_Data extends DataClass {

	/**
	 * Size of structure in bytes.
	 * Only valid after instantiation of the EF_Controller_Activity_Data object.
	 */
	public final int size;

	private ControlCardControlActivityData controlCardControlActivityData;
	

	/**
	 * Constructor for an EF_Controller_Activity_Data object
	 * 
	 * @param	value	byte array of an EF_Controller_Activity_Data structure
	 * 					whose data is used when the EF_Controller_Activity_Data
	 * 					object is created.
	 */
	public EF_Controller_Activity_Data( byte[] value, int noOfControlActivityRecords ) {
		controlCardControlActivityData = new ControlCardControlActivityData( value, noOfControlActivityRecords );
		size = controlCardControlActivityData.size;
	}
	
	public Element generateXMLElement( String name ) {
		// discard name - this.getClass().getSimpleName() is unique!
		Element node = new Element( this.getClass().getSimpleName() );

		Element child = controlCardControlActivityData.generateXMLElement( "controlCardControlActivityData" );
		node.addContent( child );

		return node;
	}
}
