/*
    $Id:ManufacturerCode.java 26 2008-02-25 22:28:27Z deetee $

    Copyright (C) 2008, Gerald Schnabel

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
 * Code identifying a manufacturer.
 */
public class ManufacturerCode extends DataClass {
	/*
	 * ManufacturerCode ::= INTEGER(0..255), 1 byte
	 */

	/**
	 * Size of structure in bytes.
	 */
	public final static int size = 1;

	private short manufacturerCode;


	/**
	 * Constructor for a ManufacturerCode object
	 */
	public ManufacturerCode() {
		manufacturerCode = 0;
	}

	/**
	 * Constructor for a ManufacturerCode object
	 * 
	 * @param	manufacturerCode	short value that identifies a manufacturer.
	 */
	public ManufacturerCode( short manufacturerCode ) {
		this.manufacturerCode = manufacturerCode;
	}

	/**
	 * Constructor for a ManufacturerCode object
	 * 
	 * @param	b	byte that identifies a manufacturer.
	 */
	public ManufacturerCode( byte b ) {
		manufacturerCode = convertIntoUnsigned1ByteInt( b );
	}

	/**
	 * Returns the code identifying a manufacturer of a ManufacturerCode object.
	 * 
	 * @return	the code identifying a manufacturer of the ManufacturerCode object
	 */
	public short getManufacturerCode() {
		return manufacturerCode;
	}

	/**
	 * Sets the code identifying a manufacturer of a ManufacturerCode object.
	 * 
	 * @param	manufacturerCode	code identifying a manufacturer to be set
	 * 								for the ManufacturerCode object
	 */
	public void setManufacturerCode( short manufacturerCode ) {
		this.manufacturerCode = manufacturerCode;
	}

	/**
	 * Returns the name of the manufacturer of a ManufacturerCode object.
	 * 
	 * @return	the name of the manufacturer of the ManufacturerCode object
	 * 
	 * Manufacturer codes list as of 21/02/2008
	 * http://dtc.jrc.it/text/cm.html
	 */
	public String toString() {
		if ( manufacturerCode > 0xdf ) {
			return "???";
		}

		if ( manufacturerCode == 0x00 ) {
			return "No information available";
		}
		else if ( manufacturerCode == 0x01 ) {
			return "Reserved value";
		}
		else if ( manufacturerCode == 0x10 ) {
			return "Actia S.A.";
		}
		else if ( manufacturerCode == 0x11 ) {
			return "Autoguard & Insurance";
		}
		else if ( manufacturerCode == 0x12 ) {
			return "Austria Card Plastikkarten und Ausweissysteme GmbH";
		}
		else if ( manufacturerCode == 0x20 ) {
			return "CETIS d.d.";
		}
		else if ( manufacturerCode == 0x21 ) {
			return "certSIGN";
		}
		else if ( manufacturerCode == 0x30 ) {
			return "Sdu Identification B.V. (formerly Enschedé/Sdu B.V.)";
		}
		else if ( manufacturerCode == 0x31 ) {
			return "Electricfil Industries";
		}
		else if ( manufacturerCode == 0x32 ) {
			return "EFKON AG.";
		}
		else if ( manufacturerCode == 0x38 ) {
			return "Fábrica Nacional de Moneda y Timbre";
		}
		else if ( manufacturerCode == 0x40 ) {
			return "Giesecke & Devrient GmbH";
		}
		else if ( manufacturerCode == 0x41 ) {
			return "GEM plus";
		}
		else if ( manufacturerCode == 0x42 ) {
			return "Grundig Car InterMedia System GmbH";
		}
		else if ( manufacturerCode == 0x43 ) {
			return "Giesecke & Devrient GB Ltd.";
		}
		else if ( manufacturerCode == 0x48 ) {
			return "Hungarian Banknote Printing Co. Ltd.";
		}
		else if ( manufacturerCode == 0x50 ) {
			return "Imprimerie Nationale";
		}
		else if ( manufacturerCode == 0x51 ) {
			return "Imprensa Nacional-Casa da Moeda, SA";
		}
		else if ( manufacturerCode == 0x52 ) {
			return "InfoCamere S.C.p.A.";
		}
		else if ( manufacturerCode == 0x80 ) {
			return "OSCard";
		}
		else if ( manufacturerCode == 0x81 ) {
			return "Sagem Orga (formerly ORGA Kartensysteme GmbH)";
		}
		else if ( manufacturerCode == 0x82 ) {
			return "Österreichische Staatsdruckerei GmbH";
		}
		else if ( manufacturerCode == 0x88 ) {
			return "PVT a.s.";
		}
		else if ( manufacturerCode == 0xa0 ) {
			return "Setec";
		}
		else if ( manufacturerCode == 0xa1 ) {
			return "Siemens";
		}
		else if ( manufacturerCode == 0xa2 ) {
			return "Stoneridge";
		}
		else if ( manufacturerCode == 0xa3 ) {
			return "Axalto (formerly SchlumbergerSEMA)";
		}
		else if ( manufacturerCode == 0xa4 ) {
			return "Security Printing and Systems Ltd.";
		}
		else if ( manufacturerCode == 0xa5 ) {
			return "ST Incard S.r.l.";
		}
		else if ( manufacturerCode == 0xaa ) {
			return "Tachocontrol";
		}
		else if ( manufacturerCode == 0xab ) {
			return "T-Systems International GmbH";
		}
		else if ( manufacturerCode == 0xac ) {
			return "Trüb AG";
		}
		else if ( manufacturerCode == 0xad ) {
			return "Trüb Baltic AS";
		}
		else if ( manufacturerCode == 0xae ) {
			return "TEMPEST a.s.";
		}
		else if ( manufacturerCode == 0xaf ) {
			return "Trueb - DEMAX PLC";
		}
		else {
			return "???";
		}
	}

	@Override
	public Element generateXMLElement( String name ) {
		return new Element( name ).setText( Short.toString( manufacturerCode ) );
	}
}
