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

package org.digitalertachograph.DDDQuery;

import java.io.File;

import org.digitalertachograph.DDDQuery.internalData.DataClass;
import org.jdom.Element;

/**
 * Class that provides the elements for an Info section in a XML file.
 */
public class XMLInfo extends DataClass {

	private static String sourceFilename;
	private static boolean dataValidatedWithEURPK;

	/**
	 * Constructor for a XMLInfo object.
	 */
	public XMLInfo() {

	}

	/**
	 * Returns the source file name of a XMLInfo object.
	 * 
	 * @return	the source file name of the XMLInfo object
	 */
	public static String getSourceFilename() {
		return sourceFilename;
	}

	/**
	 * Sets the source file name of a XMLInfo object.
	 * 
	 * @param	sourceFile		the source file name to be set for the XMLInfo object
	 */
	public static void setSourceFilename( String sourceFile ) {
		sourceFilename = sourceFile;
	}

	/**
	 * Indicates if the data is validated with the European Public Key
	 * of a XMLInfo object.
	 * 
	 * @return	true if the data is validated with the European Public Key
	 */
	public static boolean isDataValidatedWithEURPK() {
		return dataValidatedWithEURPK;
	}

	/**
	 * Sets the status of validating data with the European Public Key
	 * of a XMLInfo object.
	 * 
	 * @param	dataValidated		the status of validating data with the European Public Key
	 */
	public static void setDataValidatedWithEURPK( boolean dataValidated ) {
		dataValidatedWithEURPK = dataValidated;
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		node.addContent( new Element( "source" ).setText( new File( sourceFilename ).getName() ) );
		node.addContent( new Element( "dataValidatedWithEURPK" ).setText( Boolean.toString( dataValidatedWithEURPK ) ) );

		return node;
	}
}
