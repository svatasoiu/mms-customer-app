package com.example.test_wcf_service;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.os.AsyncTask;
import android.widget.TextView;

public class RetrieveNameTask extends AsyncTask<Void, Void, String> {
	
	private static final String METHOD_NAME = "GetAllUsers";
	//  private static final String METHOD_NAME = "HelloWorld";
	
	private static final String NAMESPACE = "http://tempuri.org/";
	//  private static final String NAMESPACE = "http://tempuri.org";
	
	private static final String URL = "http://172.16.151.35:4040/Service1.svc";
	//  private static final String URL = "http://192.168.0.2:8080/webservice1  /Service1.asmx";
	
	final String SOAP_ACTION = "http://tempuri.org/TestService/GetAllUsers";
	//  final String SOAP_ACTION = "http://tempuri.org/HelloWorld";
	StringBuilder sb;
	TextView _tv;
	
	public RetrieveNameTask(TextView tv) {
		_tv = tv;
	}
	
	@Override
	protected String doInBackground(Void... params) {
		sb = new StringBuilder();
		try {
			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

			//					request.addProperty("Name", "Qing");

			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.dotNet = true;
			envelope.setOutputSoapObject(request);

			HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
			androidHttpTransport.call(SOAP_ACTION, envelope);
			SoapObject result = (SoapObject) envelope.getResponse();

			//to get the data
			String resultData = result.toString();
			// 0 is the first object of data 

			sb.append(resultData + "\n");
		} catch (Exception e) {
			sb.append(e.getMessage());
		}
		return sb.toString();
	}
	
	@Override
	protected void onPostExecute(String result) {
	    _tv.setText(result);
	}
}
