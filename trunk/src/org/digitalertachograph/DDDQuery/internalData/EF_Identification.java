/*   Copyright (C) 2007, Martin Barth

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

package org.digitalertachograph.DDDQuery.internalData;

import org.digitalertachograph.DDDQuery.internalData.DataTypes.*;
import org.jdom.Element;

public class EF_Identification extends DataClass {
	public static final int size = 143;
	private int cardType;
	private CardIdentification cardIdentification;
	private DriverCardHolderIdentification driverCardHolderIdentification;
	private WorkshopCardHolderIdentification workshopCardHolderIdentification;
	private ControlCardApplicationIdentification controlCardApplicationIdentification;
	private CompanyCardHolderIdentification companyCardHolderIdentification;

	public EF_Identification(byte[] value, int cardType){
		// 65 bytes
		this.cardType = cardType;
		cardIdentification = new CardIdentification( arrayCopy(value,0, 65), cardType );
	
		if( EquipmentType.COMPANY_CARD == cardType){
			companyCardHolderIdentification = new CompanyCardHolderIdentification( arrayCopy(value, 65, value.length - 65));
		}else if( EquipmentType.CONTROL_CARD == cardType){
			controlCardApplicationIdentification = new ControlCardApplicationIdentification( arrayCopy(value, 65, value.length - 65));
		}else if( EquipmentType.DRIVER_CARD == cardType){
			driverCardHolderIdentification = new DriverCardHolderIdentification( arrayCopy(value, 65, 78));
		}else if( EquipmentType.WORKSHOP_CARD == cardType){
			workshopCardHolderIdentification = new WorkshopCardHolderIdentification( arrayCopy(value, 65, value.length - 65));
		}else
			System.out.println("PANIC PANIC PANIC: EF_IDENTIFICATION");
		
	}
	
	public Element generateXMLElement(String name){
		// discard name - this.getClass().getSimpleName() is unique!
		Element node = new Element(this.getClass().getSimpleName());
		Element child;
		node.addContent( cardIdentification.generateXMLElement("cardIdentification"));
		switch (cardType) {
		case EquipmentType.COMPANY_CARD:
			child = companyCardHolderIdentification.generateXMLElement("companyCardHolderIdentification");
			break;
			
		case EquipmentType.CONTROL_CARD:
			child = controlCardApplicationIdentification.generateXMLElement("controlCardApplicationIdentification");
			break;
			
		case EquipmentType.DRIVER_CARD:
			child = driverCardHolderIdentification.generateXMLElement("driverCardHolderIdentification");
			break;
			
		case EquipmentType.WORKSHOP_CARD:
			child = workshopCardHolderIdentification.generateXMLElement("workshopCardHolderIdentification");
			break;

		default:
			child = new Element("error");
			break;
		}
		node.addContent(child);
		return node;
	}
	
}
