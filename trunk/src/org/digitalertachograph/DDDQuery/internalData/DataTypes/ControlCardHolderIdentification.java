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
 * Information, stored in a control card, related to the identification of the cardholder.
 */
public class ControlCardHolderIdentification extends DataClass {
	/*
	 * ControlCardHolderIdentification ::= SEQUENCE {
	 * 	controlBodyName Name, 36 bytes
	 * 	controlBodyAddress Address, 36 bytes
	 * 	cardHolderName HolderName, 72 bytes
	 * 	cardHolderPreferredLanguage Language, 2 bytes
	 * }
	 */
	
	private Name controlBodyName;
	private Address controlBodyAddress;
	private HolderName cardHolderName;
	private Language cardHolderPreferredLanguage;
	

	/**
	 * Constructor for a ControlCardHolderIdentification object
	 */
	public ControlCardHolderIdentification() {
		controlBodyName = new Name();
		controlBodyAddress = new Address();
		cardHolderName = new HolderName();
		cardHolderPreferredLanguage = new Language();
	}

	/**
	 * Constructor for a ControlCardHolderIdentification object
	 * 
	 * @param	value	byte array of a ControlCardHolderIdentification structure
	 * 					whose data is used when the ControlCardHolderIdentification
	 * 					object is created.
	 */
	public ControlCardHolderIdentification( byte[] value ) {
		controlBodyName = new Name( arrayCopy( value, 0, 36 ) );
		controlBodyAddress = new Address( arrayCopy( value, 36, 36 ) );
		cardHolderName = new HolderName( arrayCopy( value, 72, 72 ) );
		cardHolderPreferredLanguage = new Language( arrayCopy( value, 144, 2 ) );
	}

	/**
	 * Returns the name of the control body of the card holder
	 * of a ControlCardHolderIdentification object.
	 * 
	 * @return	the name of the control body of the card holder
	 * 			of the ControlCardHolderIdentification object
	 */
	public Name getControlBodyName() {
		return controlBodyName;
	}

	/**
	 * Sets the name of the control body of the card holder
	 * of a ControlCardHolderIdentification object.
	 * 
	 * @param	controlBodyName		the name of the control body of the card holder
	 * 								to be set for the ControlCardHolderIdentification object
	 */
	public void setControlBodyName( Name controlBodyName ) {
		this.controlBodyName = controlBodyName;
	}
	
	/**
	 * Returns the address of the control body of the card holder
	 * of a ControlCardHolderIdentification object.
	 * 
	 * @return	the address of the control body of the card holder
	 * 			of the ControlCardHolderIdentification object
	 */
	public Address getControlBodyAddress() {
		return controlBodyAddress;
	}

	/**
	 * Sets the address of the control body of the card holder
	 * of a ControlCardHolderIdentification object.
	 * 
	 * @param	controlBodyAddress	the address of the control body of the card holder
	 * 								to be set for the ControlCardHolderIdentification object
	 */
	public void setControlBodyAddress( Address controlBodyAddress ) {
		this.controlBodyAddress = controlBodyAddress;
	}

	/**
	 * Returns the name and the first name(s) of the holder of the control card
	 * of a ControlCardHolderIdentification object.
	 * 
	 * @return	the name and the first name(s) of the holder of the control card
	 * 			of the ControlCardHolderIdentification object
	 */
	public HolderName getCardHolderName() {
		return cardHolderName;
	}

	/**
	 * Sets the name and the first name(s) of the holder of the control card
	 * of a ControlCardHolderIdentification object.
	 * 
	 * @param	cardHolderName	the name and the first name(s) of the holder of the control card
	 * 							to be set for the ControlCardHolderIdentification object
	 */
	public void setCardHolderName( HolderName cardHolderName ) {
		this.cardHolderName = cardHolderName;
	}

	/**
	 * Returns the preferred language of the card holder
	 * of a ControlCardHolderIdentification object.
	 * 
	 * @return	the preferred language of the card holder
	 * 			of the ControlCardHolderIdentification object
	 */
	public Language getCardHolderPreferredLanguage() {
		return cardHolderPreferredLanguage;
	}

	/**
	 * Sets the preferred language of the card holder
	 * of a ControlCardHolderIdentification object.
	 * 
	 * @param	cardHolderPreferredLanguage		the preferred language of the card holder
	 * 											to be set for the ControlCardHolderIdentification object
	 */
	public void setCardHolderPreferredLanguage( Language cardHolderPreferredLanguage ) {
		this.cardHolderPreferredLanguage = cardHolderPreferredLanguage;
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );
		node.addContent( controlBodyName.generateXMLElement( "controlBodyName" ) );
		node.addContent( controlBodyAddress.generateXMLElement( "controlBodyAddress" ) );
		node.addContent( cardHolderName.generateXMLElement( "cardHolderName" ) );
		node.addContent( cardHolderPreferredLanguage.generateXMLElement( "cardHolderPreferredLanguage" ) );

		return node;
	}
}
