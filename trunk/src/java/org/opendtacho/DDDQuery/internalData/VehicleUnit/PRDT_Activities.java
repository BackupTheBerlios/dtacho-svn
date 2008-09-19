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
 * Appendix 7, 2.2.6.2: Positive response transfer data activities
 * SID 76H, TREP 02H
 */
public class PRDT_Activities extends DataClass {

	/**
	 * Size of structure in bytes.
	 * Only valid after instantiation of the PRDT_Activities object.
	 */
	public final int size;

	private TimeReal downloadedDayDate;
	private OdometerShort odoMeterValueMidnight;

	private VuCardIWData vuCardIWData;

	private VuActivityDailyData vuActivityDailyData;

	private VuPlaceDailyWorkPeriodData vuPlaceDailyWorkPeriodData;

	private VuSpecificConditionData vuSpecificConditionData;

	private DebugLogger debugLogger;


	/**
	 * Constructor for a PRDT_Activities object
	 * 
	 * @param	value	byte array of a PRDT_Activities structure
	 * 					whose data is used when the PRDT_Activities
	 * 					object is created.
	 */
	public PRDT_Activities( byte[] value ) {
		debugLogger = new DebugLogger();

		int offset1 = 7;
		debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] * Date of day downloaded:" );
		downloadedDayDate = new TimeReal( arrayCopy( value, 0, 4 ) );

		odoMeterValueMidnight = new OdometerShort( arrayCopy( value, 4, 3 ) );
		debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] * Odometer at end of downloaded day: %d\n", odoMeterValueMidnight.getOdometerShort() );

		int offset2 = 2 + convertIntoUnsigned2ByteInt( arrayCopy( value, offset1, 2 ) ) * VuCardIWRecord.size;
		debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] * Cards insertion withdrawal cycles data:" );
		vuCardIWData = new VuCardIWData( arrayCopy( value, offset1, offset2 ) );

		int offset3 = 2 + convertIntoUnsigned2ByteInt( arrayCopy( value, offset1 + offset2, 2 ) ) * ActivityChangeInfo.size;
		debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] * Slots status at 00:00 and activity changes" );
		debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] * for the day downloaded:" );
		vuActivityDailyData = new VuActivityDailyData( arrayCopy( value, offset1 + offset2, offset3 ), downloadedDayDate );

		int offset4 = 1 + convertIntoUnsigned1ByteInt( value[ offset1 + offset2 + offset3 ] ) * VuPlaceDailyWorkPeriodRecord.size;
		debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] * Places related data:" );
		vuPlaceDailyWorkPeriodData = new VuPlaceDailyWorkPeriodData( arrayCopy( value, offset1 + offset2 + offset3, offset4 ) );

		int offset5 = 2 + convertIntoUnsigned2ByteInt( arrayCopy( value, offset1 + offset2 + offset3 + offset4, 2 ) ) * SpecificConditionRecord.size;
		debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] * Specific conditions data:" );
		vuSpecificConditionData = new VuSpecificConditionData( arrayCopy( value, offset1 + offset2 + offset3 + offset4, offset5 ) );

		size = offset1 + offset2 + offset3 + offset4 + offset5;
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( this.getClass().getSimpleName() );

		node.addContent( downloadedDayDate.generateXMLElement( "downloadedDayDate" ) );
		node.addContent( odoMeterValueMidnight.generateXMLElement( "odoMeterValueMidnight" ) );

		node.addContent( vuCardIWData.generateXMLElement( "vuCardIWData" ) );

		node.addContent( vuActivityDailyData.generateXMLElement( "vuActivityDailyData" ) );

		node.addContent( vuPlaceDailyWorkPeriodData.generateXMLElement( "vuPlaceDailyWorkPeriodData" ) );

		node.addContent( vuSpecificConditionData.generateXMLElement( "vuSpecificConditionData" ) );

		return node;
	}
}
