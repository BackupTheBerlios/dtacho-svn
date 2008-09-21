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

import org.jdom.Element;
import org.opendtacho.DDDQuery.DebugLogger;
import org.opendtacho.DDDQuery.internalData.DataClass;
import org.opendtacho.DDDQuery.internalData.DataTypes.*;

/**
 * Appendix 7, 2.2.6.1: Positive response transfer data overview
 * SID 76H, TREP 01H
 */
public class PRDT_Overview extends DataClass {

	/**
	 * Size of structure in bytes.
	 * Only valid after instantiation of the PRDT_Overview object.
	 */
	public final int size;

	private MemberStateCertificate memberStateCertificate;
	private VuCertificate vuCertificate;

	private VehicleIdentificationNumber vehicleIdentificationNumber;
	private VehicleRegistrationIdentification vehicleRegistrationIdentification;
	
	private CurrentDateTime currentDateTime;

	private VuDownloadablePeriod vuDownloadablePeriod;

	private CardSlotsStatus cardSlotsStatus;

	private VuDownloadActivityData vuDownloadActivityData;
	private VuCompanyLocksData vuCompanyLocksData;

	private VuControlActivityData vuControlActivityData;

	private DebugLogger debugLogger;


	/**
	 * Constructor for a PRDT_Overview object
	 * 
	 * @param	value	byte array of a PRDT_Overview structure
	 * 					whose data is used when the PRDT_Overview
	 * 					object is created.
	 */
	public PRDT_Overview( byte[] value ) {
		debugLogger = new DebugLogger();

		int offset1 = 194 + 194 + 17 + 1 + 14 + 4 + 4 + 4 + 1 + 4 + 18 + 36;
		memberStateCertificate = new MemberStateCertificate( arrayCopy( value, 0, 194 ) );
		vuCertificate = new VuCertificate( arrayCopy( value, 194, 194 ) );

		debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] * Vehicle identification:" );
		vehicleIdentificationNumber = new VehicleIdentificationNumber( arrayCopy( value, 388, 17 ) );

		vehicleRegistrationIdentification = new VehicleRegistrationIdentification( arrayCopy( value, 405, 15 ) );

		debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] * Current date and time:" );
		currentDateTime = new CurrentDateTime( arrayCopy( value, 420, 4 ) );

		debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] * Vu downloadable period:" );
		vuDownloadablePeriod = new VuDownloadablePeriod( arrayCopy( value, 424, 8 ) );

		debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] * Card slots status:" );
		cardSlotsStatus = new CardSlotsStatus( value[ 432 ] );

		debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Value: %02x\n", cardSlotsStatus.getCardSlotsStatus() );

		debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Driver slot:" );

		byte driverSlotsStatus = cardSlotsStatus.getDriverCardSlotsStatus();

		switch ( driverSlotsStatus ) {
			case CardSlotsStatus.NO_CARD_INSERTED:
				debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] no card inserted" );
				break;

			case CardSlotsStatus.DRIVER_CARD_INSERTED:
				debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] driver card inserted" );
				break;

			case CardSlotsStatus.WORKSHOP_CARD_INSERTED:
				debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] workshop card inserted" );
				break;

			case CardSlotsStatus.CONTROL_CARD_INSERTED:
				debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] control card inserted" );
				break;

			case CardSlotsStatus.COMPANY_CARD_INSERTED:
				debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] company card inserted" );
				break;

			default:
				debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] ????" );
				break;
		}

		debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Co-driver slot:" );

		byte coDriverSlotsStatus = cardSlotsStatus.getCoDriverCardSlotsStatus();

		switch ( coDriverSlotsStatus ) {
			case CardSlotsStatus.NO_CARD_INSERTED:
				debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] no card inserted" );
				break;

			case CardSlotsStatus.DRIVER_CARD_INSERTED:
				debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] driver card inserted" );
				break;

			case CardSlotsStatus.WORKSHOP_CARD_INSERTED:
				debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] workshop card inserted" );
				break;

			case CardSlotsStatus.CONTROL_CARD_INSERTED:
				debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] control card inserted" );
				break;

			case CardSlotsStatus.COMPANY_CARD_INSERTED:
				debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] company card inserted" );
				break;

			default:
				debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] ????" );
				break;
		}

		debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] * Previous VU download:" );
		vuDownloadActivityData = new VuDownloadActivityData( arrayCopy( value, 433, 58 ) );

		debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] * VU company locks data:" );
		int offset2 = 1 + convertIntoUnsigned1ByteInt( value[ offset1 ] ) * VuCompanyLocksRecord.size;
		vuCompanyLocksData = new VuCompanyLocksData( arrayCopy( value, offset1, offset2 ) );

		debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] * VU control activity data:" );
		int offset3 = 1 + convertIntoUnsigned1ByteInt( value[ offset1 + offset2 ] ) * VuControlActivityRecord.size;
		vuControlActivityData = new VuControlActivityData( arrayCopy( value, offset1 + offset2, offset3 ) );

		size = offset1 + offset2 + offset3;
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( this.getClass().getSimpleName() );

		node.addContent( memberStateCertificate.generateXMLElement( "memberStateCertificate" ) );
		node.addContent( vuCertificate.generateXMLElement( "vuCertificate" ) );

		node.addContent( vehicleIdentificationNumber.generateXMLElement( "vehicleIdentificationNumber" ) );
		node.addContent( vehicleRegistrationIdentification.generateXMLElement( "vehicleRegistrationIdentification" ) );

		node.addContent( currentDateTime.generateXMLElement( "currentDateTime" ) );

		node.addContent( vuDownloadablePeriod.generateXMLElement( "vuDownloadablePeriod" ) );

		node.addContent( cardSlotsStatus.generateXMLElement( "cardSlotsStatus" ) );

		node.addContent( vuDownloadActivityData.generateXMLElement( "vuDownloadActivityData" ) );

		node.addContent( vuCompanyLocksData.generateXMLElement( "vuCompanyLocksData" ) );

		node.addContent( vuControlActivityData.generateXMLElement( "vuControlActivityData" ) );

		return node;
	}
}
