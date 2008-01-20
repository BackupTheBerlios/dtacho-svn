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

package org.digitalertachograph.DDDQuery.internalData.DataTypes;

import org.digitalertachograph.DDDQuery.Controller;
import org.digitalertachograph.DDDQuery.internalData.DataClass;
import org.jdom.Element;

/**
 * Card number of a driver card or a workshop/control/company card.
 */
public class CardNumber extends DataClass {
	
	// Abhängig vom Kartentyp (cardType) wird eine andere Wahl getroffen!
	
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
	

	// card type
	private int cardType;

	private String driverIdentification;
	private char cardReplacementIndex;
	private char cardRenewalIndex;

	private String ownerIdentification;
	private char cardConsecutiveIndex;

	
	/**
	 * Constructor for a CardNumber object
	 */
	public CardNumber() {
		cardType = 0;
		driverIdentification = new String();
		cardReplacementIndex = '\0';
		cardRenewalIndex = '\0';
		ownerIdentification = new String();
		cardConsecutiveIndex = '\0';
	}

	/**
	 * Constructor for a CardNumber object
	 */
	public CardNumber( int cardType ) {
		this.cardType = cardType;
		
		if ( cardType == EquipmentType.DRIVER_CARD ) {
			// driver card
			driverIdentification = new String();
			cardReplacementIndex = '\0';
			cardRenewalIndex = ' ';

			ownerIdentification = new String();
			cardConsecutiveIndex = '\0';
		}
		else {
			// workshop, control or company card
			ownerIdentification = new String();
			cardConsecutiveIndex = '\0';
			cardReplacementIndex = '\0';
			cardRenewalIndex = '\0';

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
	public CardNumber( byte[] value, int cardType ) {
		this.cardType = cardType;
		if ( cardType == EquipmentType.DRIVER_CARD ) {
			driverIdentification = new String( arrayCopy( value, 0, 14 ) );
			cardReplacementIndex = (char)value[ 14 ];
			cardRenewalIndex = (char)value[ 15 ];
		}
		else {
			ownerIdentification = new String( arrayCopy( value, 0, 13 ) );
			cardConsecutiveIndex = (char)value[ 13 ];
			cardReplacementIndex = (char)value[ 14 ];
			cardRenewalIndex = (char)value[ 15 ];
		}
	}

	/**
	 * Returns the type of the tachograph card of a FullCardNumber object.
	 * 
	 * @return	the type of the tachograph card of the FullCardNumber object
	 */
	public int getCardType() {
		return cardType;
	}
	
	/**
	 * Sets the type of the tachograph card of a FullCardNumber object.
	 * 
	 * @param	cardType	the type of the tachograph card	to be set for
	 * 						the FullCardNumber object
	 */
	public void setCardType( int cardType ) {
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
		this.driverIdentification = driverIdentification;
	}

	/**
	 * Returns the card replacement index of a CardNumber object.
	 * 
	 * @return	the card replacement index of the CardNumber object
	 */
	public char getCardReplacementIndex() {
		return cardReplacementIndex;
	}

	/**
	 * Sets the card replacement index of a CardNumber object.
	 * 
	 * @param	cardReplacementIndex	the card replacement index
	 * 									to be set for the CardNumber object
	 */
	public void setCardReplacementIndex( char cardReplacementIndex ) {
		this.cardReplacementIndex = cardReplacementIndex;
	}

	/**
	 * Returns the card renewal index of a CardNumber object.
	 * 
	 * @return	the card renewal index of the CardNumber object
	 */
	public char getCardRenewalIndex() {
		return cardRenewalIndex;
	}

	/**
	 * Sets the card renewal index of a CardNumber object.
	 * 
	 * @param	cardRenewalIndex	the card renewal index
	 * 									to be set for the CardNumber object
	 */
	public void setCardRenewalIndex( char cardRenewalIndex ) {
		this.cardRenewalIndex = cardRenewalIndex;
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
		this.ownerIdentification = ownerIdentification;
	}

	/**
	 * Returns the card consecutive index of a CardNumber object.
	 * 
	 * @return	the card consecutive index of the CardNumber object
	 */
	public char getCardConsecutiveIndex() {
		return cardConsecutiveIndex;
	}

	/**
	 * Sets the card consecutive index of a CardNumber object.
	 * 
	 * @param	cardConsecutiveIndex	the card consecutive index
	 * 									to be set for the CardNumber object
	 */
	public void setCardConsecutiveIndex( char cardConsecutiveIndex ) {
		this.cardConsecutiveIndex = cardConsecutiveIndex;
	}


	@Override
	public Element generateXMLElement( String name ) {
		Controller c = Controller.getInstance();
		
		Element node = new Element( name );
		
		if ( c.isAnonymized() ) {
			if ( cardType == EquipmentType.DRIVER_CARD ) {
				node.addContent( new Element( "driverIdentification" ).setText( "1anonymous0123" ) );
				
				node.addContent( new Element( "cardReplacementIndex" ).setText( "x" ) );
				node.addContent( new Element( "cardRenewalIndex" ).setText( "y" ) );
			}
			else {
				node.addContent( new Element( "ownerIdentification" ).setText( "1anonymous012" ) );

				node.addContent( new Element( "cardConsecutiveIndex" ).setText( "y" ) );
				node.addContent( new Element( "cardReplacementIndex" ).setText( "x" ) );
				node.addContent( new Element( "cardRenewalIndex" ).setText( "y" ) );
			}
		}
		else {
			if ( cardType == EquipmentType.DRIVER_CARD ) {
				node.addContent( new Element( "driverIdentification" ).setText( driverIdentification ) );
				node.addContent( new Element( "cardReplacementIndex" ).setText(Character.toString( cardReplacementIndex ) ) );
				node.addContent( new Element( "cardRenewalIndex" ).setText(Character.toString( cardRenewalIndex ) ) );
			}
			else {
				node.addContent( new Element( "ownerIdentification" ).setText( ownerIdentification ) );
				node.addContent( new Element( "cardConsecutiveIndex" ).setText( Character.toString( cardConsecutiveIndex ) ) );
				node.addContent( new Element( "cardReplacementIndex" ).setText( Character.toString( cardReplacementIndex ) ) );
				node.addContent( new Element( "cardRenewalIndex" ).setText( Character.toString( cardRenewalIndex ) ) );
			}
		}

		return node;
	}
}
