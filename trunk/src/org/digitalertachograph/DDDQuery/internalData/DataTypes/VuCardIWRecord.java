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

import org.digitalertachograph.DDDQuery.internalData.DataClass;
import org.jdom.Element;

/**
 * Information, stored in a vehicle unit, related to insertion and withdrawal cycle
 * of a driver card or of a workshop card in the vehicle unit.
 */
public class VuCardIWRecord extends DataClass {
	/*
	 * VuCardIWRecord ::= SEQUENCE {
	 *	cardHolderName HolderName, 72 bytes
	 *	fullCardNumber FullCardNumber, 18 bytes
	 *	cardExpiryDate TimeReal, 4 bytes
	 *	cardInsertionTime TimeReal, 4 bytes
	 *	vehicleOdometerValueAtInsertion OdometerShort, 3 bytes
	 *	cardSlotNumber CardSlotNumber, 1 byte
	 *	cardWithdrawalTime TimeReal, 4 bytes
	 *	vehicleOdometerValueAtWithdrawal OdometerShort, 3 bytes
	 *	previousVehicleInfo PreviousVehicleInfo, 19 bytes
	 *	manualInputFlag ManualInputFlag, 1 byte
	 * }
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 129;

	private HolderName cardHolderName;
	private FullCardNumber fullCardNumber;
	private TimeReal cardExpiryDate;
	private TimeReal cardInsertionTime;
	private OdometerShort vehicleOdometerValueAtInsertion;
	private CardSlotNumber cardSlotNumber;
	private TimeReal cardWithdrawalTime;
	private OdometerShort vehicleOdometerValueAtWithdrawal;
	private PreviousVehicleInfo previousVehicleInfo;
	private ManualInputFlag manualInputFlag;


	/**
	 * Constructor for a VuCardIWRecord object
	 */
	public VuCardIWRecord() {
		cardHolderName = new HolderName();
		fullCardNumber = new FullCardNumber();
		cardExpiryDate = new TimeReal();
		cardInsertionTime = new TimeReal();
		vehicleOdometerValueAtInsertion = new OdometerShort();
		cardSlotNumber = new CardSlotNumber();
		cardWithdrawalTime = new TimeReal();
		vehicleOdometerValueAtWithdrawal = new OdometerShort();
		previousVehicleInfo = new PreviousVehicleInfo();
		manualInputFlag = new ManualInputFlag();
	}

	/**
	 * Constructor for a VuCardIWRecord object
	 * 
	 * @param	value	byte array of a VuCardIWRecord structure
	 * 					whose data is used when the VuCardIWRecord
	 * 					object is created.
	 */
	public VuCardIWRecord( byte[] value ) {
		cardHolderName = new HolderName( arrayCopy( value, 0, 72 ) );
		fullCardNumber = new FullCardNumber( arrayCopy( value, 72, 18 ) );
		cardExpiryDate = new TimeReal( arrayCopy( value, 90, 4 ) );
		cardInsertionTime = new TimeReal( arrayCopy( value, 94, 4 ) );
		vehicleOdometerValueAtInsertion = new OdometerShort( arrayCopy( value, 98, 3 ) );
		cardSlotNumber = new CardSlotNumber( value[ 101 ] );
		cardWithdrawalTime = new TimeReal( arrayCopy( value, 102, 4 ) );
		vehicleOdometerValueAtWithdrawal = new OdometerShort( arrayCopy( value, 106, 3 ) );
		previousVehicleInfo = new PreviousVehicleInfo( arrayCopy( value, 109, 19 ) );
		manualInputFlag = new ManualInputFlag( value[ 128 ] );
	}

	/**
	 * Returns the driver or workshop card holder's surname and first names as stored in the card
	 * of a VuCardIWRecord object.
	 * 
	 * @return	the driver or workshop card holder's surname and first names as stored in the card
	 * 			of the VuCardIWRecord object
	 */
	public HolderName getHolderName() {
		return cardHolderName;
	}

