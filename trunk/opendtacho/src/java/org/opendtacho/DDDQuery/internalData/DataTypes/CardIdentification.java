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
import org.opendtacho.DDDQuery.internalData.*;

/**
 * Information, stored in a card, related to the identification of the card.
 */
public class CardIdentification extends DataClass {
	/*
	 * CardIdentification ::= SEQUENCE {
	 * 	cardIssuingMemberState NationNumeric, 1 byte
	 * 	cardNumber CardNumber, 16 bytes
	 * 	cardIssuingAuthorityName Name, 36 bytes
	 * 	cardIssueDate TimeReal, 4 bytes
	 * 	cardValidityBegin TimeReal, 4 bytes
	 * 	cardExpiryDate TimeReal, 4 bytes
	 * }
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 65;

	private NationNumeric cardIssuingMemberState;
	private CardNumber cardNumber; 
	private Name cardIssuingAuthorityName;
	private TimeReal cardIssueDate;
	private TimeReal cardValidityBegin;
	private TimeReal cardExpiryDate;

	private DebugLogger debugLogger;


	/**
	 * Constructor for a CardIdentification object
	 */
	public CardIdentification() {
		cardIssuingMemberState = new NationNumeric();
		cardNumber = new CardNumber();
		cardIssuingAuthorityName = new Name();
		cardIssueDate = new TimeReal();
		cardValidityBegin = new TimeReal();
		cardExpiryDate = new TimeReal();
	}

