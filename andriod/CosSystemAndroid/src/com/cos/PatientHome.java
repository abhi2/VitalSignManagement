package com.cos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

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

import com.cos.model.AddVitalSigns;
import com.cos.model.LoginManager;
import com.cos.util.Utility;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PatientHome extends Activity {
//	 private static final String NAMESPACE = "http://patient.com";
//		private static final String URL = 
//			"http://10.11.32.29:8080/VitalSignServerApplication/services/PatientDetails?wsdl";	
//		private static final String SOAP_ACTION = "checkPatient";
//		private static final String METHOD_NAME = "checkPatient";
	private String authenticationToken;
	private String companyId;
	private String firstName;
	private String lastName;
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.patienthome);
	        Bundle bundl=this.getIntent().getExtras();
	     //   authenticationToken=bundl.getString("authenticationToken");
	    //    companyId=bundl.getString("companyId");
	        firstName=bundl.getString("firstName");
	        lastName=bundl.getString("lastName");
	        Button addVitalSigns=(Button)findViewById(R.id.button3);
	        Button updateVitalSigns=(Button)findViewById(R.id.button2);
	        Button myProfile=(Button)findViewById(R.id.myProfileB);
	        Button logout=(Button)findViewById(R.id.button4);
	        logout.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i=new Intent(v.getContext(), LoginPage.class);
					startActivity(i);
				}
	        	
	        });
	        myProfile.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					try{
						Intent i=new Intent(v.getContext(),PatientProfilePage.class);
						i.putExtra("firstName", firstName);
						i.putExtra("lastName", lastName);
					//	i.putExtra("authenticationToken", authenticationToken);
					//	i.putExtra("companyId", companyId);
						startActivity(i);
						}catch (Exception e) {
							// TODO: handle exception
						}
				}
			});
	        
	        addVitalSigns.setOnClickListener(new OnClickListener(){
	        	
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					
					
					
						try{
						Intent i=new Intent(arg0.getContext(), AddVitalSign.class);
						i.putExtra("firstName", firstName);
						i.putExtra("lastName", lastName);
					//	i.putExtra("authenticationToken", authenticationToken);
					//	i.putExtra("companyId", companyId);
						startActivity(i);
						}catch (Exception e) {
							// TODO: handle exception
						}
				
				}
				
				
	        });
	        updateVitalSigns.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent i=new Intent(arg0.getContext(), UpdateVitalSigns.class);
				//	i.putExtra("authenticationToken", authenticationToken);
				//	i.putExtra("companyId", companyId);
					i.putExtra("firstName", firstName);
					startActivity(i);
				}
	        	
	        });
	        
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
