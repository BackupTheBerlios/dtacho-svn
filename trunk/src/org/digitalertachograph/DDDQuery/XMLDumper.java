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

/**
 * The XMLDumper interface defines methods for dumping XML files.
 */
public interface XMLDumper {

	/**
	 * Sets the XMLInfo object that contains the information of an
	 * Info section for a dumped XML file.
	 * 
	 * @param	xmlInfo		the XMLInfo object to be set.
	 */
	public abstract void setXMLInfo( XMLInfo xmlInfo );

	/**
	 * Dumps the collected tags as XML and returns it as string.
	 * 
	 * @return		the dumped XML string
	 */
	public abstract String generateXML();

	/**
	 * Dumps the collected tags as XML file.
	 * 
	 * @param	filename		name of the XML file
	 */
	public abstract void generateXML( String filename );
}