	/**
	 * Constructor for a CardIdentification object
	 * 
	 * @param	value	byte array of a CardIdentification structure
	 * 					whose data is used when the CardIdentification
	 * 					object is created.
	 */
	public CardIdentification( byte[] value, short cardType ) {
		debugLogger = new DebugLogger();

		cardIssuingMemberState = new NationNumeric( value[ 0 ] );
		debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Card issuing member state: %s\n", cardIssuingMemberState.toString() );

		cardNumber = new CardNumber( arrayCopy( value, 1, 16 ), cardType );
		if ( cardType == EquipmentType.DRIVER_CARD ) {
			debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Card number: %s%s%s\n", cardNumber.getDriverIdentification(), cardNumber.getCardReplacementIndex().getCardReplacementIndex(), cardNumber.getCardRenewalIndex().getCardRenewalIndex() );
		}
		else {
			debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Card number: %s%s%s%s\n", cardNumber.getOwnerIdentification(), cardNumber.getCardConsecutiveIndex().getCardConsecutiveIndex(), cardNumber.getCardReplacementIndex().getCardReplacementIndex(), cardNumber.getCardRenewalIndex().getCardRenewalIndex() );
		}

		cardIssuingAuthorityName = new Name( arrayCopy( value, 17, 36 ) );
		debugLogger.printf( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Card issuing authority name: %s\n", cardIssuingAuthorityName.getNameString() );

		debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Card issue date:" );
		cardIssueDate = new TimeReal( arrayCopy( value, 53, 4 ) );

		debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Card validity begin:" );
		cardValidityBegin = new TimeReal( arrayCopy( value, 57, 4 ) );

		debugLogger.println( DebugLogger.DEBUG_LOGLEVEL_INFO_EXTENDED, " [INFO_EXT] Card expiry date:" );
		cardExpiryDate = new TimeReal( arrayCopy( value, 61, 4 ) );
	}

	/**
	 * Returns the code of the Member State issuing the card
	 * of a CardIdentification object.
	 * 
	 * @return	the code of the Member State issuing the card
	 * 			of the CardIdentification object
	 */
	public NationNumeric getCardIssuingMemberState() {
		return cardIssuingMemberState;
	}

	/**
	 * Sets the code of the Member State issuing the card
	 * of a CardIdentification object.
	 * 
	 * @param	cardIssuingMemberState	the code of the Member State issuing the card
	 * 									to be set for the CardIdentification object
	 */
	public void setCardIssuingMemberState( NationNumeric cardIssuingMemberState ) {
		this.cardIssuingMemberState = cardIssuingMemberState;
	}

	/**
	 * Sets the code of the Member State issuing the card
	 * of a CardIdentification object.
	 * 
	 * @param	cardIssuingMemberState	the code of the Member State issuing the card
	 * 									to be set for the CardIdentification object
	 */
	public void setCardIssuingMemberState( short cardIssuingMemberState ) {
		this.cardIssuingMemberState.setNationNumeric( cardIssuingMemberState );
	}

	/**
	 * Returns the card number of the card of a CardIdentification object.
	 * 
	 * @return	the card number of the card	of the CardIdentification object
	 */
	public CardNumber getCardNumber() {
		return cardNumber;
	}

	/**
	 * Sets the card number of the card of a CardIdentification object.
	 * 
	 * @param	cardNumber	the card number of the card to be set
	 * 						for the CardIdentification object
	 */
	public void setCardNumber( CardNumber cardNumber ) {
		this.cardNumber = cardNumber;
	}

	/**
	 * Returns the name of the authority having issued the card
	 * of a CardIdentification object.
	 * 
	 * @return	the name of the authority having issued the card
	 * 			of the CardIdentification object
	 */
	public Name getCardIssuingAuthorityName() {
		return cardIssuingAuthorityName;
	}

	/**
	 * Sets the name of the authority having issued the card of a CardIdentification object.
	 * 
	 * @param	cardIssuingAuthorityName	the name of the authority having issued the card
	 * 										to be set for the CardIdentification object
	 */
	public void setCardIssuingAuthorityName( Name cardIssuingAuthorityName ) {
		this.cardIssuingAuthorityName = cardIssuingAuthorityName;
	}

	/**
	 * Returns the issue date of the card to the current holder
	 * of a CardIdentification object.
	 * 
	 * @return	the issue date of the card to the current holder
	 * 			of the CardIdentification object
	 */
	public TimeReal getCardIssueDate() {
		return cardIssueDate;
	}

	/**
	 * Sets the issue date of the card to the current holder of a CardIdentification object.
	 * 
	 * @param	cardIssueDate	the issue date of the card to the current holder
	 * 							to be set for the CardIdentification object
	 */
	public void setCardIssueDate( TimeReal cardIssueDate ) {
		this.cardIssueDate = cardIssueDate;
	}

	/**
	 * Returns the first date of validity of the card of a CardIdentification object.
	 * 
	 * @return	the first date of validity of the card of a CardIdentification object
	 */
	public TimeReal getCardValidityBegin() {
		return cardValidityBegin;
	}

	/**
	 * Sets the first date of validity of the card of a CardIdentification object.
	 * 
	 * @param	cardValidityBegin	the first date of validity of the card
	 * 								to be set for the CardIdentification object
	 */
	public void setCardValidityBegin( TimeReal cardValidityBegin ) {
		this.cardValidityBegin = cardValidityBegin;
	}
	
	/**
	 * Returns the date when the validity of the card ends of a CardIdentification object.
	 * 
	 * @return	the date when the validity of the card ends of a CardIdentification object
	 */
	public TimeReal getCardExpiryDate() {
		return cardExpiryDate;
	}

	/**
	 * Sets the date when the validity of the card ends of a CardIdentification object.
	 * 
	 * @param	cardExpiryDate	the date when the validity of the card ends
	 * 							to be set for the CardIdentification object
	 */
	public void setCardExpiryDate( TimeReal cardExpiryDate ) {
		this.cardExpiryDate = cardExpiryDate;
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		node.addContent( cardIssuingMemberState.generateXMLElement( "cardIssuingMemberState" ) );
		node.addContent( cardNumber.generateXMLElement( "cardNumber" ) );
		node.addContent( cardIssuingAuthorityName.generateXMLElement( "cardIssuingAuthorityName" ) );
		node.addContent( cardIssueDate.generateXMLElement( "cardIssueDate" ) );
		node.addContent( cardValidityBegin.generateXMLElement( "cardValidityBegin" ) );
		node.addContent( cardExpiryDate.generateXMLElement( "cardExpiryDate" ) );

		return node;
	}
}

