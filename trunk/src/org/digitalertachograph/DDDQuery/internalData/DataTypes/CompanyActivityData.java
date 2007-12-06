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

import java.util.Iterator;
import java.util.Vector;

import org.digitalertachograph.DDDQuery.internalData.DataClass;
import org.jdom.Element;

public class CompanyActivityData extends DataClass {
	/*
	 * CompanyActivityData ::= SEQUENCE {
	 * 	companyPointerNewestRecord INTEGER(0..NoOfCompanyActivityRecords-1), 2byte
	 * 	companyActivityRecords SET SIZE(NoOfCompanyActivityRecords) OF
	 * 		companyActivityRecord SEQUENCE {
	 * 				companyActivityType CompanyActivityType,
	 * 				companyActivityTime TimeReal,
	 * 				cardNumberInformation FullCardNumber,
	 * 				vehicleRegistrationInformation VehicleRegistrationIdentification,
	 * 				downloadPeriodBegin TimeReal,
	 * 				downloadPeriodEnd TimeReal
	 * 		}
	 * }
	 */

	private int companyPointerNewestRecord;
	private Vector<CompanyActivityRecord> companyActivityRecords = new Vector();
	
	public CompanyActivityData(byte[] value){
		companyPointerNewestRecord = convertIntoUnsigned2ByteInt( arrayCopy(value, 0, 2));
		for (int i = 2; i < value.length; i+= 46) {
			byte[] record = arrayCopy(value, i, 46);
			CompanyActivityRecord tmp = new CompanyActivityRecord(record);
			companyActivityRecords.add( tmp );
		}
	}
	
	@Override
	public Element generateXMLElement(String name) {
		Element node = new Element(name);
		node.addContent( new Element("companyPointerNewestRecord").setText(Integer.toString(companyPointerNewestRecord)));
		Iterator iter = companyActivityRecords.iterator();
		while (iter.hasNext()) {
			CompanyActivityRecord car = (CompanyActivityRecord) iter.next();
			node.addContent( car.generateXMLElement("companyActivityRecord"));
		}
		
		return node;
	
	}
}
