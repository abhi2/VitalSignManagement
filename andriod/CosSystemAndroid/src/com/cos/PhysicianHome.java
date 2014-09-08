package com.cos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PhysicianHome extends Activity {
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.home);
	        Bundle bundl=this.getIntent().getExtras();
	        final String authenticationToken=bundl.getString("authenticationToken");
	        final String companyId=bundl.getString("companyId");
	        String firstName=bundl.getString("firstName");
	        Button searchPatient=(Button)findViewById(R.id.button2);
	        searchPatient.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent i=new Intent(arg0.getContext(), PatientSearchPage.class);
					i.putExtra("authenticationToken", authenticationToken);
					i.putExtra("companyId", companyId);
					startActivity(i);
					
				}
	        	
	        });
	        
}
}