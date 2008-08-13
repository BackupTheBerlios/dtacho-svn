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
 * Information, stored in a vehicle unit, related to a control performed using this VU.
 */
public class VuControlActivityRecord extends DataClass {
	/*
	 * VuControlActivityRecord ::= SEQUENCE {
	 * 	controlType ControlType, 1 byte
	 * 	controlTime TimeReal, 4 bytes
	 * 	controlCardNumber FullCardNumber, 18 bytes 
	 * 	downloadPeriodBeginTime TimeReal, 4 bytes
	 * 	downloadPeriodEndTime TimeReal, 4 bytes
	 * }
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 31;

	private ControlType controlType;
	private TimeReal controlTime;
	private FullCardNumber controlCardNumber; 
	private TimeReal downloadPeriodBeginTime;
	private TimeReal downloadPeriodEndTime;

	private DebugLogger debugLogger;


	/**
	 * Constructor for a VuControlActivityRecord object
	 */
	public VuControlActivityRecord() {
		controlType = new ControlType();
		controlTime = new TimeReal();
		controlCardNumber = new FullCardNumber(); 
		downloadPeriodBeginTime = new TimeReal();
		downloadPeriodEndTime = new TimeReal();
	}

	/**
	 * Constructor for a VuControlActivityRecord object
	 * 
	 * @param	value	byte array of a VuControlActivityRecord structure
	 * 					whose data is used when the VuControlActivityRecord
	 * 					object is created.
	 */
	public VuControlActivityRecord( byte[] value ) {
		debugLogger = new DebugLogger();
		debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] ------------------------------------------------------------" );

		controlType = new ControlType( value[ 0 ] );
		debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Control type:" );
		debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Card downloaded: %s\n", Boolean.toString( controlType.isCardDownloading() ) );
		debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] VU downloaded: %s\n", Boolean.toString( controlType.isVuDownloading() ) );
		debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Printing done during control: %s\n", Boolean.toString( controlType.isPrinting() ) );
		debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Display used during control: %s\n", Boolean.toString( controlType.isDisplay() ) );

		debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Control time:" );
		controlTime = new TimeReal( arrayCopy( value, 1, 4 ) );

		controlCardNumber = new FullCardNumber( arrayCopy( value, 5, 18 ) );
		debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Control card number: %s\n", controlCardNumber.getCardNumber().getOwnerIdentification() );

		debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Download period begin time:" );
		downloadPeriodBeginTime = new TimeReal( arrayCopy( value, 23, 4 ) );

		debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Download period end time:" );
		downloadPeriodEndTime = new TimeReal( arrayCopy( value, 27, 4 ) );
	}

	/**
	 * Returns the type of the control of a VuControlActivityRecord object.
	 * 
	 * @return	the type of the control of the VuControlActivityRecord object
	 */
	public ControlType getControlType() {
		return controlType;
	}

	/**
	 * Sets the type of the control of a VuControlActivityRecord object.
	 * 
	 * @param	controlType		the type of the control of a VuControlActivityRecord object
	 */
	public void setControlType( ControlType controlType ) {
		this.controlType = controlType;
	}

	/**
	 * Returns the time and date of the control of a VuControlActivityRecord object.
	 * 
	 * @return	the time and date of the control of the VuControlActivityRecord object
	 */
	public TimeReal getControlTime() {
		return controlTime;
	}

	/**
	 * Sets the time and date of the control of a VuControlActivityRecord object.
	 * 
	 * @param	controlTime		the time and date of the control of a VuControlActivityRecord object
	 */
	public void setControlTime( TimeReal controlTime ) {
		this.controlTime = controlTime;
	}

	/**
	 * Returns the control card used for the control of a VuControlActivityRecord object.
	 * 
	 * @return	the control card used for the control of the VuControlActivityRecord object
	 */
	public FullCardNumber getControlCardNumber() {
		return controlCardNumber;
	}

	/**
	 * Sets the control card used for the control of a VuControlActivityRecord object.
	 * 
	 * @param	controlCardNumber		the control card used for the control of a VuControlActivityRecord object
	 */
	public void setControlCardNumber( FullCardNumber controlCardNumber ) {
		this.controlCardNumber = controlCardNumber;
	}
	/**
	 * Returns the begin time of the downloaded period, in case of downloading
	 * of a VuControlActivityRecord object.
	 * 
	 * @return	the begin time of the downloaded period, in case of downloading
	 * 			of the VuControlActivityRecord object
	 */
	public TimeReal getDownloadPeriodBeginTime() {
		return downloadPeriodBeginTime;
	}

	/**
	 * Sets the begin time of the downloaded period, in case of downloading
	 * of a VuControlActivityRecord object.
	 * 
	 * @param	downloadPeriodBeginTime		the begin time of the downloaded period, in case
	 * 										of downloading of a VuControlActivityRecord object
	 */
	public void setDownloadPeriodBeginTime( TimeReal downloadPeriodBeginTime ) {
		this.downloadPeriodBeginTime = downloadPeriodBeginTime;
	}

	/**
	 * Returns the end time of the downloaded period, in case of downloading
	 * of a VuControlActivityRecord object.
	 * 
	 * @return	the end time of the downloaded period, in case of downloading
	 * 			of the VuControlActivityRecord object
	 */
	public TimeReal getDownloadPeriodEndTime() {
		return downloadPeriodEndTime;
	}

	/**
	 * Sets the end time of the downloaded period, in case of downloading
	 * of a VuControlActivityRecord object.
	 * 
	 * @param	downloadPeriodEndTime		the end time of the downloaded period, in case
	 * 									of downloading of a VuControlActivityRecord object
	 */
	public void setDownloadPeriodEndTime( TimeReal downloadPeriodEndTime ) {
		this.downloadPeriodEndTime = downloadPeriodEndTime;
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		node.addContent( controlType.generateXMLElement( "controlType" ) );
		node.addContent( controlTime.generateXMLElement( "controlTime" ) );
		node.addContent( controlCardNumber.generateXMLElement( "controlCardNumber" ) );
		node.addContent( downloadPeriodBeginTime.generateXMLElement( "downloadPeriodBeginTime" ) );
		node.addContent( downloadPeriodEndTime.generateXMLElement( "downloadPeriodEndTime" ) );

		return node;
	}
}
