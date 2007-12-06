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

package org.digitalertachograph.DDDQuery;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.Vector;

//ssl support
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.WebServer;
import org.digitalertachograph.DDDQuery.internalData.TachographData;


public class Controller {
	private static Controller self;
	private boolean anonymized = false;

	public boolean isAnonymized() {
		return anonymized;
	}

	public static void main(String[] argv){
		Controller c = Controller.getInstance();
		c.setupWebserver();
		//c.manual();
		c.process(argv[0]);
	}

	public static Controller getInstance(){
		if(self == null)
			self = new Controller();

		return self;	
	}

	private Controller() {

	}

	public static void sendXML(String xml) {
		try {
			XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
			config.setServerURL(new URL("http://127.0.0.1:5001/RPC2"));
			XmlRpcClient client = new XmlRpcClient();
			client.setConfig(config);


			Vector params = new Vector ();
			params.addElement(xml);
			// this method returns a string
			String result = (String) client.execute ("storeXML", params);

			//System.out.println(result);
		}catch(Exception e){
			e.printStackTrace();
		}
	}



	public void manual(){
		String pwd = "org_daten";	

		String org_file1 = pwd + "/VDO/" + "vdo.DDD";
		String org_file2 = pwd + "/OPTAC/" + "optac.DDD";

		process(org_file1);
		process(org_file2);

	}

	public void process(String file){
		System.out.println("\n\n" + file);	
		DDDDataSource ds = new DDDDataSource();
		ds.setSourceFile(file);
		TachographData td1 = ds.getTachographData();
		td1.generateXML(file + ".xml");	
	}

	public String process(byte[] data){
		DDDDataSource ds = new DDDDataSource();
		for(int i = 0; i< data.length; i++)
			System.out.print((char) data[i]);



		ds.setSource(data);
		TachographData td = ds.getTachographData();
		String xml = td.generateXML();
		//System.out.println("pre sendXML");
		sendXML(xml);
		//System.out.println("post sendXML");
		return xml;

		//return xml;
	}

	public void setupWebserver(){
		try{
			WebServer webserver = new WebServer(5001);
			XmlRpcServer xmlRpcServer = webserver.getXmlRpcServer();

			PropertyHandlerMapping phm = new PropertyHandlerMapping();

			phm.addHandler("RpcController", RpcController.class);
			xmlRpcServer.setHandlerMapping(phm);

			XmlRpcServerConfigImpl serverConfig = (XmlRpcServerConfigImpl) xmlRpcServer.getConfig();
			serverConfig.setEnabledForExtensions(false);
			serverConfig.setContentLengthOptional(false);

			webserver.start();
		}catch(Exception e){
			e.printStackTrace();
		}
	}


}
