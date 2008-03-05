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

package org.digitalertachograph.DDDQuery.internalData.DataTypes;

import org.digitalertachograph.DDDQuery.internalData.DataClass;
import org.jdom.Element;

/**
 * Information, stored in a card, related to the identification of the integrated
 * circuit (IC) card.
 */
public class CardIccIdentification extends DataClass {
	/*
	 * CardIccIdentification ::= SEQUENCE {
	 *  clockStop OCTET STRING (SIZE(1)), 1 byte				0x00
	 *  cardExtendedSerialNumber ExtendedSerialNumber, 8 bytes	0x00..0x00
	 *  cardApprovalNumber CardApprovalNumber, 8 bytes			0x20..0x20
	 *  cardPersonaliserID OCTET STRING (SIZE(1)), 1 byte		0x00
	 *  embedderIcAssemblerID OCTET STRING (SIZE(5)), 5 bytes	0x00..0x00
	 *  icIdentifier OCTET STRING (SIZE(2)), 2 bytes			0x00..0x00
	 * }
	 * ---
	 * ExtendedSerialNumber ::= SEQUENCE {
	 * 		serialNumber INTEGER(0..2^32-1), 4 bytes
	 * 		monthYear BCDString(SIZE(2)), 2 bytes
	 * 		type OCTET STRING(SIZE(1)), 1 byte
	 * 		manufacturerCode ManufacturerCode, 1 byte
	 * }
	 * ---
	 * CardApprovalNumber ::= IA5String(SIZE(8))
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 25;

	private byte clockStop;
	private ExtendedSerialNumber cardExtendedSerialNumber;
	private CardApprovalNumber cardApprovalNumber;
	private byte cardPersonaliserID;
	private byte[] embedderIcAssemblerId;
	private byte[] icIdentifier;

	
	/**
	 * Constructor for a CardIccIdentification object
	 */
	public CardIccIdentification() {
		clockStop = 0;
		cardExtendedSerialNumber = new ExtendedSerialNumber();
		cardApprovalNumber = new CardApprovalNumber();
		cardPersonaliserID = 0;
		embedderIcAssemblerId = new byte[ 5 ];
		icIdentifier = new byte[ 2 ];
	}

	/**
	 * Constructor for a CardIccIdentification object
	 * 
	 * @param	value	byte array of a CardIccIdentification structure
	 * 					whose data is used when the CardIccIdentification
	 * 					object is created.
	 */
	public CardIccIdentification( byte[] value ) {
		clockStop = value[ 0 ];
		cardExtendedSerialNumber = new ExtendedSerialNumber( arrayCopy( value, 1, 8 ) );
		cardApprovalNumber = new CardApprovalNumber( arrayCopy( value, 9, 8 ) );
		cardPersonaliserID = value[ 17 ];
		embedderIcAssemblerId = arrayCopy( value, 18, 5 );
		icIdentifier = arrayCopy( value, 23, 2 );
	}
	
	/**
	 * Returns the Clockstop mode as defined in EN 726-3
	 * of a CardIccIdentification object.
	 * 
	 * @return	the Clockstop mode as defined in EN 726-3
	 * 			of the CardIccIdentification object
	 */
	public byte getClockStop() {
		return clockStop;
	}

	/**
	 * Sets the Clockstop mode as defined in EN 726-3
	 * of a CardIccIdentification object.
	 * 
	 * @param	clockStop	the Clockstop mode as defined in EN 726-3
	 * 						to be set for the CardIccIdentification object
	 */
	public void setClockStop( byte clockStop ) {
		this.clockStop = clockStop;
	}

	/**
	 * Returns the IC card serial number an IC card manufacturing reference as defined in EN 726-3
	 * of a CardIccIdentification object.
	 * 
	 * @return	the IC card serial number an IC card manufacturing reference as defined in EN 726-3
	 * 			of the CardIccIdentification object
	 */
	public ExtendedSerialNumber getCardExtendedSerialNumber() {
		return cardExtendedSerialNumber;
	}

