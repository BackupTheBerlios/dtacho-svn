/*
    $Id:CardNumber.java 26 2008-02-25 22:28:27Z deetee $

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

import org.digitalertachograph.DDDQuery.Controller;
import org.digitalertachograph.DDDQuery.internalData.DataClass;
import org.jdom.Element;

/**
 * Card number of a driver card or a workshop/control/company card.
 */
public class CardNumber extends DataClass {

	// Depending on the card type (cardType) a choice is made!

	/*
	 * CardNumber ::= CHOICE {
	 * 	SEQUENCE {
	 * 		driverIdentification IA5String(SIZE(14)), 14 bytes
	 * 		cardReplacementIndex CardReplacementIndex, 1 byte
	 * 		cardRenewalIndex CardRenewalIndex, 1 byte
	 * 	}
	 * 	SEQUENCE {
	 * 		ownerIdentification IA5String(SIZE(13)), 13 bytes
	 * 		cardConsecutiveIndex CardConsecutiveIndex, 1 byte
	 * 		cardReplacementIndex CardReplacementIndex, 1 byte
	 * 		cardRenewalIndex CardRenewalIndex, 1 byte
	 * 	}
	 * }
	 * The first sequence of the choice is suitable to code a driver
	 * card number, the second sequence of the choice is suitable to
	 * code workshop, control and company card numbers. 
	 * ---
	 * CardReplacementIndex ::= IA5String(SIZE(1)), 1 byte
	 * ---
	 * CardConsecutiveIndex ::= IA5String(SIZE(1)), 1 byte
	 * Value assignment: (see Annex Chapter VII)
	 * Order for increase: "0..9, A..Z, a..z".
	 * ---
	 * CardRenewalIndex ::= IA5String(SIZE(1)), 1 byte
	 * Value assignment: (see Annex Chapter VII).
	 * "0" First issue.
	 * Order for increase: "0..9, A..Z".
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 16;

	// card type
	private short cardType;

	private String driverIdentification;
	private CardReplacementIndex cardReplacementIndex;
	private CardRenewalIndex cardRenewalIndex;

	private String ownerIdentification;
	private CardConsecutiveIndex cardConsecutiveIndex;


	/**
	 * Constructor for a CardNumber object
	 */
	public CardNumber() {
		cardType = 0;
		driverIdentification = new String();
		cardReplacementIndex = new CardReplacementIndex();
		cardRenewalIndex = new CardRenewalIndex();
		ownerIdentification = new String();
		cardConsecutiveIndex = new CardConsecutiveIndex();
	}

	/**
	 * Constructor for a CardNumber object
	 */
	public CardNumber( short cardType ) {
		this.cardType = cardType;
		
		if ( cardType == EquipmentType.DRIVER_CARD ) {
			// driver card
			driverIdentification = new String();
			cardReplacementIndex = new CardReplacementIndex();
			cardRenewalIndex = new CardRenewalIndex();

			ownerIdentification = new String();
			cardConsecutiveIndex = new CardConsecutiveIndex();
		}
		else {
			// workshop, control or company card
			ownerIdentification = new String();
			cardConsecutiveIndex = new CardConsecutiveIndex();
			cardReplacementIndex = new CardReplacementIndex();
			cardRenewalIndex = new CardRenewalIndex();

			driverIdentification = new String();
		}
	}

	/**
	 * Constructor for a CardNumber object
	 * 
	 * @param	value		byte array of a CardNumber structure
	 * 						whose data is used when the CardNumber
	 * 						object is created.
	 * @param	cardType	the card type ({@link EquipmentType#DRIVER_CARD DRIVER_CARD},
	 * 						{@link EquipmentType#WORKSHOP_CARD WORKSHOP_CARD},
	 * 						{@link EquipmentType#CONTROL_CARD CONTROL_CARD},
	 * 						{@link EquipmentType#COMPANY_CARD COMPANY_CARD},)
	 */
	public CardNumber( byte[] value, short cardType ) {
		this.cardType = cardType;

		if ( cardType == EquipmentType.DRIVER_CARD ) {
			driverIdentification = new String( arrayCopy( value, 0, 14 ) );
			cardReplacementIndex = new CardReplacementIndex( arrayCopy( value, 14, 1 ) );
			cardRenewalIndex = new CardRenewalIndex( arrayCopy( value, 15, 1 ) );
		}
		else {
			ownerIdentification = new String( arrayCopy( value, 0, 13 ) );
			cardConsecutiveIndex = new CardConsecutiveIndex( arrayCopy( value, 13, 1 ) );
			cardReplacementIndex = new CardReplacementIndex( arrayCopy( value, 14, 1 ) );
			cardRenewalIndex = new CardRenewalIndex( arrayCopy( value, 15, 1 ) );
		}
	}

	/**
	 * Returns the type of the tachograph card of a FullCardNumber object.
	 * 
	 * @return	the type of the tachograph card of the FullCardNumber object
	 */
	public short getCardType() {
		return cardType;
	}

	/**
	 * Sets the type of the tachograph card of a FullCardNumber object.
	 * 
	 * @param	cardType	the type of the tachograph card	to be set for
	 * 						the FullCardNumber object
	 */
	public void setCardType( short cardType ) {
		this.cardType = cardType;
	}

	/**
	 * Returns the unique identification of a driver in a Member State
	 * of a CardNumber object.
	 * 
	 * @return	the unique identification of a driver in a Member State
	 * 			of the CardNumber object
	 */
	public String getDriverIdentification() {
		return driverIdentification;
	}

