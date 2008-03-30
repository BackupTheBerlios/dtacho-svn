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

import java.util.Iterator;
import java.util.Vector;

import org.digitalertachograph.DDDQuery.internalData.DataClass;
import org.jdom.Element;

/**
 * Information, stored in a company card, related to activities performed with the card.
 */
public class CompanyActivityData extends DataClass {
	/*
	 * CompanyActivityData ::= SEQUENCE {
	 * 	companyPointerNewestRecord INTEGER(0..NoOfCompanyActivityRecords-1), 2 bytes
	 * 	companyActivityRecords SET SIZE(NoOfCompanyActivityRecords) OF
	 * 		companyActivityRecord SEQUENCE {
	 * 				companyActivityType CompanyActivityType, 1 byte
	 * 				companyActivityTime TimeReal, 4 bytes
	 * 				cardNumberInformation FullCardNumber, 18 bytes
	 * 				vehicleRegistrationInformation VehicleRegistrationIdentification, 15 bytes
	 * 				downloadPeriodBegin TimeReal, 4 bytes
	 * 				downloadPeriodEnd TimeReal 4 bytes
	 * 		}
	 * }
	 * ---
	 * NoOfCompanyActivityRecords ::= INTEGER(0..2^16-1)
	 * min.: 230
	 * max.: 520
	 */

	/**
	 * Size of structure in bytes.
	 * Only valid after instantiation of the CompanyActivityData object.
	 */
	public final int size;

	// create min. 230 vectors; will be automatically expanded at run time if required!
	private Vector<CompanyActivityRecord> companyActivityRecords = new Vector<CompanyActivityRecord>( 230 );


	/**
	 * Constructor for a CompanyActivityData object
	 * 
	 * @param	value	byte array of a CompanyActivityData structure
	 * 					whose data is used when the CompanyActivityData
	 * 					object is created.
	 */
	public CompanyActivityData( byte[] value, int noOfCompanyActivityRecords ) {
		int noOfValidCompanyActivityRecords = 0;

		for ( int i = 0; i < noOfCompanyActivityRecords; i += 1 ) {
			byte[] record = arrayCopy( value, 2 + ( i * CompanyActivityRecord.size ), CompanyActivityRecord.size );

			CompanyActivityRecord car = new CompanyActivityRecord( record );

			// only add entries with non-default values, i.e. skip empty entries
			if ( car.getCompanyActivityTime().getTimeReal() != 0 ) {
				companyActivityRecords.add( car );

				noOfValidCompanyActivityRecords += 1;
			}
		}

		size = 2 + noOfValidCompanyActivityRecords * CompanyActivityRecord.size;
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		Iterator<CompanyActivityRecord> iter = companyActivityRecords.iterator();
		while ( iter.hasNext() ) {
			CompanyActivityRecord car = (CompanyActivityRecord)iter.next();
			node.addContent( car.generateXMLElement( "companyActivityRecord" ) );
		}

		return node;
	}
}
