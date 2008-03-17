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

import org.digitalertachograph.DDDQuery.DebugLogger;
import org.digitalertachograph.DDDQuery.internalData.DataClass;
import org.jdom.Element;

/**
 * Information related to a place where a daily work period begins or ends.
 */
public class PlaceRecord extends DataClass {
	/*
	 * PlaceRecord ::= SEQUENCE {
	 * 	entryTime TimeReal, 4 bytes
	 * 	entryTypeDailyWorkPeriod EntryTypeDailyWorkPeriod, 1 byte
	 * 	dailyWorkPeriodCountry NationNumeric, 1 byte
	 * 	dailyWorkPeriodRegion RegionNumeric, 1 byte
	 * 	vehicleOdometerValue OdometerShort, 3 bytes
	 * } 
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 10;

	private TimeReal entryTime;
	private EntryTypeDailyWorkPeriod entryTypeDailyWorkPeriod;
	private NationNumeric dailyWorkPeriodCountry;
	private RegionNumeric dailyWorkPeriodRegion;
	private OdometerShort vehicleOdometerValue;

	private DebugLogger debugLogger;


	/**
	 * Constructor for a PlaceRecord object
	 */
	public PlaceRecord() {
		entryTime = new TimeReal();
		entryTypeDailyWorkPeriod = new EntryTypeDailyWorkPeriod();
		dailyWorkPeriodCountry = new NationNumeric();
		dailyWorkPeriodRegion = new RegionNumeric();
		vehicleOdometerValue = new OdometerShort();
	}

	/**
	 * Constructor for a PlaceRecord object
	 * 
	 * @param	record	byte array of a PlaceRecord structure
	 * 					whose data is used when the PlaceRecord
	 * 					object is created.
	 */
	public PlaceRecord( byte[] record ) {
		debugLogger = new DebugLogger();
		debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] ------------------------------------------------------------" );

		debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Place record date and time:" );
		entryTime = new TimeReal( arrayCopy( record, 0, 4 ) );

		entryTypeDailyWorkPeriod = new EntryTypeDailyWorkPeriod( record[ 4 ] );
		debugLogger.print( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Entry type daily work period: " );

		switch( entryTypeDailyWorkPeriod.getEntryTypeDailyWorkPeriod() ) {
			case EntryTypeDailyWorkPeriod.CARD_INSERTION_TIME_OR_TIME_OF_ENTRY:
				debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, "card insertion time or time of entry" );
				break;

			case EntryTypeDailyWorkPeriod.CARD_WITHDRAWAL_TIME_OR_TIME_OF_ENTRY:
				debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, "card withdrawal time or time of entry" );
				break;

			case EntryTypeDailyWorkPeriod.MANUALLY_ENTERED_START_TIME:
				debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, "manually entered start time" );
				break;

			case EntryTypeDailyWorkPeriod.MANUALLY_ENTERED_END_TIME:
				debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, "manually entered end of work period" );
				break;

			case EntryTypeDailyWorkPeriod.START_TIME_ASSUMED_BY_VU:
				debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, "start time assumed by VU" );
				break;

			case EntryTypeDailyWorkPeriod.END_TIME_ASSUMED_BY_VU:
				debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, "end time assumed by VU" );
				break;

			default:
				debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, "????" );
				break;
		}
		
		dailyWorkPeriodCountry = new NationNumeric( record[ 5 ] );
		debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Daily work period country: %s\n", dailyWorkPeriodCountry.toString() );

		dailyWorkPeriodRegion = new RegionNumeric( record[ 6 ] );
		debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Daily work period region: %s\n", dailyWorkPeriodRegion.toString() );

		vehicleOdometerValue = new OdometerShort( arrayCopy( record, 7, 3 ) );
		debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Vehicle odometer value: %d\n", vehicleOdometerValue.getOdometerShort() );
	}

	/**
	 * Returns the timestamp (date and time) related to the entry
	 * of a PlaceRecord object.
	 * 
	 * @return	the timestamp related to the entry of the PlaceRecord object
	 */
	public TimeReal getEntryTime() {
		return entryTime;
	}

	/**
	 * Sets the timestamp (date and time) related to the entry of a PlaceRecord object.
	 * 
	 * @param	entryTime		the timestamp related to the entry to be set for the PlaceRecord object
	 */
	public void setEntryTime( TimeReal entryTime ) {
		this.entryTime = entryTime;
	}

	/**
	 * Returns the type of entry of a PlaceRecord object.
	 * 
	 * @return	the type of entry of the PlaceRecord object
	 */
	public EntryTypeDailyWorkPeriod getEntryTypeDailyWorkPeriod() {
		return entryTypeDailyWorkPeriod;
	}

	/**
	 * Sets the type of entry of a PlaceRecord object.
	 * 
	 * @param	entryTypeDailyWorkPeriod	the type of entry to be set for the PlaceRecord object
	 */
	public void setEntryTypeDailyWorkPeriod( EntryTypeDailyWorkPeriod entryTypeDailyWorkPeriod ) {
		this.entryTypeDailyWorkPeriod = entryTypeDailyWorkPeriod;
	}

	/**
	 * Returns the country entered of a PlaceRecord object.
	 * 
	 * @return	the country entered of the PlaceRecord object
	 */
	public NationNumeric getDailyWorkPeriodCountry() {
		return dailyWorkPeriodCountry;
	}

	/**
	 * Sets the country entered of a PlaceRecord object.
	 * 
	 * @param	dailyWorkPeriodCountry		the country entered to be set for the PlaceRecord object
	 */
	public void setDailyWorkPeriodCountry( NationNumeric dailyWorkPeriodCountry ) {
		this.dailyWorkPeriodCountry = dailyWorkPeriodCountry;
	}

	/**
	 * Returns the region entered of a PlaceRecord object.
	 * 
	 * @return	the region entered of the PlaceRecord object
	 */
	public RegionNumeric getDailyWorkPeriodRegion() {
		return dailyWorkPeriodRegion;
	}

	/**
	 * Sets the region entered of a PlaceRecord object.
	 * 
	 * @param	dailyWorkPeriodRegion		the region entered to be set for the PlaceRecord object
	 */
	public void setDailyWorkPeriodRegion( RegionNumeric dailyWorkPeriodRegion ) {
		this.dailyWorkPeriodRegion = dailyWorkPeriodRegion;
	}

	/**
	 * Returns the odometer value at the time of place entry of a PlaceRecord object.
	 * 
	 * @return	the odometer value at the time of place entry of the PlaceRecord object
	 */
	public int getVehicleOdometerValue() {
		return vehicleOdometerValue.getOdometerShort();
	}

	/**
	 * Sets the odometer value at the time of place entry of a PlaceRecord object.
	 * 
	 * @param	vehicleOdometerValue		the odometer value at the time of place entry
	 * 										to be set for the PlaceRecord object
	 */
	public void setVehicleOdometerValue( int vehicleOdometerValue ) {
		this.vehicleOdometerValue.setOdometerShort( vehicleOdometerValue );
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		node.addContent( entryTime.generateXMLElement( "entryTime" ) );
		node.addContent( entryTypeDailyWorkPeriod.generateXMLElement( "entryTypeDailyWorkPeriod" ) );
		node.addContent( dailyWorkPeriodCountry.generateXMLElement( "dailyWorkPeriodCountry" ) );
		node.addContent( dailyWorkPeriodRegion.generateXMLElement( "dailyWorkPeriodRegion" ) );
		node.addContent( vehicleOdometerValue.generateXMLElement( "vehicleOdometerValue" ) );	

		return node;
	}
}
