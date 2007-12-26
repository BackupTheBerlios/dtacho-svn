/*   Copyright (C) 2007, Martin Barth, Gerald Schnabel

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
	
	private CompanyActivityType companyActivityType;
	private TimeReal companyActivityTime;
	private FullCardNumber cardNumberInformation;
	private VehicleRegistrationIdentification vehicleRegistrationInformation;
	private TimeReal downloadPeriodBegin;
	private TimeReal downloadPeriodEnd;

	
	/**
	 * Constructor for a CompanyActivityRecord object
	 * 
	 * @param	value	byte array of a CompanyActivityRecord structure
	 * 					whose data is used when the CompanyActivityRecord
	 * 					object is created.
	 */
	public CompanyActivityRecord(byte[] value){
		companyActivityType = new CompanyActivityType( value[0] );
		companyActivityTime = new TimeReal( arrayCopy(value, 1, 4));
		cardNumberInformation = new FullCardNumber( arrayCopy(value, 5, 18));
		vehicleRegistrationInformation = new VehicleRegistrationIdentification( arrayCopy(value, 23, 15));
		downloadPeriodBegin = new TimeReal( arrayCopy(value, 38, 4));
		downloadPeriodEnd = new TimeReal( arrayCopy(value, 42, 4));
	}
	
	@Override
	public Element generateXMLElement(String name) {
		Element node = new Element(name);
		node.addContent( companyActivityType.generateXMLElement("companyActivityType"));
		node.addContent( companyActivityTime.generateXMLElement("companyActivityTime"));
		node.addContent( cardNumberInformation.generateXMLElement("cardNumberInformation"));
		node.addContent( vehicleRegistrationInformation.generateXMLElement("vehicleRegistrationInformation"));
		node.addContent( downloadPeriodBegin.generateXMLElement("downloadPeriodBegin"));
		node.addContent( downloadPeriodEnd.generateXMLElement("downloadPeriodEnd"));
		return node;
	}
}
