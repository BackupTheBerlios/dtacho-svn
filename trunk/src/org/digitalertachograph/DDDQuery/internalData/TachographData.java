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
import java.io.FileOutputStream;
import java.util.Vector;
import java.util.Arrays;
import java.util.Iterator;
import org.jdom.*;
import org.jdom.output.XMLOutputter;


public class TachographData extends DataClass {
	private EF_ICC ef_icc;
	private EF_IC ef_ic;
	private EF_Application_Identification ef_application_identification;
	private EF_Card_Certificate ef_card_certificate;
	private EF_CA_Cerfiticate ef_ca_certificate;
	private EF_Identification ef_identification;
	private EF_Card_Download ef_card_download;
	private EF_Driving_Licence_Info ef_driving_licence_info;
	private EF_Events_Data ef_events_data;
	private EF_Faults_Data ef_faults_data;
	private EF_Driver_Activity_Data ef_driver_activity;
	private EF_Vehicles_Used ef_vehicles_used;
	private EF_Places ef_places;
	private EF_Current_Usage ef_current_usage;
	private EF_Control_Activity_Data ef_control_activity_data;
	private EF_Specific_Conditions ef_specific_conditions;
	private EF_Calibration ef_calibration;
	private EF_Sensor_Installation_Data ef_sensor_installation_data;
	private EF_Controller_Activity_Data ef_controller_activity_data;
	
	
	public Vector data = new Vector(); // deprecated
	private int cardType;
	private Vector<DataClass> dispatcherQueue = new Vector();
		
	public void add(byte[] tag, byte[] length, byte[] value){
		Object[] tmp = {tag, value};
		data.add(tmp);
		
		if( Arrays.equals(tag, new byte[]{0x00,0x02,0x00}) ){
			ef_icc = new EF_ICC(value);
			dispatcherQueue.add(ef_icc);
		}
		else if(Arrays.equals(tag, new byte[]{ 0x00, 0x05, 0x00})){
			ef_ic = new EF_IC(value);
			dispatcherQueue.add(ef_ic);
		}
		else if(Arrays.equals(tag, new byte[]{ 0x05, 0x01, 0x00 })){
			ef_application_identification = new EF_Application_Identification(value);
			cardType = ef_application_identification.getCardType();
			dispatcherQueue.add(ef_application_identification);
		}
		else if(Arrays.equals(tag, new byte[]{ (byte)0xC1, 0x00, 0x00} )){
			ef_card_certificate = new EF_Card_Certificate(value);
			dispatcherQueue.add(ef_card_certificate);
		}
		else if(Arrays.equals(tag, new byte[]{ (byte)0xC1, 0x08, 0x00} )){
			ef_ca_certificate = new EF_CA_Cerfiticate(value);
			dispatcherQueue.add(ef_ca_certificate);
		}
		else if(Arrays.equals(tag, new byte[]{ (byte)0x05, 0x20, 0x00} )){
			ef_identification = new EF_Identification(value, cardType);
			dispatcherQueue.add(ef_identification);
		}
		else if(Arrays.equals(tag, new byte[]{ (byte)0x05, 0x0E, 0x00} )){
			ef_card_download = new EF_Card_Download(value, cardType);
			dispatcherQueue.add(ef_card_download);
		}
		else if(Arrays.equals(tag, new byte[]{ (byte)0x05, 0x21, 0x00} )){
			ef_driving_licence_info = new EF_Driving_Licence_Info(value);
			dispatcherQueue.add(ef_driving_licence_info);
		}
		else if(Arrays.equals(tag, new byte[]{ (byte)0x05, 0x02, 0x00} )){
			ef_events_data = new EF_Events_Data(value);
			dispatcherQueue.add(ef_events_data);
		}
		else if(Arrays.equals(tag, new byte[]{ (byte)0x05, 0x03, 0x00} )){
			ef_faults_data = new EF_Faults_Data(value);
			dispatcherQueue.add(ef_faults_data);
		}
		else if(Arrays.equals(tag, new byte[]{ (byte)0x05, 0x04, 0x00} )){
			ef_driver_activity = new EF_Driver_Activity_Data(value);
			dispatcherQueue.add(ef_driver_activity);
		}
		else if(Arrays.equals(tag, new byte[]{ (byte)0x05, 0x05, 0x00} )){
			ef_vehicles_used = new EF_Vehicles_Used(value);
			dispatcherQueue.add(ef_vehicles_used);
		}
		else if(Arrays.equals(tag, new byte[]{ (byte)0x05, 0x06, 0x00} )){
			ef_places = new EF_Places(value);
			dispatcherQueue.add(ef_places);
		}
		else if(Arrays.equals(tag, new byte[]{ (byte)0x05, 0x07, 0x00} )){
			ef_current_usage = new EF_Current_Usage(value);
			dispatcherQueue.add(ef_current_usage);
		}
		else if(Arrays.equals(tag, new byte[]{ (byte)0x05, 0x08, 0x00} )){
			ef_control_activity_data = new EF_Control_Activity_Data(value);
			dispatcherQueue.add(ef_control_activity_data);
		}
		else if(Arrays.equals(tag, new byte[]{ (byte)0x05, 0x22, 0x00} )){
			ef_specific_conditions = new EF_Specific_Conditions(value);
			dispatcherQueue.add(ef_specific_conditions);
		}
		else if(Arrays.equals(tag, new byte[]{ (byte)0x05, 0x0a, 0x00} )){
			ef_calibration = new EF_Calibration(value);
			dispatcherQueue.add(ef_calibration);
		}
		else if(Arrays.equals(tag, new byte[]{ (byte)0x05, 0x0b, 0x00} )){
			ef_sensor_installation_data = new EF_Sensor_Installation_Data(value);
			dispatcherQueue.add(ef_sensor_installation_data);
		}
		else if(Arrays.equals(tag, new byte[]{ (byte)0x05, 0x0c, 0x00} )){
			ef_controller_activity_data = new EF_Controller_Activity_Data(value);
			dispatcherQueue.add(ef_control_activity_data);
		}else{
			if(tag[2] != 0x01){
				System.out.print("\nPANIC PANIC PANIC:" );
				System.out.print( " " + Integer.toHexString( (int)(tag[0] & 0xFF)  ));
				System.out.print( " " + Integer.toHexString( (int)(tag[1] & 0xFF)  ));
				System.out.print( " " + Integer.toHexString( (int)(tag[2] & 0xFF)  ));
			}
		}
	}
	
	
	public void setCardType(int cardType) {
		this.cardType = cardType;
	}


