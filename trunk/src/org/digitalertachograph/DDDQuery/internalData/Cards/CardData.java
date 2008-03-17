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

package org.digitalertachograph.DDDQuery.internalData.Cards;

import org.digitalertachograph.DDDQuery.DebugLogger;
import org.digitalertachograph.DDDQuery.XMLDumper;
import org.digitalertachograph.DDDQuery.internalData.DTCODataClass;
import org.digitalertachograph.DDDQuery.internalData.DataTypes.*;
import org.digitalertachograph.DDDQuery.internalData.Security.SecurityByteArrayTools;
import org.digitalertachograph.DDDQuery.internalData.Security.SecurityCertificateCheck;

import java.io.FileOutputStream;
import java.util.Arrays;
import java.security.*;
import java.security.spec.*;

import org.jdom.*;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;


/*
 * Appendix 7, 3.3 Card downloading describes which EFs are followed by a digital
 * signature and which EFs are not.
 */

/**
 * The data of a card's .DDD file.
 */
public class CardData extends DTCODataClass implements XMLDumper {

	private EF_ICC ef_icc;
	private EF_IC ef_ic;
	private EF_Application_Identification ef_application_identification;
	private EF_Card_Certificate ef_card_certificate;
	private EF_CA_Certificate ef_ca_certificate;
	private EF_Identification ef_identification;
	private EF_Card_Download ef_card_download;
	private EF_Driving_Licence_Info ef_driving_licence_info;
	private EF_Events_Data ef_events_data;
	private EF_Faults_Data ef_faults_data;
	private EF_Driver_Activity_Data ef_driver_activity;
	private EF_Vehicles_Used ef_vehicles_used;
	private EF_Places ef_places;
	private EF_Current_Usage ef_current_usage;
	private EF_Control_Activity_Data ef_control_activity_data;
	private EF_Specific_Conditions ef_specific_conditions;
	private EF_Calibration ef_calibration;
	private EF_Sensor_Installation_Data ef_sensor_installation_data;
	private EF_Controller_Activity_Data ef_controller_activity_data;
	private EF_Company_Activity_Data ef_company_activity_data;

	private short cardType;

	private static final int EF_DATA_ENTITY = 0;
	private static final int EF_SIGNATURE_ENTITY = 1;
	
	private int efstate = EF_DATA_ENTITY;
	private byte[] tag2;

	private SecurityCertificateCheck caCertificate;
	private boolean genuineCACertificate;

	private SecurityCertificateCheck cardCertificate;
	private boolean genuineCardCertificate;

	private byte[] ef_application_identification_value;
	private byte[] ef_application_identification_signature;
	private byte[] ef_card_value;
	private byte[] ef_value;

	private boolean invalid_data_found;

	private DebugLogger debugLogger;
	
	/**
	 * Constructor for a TachographData object
	 */
	public CardData() {
		invalid_data_found = false;
		debugLogger = new DebugLogger();
	}

