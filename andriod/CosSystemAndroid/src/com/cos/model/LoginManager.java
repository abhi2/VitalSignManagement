/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cos.model;


import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author akbar
 */
public class LoginManager {
 
    public String createcOSmoLoginRequest(String username,String password)
     {
         
        //username=LoginServlet.username;
        //password=LoginServlet.password;
        // String xmlResponse=null;
            
                  // The login request
    	String loginRequest = "html=false&removeNamespace=true&cos_xml=" +
        "<CosmoContainer xmlns='http://cos.ndorange.com/definitions' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'>" +
        "<CosmoRequest xsi:type='ent:LogonRequest' xmlns:ent='http://cos.ndorange.com/entitlement'>" +
              "<ent:logonInformation userId='jmartin' password='jmartin' /></CosmoRequest>" +
  "</CosmoContainer>";
           return loginRequest;
      
     }//method
public Map parsecOSLoginResponse(String xmlResponse,String username)throws Exception
{

        Map<String,String> logininfo=null;
                
        DocumentBuilder db=null;
        try {
            db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            } 
        catch (ParserConfigurationException ex) 
        {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    InputSource is = new InputSource();
    is.setCharacterStream(new StringReader(xmlResponse));

    Document doc=null;
        try {
            doc = db.parse(is);
            } 
        catch (SAXException ex)
        {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex)
        {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    NodeList nodes = doc.getElementsByTagName("CosmoResponse");

    for (int s = 0; s < nodes.getLength(); s++) 
    {

    Node fstNode = (Node) nodes.item(s);

    if (fstNode.getNodeType() == Node.ELEMENT_NODE)
    {

           Element fstElmnt = (Element) fstNode;
	   String authenticationToken= fstElmnt.getAttribute("authenticationToken");
           String companyId= fstElmnt.getAttribute("companyId");
           logininfo=new HashMap<String,String>();
           logininfo.put("authenticationToken", authenticationToken);
           logininfo.put("companyId", companyId);
           logininfo.put("username",username);
           boolean b=fstElmnt.hasAttribute("errorMessage");
           if(b)
           logininfo.clear();
    }//if
    }//for
       return logininfo;
}   
}
