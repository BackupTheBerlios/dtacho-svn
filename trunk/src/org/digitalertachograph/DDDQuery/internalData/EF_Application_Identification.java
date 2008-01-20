/*   Copyright (C) 2007-2008, Martin Barth, Gerald Schnabel

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

package org.digitalertachograph.DDDQuery.internalData;

import org.digitalertachograph.DDDQuery.internalData.DataTypes.*;
import org.jdom.Element;

/**
 * EF_Application_Identification,
 * 
 * Council Regulation (EEC) No 3821/85 File ID: 0501
 * 
 */
public class EF_Application_Identification extends DataClass {
	//public static final int size = 10;
	private DriverCardApplicationIdentification driverCardApplicationIdentification;
	private WorkshopCardApplicationIdentification workshopCardApplicationIdentification;
	private ControlCardApplicationIdentification controlCardApplicationIdentification;
	private CompanyCardApplicationIdentification companyCardApplicationIdentification;

	// all cards
	private int cardType;
	private byte[] cardStructureVersion;
	
	// driver card, workshop card
	private short noOfEventsPerType;
	private short noOfFaultsPerType;
	private int activityStructureLength;
	private int noOfCardVehicleRecords;
	private short noOfCardPlaceRecords;

	// workshop card
	private short noOfCalibrationRecords;

	// control card
	private int noOfControlActivityRecords;

	// company card
	private int noOfCompanyActivityRecords;
	
	
	/**
	 * Constructor for an EF_Application_Identification object
	 * 
	 * @param	value	byte array of an EF_Application_Identification structure
	 * 					whose data is used when the EF_Application_Identification
	 * 					object is created.
	 */
	public EF_Application_Identification( byte[] value ) {
		// size = value.length;
		cardType = value[ 0 ];

		switch ( cardType ) {
			case EquipmentType.DRIVER_CARD:
				driverCardApplicationIdentification = new DriverCardApplicationIdentification( value );

				cardStructureVersion = driverCardApplicationIdentification.getCardStructureVersion();
				noOfEventsPerType = driverCardApplicationIdentification.getNoOfEventsPerType();
				noOfFaultsPerType = driverCardApplicationIdentification.getNoOfFaultsPerType();
				activityStructureLength = driverCardApplicationIdentification.getActivityStructureLength();
				noOfCardVehicleRecords = driverCardApplicationIdentification.getNoOfCardVehicleRecords();
				noOfCardPlaceRecords = driverCardApplicationIdentification.getNoOfCardPlaceRecords();
				System.out.println( " [INFO] this is a driver card" );
				break;

			case EquipmentType.WORKSHOP_CARD:
				workshopCardApplicationIdentification = new WorkshopCardApplicationIdentification( value );

				cardStructureVersion = workshopCardApplicationIdentification.getCardStructureVersion();
				noOfEventsPerType = workshopCardApplicationIdentification.getNoOfEventsPerType();
				noOfFaultsPerType = workshopCardApplicationIdentification.getNoOfFaultsPerType();
				activityStructureLength = workshopCardApplicationIdentification.getActivityStructureLength();
				noOfCardVehicleRecords = workshopCardApplicationIdentification.getNoOfCardVehicleRecords();
				noOfCardPlaceRecords = workshopCardApplicationIdentification.getNoOfCardPlaceRecords();
				noOfCalibrationRecords = workshopCardApplicationIdentification.getNoOfCalibrationRecords();
				System.out.println( " [INFO] this is a workshop card" );
				break;

			case EquipmentType.CONTROL_CARD:
				controlCardApplicationIdentification = new ControlCardApplicationIdentification( value );

				cardStructureVersion = controlCardApplicationIdentification.getCardStructureVersion();
				noOfControlActivityRecords = controlCardApplicationIdentification.getNoOfControlActivityRecords();
				System.out.println( " [INFO] this is a control card" );
				break;

			case EquipmentType.COMPANY_CARD:
				companyCardApplicationIdentification = new CompanyCardApplicationIdentification( value );

				cardStructureVersion = companyCardApplicationIdentification.getCardStructureVersion();
				noOfCompanyActivityRecords = companyCardApplicationIdentification.getNoOfCompanyActivityRecords();
				System.out.println( " [INFO] this is a company card" );
				break;
				
			default:
				break;
		}
	}

