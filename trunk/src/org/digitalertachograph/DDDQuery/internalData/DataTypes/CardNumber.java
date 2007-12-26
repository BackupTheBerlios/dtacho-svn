/*   Copyright (C) 2007, Martin Barth, Gerald Schnabel

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

public class CardNumber extends DataClass {
	
	// Abhängig vom Kartentyp (cardType) wird eine andere Wahl getroffen!
	
	/*
	 * 	 * CardNumber ::= CHOICE {
	 * 	SEQUENCE {
	 * 		driverIdentification IA5String(SIZE(14)), 14 bytes
	 * 		cardReplacementIndex CardReplacementIndex, 1 byte
	 * 		cardRenewalIndex CardRenewalIndex, 1 byte
	 * 		}
	 * 	SEQUENCE {
	 * 		ownerIdentification IA5String(SIZE(13)), 13 bytes
	 * 		cardConsecutiveIndex CardConsecutiveIndex, 1 byte
	 * 		cardReplacementIndex CardReplacementIndex, 1 byte
	 * 		cardRenewalIndex CardRenewalIndex, 1 byte
	 * 		}
	 * }
	 * ---
	 * CardReplacementIndex ::= IA5String(SIZE(1)), 1 byte
	 * ---
	 * CardConsecutiveIndex ::= IA5String(SIZE(1)), 1 byte
	 * Value assignment: (see this Annex Chapter VII)
	 * Order for increase: "0..9, A..Z, a..z".
	 * ---
	 * CardRenewalIndex ::= IA5String(SIZE(1)), 1 byte
	 * Value assignment: (see this Annex Chapter VII).
	 * "0" First issue.
	 * Order for increase: "0..9, A..Z".
	 */
	
	// TODO versteh mich und schreib mich!!!!!!
	
	private String driverIdentification;
	private String ownerIdentification;
	private char cardConsecutiveIndex;
	private char cardReplacementIndex;
	private char cardRenewalIndex;
	private int cardType;
	
	
	/**
	 * Constructor for a CardNumber object
	 * 
	 * @param	value	byte array of a CardNumber structure
	 * 					whose data is used when the CardNumber
	 * 					object is created.
	 */
	public CardNumber(byte[] value, int cardType){
		this.cardType = cardType;
		if(cardType == EquipmentType.DRIVER_CARD){
			driverIdentification = new String( arrayCopy(value, 0, 14));
			cardReplacementIndex = (char) value[14];
			cardRenewalIndex = (char) value[15];
		}else{
			ownerIdentification = new String( arrayCopy(value, 0, 13));
			cardConsecutiveIndex = (char) value[13];
			cardReplacementIndex = (char) value[14];
			cardRenewalIndex = (char) value[15];
		}
	}


	public char getCardConsecutiveIndex() {
		return cardConsecutiveIndex;
	}


	public void setCardConsecutiveIndex(char cardConsecutiveIndex) {
		this.cardConsecutiveIndex = cardConsecutiveIndex;
	}


	public char getCardRenewalIndex() {
		return cardRenewalIndex;
	}


	public void setCardRenewalIndex(char cardRenewalIndex) {
		this.cardRenewalIndex = cardRenewalIndex;
	}


	public char getCardReplacementIndex() {
		return cardReplacementIndex;
	}


	public void setCardReplacementIndex(char cardReplacementIndex) {
		this.cardReplacementIndex = cardReplacementIndex;
	}


	public String getDriverIdentification() {
		return driverIdentification;
	}


	public void setDriverIdentification(String driverIdentification) {
		this.driverIdentification = driverIdentification;
	}


	public String getOwnerIdentification() {
		return ownerIdentification;
	}


	public void setOwnerIdentification(String ownerIdentification) {
		this.ownerIdentification = ownerIdentification;
	}

	
	@Override
	public Element generateXMLElement(String name) {
		Controller c = Controller.getInstance();
		
		Element node = new Element(name);
		
		if(c.isAnonymized()){
			if(cardType == EquipmentType.DRIVER_CARD){
				node.addContent( new Element("driverIdentification").setText("1anonymous0123"));
				
				node.addContent( new Element("cardReplacementIndex").setText("x"));
				node.addContent( new Element("cardRenewalIndex").setText("y"));
			}else{
				node.addContent( new Element("ownerIdentification").setText("1anonymous012"));

				node.addContent( new Element("cardConsecutiveIndex").setText("y"));
				node.addContent( new Element("cardReplacementIndex").setText("x"));
				node.addContent( new Element("cardRenewalIndex").setText("y"));
			}
		}else{
		
			if(cardType == EquipmentType.DRIVER_CARD){
				node.addContent( new Element("driverIdentification").setText(driverIdentification));
				node.addContent( new Element("cardReplacementIndex").setText(Character.toString(cardReplacementIndex)));
				node.addContent( new Element("cardRenewalIndex").setText(Character.toString(cardRenewalIndex)));
			}else{
				node.addContent( new Element("ownerIdentification").setText(ownerIdentification));
				node.addContent( new Element("cardConsecutiveIndex").setText(Character.toString(cardConsecutiveIndex)));
				node.addContent( new Element("cardReplacementIndex").setText(Character.toString(cardReplacementIndex)));
				node.addContent( new Element("cardRenewalIndex").setText(Character.toString(cardRenewalIndex)));
			}
		}
		return node;
	}
}
