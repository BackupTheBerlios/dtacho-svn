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
	private EF_CA_Certificate ef_ca_certificate;
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
	
	
	public Vector<Object> data = new Vector<Object>(); // deprecated
	private int cardType;
	private Vector<DataClass> dispatcherQueue = new Vector<DataClass>();

	
	private static final int EF_ENTITY = 0;
	private static final int EF_WITH_SIGNATURE = 1;
	
	private int efstate = EF_ENTITY;
	private byte[] tag2;

	
	public boolean isValidTag(byte[] tag){
		if ((Arrays.equals(new byte[]{tag[0],tag[1]}, new byte[]{      0x00,0x02})) |
			(Arrays.equals(new byte[]{tag[0],tag[1]}, new byte[]{      0x00,0x05})) |
			(Arrays.equals(new byte[]{tag[0],tag[1]}, new byte[]{(byte)0xc1,0x00})) |
			(Arrays.equals(new byte[]{tag[0],tag[1]}, new byte[]{(byte)0xc1,0x08})) |
			(Arrays.equals(new byte[]{tag[0],tag[1]}, new byte[]{      0x05,0x01})) |
			(Arrays.equals(new byte[]{tag[0],tag[1]}, new byte[]{      0x05,0x20})) |
			(Arrays.equals(new byte[]{tag[0],tag[1]}, new byte[]{      0x05,0x0e})) |
			(Arrays.equals(new byte[]{tag[0],tag[1]}, new byte[]{      0x05,0x21})) |
			(Arrays.equals(new byte[]{tag[0],tag[1]}, new byte[]{      0x05,0x02})) |
			(Arrays.equals(new byte[]{tag[0],tag[1]}, new byte[]{      0x05,0x03})) |
			(Arrays.equals(new byte[]{tag[0],tag[1]}, new byte[]{      0x05,0x04})) |
			(Arrays.equals(new byte[]{tag[0],tag[1]}, new byte[]{      0x05,0x05})) |
			(Arrays.equals(new byte[]{tag[0],tag[1]}, new byte[]{      0x05,0x06})) |
			(Arrays.equals(new byte[]{tag[0],tag[1]}, new byte[]{      0x05,0x07})) |
			(Arrays.equals(new byte[]{tag[0],tag[1]}, new byte[]{      0x05,0x08})) |
			(Arrays.equals(new byte[]{tag[0],tag[1]}, new byte[]{      0x05,0x22})) |
			(Arrays.equals(new byte[]{tag[0],tag[1]}, new byte[]{      0x05,0x0a})) |
			(Arrays.equals(new byte[]{tag[0],tag[1]}, new byte[]{      0x05,0x0b})) |
			(Arrays.equals(new byte[]{tag[0],tag[1]}, new byte[]{      0x05,0x0c})) )	{

			return true;
		}
		else {
			return false;

		}
	}


	public boolean add(byte[] tag, byte[] length, byte[] value){
		boolean parseresult = false;
		Object[] tmp = {tag, value};
		data.add(tmp);
		
		switch(efstate) {
			case EF_ENTITY:
				parseresult = true;

				if( Arrays.equals(tag, new byte[]{0x00,0x02,0x00}) ){
					System.out.println( this.getClass().getSimpleName() + ":\n [TAG] EF_ICC, 00 02 00");
					ef_icc = new EF_ICC(value);
					dispatcherQueue.add(ef_icc);
					tag2 = new byte[]{0x00,0x02};
					efstate = EF_ENTITY;
				}
				else if(Arrays.equals(tag, new byte[]{ 0x00, 0x05, 0x00})){
					System.out.println( this.getClass().getSimpleName() + ":\n [TAG] EF_IC, 00 05 00");
					ef_ic = new EF_IC(value);
					dispatcherQueue.add(ef_ic);
					tag2 = new byte[]{0x00,0x05};
					efstate = EF_ENTITY;
				}
				else if(Arrays.equals(tag, new byte[]{ (byte)0xc1, 0x00, 0x00} )){
					System.out.println( this.getClass().getSimpleName() + ":\n [TAG] EF_CARD_CERTIFICATE, c1 00 00");
					ef_card_certificate = new EF_Card_Certificate(value);
					dispatcherQueue.add(ef_card_certificate);
					tag2 = new byte[]{(byte)0xc1,0x00};
					efstate = EF_ENTITY;
				}
				else if(Arrays.equals(tag, new byte[]{ (byte)0xc1, 0x08, 0x00} )){
					System.out.println( this.getClass().getSimpleName() + ":\n [TAG] EF_CA_CERTIFICATE, c1 08 00");
					ef_ca_certificate = new EF_CA_Certificate(value);
					dispatcherQueue.add(ef_ca_certificate);
					tag2 = new byte[]{(byte)0xc1,0x08};
					efstate = EF_ENTITY;
				}
				else if(Arrays.equals(tag, new byte[]{ 0x05, 0x01, 0x00 })){
					System.out.println( this.getClass().getSimpleName() + ":\n [TAG] EF_APPLICATION_IDENTIFICATION, 05 01 00");
					ef_application_identification = new EF_Application_Identification(value);
					cardType = ef_application_identification.getCardType();
					dispatcherQueue.add(ef_application_identification);
					tag2 = new byte[]{0x05,0x01};
					efstate = EF_WITH_SIGNATURE;
				}
				else if(Arrays.equals(tag, new byte[]{ (byte)0x05, 0x20, 0x00} )){
					System.out.println( this.getClass().getSimpleName() + ":\n [TAG] EF_IDENTIFICATION, 05 20 00");
					ef_identification = new EF_Identification(value, cardType);
					dispatcherQueue.add(ef_identification);
					tag2 = new byte[]{0x05,0x20};
					efstate = EF_WITH_SIGNATURE;
				}
				else if(Arrays.equals(tag, new byte[]{ (byte)0x05, 0x0e, 0x00} )){
					System.out.println( this.getClass().getSimpleName() + ":\n [TAG] EF_CARD_DOWNLOAD, 05 0e 00");
					ef_card_download = new EF_Card_Download(value, cardType);
					dispatcherQueue.add(ef_card_download);
					tag2 = new byte[]{0x05,0x0e};
					efstate = EF_WITH_SIGNATURE;
				}
				else if(Arrays.equals(tag, new byte[]{ (byte)0x05, 0x21, 0x00} )){
					System.out.println( this.getClass().getSimpleName() + ":\n [TAG] EF_DRIVING_LICENCE_INFO, 05 21 00");
					ef_driving_licence_info = new EF_Driving_Licence_Info(value);
					dispatcherQueue.add(ef_driving_licence_info);
					tag2 = new byte[]{0x05,0x21};
					efstate = EF_WITH_SIGNATURE;
				}
				else if(Arrays.equals(tag, new byte[]{ (byte)0x05, 0x02, 0x00} )){
					System.out.println( this.getClass().getSimpleName() + ":\n [TAG] EF_EVENTS_DATA, 05 02 00");
					ef_events_data = new EF_Events_Data(value);
					dispatcherQueue.add(ef_events_data);
					tag2 = new byte[]{0x05,0x02};
					efstate = EF_WITH_SIGNATURE;
				}
				else if(Arrays.equals(tag, new byte[]{ (byte)0x05, 0x03, 0x00} )){
					System.out.println( this.getClass().getSimpleName() + ":\n [TAG] EF_FAULTS_DATA, 05 03 00");
					ef_faults_data = new EF_Faults_Data(value);
					dispatcherQueue.add(ef_faults_data);
					tag2 = new byte[]{0x05,0x03};
					efstate = EF_WITH_SIGNATURE;
				}
				else if(Arrays.equals(tag, new byte[]{ (byte)0x05, 0x04, 0x00} )){
					System.out.println( this.getClass().getSimpleName() + ":\n [TAG] EF_DRIVER_ACTIVITY_DATA, 05 04 00");
					ef_driver_activity = new EF_Driver_Activity_Data(value);
					dispatcherQueue.add(ef_driver_activity);
					tag2 = new byte[]{0x05,0x04};
					efstate = EF_WITH_SIGNATURE;
				}
				else if(Arrays.equals(tag, new byte[]{ (byte)0x05, 0x05, 0x00} )){
					System.out.println( this.getClass().getSimpleName() + ":\n [TAG] EF_VEHICLES_USED, 05 05 00");
					ef_vehicles_used = new EF_Vehicles_Used(value);
					dispatcherQueue.add(ef_vehicles_used);
					tag2 = new byte[]{0x05,0x05};
					efstate = EF_WITH_SIGNATURE;
				}
				else if(Arrays.equals(tag, new byte[]{ (byte)0x05, 0x06, 0x00} )){
					System.out.println( this.getClass().getSimpleName() + ":\n [TAG] EF_PLACES, 05 06 00");
					ef_places = new EF_Places(value);
					dispatcherQueue.add(ef_places);
					tag2 = new byte[]{0x05,0x06};
					efstate = EF_WITH_SIGNATURE;
				}
				else if(Arrays.equals(tag, new byte[]{ (byte)0x05, 0x07, 0x00} )){
					System.out.println( this.getClass().getSimpleName() + ":\n [TAG] EF_CURRENT_USAGE, 05 07 00");
					ef_current_usage = new EF_Current_Usage(value);
					dispatcherQueue.add(ef_current_usage);
					tag2 = new byte[]{0x05,0x07};
					efstate = EF_WITH_SIGNATURE;
				}
				else if(Arrays.equals(tag, new byte[]{ (byte)0x05, 0x08, 0x00} )){
					System.out.println( this.getClass().getSimpleName() + ":\n [TAG] EF_CONTROL_ACTIVITY_DATA, 05 08 00");
					ef_control_activity_data = new EF_Control_Activity_Data(value);
					dispatcherQueue.add(ef_control_activity_data);
					tag2 = new byte[]{0x05,0x08};
					efstate = EF_WITH_SIGNATURE;
				}
				else if(Arrays.equals(tag, new byte[]{ (byte)0x05, 0x22, 0x00} )){
					System.out.println( this.getClass().getSimpleName() + ":\n [TAG] EF_SPECIFIC_CONDITIONS, 05 22 00");
					ef_specific_conditions = new EF_Specific_Conditions(value);
					dispatcherQueue.add(ef_specific_conditions);
					tag2 = new byte[]{0x05,0x22};
					efstate = EF_WITH_SIGNATURE;
				}
				else if(Arrays.equals(tag, new byte[]{ (byte)0x05, 0x0a, 0x00} )){
					System.out.println( this.getClass().getSimpleName() + ":\n [TAG] EF_CALIBRATION, 05 0a 00");
					ef_calibration = new EF_Calibration(value);
					dispatcherQueue.add(ef_calibration);
					tag2 = new byte[]{0x05,0x0a};
					efstate = EF_WITH_SIGNATURE;
				}
				else if(Arrays.equals(tag, new byte[]{ (byte)0x05, 0x0b, 0x00} )){
					System.out.println( this.getClass().getSimpleName() + ":\n [TAG] EF_SENSOR_INSTALLATION, 05 0b 00");
					ef_sensor_installation_data = new EF_Sensor_Installation_Data(value);
					dispatcherQueue.add(ef_sensor_installation_data);
					tag2 = new byte[]{0x05,0x0b};
					efstate = EF_WITH_SIGNATURE;
				}
				else if(Arrays.equals(tag, new byte[]{ (byte)0x05, 0x0c, 0x00} )){
					System.out.println( this.getClass().getSimpleName() + ":\n [TAG] EF_CONTROLLER_ACTIVITY_DATA, 05 0c 00");
					ef_controller_activity_data = new EF_Controller_Activity_Data(value);
					dispatcherQueue.add(ef_controller_activity_data);
					tag2 = new byte[]{0x05,0x0c};
					efstate = EF_WITH_SIGNATURE;
				}
				else{
					System.out.print(" [PANIC] unknown tag, " );
					System.out.printf( " %02x", (int)(tag[0] & 0xff) );
					System.out.printf( " %02x", (int)(tag[1] & 0xff) );
					System.out.printf( " %02x\n", (int)(tag[2] & 0xff) );
					parseresult = false;
				}

				break;

			case EF_WITH_SIGNATURE:
				if(tag[2] != 0x01){
					// not a signature tag
					System.out.printf(" [PANIC] expected signature tag %02x %02x 01, got", tag2[0], tag2[1] );
					System.out.printf( " %02x", (int)(tag[0] & 0xff) );
					System.out.printf( " %02x", (int)(tag[1] & 0xff) );
					System.out.printf( " %02x\n", (int)(tag[2] & 0xff) );
					parseresult = false;
				}
				else {
					System.out.print(" [TAG] signature tag," );
					System.out.printf( " %02x", (int)(tag[0] & 0xff) );
					System.out.printf( " %02x", (int)(tag[1] & 0xff) );
					System.out.printf( " %02x\n", (int)(tag[2] & 0xff) );
					if(Arrays.equals(tag2, new byte[]{tag[0], tag[1]})) {
						System.out.println(" [INFO] signature belongs to previous tag - that's fine!");
						parseresult = true;
					}
					else
						parseresult = false;
				}

				efstate = EF_ENTITY;
				break;

			default:
				parseresult = false;
				break;
		}

		return parseresult;
	}
	
	
	public void setCardType(int cardType) {
		this.cardType = cardType;
	}


	public void printT(){
		Iterator<Object> it = data.iterator();
		while(it.hasNext()){
			Object [] tmp = (Object[]) it.next();
			System.out.print("tag: ");
			for(int i = 0; i < ((byte[]) tmp[0]).length; i++)
				System.out.printf(" %02x", (int)(((byte[]) tmp[0])[i] & 0xff));		
				System.out.println();
		}
	}
	
	public void printTL(){
		Iterator<Object> it = data.iterator();
		while(it.hasNext()){
			Object [] tmp = (Object[]) it.next();
			System.out.print("tag: ");
			for(int i = 0; i < ((byte[]) tmp[0]).length; i++)
				System.out.printf(" %02x", (int)(((byte[]) tmp[0])[i] & 0xff));		

			System.out.println("\t - length: " + ( (byte[]) tmp[1]).length );
		}
	}
	
	public void printTV() {
		printTV( 16 );
	}
		
	public void printTV( int bytesperline ) {
		hexdumpwidth = bytesperline;
		Iterator<Object> it = data.iterator();
		while(it.hasNext()){
			Object [] tmp = (Object[]) it.next();

			// TAG
			System.out.print("tag: ");

			for(int i = 0; i < ((byte[]) tmp[0]).length; i++)
				System.out.printf(" %02x", (int)(((byte[]) tmp[0])[i] & 0xff));		

			System.out.println("\t - length: " + ( (byte[]) tmp[1]).length );

			// VALUE
			System.out.println("value:");

			for(int i = 0; i < ((byte[]) tmp[1]).length; i++){
				hexdump( (byte)(((byte[]) tmp[1])[i] & 0xff) );
			}
			hexdump_flush();
			System.out.println();
		}
	}

	private static int hexdumpwidth = 16;
	private static int hexdumpcount = 0;
	private static byte[] hexdumpbyte = new byte[hexdumpwidth];

	private void hexdump( byte b ) {
		hexdumpbyte[ hexdumpcount ] = b;
		hexdumpcount += 1;
    
		if (hexdumpcount == hexdumpwidth) {
			hexdump_flush();
		}
	}
    
	private void hexdump_flush() {
		if (hexdumpcount > 0) {
			for(int i = 0; i < hexdumpcount; i++)
				System.out.printf(" %02x", hexdumpbyte[i]);		

			System.out.print("  ");		

			for(int i = 0; i < hexdumpwidth-hexdumpcount; i++)
				System.out.print("   ");		

			for(int i = 0; i < hexdumpcount; i++) {
				// print visible chars only
				if ( hexdumpbyte[i] >= 32)
					System.out.print((char)hexdumpbyte[i]);		
				else
					System.out.print(".");		
			}
			
			hexdumpcount = 0;
			System.out.println();
		}
	}

	
	public Element generateXMLElement(String name){
		Element root = new Element(name);
		Iterator<DataClass> dispaterIterator = dispatcherQueue.iterator();
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