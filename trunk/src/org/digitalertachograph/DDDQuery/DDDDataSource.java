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

package org.digitalertachograph.DDDQuery;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;

import org.digitalertachograph.DDDQuery.internalData.Cards.CardData;
import org.digitalertachograph.DDDQuery.internalData.VehicleUnit.VehicleUnitData;

/**
 * A DDD data source.
 */
public class DDDDataSource implements DataSource {

	short srcType;
	private byte[] src;
	private String srcfile;
	private CardData cardData;
	private VehicleUnitData vehicleUnitData;
	private XMLDumper xmlDumper;

	private DebugLogger debugLogger;

	enum tagInfoOutput { TAG, TAGLENGTH, TAGVALUE };

	/**
	 * Source type: Card
	 */
	public final static short SRC_TYPE_CARD = 0;
	
	/**
	 * Source type: Vehicle unit
	 */
	public final static short SRC_TYPE_VU = 1;

	/**
	 * Constructor for a DDDDataSource object
	 */
	public DDDDataSource() {
		srcType = SRC_TYPE_CARD;
		src = null;
		srcfile = new String();
		cardData = new CardData();
		vehicleUnitData = new VehicleUnitData();
		xmlDumper = null;
		debugLogger = new DebugLogger();
	}

	/**
	 * Returns the source type, see ({@link DDDDataSource#SRC_TYPE_CARD}, {@link DDDDataSource#SRC_TYPE_VU}).
	 */
	public short getSrcType() {
		return srcType;
	}

	/**
	 * Sets the source type, see ({@link DDDDataSource#SRC_TYPE_CARD}, {@link DDDDataSource#SRC_TYPE_VU}).
	 */
	public void setSrcType( short srcType ) {
		this.srcType = srcType;
	}

	/**
	 * Sets the source byte array with data of a .ddd file.
	 * The byte array will be parsed and a XMLDumper object
	 * will be created internally. Depending on the data the
	 * XMLDumper object will be a {@link CardData} or a
	 * {@link VehicleUnitData} object.
	 * The created object can then be copied with the
	 * {@link #getXMLDumper()} method.
	 *
	 * @param	src		byte array with data of a .ddd file
	 */
	public void setSource( byte[] src ) {
		this.src = src;
	}

	/**
	 * Processes the source set by {@link #setSource} as the type of data
	 * set by {@link #setSrcType}.
	 * 
	 * @return	true, if processing was successful
	 */
	public boolean processSource() {
		return readSource( src );
	}

	/**
	 * Sets the source .ddd file. The file will be parsed and a
	 * XMLDumper object will be created internally. Depending on
	 * the data the XMLDumper object will be a {@link CardData}
	 * or a {@link VehicleUnitData} object.
	 * The created object can then be copied with the
	 * {@link #getXMLDumper()} method.
	 *
	 * @param	srcfile		the location of the .ddd file that will be processed
	 */
	public void setSourceFile( String srcfile ) {
		this.srcfile = srcfile;
	}

	/**
	 * processes the source file set by setSourceFile()
	 */
	public boolean processSourceFile() {
		return readSourceFile();
	}

