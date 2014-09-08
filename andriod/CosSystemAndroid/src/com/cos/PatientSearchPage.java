package com.cos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class PatientSearchPage extends Activity {
	  String firstName="";
      String lastName="";
      String middleName="";
     
	protected HashMap<String, String> patientDetails;
	protected String companyId;
	protected String authenticationToken;
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.searchpatient);
	        
	        Bundle bundl=this.getIntent().getExtras();
	        authenticationToken=bundl.getString("authenticationToken");
	        companyId=bundl.getString("companyId");
	        final EditText fName=(EditText)findViewById(R.id.editText1);
	       final EditText lName=(EditText)findViewById(R.id.editText2);
	       final EditText mName=(EditText)findViewById(R.id.editText3);
	       final TableLayout viewPatient=(TableLayout)findViewById(R.id.tableLayout1);
	       viewPatient.setHorizontalScrollBarEnabled(true);
	        Button search=(Button)findViewById(R.id.button1);
	        Button home=(Button)findViewById(R.id.button3);
	        Button logout=(Button)findViewById(R.id.button2);
	        logout.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i=new Intent(v.getContext(), LoginPage_temp.class);
					startActivity(i);
				}
	        	
	        });
	        home.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					finish();
				}
	        	
	        });
	        search.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if((fName.getText().toString().equals(""))&&(lName.getText().toString().equals(""))&&
							(mName.getText().toString().equals("")))
					{
						displayAlert("Please enter firstName OR LastName");
					}
					else
					{
					if(!(fName.getText().toString().equals("")))
					{
				       firstName=fName.getText().toString();		
					}
					if(!(lName.getText().toString().equals("")))
					{
						lastName=lName.getText().toString();
					}
					if(!(mName.getText().toString().equals("")))
					{
						middleName=mName.getText().toString();
					}
					Map<String,String> patientDetails=getPatientDetails();
					
					if(patientDetails==null)
					{
						displayAlert("There is no patient data with this details");
					}
					else
					{
					for(int j=0;j<(patientDetails.size()/6);j++)
					{
						
						
						TableRow row=new TableRow(v.getContext());
					     String fName=patientDetails.get("firstName");
					     String lName=patientDetails.get("lastName");
					     String gender=patientDetails.get("gender");
					     String dob=patientDetails.get("DOB");
					     String partyId=patientDetails.get("PartyId");
						Button b=new Button(v.getContext());
						final TextView t2=new TextView(v.getContext());
						final TextView t3=new TextView(v.getContext());
						final TextView t4=new TextView(v.getContext());
						final TextView t5=new TextView(v.getContext());
						 final TextView t6=new TextView(v.getContext());
						 b.setHeight(25);
						 b.setWidth(35);
						 b.setTextSize(9.0f);
						 b.setText("SetThreshold");
						b.setEnabled(true);
						t2.setEnabled(true);
						t3.setEnabled(true);
						t4.setEnabled(true);
						t5.setEnabled(true);
						 t2.setText("  "+fName);
						 t3.setText("  "+lName);
						 t4.setText("     "+gender);
						 t5.setText("    "+dob);
						 t6.setText(" "+partyId);
						 
						 
						 t2.setTextColor(getResources().getColor(R.color.black));
						 t3.setTextColor(getResources().getColor(R.color.black));
						 t4.setTextColor(getResources().getColor(R.color.black));
						 t5.setTextColor(getResources().getColor(R.color.black));
						// row.addView(t1);
						 row.addView(b);
						 row.addView(t2);
						 row.addView(t3);
						 row.addView(t4);
						 row.addView(t5);
						 row.addView(t6);
                      viewPatient.addView(row);
                      b.setOnClickListener(new OnClickListener(){

						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							String partyid=(t6.getText().toString()).trim();
							 SQLiteDatabase db1;
						     //String path="C:/Documents and Settings/genesis/";
								    db1 = openOrCreateDatabase("vitalsign1", SQLiteDatabase.CREATE_IF_NECESSARY, null);
									db1.setVersion(3);
								   Integer pid=Integer.parseInt(partyid);
								    	/*
								    	 Fetching the prescription data from the table and assigning to edittext
								    	 */
								   Intent i=new Intent(arg0.getContext(), ThresholdTable.class);
									i.putExtra("partyId", partyid);
								      Cursor cursor;
								      String sel="select * from VitalSignThreshold WHERE PId='"+pid+"'";
							    cursor=db1.rawQuery(sel,null);
								      if(cursor.moveToNext())
								      {
								        
								        try{
								        Float weight=cursor.getFloat(0);
								        Float temparature=cursor.getFloat(1);
								        Float respiration=cursor.getFloat(2);
								        Float pulseOximetry=cursor.getFloat(3);
								        Float pulse=cursor.getFloat(4);
								        Float postRespFlowRt=cursor.getFloat(5);
								        Float peakRespFlowRt=cursor.getFloat(6);
								        Float painScale=cursor.getFloat(7);
								        Float height=cursor.getFloat(8);
								        Float headCircumference=cursor.getFloat(9);
								        Float headCircumference2=cursor.getFloat(10);
								        Float glucose=cursor.getFloat(11);
								        Float bps=cursor.getFloat(12);
								        Float bpd=cursor.getFloat(13);
								       i.putExtra("w", weight);
								       i.putExtra("temp",temparature);
								       i.putExtra("resp", respiration);
								       i.putExtra("po", pulseOximetry);
								       i.putExtra("pu", pulse);
								       i.putExtra("post", postRespFlowRt);
								       i.putExtra("peak", peakRespFlowRt);
								       i.putExtra("ps", painScale);
								       i.putExtra("h", height);
								       i.putExtra("hc", headCircumference);
								       i.putExtra("hc2", headCircumference2);
								       i.putExtra("gl", glucose);
								       i.putExtra("bps", bps);
								       i.putExtra("bpd", bpd);
								        
								        }catch (Exception e) {
											// TODO: handle exception
								        	e.printStackTrace();
										}
								        
								      }
								      else
								      {
								    	  ContentValues cv=new ContentValues(16);
											cv.put("PId", pid);
											cv.put("Weight", 0.0f);
											cv.put("Temparature", 0.0f);
											cv.put("Respiration", 0.0f);
											cv.put("PulseOximetry", 0.0f);
											cv.put("Pulse", 0.0f);
											cv.put("PostRespFlowRt", 0.0f);
											cv.put("PeakRespFlowRt", 0.0f);
											cv.put("PainScale", 0.0f);
											cv.put("Height", 0.0f);
											cv.put("HeadCircumference", 0.0f);
											cv.put("HeadCircumference2", 0.0f);
											cv.put("Glucose", 0.0f);
											cv.put("BPS", 0.0f);
											cv.put("BPD", 0.0f);
											try{
											db1.insert("VitalSignThreshold", null, cv);
											displayAlert("Threshold value updated");
											}catch (Exception e) {
												// TODO: handle exception
												e.printStackTrace();
											}
								      }
							
							startActivity(i);
						}
                    	  
                      });
						}
					}
					}
					
				}
				public Map<String,String> getPatientDetails()
				  {
					 
					  String location="https://training.therasystem.net/uiservices/TSProxy";
						URL url;
						try{
					  url = new URL(location);
				 HttpURLConnection urlConnection;
					     urlConnection = (HttpURLConnection) url.openConnection();
					     urlConnection.setDoInput(true);
					     urlConnection.setDoOutput(true);
					     urlConnection.setRequestMethod("POST");
					     urlConnection.setAllowUserInteraction(true);
					    
					     urlConnection.connect();
					   //  String str=checkPatientLogin("tester", "tester");
					     //String[] str1=str.split(",");
					    
					     String loginRequest="html=false&removeNamespace=true&cos_xml= " +
							"<CosmoContainer xmlns='http://cos.ndorange.com/definitions' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'>"+
			"<CosmoRequest action='query' xsi:type='ser:IndependentObjectCosmoRequest' xmlns:ser='http://cos.ndorange.com/xml/services'>"+
			"<ExecutionContext authenticationToken='"+authenticationToken+"' companyId='"+companyId+"' opcode='query'  userId='jmartin'/>"+
			"<requestObject xsi:type='typ:PatientType' firstName='"+firstName+"' lastName='"+lastName+"' xmlns:typ='http://cos.ndorange.com/xml/MedicalRecord/types' xmlns=''/>"+
			"</CosmoRequest>"+"</CosmoContainer>";
					    
					    
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

					//Step 10 :parse the cos response xml using dom
					               
					     InputSource is= new InputSource();
					             
					     is.setCharacterStream(new StringReader(out.toString()));
					          
					     Document doc = db.parse(is);


					//Step 11: get the cosmo response node

					             NodeList nodes = doc.getElementsByTagName("CosmoResponse");
					             NodeList nodes1=null;
					             try{  
					             nodes1= doc.getElementsByTagName("results");
					                }catch(Exception e)
					                {
					                throw new Exception("no such patient details");
					                }
					//iterate the node to get the further elements
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
									        	 // retVal="error";
					          					} 
					           }
					               }
										for (int s = 0; s < nodes1.getLength(); s++) 
					          				    {

					          					    Node fstNode = (Node) nodes1.item(s);

					          					    if (fstNode.getNodeType() == Node.ELEMENT_NODE)
					          					    {

					          					           Element fstElmnt = (Element) fstNode;
					          					           patientDetails=new HashMap<String,String>();
					          						   String PartyId= fstElmnt.getAttribute("partyId");
					          					           String firstName= fstElmnt.getAttribute("firstName");
					          					           String middleName=fstElmnt.getAttribute("middleName");
					          					           String lastName= fstElmnt.getAttribute("lastName");
					          					           String gender=fstElmnt.getAttribute("gender");
					          					           String dob=fstElmnt.getAttribute("birthTime");
					          					           patientDetails.put("PartyId", PartyId);
					          					           patientDetails.put("firstName", firstName);
					          					           patientDetails.put("lastName", lastName);
					          					           patientDetails.put("gender", gender);
					          					           patientDetails.put("DOB", dob);
					          					           patientDetails.put("middleName", middleName);
					          					          
					          					           
					          					    }//if
					          					    }//for

				
						}catch (Exception e) {
							e.printStackTrace();
						}
						return patientDetails;
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
