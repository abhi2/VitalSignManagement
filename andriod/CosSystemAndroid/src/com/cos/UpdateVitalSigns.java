package com.cos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class UpdateVitalSigns extends Activity {
	private String authenticationToken;
	private String companyId;
	protected String xmlResponse;
	String noOfElements;
	private static final String NAMESPACE = "http://service.vital";
	private static final String URL = 
			"http://10.0.2.2:8080/VitalSignWS/services/VitalSignWS?wdl";	
	private static final String SOAP_ACTION = "checkPatient";
	private static final String METHOD_NAME = "getVitals";
	private static final String METHOD_NAME1 = "updateVitals";
	public String uname="";
	private ArrayList<VitalDto> vitalsList;
	TableLayout updateVitals;
	int getVitalsCounter=0;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.updatevitalsigns);
		Bundle bundl=this.getIntent().getExtras();
		//    authenticationToken=bundl.getString("authenticationToken");
		//    companyId=bundl.getString("companyId");
		final String firstName=bundl.getString("firstName");
		final  String partyId=AddVitalSign.partyId;
		uname=firstName;
		final String encounterId="";
		Button getVitals=(Button)findViewById(R.id.button1);

		updateVitals=(TableLayout)findViewById(R.id.tableLayout1);
		try{
			getVitals.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(final View v) {
					// TODO Auto-generated method stub
					if(getVitalsCounter==0)
					{
					new GetVitals().execute();
					}
					getVitalsCounter++;
				}});
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	class GetVitals extends AsyncTask<String,String,String>
	{
		String dob,gender,martialstatus,partyId,healthStatus,createdBy;
		@Override
		protected String doInBackground(String... params) {

			String str1;
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("uname", uname ));

			str1 = RequestHandler.getStringfromURL(Constants.GET_VITALS, nameValuePairs);
			try {

				//JSONObject jobject = new JSONObject(str1);
				JSONArray jarray = new JSONArray(str1);
				vitalsList = getVitalsList(jarray);
			} catch (JSONException e) {
				e.printStackTrace();		
			}

			System.out.println("@@@@@@@@@@@@@@@@@@@@"+str1);


			return null;
		}
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			int size2 = vitalsList.size();
			for(int i=0;i<size2;i++)
			{
				VitalDto dto = vitalsList.get(i);	

				TableRow row=new TableRow(UpdateVitalSigns.this);
				final TextView t2=new TextView(UpdateVitalSigns.this);
				final TextView t3=new TextView(UpdateVitalSigns.this);
				final TextView t4=new TextView(UpdateVitalSigns.this);
				final TextView t5=new TextView(UpdateVitalSigns.this);

				noOfElements+=dto.getVitalType()+dto.getVitalValue()+dto.getUnitOfMeasure()+dto.getEnteredTime()+",";
				t2.setEnabled(true);
				t3.setEnabled(true);
				t4.setEnabled(true);
				t5.setEnabled(true);
				t2.setText(""+dto.getVitalType());
				t3.setText(""+dto.getVitalValue());
				t4.setText(""+dto.getUnitOfMeasure());
				t5.setText(""+dto.getEnteredTime());

				t2.setTextColor(getResources().getColor(R.color.black));
				t3.setTextColor(getResources().getColor(R.color.black));
				t4.setTextColor(getResources().getColor(R.color.black));
				t5.setTextColor(getResources().getColor(R.color.black));
				// row.addView(t1);
				row.addView(t2);
				row.addView(t3);
				row.addView(t4);
				row.addView(t5);
				updateVitals.addView(row);

				t3.setOnLongClickListener(new OnLongClickListener(){

					@Override
					public boolean onLongClick(View arg0) {
						// TODO Auto-generated method stub
						final View arg4=arg0;

						final AlertDialog.Builder builder = new AlertDialog.Builder(arg4.getContext());
						builder.setMessage("Enter the new Value");
						final EditText newValue=new EditText(arg4.getContext());
						builder.setView(newValue);
						builder.setCancelable(true);

						builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {



							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								if(newValue.getText().toString().equals(""))
								{
									displayAlert("Please enter the value");

								}
								else
								{
									String vitalType=(t2.getText().toString()).trim();
									String vitalValue=newValue.getText().toString();
									String measure=(t4.getText().toString()).trim();
									//String time="2011-10-24T03:12:04";
									Date d=new Date();
									GregorianCalendar gcalendar = new GregorianCalendar();
									//String time1=gcalendar.get(Calendar.HOUR) +":"+gcalendar.get(Calendar.MINUTE) + ":"+gcalendar.get(Calendar.SECOND);
									SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
									String s1=format.format(d);
									SimpleDateFormat timeformat=new SimpleDateFormat("hh:mm:ss");
									String time1=timeformat.format(d);
									String time=s1+"T"+time1+".000-00:00";
									//displayAlert(time);


									t3.setText(""+vitalValue);
									new UpdateVital(vitalType, vitalValue).execute();
								}
							}

						});
						builder.show();
						return true;
					}

				});


			}
		}
	}
	class UpdateVital extends AsyncTask<String,String,String>
	{
		String vitalType,vitalValue,str1;
		public UpdateVital(String vitalType,String vitalValue)
		{
			this.vitalType = vitalType;
			this.vitalValue = vitalValue;
		}
		@Override
		protected String doInBackground(String... params) {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
			nameValuePairs.add(new BasicNameValuePair("uname", uname ));
			nameValuePairs.add(new BasicNameValuePair("vitaltype", vitalType ));
			nameValuePairs.add(new BasicNameValuePair("vitalValue", vitalValue ));

			str1 = RequestHandler.getStringfromURL(Constants.UPDATE_VITAL_URL, nameValuePairs);
			return null;
		}
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			displayAlert(str1);
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
	public ArrayList<VitalDto> getVitalsList(JSONArray jarray)
	{
		int size = jarray.length();
		vitalsList = new ArrayList<VitalDto>();
		for(int i=0;i<size;i++)
		{
			VitalDto dto = new VitalDto();
			try{
				JSONObject jobject = jarray.getJSONObject(i);
				dto.setVitalId(jobject.getString("vitalsignId"));
				dto.setPartyId(jobject.getString("partyId"));
				dto.setVitalType(jobject.getString("vitalType"));
				dto.setVitalValue(jobject.getString("vitalValue"));
				dto.setUnitOfMeasure(jobject.getString("unitOfMeasure"));
				dto.setUsername(jobject.getString("username"));
				dto.setEnteredTime(jobject.getString("enteredtime"));
				vitalsList.add(dto);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}

		return vitalsList;
	}
}
