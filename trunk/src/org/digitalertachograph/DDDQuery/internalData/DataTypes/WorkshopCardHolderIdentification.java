/*
    $Id:WorkshopCardHolderIdentification.java 26 2008-02-25 22:28:27Z deetee $

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

import org.digitalertachograph.DDDQuery.internalData.DataClass;
import org.jdom.Element;

/**
 * Information, stored in a workshop card, related to the identification
 * of the cardholder.
 */
public class WorkshopCardHolderIdentification extends DataClass {
	/*
	 * WorkshopCardHolderIdentification ::= SEQUENCE {
	 * 	workshopName Name, 36 bytes
	 * 	workshopAddress Address, 36 bytes
	 * 	cardHolderName HolderName, 72 bytes
	 * 	cardHolderPreferredLanguage Language, 2 bytes
	 * }
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 146;

	private Name workshopName;
	private Address workshopAddress;
	private HolderName cardHolderName;
	private Language cardHolderPreferredLanguage;
	
	
	/**
	 * Constructor for a WorkshopCardHolderIdentification object
	 */
	public WorkshopCardHolderIdentification() {
		workshopName = new Name();
		workshopAddress = new Address();
		cardHolderName = new HolderName();
		cardHolderPreferredLanguage = new Language();
	}

	/**
	 * Constructor for a WorkshopCardHolderIdentification object
	 * 
	 * @param	value	byte array of a WorkshopCardHolderIdentification structure
	 * 					whose data is used when the WorkshopCardHolderIdentification
	 * 					object is created.
	 */
	public WorkshopCardHolderIdentification( byte[] value ) {
		workshopName = new Name( arrayCopy( value, 0, 36 ) );
		workshopAddress = new Address( arrayCopy( value, 36, 36 ) );
		cardHolderName = new HolderName( arrayCopy( value, 72, 72 ) );
		cardHolderPreferredLanguage = new Language( arrayCopy( value, 144, 2 ) );
	}

	/**
	 * Returns the name of the workshop of the card holder
	 * of a WorkshopCardHolderIdentification object.
	 * 
	 * @return	the name of the workshop of the card holder
	 * 			of the WorkshopCardHolderIdentification object
	 */
	public Name getWorkshopName() {
		return workshopName;
	}

	/**
	 * Sets the name of the workshop of the card holder
	 * of a WorkshopCardHolderIdentification object.
	 * 
	 * @param	workshopName		the name of the workshop of the card holder
	 * 								to be set for the WorkshopCardHolderIdentification object
	 */
	public void setWorkshopName( Name workshopName ) {
		this.workshopName = workshopName;
	}

	/**
	 * Returns the address of the workshop of the card holder
	 * of a WorkshopCardHolderIdentification object.
	 * 
	 * @return	the address of the workshop of the card holder
	 * 			of the WorkshopCardHolderIdentification object
	 */
	public Address getWorkshopAddress() {
		return workshopAddress;
	}

	/**
	 * Sets the address of the workshop of the card holder
	 * of a WorkshopCardHolderIdentification object.
	 * 
	 * @param	workshopAddress		the address of the workshop of the card holder
	 * 								to be set for the WorkshopCardHolderIdentification object
	 */
	public void setWorkshopAddress( Address workshopAddress ) {
		this.workshopAddress = workshopAddress;
	}

	/**
	 * Returns the name and the first name(s) of the holder (e.g. name of the mechanic)
	 * of a WorkshopCardHolderIdentification object.
	 * 
	 * @return	the name and the first name(s) of the holder (e.g. name of the mechanic)
	 * 			of the WorkshopCardHolderIdentification object
	 */
	public HolderName getCardHolderName() {
		return cardHolderName;
	}

	/**
	 * Sets the name and the first name(s) of the holder (e.g. name of the mechanic)
	 * of a WorkshopCardHolderIdentification object.
	 * 
	 * @param	cardHolderName		the name and the first name(s) of the holder (e.g. name of the mechanic)
	 * 								to be set for the WorkshopCardHolderIdentification object
	 */
	public void setCardHolderName( HolderName cardHolderName ) {
		this.cardHolderName = cardHolderName;
	}

	/**
	 * Returns the preferred language of the card holder
	 * of a WorkshopCardHolderIdentification object.
	 * 
	 * @return	the preferred language of the card holder
	 * 			of the WorkshopCardHolderIdentification object
	 */
	public Language getCardHolderPreferredLanguage() {
		return cardHolderPreferredLanguage;
	}

	/**
	 * Sets the preferred language of the card holder
	 * of a WorkshopCardHolderIdentification object.
	 * 
	 * @param	cardHolderPreferredLanguage		the preferred language of the card holder
	 *			 								to be set for the WorkshopCardHolderIdentification object
	 */
	public void setCardHolderPreferredLanguage( Language cardHolderPreferredLanguage ) {
		this.cardHolderPreferredLanguage = cardHolderPreferredLanguage;
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		node.addContent( workshopName.generateXMLElement( "workshopName" ) );
		node.addContent( workshopAddress.generateXMLElement( "workshopAddress" ) );
		node.addContent( cardHolderName.generateXMLElement( "cardHolderName" ) );
		node.addContent( cardHolderPreferredLanguage.generateXMLElement( "cardHolderPreferredLanguage" ) );

		return node;
	}
}
