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

import org.digitalertachograph.DDDQuery.internalData.DataTypes.*;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;
import java.util.Arrays;
import java.util.Iterator;
import java.security.*;
import java.security.spec.*;

import org.jdom.*;
import org.jdom.output.XMLOutputter;


/*
 * Appendix 7, 3.3 Card downloading describes which EFs have a following digital
 * signature and which EFs have none.
 */

/**
 * The data of a .DDD file.
 */
public class TachographData extends DataClass {
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
	
	public Vector<Object> data;

	private int cardType;
	
	private Vector<DataClass> dispatcherQueue;

	private static final int EF_DATA_ENTITY = 0;
	private static final int EF_SIGNATURE_ENTITY = 1;
	
	private int efstate = EF_DATA_ENTITY;
	private byte[] tag2;

	private CAPublicKey caPublicKey;
	private String ecPubKeyModulus;
	private String ecPubKeyExponent;

	private boolean ecPublicKeyAvailable;

	private CertificateCheck CACertificate;
	private boolean genuineCACertificate;

	private CertificateCheck CardCertificate;
	private boolean genuineCardCertificate;

	private byte[] ef_application_identification_value;
	private byte[] ef_application_identification_signature;
	private byte[] ef_card_value;
	private byte[] ef_value;

	private boolean invalid_data_found;

	/**
	 * Constructor for a TachographData object
	 */
	public TachographData() {
		super();
		data = new Vector<Object>();
		dispatcherQueue = new Vector<DataClass>();
		invalid_data_found = false;
	}

	/**
	 * Tries to load and initialise the European Pubic Key. If the key
	 * could be successfully initialised, signatures of data will be checked.
	 * 
	 * @return	true if the EC public key could be initialised
	 * 			false otherwise
	 * 	
	 */
	public boolean initECPublicKey() {
		try {
			// try to open EC Public Key
			FileInputStream ecPubKeyFIS = new FileInputStream( "EC_PK.bin" );
			int ecPKLength = ecPubKeyFIS.available();

			if ( ecPKLength != 144 ) {
				ecPubKeyFIS.close();
				ecPublicKeyAvailable = false;
				System.out.println( " [INFO] EC public key not found. Will skip signature checks." );
				
				return ecPublicKeyAvailable;
			}

			byte[] ecPublicKey = new byte[ ecPKLength ];
			ecPubKeyFIS.read( ecPublicKey );
			ecPubKeyFIS.close();

			caPublicKey = new CAPublicKey( ecPublicKey );
			ecPubKeyModulus = caPublicKey.getPublicKeyModulusHexString();
			ecPubKeyExponent = caPublicKey.getPublicKeyExponentHexString();
			
			ecPublicKeyAvailable = true;
			System.out.println( " [INFO] EC public key found. Signatures will be checked." );

			return ecPublicKeyAvailable;
		}
		catch ( FileNotFoundException fnfe ) {
			ecPublicKeyAvailable = false;
			System.out.println( " [INFO] EC public key not found. Will skip signature checks." );

			return ecPublicKeyAvailable;
		}
		catch ( IOException ioe ) {
			ecPublicKeyAvailable = false;
			System.out.println( " [INFO] EC public key not found. Will skip signature checks." );

			return ecPublicKeyAvailable;
		}
	}
	
