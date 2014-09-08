package com.cos;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.cos.util.RequestHandler;



import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PatientProfilePage extends Activity {

	private static final String NAMESPACE = "http://service.vital";
	private static final String URL = 
			"http://146.145.42.84:8080/VitalSignWSRS/vitalsign/viewprofilew3wse1z`";	
	private static final String SOAP_ACTION = "patientProfile";
	private static final String METHOD_NAME = "viewProfile";

	String w="Welcome";
	TextView welcome,fname,lname,dobT,genderTV,martialstatusTV,idTV,dctrnameTV,healthStatusTV;
	static String authenticationToken;
	static String companyId;
	private String firstName;
	private String lastName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.patientprofile);

		welcome=(TextView)findViewById(R.id.welcomeText);
		fname=(TextView)findViewById(R.id.fnametxt);
		lname=(TextView)findViewById(R.id.lnameTxt);
		dobT=(TextView)findViewById(R.id.dobTxt);
		genderTV=(TextView)findViewById(R.id.gender);
		martialstatusTV=(TextView)findViewById(R.id.martialstatusTV);
		idTV=(TextView)findViewById(R.id.idTV);
		dctrnameTV=(TextView)findViewById(R.id.dctrnameTV);
		healthStatusTV=(TextView)findViewById(R.id.healthSTV);



		Bundle bundl=this.getIntent().getExtras();
		firstName=bundl.getString("firstName");
		lastName=bundl.getString("lastName");
		//  authenticationToken=bundl.getString("authenticationToken");
		//   companyId=bundl.getString("companyId");

		welcome.setText(w+" "+firstName.toUpperCase());

		new GetProfile().execute();

		Button home=(Button)findViewById(R.id.homePProfile);
		home.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(PatientProfilePage.this,PatientHome.class);
				i.putExtra("firstName", firstName);
				i.putExtra("lastName", lastName);
				//	i.putExtra("authenticationToken", authenticationToken);
				//	i.putExtra("companyId", companyId);
				startActivity(i);
			}
		});

	}

	class GetProfile extends AsyncTask<String,String,String>
	{
		String dob,gender,martialstatus,partyId,healthStatus,createdBy;
		@Override
		protected String doInBackground(String... params) {

			String str1;
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("uname", firstName ));
			nameValuePairs.add(new BasicNameValuePair("pwd", lastName));

			str1 = RequestHandler.getStringfromURL(Constants.PROFILE_URL, nameValuePairs);
			System.out.println("@@@@@@@@@@@@@@@@@@@@"+str1);
			if(!str1.equalsIgnoreCase("No Data")){
				String patientDetails[]=str1.split("@");

				dob=patientDetails[2];
				gender=patientDetails[3];
				if(gender.equalsIgnoreCase("M"))
					gender="Male";
				else if(gender.equalsIgnoreCase("F"))
					gender="Female";
				martialstatus=patientDetails[4];
				if(martialstatus.equalsIgnoreCase("m"))
					martialstatus="Married";
				else if(martialstatus.equalsIgnoreCase("S"))
					martialstatus="Single";

				partyId=patientDetails[5];
				healthStatus=patientDetails[7];
				createdBy=patientDetails[6];
			}
			return null;
		}
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			fname.setText(firstName);
			lname.setText(lastName);
			dobT.setText(dob);
			genderTV.setText(gender);
			martialstatusTV.setText(martialstatus);
			idTV.setText(partyId);
			dctrnameTV.setText(createdBy);
			healthStatusTV.setText(healthStatus);
		}

	}

}
