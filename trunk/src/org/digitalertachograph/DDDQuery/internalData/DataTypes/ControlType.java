/*
    $Id:ControlType.java 26 2008-02-25 22:28:27Z deetee $

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
 * Code indicating the activities carried out during a control.
 */
public class ControlType extends DataClass {
	/*
	 * ControlType ::= OCTET STRING (SIZE(1)), 1 byte
	 * 
	 * Value assignment  Octet aligned: 'cvpdxxxx'B (8 bits)
	 * 'c'B card downloading:
	 * 		'0'B: card not downloaded during this control activity,
	 * 		'1'B: card downloaded during this control activity
	 * 'v'B VU downloading:
	 * 		'0'B: VU not downloaded during this control activity,
	 * 		'1'B: VU downloaded during this control activity
	 * 'p'B printing:
	 * 		'0'B: no printing done during this control activity,
	 * 		'1'B: printing done during this control activity
	 * 'd'B display:
	 * 		'0'B: no display used during this control activity,
	 * 		'1'B: display used during this control activity
	 * 'xxxx'B Not used.
	 * 
	 * 10000000 = 0x80
	 * 01000000 = 0x40
	 * 00100000 = 0x20
	 * 00010000 = 0x10
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 1;

	private byte value;

	/*
	 * Control type: card downloading mask
	 */
	private static final byte CARD_DOWNLOADING_MASK = (byte)0x80;
	private boolean card_downloading = false;

	/*
	 * Control type: VU downloading mask
	 */
	private static final byte VU_DOWNLOADING_MASK = (byte)0x40;
	private boolean vu_downloading = false;

	/*
	 * Control type: printing mask
	 */
	private static final byte PRINTING_MASK = (byte)0x20;
	private boolean printing = false;

	/*
	 * Control type: card display mask
	 */
	private static final byte DISPLAY_MASK = (byte)0x10;
	private boolean display = false;


	/**
	 * Constructor for a ControlType object
	 */
	public ControlType() {
		value = 0;
	}

	/**
	 * Constructor for a ControlType object
	 * 
	 * @param	value	byte array of a ControlType structure
	 * 					whose data is used when the ControlType
	 * 					object is created.
	 */
	public ControlType( byte[] value ) {
		this.setControlType( value[ 0 ] );
	}

	/**
	 * Constructor for a ControlType object
	 * 
	 * @param	controlType		byte that contains the code indicating the activities carried out during
	 * 							a control.
	 */
	public ControlType( byte controlType ) {
		this.setControlType( controlType );
	}

	/**
	 * Returns the code indicating the activities carried out during a control
	 * of a ControlType object.
	 * 
	 * @return	the code indicating the activities carried out during a control
	 * 			of the ControlType object
	 */
	public byte getControlType() {
		return value;
	}

	/**
	 * Sets the code indicating the activities carried out during a control
	 * of a ControlType object.
	 * 
	 * @param	value			the code indicating the activities carried out during a control
	 * 							to be set for the ControlType object
	 */
	public void setControlType( byte value ) {
		this.value = value;

		card_downloading = ( ( value & CARD_DOWNLOADING_MASK ) == CARD_DOWNLOADING_MASK );
		vu_downloading = ( ( value & VU_DOWNLOADING_MASK ) == VU_DOWNLOADING_MASK );
		printing = ( ( value & PRINTING_MASK ) == PRINTING_MASK );
		display = ( ( value & DISPLAY_MASK ) == DISPLAY_MASK );
	}

	/**
	 * Returns the card downloading status of a ControlType object.
	 * 
	 * @return	the card downloading status of the ControlType object
	 */
	public boolean isCardDownloading() {
		return card_downloading;
	}

	/**
	 * Sets the card downloading status of a ControlType object.
	 * 
	 * @param	value		the card downloading status
	 * 							to be set for the ControlType object
	 */
	public void setCardDownloading( boolean value ) {
		if ( value == true ) {
			this.value |= CARD_DOWNLOADING_MASK;
		}
		else {
			this.value &= ~CARD_DOWNLOADING_MASK;
		}

		card_downloading = ( ( this.value & CARD_DOWNLOADING_MASK ) == CARD_DOWNLOADING_MASK );
	}

	/**
	 * Returns the VU downloading status of a ControlType object.
	 * 
	 * @return	the VU downloading status of the ControlType object
	 */
	public boolean isVuDownloading() {
		return vu_downloading;
	}

	/**
	 * Sets the VU downloading status of a ControlType object.
	 * 
	 * @param	value		the VU downloading status
	 * 							to be set for the ControlType object
	 */
	public void setVuDownloading( boolean value ) {
		if ( value == true ) {
			this.value |= VU_DOWNLOADING_MASK;
		}
		else {
			this.value &= ~VU_DOWNLOADING_MASK;
		}

		vu_downloading = ( ( this.value & VU_DOWNLOADING_MASK ) == VU_DOWNLOADING_MASK );
	}
	
	/**
	 * Returns the printing status of a ControlType object.
	 * 
	 * @return	the printing status of the ControlType object
	 */
	public boolean isPrinting() {
		return printing;
	}

	/**
	 * Sets the printing status of a ControlType object.
	 * 
	 * @param	value		the printing status
	 * 						to be set for the ControlType object
	 */
	public void setPrinting( boolean value ) {
		if ( value == true ) {
			this.value |= PRINTING_MASK;
		}
		else {
			this.value &= ~PRINTING_MASK;
		}

		printing = ( ( this.value & PRINTING_MASK ) == PRINTING_MASK );
	}
	
	/**
	 * Returns the display status of a ControlType object.
	 * 
	 * @return	the display status of the ControlType object
	 */
	public boolean isDisplay() {
		return display;
	}

	/**
	 * Sets the display status of a ControlType object.
	 * 
	 * @param	value		the display status
	 * 						to be set for the ControlType object
	 */
	public void setDisplay( boolean value ) {
		if ( value == true ) {
			this.value |= DISPLAY_MASK;
		}
		else {
			this.value &= ~DISPLAY_MASK;
		}

		display = ( ( this.value & DISPLAY_MASK ) == DISPLAY_MASK );
	}
	
	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );

		node.addContent( new Element( "value" ).setText( convertIntoHexString( value ) ) );
		node.addContent( new Element( "card_downloading" ).setText( Boolean.toString( card_downloading ) ) );
		node.addContent( new Element( "vu_downloading" ).setText( Boolean.toString( vu_downloading ) ) );
		node.addContent( new Element( "display" ).setText( Boolean.toString( display ) ) );
		node.addContent( new Element( "printing" ).setText( Boolean.toString( printing ) ) );

		return node;
	}
}