	/**
	 * Prints the name, type file id of the given 3 byte tag
	 * 
	 * @param	tag		3 byte tag: 2 bytes file id, 1 byte type
	 */
	private void printTagInfo( byte[] tag ) {
		String tagInfo;

		if ( tag.length != 3 )
			return;

		if ( Arrays.equals( new byte[]{ tag[ 0 ],tag[ 1 ] },      new byte[]{       0x00, 0x02 } ) ) {
			//	EF_ICC
			tagInfo = "EF_ICC";
		}
		else if ( Arrays.equals( new byte[]{ tag[ 0 ],tag[ 1 ] }, new byte[]{       0x00, 0x05 } ) ) {
			//	EF_IC
			tagInfo = "EF_IC";
		}
		else if ( Arrays.equals( new byte[]{ tag[ 0 ],tag[ 1 ] }, new byte[]{ (byte)0xc1, 0x00 } ) ) {
			//	EF_Card_Certificate
			tagInfo = "EF_Card_Certificate";
		}
		else if ( Arrays.equals( new byte[]{ tag[ 0 ],tag[ 1 ] }, new byte[]{ (byte)0xc1, 0x08 } ) ) {
			//	EF_CA_Certificate
			tagInfo = "EF_CA_Certificate";
		}
		else if ( Arrays.equals( new byte[]{ tag[ 0 ],tag[ 1 ] }, new byte[]{       0x05, 0x01 } ) ) {
			//	EF_Application_Identification
			tagInfo = "EF_Application_Identification";
		}
		else if ( Arrays.equals( new byte[]{ tag[ 0 ],tag[ 1 ] }, new byte[]{       0x05, 0x20 } ) ) {
			//	EF_Identification
			tagInfo = "EF_Identification";
		}
		else if ( Arrays.equals( new byte[]{ tag[ 0 ],tag[ 1 ] }, new byte[]{       0x05, 0x0e } ) ) {
			//	EF_Card_Download (driver card)
			tagInfo = "EF_Card_Download (driver card)";
		}
		else if ( Arrays.equals( new byte[]{ tag[ 0 ],tag[ 1 ] }, new byte[]{       0x05, 0x21 } ) ) {
			//	EF_Driving_Licence_Info
			tagInfo = "EF_Driving_Licence_Info";
		}
		else if ( Arrays.equals( new byte[]{ tag[ 0 ],tag[ 1 ] }, new byte[]{       0x05, 0x02 } ) ) {
			//	EF_Events_Data
			tagInfo = "EF_Events_Data";
		}
		else if ( Arrays.equals( new byte[]{ tag[ 0 ],tag[ 1 ] }, new byte[]{       0x05, 0x03 } ) ) {
			//	EF_Faults_Data
			tagInfo = "EF_Faults_Data";
		}
		else if ( Arrays.equals( new byte[]{ tag[ 0 ],tag[ 1 ] }, new byte[]{       0x05, 0x04 } ) ) {
			//	EF_Driver_Activity_Data
			tagInfo = "EF_Driver_Activity_Data";
		}
		else if ( Arrays.equals( new byte[]{ tag[ 0 ],tag[ 1 ] }, new byte[]{       0x05, 0x05 } ) ) {
			//	EF_Vehicles_Used
			tagInfo = "EF_Vehicles_Used";
		}
		else if ( Arrays.equals( new byte[]{ tag[ 0 ],tag[ 1 ] }, new byte[]{       0x05, 0x06 } ) ) {
			//	EF_Places
			tagInfo = "EF_Places";
		}
		else if ( Arrays.equals( new byte[]{ tag[ 0 ],tag[ 1 ] }, new byte[]{       0x05, 0x07 } ) ) {
			//	EF_Current_Usage
			tagInfo = "EF_Current_Usage";
		}
		else if ( Arrays.equals( new byte[]{ tag[ 0 ],tag[ 1 ] }, new byte[]{       0x05, 0x08 } ) ) {
			//	EF_Control_Activity_Data
			tagInfo = "EF_Control_Activity_Data";
		}
		else if ( Arrays.equals( new byte[]{ tag[ 0 ],tag[ 1 ] }, new byte[]{       0x05, 0x22 } ) ) {
			//	EF_Specific_Conditions
			tagInfo = "EF_Specific_Conditions";
		}
		else if ( Arrays.equals( new byte[]{ tag[ 0 ],tag[ 1 ] }, new byte[]{       0x05, 0x09 } ) ) {
			//	EF_Card_Download (workshop card)
			tagInfo = "EF_Card_Download (workshop card)";
		}
		else if ( Arrays.equals( new byte[]{ tag[ 0 ],tag[ 1 ] }, new byte[]{       0x05, 0x0a } ) ) {
			//	EF_Calibration
			tagInfo = "EF_Calibration";
		}
		else if ( Arrays.equals( new byte[]{ tag[ 0 ],tag[ 1 ] }, new byte[]{       0x05, 0x0b } ) ) {
			//	EF_Sensor_Installation_Data
			tagInfo = "EF_Sensor_Installation_Data";
		}
		else if ( Arrays.equals( new byte[]{ tag[ 0 ],tag[ 1 ] }, new byte[]{       0x05, 0x0c } ) ) {
			//	EF_Controller_Activity_Data
			tagInfo = "EF_Controller_Activity_Data";
		}
		else if ( Arrays.equals( new byte[]{ tag[ 0 ],tag[ 1 ] }, new byte[]{       0x05, 0x0d } ) ) {
			//	EF_Company_Activity_Data
			tagInfo = "EF_Company_Activity_Data";
		}
		else {
			tagInfo = "Unknown tag name";
		}

		// type of tag
		if ( tag[ 2 ] == 0x00 ) {
			// data tag
			tagInfo = tagInfo + " data tag";
		}
		else if ( tag[ 2 ] == 0x01 ) {
			// signature tag
			tagInfo = tagInfo + " signature tag";
		}
		else {
			// unknown tag
			tagInfo = tagInfo + " unknown tag type";
		}

		debugLogger.println( DebugLogger.LOGLEVEL_INFO, "[INFO] card data tag:" );
		debugLogger.printf( DebugLogger.LOGLEVEL_INFO, " [INFO] FID %02x %02x %02x, %s\n", tag[ 0 ], tag[ 1 ], tag[ 2 ], tagInfo );
	}

