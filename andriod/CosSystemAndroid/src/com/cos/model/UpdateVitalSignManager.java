/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cos.model;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
public class UpdateVitalSignManager {
 
    public String getEncounterId(String authentToken,String companyId,String partyId,String username)
    {
    String encounterxmlRequest="html=false&removeNamespace=true&cos_xml= " +
"<CosmoContainer xmlns='http://cos.ndorange.com/definitions' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'>"+
"<CosmoRequest service='com.ndorange.therasystem.services.EncounterService' action='insert' companyId='"+companyId+"' xsi:type='ser:encounterRequest' xmlns:ser='http://cos.ndorange.com/xml/services'>"+
"<ExecutionContext authenticationToken='"+authentToken+"' companyId='"+companyId+"' organizationId='' opcode='insert' userId='"+username+"'/>"+
"<requestObject partyId='"+partyId+"' status='Sch' encounterType='IC'  currentInsuranceId='Medicaid' reason='Testing successful' xsi:type='typ:EncounterType' xmlns:typ='http://cos.ndorange.com/xml/MedicalRecord/types' xmlns=''/>"+
"</CosmoRequest>"+
"</CosmoContainer>";
    
    
    return encounterxmlRequest;
    }
    
    public String parseEncounterRespose(String encouterXMLResponse)throws Exception
    {
        String encouterId=null;
        DocumentBuilder db=null;
        try {
            db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            } 
        catch (ParserConfigurationException ex) 
        {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    InputSource is = new InputSource();
    is.setCharacterStream(new StringReader(encouterXMLResponse));

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
    
   
     NodeList nodes=doc.getElementsByTagName("results");
    for (int i= 0; i< nodes.getLength(); i++) {

    Node fstNode1 = (Node) nodes.item(i);
    
    if (fstNode1.getNodeType() == Node.ELEMENT_NODE) {
        Element fstElmnt1 = (Element) fstNode1;
        if(fstElmnt1.hasAttribute("errorMessage"))
        {
        encouterId=null;
        }
        String encouterID= fstElmnt1.getAttribute("encounterId");
        encouterId=encouterID;
    }
     }
        
        return encouterId;
    }
   
    public String createQueryVitalSign(String authentToken,String companyId,String partyId,String encounterId,String username)
    {
    String createXMLQueryRequest="html=false&removeNamespace=true&cos_xml= " +
"<CosmoContainer xmlns='http://cos.ndorange.com/definitions' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'>"+
"<CosmoRequest service='com.ndorange.therasystem.services.EncounterService' action='query' id='"+encounterId+"' companyId='"+companyId+"' xsi:type='ser:dependentEncounterCosmoRequest' xmlns:ser='http://cos.ndorange.com/xml/services'>"+
"<ExecutionContext authenticationToken='"+authentToken+"' companyId='"+companyId+"'  organizationId='' opcode='query' userId='"+username+"'/>"+
"<requestObject partyId='144974' xsi:type='typ:VitalSignType' xmlns:typ='http://cos.ndorange.com/xml/MedicalRecord/types' xmlns=''/>"+"</CosmoRequest></CosmoContainer>";
    return createXMLQueryRequest;
    }//createQueryvitalSign
    
    
    public List parseQueryVitalsignResponse(String vitalSignResponse)throws Exception
    {
    
    List vitalSigns=new ArrayList();
    DocumentBuilder db=null;
        try {
            db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            } 
        catch (ParserConfigurationException ex) 
        {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    InputSource is = new InputSource();
    is.setCharacterStream(new StringReader(vitalSignResponse));

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
    
   
     NodeList nodes=doc.getElementsByTagName("results");
    for (int i= 0; i< nodes.getLength(); i++) {

    Node fstNode1 = (Node) nodes.item(i);
    
    if (fstNode1.getNodeType() == Node.ELEMENT_NODE) {
        Element fstElmnt1 = (Element) fstNode1;
        
        String vitalSignDetails=fstElmnt1.getAttribute("id")+","+fstElmnt1.getAttribute("vitalSignType")+","+fstElmnt1.getAttribute("vitalSignValue")+","+fstElmnt1.getAttribute("recordingDateTime")+","+fstElmnt1.getAttribute("vitalSignUnitOfMeasure")+","+fstElmnt1.getAttribute("encounterId");
        vitalSigns.add(vitalSignDetails);
        }
    }
    
   
    return vitalSigns;
    }//parsevitalsign
    public String createUpdateVitalSignQuery(String authentToken,String companyId,String partyId,String encounterId,String username,String id,String vitalType,String vitalValue,String time,String measure)
    {
       String updateXMLQuery="html=false&removeNamespace=true&cos_xml= " +
"<CosmoContainer xmlns='http://cos.ndorange.com/definitions' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'>"+
"<CosmoRequest action='update' companyId='"+companyId+"' id='"+encounterId+"' patientPartyId='"+partyId+"' requestObjectClassName='VitalSignType' service='com.ndorange.therasystem.services.EncounterService' xmlns:ser='http://cos.ndorange.com/xml/services' xsi:type='ser:dependentEncounterCosmoRequest'>"+
"<ExecutionContext authenticationToken='"+authentToken+"' companyId='"+companyId+"' organizationId='' opcode='update' userId='"+username+"'/>"+
"<requestObject comments='AKBAR Updated' recordingDateTime='"+time+"' id='"+id+"' vitalSignType='"+vitalType+"' vitalSignUnitOfMeasure='"+measure+"' vitalSignValue='"+vitalValue+"' xmlns='' xmlns:typ='http://cos.ndorange.com/xml/MedicalRecord/types' xsi:type='typ:VitalSignType'/>" +
				  "</CosmoRequest></CosmoContainer>";
 
        
        return updateXMLQuery;
    }//update
    
    
    public String parseUpdatedXMLResponse(String xmlResponse)throws Exception
    {
     
        String message=null;
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
    
   
     NodeList nodes=doc.getElementsByTagName("results");
    for (int i= 0; i< nodes.getLength(); i++) {

    Node fstNode1 = (Node) nodes.item(i);
    
    if (fstNode1.getNodeType() == Node.ELEMENT_NODE) {
        Element fstElmnt1 = (Element) fstNode1;
        if(fstElmnt1.hasAttribute("comments"))
        {
            message="success fully updated";
        }
        else
        {
            message="failed to update";
        }
    }
     }
     
    return message;
    }
    
}
