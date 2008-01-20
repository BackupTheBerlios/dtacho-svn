/*   Copyright (C) 2008, Gerald Schnabel

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

package org.digitalertachograph.DDDQuery.internalData;

/**
 * The ByteArrayTools class provides methods to convert
 * byte arrays to hex strings and vice versa.
 */
public class ByteArrayTools {
	
	/**
	 * Constructor for a ByteArrayTools object
	 */
	public ByteArrayTools() {

	}
	 

	/**
	 * Converts a hex string to its corresponding byte array
	 * 
	 * @param	hexString	the hex string to be converted to a byte array
	 *
	 * @return	byte array representation of the hex string
	 */
	public byte[] HexStringToByteArray( String hexString ) {
		if ( ( hexString.length() % 2 ) != 0 )
			return null;

		byte[] byteArray = new byte[ hexString.length() / 2 ];

		for ( int i = 0; i < byteArray.length; i++ ) {
			byteArray[ i ] = (byte)Integer.parseInt( hexString.substring( 2*i, 2*i+2 ), 16 );
		}
		
		return byteArray;
	}

	/**
	 * Converts a byte array to its corresponding hex string
	 * 
	 * @param 	byteArray	the byte array to be converted to a hex string
	 * 
	 * @return	hex string representation of the byte array
	 */
	public String ByteArrayToHexString( byte[] byteArray ) {
	    if ( ( byteArray == null ) || ( byteArray.length <= 0 ) )
	        return null;

	    String hexchar[] = { "0", "1", "2", "3",
	    					 "4", "5", "6", "7",
	    					 "8", "9", "A", "B",
	    					 "C", "D", "E", "F" };

	    StringBuffer out = new StringBuffer( byteArray.length * 2 );
	    int i = 0; 
	    byte hchr;

	    while ( i < byteArray.length ) {
	        hchr = (byte)( ( byteArray[ i ] >> 4 ) & 0x0f );
	        out.append( hexchar[ (int)hchr ] );

	        hchr = (byte)( byteArray[ i ] & 0x0f ); 
	        out.append( hexchar[ (int)hchr ] );

	        i++;
	    }

		return new String( out );
	}
}