	public void printT(){
		Iterator it = data.iterator();
		while(it.hasNext()){
			Object [] tmp = (Object[]) it.next();
			System.out.print("\ntag: ");
			for(int i = 0; i < ((byte[]) tmp[0]).length; i++)
				System.out.print(" " +  Integer.toHexString( (int)((byte[]) tmp[0])[i] & 0xFF ));
		}
	}
	
	public void printTL(){
		Iterator it = data.iterator();
		while(it.hasNext()){
			Object [] tmp = (Object[]) it.next();
			System.out.print("\ntag: ");
			for(int i = 0; i < ((byte[]) tmp[0]).length; i++)
				System.out.print(" " +  Integer.toHexString( (int)((byte[]) tmp[0])[i] & 0xFF ));	
			System.out.print("\t - length: " + ( (byte[]) tmp[1]).length );
		}
	}
	
	public void printTV(){
		Iterator it = data.iterator();
		while(it.hasNext()){
			Object [] tmp = (Object[]) it.next();

			// TAG
			System.out.print("\n\ntag : ");
			for(int i = 0; i < ((byte[]) tmp[0]).length; i++)
				System.out.print(" " +  Integer.toHexString( (int)((byte[]) tmp[0])[i] & 0xFF ));		
			// VALUE
	
			System.out.print("\nvalue :\n");
			for(int i = 0; i < ((byte[]) tmp[1]).length; i++){
				// (value[i]<0?256 + value[i]:value[i])
				System.out.print(" " +  Integer.toHexString((int) ((byte[]) tmp[1])[i] & 0xFF) );
			}
			System.out.println();
			for(int i = 0; i < ((byte[]) tmp[1]).length; i++){
				char c = (char) ( (int)((byte[]) tmp[1])[i] & 0xFF );
				System.out.print( " " +  c);
			}
			System.out.println();
		}
	}
	
	public Element generateXMLElement(String name){
		Element root = new Element(name);
		Iterator dispaterIterator = dispatcherQueue.iterator();
		while(dispaterIterator.hasNext()){
			DataClass dc = (DataClass) dispaterIterator.next();
			Element node = dc.generateXMLElement(dc.getClass().getSimpleName());
			root.addContent(node);
		}
		
		return root;
	}

	public String generateXML(){
		Document doc = new Document();
		Element root = generateXMLElement(this.getClass().getSimpleName());
		doc.setRootElement(root);
		
		XMLOutputter serializer = new XMLOutputter();
		return serializer.outputString(doc);
	}
	
	public void generateXML(String file){
		Document doc = new Document();
		Element root = generateXMLElement(this.getClass().getSimpleName());
		doc.setRootElement(root);

		try{ 
			FileOutputStream fos = new FileOutputStream(file);
			XMLOutputter serializer = new XMLOutputter();
			serializer.output(doc, fos);
			fos.flush();
			fos.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}


};