	/**
	 * Returns the card type of an EF_Application_Identification object.
	 * 
	 * @return	card type of the EF_Application_Identification object,
	 * 			{@link EquipmentType#DRIVER_CARD}, {@link EquipmentType#WORKSHOP_CARD},
	 * 			{@link EquipmentType#CONTROL_CARD}, {@link EquipmentType#COMPANY_CARD},
	 */
	public int getCardType() {
		return cardType;
	}
	
	/**
	 * Returns the version of the structure that is implemented in the card
	 * of an EF_Application_Identification object.
	 * 
	 * @return	the version of the structure that is implemented in the card
	 * 			of the EF_Application_Identification object
	 */
	public byte[] getCardStructureVersion() {
		return cardStructureVersion;
	}

	/**
	 * Returns the number of events per type of event the card can record
	 * of an EF_Application_Identification object.
	 * 
	 * @return	the number of events per type of event the card can record
	 * 			of the EF_Application_Identification object
	 */
	public short getNoOfEventsPerType() {
		return noOfEventsPerType;
	}
	
	/**
	 * Returns the number of faults per type of fault the card can record
	 * of an EF_Application_Identification object.
	 * 
	 * @return	the number of faults per type of fault the card can record
	 * 			of the EF_Application_Identification object
	 */
	public short getNoOfFaultsPerType() {
		return noOfFaultsPerType;
	}
	
	/**
	 * Returns the number of bytes available for storing activity records
	 * of an EF_Application_Identification object.
	 * 
	 * @return	the number of bytes available for storing activity records
	 * 			of the EF_Application_Identification object
	 */
	public int getActivityStructureLength() {
		return activityStructureLength;
	}
	
	/**
	 * Returns the number of vehicle records the card can contain
	 * of an EF_Application_Identification object.
	 * 
	 * @return	the number of vehicle records the card can contain
	 * 			of the EF_Application_Identification object
	 */
	public int getNoOfCardVehicleRecords() {
		return noOfCardVehicleRecords;
	}

	/**
	 * Returns the number of places the card can record
	 * of an EF_Application_Identification object.
	 * 
	 * @return	the number of places the card can record
	 * 			of the EF_Application_Identification object
	 */
	public short getNoOfCardPlaceRecords() {
		return noOfCardPlaceRecords;
	}

	/**
	 * Returns the number of calibration records the card can store
	 * of an EF_Application_Identification object.
	 * 
	 * @return	the number of calibration records the card can store
	 * 			of the EF_Application_Identification object
	 */
	public short getNoOfCalibrationRecords() {
		return noOfCalibrationRecords;
	}

	/**
	 * Returns the number of company activity records the card can store
	 * of an EF_Application_Identification object.
	 * 
	 * @return	the number of company activity records the card can store
	 * 			of the EF_Application_Identification object
	 */
	public int getNoOfControlActivityRecords() {
		return noOfControlActivityRecords;
	}

	/**
	 * Returns the number of company activity records the card can store
	 * of an EF_Application_Identification object.
	 * 
	 * @return	the number of company activity records the card can store
	 * 			of the EF_Application_Identification object
	 */
	public int getNoOfCompanyActivityRecords() {
		return noOfCompanyActivityRecords;
	}
	
	public Element generateXMLElement( String name ) {
		// discard name - this.getClass().getSimpleName() is unique!
		Element node = new Element( this.getClass().getSimpleName() );
		Element child;
		String child_name;

		switch ( cardType ) {
			case EquipmentType.DRIVER_CARD :
				child_name = driverCardApplicationIdentification.getClass().getSimpleName();
				child = driverCardApplicationIdentification.generateXMLElement( child_name );
				break;

			case EquipmentType.WORKSHOP_CARD :
				child_name = workshopCardApplicationIdentification.getClass().getSimpleName(); 
				child = workshopCardApplicationIdentification.generateXMLElement( child_name );
				break;

			case EquipmentType.CONTROL_CARD :
				child_name = controlCardApplicationIdentification.getClass().getSimpleName();
				child = controlCardApplicationIdentification.generateXMLElement( child_name );
				break;

			case EquipmentType.COMPANY_CARD :
				child_name = companyCardApplicationIdentification.getClass().getSimpleName();
				child = companyCardApplicationIdentification.generateXMLElement( child_name );
				break;
			
			default :
				child = new Element( "default" );
				break;
		}
		
		node.addContent( child );

		return node;
	}
}

