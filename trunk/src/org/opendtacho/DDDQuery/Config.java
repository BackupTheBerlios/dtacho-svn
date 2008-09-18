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

package org.opendtacho.DDDQuery;

import org.opendtacho.DDDQuery.DDDDataSource.tagInfoOutput;

/**
 * Class for configuration of the format converter.
 */
public class Config {

	private static boolean configureFromEnv = true;
	private static tagInfoOutput tagInfo;
	private static int hexDumpWidth = 16;
	private static String eurPKPath = new String();
	private static boolean noDetailedSpeedData = false;

	/**
	 * Indicates if configuration is read from environment variables.
	 * 
	 * @return	true if configuration is read from environment variables
	 */
	public static boolean getConfigureFromEnv() {
		return configureFromEnv;
	}

	/**
	 * Sets that configuration is read from environment variables
	 * 
	 * @param	pConfigureFromEnv	sets that configuration is read
	 * 								from environment variables
	 */
	public static void setConfigureFromEnv( boolean pConfigureFromEnv ) {
		configureFromEnv = pConfigureFromEnv;
	}

	/**
	 * Returns the taginfo value for DDDDataSource.
	 * 
	 * @return		the taginfo value for DDDDataSource
	 */
	public static tagInfoOutput getTagInfo() {
		if ( configureFromEnv == true ) {
			tagInfo = tagInfoOutput.TAGLENGTH;
			String envTagInfo = System.getenv( "TAGINFO" );

			if ( ( envTagInfo != null ) && ( envTagInfo.length() > 0 ) ) {
				if ( envTagInfo.toUpperCase().compareTo( "TAG" ) == 0 ) {
					tagInfo = tagInfoOutput.TAG;
				}

				if ( envTagInfo.toUpperCase().compareTo( "TAGLENGTH" ) == 0 ) {
					tagInfo = tagInfoOutput.TAGLENGTH;
				}

				if ( envTagInfo.toUpperCase().compareTo( "TAGVALUE" ) == 0 ) {
					tagInfo = tagInfoOutput.TAGVALUE;
				}
			}

			return tagInfo;
		}
		else {
			return tagInfo;
		}
	}

	/**
	 * Sets the taginfo value for DDDDataSource.
	 * 
	 * @param	pTagInfo	the taginfo value for DDDDataSource
	 */
	public static void setTagInfo( tagInfoOutput pTagInfo ) {
		tagInfo = pTagInfo;
	}

	/**
	 * Returns the hexdump width for DDDDataSource.
	 * 
	 * @return		the hexdump width for DDDDataSource
	 */
	public static int getHexDumpWidth() {
		if ( configureFromEnv == true ) {
			hexDumpWidth = 16;
			String envHexDumpWidth = System.getenv( "HEXDUMPWIDTH" );

			if ( ( envHexDumpWidth != null ) && ( envHexDumpWidth.length() > 0 ) ) {
				int hexDumpWidthTemp = Integer.parseInt( envHexDumpWidth, 10 );

				if ( ( hexDumpWidthTemp >= 16 ) && ( hexDumpWidthTemp <= 64 ) ) {
					hexDumpWidth = hexDumpWidthTemp;
				}
			}

			return hexDumpWidth;
		}
		else {
			return hexDumpWidth;
		}
	}

	/**
	 * Sets the hexdump width for DDDDataSource
	 * 
	 * @param	pHexDumpWidth	the hexdump width for DDDDataSource
	 */
	public static void setHexDumpWidth( int pHexDumpWidth ) {
		hexDumpWidth = pHexDumpWidth;
	}

	/**
	 * Returns the path of the European Public Key for DTCODataClass.
	 * 
	 * @return		the path of the European Public Key for DTCODataClass
	 */
	public static String getEURPKPath() { 
		if ( configureFromEnv == true ) {
			eurPKPath = new String();
			String envEURPKPath = System.getenv( "EURPKPATH" );

			if ( ( envEURPKPath != null ) && ( envEURPKPath.length() > 0 ) ) {
				if ( envEURPKPath.charAt( envEURPKPath.length() - 1 ) != '/' ) {
					envEURPKPath = envEURPKPath + "/";
				}

				eurPKPath = envEURPKPath;
			}

			return eurPKPath;
		}
		else {
			return eurPKPath;
		}
	}

	/**
	 * Sets the path of the European Public Key for DTCODataClass.
	 * 
	 * @param	pEURPKPath	the path of the European Public Key
	 */
	public static void setEURPKPath( String pEURPKPath ) {
		eurPKPath = pEURPKPath;
	}

	/**
	 * Indicates if detailed speed data should not be dumped to XML for
	 * VuDetailedSpeedData.
	 * 
	 * @return	true if detailed speed data is not dumped to XML
	 */
	public static boolean getNoDetailedSpeedData() {
		if ( configureFromEnv == true ) {
			noDetailedSpeedData = false;
			String envNoDetailedSpeedData = System.getenv( "NODETAILEDSPEEDDATA" );

			if ( ( envNoDetailedSpeedData != null ) && ( envNoDetailedSpeedData.length() > 0 ) ) {
				if ( envNoDetailedSpeedData.toLowerCase().compareTo( "true" ) == 0 ) {
					noDetailedSpeedData = true;
				}
			}

			return noDetailedSpeedData;
		}
		else {
			return noDetailedSpeedData;
		}
	}

	/**
	 * Sets if detailed speed data should not be dumped to XML for
	 * VuDetailedSpeedData.
	 * 
	 * @param	pNoDetailedSpeedData	indicates if detailed speed data
	 * 									should not be dumped to XML
	 */
	public static void setNoDetailedSpeedData( boolean pNoDetailedSpeedData ) {
		noDetailedSpeedData = pNoDetailedSpeedData;
	}
}
