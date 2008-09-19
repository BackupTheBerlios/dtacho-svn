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

package org.opendtacho.DDDQuery;

import org.opendtacho.DDDQuery.XMLDumper;

/**
 * The DataSource interface defines methods for a data source.
 */
public interface DataSource {
	/**
	 * Sets the byte array with the data of a DataSource object.
	 * <br><br>
	 * <b>Note</b>: the class that implements this interface must provide a method
	 * that processes the data and generates a XMLDumper object of it.
	 * 
	 * @param	src		the byte array with the data of the DataSource object
	 */
	public void setSource( byte[] src );
	
	/**
	 * Returns the XMLDumper object that dumps the data provided by {@link #setSource(byte[])}
	 * as XML.
	 * <br><br>
	 * <b>Note</b>: the class that implements this interface must provide a method
	 * that processes the data and generates a XMLDumper object of it.
	 * 
	 * @return	XMLDumper object that has been generated with the data
	 */
	public XMLDumper getXMLDumper();
}