	/**
	 * Sets the IC card serial number an IC card manufacturing reference as defined in EN 726-3
	 * of a CardIccIdentification object.
	 * 
	 * @param	cardExtendedSerialNumber	the IC card serial number an IC card manufacturing reference as defined in EN 726-3
	 * 										to be set for the CardIccIdentification object
	 */
	public void setCardExtendedSerialNumber( ExtendedSerialNumber cardExtendedSerialNumber) {
		this.cardExtendedSerialNumber = cardExtendedSerialNumber;
	}

	/**
	 * Returns the type approval number of the card of a CardIccIdentification object.
	 * 
	 * @return	the type approval number of the card of the CardIccIdentification object
	 */
	public String getCardApprovalNumber() {
		return cardApprovalNumber.getCardApprovalNumber();
	}

	/**
	 * Sets the type approval number of the card of a CardIccIdentification object
	 * of a CardIccIdentification object.
	 * 
	 * @param	cardApprovalNumber	the type approval number of the card of a CardIccIdentification object
	 * 								to be set for the CardIccIdentification object
	 */
	public void setCardApprovalNumber( String cardApprovalNumber ) {
		this.cardApprovalNumber.setCardApprovalNumber( cardApprovalNumber );
	}

	/**
	 * Returns the card personaliser ID as defined in EN 726-3 of a CardIccIdentification object.
	 * 
	 * @return	the card personaliser ID as defined in EN 726-3 of the CardIccIdentification object
	 */
	public byte getCardPersonaliserID() {
		return cardPersonaliserID;
	}

	/**
	 * Sets the card personaliser ID as defined in EN 726-3 of a CardIccIdentification object
	 * of a CardIccIdentification object.
	 * 
	 * @param	cardPersonaliserID	the card personaliser ID as defined in EN 726-3 of a CardIccIdentification object
	 * 								to be set for the CardIccIdentification object
	 */
	public void setCardPersonaliserID( byte cardPersonaliserID ) {
		this.cardPersonaliserID = cardPersonaliserID;
	}

	/**
	 * Returns the embedder/IC assembler identifier as defined in EN 726-3
	 * of a CardIccIdentification object.
	 * 
	 * @return	the embedder/IC assembler identifier as defined in EN 726-3
	 * 			of the CardIccIdentification object
	 */
	public byte[] getEmbedderIcAssemblerId() {
		return embedderIcAssemblerId;
	}

	/**
	 * Sets the embedder/IC assembler identifier as defined in EN 726-3
	 * of a CardIccIdentification object.
	 * 
	 * @param	embedderIcAssemblerId	the embedder/IC assembler identifier as defined in EN 726-3
	 * 									to be set for the CardIccIdentification object
	 */
	public void setEmbedderIcAssemblerId( byte[] embedderIcAssemblerId ) {
		this.embedderIcAssemblerId = arrayCopy( embedderIcAssemblerId, 0, 5 );
	}

	/**
	 * Returns the identifier of the IC on the card and its IC manufacturer as defined
	 * in EN 726-3 of a CardIccIdentification object.
	 * 
	 * @return	the identifier of the IC on the card and its IC manufacturer as defined
	 * 			in EN 726-3 of the CardIccIdentification object
	 */
	public byte[] getIcIdentifier() {
		return icIdentifier;
	}

	/**
	 * Sets the identifier of the IC on the card and its IC manufacturer as defined
	 * of a CardIccIdentification object.
	 * 
	 * @param	icIdentifier	the identifier of the IC on the card and its IC manufacturer as defined
	 * 							to be set for the CardIccIdentification object
	 */
	public void setIcIdentifier( byte[] icIdentifier ) {
		this.icIdentifier = arrayCopy( icIdentifier, 0, 2 );
	}

	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		node.addContent( new Element( "clockStop" ).setText( convertIntoHexString( clockStop ) ) );
		node.addContent( cardExtendedSerialNumber.generateXMLElement( "cardExtendedSerialNumber" ) );
		node.addContent( cardApprovalNumber.generateXMLElement( "cardApprovalNumber" ) );
		node.addContent( new Element( "cardPersonaliserID" ).setText( convertIntoHexString( cardPersonaliserID ) ) );
		node.addContent( new Element( "embedderIcAssemblerId" ).setText( convertIntoHexString( embedderIcAssemblerId ) ) );
		node.addContent( new Element( "icIdentifier" ).setText( convertIntoHexString( icIdentifier ) ) );

		return node;
	}
}
