/*
    $Id:EF_Control_Activity_Data.java 26 2008-02-25 22:28:27Z deetee $

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

package org.digitalertachograph.DDDQuery.internalData.Cards;

import org.digitalertachograph.DDDQuery.internalData.DataClass;
import org.digitalertachograph.DDDQuery.internalData.DataTypes.CardControlActivityDataRecord;
import org.jdom.Element;

/**
 * EF_Control_Activity_Data,
 * 
 * Council Regulation (EEC) No 3821/85 File ID: 0508
 */
public class EF_Control_Activity_Data extends DataClass {

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = CardControlActivityDataRecord.size;

	private CardControlActivityDataRecord cardControlActivityDataRecord;

	/**
	 * Constructor for an EF_Control_Activity_Data object
	 * 
	 * @param	value	byte array of an EF_Control_Activity_Data structure
	 * 					whose data is used when the EF_Control_Activity_Data
	 * 					object is created.
	 */
	public EF_Control_Activity_Data( byte[] value ) {
		cardControlActivityDataRecord = new CardControlActivityDataRecord( value );
	}
	
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		Element child = cardControlActivityDataRecord.generateXMLElement( "cardControlActivityDataRecord" );
		node.addContent( child );

		return node;
	}
}