	/**
	 * Parses the byte array given by src. The type of data
	 * 
	 * @param	src		byte array with the source data (card or vehicle unit data)
	 */
	private boolean readSource( byte[] src ) {
		if ( srcType == SRC_TYPE_CARD ) {
			// card data parser

			debugLogger.println( DebugLogger.LOGLEVEL_INFO, "[INFO] parsing card data" );

			boolean parseresult = false;

			cardData = new CardData();
			xmlDumper = cardData;

			cardData.initECPublicKey();

			int pos = 0;

			// data parser
			while ( true ) {
				byte[] tag = new byte[ 3 ];
				byte[] length = new byte[ 2 ];
				int length_i = 0;

				byte[] value;

				// tag parser
				while ( true ) {
					if ( src.length < pos + 3 ) {
						// end of stream
						// break tag parser
						break;
					}

					// copy tag
					System.arraycopy( src, pos, tag, 0, 3 );
					pos += 3;

					if ( cardData.isValidFileID( tag ) == false ) {
						if ( Arrays.equals( new byte[]{ tag[ 0 ], tag[ 1 ] }, new byte[]{ 0x76,0x06 } ) ) {
							// valid tag

							// OPTAC download tools with firmware < v2.3 write two bytes (76 06, SID/TREP?!) at the
							// beginning of a .DDD file that are out of specs...
							debugLogger.println( DebugLogger.LOGLEVEL_INFO, " [INFO] data 76 06 (SID/TREP?!, OPTAC FW < v2.3 ) found, skipping..." );
							pos -= 1;
						}
						else {
							// invalid tag
							debugLogger.printf( DebugLogger.LOGLEVEL_ERROR, "[ERROR] invalid tag, %02x %02x %02x\n", tag[ 0 ], tag[ 1 ], tag[ 2 ] );
							pos -= 2;
						}
					}
					else {
						parseresult = true;
						// break tag parser
						break;
					}
				}// end tag parser

				if ( parseresult == false ) {
					// break data parser
					break;
				}

				if ( src.length < pos + 2 ) {
					// break data parser
					break;
				}

				// copy length
				System.arraycopy( src, pos, length, 0, 2 );
				pos += 2;

				length_i = calculateLength( length );
				if ( src.length < pos + length_i ){
					parseresult = false;
					// break data parser
					break;
				}

				// copy value
				value = new byte[ length_i ];
				System.arraycopy( src, pos, value, 0, length_i );
				pos += length_i;

				// add data
				parseresult = cardData.add( tag, length, value );
				if ( parseresult == false ) {
					// break data parser
					break;
				}
			}// end data parser

			if ( parseresult == true ) {
				debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, "internal tag structure:" );

				tagInfoOutput tagInfo = tagInfoOutput.TAGLENGTH;
				String tagInfoEnv = System.getenv( "TAGINFO" );

				if ( ( tagInfoEnv != null ) && ( tagInfoEnv.length() > 0 ) ) {
					if ( tagInfoEnv.toUpperCase().compareTo( "TAG" ) == 0 ) {
						tagInfo = tagInfoOutput.TAG;
					}

					if ( tagInfoEnv.toUpperCase().compareTo( "TAGLENGTH" ) == 0 ) {
						tagInfo = tagInfoOutput.TAGLENGTH;
					}

					if ( tagInfoEnv.toUpperCase().compareTo( "TAGVALUE" ) == 0 ) {
						tagInfo = tagInfoOutput.TAGVALUE;
					}
				}

				if ( tagInfo == tagInfoOutput.TAG ) {
					cardData.printT();
				}

				if ( tagInfo == tagInfoOutput.TAGLENGTH ) {
					cardData.printTL();
				}

				if ( tagInfo == tagInfoOutput.TAGVALUE ) {
					int hexDumpWidth = 16;
					String hexDumpWidthEnv = System.getenv( "HEXDUMPWIDTH" );

					if ( ( hexDumpWidthEnv != null ) && ( hexDumpWidthEnv.length() > 0 ) ) {
						int hexDumpWidthTemp = Integer.parseInt( hexDumpWidthEnv, 10 );

						if ( ( hexDumpWidthTemp >= 16 ) && ( hexDumpWidthTemp <= 64 ) ) {
							hexDumpWidth = hexDumpWidthTemp;
						}
					}

					cardData.printTV( hexDumpWidth );
				}

				
				if ( cardData.invalidDataFound() == true ) {
					debugLogger.println( DebugLogger.LOGLEVEL_ERROR, "[ERROR] invalid certificates/data/signatures were found" );
					return false;
				}

				return true;
			}

			return false;
		} // end card data parser
		else if ( srcType == SRC_TYPE_VU ) {
			// vehicle unit data parser

			debugLogger.println( DebugLogger.LOGLEVEL_INFO, "[INFO] parsing vehicle unit data" );

			boolean parseresult = false;

			vehicleUnitData = new VehicleUnitData();
			xmlDumper = vehicleUnitData;

			vehicleUnitData.initECPublicKey();

			
			int pos = 0;
			int prdtLength;
			
			// data parser
			while ( true ) {
				byte[] tag = new byte[ 2 ];
				byte[] value;

				// tag parser
				while ( true ) {
					prdtLength = 0;

					if ( src.length < pos + 2 ) {
						// end of stream
						// break tag parser
						break;
					}

					// copy tag
					System.arraycopy( src, pos, tag, 0, 2 );
					pos += 2;

					if ( vehicleUnitData.isValidSIDTREP( tag ) == true ) {
						if ( Arrays.equals( tag, new byte[]{ 0x76, 0x01 } ) ) {
							// positive response transfer data overview
							debugLogger.println( DebugLogger.LOGLEVEL_INFO, "[INFO] vehicle unit data tag:" );
							debugLogger.println( DebugLogger.LOGLEVEL_INFO, " [INFO] SID/TREP: 76/01, overview" );

							prdtLength = 194 + 194 + 17 + 1 + 14 + 4 + 4 + 4 + 1 + 4 + 18 + 36;

							int noOfLocks = ( src[ pos + prdtLength ] & 0xff );
							debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  noOfLocks: %d\n", noOfLocks );

							prdtLength += 1;
							prdtLength += ( noOfLocks * 98 );

							int noOfControls = ( src[ pos + prdtLength ] & 0xff );
							debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  noOfControls: %d\n", noOfControls );

							prdtLength += 1;
							prdtLength += ( noOfControls * 31 );

							// signature length
							prdtLength += 128;

							debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  length: %d bytes\n", prdtLength );

							parseresult = true;
							break;
						}

						if ( Arrays.equals( tag, new byte[]{ 0x76, 0x02 } ) ) {
							// positive response transfer data activities
							debugLogger.println( DebugLogger.LOGLEVEL_INFO, "[INFO] vehicle unit data tag:" );
							debugLogger.println( DebugLogger.LOGLEVEL_INFO, " [INFO] SID/TREP: 76/02, activities" );

							prdtLength = 4 + 3;

							int noOfVuCardIWRecords = ( ( src[ pos + prdtLength ] & 0xff ) << 8 ) + ( src[ pos + prdtLength + 1 ] & 0xff );
							debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  noOfVuCardIWRecords: %d\n", noOfVuCardIWRecords );

							prdtLength += 2;
							prdtLength += ( noOfVuCardIWRecords * 129 );
							
							int noOfActivityChanges = ( ( src[ pos + prdtLength ] & 0xff ) << 8 ) + ( src[ pos + prdtLength + 1 ] & 0xff );
							debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  noOfActivityChanges: %d\n", noOfActivityChanges );

							prdtLength += 2;
							prdtLength += ( noOfActivityChanges * 2 );

							int noOfPlaceRecords = src[ pos + prdtLength ] & 0xff;
							debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  noOfPlaceRecords: %d\n", noOfPlaceRecords );

							prdtLength += 1;
							prdtLength += ( noOfPlaceRecords * 28 );

							int noOfSpecificConditionsRecords = ( ( src[ pos + prdtLength ] & 0xff ) << 8 ) + ( src[ pos + prdtLength + 1 ] & 0xff );
							debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  noOfSpecificConditionsRecords: %d\n", noOfSpecificConditionsRecords );

							prdtLength += 2;
							prdtLength += ( noOfSpecificConditionsRecords * 5 );

							// signature length
							prdtLength += 128;

							debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  length: %d bytes\n", prdtLength );

							parseresult = true;
							break;
						}

						if ( Arrays.equals( tag, new byte[]{ 0x76, 0x03 } ) ) {
							// positive response transfer data events and faults
							debugLogger.println( DebugLogger.LOGLEVEL_INFO, "[INFO] vehicle unit data tag:" );
							debugLogger.println( DebugLogger.LOGLEVEL_INFO, " [INFO] SID/TREP: 76/03, events and faults" );

							int noOfVuFaults = src[ pos + prdtLength ] & 0xff;
							debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  noOfVuFaults: %d\n", noOfVuFaults );

							prdtLength += 1;
							prdtLength += ( noOfVuFaults * 82 );

							int noOfVuEvents = src[ pos + prdtLength ] & 0xff;
							debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  noOfVuEvents: %d\n", noOfVuEvents );

							prdtLength += 1;
							prdtLength += ( noOfVuEvents * 83 );

							prdtLength += 4 + 4 + 1;

							int noOfVuOverSpeedingRecords = src[ pos + prdtLength ] & 0xff;
							debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  noOfVuOverSpeedingRecords: %d\n", noOfVuOverSpeedingRecords );

							prdtLength += 1;
							prdtLength += ( noOfVuOverSpeedingRecords * 31 );

							int noOfVuTimeAdjRecords = src[ pos + prdtLength ] & 0xff;
							debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  noOfVuTimeAdjRecords: %d\n", noOfVuTimeAdjRecords );

							prdtLength += 1;
							prdtLength += ( noOfVuTimeAdjRecords * 98 );

							// signature length
							prdtLength += 128;

							debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  length: %d bytes\n", prdtLength );

							parseresult = true;
							break;
						}

						if ( Arrays.equals( tag, new byte[]{ 0x76, 0x04 } ) ) {
							// positive response transfer data detailed speed
							debugLogger.println( DebugLogger.LOGLEVEL_INFO, "[INFO] vehicle unit data tag:" );
							debugLogger.println( DebugLogger.LOGLEVEL_INFO, " [INFO] SID/TREP: 76/04, detailed speed" );

							int noOfSpeedBlocks = ( ( src[ pos + prdtLength ] & 0xff ) << 8 ) + ( src[ pos + prdtLength + 1 ] & 0xff );
							debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  noOfSpeedBlocks: %d\n", noOfSpeedBlocks );

							prdtLength += 2;
							prdtLength += ( noOfSpeedBlocks * 64 );

							// signature length
							prdtLength += 128;

							debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  length: %d bytes\n", prdtLength );

							parseresult = true;
							break;
						}

						if ( Arrays.equals( tag, new byte[]{ 0x76, 0x05 } ) ) {
							// positive response transfer data technical data
							debugLogger.println( DebugLogger.LOGLEVEL_INFO, "[INFO] vehicle unit data tag:" );
							debugLogger.println( DebugLogger.LOGLEVEL_INFO, " [INFO] SID/TREP: 76/05, technical data" );

							prdtLength = 36 + 36 + 16 + 8 + 4 + 4 + 4 + 8 + 8 + 8 + 4;

							int noOfVuCalibrationsRecords = ( src[ pos + prdtLength ] & 0xff );
							debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  noOfVuCalibrationsRecords: %d\n", noOfVuCalibrationsRecords );

							prdtLength += 1;
							prdtLength += ( noOfVuCalibrationsRecords * 167 );

							// signature length
							prdtLength += 128;

							debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  length: %d bytes\n", prdtLength );

							parseresult = true;
							break;
						}

						parseresult = false;
						break;
					}
					else {
						parseresult = false;
						break;
					}
				}// end tag parser

				if ( parseresult == false ) {
					// break data parser
					break;
				}

				if ( src.length < pos + 2 ) {
					// end of stream
					// break data parser
					break;
				}

				if ( src.length < pos + prdtLength ){
					parseresult = false;
					// break data parser
					break;
				}

				// copy value
				value = new byte[ prdtLength ];
				System.arraycopy( src, pos, value, 0, prdtLength );
				pos += prdtLength;

				// add data
				parseresult = vehicleUnitData.add( tag, prdtLength, value );
				if ( parseresult == false ) {
					// break data parser
					break;
				}
			}// end data parser

			if ( parseresult == true ) {
				debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, "internal tag structure:" );

				tagInfoOutput tagInfo = tagInfoOutput.TAGLENGTH;
				String tagInfoEnv = System.getenv( "TAGINFO" );

				if ( ( tagInfoEnv != null ) && ( tagInfoEnv.length() > 0 ) ) {
					if ( tagInfoEnv.toUpperCase().compareTo( "TAG" ) == 0 ) {
						tagInfo = tagInfoOutput.TAG;
					}

					if ( tagInfoEnv.toUpperCase().compareTo( "TAGLENGTH" ) == 0 ) {
						tagInfo = tagInfoOutput.TAGLENGTH;
					}

					if ( tagInfoEnv.toUpperCase().compareTo( "TAGVALUE" ) == 0 ) {
						tagInfo = tagInfoOutput.TAGVALUE;
					}
				}

				if ( tagInfo == tagInfoOutput.TAG ) {
					vehicleUnitData.printT();
				}

				if ( tagInfo == tagInfoOutput.TAGLENGTH ) {
					vehicleUnitData.printTL();
				}

				if ( tagInfo == tagInfoOutput.TAGVALUE ) {
					int hexDumpWidth = 16;
					String hexDumpWidthEnv = System.getenv( "HEXDUMPWIDTH" );

					if ( ( hexDumpWidthEnv != null ) && ( hexDumpWidthEnv.length() > 0 ) ) {
						int hexDumpWidthTemp = Integer.parseInt( hexDumpWidthEnv, 10 );

						if ( ( hexDumpWidthTemp >= 16 ) && ( hexDumpWidthTemp <= 64 ) ) {
							hexDumpWidth = hexDumpWidthTemp;
						}
					}

					vehicleUnitData.printTV( hexDumpWidth );
				}

				if ( vehicleUnitData.invalidDataFound() == true ) {
					debugLogger.println( DebugLogger.LOGLEVEL_ERROR, "[ERROR] invalid certificates/data/signatures were found" );
					return false;
				}

				return true;
			}