	/**
	 * Indicates if the given tag in the first two bytes of the byte array is a valid File ID
	 * of a driver card, workshop card, control card or company card.
	 * 
	 * @return	true if the given tag is a valid File ID 
	 */
	public boolean isValidFileID( byte[] tag ) {
		if ( ( Arrays.equals( new byte[]{ tag[ 0 ],tag[ 1 ] }, new byte[]{       0x00, 0x02 } ) ) |		//	EF_ICC
			 ( Arrays.equals( new byte[]{ tag[ 0 ],tag[ 1 ] }, new byte[]{       0x00, 0x05 } ) ) |		//	EF_IC
			 ( Arrays.equals( new byte[]{ tag[ 0 ],tag[ 1 ] }, new byte[]{ (byte)0xc1, 0x00 } ) ) |		//	EF_Card_Certificate
			 ( Arrays.equals( new byte[]{ tag[ 0 ],tag[ 1 ] }, new byte[]{ (byte)0xc1, 0x08 } ) ) |		//	EF_CA_Certificate
			 ( Arrays.equals( new byte[]{ tag[ 0 ],tag[ 1 ] }, new byte[]{       0x05, 0x01 } ) ) |		//	EF_Application_Identification
			 ( Arrays.equals( new byte[]{ tag[ 0 ],tag[ 1 ] }, new byte[]{       0x05, 0x20 } ) ) |		//	EF_Identification
			 ( Arrays.equals( new byte[]{ tag[ 0 ],tag[ 1 ] }, new byte[]{       0x05, 0x0e } ) ) |		//	EF_Card_Download (driver card)
			 ( Arrays.equals( new byte[]{ tag[ 0 ],tag[ 1 ] }, new byte[]{       0x05, 0x21 } ) ) |		//	EF_Driving_Licence_Info
			 ( Arrays.equals( new byte[]{ tag[ 0 ],tag[ 1 ] }, new byte[]{       0x05, 0x02 } ) ) |		//	EF_Events_Data
			 ( Arrays.equals( new byte[]{ tag[ 0 ],tag[ 1 ] }, new byte[]{       0x05, 0x03 } ) ) |		//	EF_Faults_Data
			 ( Arrays.equals( new byte[]{ tag[ 0 ],tag[ 1 ] }, new byte[]{       0x05, 0x04 } ) ) |		//	EF_Driver_Activity_Data
			 ( Arrays.equals( new byte[]{ tag[ 0 ],tag[ 1 ] }, new byte[]{       0x05, 0x05 } ) ) |		//	EF_Vehicles_Used
			 ( Arrays.equals( new byte[]{ tag[ 0 ],tag[ 1 ] }, new byte[]{       0x05, 0x06 } ) ) |		//	EF_Places
			 ( Arrays.equals( new byte[]{ tag[ 0 ],tag[ 1 ] }, new byte[]{       0x05, 0x07 } ) ) |		//	EF_Current_Usage
			 ( Arrays.equals( new byte[]{ tag[ 0 ],tag[ 1 ] }, new byte[]{       0x05, 0x08 } ) ) |		//	EF_Control_Activity_Data
			 ( Arrays.equals( new byte[]{ tag[ 0 ],tag[ 1 ] }, new byte[]{       0x05, 0x22 } ) ) |		//	EF_Specific_Conditions
			 ( Arrays.equals( new byte[]{ tag[ 0 ],tag[ 1 ] }, new byte[]{       0x05, 0x09 } ) ) |		//	EF_Card_Download (workshop card)
			 ( Arrays.equals( new byte[]{ tag[ 0 ],tag[ 1 ] }, new byte[]{       0x05, 0x0a } ) ) |		//	EF_Calibration
			 ( Arrays.equals( new byte[]{ tag[ 0 ],tag[ 1 ] }, new byte[]{       0x05, 0x0b } ) ) |		//	EF_Sensor_Installation_Data
			 ( Arrays.equals( new byte[]{ tag[ 0 ],tag[ 1 ] }, new byte[]{       0x05, 0x0c } ) ) |		//	EF_Controller_Activity_Data
			 ( Arrays.equals( new byte[]{ tag[ 0 ],tag[ 1 ] }, new byte[]{       0x05, 0x0d } ) ) )	{	//	EF_Company_Activity_Data

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
	 * Tries to identify the given data by tag/file id and to parse the given data (value).
	 * If successful the data is added internally to the CardData for XML
	 * dump. 
	 * 
	 * @param tag		byte array with tag value & data/signature indicator (2 + 1 bytes)
	 * @param length	byte array that contains the length of the value (2 bytes)
	 * @param value		byte array with data

	 * @return			true if parsing of the given byte array was successful
	 */
	public boolean add( byte[] tag, byte[] length, byte[] value ) {
		boolean parseresult = false;
		Object[] tmp = { tag, value };
		data.add( tmp );

		switch( efstate ) {
			case EF_DATA_ENTITY:
				parseresult = true; // default for all the following

				if ( Arrays.equals( tag, new byte[]{ 0x00, 0x02, 0x00 } ) ) {
					// EF_ICC
					printTagInfo( tag );
					ef_icc = new EF_ICC( value );
					dispatcherQueue.add( ef_icc );
					tag2 = new byte[]{ 0x00, 0x02 };
					efstate = EF_DATA_ENTITY;
				}
				else if ( Arrays.equals( tag, new byte[]{ 0x00, 0x05, 0x00} ) ) {
					// EF_IC
					printTagInfo( tag );
					ef_ic = new EF_IC( value );
					dispatcherQueue.add( ef_ic );
					tag2 = new byte[]{ 0x00, 0x05 };
					efstate = EF_DATA_ENTITY;
				}
				else if ( Arrays.equals( tag, new byte[]{ (byte)0xc1, 0x00, 0x00} ) ) {
					// EF_Card_Certificate
					printTagInfo( tag );
					ef_card_certificate = new EF_Card_Certificate( value );
					dispatcherQueue.add( ef_card_certificate );
					tag2 = new byte[]{ (byte)0xc1, 0x00 };
					efstate = EF_DATA_ENTITY;
					ef_card_value = value;
				}
				else if ( Arrays.equals( tag, new byte[]{ (byte)0xc1, 0x08, 0x00 } ) ) {
					// EF_CA_Certificate
					printTagInfo( tag );
					ef_ca_certificate = new EF_CA_Certificate( value );
					dispatcherQueue.add( ef_ca_certificate );
					tag2 = new byte[]{ (byte)0xc1, 0x08 };
					efstate = EF_DATA_ENTITY;

					if ( super.isECPublicKeyAvailable() == true ) {
						// check CA certificate
						debugLogger.println( DebugLogger.LOGLEVEL_INFO, " [INFO] checking CA certificate" );
						caCertificate = new SecurityCertificateCheck();

						// set public key
						try {
							caCertificate.setRSAPublicKey( super.getECPubKeyModulus(), super.getECPubKeyExponent() );

							// decipher CA certificate
							debugLogger.println( DebugLogger.LOGLEVEL_INFO, " [INFO] deciphering CA certificate" );
							caCertificate.decipherCertificateSignature( value );
							genuineCACertificate = caCertificate.isGenuineCertificate();

							debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  certificate          [%3d]: %s\n", value.length, new SecurityByteArrayTools().ByteArrayToHexString( value ) );
							debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  signature            [%3d]: %s\n", caCertificate.getDecipheredSignatureByteArray().length, caCertificate.getDecipheredSignatureHexString() );
							debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  valid signature?     [---]: %s\n", caCertificate.isValidSignature());
							debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  c_n                  [%3d]: %s\n", caCertificate.getC_NByteArray().length, caCertificate.getC_NHexString() );
							debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  car                  [%3d]: %s\n", caCertificate.getCARByteArray().length, caCertificate.getCARHexString() );
							debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  c_r                  [%3d]: %s\n", caCertificate.getC_RByteArray().length, caCertificate.getC_RHexString() );
							debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  c_0                  [%3d]: %s\n", caCertificate.getC_0ByteArray().length, caCertificate.getC_0HexString() );
							debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  h_0                  [%3d]: %s\n", caCertificate.getH_0ByteArray().length, caCertificate.getH_0HexString() );
							debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  calculated SHA1 hash [%3d]: %s\n", caCertificate.getC_0SHAHashByteArray().length, caCertificate.getC_0SHAHashHexString() );
							debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  genuine certificate? [---]: %s\n", caCertificate.isGenuineCertificate());

							if ( genuineCACertificate == true ) {
								debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  certificate content:");
								debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  cpi                  [%3d]: %s\n", caCertificate.getCertificateContent().getCPIByteArray().length, caCertificate.getCertificateContent().getCPIHexString() );
								debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  car                  [%3d]: %s\n", caCertificate.getCertificateContent().getCARByteArray().length, caCertificate.getCertificateContent().getCARHexString() );
								debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  cha                  [%3d]: %s\n", caCertificate.getCertificateContent().getCHAByteArray().length, caCertificate.getCertificateContent().getCHAHexString() );
								debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  eov                  [%3d]: %s\n", caCertificate.getCertificateContent().getEOVByteArray().length, caCertificate.getCertificateContent().getEOVHexString() );
								debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  chr                  [%3d]: %s\n", caCertificate.getCertificateContent().getCHRByteArray().length, caCertificate.getCertificateContent().getCHRHexString() );
								debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  n                    [%3d]: %s\n", caCertificate.getCertificateContent().getNByteArray().length, caCertificate.getCertificateContent().getNHexString() );
								debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  e                    [%3d]: %s\n", caCertificate.getCertificateContent().getEByteArray().length, caCertificate.getCertificateContent().getEHexString() );

								debugLogger.println( DebugLogger.LOGLEVEL_INFO, " [INFO] CA certificate is valid" );
								if ( caCertificate.getCertificateContent().getEOVHexString().compareTo( "FFFFFFFF") != 0 ) {
									debugLogger.print( DebugLogger.LOGLEVEL_INFO, " [INFO] CA certificate end of validity:\n " );
									new TimeReal( convertIntoUnsigned4ByteInt( caCertificate.getCertificateContent().getEOVByteArray() ) );
								}

								// check card certificate
								debugLogger.println( DebugLogger.LOGLEVEL_INFO, " [INFO] checking Card certificate" );
								cardCertificate = new SecurityCertificateCheck();

								try {
									// set public key
									cardCertificate.setRSAPublicKey( caCertificate.getCertificateContent().getNHexString(), caCertificate.getCertificateContent().getEHexString() );

									// decipher card certificate
									debugLogger.println( DebugLogger.LOGLEVEL_INFO, " [INFO] deciphering Card certificate" );
									cardCertificate.decipherCertificateSignature( ef_card_value );
									genuineCardCertificate = cardCertificate.isGenuineCertificate();

									debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  certificate          [%3d]: %s\n", ef_card_value.length, new SecurityByteArrayTools().ByteArrayToHexString( ef_card_value ) );
									debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  signature            [%3d]: %s\n", cardCertificate.getDecipheredSignatureByteArray().length, cardCertificate.getDecipheredSignatureHexString() );
									debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  valid signature?     [---]: %s\n", cardCertificate.isValidSignature() );
									debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  c_n                  [%3d]: %s\n", cardCertificate.getC_NByteArray().length, cardCertificate.getC_NHexString() );
									debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  car                  [%3d]: %s\n", cardCertificate.getCARByteArray().length, cardCertificate.getCARHexString() );
									debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  c_r                  [%3d]: %s\n", cardCertificate.getC_RByteArray().length, cardCertificate.getC_RHexString() );
									debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  c_0                  [%3d]: %s\n", cardCertificate.getC_0ByteArray().length, cardCertificate.getC_0HexString() );
									debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  h_0                  [%3d]: %s\n", cardCertificate.getH_0ByteArray().length, cardCertificate.getH_0HexString() );
									debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  calculated SHA1 hash [%3d]: %s\n", cardCertificate.getC_0SHAHashByteArray().length, cardCertificate.getC_0SHAHashHexString() );
									debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  genuine certificate? [---]: %s\n", cardCertificate.isGenuineCertificate() );

									if ( genuineCardCertificate == true ) {
										debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  certificate content:");
										debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  cpi                  [%3d]: %s\n", cardCertificate.getCertificateContent().getCPIByteArray().length, cardCertificate.getCertificateContent().getCPIHexString() );
										debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  car                  [%3d]: %s\n", cardCertificate.getCertificateContent().getCARByteArray().length, cardCertificate.getCertificateContent().getCARHexString() );
										debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  cha                  [%3d]: %s\n", cardCertificate.getCertificateContent().getCHAByteArray().length, cardCertificate.getCertificateContent().getCHAHexString() );
										debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  eov                  [%3d]: %s\n", cardCertificate.getCertificateContent().getEOVByteArray().length, cardCertificate.getCertificateContent().getEOVHexString() );
										debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  chr                  [%3d]: %s\n", cardCertificate.getCertificateContent().getCHRByteArray().length, cardCertificate.getCertificateContent().getCHRHexString() );
										debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  n                    [%3d]: %s\n", cardCertificate.getCertificateContent().getNByteArray().length, cardCertificate.getCertificateContent().getNHexString() );
										debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  e                    [%3d]: %s\n", cardCertificate.getCertificateContent().getEByteArray().length, cardCertificate.getCertificateContent().getEHexString() );

										debugLogger.println( DebugLogger.LOGLEVEL_INFO, " [INFO] Card certificate is valid" );
										if ( cardCertificate.getCertificateContent().getEOVHexString().compareTo( "FFFFFFFF") != 0 ) {
											debugLogger.print( DebugLogger.LOGLEVEL_INFO, " [INFO] Card certificate end of validity:\n " );
											new TimeReal( convertIntoUnsigned4ByteInt( cardCertificate.getCertificateContent().getEOVByteArray() ) );
										}

										// check saved application identification signature now
										if ( verifyDataSignatureIntegrity( cardCertificate.getCertificateContent().getNHexString(), cardCertificate.getCertificateContent().getEHexString(), new byte[]{ 0x05, 0x01 }, ef_application_identification_value, ef_application_identification_signature ) == false ) {
											invalid_data_found = true;
										}
									}
									else {
										debugLogger.println( DebugLogger.LOGLEVEL_ERROR, "[ERROR] Card certificate is invalid" );
										invalid_data_found = true;
									}
								}
								catch ( NoSuchAlgorithmException nsae ) {
									genuineCardCertificate = false;
								}
								catch ( InvalidKeySpecException ikse ) {
									genuineCardCertificate = false;
								}
							}
							else {
								debugLogger.println( DebugLogger.LOGLEVEL_ERROR, "[ERROR] CA certificate is invalid" );
								invalid_data_found = true;
							}
						}
						catch ( NoSuchAlgorithmException nsae ) {
							genuineCACertificate = false;
						}
						catch ( InvalidKeySpecException ikse ) {
							genuineCACertificate = false;
						}
					}
				}
				else if (Arrays.equals( tag, new byte[]{ 0x05, 0x01, 0x00 } ) ) {
					// EF_Application_Identification
					printTagInfo( tag );
					ef_application_identification = new EF_Application_Identification( value );
					cardType = ef_application_identification.getCardType();
					dispatcherQueue.add( ef_application_identification );
					tag2 = new byte[]{ 0x05, 0x01 };
					efstate = EF_SIGNATURE_ENTITY;

					// save application identification for other
					// classes that need information from it
					ef_application_identification_value = value;
				}
				else if (Arrays.equals( tag, new byte[]{ (byte)0x05, 0x20, 0x00 } ) ) {
					// EF_Identification
					printTagInfo( tag );
					ef_identification = new EF_Identification( value, cardType );
					dispatcherQueue.add( ef_identification );
					tag2 = new byte[]{ 0x05, 0x20 };
					efstate = EF_SIGNATURE_ENTITY;
				}
				else if (Arrays.equals( tag, new byte[]{ (byte)0x05, 0x0e, 0x00} ) ) {
					// EF_Card_Download (driver card)
					printTagInfo( tag );
					ef_card_download = new EF_Card_Download( value, cardType );
					dispatcherQueue.add( ef_card_download );
					tag2 = new byte[]{ 0x05, 0x0e };
					efstate = EF_SIGNATURE_ENTITY;
				}
				else if (Arrays.equals( tag, new byte[]{ (byte)0x05, 0x21, 0x00} ) ) {
					// EF_Driving_Licence_Info
					printTagInfo( tag );
					ef_driving_licence_info = new EF_Driving_Licence_Info( value );
					dispatcherQueue.add( ef_driving_licence_info );
					tag2 = new byte[]{ 0x05, 0x21 };
					efstate = EF_SIGNATURE_ENTITY;
				}
				else if (Arrays.equals( tag, new byte[]{ (byte)0x05, 0x02, 0x00} ) ) {
					// EF_Events_Data
					printTagInfo( tag );
					ef_events_data = new EF_Events_Data( value, ef_application_identification.getNoOfEventsPerType() );
					dispatcherQueue.add( ef_events_data );
					tag2 = new byte[]{ 0x05, 0x02 };
					efstate = EF_SIGNATURE_ENTITY;
				}
				else if ( Arrays.equals( tag, new byte[]{ (byte)0x05, 0x03, 0x00} ) ) {
					// EF_Faults_Data
					printTagInfo( tag );
					ef_faults_data = new EF_Faults_Data( value, ef_application_identification.getNoOfFaultsPerType() );
					dispatcherQueue.add( ef_faults_data );
					tag2 = new byte[]{ 0x05, 0x03 };
					efstate = EF_SIGNATURE_ENTITY;
				}
				else if ( Arrays.equals( tag, new byte[]{ (byte)0x05, 0x04, 0x00} ) ) {
					// EF_Driver_Activity_Data
					printTagInfo( tag );
					ef_driver_activity = new EF_Driver_Activity_Data( value, ef_application_identification.getActivityStructureLength() );
					dispatcherQueue.add( ef_driver_activity );
					tag2 = new byte[]{ 0x05, 0x04 };
					efstate = EF_SIGNATURE_ENTITY;
				}
				else if ( Arrays.equals( tag, new byte[]{ (byte)0x05, 0x05, 0x00} ) ) {
					// EF_Vehicles_Used
					printTagInfo( tag );
					ef_vehicles_used = new EF_Vehicles_Used( value, ef_application_identification.getNoOfCardVehicleRecords() );
					dispatcherQueue.add( ef_vehicles_used );
					tag2 = new byte[]{ 0x05, 0x05 };
					efstate = EF_SIGNATURE_ENTITY;
				}
				else if ( Arrays.equals( tag, new byte[]{ (byte)0x05, 0x06, 0x00 } ) ) {
					// EF_Places
					printTagInfo( tag );
					ef_places = new EF_Places( value, ef_application_identification.getNoOfCardPlaceRecords() );
					dispatcherQueue.add( ef_places );
					tag2 = new byte[]{ 0x05, 0x06 };
					efstate = EF_SIGNATURE_ENTITY;
				}
				else if ( Arrays.equals( tag, new byte[]{ (byte)0x05, 0x07, 0x00 } ) ) {
					// EF_Current_Usage
					printTagInfo( tag );
					ef_current_usage = new EF_Current_Usage( value );
					dispatcherQueue.add( ef_current_usage );
					tag2 = new byte[]{ 0x05, 0x07 };
					efstate = EF_SIGNATURE_ENTITY;
				}
				else if ( Arrays.equals( tag, new byte[]{ (byte)0x05, 0x08, 0x00 } ) ) {
					// EF_Control_Activity_Data
					printTagInfo( tag );
					ef_control_activity_data = new EF_Control_Activity_Data( value );
					dispatcherQueue.add( ef_control_activity_data );
					tag2 = new byte[]{ 0x05, 0x08 };
					efstate = EF_SIGNATURE_ENTITY;
				}
				else if ( Arrays.equals( tag, new byte[]{ (byte)0x05, 0x22, 0x00 } ) ) {
					// EF_Specific_Conditions
					printTagInfo( tag );
					ef_specific_conditions = new EF_Specific_Conditions( value, cardType );
					dispatcherQueue.add( ef_specific_conditions );
					tag2 = new byte[]{ 0x05, 0x22 };
					efstate = EF_SIGNATURE_ENTITY;
				}
				else if ( Arrays.equals( tag, new byte[]{ (byte)0x05, 0x09, 0x00 } ) ) {
					// EF_Card_Download (workshop card)
					printTagInfo( tag );
					ef_card_download = new EF_Card_Download( value, cardType );
					dispatcherQueue.add( ef_card_download );
					tag2 = new byte[]{ 0x05, 0x09 };
					efstate = EF_SIGNATURE_ENTITY;
				}
				else if ( Arrays.equals( tag, new byte[]{ (byte)0x05, 0x0a, 0x00 } ) ) {
					// EF_Calibration
					printTagInfo( tag );
					ef_calibration = new EF_Calibration( value, ef_application_identification.getNoOfCalibrationRecords() );
					dispatcherQueue.add( ef_calibration );
					tag2 = new byte[]{ 0x05, 0x0a };
					efstate = EF_SIGNATURE_ENTITY;
				}
				else if ( Arrays.equals( tag, new byte[]{ (byte)0x05, 0x0b, 0x00 } ) ) {
					// EF_Sensor_Installation_Data
					printTagInfo( tag );
					ef_sensor_installation_data = new EF_Sensor_Installation_Data( value );
					dispatcherQueue.add( ef_sensor_installation_data );
					tag2 = new byte[]{ 0x05, 0x0b };
					efstate = EF_SIGNATURE_ENTITY;
				}
				else if ( Arrays.equals( tag, new byte[]{ (byte)0x05, 0x0c, 0x00 } ) ) {
					// EF_Controller_Activity_Data
					printTagInfo( tag );
					ef_controller_activity_data = new EF_Controller_Activity_Data( value, ef_application_identification.getNoOfControlActivityRecords() );
					dispatcherQueue.add( ef_controller_activity_data );
					tag2 = new byte[]{ 0x05, 0x0c };
					efstate = EF_SIGNATURE_ENTITY;
				}
				else if ( Arrays.equals( tag, new byte[]{ (byte)0x05, 0x0d, 0x00 } ) ) {
					// EF_Company_Activity_Data
					printTagInfo( tag );
					ef_company_activity_data = new EF_Company_Activity_Data( value, ef_application_identification.getNoOfCompanyActivityRecords() );
					dispatcherQueue.add( ef_company_activity_data );
					tag2 = new byte[]{ 0x05, 0x0d };
					efstate = EF_SIGNATURE_ENTITY;
				}
				else {
					debugLogger.print( DebugLogger.LOGLEVEL_ERROR, "[ERROR] unknown tag, " );
					debugLogger.printf( DebugLogger.LOGLEVEL_ERROR, "%02x", (int)( tag[ 0 ] & 0xff ) );
					debugLogger.printf( DebugLogger.LOGLEVEL_ERROR, "%02x", (int)( tag[ 1 ] & 0xff ) );
					debugLogger.printf( DebugLogger.LOGLEVEL_ERROR, "%02x\n", (int)( tag[ 2 ] & 0xff ) );
					parseresult = false;
				}

				// save data for signature check
				ef_value = value;
				break;

			case EF_SIGNATURE_ENTITY:
				if ( tag[ 2 ] != 0x01 ) {
					// not a signature tag
					debugLogger.printf( DebugLogger.LOGLEVEL_ERROR, "[ERROR] expected signature tag %02x %02x 01, got", tag2[ 0 ], tag2[ 1 ] );
					debugLogger.printf( DebugLogger.LOGLEVEL_ERROR, "%02x", (int)( tag[ 0 ] & 0xff ) );
					debugLogger.printf( DebugLogger.LOGLEVEL_ERROR, "%02x", (int)( tag[ 1 ] & 0xff ) );
					debugLogger.printf( DebugLogger.LOGLEVEL_ERROR, "%02x\n", (int)( tag[ 2 ] & 0xff ) );
					parseresult = false;
				}
				else {
					printTagInfo( tag );

					if ( Arrays.equals( tag2, new byte[]{ tag[ 0 ], tag[ 1 ] } ) ) {
						debugLogger.println( DebugLogger.LOGLEVEL_INFO, " [INFO] signature belongs to previous tag" );
						parseresult = true;

						if ( Arrays.equals( tag, new byte[]{ 0x05, 0x01, 0x01 } ) && ( super.isECPublicKeyAvailable() == true ) ) {
							// save application identification signature
							debugLogger.println( DebugLogger.LOGLEVEL_INFO, " [INFO] saving this signature. Checking will be done when the required certificates are available" );
							ef_application_identification_signature = value;
						}

						if ( genuineCardCertificate == true ) {
							if ( verifyDataSignatureIntegrity( cardCertificate.getCertificateContent().getNHexString(), cardCertificate.getCertificateContent().getEHexString(), tag, ef_value, value ) == false ) {
								invalid_data_found = true;;
							}
						}
					}
					else {
						parseresult = false;
					}
				}

				efstate = EF_DATA_ENTITY;
				break;

			default:
				parseresult = false;
				break;
		}

		return parseresult;
	}

	/**
	 * Dumps the collected tags as XML and returns it as string.
	 * 
	 * @return		the dumped XML string
	 */
	@Override
	public String generateXML() {
		Document doc = new Document();

		Element root = generateXMLElement( this.getClass().getSimpleName() );
		doc.setRootElement( root );

		Format fmt = Format.getPrettyFormat();
		fmt.setLineSeparator( "\n" );
		fmt.setEncoding( "UTF-8" );
		fmt.setExpandEmptyElements( true );

		XMLOutputter serializer = new XMLOutputter( fmt );

		String cardDTD;

		if ( cardType == EquipmentType.DRIVER_CARD ) {
			cardDTD = "C_Driver_Card.dtd";
		}
		else if ( cardType == EquipmentType.WORKSHOP_CARD ) {
			cardDTD = "C_Workshop_Card.dtd";
		}
		else if ( cardType == EquipmentType.CONTROL_CARD ) {
			cardDTD = "C_Control_Card.dtd";
		}
		else if ( cardType == EquipmentType.COMPANY_CARD ) {
			cardDTD = "C_Company_Card.dtd";
		}
		else {
			cardDTD = "C_Unknown_Card.dtd";
		}

		doc.setDocType( new DocType( "CardData", cardDTD ) );

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
		doc.setRootElement( root );

		try { 
			FileOutputStream fos = new FileOutputStream( filename );

			Format fmt = Format.getPrettyFormat();
			fmt.setLineSeparator( "\n" );
			fmt.setEncoding( "UTF-8" );
			fmt.setExpandEmptyElements( true );

			XMLOutputter serializer = new XMLOutputter( fmt );

			String cardDTD;

			if ( cardType == EquipmentType.DRIVER_CARD ) {
				cardDTD = "C_Driver_Card.dtd";
			}
			else if ( cardType == EquipmentType.WORKSHOP_CARD ) {
				cardDTD = "C_Workshop_Card.dtd";
			}
			else if ( cardType == EquipmentType.CONTROL_CARD ) {
				cardDTD = "C_Control_Card.dtd";
			}
			else if ( cardType == EquipmentType.COMPANY_CARD ) {
				cardDTD = "C_Company_Card.dtd";
			}
			else {
				cardDTD = "C_Unknown_Card.dtd";
			}

			doc.setDocType( new DocType( "CardData", cardDTD ) );

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