	/**
	 * Verifies the integrity of the given data against the given signature
	 * 
	 * @param	signature	the signature of the data
	 * @param	data		byte array with the data to be checked
	 * @param	tag			byte array with the file id of the current data
	 *
	 * @return	true, if the integrity of the data could be verified
	 */
	private boolean verifiyDataSignatureIntegrity( byte[] signature, byte[] data, byte[] tag ) {
		// check data/signature integrity
		System.out.printf( " [INFO] verifying data/signature integrity of FID %02x %02x 00/01\n", tag[0], tag[1] );
		DataSignatureCheck efDataSignatureCheck = new DataSignatureCheck();

		try {
			// set public key
			efDataSignatureCheck.setRSAPublicKey( CardCertificate.getCertificateContent().getNHexString(), CardCertificate.getCertificateContent().getEHexString() );

			// decipher signature
			System.out.println( " [INFO] deciphering signature" );
			efDataSignatureCheck.decipherSignature( signature );

			// calculate data sha1 hash
			efDataSignatureCheck.setDataByteArray( data );

			System.out.printf( "  signature            [%3d]: %s\n", efDataSignatureCheck.getDecipheredSignatureByteArray().length, efDataSignatureCheck.getDecipheredSignatureHexString() );
			System.out.printf( "  data SHA1 hash       [%3d]: %s\n", efDataSignatureCheck.getDataSHAHashByteArray().length, efDataSignatureCheck.getDataSHAHashHexString() );
			System.out.printf( "  signature SHA1 hash  [%3d]: %s\n", efDataSignatureCheck.getDecipheredSignatureByteArray().length-108, efDataSignatureCheck.getDecipheredSignatureHexString().substring( 216, 256 ) );
			System.out.printf( "  data valid?          [---]: %s\n", efDataSignatureCheck.isValidData());

			if ( efDataSignatureCheck.isValidData() == true ) {
				System.out.println( " [INFO] data is valid. Data and signature SHA1 hash are equal." );
				return true;
			}
			else {
				System.out.println( " [ERROR] data is invalid. Data and signature SHA1 hash are not equal." );
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
	 * Prints the name, type file id of the given 3 byte tag
	 * 
	 * @param	tag		3 byte tag: 2 bytes file id, 1 byte tag type
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
		
		System.out.printf( this.getClass().getSimpleName() + ":\n [TAG] %s, FID %02x %02x %02x\n", tagInfo, tag[ 0 ], tag[ 1 ], tag[ 2 ] );
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
	 * If successful the data is added internally to the TachographData for XML
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
				parseresult = true; // default

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

					if ( ecPublicKeyAvailable == true ) {
						// check ca certificate
						System.out.println( " [INFO] checking CA certificate" );
						CACertificate = new CertificateCheck();

						// set public key
						try {
							CACertificate.setRSAPublicKey( ecPubKeyModulus, ecPubKeyExponent );

							// decipher ca certifcate
							System.out.println( " [INFO] deciphering CA certificate" );
							CACertificate.decipherCertificateSignature( value );
							genuineCACertificate = CACertificate.isGenuineCertificate();
							
							System.out.printf( "  certificate          [%3d]: %s\n", value.length, new ByteArrayTools().ByteArrayToHexString( value ) );
							System.out.printf( "  signature            [%3d]: %s\n", CACertificate.getDecipheredSignatureByteArray().length, CACertificate.getDecipheredSignatureHexString() );
							System.out.printf( "  valid signature?     [---]: %s\n", CACertificate.isValidSignature());
							System.out.printf( "  c_n                  [%3d]: %s\n", CACertificate.getC_NByteArray().length, CACertificate.getC_NHexString() );
							System.out.printf( "  car                  [%3d]: %s\n", CACertificate.getCARByteArray().length, CACertificate.getCARHexString() );
							System.out.printf( "  c_r                  [%3d]: %s\n", CACertificate.getC_RByteArray().length, CACertificate.getC_RHexString() );
							System.out.printf( "  c_0                  [%3d]: %s\n", CACertificate.getC_0ByteArray().length, CACertificate.getC_0HexString() );
							System.out.printf( "  h_0                  [%3d]: %s\n", CACertificate.getH_0ByteArray().length, CACertificate.getH_0HexString() );
							System.out.printf( "  calculated SHA1 hash [%3d]: %s\n", CACertificate.getC_0SHAHashByteArray().length, CACertificate.getC_0SHAHashHexString() );
							System.out.printf( "  genuine certificate? [---]: %s\n", CACertificate.isGenuineCertificate());

							if ( genuineCACertificate == true ) {
								System.out.println( "  certificate content:");
								System.out.printf( "  cpi                  [%3d]: %s\n", CACertificate.getCertificateContent().getCPIByteArray().length, CACertificate.getCertificateContent().getCPIHexString() );
								System.out.printf( "  car                  [%3d]: %s\n", CACertificate.getCertificateContent().getCARByteArray().length, CACertificate.getCertificateContent().getCARHexString() );
								System.out.printf( "  cha                  [%3d]: %s\n", CACertificate.getCertificateContent().getCHAByteArray().length, CACertificate.getCertificateContent().getCHAHexString() );
								System.out.printf( "  eov                  [%3d]: %s\n", CACertificate.getCertificateContent().getEOVByteArray().length, CACertificate.getCertificateContent().getEOVHexString() );
								System.out.printf( "  chr                  [%3d]: %s\n", CACertificate.getCertificateContent().getCHRByteArray().length, CACertificate.getCertificateContent().getCHRHexString() );
								System.out.printf( "  n                    [%3d]: %s\n", CACertificate.getCertificateContent().getNByteArray().length, CACertificate.getCertificateContent().getNHexString() );
								System.out.printf( "  e                    [%3d]: %s\n", CACertificate.getCertificateContent().getEByteArray().length, CACertificate.getCertificateContent().getEHexString() );

								System.out.println( " [INFO] CA certificate is valid" );
								if ( CACertificate.getCertificateContent().getEOVHexString().compareTo( "FFFFFFFF") != 0 ) {
									System.out.print( " [INFO] CA certificate end of validity:\n " );
									new TimeReal( convertIntoUnsigned4ByteInt( CACertificate.getCertificateContent().getEOVByteArray() ) );
								}
							
								// check card certificate
								System.out.println( " [INFO] checking Card certificate" );
								CardCertificate = new CertificateCheck();

								// set public key
								try {
									CardCertificate.setRSAPublicKey(CACertificate.getCertificateContent().getNHexString(), CACertificate.getCertificateContent().getEHexString() );
								
									// decipher card certifcate
									System.out.println( " [INFO] deciphering Card certificate" );
									CardCertificate.decipherCertificateSignature( ef_card_value );
									genuineCardCertificate = CardCertificate.isGenuineCertificate();

									System.out.printf( "  certificate          [%3d]: %s\n", ef_card_value.length, new ByteArrayTools().ByteArrayToHexString( ef_card_value ) );
									System.out.printf( "  signature            [%3d]: %s\n", CardCertificate.getDecipheredSignatureByteArray().length, CardCertificate.getDecipheredSignatureHexString() );
									System.out.printf( "  valid signature?     [---]: %s\n", CardCertificate.isValidSignature() );
									System.out.printf( "  c_n                  [%3d]: %s\n", CardCertificate.getC_NByteArray().length, CardCertificate.getC_NHexString() );
									System.out.printf( "  car                  [%3d]: %s\n", CardCertificate.getCARByteArray().length, CardCertificate.getCARHexString() );
									System.out.printf( "  c_r                  [%3d]: %s\n", CardCertificate.getC_RByteArray().length, CardCertificate.getC_RHexString() );
									System.out.printf( "  c_0                  [%3d]: %s\n", CardCertificate.getC_0ByteArray().length, CardCertificate.getC_0HexString() );
									System.out.printf( "  h_0                  [%3d]: %s\n", CardCertificate.getH_0ByteArray().length, CardCertificate.getH_0HexString() );
									System.out.printf( "  calculated SHA1 hash [%3d]: %s\n", CardCertificate.getC_0SHAHashByteArray().length, CardCertificate.getC_0SHAHashHexString() );
									System.out.printf( "  genuine certificate? [---]: %s\n", CardCertificate.isGenuineCertificate() );

									if ( genuineCardCertificate == true ) {
										System.out.println( "  certificate content:");
										System.out.printf( "  cpi                  [%3d]: %s\n", CardCertificate.getCertificateContent().getCPIByteArray().length, CardCertificate.getCertificateContent().getCPIHexString() );
										System.out.printf( "  car                  [%3d]: %s\n", CardCertificate.getCertificateContent().getCARByteArray().length, CardCertificate.getCertificateContent().getCARHexString() );
										System.out.printf( "  cha                  [%3d]: %s\n", CardCertificate.getCertificateContent().getCHAByteArray().length, CardCertificate.getCertificateContent().getCHAHexString() );
										System.out.printf( "  eov                  [%3d]: %s\n", CardCertificate.getCertificateContent().getEOVByteArray().length, CardCertificate.getCertificateContent().getEOVHexString() );
										System.out.printf( "  chr                  [%3d]: %s\n", CardCertificate.getCertificateContent().getCHRByteArray().length, CardCertificate.getCertificateContent().getCHRHexString() );
										System.out.printf( "  n                    [%3d]: %s\n", CardCertificate.getCertificateContent().getNByteArray().length, CardCertificate.getCertificateContent().getNHexString() );
										System.out.printf( "  e                    [%3d]: %s\n", CardCertificate.getCertificateContent().getEByteArray().length, CardCertificate.getCertificateContent().getEHexString() );

										System.out.println( " [INFO] Card certificate is valid" );
										if ( CardCertificate.getCertificateContent().getEOVHexString().compareTo( "FFFFFFFF") != 0 ) {
											System.out.print( " [INFO] Card certificate end of validity:\n " );
											new TimeReal( convertIntoUnsigned4ByteInt(CardCertificate.getCertificateContent().getEOVByteArray() ) );
										}

										// check saved application identification signature now
										if ( verifiyDataSignatureIntegrity( ef_application_identification_signature, ef_application_identification_value, new byte[]{ 0x05, 0x01 } ) == false ) {
											invalid_data_found = true;
										}
									}
									else {
										System.out.println( " [INFO] Card certificate is invalid" );
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
								System.out.println( " [ERROR] CA certificate is invalid" );
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
					
					// save application identification signature
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
					ef_driver_activity = new EF_Driver_Activity_Data(value);
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
					dispatcherQueue.add(ef_control_activity_data);
					tag2 = new byte[]{ 0x05, 0x08 };
					efstate = EF_SIGNATURE_ENTITY;
				}
				else if ( Arrays.equals( tag, new byte[]{ (byte)0x05, 0x22, 0x00 } ) ) {
					// EF_Specific_Conditions
					printTagInfo( tag );
					ef_specific_conditions = new EF_Specific_Conditions( value );
					dispatcherQueue.add(ef_specific_conditions);
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
					System.out.print(" [ERROR] unknown tag, " );
					System.out.printf( " %02x", (int)( tag[0] & 0xff ) );
					System.out.printf( " %02x", (int)( tag[1] & 0xff ) );
					System.out.printf( " %02x\n", (int)( tag[2] & 0xff ) );
					parseresult = false;
				}

				// save data for signature check
				ef_value = value;
				break;

			case EF_SIGNATURE_ENTITY:
				if ( tag[2] != 0x01 ) {
					// not a signature tag
					System.out.printf( " [ERROR] expected signature tag %02x %02x 01, got", tag2[0], tag2[1] );
					System.out.printf( " %02x", (int)( tag[0] & 0xff ) );
					System.out.printf( " %02x", (int)( tag[1] & 0xff ) );
					System.out.printf( " %02x\n", (int)( tag[2] & 0xff ) );
					parseresult = false;
				}
				else {
					printTagInfo( tag );

					if ( Arrays.equals( tag2, new byte[]{ tag[0], tag[1] } ) ) {
						System.out.println( " [INFO] signature belongs to previous tag" );
						parseresult = true;
						
						if ( Arrays.equals(tag, new byte[]{ 0x05, 0x01, 0x01 } ) ) {
							// save application identification signature
							System.out.println( " [INFO] saving this signature for later checking when required certificates are available" );
							ef_application_identification_signature = value;
						}
							
						if ( genuineCardCertificate == true ) {
							if ( verifiyDataSignatureIntegrity( value, ef_value, tag ) == false ) {
								invalid_data_found = true;;
							}
						}
					}
					else
						parseresult = false;
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
	 * Dumps all tags/file ids collected.
	 */
	public void printT() {
		Iterator<Object> it = data.iterator();
		while ( it.hasNext() ) {
			Object[] tmp = (Object[])it.next();
			System.out.print( "tag: " );
			for ( int i = 0; i < ( (byte[])tmp[ 0 ]).length; i++ )
				System.out.printf( " %02x", (int)( ( (byte[])tmp[ 0 ])[ i ] & 0xff ) );		
				System.out.println();
		}
	}
	
	/**
	 * Dumps all tags/file ids collected with its lengths.
	 */
	public void printTL() {
		Iterator<Object> it = data.iterator();
		while ( it.hasNext() ) {
			Object[] tmp = (Object[])it.next();
			System.out.print( "tag: " );
			for ( int i = 0; i < ( (byte[])tmp[ 0 ]).length; i++ )
				System.out.printf( " %02x", (int)( ( (byte[])tmp[ 0 ])[ i ] & 0xff ) );		

			System.out.println( "\t - length: " + ( (byte[])tmp[ 1 ] ).length );
		}
	}
	
	/**
	 * Dumps all tags/file ids collected with its lengths and values (data) as combined
	 * hex/ASCII dump with 16 bytes per line.
	 */
	public void printTV() {
		printTV( 16 );
	}

	private int hexdumpwidth;
	private byte[] hexdumpbyte;
	private int hexdumpcount;

	/**
	 * Dumps all tags/file ids collected with its lengths and values (data) as combined
	 * hex/ASCII dump with the given bytesperline bytes per line.
	 * 
	 * @param	bytesperline	number of bytes dumped per line
	 */
	public void printTV( int bytesperline ) {
		hexdumpwidth = bytesperline;
		hexdumpbyte = new byte[ hexdumpwidth ];
		hexdumpcount = 0;
		
		Iterator<Object> it = data.iterator();
		while ( it.hasNext() ){
			Object[] tmp = (Object[])it.next();

			// TAG
			System.out.print( "tag: " );

			for ( int i = 0; i < ( (byte[])tmp[ 0 ] ).length; i++ )
				System.out.printf( " %02x", (int)( ( (byte[])tmp[ 0 ] )[ i ] & 0xff) );		

			System.out.println( "\t - length: " + ( (byte[])tmp[ 1 ] ).length );

			// VALUE
			System.out.println( "value:" );

			for ( int i = 0; i < ( (byte[])tmp[1] ).length; i++ ) {
				hexdump( (byte)( ( (byte[])tmp[1] )[ i ] & 0xff) );
			}
			hexdump_flush();
			System.out.println();
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
			for ( int i = 0; i < hexdumpcount; i++ )
				System.out.printf( " %02x", hexdumpbyte[ i ] );		

			System.out.print( "  " );		

			for ( int i = 0; i < hexdumpwidth-hexdumpcount; i++ )
				System.out.print( "   " );		

			for( int i = 0; i < hexdumpcount; i++ ) {
				if ( ( hexdumpbyte[ i ] >= 0x20 ) && ( hexdumpbyte[i] < 0x7f ) )
					// print visible chars only
					System.out.print( (char)hexdumpbyte[ i ] );
				else
					// otherwise print a dot
					System.out.print( "." );		
			}
			
			hexdumpcount = 0;
			System.out.println();
		}
	}

	private String dddfilename;

	/**
	 * Sets the source file name that is written as reference to the XML output.
	 * Note that the source file name can be given with full path. The leading
	 * path will be omitted for the XML output.
	 * 
	 * @param	dddfilename		the source file name
	 */
	public void setDDDFileName( String dddfilename ) {
		this.dddfilename = dddfilename;
	}
	
	public Element generateXMLElement( String name ) {
		Element root = new Element( name );

		// write source file name without leading path
		root.addContent( new Element("sourceFileName").setText( new File( this.dddfilename ).getName() ) );
		
		Iterator<DataClass> dispaterIterator = dispatcherQueue.iterator();
		while( dispaterIterator.hasNext() ){
			DataClass dc = (DataClass)dispaterIterator.next();
			Element node = dc.generateXMLElement( dc.getClass().getSimpleName() );
			root.addContent( node );
		}
		
		return root;
	}

	/**
	 * Dumps the collected tags as XML.
	 * 
	 * @return		the dumped XML string
	 */
	public String generateXML() {
		Document doc = new Document();
		Element root = generateXMLElement( this.getClass().getSimpleName() );
		doc.setRootElement( root );
		
		XMLOutputter serializer = new XMLOutputter();
		return serializer.outputString( doc );
	}
	
	/**
	 * Dumps the collected tags as XML file.
	 * 
	 * @param	file		name of the XML file
	 */
	public void generateXML( String file ) {
		Document doc = new Document();

		Element root = generateXMLElement( this.getClass().getSimpleName() );
		doc.setRootElement( root );

		try { 
			FileOutputStream fos = new FileOutputStream( file );
			XMLOutputter serializer = new XMLOutputter();
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
