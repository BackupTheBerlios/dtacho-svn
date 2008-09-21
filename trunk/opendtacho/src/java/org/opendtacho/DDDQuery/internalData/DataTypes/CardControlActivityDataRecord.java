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

package org.opendtacho.DDDQuery.internalData.DataTypes;

import org.jdom.Element;
import org.opendtacho.DDDQuery.DebugLogger;
import org.opendtacho.DDDQuery.internalData.DataClass;
import org.opendtacho.DDDQuery.internalData.DataTypes.EquipmentType;

/**
 * Information, stored in a driver or workshop card, related to the last control
 * the driver has been subject to.
 */
public class CardControlActivityDataRecord extends DataClass {
	/*
	 * CardControlActivityDataRecord ::= SEQUENCE {
	 * 	controlType ControlType, 1 byte
	 * 	controlTime TimeReal, 4 bytes
	 * 	controlCardNumber FullCardNumber, 18 bytes
	 * 	controlVehicleRegistration VehicleRegistrationIdentification, 15 bytes
	 * 	controlDownloadPeriodBegin TimeReal, 4 bytes
	 * 	controlDownloadPeriodEnd TimeReal, 4 bytes
	 * }
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 46;

	private ControlType controlType;
	private TimeReal controlTime;
	private FullCardNumber controlCardNumber;
	private VehicleRegistrationIdentification controlVehicleRegistration;
	private TimeReal controlDownloadPeriodBegin;
	private TimeReal controlDownloadPeriodEnd;

	private DebugLogger debugLogger;


	/**
	 * Constructor for a CardControlActivityDataRecord object
	 */
	public CardControlActivityDataRecord() {
		controlType = new ControlType();
		controlTime = new TimeReal();
		controlCardNumber = new FullCardNumber();
		controlVehicleRegistration = new VehicleRegistrationIdentification();
		controlDownloadPeriodBegin = new TimeReal();
		controlDownloadPeriodEnd = new TimeReal();
	}

	/**
	 * Constructor for a CardControlActivityDataRecord object
	 * 
	 * @param	value	byte array of a CardControlActivityDataRecord structure
	 * 					whose data is used when the CardControlActivityDataRecord
	 * 					object is created.
	 */
	public CardControlActivityDataRecord( byte[] value ) {
		debugLogger = new DebugLogger();

		controlType = new ControlType( value[ 0 ] );
		debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Control type:" );
		debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Card downloaded: %s\n", Boolean.toString( controlType.isCardDownloading() ) );
		debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] VU downloaded: %s\n", Boolean.toString( controlType.isVuDownloading() ) );
		debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Printing done during control: %s\n", Boolean.toString( controlType.isPrinting() ) );
		debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Display used during control: %s\n", Boolean.toString( controlType.isDisplay() ) );

		long controlTimeTmp = convertIntoUnsigned4ByteInt( arrayCopy( value, 1, 4 ) );
		if ( controlTimeTmp != 0 ) {
			debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Control time:" );
		}
		controlTime = new TimeReal( arrayCopy( value, 1, 4 ) );

		controlCardNumber = new FullCardNumber( arrayCopy( value, 5, 18 ) );
		if ( controlTimeTmp != 0 ) {
			debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Control card:" );
			debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Card type: %s\n", controlCardNumber.getCardType().toString() );
			debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Card issuing member state: %s\n", controlCardNumber.getCardIssuingMemberState().toString());
			if ( controlCardNumber.getCardType().getEquipmentType() == EquipmentType.CONTROL_CARD ) {
				debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Card number: %s%s%s%s\n", controlCardNumber.getCardNumber().getOwnerIdentification(), controlCardNumber.getCardNumber().getCardConsecutiveIndex().getCardConsecutiveIndex(), controlCardNumber.getCardNumber().getCardReplacementIndex().getCardReplacementIndex(), controlCardNumber.getCardNumber().getCardRenewalIndex().getCardRenewalIndex() );
			}
			else {
				debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Wrong card type?! Expected a control card" );
			}
		}

