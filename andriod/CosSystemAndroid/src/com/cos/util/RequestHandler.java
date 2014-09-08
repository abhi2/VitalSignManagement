package com.cos.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;


/**
 * This class contains methods used for server client communication.
 * 
 */
public class RequestHandler {

	/**
	 * This method communicates with the server and gets the Json object.
	 * 
	 * @param url
	 *            the url to connect to.
	 * @return returns json object containing the data.
	 */
	public static String getStringfromURL(String url, List<NameValuePair> nameValuePairs) {

		InputStream is = null;
		String result = "";
		JSONObject jobject = null;
		// Http Post
		try {
			
			HttpParams params = new BasicHttpParams();			
			HttpConnectionParams.setConnectionTimeout(params, 100000);
			HttpConnectionParams.setSoTimeout(params, 100000);
			System.out.println(url);
			HttpClient httpclient = new DefaultHttpClient(params);
			HttpPost httppost = new HttpPost(url);
//			httppost.setHeader("Content-Type", "application/x-www-form-urlencoded");
			
			
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            
            
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
		
			is = entity.getContent();
		} catch (Exception e) {			
			e.printStackTrace();	
		}
		// convert response to string
		try {			
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF8"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			
			while ((line = reader.readLine()) != null) {			
				sb.append(line + "\n");			
			}			
			is.close();			
			result = sb.toString();
		
		} catch (Exception e) {
			e.printStackTrace();		
		}
		finally {
	        if (is != null) {
	            is = null;
	        }
	    }
		
		
		return result;
	}
	
	
	

}
