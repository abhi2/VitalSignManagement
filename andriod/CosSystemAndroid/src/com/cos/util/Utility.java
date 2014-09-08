/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cos.util;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.*;
/**
 *
 * @author sravan
 */
public class Utility {
 static String xmlResponse=null;
  public static String sendToCos(String cosmoRequest)
  {
            
            URL url = null;
            HttpURLConnection urlConnection=null;

            // the location of the server

            String location = "https://training.therasystem.net/uiservices/TSProxy";

            // Create a connection in java to the server url

            try {

                  url = new URL(location);

                  urlConnection = (HttpURLConnection) url.openConnection();

                  urlConnection.setDoInput(true);

                  urlConnection.setDoOutput(true);

                  urlConnection.setRequestMethod("POST");

                  urlConnection.setAllowUserInteraction(true);

                   urlConnection.connect();

                  }catch (Exception e){

                        e.printStackTrace();

                  }
                  String xmlRequest=cosmoRequest;
                  
                   BufferedReader br = null;

                  BufferedWriter bw = null;

                  OutputStreamWriter os = null;

                  InputStreamReader in = null;
                  // receive the response from cos in the following data type.

                  //StringBuilder out = new StringBuilder();
                  try{

                	  StringBuilder out = new StringBuilder();
                      
                	  
                      os = new OutputStreamWriter(urlConnection.getOutputStream());
                      bw = new BufferedWriter(os); // Writing to the servlet

                      // Writing the contents of the login request to the cos servlet
                bw.write(xmlRequest);
                bw.flush();
         
         
                in = new InputStreamReader(urlConnection.getInputStream());
                br = new BufferedReader(in);
         
                  //System.out.println(br.readLine());
                  int responseCode = urlConnection.getResponseCode();
                  if (responseCode == HttpURLConnection.HTTP_OK) { // Response is o.

                        // then reading

                        // the

                        // CosmoResponse
                        String readLin = null;
                        
                        //System.out.println(br.readLine());
                        while ((readLin = br.readLine()) != null) {
                              xmlResponse=out.append(readLin).toString();
                              }

                        System.out.println(out.toString());

                  }
                  else if (responseCode == HttpURLConnection.HTTP_INTERNAL_ERROR)
                  {
                     br = new BufferedReader(new InputStreamReader(urlConnection.getErrorStream()));

                  }
                  else 
                  {
                      System.out.println("The response code " + responseCode);

                  }

                  }
                  catch (Exception e)
                  {

                        e.printStackTrace();
                  }
      
      return xmlResponse;
  }   
}
