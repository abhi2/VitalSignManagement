package com.cos;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class ThresholdTable extends Activity {
	
	private CheckBox chkbx1;
	private CheckBox chkbx2;
	private CheckBox chkbx3;
	private CheckBox chkbx4;
	private CheckBox chkbx5;
	private CheckBox chkbx6;
	private CheckBox chkbx7;
	private CheckBox chkbx8;
	private CheckBox chkbx9;
	private CheckBox chkbx10;
	private CheckBox chkbx11;
	private CheckBox chkbx12;
	private CheckBox chkbx13;
	private CheckBox chkbx14;
	private EditText ediText1;
	private EditText ediText2;
	private EditText ediText3;
	private EditText ediText4;
	private EditText ediText5;
	private EditText ediText6;
	private EditText ediText7;
	private EditText ediText8;
	private EditText ediText9;
	private EditText ediText10;
	private EditText ediText11;
	private EditText ediText12;
	private EditText ediText13;
	private EditText ediText14;
	Button cancel;
	Button update;
	String partyId;
	Map<String,Float> map;
	@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.table);
	        Bundle bundl=this.getIntent().getExtras();
	        partyId=bundl.getString("partyId");
	         chkbx1=(CheckBox)findViewById(R.id.checkBox1);
	         chkbx2=(CheckBox)findViewById(R.id.checkBox2);
	         chkbx3=(CheckBox)findViewById(R.id.checkBox3);
	         chkbx4=(CheckBox)findViewById(R.id.checkBox4);
	         chkbx5=(CheckBox)findViewById(R.id.checkBox5);
	         chkbx6=(CheckBox)findViewById(R.id.checkBox6);
	         chkbx7=(CheckBox)findViewById(R.id.checkBox7);
	         chkbx8=(CheckBox)findViewById(R.id.checkBox8);
	         chkbx9=(CheckBox)findViewById(R.id.checkBox9);
	         chkbx10=(CheckBox)findViewById(R.id.checkBox10);
	         chkbx11=(CheckBox)findViewById(R.id.checkBox11);
	         chkbx12=(CheckBox)findViewById(R.id.checkBox12);
	         chkbx13=(CheckBox)findViewById(R.id.checkBox13);
	         chkbx14=(CheckBox)findViewById(R.id.checkBox14);
	         ediText1=((EditText)findViewById(R.id.edittext1));
	         ediText1.setText(""+bundl.getFloat("bpd"));
	         ediText2=(EditText)findViewById(R.id.edittext2);
	         ediText2.setText(""+bundl.getFloat("bps"));
	         ediText3=(EditText)findViewById(R.id.edittext3);
	         ediText3.setText(""+bundl.getFloat("gl"));
	         ediText4=(EditText)findViewById(R.id.edittext4);
	         ediText4.setText(""+bundl.getFloat("hc"));
	         ediText5=(EditText)findViewById(R.id.edittext5);
	         ediText5.setText(""+bundl.getFloat("hc2"));
	         ediText6=(EditText)findViewById(R.id.edittext6);
	         ediText6.setText(""+bundl.getFloat("h"));
	         ediText7=(EditText)findViewById(R.id.edittext7);
	         ediText7.setText(""+bundl.getFloat("ps"));
	         ediText8=(EditText)findViewById(R.id.edittext8);
	         ediText8.setText(""+bundl.getFloat("peak"));
	         ediText9=(EditText)findViewById(R.id.edittext9);
	         ediText9.setText(""+bundl.getFloat("post"));
	         ediText10=(EditText)findViewById(R.id.edittext10);
	         ediText10.setText(""+bundl.getFloat("pu"));
	         ediText11=(EditText)findViewById(R.id.edittext11);
	         ediText11.setText(""+bundl.getFloat("po"));
	         ediText12=(EditText)findViewById(R.id.edittext12);
	         ediText12.setText(""+bundl.getFloat("resp"));
	         ediText13=(EditText)findViewById(R.id.edittext13);
	         ediText13.setText(""+bundl.getFloat("temp"));
	         ediText14=(EditText)findViewById(R.id.edittext14);
	         ediText14.setText(""+bundl.getFloat("w"));
	         
	         cancel=(Button)findViewById(R.id.button2);
	         update=(Button)findViewById(R.id.button1);
	         ediText1.setEnabled(false);
	         ediText2.setEnabled(false);
	         ediText3.setEnabled(false);
	         ediText4.setEnabled(false);
	         ediText5.setEnabled(false);
	         ediText6.setEnabled(false);
	         ediText7.setEnabled(false);
	         ediText8.setEnabled(false);
	         ediText9.setEnabled(false);
	         ediText10.setEnabled(false);
	         ediText11.setEnabled(false);
	         ediText12.setEnabled(false);
	         ediText13.setEnabled(false);
	         ediText14.setEnabled(false);
	         
	    	chkbx1.setOnCheckedChangeListener(new myCheckBoxChnageClicker());
	    	chkbx2.setOnCheckedChangeListener(new myCheckBoxChnageClicker());
	    	chkbx3.setOnCheckedChangeListener(new myCheckBoxChnageClicker());
	    	chkbx4.setOnCheckedChangeListener(new myCheckBoxChnageClicker());
	    	chkbx5.setOnCheckedChangeListener(new myCheckBoxChnageClicker());
	    	chkbx6.setOnCheckedChangeListener(new myCheckBoxChnageClicker());
	    	chkbx7.setOnCheckedChangeListener(new myCheckBoxChnageClicker());
	    	chkbx8.setOnCheckedChangeListener(new myCheckBoxChnageClicker());
	    	chkbx9.setOnCheckedChangeListener(new myCheckBoxChnageClicker());
	    	chkbx10.setOnCheckedChangeListener(new myCheckBoxChnageClicker());
	    	chkbx11.setOnCheckedChangeListener(new myCheckBoxChnageClicker());
	    	chkbx12.setOnCheckedChangeListener(new myCheckBoxChnageClicker());
	    	chkbx13.setOnCheckedChangeListener(new myCheckBoxChnageClicker());
	    	chkbx14.setOnCheckedChangeListener(new myCheckBoxChnageClicker());
	    	
	    	cancel.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					finish();
				}
	    		
	    	});
	update.setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			map=new HashMap<String, Float>();
		if(chkbx1.isChecked()&&!(ediText1.getText().toString().equals("")))
		{
			String bpd=ediText1.getText().toString();
			
			map.put("BPD", Float.parseFloat(bpd));
		}
		if(chkbx2.isChecked()&&!(ediText2.getText().toString().equals("")))
		{
			String bps=ediText2.getText().toString();
			map.put("BPS", Float.parseFloat(bps));
		}
		if(chkbx3.isChecked()&&!(ediText3.getText().toString().equals("")))
		{
			String glucose=ediText3.getText().toString();
			map.put("Glucose", Float.parseFloat(glucose));
		}
		if(chkbx4.isChecked()&&!(ediText4.getText().toString().equals("")))
		{
			String headCir1=ediText4.getText().toString();
			map.put("HeadCircumference", Float.parseFloat(headCir1));
		}
		if(chkbx5.isChecked()&&!(ediText5.getText().toString().equals("")))
		{
			String headCir2=ediText5.getText().toString();
			map.put("HeadCircumference2", Float.parseFloat(headCir2));
		}
		if(chkbx6.isChecked()&&!(ediText6.getText().toString().equals("")))
		{
			String height=ediText6.getText().toString();
			map.put("Height", Float.parseFloat(height));
		}
		if(chkbx7.isChecked()&&!(ediText7.getText().toString().equals("")))
		{
			String ps=ediText7.getText().toString();
			map.put("PainScale", Float.parseFloat(ps));
		}
		if(chkbx8.isChecked()&&!(ediText8.getText().toString().equals("")))
		{
			String peak=ediText8.getText().toString();
			map.put("PeakRespFlowRt", Float.parseFloat(peak));
		}
		if(chkbx9.isChecked()&&!(ediText9.getText().toString().equals("")))
		{
			String post=ediText9.getText().toString();
			map.put("PostRespFlowRt", Float.parseFloat(post));
		}
		if(chkbx10.isChecked()&&!(ediText10.getText().toString().equals("")))
		{
			String pulse=ediText10.getText().toString();
			map.put("Pulse", Float.parseFloat(pulse));
		}
		if(chkbx11.isChecked()&&!(ediText11.getText().toString().equals("")))
		{
			String pulseox=ediText11.getText().toString();
			map.put("PulseOximetry", Float.parseFloat(pulseox));
		}
		if(chkbx12.isChecked()&&!(ediText12.getText().toString().equals("")))
		{
			String resp=ediText12.getText().toString();
			map.put("Respiration", Float.parseFloat(resp));
		}
		if(chkbx13.isChecked()&&!(ediText13.getText().toString().equals("")))
		{
			String temp=ediText1.getText().toString();
			map.put("Temparature", Float.parseFloat(temp));
		}
		if(chkbx14.isChecked()&&!(ediText14.getText().toString().equals("")))
		{
			String weight=ediText1.getText().toString();
			map.put("Weight", Float.parseFloat(weight));
		}
		updatePatientThreshold(partyId,map);
		
		}
		
	});      
	 }
	
	 protected void updatePatientThreshold(String partId, Map<String, Float> map2) {
		// TODO Auto-generated method stub
		 System.out.println(map2.size());
		 SQLiteDatabase db1;
	     //String path="C:/Documents and Settings/genesis/";
			    db1 = openOrCreateDatabase("vitalsign1", SQLiteDatabase.CREATE_IF_NECESSARY, null);
				db1.setVersion(3);
			   Integer pid=Integer.parseInt(partId);
			    	/*
			    	 Fetching the prescription data from the table and assigning to edittext
			    	 */
			  Set<String> s=map2.keySet();
			  Iterator<String> iter=s.iterator();
			      Cursor cursor;
			      for(int i=0;i<map2.size();i++)
			 		 {
			 		String name=iter.next();    
			 		Float f=map2.get(name);			     
			 		 
			      String sel="Update VitalSignThreshold set "+name+"='"+f+"'   WHERE PId='"+partId+"'";
			      try{
			    	  db1.execSQL(sel);
			    	  
			      }catch (Exception e) {
					// TODO: handle exception
			    	  e.printStackTrace();
				}
			 		 }
	}

	class myCheckBoxChnageClicker implements CheckBox.OnCheckedChangeListener
	 {

		

		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			// TODO Auto-generated method stub
			 if(isChecked)
			  {
			   if(buttonView==chkbx1)
				{
					ediText1.setEnabled(true);
					
				}

				if(buttonView==chkbx2)
				{
					ediText2.setEnabled(true);
				}

				if(buttonView==chkbx3)
				{
					ediText3.setEnabled(true);
				}
				  if(buttonView==chkbx4)
					{
						ediText4.setEnabled(true);
						
					}

					if(buttonView==chkbx5)
					{
						ediText5.setEnabled(true);
					}

					if(buttonView==chkbx6)
					{
						ediText6.setEnabled(true);
					}
					 if(buttonView==chkbx7)
						{
							ediText7.setEnabled(true);
							
						}

						if(buttonView==chkbx8)
						{
							ediText8.setEnabled(true);
						}

						if(buttonView==chkbx9)
						{
							ediText9.setEnabled(true);
						}
						 if(buttonView==chkbx10)
							{
								ediText10.setEnabled(true);
								
							}

							if(buttonView==chkbx11)
							{
								ediText11.setEnabled(true);
							}

							if(buttonView==chkbx12)
							{
								ediText12.setEnabled(true);
							}
							 if(buttonView==chkbx13)
								{
									ediText13.setEnabled(true);
									
								}

								if(buttonView==chkbx14)
								{
									ediText14.setEnabled(true);
								}

								
						
			  }
			 if(!isChecked)
			 {
				 if(buttonView==chkbx1)
					{
						ediText1.setEnabled(false);
					}

					if(buttonView==chkbx2)
					{
						ediText2.setEnabled(false);
					}

					if(buttonView==chkbx3)
					{
						ediText3.setEnabled(false);
					}
					 if(buttonView==chkbx4)
						{
							ediText4.setEnabled(false);
						}

						if(buttonView==chkbx5)
						{
							ediText5.setEnabled(false);
						}

						if(buttonView==chkbx6)
						{
							ediText6.setEnabled(false);
						}
						 if(buttonView==chkbx7)
							{
								ediText7.setEnabled(false);
							}

							if(buttonView==chkbx8)
							{
								ediText8.setEnabled(false);
							}

							if(buttonView==chkbx9)
							{
								ediText9.setEnabled(false);
							}
							 if(buttonView==chkbx10)
								{
									ediText10.setEnabled(false);
								}

								if(buttonView==chkbx11)
								{
									ediText11.setEnabled(false);
								}

								if(buttonView==chkbx12)
								{
									ediText12.setEnabled(false);
								}
								 if(buttonView==chkbx13)
									{
										ediText13.setEnabled(false);
									}

									if(buttonView==chkbx14)
									{
										ediText14.setEnabled(false);
									}

								
			 }
		}

	 }
	 
}