	/**
	 * Sets the driver or workshop card holder's surname and first names as stored in the card
	 * of a VuCardIWRecord object.
	 * 
	 * @param	cardHolderName		the driver or workshop card holder's surname and first names
	 * 								as stored in the card to be set for the VuCardIWRecord object
	 */
	public void setHolderName( HolderName cardHolderName ) {
		this.cardHolderName = cardHolderName;
	}

	/**
	 * Returns the type of card, its issuing Member Sate and its card number as stored in the card
	 * of a VuCardIWRecord object.
	 * 
	 * @return	the type of card, its issuing Member Sate and its card number as stored in the card
	 * 			of the VuCardIWRecord object
	 */
	public FullCardNumber getFullCardNumber() {
		return fullCardNumber;
	}

	/**
	 * Sets the type of card, its issuing Member Sate and its card number as stored in the card
	 * of a VuCardIWRecord object.
	 * 
	 * @param	fullCardNumber		the type of card, its issuing Member Sate and its card number
	 * 								as stored in the card to be set for the VuCardIWRecord object
	 */
	public void setFullCardNumber( FullCardNumber fullCardNumber ) {
		this.fullCardNumber = fullCardNumber;
	}

	/**
	 * Returns the card's expiry date as stored in the card
	 * of a VuCardIWRecord object.
	 * 
	 * @return	the card's expiry date as stored in the card
	 * 			of the VuCardIWRecord object
	 */
	public TimeReal getCardExpiryDate() {
		return cardExpiryDate;
	}

	/**
	 * Sets the card's expiry date as stored in the card
	 * of a VuCardIWRecord object.
	 * 
	 * @param	cardExpiryDate		the card's expiry date as stored in the card
	 * 								to be set for the VuCardIWRecord object
	 */
	public void setCardExpiryDate( TimeReal cardExpiryDate ) {
		this.cardExpiryDate = cardExpiryDate;
	}


	/**
	 * Returns the insertion date and time
	 * of a VuCardIWRecord object.
	 * 
	 * @return	the insertion date and time
	 * 			of the VuCardIWRecord object
	 */
	public TimeReal getCardInsertionTimee() {
		return cardInsertionTime;
	}

	/**
	 * Sets the insertion date and time
	 * of a VuCardIWRecord object.
	 * 
	 * @param	cardInsertionTime		the insertion date and time
	 * 									to be set for the VuCardIWRecord object
	 */
	public void setCardInsertionTime( TimeReal cardInsertionTime ) {
		this.cardInsertionTime = cardInsertionTime;
	}

	/**
	 * Returns the vehicle odometer value at card insertion
	 * of an VuCardIWRecord object.
	 * 
	 * @return	the vehicle odometer value at card insertion
	 * 			of the VuCardIWRecord object
	 */
	public OdometerShort getVehicleOdometerValueAtInsertion() {
		return vehicleOdometerValueAtInsertion;
	}

	/**
	 * Sets the vehicle odometer value at card insertion
	 * of an VuCardIWRecord object.
	 * 
	 * @param	vehicleOdometerValueAtInsertion		the vehicle odometer value at card insertion
	 * 												to be set for the VuCardIWRecord object
	 */
	public void setVehicleOdometerValueAtInsertion( OdometerShort vehicleOdometerValueAtInsertion ) {
		this.vehicleOdometerValueAtInsertion = vehicleOdometerValueAtInsertion;
	}

	/**
	 * Returns the card slot in which the card is inserted
	 * of a VuCardIWRecord object.
	 * 
	 * @return	the card slot in which the card is inserted
	 * 			of the VuCardIWRecord object
	 */
	public CardSlotNumber getCardSlotNumber() {
		return cardSlotNumber;
	}

	/**
	 * Sets the card slot in which the card is inserted
	 * of a VuCardIWRecord object.
	 * 
	 * @param	cardSlotNumber		the card slot in which the card is inserted
	 * 								to be set for the VuCardIWRecord object
	 */
	public void setCardSlotNumber( CardSlotNumber cardSlotNumber ) {
		this.cardSlotNumber = cardSlotNumber;
	}

	/**
	 * Returns the withdrawal date and time
	 * of a VuCardIWRecord object.
	 * 
	 * @return	the withdrawal date and time
	 * 			of the VuCardIWRecord object
	 */
	public TimeReal getCardWithdrawalTime() {
		return cardWithdrawalTime;
	}

