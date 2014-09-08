package com.cos;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.mail.Header;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class LoginPage_temp extends Activity {
    private String[] array_userType=new String[]{"Physician","Patient"};
    private String firstName;
    private String lastName;
    private static final String NAMESPACE = "http://service.vital";
	private static final String URL = 
		"http://10.0.2.2:8080/VitalSignWS/services/VitalSignWS?wdl";	
	private static final String SOAP_ACTION = "checkPatient";
	private static final String METHOD_NAME = "userLogin";
	//private static final String METHOD_NAME1="checkPatientLogin";
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        try{
        final EditText userId=(EditText)findViewById(R.id.userid);
        
        final EditText password=(EditText)findViewById(R.id.password);
        
        final Spinner userType=(Spinner)findViewById(R.id.spinner1);
        ArrayAdapter adapter1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,array_userType);
		//locations.setPrompt("Choose Location");
		userType.setAdapter(adapter1);
	   Button signIn=(Button)findViewById(R.id.signbtn);
	   signIn.setOnClickListener(new OnClickListener(){

		private HttpURLConnection urlConnection;

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(userId.getText().toString().equals("")||password.getText().toString().equals(""))
			{
				displayAlert("Please enter username and password");
			}
			else
			{
		
			 if(userType.getSelectedItem().toString().equals("Patient"))
			{
				
				
				String location="https://training.therasystem.net/uiservices/TSProxy";			
				firstName=userId.getText().toString();
				lastName=password.getText().toString();
				String retVal="";
				try{
					 SoapObject request=new SoapObject(NAMESPACE, METHOD_NAME);
				     request.addProperty("uname", firstName);
				     request.addProperty("pwd", lastName);
				    
				     SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
				     envelope.setOutputSoapObject(request);
				     HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

				     try {
				      
				      androidHttpTransport.call(SOAP_ACTION, envelope);
				      SoapObject resultsRequestSOAP = (SoapObject) envelope.bodyIn;
				      String str1=resultsRequestSOAP.toString();
				      //displayAlert(str1);
				       String[] arr=str1.split("=");
				        String[] arr1=arr[1].split(";");
				        // String[] arr2=arr1[0].split("@");
				         if(arr1[0].equals("login succesfull"))
				         {
//	
				    	   String loginRequest = "html=false&removeNamespace=true&cos_xml= " +
			          "<CosmoContainer xmlns='http://cos.ndorange.com/definitions' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'>" +
			          "<CosmoRequest xsi:type='ent:LogonRequest' xmlns:ent='http://cos.ndorange.com/entitlement'>" +
			                "<ent:logonInformation userId='jmartin' password='jmartin' /></CosmoRequest>" +
			    "</CosmoContainer>";
//--------------------------------------------------------------------------------------------------------
//			GetConnected to NdOrange (cOS)
//-----------------------------------------------------------------------------------------------------------					
					URL url = new URL(location);
		              urlConnection = (HttpURLConnection) url
		                          .openConnection();
		              urlConnection.setDoInput(true);
		              urlConnection.setDoOutput(true);
		              urlConnection.setRequestMethod("POST");
		              urlConnection.setAllowUserInteraction(true);
		             
		              urlConnection.connect();
		              BufferedReader br = null;
		              BufferedWriter bw = null;
		              OutputStreamWriter os = null;
		              InputStreamReader in = null;
		             
		              // receive the response from cos in the following data type.
		              StringBuilder out = new StringBuilder();
		             
		              
		                    os = new OutputStreamWriter(urlConnection.getOutputStream());
		                    bw = new BufferedWriter(os); // Writing to the servlet

		                    // Writing the contents of the login request to the cos servlet
		              bw.write(loginRequest);
		              bw.flush();
		       
		       
		              in = new InputStreamReader(urlConnection.getInputStream());
		              br = new BufferedReader(in);
		       
		              int responseCode = urlConnection.getResponseCode();
		             
		              if (responseCode == HttpURLConnection.HTTP_OK) { // Response is o.
		                    // then reading
		                    // the
		                    // CosmoResponse

		                    String readLine = null;
		                   
		                    while ((readLine = br.readLine()) != null) {
		                    System.out.println(readLine);
		                          out.append(readLine);
		                          }
		                    System.out.println(out.toString());
		                                     
		              }


		 //Step 9:create an instance of DOM parser         
		DocumentBuilder db= DocumentBuilderFactory.newInstance().newDocumentBuilder();
		     
		// Step 10 :parse the cos response xml using dom
		InputSource is= new InputSource();
		is.setCharacterStream(new StringReader(out.toString()));
		Document doc = db.parse(is);

		       
		//Step 11: get the cosmo response node

		 NodeList nodes = doc.getElementsByTagName("CosmoResponse");

		    // iterate the node to get the further elements
		    for (int s = 0; s < nodes.getLength(); s++) 
		    {
		    //get child nodes of cosmo response
		    Node fstNode = (Node) nodes.item(s);

		    //check whether the child node is an element
		   if (fstNode.getNodeType() == Node.ELEMENT_NODE)
		    {

		       Element fstElmnt = (Element) fstNode;
							 //check if the cosmo response have an attribute “error message”
								if(fstElmnt.hasAttribute("errorMessage"))
			          					 {
							        	  String error = fstElmnt.getAttribute("errorMessage");
							        	  System.out.println("dadasd"+error);
			           					}else
			           					{
		                   //get the authentication token , companyId             
			        	        String authenticationToken= fstElmnt.getAttribute("authenticationToken");
			        	   				String companyId= fstElmnt.getAttribute("companyId");
			        	   				System.out.println("dadasd"+authenticationToken+"@@@@@@"+companyId);
			        	   				Intent i=new Intent(arg0.getContext(), PatientHome.class);
			        	   				i.putExtra("authenticationToken", authenticationToken);
			        	   				i.putExtra("companyId", companyId);
			        	   				i.putExtra("firstName", userId.getText().toString());
			        	   				i.putExtra("lastName", password.getText().toString());
			        	   				startActivity(i);
			        	   			  displayAlert(authenticationToken+""+companyId);
			        	   			
			           					}

		    }//if
		    }
		              //for
				 
				   
				  
				      }
				      else
				      {
				    	  displayAlert("Wrong username and password");
				      }
				     }
				     catch (Exception e) {
						// TODO: handle exception
				    	 e.printStackTrace();
					}
				}catch(Exception e){
                       e.printStackTrace();  
				}
				
				
			}
			}
			
		}  
	   });
        }
        catch (Exception e) {
			e.printStackTrace();
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