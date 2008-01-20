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

	private byte controlType;
	
	private boolean card_downloading = false;
	private static final byte CARD_DOWNLOADING_MASK = (byte)0x80;
	private boolean vu_downloading = false;
	private static final byte VU_DOWNLOADING_MASK = (byte)0x40;
	private boolean printing = false;
	private static final byte PRINTING_MASK = (byte)0x20;
	private boolean display = false;
	private static final byte DISPLAY_MASK = (byte)0x10;
		

	/**
	 * Constructor for a ControlType object
	 */
	public ControlType() {
		controlType = 0;
	}

	/**
	 * Constructor for a ControlType object
	 * 
	 * @param	value	byte that contains the code indicating the activities carried out during
	 * 					a control.
	 */
	public ControlType( byte value ) {
		this.setControlType( value );
	}

	/**
	 * Returns the code indicating the activities carried out during a control
	 * of a ControlType object.
	 * 
	 * @return	the code indicating the activities carried out during a control
	 * 			of the ControlType object
	 */
	public byte getControlType() {
		return controlType;
	}

	/**
	 * Sets the code indicating the activities carried out during a control
	 * of a ControlType object.
	 * 
	 * @param	controlType		the code indicating the activities carried out during a control
	 * 							to be set for the ControlType object
	 */
	public void setControlType( byte controlType ) {
		this.controlType = controlType;
		
		card_downloading = ( ( controlType & CARD_DOWNLOADING_MASK ) == CARD_DOWNLOADING_MASK );
		vu_downloading = ( ( controlType & VU_DOWNLOADING_MASK ) == VU_DOWNLOADING_MASK );
		printing = ( ( controlType & PRINTING_MASK ) == PRINTING_MASK );
		display = ( ( controlType & DISPLAY_MASK ) == DISPLAY_MASK );
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
			this.controlType |= CARD_DOWNLOADING_MASK;
		}
		else {
			this.controlType &= ~CARD_DOWNLOADING_MASK;
		}
		
		card_downloading = ( ( controlType & CARD_DOWNLOADING_MASK ) == CARD_DOWNLOADING_MASK );
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
			this.controlType |= VU_DOWNLOADING_MASK;
		}
		else {
			this.controlType &= ~VU_DOWNLOADING_MASK;
		}

		vu_downloading = ( ( controlType & VU_DOWNLOADING_MASK ) == VU_DOWNLOADING_MASK );
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
			this.controlType |= PRINTING_MASK;
		}
		else {
			this.controlType &= ~PRINTING_MASK;
		}

		printing = ( ( controlType & PRINTING_MASK ) == PRINTING_MASK );
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
			this.controlType |= DISPLAY_MASK;
		}
		else {
			this.controlType &= ~DISPLAY_MASK;
		}

		display = ( ( controlType & DISPLAY_MASK ) == DISPLAY_MASK );
	}
	
	@Override
	public Element generateXMLElement( String name ) {
		Element node = new Element( name );
		node.setAttribute( "card_downloading", Boolean.toString( card_downloading ) );
		node.setAttribute( "vu_downloading", Boolean.toString( vu_downloading ) );
		node.setAttribute( "display", Boolean.toString( display ) );
		node.setAttribute( "printing", Boolean.toString( printing ) );

		return node;
	}
}
