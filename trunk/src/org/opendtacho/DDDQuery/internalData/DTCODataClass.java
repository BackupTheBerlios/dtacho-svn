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

package org.opendtacho.DDDQuery.internalData;

import java.io.FileInputStream;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Iterator;
import java.util.Vector;

import org.jdom.Element;
import org.opendtacho.DDDQuery.DebugLogger;
import org.opendtacho.DDDQuery.XMLInfo;
import org.opendtacho.DDDQuery.internalData.Security.SecurityCAPublicKey;
import org.opendtacho.DDDQuery.internalData.Security.SecurityDataSignatureCheck;

/**
 * The abstract DTCODataClass provides common methods for examining data
 * of driver/workshop/company/control cards and vehicle units.
 */
public abstract class DTCODataClass extends DataClass {

	private SecurityCAPublicKey caPublicKey;
	private String ecPubKeyModulus;
	private String ecPubKeyExponent;

	private boolean ecPublicKeyAvailable;

	private int hexdumpwidth;
	private byte[] hexdumpbyte;
	private int hexdumpcount;

	private XMLInfo xmlInfo;

	protected Vector<DataClass> dispatcherQueue;

	private DebugLogger debugLogger;

	/**
	 * The data (tag & value objects) of an inherited DTCODataClass data class.
	 */
	public Vector<Object> data;


	/**
	 * Constructor for a DTCODataClass object
	 */
	public DTCODataClass() {
		ecPubKeyModulus = new String();
		ecPubKeyExponent = new String();

		ecPublicKeyAvailable = false;

		data = new Vector<Object>();

		dispatcherQueue = new Vector<DataClass>();
		
		debugLogger = new DebugLogger();
	}

	/**
	 * Tries to load and initialise the European Public Key. If the key
	 * could be successfully initialised, it will be used for deciphering
	 * the CA certificate.
	 * The path where the European Public Key resides can be set by the
	 * environment variable EURPKPATH.
	 * 
	 * @return	true if the EC public key could be initialised
	 * 			false otherwise
	 * 	
	 */
	public boolean initECPublicKey() {
		String envEURPKPath = new String();
		FileInputStream ecPubKeyFIS;

		try {
			envEURPKPath = System.getenv( "EURPKPATH" );

			if ( ( envEURPKPath != null ) && ( envEURPKPath.length() > 0 ) ) {
				if ( envEURPKPath.charAt( envEURPKPath.length() - 1 ) != '/' ) {
					envEURPKPath = envEURPKPath + "/";
				}
			}
		}
		catch ( NullPointerException npe ) {
			npe.printStackTrace();
		}
		catch ( SecurityException se ) {
			se.printStackTrace();
		}

		try {
			// try to open the EC Public Key
			debugLogger.println( DebugLogger.LOGLEVEL_INFO, "[INFO] trying to open the European Public Key:" );
			debugLogger.print( DebugLogger.LOGLEVEL_INFO, "[INFO] " );
			if ( envEURPKPath != null ) {
				debugLogger.print( DebugLogger.LOGLEVEL_INFO, envEURPKPath );
			}
			debugLogger.println( DebugLogger.LOGLEVEL_INFO, "EC_PK.bin" );

			if ( envEURPKPath != null ) {
				ecPubKeyFIS = new FileInputStream( envEURPKPath + "EC_PK.bin" );
			}
			else {
				ecPubKeyFIS = new FileInputStream( "EC_PK.bin" );
			}

			int ecPKLength = ecPubKeyFIS.available();

			if ( ecPKLength != 144 ) {
				ecPubKeyFIS.close();
				ecPublicKeyAvailable = false;
				debugLogger.println( DebugLogger.LOGLEVEL_INFO, "[INFO] EC public key not found. Will skip signature checks." );

				return ecPublicKeyAvailable;
			}

			byte[] ecPublicKey = new byte[ ecPKLength ];
			ecPubKeyFIS.read( ecPublicKey );
			ecPubKeyFIS.close();

			caPublicKey = new SecurityCAPublicKey( ecPublicKey );
			ecPubKeyModulus = caPublicKey.getPublicKeyModulusHexString();
			ecPubKeyExponent = caPublicKey.getPublicKeyExponentHexString();

			ecPublicKeyAvailable = true;
			debugLogger.println( DebugLogger.LOGLEVEL_INFO, "[INFO] EC public key found. Signatures will be checked." );

			XMLInfo.setDataValidatedWithEURPK( true );

			return ecPublicKeyAvailable;
		}
		catch ( Exception e ) {
			ecPublicKeyAvailable = false;
			debugLogger.println( DebugLogger.LOGLEVEL_INFO, "[INFO] EC public key not found. Will skip signature checks." );

			return ecPublicKeyAvailable;
		}
	}

