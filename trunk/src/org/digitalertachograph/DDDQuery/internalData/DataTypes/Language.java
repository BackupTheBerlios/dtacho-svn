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
 * Code identifying a language.
 */
public class Language extends DataClass {
	/* 
	 * Language ::= IA5String(SIZE(2)), 2 bytes
	 * Value assignment: Two-letter lower-case coding according to ISO 639.
	 */

	private String language;
	
	
	/**
	 * Constructor for a Language object
	 */
	public Language() {
		language = new String();
	}

	/**
	 * Constructor for a Language object
	 * 
	 * @param	value	byte array of a Language structure
	 * 					whose data is used when the Language
	 * 					object is created.
	 */
	public Language( byte[] value ){
		language = new String( value );
	}

	/**
	 * Returns the code identifying the language of a Language object.
	 * 
	 * @return	the code identifying the language	of the Language object
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * Sets the code identifying the language of a Language object.
	 * 
	 * @param	language	the code identifying the language to be set for the Language object
	 */
	public void setLanguage( String language ) {
		this.language = language;
	}
	
	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );
		node.addContent( new Element( "language" ).setText( language ) );

		return node;
	}
}
