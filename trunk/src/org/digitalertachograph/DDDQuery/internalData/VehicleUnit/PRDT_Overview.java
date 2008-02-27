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

package org.digitalertachograph.DDDQuery.internalData.VehicleUnit;

import org.digitalertachograph.DDDQuery.internalData.DataClass;
import org.digitalertachograph.DDDQuery.internalData.DataTypes.*;
import org.jdom.Element;

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


	/**
	 * Constructor for a PRDT_Overview object
	 * 
	 * @param	value	byte array of a PRDT_Overview structure
	 * 					whose data is used when the PRDT_Overview
	 * 					object is created.
	 */
	public PRDT_Overview( byte[] value ) {
		int offset1 = 194 + 194 + 17 + 1 + 14 + 4 + 4 + 4 + 1 + 4 + 18 + 36;
		memberStateCertificate = new MemberStateCertificate( arrayCopy( value, 0, 194 ) );
		vuCertificate = new VuCertificate( arrayCopy( value, 194, 194 ) );

		vehicleIdentificationNumber = new VehicleIdentificationNumber( arrayCopy( value, 388, 17 ) );
		vehicleRegistrationIdentification = new VehicleRegistrationIdentification( arrayCopy( value, 405, 15 ) );
		
		currentDateTime = new CurrentDateTime( arrayCopy( value, 420, 4 ) );

		vuDownloadablePeriod = new VuDownloadablePeriod( arrayCopy( value, 424, 8 ) );

		cardSlotsStatus = new CardSlotsStatus( value[ 432 ] );

		vuDownloadActivityData = new VuDownloadActivityData( arrayCopy( value, 433, 58 ) );

		int offset2 = 1 + convertIntoUnsigned1ByteInt( value[ offset1 ] ) * VuCompanyLocksRecord.size;
		vuCompanyLocksData = new VuCompanyLocksData( arrayCopy( value, offset1, offset2 ) );

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