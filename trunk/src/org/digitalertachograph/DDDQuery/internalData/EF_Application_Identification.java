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

package org.digitalertachograph.DDDQuery.internalData;

import org.digitalertachograph.DDDQuery.internalData.DataTypes.*;
import org.jdom.Element;

public class EF_Application_Identification extends DataClass {
	public static int size;
	private DriverCardApplicationIdentification driverCardApplicationIdentification;
	private WorkshopCardApplicationIdentification workshopCardApplicationIdentification;
	private ControlCardApplicationIdentification controlCardApplicationIdentification;
	private CompanyCardApplicationIdentification companyCardApplicationIdentification;
	private int cardType;
	
	
	public EF_Application_Identification(byte[] value) {
		size = value.length;
		switch (cardType = value[0]) {
			case EquipmentType.DRIVER_CARD :
				driverCardApplicationIdentification = new DriverCardApplicationIdentification(value);
				break;
			case EquipmentType.WORKSHOP_CARD :
				workshopCardApplicationIdentification = new WorkshopCardApplicationIdentification(value);
				break;
			case EquipmentType.CONTROL_CARD :
				controlCardApplicationIdentification = new ControlCardApplicationIdentification(value);
				break;
			case EquipmentType.COMPANY_CARD :
				companyCardApplicationIdentification = new CompanyCardApplicationIdentification(value);
				break;
				
			default :
				break;
		}
	}


	public int getCardType() {
		return cardType;
	}
	
	public Element generateXMLElement(String name){
		// discard name - this.getClass().getSimpleName() is unique!
		Element node = new Element(this.getClass().getSimpleName());
		Element child;
		String child_name;
		switch (cardType) {
		case EquipmentType.DRIVER_CARD :
			child_name = driverCardApplicationIdentification.getClass().getSimpleName();
			child = driverCardApplicationIdentification.generateXMLElement(child_name);
			break;
		case EquipmentType.WORKSHOP_CARD :
			child_name = workshopCardApplicationIdentification.getClass().getSimpleName(); 
			child = workshopCardApplicationIdentification.generateXMLElement(child_name);
			break;
		case EquipmentType.CONTROL_CARD :
			child_name = controlCardApplicationIdentification.getClass().getSimpleName();
			child = controlCardApplicationIdentification.generateXMLElement(child_name);
			break;
		case EquipmentType.COMPANY_CARD :
			child_name = companyCardApplicationIdentification.getClass().getSimpleName();
			child = companyCardApplicationIdentification.generateXMLElement(child_name);
			break;
			
		default :
			child = new Element("default");
			break;
		}
		
		node.addContent(child);
		return node;
		}
	}
