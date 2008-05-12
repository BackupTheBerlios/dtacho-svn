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

package org.digitalertachograph.DDDQuery;

import java.net.URL;
import java.util.Vector;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.WebServer;


public class Controller {
	private static Controller self;
	private boolean anonymized = false;
	private static DebugLogger debugLogger;
	
	public boolean isAnonymized() {
		return anonymized;
	}

	public static void main ( String[] args ) {
		debugLogger = new DebugLogger();

		DebugLogger.InitEnv();

		Controller c = Controller.getInstance();
		c.setupWebserver();
		//c.manual();
		if ( args.length > 0) {
			for ( int i = 0; i < args.length; i++ ) {
				debugLogger.resetPrintDelayed();
				c.process( args[ i ] );
				debugLogger.println( DebugLogger.LOGLEVEL_INFO, "[INFO] end of file processing" );
			}

			System.exit( 0 );
		}
	}

	public static Controller getInstance(){
		if ( self == null ) {
			self = new Controller();
		}

		return self;
	}

	private Controller() {

	}

	public static void sendXML( String xml ) {
		try {
			XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
			config.setServerURL( new URL( "http://127.0.0.1:5002/RPC2" ) );
			XmlRpcClient client = new XmlRpcClient();
			client.setConfig( config );

			Vector<String> params = new Vector<String>();
			params.addElement( xml );
			// this method returns a string
			//String result = (String) client.execute ("storeXML", params);
			client.execute( "storeXML", params );

			//System.out.println(result);
		}
		catch ( Exception e ) {
			e.printStackTrace();
		}
	}

	public void manual() {
		String pwd = "org_daten";

		String org_file1 = pwd + "/VDO/" + "vdo.DDD";
		String org_file2 = pwd + "/OPTAC/" + "optac.DDD";

		process( org_file1 );
		process( org_file2 );
	}

	public void process( String file ) {
		debugLogger.println( DebugLogger.LOGLEVEL_ERROR, "\n" + file + "\n", true );

		DDDDataSource ds = new DDDDataSource();
		ds.setSourceFile( file );

		if ( ds.processSourceFile() == true ) {
			XMLDumper xmld = ds.getXMLDumper();
			debugLogger.println( DebugLogger.LOGLEVEL_INFO, "[INFO] dumping XML..." );

			XMLInfo.setSourceFilename( file );

			xmld.setXMLInfo( new XMLInfo() );
			xmld.generateXML( file + ".xml" );	
		}
		else {
			debugLogger.println( DebugLogger.LOGLEVEL_ERROR, "[ERROR] there was an error while parsing data" );
			debugLogger.println( DebugLogger.LOGLEVEL_ERROR, "[ERROR] NOT dumping XML!" );
		}
	}

	/**
	 * 
	 * @param data			byte array of DDD data
	 * @param srcType		the type of DDD data ({@link DDDDataSource#SRC_TYPE_CARD}, {@link DDDDataSource#SRC_TYPE_VU})
	 * @return				the parsed DDD data as XML stream
	 */
	public String process( byte[] data, short srcType ) {
		DDDDataSource ds = new DDDDataSource();

		for ( int i = 0; i < data.length; i++ ) {
			debugLogger.print( DebugLogger.LOGLEVEL_INFO_EXTENDED, (char)data[ i ] );
		}

		ds.setSrcType( srcType );
		ds.setSource( data );

		if ( ds.processSource() == true ) {
			XMLDumper xmld = ds.getXMLDumper();

			xmld.setXMLInfo( new XMLInfo() );
			String xml = xmld.generateXML();
			// System.out.println( "pre sendXML" );
			//sendXML( xml );
			//	System.out.println( "post sendXML" );
			return xml;
		}

		return null;
	}

	public void setupWebserver() {
		try {
			WebServer webserver = new WebServer( 5001 );
			XmlRpcServer xmlRpcServer = webserver.getXmlRpcServer();

			PropertyHandlerMapping phm = new PropertyHandlerMapping();

			phm.addHandler( "RpcController", RpcController.class );
			xmlRpcServer.setHandlerMapping( phm );

			XmlRpcServerConfigImpl serverConfig = (XmlRpcServerConfigImpl)xmlRpcServer.getConfig();
			serverConfig.setEnabledForExtensions( false );
			serverConfig.setContentLengthOptional( false );

			webserver.start();
		}
		catch ( Exception e ) {
			e.printStackTrace();
		}
	}
}
