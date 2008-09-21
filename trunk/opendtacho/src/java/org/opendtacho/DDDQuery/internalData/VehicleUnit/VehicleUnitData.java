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

package org.opendtacho.DDDQuery.internalData.VehicleUnit;

import org.jdom.*;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.opendtacho.DDDQuery.DebugLogger;
import org.opendtacho.DDDQuery.XMLDumper;
import org.opendtacho.DDDQuery.internalData.DTCODataClass;
import org.opendtacho.DDDQuery.internalData.DataTypes.*;
import org.opendtacho.DDDQuery.internalData.Security.SecurityByteArrayTools;
import org.opendtacho.DDDQuery.internalData.Security.SecurityCertificateCheck;

import java.io.FileOutputStream;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

/**
 * The data of a vehicle unit's .DDD file.
 */
public class VehicleUnitData extends DTCODataClass implements XMLDumper {

	private PRDT_Overview prdt_Overview;					// SID 76H, TREP 01H: overview
	private PRDT_Activities prdt_Activities;				// SID 76H, TREP 02H: activities
	private PRDT_Events_And_Faults prdt_Events_And_Faults;	// SID 76H, TREP 03H: events and faults
	private PRDT_Detailed_Speed prdt_Detailed_Speed;		// SID 76H, TREP 04H: detailed speed
	private PRDT_Technical_Data prdt_Technical_Data;		// SID 76H, TREP 05H: technical data

	private boolean invalid_data_found;

	private SecurityCertificateCheck msCertificate;
	private boolean genuineMSCertificate;
	private SecurityCertificateCheck vuCertificate;
	private boolean genuineVuCertificate;

	private DebugLogger debugLogger;

	/**
	 * Constructor for a VehicleUnit object
	 */
	public VehicleUnitData() {
		invalid_data_found = false;
		debugLogger = new DebugLogger();
	}