	/**
	 * Returns the RSA public key modulus as hex string
	 * 
	 * @return	the RSA public key modulus as hex string
	 */
	public String getECPubKeyModulus() {
		return ecPubKeyModulus;
	}

	/**
	 * Returns the RSA public key exponent as hex string
	 * 
	 * @return	the RSA public key exponent as hex string
	 */
	public String getECPubKeyExponent() {
		return ecPubKeyExponent;
	}

	/**
	 * Indicates if the EC Public Key is available.
	 * 
	 * @return	true, if the EC Public Key is available.
	 */
	public boolean isECPublicKeyAvailable() {
		return ecPublicKeyAvailable;
	}

	/**
	 * Verifies the integrity of the given data against the given signature
	 * 
	 * @param	publicKeyModulusHexString		the modulus of the public key that is used to decipher the signature as hex string
	 * @param	publicKeyExponentHexString		the exponent of the public key that is used to decipher the signature as hex string
	 * @param	tag								byte array with the 2 byte id of the current data
	 * @param	data							byte array with the data to be checked
	 * @param	signature						the signature of the data
	 *
	 * @return	true, if the integrity of the data could be verified
	 */
	public boolean verifyDataSignatureIntegrity( String publicKeyModulusHexString, String publicKeyExponentHexString, byte[] tag, byte[] data, byte[] signature ) {
		// check data/signature integrity
		debugLogger.printf( DebugLogger.LOGLEVEL_INFO, " [INFO] verifying data/signature integrity of %02x %02x\n", tag[ 0 ], tag[ 1 ] );
		SecurityDataSignatureCheck efDataSignatureCheck = new SecurityDataSignatureCheck();

		try {
			// set public key
			efDataSignatureCheck.setRSAPublicKey( publicKeyModulusHexString, publicKeyExponentHexString );

			// decipher signature
			debugLogger.println( DebugLogger.LOGLEVEL_INFO, " [INFO] deciphering signature" );
			efDataSignatureCheck.decipherSignature( signature );

			// calculate data sha1 hash
			efDataSignatureCheck.setDataByteArray( data );

			debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  signature            [%3d]: %s\n", efDataSignatureCheck.getDecipheredSignatureByteArray().length, efDataSignatureCheck.getDecipheredSignatureHexString() );
			debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  data SHA1 hash       [%3d]: %s\n", efDataSignatureCheck.getDataSHAHashByteArray().length, efDataSignatureCheck.getDataSHAHashHexString() );
			debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  signature SHA1 hash  [%3d]: %s\n", efDataSignatureCheck.getDecipheredSignatureByteArray().length-108, efDataSignatureCheck.getDecipheredSignatureHexString().substring( 216, 256 ) );
			debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  data valid?          [---]: %s\n", efDataSignatureCheck.isValidData());

			if ( efDataSignatureCheck.isValidData() == true ) {
				debugLogger.println( DebugLogger.LOGLEVEL_INFO, " [INFO] data is valid. Data and signature SHA1 hash are equal." );
				return true;
			}
			else {
				debugLogger.println( DebugLogger.LOGLEVEL_ERROR, "[ERROR] data is invalid. Data and signature SHA1 hash are not equal." );
				return false;
			}
		}
		catch ( NoSuchAlgorithmException nsae ) {
			return false;
		}
		catch ( InvalidKeySpecException ikse ) {
			return false;
		}
	}

