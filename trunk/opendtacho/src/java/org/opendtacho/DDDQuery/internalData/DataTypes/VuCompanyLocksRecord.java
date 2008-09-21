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

package org.opendtacho.DDDQuery.internalData.DataTypes;

import org.jdom.Element;
import org.opendtacho.DDDQuery.DebugLogger;
import org.opendtacho.DDDQuery.internalData.DataClass;

/**
 * Information, stored in a vehicle unit, related to one company lock.
 */
public class VuCompanyLocksRecord extends DataClass {
	/*
	 * VuCompanyLocksRecord ::= SEQUENCE {
	 * 	lockInTime TimeReal, 4 bytes
	 * 	lockOutTime TimeReal, 4 bytes
	 * 	companyName Name, 36 bytes
	 * 	companyAddress Address, 36 bytes
	 * 	companyCardNumber FullCardNumber, 18 bytes 
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 98;

	private TimeReal lockInTime;
	private TimeReal lockOutTime;
	private Name companyName;
	private Address companyAddress;
	private FullCardNumber companyCardNumber;

	private DebugLogger debugLogger;


	/**
	 * Constructor for a VuCompanyLocksRecord object
	 */
	public VuCompanyLocksRecord() {
		lockInTime = new TimeReal();
		lockOutTime = new TimeReal();
		companyName = new Name();
		companyAddress = new Address();
		companyCardNumber = new FullCardNumber(); 
	}

	/**
	 * Constructor for a VuCompanyLocksRecord object
	 * 
	 * @param	value	byte array of a VuCompanyLocksRecord structure
	 * 					whose data is used when the VuCompanyLocksRecord
	 * 					object is created.
	 */
	public VuCompanyLocksRecord( byte[] value ) {
		debugLogger = new DebugLogger();
		debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] ------------------------------------------------------------" );

		debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Lock-in time:" );
		lockInTime = new TimeReal( arrayCopy( value, 0, 4 ) );

		debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Lock-out time:" );
		lockOutTime = new TimeReal( arrayCopy( value, 4, 4 ) );

		companyName = new Name( arrayCopy( value, 8, 36 ) );
		debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Company name: %s\n", companyName.getNameString() );

		companyAddress = new Address( arrayCopy( value, 44, 36 ) );
		debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Company address: %s\n", companyAddress.getAddressString() );

		companyCardNumber = new FullCardNumber( arrayCopy( value, 80, 18 ) ); 
		debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Company card number: %s\n", companyCardNumber.getCardNumber().getOwnerIdentification() );
	}

	/**
	 * Returns the date and time of lock-in of a VuCompanyLocksRecord object.
	 * 
	 * @return	the date and time of lock-in of the VuCompanyLocksRecord object
	 */
	public TimeReal getLockInTime() {
		return lockInTime;
	}

	/**
	 * Sets the date and time of lock-in of a VuCompanyLocksRecord object.
	 * 
	 * @param	lockInTime		the date and time of lock-in
	 * 							to be set for the VuCompanyLocksRecord object
	 */
	public void setLockInTime( TimeReal lockInTime ) {
		this.lockInTime = lockInTime;
	}

	/**
	 * Returns the date and time of lock-out of a VuCompanyLocksRecord object.
	 * 
	 * @return	the date and time of lock-out of the VuCompanyLocksRecord object
	 */
	public TimeReal getLockOutTime() {
		return lockOutTime;
	}

	/**
	 * Sets the date and time of lock-out of a VuCompanyLocksRecord object.
	 * 
	 * @param	lockOutTime		the date and time of lock-out
	 * 								to be set for the VuCompanyLocksRecord object
	 */
	public void setLockOutTime( TimeReal lockOutTime ) {
		this.lockOutTime = lockOutTime;
	}

	/**
	 * Returns the company name related with the lock-in of a VuCompanyLocksRecord object.
	 * 
	 * @return	the company name related with the lock-in of the VuCompanyLocksRecord object
	 */
	public Name getCompanyName() {
		return companyName;
	}

	/**
	 * Sets the company name related with the lock-in of a VuCompanyLocksRecord object.
	 * 
	 * @param	companyName		the company name related with the lock-in
	 * 								to be set for the VuCompanyLocksRecord object
	 */
	public void setCompanyName( Name companyName ) {
		this.companyName = companyName;
	}

	/**
	 * Returns the company address related with the lock-in of a VuCompanyLocksRecord object.
	 * 
	 * @return	the company address related with the lock-in of the VuCompanyLocksRecord object
	 */
	public Address getCompanyAddress() {
		return companyAddress;
	}

	/**
	 * Sets the company address related with the lock-in of a VuCompanyLocksRecord object.
	 * 
	 * @param	companyAddress		the company address related with the lock-in
	 * 								to be set for the VuCompanyLocksRecord object
	 */
	public void setCompanyAddress( Address companyAddress ) {
		this.companyAddress = companyAddress;
	}

	/**
	 * Returns the identifier of the card used at lock-in of a VuCompanyLocksRecord object.
	 * 
	 * @return	the identifier of the card used at lock-in of the VuCompanyLocksRecord object
	 */
	public FullCardNumber getCompanyCardNumber() {
		return companyCardNumber;
	}

	/**
	 * Sets the identifier of the card used at lock-in of a VuCompanyLocksRecord object.
	 * 
	 * @param	companyCardNumber		the identifier of the card used at lock-in
	 * 									to be set for the VuCompanyLocksRecord object
	 */
	public void setCompanyCardNumber( FullCardNumber companyCardNumber ) {
		this.companyCardNumber = companyCardNumber;
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		node.addContent( lockInTime.generateXMLElement( "lockInTime" ) );
		node.addContent( lockOutTime.generateXMLElement( "lockOutTime" ) );
		node.addContent( companyName.generateXMLElement( "companyName" ) );
		node.addContent( companyAddress.generateXMLElement( "companyAddress" ) );
		node.addContent( companyCardNumber.generateXMLElement( "companyCardNumber" ) );

		return node;
	}
}