	/**
	 * Indicates if the given tag in the first two bytes of the byte array is a valid SID/TREP ID
	 * of vehicle unit data.
	 * 
	 * @return	true if the given tag is a valid SID/TREP ID 
	 */
	public boolean isValidSIDTREP( byte[] tag ) {
		if ( ( Arrays.equals( new byte[]{ tag[ 0 ],tag[ 1 ] }, new byte[]{ 0x76, 0x01 } ) ) |		// SID 76H, TREP 01H: overview
			 ( Arrays.equals( new byte[]{ tag[ 0 ],tag[ 1 ] }, new byte[]{ 0x76, 0x02 } ) ) |		// SID 76H, TREP 02H: activities
			 ( Arrays.equals( new byte[]{ tag[ 0 ],tag[ 1 ] }, new byte[]{ 0x76, 0x03 } ) ) |		// SID 76H, TREP 03H: events and faults
			 ( Arrays.equals( new byte[]{ tag[ 0 ],tag[ 1 ] }, new byte[]{ 0x76, 0x04 } ) ) |		// SID 76H, TREP 04H: detailed speed
			 ( Arrays.equals( new byte[]{ tag[ 0 ],tag[ 1 ] }, new byte[]{ 0x76, 0x05 } ) ) )	{	// SID 76H, TREP 05H: technical data

			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Indicates if invalid data was found, i.e. integrity check of data and
	 * its corresponding signature failed.
	 * 
	 * @return	true if invalid data was found
	 */
	public boolean invalidDataFound() {
		return invalid_data_found;
	}

	/**
	 * Tries to identify the given data by SID/TREP id and to parse the given data (value).
	 * If successful the data is added internally to the VehicleUnitData for XML
	 * dump. 
	 * 
	 * @param tag		byte array with tag value (2 bytes)
	 * @param length	length of the value
	 * @param value		byte array with data

	 * @return			true if parsing of the given byte array was successful
	 */
	public boolean add( byte[] tag, int length, byte[] value ) {
		boolean parseresult = true;
		Object[] tmp = { tag, value };
		data.add( tmp );

		if ( Arrays.equals( tag, new byte[]{ 0x76, 0x01 } ) ) {
			// SID 76H, TREP 01H: overview
			prdt_Overview = new PRDT_Overview( value );
			dispatcherQueue.add( prdt_Overview );

			byte[] memberStateCertificateValue = arrayCopy( value, 0, 194 );
			byte[] vuCertificateValue = arrayCopy( value, 194, 194 );

			if ( super.isECPublicKeyAvailable() == true ) {
				// check member state certificate
				debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO, " [INFO] checking Member State certificate" );
				msCertificate = new SecurityCertificateCheck();

				// set public key
				try {
					msCertificate.setRSAPublicKey( super.getECPubKeyModulus(), super.getECPubKeyExponent() );

					// decipher member state certificate
					debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO, " [INFO] deciphering Member State certificate" );
					msCertificate.decipherCertificateSignature( memberStateCertificateValue );
					genuineMSCertificate = msCertificate.isGenuineCertificate();

					debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  certificate          [%3d]: %s\n", memberStateCertificateValue.length, new SecurityByteArrayTools().ByteArrayToHexString( memberStateCertificateValue ) );
					debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  signature            [%3d]: %s\n", msCertificate.getDecipheredSignatureByteArray().length, msCertificate.getDecipheredSignatureHexString() );
					debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  valid signature?     [---]: %s\n", msCertificate.isValidSignature());
					debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  c_n                  [%3d]: %s\n", msCertificate.getC_NByteArray().length, msCertificate.getC_NHexString() );
					debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  car                  [%3d]: %s\n", msCertificate.getCARByteArray().length, msCertificate.getCARHexString() );
					debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  c_r                  [%3d]: %s\n", msCertificate.getC_RByteArray().length, msCertificate.getC_RHexString() );
					debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  c_0                  [%3d]: %s\n", msCertificate.getC_0ByteArray().length, msCertificate.getC_0HexString() );
					debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  h_0                  [%3d]: %s\n", msCertificate.getH_0ByteArray().length, msCertificate.getH_0HexString() );
					debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  calculated SHA1 hash [%3d]: %s\n", msCertificate.getC_0SHAHashByteArray().length, msCertificate.getC_0SHAHashHexString() );
					debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  genuine certificate? [---]: %s\n", msCertificate.isGenuineCertificate());

					if ( genuineMSCertificate == true ) {
						debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  certificate content:");
						debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  cpi                  [%3d]: %s\n", msCertificate.getCertificateContent().getCPIByteArray().length, msCertificate.getCertificateContent().getCPIHexString() );
						debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  car                  [%3d]: %s\n", msCertificate.getCertificateContent().getCARByteArray().length, msCertificate.getCertificateContent().getCARHexString() );
						debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  cha                  [%3d]: %s\n", msCertificate.getCertificateContent().getCHAByteArray().length, msCertificate.getCertificateContent().getCHAHexString() );
						debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  eov                  [%3d]: %s\n", msCertificate.getCertificateContent().getEOVByteArray().length, msCertificate.getCertificateContent().getEOVHexString() );
						debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  chr                  [%3d]: %s\n", msCertificate.getCertificateContent().getCHRByteArray().length, msCertificate.getCertificateContent().getCHRHexString() );
						debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  n                    [%3d]: %s\n", msCertificate.getCertificateContent().getNByteArray().length, msCertificate.getCertificateContent().getNHexString() );
						debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  e                    [%3d]: %s\n", msCertificate.getCertificateContent().getEByteArray().length, msCertificate.getCertificateContent().getEHexString() );

						debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO, " [INFO] Member State certificate is valid" );
						if ( msCertificate.getCertificateContent().getEOVHexString().compareTo( "FFFFFFFF") != 0 ) {
							debugLogger.print( DebugLogger.DEBUG_LOGLEVEL_INFO, " [INFO] Member State certificate end of validity:\n " );
							new TimeReal( convertIntoUnsigned4ByteInt( msCertificate.getCertificateContent().getEOVByteArray() ) );
						}

						// check vehicle unit certificate
						debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO, " [INFO] checking Vehicle Unit certificate" );
						vuCertificate = new SecurityCertificateCheck();

						try {
							// set public key
							vuCertificate.setRSAPublicKey( msCertificate.getCertificateContent().getNHexString(), msCertificate.getCertificateContent().getEHexString() );

							// decipher card certificate
							debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO, " [INFO] deciphering Vehicle Unit certificate" );
							vuCertificate.decipherCertificateSignature( vuCertificateValue );
							genuineVuCertificate = vuCertificate.isGenuineCertificate();

							debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  certificate          [%3d]: %s\n", vuCertificateValue.length, new SecurityByteArrayTools().ByteArrayToHexString( vuCertificateValue ) );
							debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  signature            [%3d]: %s\n", vuCertificate.getDecipheredSignatureByteArray().length, vuCertificate.getDecipheredSignatureHexString() );
							debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  valid signature?     [---]: %s\n", vuCertificate.isValidSignature() );
							debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  c_n                  [%3d]: %s\n", vuCertificate.getC_NByteArray().length, vuCertificate.getC_NHexString() );
							debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  car                  [%3d]: %s\n", vuCertificate.getCARByteArray().length, vuCertificate.getCARHexString() );
							debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  c_r                  [%3d]: %s\n", vuCertificate.getC_RByteArray().length, vuCertificate.getC_RHexString() );
							debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  c_0                  [%3d]: %s\n", vuCertificate.getC_0ByteArray().length, vuCertificate.getC_0HexString() );
							debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  h_0                  [%3d]: %s\n", vuCertificate.getH_0ByteArray().length, vuCertificate.getH_0HexString() );
							debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  calculated SHA1 hash [%3d]: %s\n", vuCertificate.getC_0SHAHashByteArray().length, vuCertificate.getC_0SHAHashHexString() );
							debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  genuine certificate? [---]: %s\n", vuCertificate.isGenuineCertificate() );

							if ( genuineVuCertificate == true ) {
								debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  certificate content:");
								debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  cpi                  [%3d]: %s\n", vuCertificate.getCertificateContent().getCPIByteArray().length, vuCertificate.getCertificateContent().getCPIHexString() );
								debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  car                  [%3d]: %s\n", vuCertificate.getCertificateContent().getCARByteArray().length, vuCertificate.getCertificateContent().getCARHexString() );
								debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  cha                  [%3d]: %s\n", vuCertificate.getCertificateContent().getCHAByteArray().length, vuCertificate.getCertificateContent().getCHAHexString() );
								debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  eov                  [%3d]: %s\n", vuCertificate.getCertificateContent().getEOVByteArray().length, vuCertificate.getCertificateContent().getEOVHexString() );
								debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  chr                  [%3d]: %s\n", vuCertificate.getCertificateContent().getCHRByteArray().length, vuCertificate.getCertificateContent().getCHRHexString() );
								debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  n                    [%3d]: %s\n", vuCertificate.getCertificateContent().getNByteArray().length, vuCertificate.getCertificateContent().getNHexString() );
								debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  e                    [%3d]: %s\n", vuCertificate.getCertificateContent().getEByteArray().length, vuCertificate.getCertificateContent().getEHexString() );

								debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO, " [INFO] Vehicle Unit certificate is valid" );
								if ( vuCertificate.getCertificateContent().getEOVHexString().compareTo( "FFFFFFFF") != 0 ) {
									debugLogger.print( DebugLogger.DEBUG_LOGLEVEL_INFO, " [INFO] Vehicle Unit certificate end of validity:\n " );
									new TimeReal( convertIntoUnsigned4ByteInt( vuCertificate.getCertificateContent().getEOVByteArray() ) );
								}

								if ( verifyDataSignatureIntegrity( vuCertificate.getCertificateContent().getNHexString(), vuCertificate.getCertificateContent().getEHexString(), tag, arrayCopy( value, 194 + 194, value.length - 194 - 194 - 128  ), arrayCopy( value, value.length - 128, 128  ) ) == false ) {
									invalid_data_found = true;
								}
							}
							else {
								debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_ERROR, "[ERROR] Vehicle Unit certificate is invalid" );
								invalid_data_found = true;
							}
						}
						catch ( NoSuchAlgorithmException nsae ) {
							genuineVuCertificate = false;
						}
						catch ( InvalidKeySpecException ikse ) {
							genuineVuCertificate = false;
						}
					}
					else {
						debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_ERROR, "[ERROR] Member State certificate is invalid" );
						invalid_data_found = true;
					}
				}
				catch ( NoSuchAlgorithmException nsae ) {
					genuineMSCertificate = false;
				}
				catch ( InvalidKeySpecException ikse ) {
					genuineMSCertificate = false;
				}
			}
		}
		else if ( Arrays.equals( tag, new byte[]{ 0x76, 0x02 } ) ) {
			// SID 76H, TREP 02H: activities
			prdt_Activities = new PRDT_Activities( value );
			dispatcherQueue.add( prdt_Activities );
			if ( genuineVuCertificate == true ) {
				if ( verifyDataSignatureIntegrity( vuCertificate.getCertificateContent().getNHexString(), vuCertificate.getCertificateContent().getEHexString(), tag, arrayCopy( value, 0, value.length - 128  ), arrayCopy( value, value.length - 128, 128  ) ) == false ) {
					invalid_data_found = true;
				}
			}
		}
		else if ( Arrays.equals( tag, new byte[]{ 0x76, 0x03 } ) ) {
			// SID 76H, TREP 03H: events and faults
			prdt_Events_And_Faults = new PRDT_Events_And_Faults( value );
			dispatcherQueue.add( prdt_Events_And_Faults );
			if ( genuineVuCertificate == true ) {
				if ( verifyDataSignatureIntegrity( vuCertificate.getCertificateContent().getNHexString(), vuCertificate.getCertificateContent().getEHexString(), tag, arrayCopy( value, 0, value.length - 128  ), arrayCopy( value, value.length - 128, 128  ) ) == false ) {
					invalid_data_found = true;
				}
			}
		}
		else if ( Arrays.equals( tag, new byte[]{ 0x76, 0x04 } ) ) {
			// SID 76H, TREP 04H: detailed speed
			prdt_Detailed_Speed = new PRDT_Detailed_Speed( value );
			dispatcherQueue.add( prdt_Detailed_Speed );
			if ( genuineVuCertificate == true ) {
				if ( verifyDataSignatureIntegrity( vuCertificate.getCertificateContent().getNHexString(), vuCertificate.getCertificateContent().getEHexString(), tag, arrayCopy( value, 0, value.length - 128  ), arrayCopy( value, value.length - 128, 128  ) ) == false ) {
					invalid_data_found = true;
				}
			}
		}
		else if ( Arrays.equals( tag, new byte[]{ 0x76, 0x05 } ) ) {
			// SID 76H, TREP 05H: technical data
			prdt_Technical_Data = new PRDT_Technical_Data( value );
			dispatcherQueue.add( prdt_Technical_Data );
			if ( genuineVuCertificate == true ) {
				if ( verifyDataSignatureIntegrity( vuCertificate.getCertificateContent().getNHexString(), vuCertificate.getCertificateContent().getEHexString(), tag, arrayCopy( value, 0, value.length - 128 ), arrayCopy( value, value.length - 128, 128 ) ) == false ) {
					invalid_data_found = true;
				}
			}
		}
		else {
			debugLogger.print( DebugLogger.DEBUG_LOGLEVEL_ERROR, "[ERROR] unknown tag, " );
			debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_ERROR, "%02x", (int)( tag[ 0 ] & 0xff ) );
			debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_ERROR, "%02x\n", (int)( tag[ 1 ] & 0xff ) );
			parseresult = false;
		}

		return parseresult;
	}

	/**
	 * Dumps the collected tags as XML.
	 * 
	 * @return		the dumped XML string
	 */
	@Override
	public String generateXML() {
		Document doc = new Document();

		Element root = generateXMLElement( this.getClass().getSimpleName() );

		Namespace ns = Namespace.getNamespace( "VehicleUnit", "VehicleUnit" );
		root.setNamespace( ns );

		Namespace nsxsi = Namespace.getNamespace( "xsi", "http://www.w3.org/2001/XMLSchema-instance" );
		root.setAttribute( new Attribute( "schemaLocation", "VehicleUnit VehicleUnit.xsd", nsxsi ) );

		doc.setRootElement( root );

		Format fmt = Format.getPrettyFormat();
		fmt.setLineSeparator( "\n" );
		fmt.setEncoding( "UTF-8" );
		fmt.setExpandEmptyElements( true );

		XMLOutputter serializer = new XMLOutputter( fmt );

		return serializer.outputString( doc );
	}

	/**
	 * Dumps the collected tags as XML file.
	 * 
	 * @param	filename		name of the XML file
	 */
	@Override
	public void generateXML( String filename ) {
		Document doc = new Document();

		Element root = generateXMLElement( this.getClass().getSimpleName() );

		Namespace ns = Namespace.getNamespace( "VehicleUnit", "VehicleUnit" );
		root.setNamespace( ns );

		Namespace nsxsi = Namespace.getNamespace( "xsi", "http://www.w3.org/2001/XMLSchema-instance" );
		root.setAttribute( new Attribute( "schemaLocation", "VehicleUnit VehicleUnit.xsd", nsxsi ) );

		doc.setRootElement( root );

		try { 
			FileOutputStream fos = new FileOutputStream( filename );

			Format fmt = Format.getPrettyFormat();
			fmt.setLineSeparator( "\n" );
			fmt.setEncoding( "UTF-8" );
			fmt.setExpandEmptyElements( true );

			XMLOutputter serializer = new XMLOutputter( fmt );

			serializer.output( doc, fos );

			fos.flush();
			fos.close();
		}
		catch ( Exception e ) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
