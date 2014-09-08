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
import java.util.ArrayList;
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
import org.apache.http.message.BasicNameValuePair;
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
import com.cos.util.RequestHandler;
import com.cos.util.Utility;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class LoginPage extends Activity {
	private String[] array_userType=new String[]{"Physician","Patient"};
	private String firstName;
	private String lastName;
	private static final String NAMESPACE = "http://service.vital";
	private static final String URL = 
			"http://10.0.2.2:8080/VitalSignWS/services/VitalSignWS?wdl";	
	private static final String SOAP_ACTION = "checkPatient";
	private static final String METHOD_NAME = "userLogin";
	private EditText userId, password;
	private Spinner userType;
	private HttpURLConnection urlConnection;
	//private static final String METHOD_NAME1="checkPatientLogin";
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		try{
			userId=(EditText)findViewById(R.id.userid);

			password=(EditText)findViewById(R.id.password);

			userType=(Spinner)findViewById(R.id.spinner1);
			ArrayAdapter adapter1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,array_userType);
			//locations.setPrompt("Choose Location");
			userType.setAdapter(adapter1);
			Button signIn=(Button)findViewById(R.id.signbtn);
			signIn.setOnClickListener(new OnClickListener(){



				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					new CheckAuth().execute();

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

	class CheckAuth extends AsyncTask<String,String,String>
	{

		@Override
		protected String doInBackground(String... params) {

			if(userId.getText().toString().equals("")||password.getText().toString().equals(""))
			{
				displayAlert("Please enter username and password");
			}
			else
			{
				//	displayAlert("am in else");
				if(userType.getSelectedItem().toString().equals("Patient"))
				{


					String location="https://training.therasystem.net/uiservices/TSProxy";			
					firstName=userId.getText().toString();
					lastName=password.getText().toString();
					String retVal="";
					try{

						try {

							String str1;
							//displayAlert(str1);
							List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
							nameValuePairs.add(new BasicNameValuePair("uname",firstName ));
							nameValuePairs.add(new BasicNameValuePair("pwd", lastName));

							str1 = RequestHandler.getStringfromURL(Constants.LOGIN_URL, nameValuePairs);
							System.out.println("@@@@@@@@@@@@@@@@@@@@"+str1);
							//  String[] arr=str1.split("=");
							//    String[] arr1=arr[1].split(";");
							// String[] arr2=arr1[0].split("@");
							if(str1.trim().equals("login succesfull"))
							{
								
								Intent i=new Intent(LoginPage.this, PatientHome.class);
								i.putExtra("firstName", userId.getText().toString());
								i.putExtra("lastName", password.getText().toString());
								startActivity(i);
							}
							else
							{
								LoginPage.this.runOnUiThread(new Runnable()
								{
									public void run()
									{
										//Do your UI operations like dialog opening or Toast here
										displayAlert("Wrong username and password");
									}
								});

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

			return null;
		}

	}
}