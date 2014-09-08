package com.cos.model;
import java.io.IOException;
import java.io.StringReader;
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





public class PatientManager {
public String createPatientDemographicSrvice(String firstname,String lastname,String authentkey,String companyId,String userId)
{
String xmlRequest="html=false&removeNamespace=true&cos_xml= " +
					"<CosmoContainer xmlns='http://cos.ndorange.com/definitions' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'>"+
"<CosmoRequest action='query' xsi:type='ser:IndependentObjectCosmoRequest' xmlns:ser='http://cos.ndorange.com/xml/services'>"+
"<ExecutionContext authenticationToken='"+authentkey+"' companyId='"+companyId+"' opcode='query'  userId='"+userId+"'/>"+
"<requestObject xsi:type='typ:PatientType' firstName='"+firstname+"' lastName='"+lastname+"' xmlns:typ='http://cos.ndorange.com/xml/MedicalRecord/types' xmlns=''/>"+
"</CosmoRequest>"+"</CosmoContainer>";
return xmlRequest;
}
public Map parsePatientDemographicService(String xmlResponse)throws Exception
{
   Map<String,String> patientDetails=null;
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
        patientDetails.clear();
    }
     }
    for (int s = 0; s < nodes.getLength(); s++) 
    {

    Node fstNode = (Node) nodes.item(s);

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
       return patientDetails;

}
}
