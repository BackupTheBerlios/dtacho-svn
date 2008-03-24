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

package org.digitalertachograph.DDDQuery.internalData.DataTypes;

import java.text.DateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import org.digitalertachograph.DDDQuery.DebugLogger;
import org.digitalertachograph.DDDQuery.internalData.DataClass;
import org.jdom.Element;

/**
 * Information, stored in a vehicle unit, related to changes of activity and/or
 * changes of driving status and/or changes of card status for a given calendar day
 * and to slots status a 00.00 that day.
 */
public class VuActivityDailyData extends DataClass {
	/*
	 * VuActivityDailyData ::= SEQUENCE {
	 * 	noOfActivityChanges INTEGER SIZE(0..1440), 2 bytes
	 *  activityChangeInfos SET SIZE(noOfActivityChanges) OF ActivityChangeInfo, 0..2880 bytes
	 * }
	 * ---
	 * ActivityChangeInfo ::= OCTET STRING (SIZE(2)), 2 bytes
	 */

	/**
	 * Size of structure in bytes.
	 * Only valid after instantiation of the VuActivityDailyData object.
	 */
	public final int size;

	private int noOfActivityChanges;

	// create min. 720 vectors; will be automatically expanded at run time if required!
	private Vector<ActivityChangeInfo> activityChangeInfos = new Vector<ActivityChangeInfo>( 720 );

	private DebugLogger debugLogger;


	/**
	 * Constructor for a VuActivityDailyData object
	 * 
	 * @param	value	byte array of a VuActivityDailyData structure
	 * 					whose data is used when the VuActivityDailyData
	 * 					object is created.
	 * 
	 * @param	downloadedDayDate		byte array of a VuActivityDailyData structure
	 */
	public VuActivityDailyData( byte[] value, TimeReal downloadedDayDate ) {
		debugLogger = new DebugLogger();

		noOfActivityChanges = convertIntoUnsigned2ByteInt( arrayCopy( value, 0, 2 ) );
		size = 2 + noOfActivityChanges * ActivityChangeInfo.size;

		if ( noOfActivityChanges != 0 ) {
			debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "  %d activity change(s) in this record\n", noOfActivityChanges );


			for ( int i = 0; i < noOfActivityChanges; i++ ) {
				byte[] record = arrayCopy( value, 2 + ( i * ActivityChangeInfo.size ), ActivityChangeInfo.size );
				ActivityChangeInfo aci = new ActivityChangeInfo( record );
				activityChangeInfos.add( aci );

				if ( downloadedDayDate.getTimereal() == 0 ) {
					debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "   %4d minutes after 00:00, activity %02x - %s\n", aci.getTime(), aci.getActivity(), aci.getActivityString() );
				}
				else {
					Date d = new Date( downloadedDayDate.getTimereal() * 1000 + aci.getTime() * 60 * 1000 );
					debugLogger.printf( DebugLogger.LOGLEVEL_INFO_EXTENDED, "   %s, value %02x%02x, activity %02x - %s\n", DateFormat.getDateTimeInstance( DateFormat.LONG, DateFormat.LONG ).format( d ), aci.getValue()[ 0 ], aci.getValue()[ 1 ], aci.getActivity(), aci.getActivityString() );
				}
			}
		}
	}

	/**
	 * Constructor for a VuActivityDailyData object
	 * 
	 * @param	value					byte array of a VuActivityDailyData structure
	 * 									whose data is used when the VuActivityDailyData
	 * 									object is created.
	 */
	public VuActivityDailyData( byte[] value ) {
		this( value, new TimeReal( 0 ) );
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		Iterator<ActivityChangeInfo> iter = activityChangeInfos.iterator();
		while ( iter.hasNext() ) {
			ActivityChangeInfo aci = (ActivityChangeInfo)iter.next();
			node.addContent( aci.generateXMLElement( "activityChangeInfo" ) );
		}

		return node;
	}
}