	/**
	 * Dumps all tags/file IDs collected.
	 */
	public void printT() {
		Iterator<Object> it = data.iterator();
		while ( it.hasNext() ) {
			Object[] tmp = (Object[])it.next();

			debugLogger.print( DebugLogger.LOGLEVEL_INFO_EXTENDED, "tag: " );

			for ( int i = 0; i < ( (byte[])tmp[ 0 ]).length; i++ ) {
				debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, " %02x", (int)( ( (byte[])tmp[ 0 ])[ i ] & 0xff ) );
			}

			debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED );
		}
	}

	/**
	 * Dumps all tags/file IDs collected with its lengths.
	 */
	public void printTL() {
		Iterator<Object> it = data.iterator();

		while ( it.hasNext() ) {
			Object[] tmp = (Object[])it.next();

			debugLogger.print( DebugLogger.LOGLEVEL_INFO_EXTENDED, "tag: " );

			for ( int i = 0; i < ( (byte[])tmp[ 0 ]).length; i++ ) {
				debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, " %02x", (int)( ( (byte[])tmp[ 0 ])[ i ] & 0xff ) );
			}

			debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, "\t - length: " + ( (byte[])tmp[ 1 ] ).length );
		}
	}

	/**
	 * Dumps all tags/file IDs collected with its lengths and values (data) as combined
	 * hex/ASCII dump with 16 bytes per line.
	 */
	public void printTV() {
		printTV( 16 );
	}

	/**
	 * Dumps all tags/file IDs collected with its lengths and values (data) as combined
	 * hex/ASCII dump with the given bytesperline bytes per line.
	 * 
	 * @param	bytesperline	number of bytes dumped per line
	 */
	public void printTV( int bytesperline ) {
		hexdumpwidth = bytesperline;
		hexdumpbyte = new byte[ hexdumpwidth ];
		hexdumpcount = 0;

		Iterator<Object> it = data.iterator();

		while ( it.hasNext() ) {
			Object[] tmp = (Object[])it.next();

			// TAG
			debugLogger.print( DebugLogger.LOGLEVEL_INFO_EXTENDED, "tag: " );

			for ( int i = 0; i < ( (byte[])tmp[ 0 ] ).length; i++ ) {
				debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, " %02x", (int)( ( (byte[])tmp[ 0 ] )[ i ] & 0xff) );
			}

			debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, "\t - length: " + ( (byte[])tmp[ 1 ] ).length );

			// VALUE
			debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, "value:" );

			for ( int i = 0; i < ( (byte[])tmp[ 1 ] ).length; i++ ) {
				hexdump( (byte)( ( (byte[])tmp[ 1 ] )[ i ] & 0xff) );
			}

			hexdump_flush();
			debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED );
		}
	}

	/**
	 * Adds the given byte for hex/ASCII dump
	 * 
	 * @param	b	byte to be added for hex/ASCII dump
	 */
	private void hexdump( byte b ) {
		hexdumpbyte[ hexdumpcount ] = b;
		hexdumpcount += 1;

		if ( hexdumpcount == hexdumpwidth ) {
			hexdump_flush();
		}
	}

	/**
	 * Flushes the collected byte by hexdump() and dumps them
	 * as hex/ASCII with hexdumpwith bytes per line
	 */	
	private void hexdump_flush() {
		if ( hexdumpcount > 0 ) {
			for ( int i = 0; i < hexdumpcount; i++ ) {
				debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, " %02x", hexdumpbyte[ i ] );
			}

			debugLogger.print( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  " );

			for ( int i = 0; i < hexdumpwidth-hexdumpcount; i++ ) {
				debugLogger.print( DebugLogger.LOGLEVEL_INFO_EXTENDED, "   " );
			}

			for( int i = 0; i < hexdumpcount; i++ ) {
				if ( ( hexdumpbyte[ i ] >= 0x20 ) && ( hexdumpbyte[i] < 0x7f ) ) {
					// print visible chars only
					debugLogger.print( DebugLogger.LOGLEVEL_INFO_EXTENDED, (char)hexdumpbyte[ i ] );
				}
				else {
					// otherwise print a dot
					debugLogger.print( DebugLogger.LOGLEVEL_INFO_EXTENDED, "." );
				}
			}
			
			hexdumpcount = 0;
			debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED );
		}
	}

	/**
	 * Returns the XMLInfo of a DTCODataClass object.
	 * 
	 * @return	xmlInfo		the XMLInfo object of the DTCODataClass object.
	 */
	public XMLInfo getXMLInfo() {
		return xmlInfo;
	}

	/**
	 * Sets the XMLInfo of a DTCODataClass object.
	 * 
	 * @param	xmlInfo		the XMLInfo object to be set for the DTCODataClass object.
	 */
	public void setXMLInfo( XMLInfo xmlInfo ) {
		this.xmlInfo = xmlInfo;
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element root = new Element( name );

		root.addContent( xmlInfo.generateXMLElement( "Info" ) );

		Iterator<DataClass> dispaterIterator = dispatcherQueue.iterator();

		while ( dispaterIterator.hasNext() ) {
			DataClass dc = (DataClass)dispaterIterator.next();
			Element node = dc.generateXMLElement( dc.getClass().getSimpleName() );

			root.addContent( node );
		}

		return root;
	}
}
