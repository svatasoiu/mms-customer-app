package com.example.test_wcf_service;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import android.os.AsyncTask;
import android.widget.TextView;

public class GetUserData extends AsyncTask<String, Void, Table> {
	
	final String METHOD_NAME = "GetUserData";
	// private static final String METHOD_NAME = "HelloWorld";

	final String NAMESPACE = "http://tempuri.org/";
	// private static final String NAMESPACE = "http://tempuri.org";

	final String URL = MainActivity.TESTING 
			? "http://172.16.151.35:4040/Service1.svc" 
			: "http://dev.mms.org:9000/CustomerData/Service1.svc";
	// private static final String URL =
	// "http://192.168.0.2:8080/webservice1  /Service1.asmx";

	final String SOAP_ACTION = "http://tempuri.org/TestService/" + METHOD_NAME;
	//  final String SOAP_ACTION = "http://tempuri.org/HelloWorld";
	StringBuilder sb;
	TextView _te1;
	TextView _te2;
	TextView _te3;
	 
	public GetUserData(TextView te1, TextView te2, TextView te3) {
		_te1 = te1;
		_te2 = te2;
		_te3 = te3;
	}

	@Override
	protected Table doInBackground(String... params) {
		SoapObject table = null; // dataset that returned through SoapObject
		SoapObject client = null; // client to the web service
		SoapObject tableRow = null; // Contains row of table
		SoapPrimitive responseBody = null; // Contains XML content of dataset
		HttpTransportSE transport = null; // call webservice
		SoapSerializationEnvelope sse = null;

		
		sse = new SoapSerializationEnvelope(SoapEnvelope.VER11);
//		sse.addMapping(NAMESPACE, "Table", this.getClass());

		sse.dotNet = true; // if WebService written .Net is result=true
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

		Table settable = new Table();
		try {
			client = new SoapObject(NAMESPACE, METHOD_NAME);
			
			client.addProperty("ID", params[0]);
//			client.addProperty("password", "pass");
			
			sse.setOutputSoapObject(client);
			sse.bodyOut = client;
			
			androidHttpTransport.call(SOAP_ACTION, sse);

			// get file XML
			responseBody = (SoapPrimitive) sse.getResponse();
			System.out.println(responseBody);
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new InputSource(new StringReader(responseBody.toString())));
			doc.getDocumentElement().normalize();
			Element el = (Element) doc.getElementsByTagName("Table").item(0);
			
			settable.CustomerID = "CTM_NBR: " + getValue("CTM_NBR", el); 
			settable.Product = "PUB_CDE: " + getValue("PUB_CDE", el);
			settable.Expiration = "EXP_DTE: " + getValue("EXP_DTE", el);
			return settable;

		} catch (Exception e) {
			settable.CustomerID = e.toString();
			settable.Product = e.toString();
			settable.Expiration = e.toString();
			return settable;
		}
	}
	
	@Override
	protected void onPostExecute(Table result) {
		_te1.setText(result.CustomerID);
		_te2.setText(result.Product);
		_te3.setText(result.Expiration);
	}

	private static String getValue(String tag, Element element) {
		NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nodes.item(0);
		return node.getNodeValue();
	}
}