	/**
	 * Sets the unique identification of a driver in a Member State
	 * of a CardNumber object.
	 * 
	 * @param	driverIdentification	the unique identification of a driver in a Member State
	 * 									to be set for the CardNumber object
	 */
	public void setDriverIdentification( String driverIdentification ) {
		int driverIdentificationLength = driverIdentification.length();

		if ( driverIdentificationLength > 14 ) {
			driverIdentificationLength = 14;
		}

		this.driverIdentification = driverIdentification.substring( 0, driverIdentificationLength );
	}

	/**
	 * Returns the card replacement index of a CardNumber object.
	 * 
	 * @return	the card replacement index of the CardNumber object
	 */
	public String getCardReplacementIndex() {
		return cardReplacementIndex.getCardReplacementIndex();
	}

	/**
	 * Sets the card replacement index of a CardNumber object.
	 * 
	 * @param	cardReplacementIndex	the card replacement index
	 * 									to be set for the CardNumber object
	 */
	public void setCardReplacementIndex( String cardReplacementIndex ) {
		this.cardReplacementIndex.setCardReplacementIndex( cardReplacementIndex );
	}

	/**
	 * Returns the card renewal index of a CardNumber object.
	 * 
	 * @return	the card renewal index of the CardNumber object
	 */
	public String getCardRenewalIndex() {
		return cardRenewalIndex.getCardRenewalIndex();
	}

	/**
	 * Sets the card renewal index of a CardNumber object.
	 * 
	 * @param	cardRenewalIndex	the card renewal index
	 * 									to be set for the CardNumber object
	 */
	public void setCardRenewalIndex( String cardRenewalIndex ) {
		this.cardRenewalIndex.setCardRenewalIndex( cardRenewalIndex );
	}

	/**
	 * Returns the unique identification of a company or a workshop
	 * or a control body within a Member State of a CardNumber object.
	 * 
	 * @return	the unique identification of a company or a workshop
	 * 			or a control body within a Member State of the CardNumber object
	 */
	public String getOwnerIdentification() {
		return ownerIdentification;
	}

	/**
	 * Sets the unique identification of a company or a workshop
	 * or a control body within a Member State of a CardNumber object.
	 * 
	 * @param	ownerIdentification		the unique identification of a company or a workshop
	 *									or a control body within a Member State
	 * 									to be set for the CardNumber object
	 */
	public void setOwnerIdentification( String ownerIdentification ) {
		int ownerIdentificationLength = ownerIdentification.length();

		if ( ownerIdentificationLength > 13 ) {
			ownerIdentificationLength = 13;
		}

		this.ownerIdentification = ownerIdentification.substring( 0, ownerIdentificationLength );
	}

	/**
	 * Returns the card consecutive index of a CardNumber object.
	 * 
	 * @return	the card consecutive index of the CardNumber object
	 */
	public String getCardConsecutiveIndex() {
		return cardConsecutiveIndex.getCardConsecutiveIndex();
	}

	/**
	 * Sets the card consecutive index of a CardNumber object.
	 * 
	 * @param	cardConsecutiveIndex	the card consecutive index
	 * 									to be set for the CardNumber object
	 */
	public void setCardConsecutiveIndex( String cardConsecutiveIndex ) {
		this.cardConsecutiveIndex.setCardConsecutiveIndex( cardConsecutiveIndex );
	}

	@Override
	public Element generateXMLElement( String name ) {
		Controller c = Controller.getInstance();

		Element node = new Element( name );

		if ( c.isAnonymized() ) {
			if ( cardType == EquipmentType.DRIVER_CARD ) {
				node.addContent( new Element( "driverIdentification" ).setText( "1anonymous0123" ) );
				node.addContent( new CardReplacementIndex( "x" ).generateXMLElement( "cardReplacementIndex" ) );
				node.addContent( new CardRenewalIndex( "y" ).generateXMLElement( "cardRenewalIndex" ) );
			}
			else {
				node.addContent( new Element( "ownerIdentification" ).setText( "1anonymous012" ) );
				node.addContent( new CardConsecutiveIndex( "y" ).generateXMLElement( "cardConsecutiveIndex" ) );
				node.addContent( new CardReplacementIndex( "x" ).generateXMLElement( "cardReplacementIndex" ) );
				node.addContent( new CardRenewalIndex( "y" ).generateXMLElement( "cardRenewalIndex" ) );
			}
		}
		else {
			if ( cardType == EquipmentType.DRIVER_CARD ) {
				if ( isValidXMLString( driverIdentification ) == false ) {
					node.addContent( new Element( "driverIdentification" ) );
				} else {
					node.addContent( new Element( "driverIdentification" ).setText( driverIdentification ) );
				}
				node.addContent( cardReplacementIndex.generateXMLElement( "cardReplacementIndex" ) );
				node.addContent( cardRenewalIndex.generateXMLElement( "cardRenewalIndex" ) );
			}
			else {
				if ( isValidXMLString( ownerIdentification ) == false ) {
					node.addContent( new Element( "ownerIdentification" ) );
				}
				else {
					node.addContent( new Element( "ownerIdentification" ).setText( ownerIdentification ) );
				}
				node.addContent( cardConsecutiveIndex.generateXMLElement( "cardConsecutiveIndex" ) );
				node.addContent( cardReplacementIndex.generateXMLElement( "cardReplacementIndex" ) );
				node.addContent( cardRenewalIndex.generateXMLElement( "cardRenewalIndex" ) );
			}
		}

		return node;
	}
}
