/*   Copyright (C) 2007-2008, Martin Barth

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

import org.digitalertachograph.DDDQuery.internalData.TachographData;

public interface DataSource {
	/**
	 * Sets the byte array with the data of a DataSource object.
	 * <br><br>
	 * <b>Note</b>: the class that implements this interface must provide a method
	 * that processes the data and generates a TachographData object of it.
	 * 
	 * @param	src		the byte array with the data of the DataSource object
	 */
	public void setSource(byte[] src);
	
	/**
	 * Returns the TachographData object that has been generated with the data
	 * that was set by {@link #setSource(byte[])}.
	 * <br><br>
	 * <b>Note</b>: the class that implements this interface must provide a method
	 * that processes the data and generates a TachographData object of it.
	 * 
	 * @return	TachographData object that has been generated with the data
	 */
	public TachographData getTachographData();
}
