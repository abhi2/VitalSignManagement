package com.cos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.cos.model.LoginManager;
import com.cos.util.RequestHandler;


import android.app.Activity;
import android.app.AlertDialog;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;

import android.os.AsyncTask;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddVitalSign extends Activity
{
	private static final String NAMESPACE = "http://service.vital";
	private static final String URL = 
			"http://10.0.2.2:8080/VitalSignWS/services/VitalSignWS?wdl";	
	private static final String SOAP_ACTION = "addvitalsPatient";
	private static final String METHOD_NAME = "addVitals";

	private String[] vitalTypes={"BP-D","BP-S","Glucose",
			"waist","hip","height","Pain Scale",
			"Peak Resp Flow Rate","PostTrmntRespFlowRt",
			"pulse","Pulse Oximetry","Resp","Temp","weight"};
	private ArrayList<String> aList;
	static String encounterID;
	static String partyId;
	String xmlResponse;
	private HashMap<String, String> patientDetails;
	private String firstName;
	private String lastName;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addvitalsign);
		final EditText bpDiastolic=(EditText)findViewById(R.id.edittext1);
		final EditText bpSistolic=(EditText)findViewById(R.id.edittext2);
		final EditText glucose=(EditText)findViewById(R.id.edittext3);
		final EditText waistCircumferance=(EditText)findViewById(R.id.edittext4);
		final EditText hipCircumferance=(EditText)findViewById(R.id.edittext5);
		final EditText height=(EditText)findViewById(R.id.edittext6);
		final EditText painScale=(EditText)findViewById(R.id.edittext7);
		final EditText peakRespitoryFlowRate=(EditText)findViewById(R.id.edittext8);
		final EditText postTreatmentRespitoryFlowRate=(EditText)findViewById(R.id.edittext9);
		final EditText pulse=(EditText)findViewById(R.id.edittext10);
		final EditText pulseOximetry=(EditText)findViewById(R.id.edittext11);
		final EditText respiration=(EditText)findViewById(R.id.edittext12);
		final EditText temparature=(EditText)findViewById(R.id.edittext13);
		final EditText weight=(EditText)findViewById(R.id.edittext14);


		Bundle bundl=this.getIntent().getExtras();
		firstName=bundl.getString("firstName");
		lastName=bundl.getString("lastName");

		new GetPartyId().execute();

		Button addVitals=(Button)findViewById(R.id.button1);

		addVitals.setOnClickListener(new View.OnClickListener(){


			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				aList=new ArrayList<String>();
				if((bpDiastolic.getText().toString()).equals("")&&(bpSistolic.getText().toString()).equals("")&&
						(glucose.getText().toString()).equals("")&&(waistCircumferance.getText().toString()).equals("")&&
						(hipCircumferance.getText().toString()).equals("")&&(height.getText().toString()).equals("")&&
						(painScale.getText().toString()).equals("")&&(peakRespitoryFlowRate.getText().toString()).equals("")&&
						(postTreatmentRespitoryFlowRate.getText().toString()).equals("")&&(pulse.getText().toString()).equals("")&&
						(pulseOximetry.getText().toString()).equals("")&&(respiration.getText().toString()).equals("")&&
						(temparature.getText().toString()).equals("")&&(weight.getText().toString()).equals(""))
				{
				//	displayAlert("Please enter fields");
				}
				else
				{
					if(!(bpDiastolic.getText().toString()).equals(""))
					{
						String v1=vitalTypes[0]+","+bpDiastolic.getText().toString()+","+"mmHg";
						aList.add(v1);
					}
					if(!(bpSistolic.getText().toString()).equals(""))
					{
						String v1=vitalTypes[1]+","+bpSistolic.getText().toString()+","+"mmHg";
						aList.add(v1);
					}
					if(!(glucose.getText().toString()).equals(""))
					{
						String v1=vitalTypes[2]+","+glucose.getText().toString()+","+"mmol/l";
						aList.add(v1);
					}
					if(!(waistCircumferance.getText().toString()).equals(""))
					{
						String v1=vitalTypes[3]+","+waistCircumferance.getText().toString()+","+"cm";
						aList.add(v1);
					}
					if(!(hipCircumferance.getText().toString()).equals(""))
					{
						String v1=vitalTypes[4]+","+hipCircumferance.getText().toString()+","+"cm";
						aList.add(v1);
					}
					if(!(height.getText().toString()).equals(""))
					{
						String v1=vitalTypes[5]+","+height.getText().toString()+","+"cm";
						aList.add(v1);
					}
					if(!(painScale.getText().toString()).equals(""))
					{
						String v1=vitalTypes[6]+","+painScale.getText().toString()+","+" ";
						aList.add(v1);
					}
					if(!(peakRespitoryFlowRate.getText().toString()).equals(""))
					{
						String v1=vitalTypes[7]+","+peakRespitoryFlowRate.getText().toString()+","+"L/S";
						aList.add(v1);
					}
					if(!(postTreatmentRespitoryFlowRate.getText().toString()).equals(""))
					{
						String v1=vitalTypes[8]+","+postTreatmentRespitoryFlowRate.getText().toString()+","+"L/S";
						aList.add(v1);
					}
					if(!(pulse.getText().toString()).equals(""))
					{
						String v1=vitalTypes[9]+","+pulse.getText().toString()+","+"BPM";
						aList.add(v1);
					}
					if(!(pulseOximetry.getText().toString()).equals(""))
					{
						String v1=vitalTypes[10]+","+pulseOximetry.getText().toString()+","+"%";
						aList.add(v1);
					}
					if(!(respiration.getText().toString()).equals(""))
					{
						String v1=vitalTypes[11]+","+respiration.getText().toString()+","+"rpm";
						aList.add(v1);
					}
					if(!(temparature.getText().toString()).equals(""))
					{
						String v1=vitalTypes[12]+","+temparature.getText().toString()+","+"F";
						aList.add(v1);
					}
					if(!(weight.getText().toString()).equals(""))
					{
						String v1=vitalTypes[13]+","+weight.getText().toString()+","+"kg";
						aList.add(v1);
					}

					try {

						new AddVital().execute();

					} catch (Exception e) {
						e.printStackTrace();
					}
				}


			}});
	}
	
	class AddVital extends AsyncTask<String,String,String>
	{
		@Override
		protected String doInBackground(String... params) {
			if(null != aList){
				
			for(int i=0;i<aList.size();i++)
			{
				String addvitalsigns=aList.get(i).toString();
				String []addvitals=addvitalsigns.split(",");
				try{
					final String str1;
					List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
					nameValuePairs.add(new BasicNameValuePair("uname",firstName ));
					nameValuePairs.add(new BasicNameValuePair("vitaltype", addvitals[0]));
					nameValuePairs.add(new BasicNameValuePair("vitalvalue", addvitals[1]));
					nameValuePairs.add(new BasicNameValuePair("unitofmeasure", addvitals[2]));
					nameValuePairs.add(new BasicNameValuePair("partyid", partyId));
					
					str1 = RequestHandler.getStringfromURL(Constants.ADD_VITAL, nameValuePairs);
					
					System.out.println(str1);
					if(i==0)//this is for avoiding repeated alerts.
					{
					AddVitalSign.this.runOnUiThread(new Runnable() {
						  public void run() {
						    displayAlert(str1);
						  }
						});
					}

				}
				catch (Exception e)
				{
					// TODO: handle exception
					e.printStackTrace();
				}

			}
			}
			return null;
		}
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
		}
	}
			
			class GetPartyId extends AsyncTask<String,String,String>
			{
				
				@Override
				protected String doInBackground(String... params) {
					String str1;
					List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
					nameValuePairs.add(new BasicNameValuePair("uname",firstName ));
					nameValuePairs.add(new BasicNameValuePair("pwd", lastName));

					str1 = RequestHandler.getStringfromURL(Constants.GET_PARTYID_URL, nameValuePairs);
					System.out.println("@@@@@@@@@@@@@@@@@@@@"+str1);
					String arr[] = str1.split("@");
					 partyId = arr[0];
					return null;
				}
				protected void onPostExecute(String result) {
//displayAlert(partyId);

				}
			}
			public void displayAlert(String displayMessage)
			{
				AlertDialog.Builder builder=new AlertDialog.Builder(this);
				builder.setTitle("Alert");
				builder.setMessage(displayMessage);
				builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {

					}
				});
				builder.show();
			}
		}
	
