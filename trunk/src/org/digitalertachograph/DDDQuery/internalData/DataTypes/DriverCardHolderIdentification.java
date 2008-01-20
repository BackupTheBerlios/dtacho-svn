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

import org.digitalertachograph.DDDQuery.internalData.DataClass;
import org.jdom.Element;

/**
 * Information, stored in a driver card, related to the identification of the cardholder.
 */
public class DriverCardHolderIdentification extends DataClass {
	/*
	 * DriverCardHolderIdentification ::= SEQUENCE {
	 * 	cardHolderName HolderName, 72 bytes
	 * 	cardHolderBirthDate Datef, 4 bytes
	 * 	cardHolderPreferredLanguage Language, 2 bytes
	 * }
	 */

	private HolderName cardHolderName;
	private Datef cardHolderBirthDate;
	private Language cardHolderPreferredLanguage;
	

	/**
	 * Constructor for a DriverCardHolderIdentification object
	 */
	public DriverCardHolderIdentification() {
		cardHolderName = new HolderName();
		cardHolderBirthDate = new Datef();
		cardHolderPreferredLanguage = new Language();
	}

	/**
	 * Constructor for a DriverCardHolderIdentification object
	 * 
	 * @param	value	byte array of a DriverCardHolderIdentification structure
	 * 					whose data is used when the DriverCardHolderIdentification
	 * 					object is created.
	 */
	public DriverCardHolderIdentification( byte[] value ) {
		cardHolderName = new HolderName( arrayCopy( value, 0, 72 ) );
		cardHolderBirthDate = new Datef( arrayCopy( value, 72, 4 ) );
		cardHolderPreferredLanguage = new Language( arrayCopy( value, 76, 2 ) );
	}
	
	/**
	 * Returns the name and the first name(s) of the holder of the driver card
	 * of a DriverCardHolderIdentification object.
	 * 
	 * @return	the name and the first name(s) of the holder of the driver card
	 * 			of the DriverCardHolderIdentification object
	 */
	public HolderName getCardHolderName() {
		return cardHolderName;
	}

	/**
	 * Sets the name and the first name(s) of the holder of the driver card
	 * of a DriverCardHolderIdentification object.
	 * 
	 * @param	cardHolderName	the name and the first name(s) of the holder of the driver card
	 * 							to be set for the DriverCardHolderIdentification object
	 */
	public void setCardHolderName( HolderName cardHolderName ) {
		this.cardHolderName = cardHolderName;
	}

	/**
	 * Returns the date of birth of the holder of the driver card
	 * of a DriverCardHolderIdentification object.
	 * 
	 * @return	the date of birth of the holder of the driver card
	 * 			of the DriverCardHolderIdentification object
	 */
	public Datef getCardHolderBirthDate() {
		return cardHolderBirthDate;
	}

	/**
	 * Sets the date of birth of the holder of the driver card
	 * of a DriverCardHolderIdentification object.
	 * 
	 * @param	cardHolderBirthDate		the date of birth of the holder of the driver card
	 * 									to be set for the DriverCardHolderIdentification object
	 */
	public void setCardHolderBirthDate( Datef cardHolderBirthDate ) {
		this.cardHolderBirthDate = cardHolderBirthDate;
	}

	/**
	 * Returns the preferred language of the card holder
	 * of a DriverCardHolderIdentification object.
	 * 
	 * @return	the preferred language of the card holder
	 * 			of the DriverCardHolderIdentification object
	 */
	public Language getCardHolderPreferredLanguage() {
		return cardHolderPreferredLanguage;
	}

	/**
	 * Sets the preferred language of the card holder
	 * of a DriverCardHolderIdentification object.
	 * 
	 * @param	cardHolderPreferredLanguage		the preferred language of the card holder
	 * 											to be set for the DriverCardHolderIdentification object
	 */
	public void setCardHolderPreferredLanguage( Language cardHolderPreferredLanguage ) {
		this.cardHolderPreferredLanguage = cardHolderPreferredLanguage;
	}
	
	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );
		node.addContent( cardHolderName.generateXMLElement( "cardHolderName" ) );
		node.addContent( cardHolderBirthDate.generateXMLElement( "cardHolderBirthDate" ) );
		node.addContent( cardHolderPreferredLanguage.generateXMLElement( "cardHolderPreferredLanguage" ) );

		return node;
	}
}
