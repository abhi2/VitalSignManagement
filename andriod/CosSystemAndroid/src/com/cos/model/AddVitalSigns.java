package com.cos.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
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
public class AddVitalSigns {
    
    public String createEncounterID(String authenticationToken,String companyId,String partyId,String userId)
    {
    
        String xmlRequest="html=false&removeNamespace=true&cos_xml= " +
"<CosmoContainer xmlns='http://cos.ndorange.com/definitions' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'>"+
"<CosmoRequest service='com.ndorange.therasystem.services.EncounterService' action='insert' companyId='2706' xsi:type='ser:encounterRequest' xmlns:ser='http://cos.ndorange.com/xml/services'>"+
"<ExecutionContext authenticationToken='"+authenticationToken+"' companyId='"+companyId+"' organizationId='' opcode='insert' userId='"+userId+"'/>"+
"<requestObject partyId='"+partyId+"' status='Sch' encounterType='IC'  currentInsuranceId='Medicaid' reason='Testing successful' xsi:type='typ:EncounterType' xmlns:typ='http://cos.ndorange.com/xml/MedicalRecord/types' xmlns=''/>"+
"</CosmoRequest>"+
"</CosmoContainer>";
    return xmlRequest;
    }
    
    
   public String parseEncounterIdXML(String encouterIdXML) throws IOException, Exception
   {
       
       String encounterid=null; 
       DocumentBuilder db=null;
        try {
            db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            } 
        catch (ParserConfigurationException ex) 
        {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    InputSource is = new InputSource();
    is.setCharacterStream(new StringReader(encouterIdXML));

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
    
    NodeList nodeLst1 = doc.getElementsByTagName("CosmoResponse");
     NodeList nodes=null;
    try{  
    nodes= doc.getElementsByTagName("results");
       }catch(Exception e)
       {
       throw new Exception("no such patient details");
       }
     for (int i= 0; i< nodeLst1.getLength(); i++) {

    Node fstNode1 = (Node) nodeLst1.item(i);
    
    if (fstNode1.getNodeType() == Node.ELEMENT_NODE) {
        Element fstElmnt1 = (Element) fstNode1;
        if(fstElmnt1.hasAttribute("errorMessage"))
        encounterid=null;
    }
     }
     
    for (int s = 0; s < nodes.getLength(); s++) 
    {

    Node fstNode = (Node) nodes.item(s);

    if (fstNode.getNodeType() == Node.ELEMENT_NODE)
    {

           Element fstElmnt = (Element) fstNode;
           encounterid=fstElmnt.getAttribute("encounterId");
    }//if
    }//for
       
          return encounterid;
   }
   public String createAddVitalSignsXMLRequest(String authentToken,String companyId,String partyId,String encounterId,ArrayList a)
   {
   StringBuilder xmlrequest=new StringBuilder("html=false&removeNamespace=true&cos_xml= "+
"<CosmoContainer xmlns='http://cos.ndorange.com/definitions' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'>"+
"<CosmoRequest action='special' companyId='"+companyId+"' patientPartyId='144974' requestObjectClassName='EncounterType' service='com.ndorange.therasystem.services.EncounterService' specialOp='FullUpdateInsert' xmlns:ser='http://cos.ndorange.com/xml/services' xsi:type='ser:encounterRequest'>"+
"<ExecutionContext authenticationToken='"+authentToken+"' companyId='"+companyId+"'/>"+
"<requestObject encounterId='"+encounterId+"' partyId='144974' xmlns='' xmlns:typ='http://cos.ndorange.com/xml/MedicalRecord/types' xsi:type='typ:EncounterType'>");
   
   for(int i=0;i<a.size();i++)
   {
       String addvitalsigns=a.get(i).toString();
       String []addvitals=addvitalsigns.split(",");
       
       xmlrequest=xmlrequest.append("<vitalSigns  comments='' partyId='"+partyId+"' patientEncounterId='"+encounterId+"' recordingDateTime='2011-10-12T16:50:00.000-00:00' vitalSignType='"+addvitals[0]+"' vitalSignUnitOfMeasure='"+addvitals[3]+"' vitalSignValue='"+addvitals[2]+"' xsi:type='typ:VitalSignType'/>");
   }
   xmlrequest=xmlrequest.append("</requestObject>"+"</CosmoRequest>"+ "</CosmoContainer>");
   
   return xmlrequest.toString();
   }
   
   public String parseAddVitalSignsResponse(String XMLResponse) throws Exception
   {
       
   String encounterid=null; 
       DocumentBuilder db=null;
        try {
            db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            } 
        catch (ParserConfigurationException ex) 
        {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    InputSource is = new InputSource();
    is.setCharacterStream(new StringReader(XMLResponse));

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
    
    NodeList nodeLst1 = doc.getElementsByTagName("CosmoResponse");
     NodeList nodes=null;
    try{  
    nodes= doc.getElementsByTagName("results");
       }catch(Exception e)
       {
       throw new Exception("no such patient details");
       }
     for (int i= 0; i< nodeLst1.getLength(); i++) {

    Node fstNode1 = (Node) nodeLst1.item(i);
    
    if (fstNode1.getNodeType() == Node.ELEMENT_NODE) {
        Element fstElmnt1 = (Element) fstNode1;
        if(fstElmnt1.hasAttribute("errorMessage"))
        encounterid=null;
    }
     }
     
    for (int s = 0; s < nodes.getLength(); s++) 
    {

    Node fstNode = (Node) nodes.item(s);

    if (fstNode.getNodeType() == Node.ELEMENT_NODE)
    {

           Element fstElmnt = (Element) fstNode;
           encounterid=fstElmnt.getAttribute("encounterId");
    }//if
    }//for
            System.out.println("encounterId after response"+encounterid);
          return encounterid;    
   }
   
   public String insertIntoDB(String partyId,String encounterId,ArrayList a)throws Exception
   {
   String inserted=null;
   Class.forName("com.mysql.jdbc.Driver");
   Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ndorange","root","root");
   Statement stm=con.createStatement();
   for(int i=0;i<a.size();i++)
       {
   String addvitalsigns=a.get(i).toString();
   String []addvitals=addvitalsigns.split(",");
   int insert=stm.executeUpdate("insert into addvitals(partyId,encounterId,vitalType,vitalValue,unitOfMeasure)values('"+partyId+"','"+encounterId+"','"+addvitals[0]+"','"+addvitals[2]+"','"+addvitals[3]+"')");
       if(insert==1)
       {
       inserted="success";
       }
       
       }
   return inserted;
   }
}