			return false;
		} // end vehicle unit data parser
		else {
			// unknown type
			return false;
		}
	}

	/**
	 * reads the source file set by setSourceFile() into a byte array
	 */
	private boolean readSourceFile() {
		File f = new File( srcfile );
		FileInputStream fin = null;
		byte[] s = null;

		try
		{
			int length = (int)f.length();

			if ( length < 0 )
			{
				throw new Exception( "foo" );
			}

			s = new byte[ length ];
			fin = new FileInputStream( f );

			int bytesRead = 0;

			while ( bytesRead < length )
			{
				int toRead = fin.available();
				fin.read( s, bytesRead, bytesRead + toRead );
				bytesRead += toRead;
			}
		}
		catch ( Exception ex )
		{
			ex.printStackTrace();
		}

		String filename = f.getName().substring( 0, 2 );
		
		if ( filename.compareTo( "C_" ) == 0 ) {
			srcType = SRC_TYPE_CARD;
		}
		else if ( filename.compareTo( "M_" ) == 0 ) {
			srcType = SRC_TYPE_VU;
		}
		else {
			return false;
		}

		return readSource( s );
	}

	/**
	 * Returns the XMLDumper object.
	 * 
	 * @return			the XMLDumper object with the content of the DDD source
	 */
	public XMLDumper getXMLDumper() {
		return xmlDumper;
	}

	/**
	 * Calculates and returns the length value given by the first two bytes
	 * of the the byte array <tt>b</tt>.
	 */
	private int calculateLength( byte[] b ) {
		return ( ( b[ 0 ] & 0xff ) << 8 ) + ( b[ 1 ] & 0xff );
	}
}
