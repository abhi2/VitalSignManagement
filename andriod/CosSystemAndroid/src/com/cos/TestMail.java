package com.cos;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class TestMail {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 try { 
			    // Security.addProvider(new com.ibm.jsse.IBMJSSEProvider()); 
			   final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory"; 
			   Properties props = System.getProperties(); 
			   props.setProperty("mail.smtp.host", "smtp.gmail.com"); 
			   props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY); 
			   props.setProperty("mail.smtp.socketFactory.fallback", "false"); 
			   props.setProperty("mail.smtp.port", "465"); 
			   props.setProperty("mail.smtp.socketFactory.port", "465"); 
			   props.put("mail.smtp.auth", "true"); 
			   final String username = "care.health.org@gmail.com"; 
			   final String password = "health@care"; 
			   Session session = Session.getDefaultInstance(props, 
			     new Authenticator() { 
			      protected PasswordAuthentication getPasswordAuthentication() { 
			       return new PasswordAuthentication(username, 
			         password); 
			      } 
			     });   
			 
			   // -- Create a new message -- 
			   Message msg = new MimeMessage(session);
			   
			 
			   // -- Set the FROM and TO fields -- 
			   msg.setFrom(new InternetAddress("tmainproject@gmail.com")); 
			   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse( 
					   "tmainproject@gmail.com", false)); 
			   msg.setSubject("ABC"); 
			   msg.setText("HI"); 
			   msg.setSentDate(new Date()); 
			   Transport.send(msg);   
			 
			  } catch (MessagingException e) { 
			   e.printStackTrace(); 
			     }  
	}
	public static final void sendMail(String to, String title, String content) { 
		  try { 
		    // Security.addProvider(new com.ibm.jsse.IBMJSSEProvider()); 
		   final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory"; 
		   Properties props = System.getProperties(); 
		   props.setProperty("mail.smtp.host", "smtp.gmail.com"); 
		   props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY); 
		   props.setProperty("mail.smtp.socketFactory.fallback", "false"); 
		   props.setProperty("mail.smtp.port", "465"); 
		   props.setProperty("mail.smtp.socketFactory.port", "465"); 
		   props.put("mail.smtp.auth", "true"); 
		   final String username = "care.health.org@gmail.com"; 
		   final String password = "health@care"; 
		   Session session = Session.getDefaultInstance(props, 
		     new Authenticator() { 
		      protected PasswordAuthentication getPasswordAuthentication() { 
		       return new PasswordAuthentication(username, 
		         password); 
		      } 
		     });   
		 
		   // -- Create a new message -- 
		   Message msg = new MimeMessage(session);
		   
		 
		   // -- Set the FROM and TO fields -- 
		   msg.setFrom(new InternetAddress(to)); 
		   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse( 
		     to, false)); 
		   msg.setSubject(title); 
		   msg.setText(content); 
		   msg.setSentDate(new Date()); 
		   Transport.send(msg);   
		 
		  } catch (MessagingException e) { 
		   e.printStackTrace(); 
		     }   
	}
}
