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
 * Appendix 7, 2.2.6.3: Positive response transfer data events and faults
 * SID 76H, TREP 03H
 */
public class PRDT_Events_And_Faults extends DataClass {

	/**
	 * Size of structure in bytes.
	 * Only valid after instantiation of the PRDT_Events_And_Faults object.
	 */
	public final int size;

	private VuFaultData vuFaultData;

	private VuEventData vuEventData;

	private VuOverSpeedingControlData vuOverSpeedingControlData;

	private VuOverSpeedingEventData vuOverSpeedingEventData;

	private VuTimeAdjustmentData vuTimeAdjustmentData;

	private DebugLogger debugLogger;


	/**
	 * Constructor for a PRDT_Events_And_Faults object
	 * 
	 * @param	value	byte array of a PRDT_Events_And_Faults structure
	 * 					whose data is used when the PRDT_Events_And_Faults
	 * 					object is created.
	 */
	public PRDT_Events_And_Faults( byte[] value ) {
		debugLogger = new DebugLogger();

		int offset1 = 1 + convertIntoUnsigned1ByteInt( value[ 0 ] ) * VuFaultRecord.size;
		debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] * Fault data:" );
		vuFaultData = new VuFaultData( arrayCopy( value, 0, offset1 ) );

		int offset2 = 1 + convertIntoUnsigned1ByteInt( value[ offset1 ] ) * VuEventRecord.size;
		debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] * Event data:" );
		vuEventData = new VuEventData( arrayCopy( value, offset1, offset2 ) );

		int offset3 = 9;
		debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] * Overspeeding control data:" );
		vuOverSpeedingControlData = new VuOverSpeedingControlData( arrayCopy( value, offset1 + offset2, offset3 ) );

		int offset4 = 1 + convertIntoUnsigned1ByteInt( value[ offset1 + offset2 + offset3 ] ) * VuOverSpeedingEventRecord.size;
		debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] * Overspeeding event data:" );
		vuOverSpeedingEventData = new VuOverSpeedingEventData( arrayCopy( value, offset1 + offset2 + offset3, offset4 ) );

		int offset5 = 1 + convertIntoUnsigned1ByteInt( value[ offset1 + offset2 + offset3 + offset4 ] ) * VuTimeAdjustmentRecord.size;
		debugLogger.println( DebugLogger.LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] * VU time adjustment data:" );
		vuTimeAdjustmentData = new VuTimeAdjustmentData( arrayCopy( value, offset1 + offset2 + offset3 + offset4, offset5 ) );

		size = offset1 + offset2 + offset3 + offset4 + offset5;
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( this.getClass().getSimpleName() );

		node.addContent( vuFaultData.generateXMLElement( "vuFaultData" ) );

		node.addContent( vuEventData.generateXMLElement( "vuEventData" ) );

		node.addContent( vuOverSpeedingControlData.generateXMLElement( "vuOverSpeedingControlData" ) );

		node.addContent( vuOverSpeedingEventData.generateXMLElement( "vuOverSpeedingEventData" ) );

		node.addContent( vuTimeAdjustmentData.generateXMLElement( "vuTimeAdjustmentData" ) );

		return node;
	}
}
