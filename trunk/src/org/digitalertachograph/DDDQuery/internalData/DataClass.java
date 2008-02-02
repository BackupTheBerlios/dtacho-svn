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

package org.digitalertachograph.DDDQuery.internalData;

import org.jdom.Element;

public abstract class DataClass {

	/**
	 * Copies the byte array <tt>value</tt> from position <tt>from</tt> with length
	 * <tt>length</tt> and returns the copy as new byte array.
	 * 
	 * @param	value	the source byte array
	 * @param	from	start position within the source byte array
	 * @param	length	number of bytes to be copied
	 * 
	 * @return	the copied byte array
	 */
	static protected byte[] arrayCopy( byte[] value, int from, int length ) {
		byte[] tmp = new byte[ length ];
		System.arraycopy( value, from, tmp, 0, length );
		return tmp;
	}

	/**
	 * 
	 * @param b
	 * @return
	 */
	static protected short convertIntoUnsigned1ByteInt( byte b ) {
		short s;
		s = (short)( b & 0xff );
		return s;
	}

	/**
	 * 
	 * @param b
	 * @return
	 */
	static protected short convertIntoUnsigned1ByteInt( byte[] b ) {
		return convertIntoUnsigned1ByteInt( b[0] );
	}
	
	/**
	 * 
	 * @param b
	 * @return
	 */
	static protected int convertIntoUnsigned2ByteInt( byte[] b ) {
		int i = 0;
		
		i += ( b[0] & 0xff ) << 8;
		i += ( b[1] & 0xff );

		return i;
	}
	
	/**
	 * 
	 * @param b
	 * @return
	 */
	static protected int convertIntoUnsigned3ByteInt( byte[] b ) {
		int i = 0;

		i += ( b[0] & 0xff ) << 16;
		i += ( b[1] & 0xff ) << 8;
		i += ( b[2] & 0xff );

		return i;
	}
	
	/**
	 * 
	 * @param b
	 * @return
	 */
	static protected long convertIntoUnsigned4ByteInt( byte[] b ) {
		long l = 0;
		//long accum;

		//accum |= ( (long)( b[i] & 0xff ) ) << shiftBy;
		
		long al = ( ( (long)b[0] ) & 0xff ) << 24 ;
		long bl = ( ( (long)b[1] ) & 0xff ) << 16 ;
		long cl = ( ( (long)b[2] ) & 0xff ) << 8 ;
		
		//long dl = ( ( (long)b[3] ) & 0xff ) << 0 ;
		int di =  ( b[3] & 0xff );
		//int a = 0xff;
		//byte c = (byte)a;
		long dl = ( (long)di ) << 0;
		
		
		l = al +  bl + cl + dl;

		return l;
	}
	
	/**
	 * 
	 * @param b
	 * @return
	 */
	static protected String convertBCDStringIntoString( byte[] b ) {
		String tmp = new String();
		
		for ( int i = 0; i < b.length; i++ ) {
			int hNibble, lNibble;
			hNibble = b[ i ] & 0xf0;
			hNibble = hNibble >> 4;
			hNibble += 0x30;

			lNibble = b[ i ] & 0x0f;
			lNibble += 0x30;

			if ( hNibble > 0x39 ) {
				hNibble = '_';
			}

			if ( lNibble > 0x39 ) {
				lNibble = '_';
			}
			
			tmp = tmp + (char)hNibble + (char)lNibble;
		}

		return tmp;
	}
	
    /**
     * 
     * @param	b
     * @return	hex string, lower case characters
     */
	static protected String convertIntoHexString( byte[] b ) {
	    char[] digits = {
	    	'0' , '1' , '2' , '3' , '4' , '5' , '6' , '7' , '8' , '9' ,
	    	'a' , 'b' , 'c' , 'd' , 'e' , 'f'   
	    };	

		String str = new String();

		for ( int ptr = 0; ptr < b.length; ptr++ ) {
			int i = b[ ptr ];
			char[] buf = new char[ 2 ];
			int charPos = 2;
			int radix = 1 << 4; // 1000(b)
			int mask = radix - 1; // 0111(b)
			for ( int c = 0; c < 2; c++ ) {
			    buf[ --charPos ] = digits[ i & mask ];
			    i >>>= 4;
			}

			str += new String( buf );
		}
		return str;
	}
	
	/**
	 * 
	 * @param b
	 * @return
	 */
	static protected String convertIntoHexString( byte b ) {
		return convertIntoHexString( new byte[]{ b } );
	}

	/**
	 * Generates a XML tree with the properties of the object.
	 * The given name is used as root name.
	 * 
	 * @param	name	the name of the root element
	 * 
	 * @return	the generated XML tree
	 */
	public abstract Element generateXMLElement( String name );
}
