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

package org.digitalertachograph.DDDQuery.internalData.DataTypes;

import org.digitalertachograph.DDDQuery.internalData.DataClass;
import org.jdom.Element;

/**
 * Information, stored in a company card, related to activities performed with the card.
 */
public class CompanyActivityRecord extends DataClass {

	/*
	 * 	companyActivityRecord SEQUENCE {
	 * 		companyActivityType CompanyActivityType, 1 byte
	 * 		companyActivityTime TimeReal, 4 bytes
	 * 		cardNumberInformation FullCardNumber, 18 bytes
	 * 		vehicleRegistrationInformation VehicleRegistrationIdentification, 15 bytes
	 * 		downloadPeriodBegin TimeReal, 4 bytes
	 * 		downloadPeriodEnd TimeReal, 4 bytes
	 * 	}
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 46;

	private CompanyActivityType companyActivityType;
	private TimeReal companyActivityTime;
	private FullCardNumber cardNumberInformation;
	private VehicleRegistrationIdentification vehicleRegistrationInformation;
	private TimeReal downloadPeriodBegin;
	private TimeReal downloadPeriodEnd;

	
	/**
	 * Constructor for a CompanyActivityRecord object
	 */
	public CompanyActivityRecord() {
		companyActivityType = new CompanyActivityType();
		companyActivityTime = new TimeReal();
		cardNumberInformation = new FullCardNumber();
		vehicleRegistrationInformation = new VehicleRegistrationIdentification();
		downloadPeriodBegin = new TimeReal();
		downloadPeriodEnd = new TimeReal();
	}

	/**
	 * Constructor for a CompanyActivityRecord object
	 * 
	 * @param	value	byte array of a CompanyActivityRecord structure
	 * 					whose data is used when the CompanyActivityRecord
	 * 					object is created.
	 */
	public CompanyActivityRecord( byte[] value ) {
		companyActivityType = new CompanyActivityType( value[ 0 ] );
		companyActivityTime = new TimeReal( arrayCopy( value, 1, 4 ) );
		cardNumberInformation = new FullCardNumber( arrayCopy( value, 5, 18 ) );
		vehicleRegistrationInformation = new VehicleRegistrationIdentification( arrayCopy( value, 23, 15 ) );
		downloadPeriodBegin = new TimeReal( arrayCopy( value, 38, 4 ) );
		downloadPeriodEnd = new TimeReal( arrayCopy( value, 42, 4 ) );
	}
	
	/**
	 * Returns the type of the company activity of a CompanyActivityRecord object.
	 * 
	 * @return	the type of the company activity of the CompanyActivityRecord object
	 */
	public CompanyActivityType getCompanyActivityType() {
		return companyActivityType;
	}

	/**
	 * Sets the type of the company activity of a CompanyActivityRecord object.
	 * 
	 * @param	companyActivityType		the type of the company activity
	 * 									to be set for the CompanyActivityRecord object
	 */
	public void setCompanyActivityType( CompanyActivityType companyActivityType ) {
		this.companyActivityType = companyActivityType;
	}

	/**
	 * Returns the date and time of the company activity of a CompanyActivityRecord object.
	 * 
	 * @return	the date and time of the company activity of the CompanyActivityRecord object
	 */
	public TimeReal getCompanyActivityTime() {
		return companyActivityTime;
	}

	/**
	 * Sets the date and time of the company activity of a CompanyActivityRecord object.
	 * 
	 * @param	companyActivityTime		the date and time of the company activity
	 * 									to be set for the CompanyActivityRecord object
	 */
	public void setCompanyActivityTime( TimeReal companyActivityTime ) {
		this.companyActivityTime = companyActivityTime;
	}

	/**
	 * Returns the card number and the card issuing Member State of the card downloaded, if any
	 * of a CompanyActivityRecord object.
	 * 
	 * @return	the card number and the card issuing Member State of the card downloaded, if any
	 * 			of the CompanyActivityRecord object
	 */
	public FullCardNumber getCardNumberInformation() {
		return cardNumberInformation;
	}

	/**
	 * Sets the card number and the card issuing Member State of the card downloaded
	 * of a CompanyActivityRecord object.
	 * 
	 * @param	cardNumberInformation	the card number and the card issuing Member State of the card downloaded
	 * 									to be set for the CompanyActivityRecord object
	 */
	public void setCardNumberInformation( FullCardNumber cardNumberInformation ) {
		this.cardNumberInformation = cardNumberInformation;
	}

	/**
	 * Returns the registration number of the vehicle (VRN) and registering Member State
	 * of the vehicle downloaded or locked in or out of a CompanyActivityRecord object.
	 * 
	 * @return	the registration number of the vehicle (VRN) and registering Member State
	 * 			of the vehicle downloaded or locked in or out of the CompanyActivityRecord object
	 */
	public VehicleRegistrationIdentification getVehicleRegistrationInformation() {
		return vehicleRegistrationInformation;
	}

	/**
	 * Sets the registration number of the vehicle (VRN) and registering Member State
	 * of the vehicle downloaded or locked in or outof a CompanyActivityRecord object.
	 * 
	 * @param	vehicleRegistrationInformation	the registration number of the vehicle (VRN) and
	 * 											registering Member State of the vehicle downloaded
	 * 											or locked in or out to be set for the
	 * 											CompanyActivityRecord object
	 */
	public void setVehicleRegistrationInformation( VehicleRegistrationIdentification vehicleRegistrationInformation ) {
		this.vehicleRegistrationInformation = vehicleRegistrationInformation;
	}

	/**
	 * Returns the date and time of the beginning of a download period
	 * of a CompanyActivityRecord object.
	 * 
	 * @return	the date and time of the beginning of a download period
	 * 			of the CompanyActivityRecord object
	 */
	public TimeReal getDownloadPeriodBegin() {
		return downloadPeriodBegin;
	}

	/**
	 * Sets the date and time of the beginning of a download period
	 * of a CompanyActivityRecord object.
	 * 
	 * @param	downloadPeriodBegin		the date and time of the beginning of a download period
	 * 									to be set for the CompanyActivityRecord object
	 */
	public void setDownloadPeriodBegin( TimeReal downloadPeriodBegin ) {
		this.downloadPeriodBegin = downloadPeriodBegin;
	}

	/**
	 * Returns the date and time of the end of a download period
	 * of a CompanyActivityRecord object.
	 * 
	 * @return	the date and time of the end of a download period
	 * 			of the CompanyActivityRecord object
	 */
	public TimeReal getDownloadPeriodEnd() {
		return downloadPeriodEnd;
	}

	/**
	 * Sets the date and time of the end of a download period
	 * of a CompanyActivityRecord object.
	 * 
	 * @param	downloadPeriodEnd		the date and time of the end of a download period
	 * 									to be set for the CompanyActivityRecord object
	 */
	public void setDownloadPeriodEnd( TimeReal downloadPeriodEnd ) {
		this.downloadPeriodEnd = downloadPeriodEnd;
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );
		node.addContent( companyActivityType.generateXMLElement( "companyActivityType" ) );
		node.addContent( companyActivityTime.generateXMLElement( "companyActivityTime" ) );
		node.addContent( cardNumberInformation.generateXMLElement( "cardNumberInformation" ) );
		node.addContent( vehicleRegistrationInformation.generateXMLElement( "vehicleRegistrationInformation" ) );
		node.addContent( downloadPeriodBegin.generateXMLElement( "downloadPeriodBegin" ) );
		node.addContent( downloadPeriodEnd.generateXMLElement( "downloadPeriodEnd" ) );

		return node;
	}
}
