/*   Copyright (C) 2007, Martin Barth

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

public class CardDrivingLicenceInformation extends DataClass {
	/*
	 * CardDrivingLicenceInformation ::= SEQUENCE {
	 * 	drivingLicenceIssuingAuthority Name, 36 
	 * 	drivingLicenceIssuingNation NationNumeric, 1 
	 * 	drivingLicenceNumber IA5String(SIZE(16)), 16
	 * }
	 */
	
	private Name drivingLicenceIssuingAuthority;
	private NationNumeric drivingLicenceIssuingNation;
	private String drivingLicenceNumber;
	
	public CardDrivingLicenceInformation(byte[] value){
		drivingLicenceIssuingAuthority = new Name( arrayCopy(value, 0, 36));
		drivingLicenceIssuingNation = new NationNumeric(value[36]);
		drivingLicenceNumber = new String( arrayCopy(value, 37, 16));
	}

	public Name getDrivingLicenceIssuingAuthority() {
		return drivingLicenceIssuingAuthority;
	}

	public void setDrivingLicenceIssuingAuthority(
			Name drivingLicenceIssuingAuthority) {
		this.drivingLicenceIssuingAuthority = drivingLicenceIssuingAuthority;
	}

	public NationNumeric getDrivingLicenceIssuingNation() {
		return drivingLicenceIssuingNation;
	}

	public void setDrivingLicenceIssuingNation(
			NationNumeric drivingLicenceIssuingNation) {
		this.drivingLicenceIssuingNation = drivingLicenceIssuingNation;
	}

	public String getDrivingLicenceNumber() {
		return drivingLicenceNumber;
	}

	public void setDrivingLicenceNumber(String drivingLicenceNumber) {
		this.drivingLicenceNumber = drivingLicenceNumber;
	}
	
	@Override
	public Element generateXMLElement(String name) {
		Element node = new Element(name);
		node.addContent( drivingLicenceIssuingAuthority.generateXMLElement("drivingLicenceIssuingAuthority") );
		node.addContent( drivingLicenceIssuingNation.generateXMLElement("drivingLicenceIssuingNation") );

		Element drivingLicenceNumberElement = new Element("drivingLicenceNumber");
		drivingLicenceNumberElement.setText(drivingLicenceNumber);
		node.addContent( drivingLicenceNumberElement );
		
		return node;
	}

}