		controlVehicleRegistration = new VehicleRegistrationIdentification( arrayCopy( value, 23, 15 ) );
		if ( controlTimeTmp != 0 ) {
			debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Control vehicle registration:" );
			debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Nation where vehicle is registered: %s\n", controlVehicleRegistration.getVehicleRegistrationNation().toString() );
			debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, "  [INFO_EXT] Vehicle registration number: %s\n", controlVehicleRegistration.getVehicleRegistrationNumber().getVehicleRegNumberString() );
		}

		long controlDownloadPeriodBeginTmp = convertIntoUnsigned4ByteInt( arrayCopy( value, 38, 4 ) );
		if ( controlDownloadPeriodBeginTmp != 0 ) {
			debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Control download period begin:" );
		}
		controlDownloadPeriodBegin = new TimeReal( arrayCopy( value, 38, 4 ) );

		long controlDownloadPeriodEndTmp = convertIntoUnsigned4ByteInt( arrayCopy( value, 42, 4 ) );
		if ( controlDownloadPeriodEndTmp != 0 ) {
			debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Control download period end:" );
		}
		controlDownloadPeriodEnd = new TimeReal( arrayCopy( value, 42, 4 ) );
	}

	/**
	 * Returns the type of a control of a CardControlActivityDataRecord object.
	 * 
	 * @return	the type of the control of the CardControlActivityDataRecord object
	 */
	public ControlType getControlType() {
		return controlType;
	}

	/**
	 * Sets the type of a control of a CardControlActivityDataRecord object.
	 * 
	 * @param	controlType		the type of the control to be set for the
	 * 							CardControlActivityDataRecord object
	 */
	public void setControlType( ControlType controlType ) {
		this.controlType = controlType;
	}

	/**
	 * Returns the timestamp (date and time) of a control of a CardControlActivityDataRecord object.
	 * 
	 * @return	the timestamp of the control of the CardControlActivityDataRecord object
	 */
	public TimeReal getControlTime() {
		return controlTime;
	}

	/**
	 * Sets the timestamp (date and time) of a control of a CardControlActivityDataRecord object.
	 * 
	 * @param	controlTime		timestamp of the control to be set for the
	 * 							CardControlActivityDataRecord object
	 */
	public void setControlTime( TimeReal controlTime ) {
		this.controlTime = controlTime;
	}

	/**
	 * Returns the control card number of the control officer having performed the control
	 * of a CardControlActivityDataRecord object.
	 * 
	 * @return	the control card number of the control officer having performed the control
	 * 			of the CardControlActivityDataRecord object
	 */
	public FullCardNumber getControlCardNumber() {
		return controlCardNumber;
	}

	/**
	 * Sets the control card number of the control officer having performed the control
	 * of a CardControlActivityDataRecord object.
	 * 
	 * @param	controlCardNumber	control card number to be set for the
	 * 								CardControlActivityDataRecord object
	 */
	public void setControlCardNumber( FullCardNumber controlCardNumber ) {
		this.controlCardNumber = controlCardNumber;
	}

	/**
	 * Returns the VRN and registering Member State of the vehicle in which the control
	 * happened of a CardControlActivityDataRecord object.
	 * 
	 * @return	the VRN and registering Member State of the vehicle in which the control
	 * 			happened of the CardControlActivityDataRecord object
	 */
	public VehicleRegistrationIdentification getControlVehicleRegistration() {
		return controlVehicleRegistration;
	}

	/**
	 * Sets the VRN and registering Member State of the vehicle in which the control
	 * happened of a CardControlActivityDataRecord object.
	 * 
	 * @param	controlVehicleRegistration		the VRN and registering Member State of the vehicle
	 *				 							in which the control happened to be set for the
	 * 											CardControlActivityDataRecord object
	 */
	public void setControlVehicleRegistration( VehicleRegistrationIdentification controlVehicleRegistration ) {
		this.controlVehicleRegistration = controlVehicleRegistration;
	}

	/**
	 * Returns the timestamp (date and time) of a download period beginning
	 * of a CardControlActivityDataRecord object.
	 * 
	 * @return	the timestamp of the download period beginning of the CardControlActivityDataRecord object
	 */
	public TimeReal getControlDownloadPeriodBegin() {
		return controlDownloadPeriodBegin;
	}

	/**
	 * Sets the timestamp (date and time) of a download period beginning
	 * of a CardControlActivityDataRecord object.
	 * 
	 * @param	controlDownloadPeriodBegin	timestamp of the download period beginning to be set for the
	 * 										CardControlActivityDataRecord object
	 */
	public void setControlDownloadPeriodBegin( TimeReal controlDownloadPeriodBegin ) {
		this.controlDownloadPeriodBegin = controlDownloadPeriodBegin;
	}

	/**
	 * Returns the timestamp (date and time) of a download period end
	 * of a CardControlActivityDataRecord object.
	 * 
	 * @return	the timestamp of the download period end of the CardControlActivityDataRecord object
	 */
	public TimeReal getControlDownloadPeriodEnd() {
		return controlDownloadPeriodEnd;
	}

	/**
	 * Sets the timestamp (date and time) of a download period end
	 * of a CardControlActivityDataRecord object.
	 * 
	 * @param	controlDownloadPeriodEnd	timestamp of the download period end to be set for the
	 * 										CardControlActivityDataRecord object
	 */
	public void setControlDownloadPeriodEnd( TimeReal controlDownloadPeriodEnd ) {
		this.controlDownloadPeriodEnd = controlDownloadPeriodEnd;
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		if ( controlTime.getTimeReal() != 0 ) {
			node.addContent( controlType.generateXMLElement( "controlType" ) );
			node.addContent( controlTime.generateXMLElement( "controlTime" ) );
			node.addContent( controlCardNumber.generateXMLElement( "controlCardNumber" ) );
			node.addContent( controlVehicleRegistration.generateXMLElement( "controlVehicleRegistration" ) );
			node.addContent( controlDownloadPeriodBegin.generateXMLElement( "controlDownloadPeriodBegin" ) );
			node.addContent( controlDownloadPeriodEnd.generateXMLElement( "controlDownloadPeriodEnd" ) );

			return node;
		}
		else {
			return null;
		}
	}
}