	/**
	 * Sets the withdrawal date and time
	 * of a VuCardIWRecord object.
	 * 
	 * @param	cardWithdrawalTime		the withdrawal date and time
	 * 									to be set for the VuCardIWRecord object
	 */
	public void setCardWithdrawalTime( TimeReal cardWithdrawalTime ) {
		this.cardWithdrawalTime = cardWithdrawalTime;
	}

	/**
	 * Returns the vehicle odometer value at card withdrawal
	 * of an VuCardIWRecord object.
	 * 
	 * @return	the vehicle odometer value at card withdrawal
	 * 			of the VuCardIWRecord object
	 */
	public OdometerShort getVehicleOdometerValueAtWithdrawal() {
		return vehicleOdometerValueAtWithdrawal;
	}

	/**
	 * Sets the vehicle odometer value at card withdrawal
	 * of an VuCardIWRecord object.
	 * 
	 * @param	vehicleOdometerValueAtWithdrawal	the vehicle odometer value at card withdrawal
	 * 												to be set for the VuCardIWRecord object
	 */
	public void setVehicleOdometerValueAtWithdrawal( OdometerShort vehicleOdometerValueAtWithdrawal ) {
		this.vehicleOdometerValueAtWithdrawal = vehicleOdometerValueAtWithdrawal;
	}

	/**
	 * Returns the information about the previous vehicle used by the driver, as stored in the card
	 * of an VuCardIWRecord object.
	 * 
	 * @return	the information about the previous vehicle used by the driver, as stored in the card
	 * 			of the VuCardIWRecord object
	 */
	public PreviousVehicleInfo getPreviousVehicleInfo() {
		return previousVehicleInfo;
	}

	/**
	 * Sets the information about the previous vehicle used by the driver, as stored in the card
	 * of an VuCardIWRecord object.
	 * 
	 * @param	previousVehicleInfo		the information about the previous vehicle used by the driver,
	 * 									as stored in the card to be set for the VuCardIWRecord object
	 */
	public void setPreviousVehicleInfo( PreviousVehicleInfo previousVehicleInfo ) {
		this.previousVehicleInfo = previousVehicleInfo;
	}

	/**
	 * Returns the flag identifying if the cardholder has manually entered driver activities at card insertion
	 * of an VuCardIWRecord object.
	 * 
	 * @return	the flag identifying if the cardholder has manually entered driver activities at card insertion
	 * 			of the VuCardIWRecord object
	 */
	public ManualInputFlag getManualInputFlag() {
		return manualInputFlag;
	}

	/**
	 * Sets the flag identifying if the cardholder has manually entered driver activities at card insertion
	 * of an VuCardIWRecord object.
	 * 
	 * @param	manualInputFlag		the flag identifying if the cardholder has manually entered driver activities at card insertion
	 * 								to be set for the VuCardIWRecord object
	 */
	public void setManualInputFlag( ManualInputFlag manualInputFlag ) {
		this.manualInputFlag = manualInputFlag;
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		node.addContent( cardHolderName.generateXMLElement( "cardHolderName" ) );
		node.addContent( fullCardNumber.generateXMLElement( "fullCardNumber" ) );
		node.addContent( cardExpiryDate.generateXMLElement( "cardExpiryDate" ) );
		node.addContent( cardInsertionTime.generateXMLElement( "cardInsertionTime" ) );
		node.addContent( vehicleOdometerValueAtInsertion.generateXMLElement( "vehicleOdometerValueAtInsertion" ) );
		node.addContent( cardSlotNumber.generateXMLElement( "cardSlotNumber" ) );
		node.addContent( cardWithdrawalTime.generateXMLElement( "cardWithdrawalTime" ) );
		node.addContent( vehicleOdometerValueAtWithdrawal.generateXMLElement( "vehicleOdometerValueAtWithdrawal" ) );
		node.addContent( previousVehicleInfo.generateXMLElement( "previousVehicleInfo" ) );
		node.addContent( manualInputFlag.generateXMLElement( "manualInputFlag" ) );

		return node;
	}
}
