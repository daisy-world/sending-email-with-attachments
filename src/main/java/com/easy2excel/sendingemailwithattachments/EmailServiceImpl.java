package com.easy2excel.sendingemailwithattachments;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EmailServiceImpl implements EmailService {
	
	String SMTP_HOST_NAME = {YOUR_SMTP_HOST_NAME}
	int SMTP_HOST_PORT={YOUR_SMTP_PORT} ;
	String SMTP_AUTH_USER={YOUR_SMTP_AUTH_USER};
	String SMTP_AUTH_PWD={YOUR_SMTP_AUTH_PASSWORD};
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@Override
	public void sendEmailWithAttachments(String[]to,String[]cc,String[]bcc,String subject
            ,MultipartFile attachment) throws IOException, MessagingException {
		
		
				
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", SMTP_HOST_NAME);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		// creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SMTP_AUTH_USER, SMTP_AUTH_PWD);
            }
        };
 
		Session mailSession = Session.getInstance(props, auth);
		String body = "<body>"+
				"     <div style=' display: block; margin-top: 60px; '>"+
				"         <div style=' margin: 0 auto; "+
				"         width: 100px; "+
				"         background-color: #f4f4f4; "+
				"         height: 100px; "+
				"         border: 1px #ccc solid; padding: 30px;'>"+
				"             <!-- heading block-->"+
				"            <h1 style='text-align: center; padding: -5px;color: rgba(0, 0, 0, 0.85); font-weight: 500; font-family:  Segoe UI Symbol'>Good Morning lipsa</h1>"+
				"       </div>"+
				"     </div>"+
				" </body>";
		  Message message = new MimeMessage(mailSession);
	        message.setFrom(new InternetAddress(SMTP_AUTH_USER)); //from address
	        /***********************to address start***********************/
	        if(to.length>0) {
          InternetAddress[] toAddress = new InternetAddress[to.length];
          // To get the array of to addresses
          for( int i = 0; i < to.length; i++ ) {
              toAddress[i] = new InternetAddress(to[i]);
          }

          // Set to: header field of the header.

          for( int i = 0; i < toAddress.length; i++) {
              message.addRecipient(Message.RecipientType.TO, toAddress[i]);
          }
	        }
          /***********************to address end***********************/
          
          
  	    /***********************cc address start***********************/

	        if(cc.length>0) {
          InternetAddress[] ccAddress = new InternetAddress[cc.length];
          
          // To get the array of ccaddresses
          for( int i = 0; i < cc.length; i++ ) {
          	
              ccAddress[i] = new InternetAddress(cc[i]);
              
          }
          
          // Set cc: header field of the header.
          for( int i = 0; i < ccAddress.length; i++) {
              message.addRecipient(Message.RecipientType.CC, ccAddress[i]);
          }
	        }
  	    /***********************cc address ends***********************/
          

  	    /***********************bcc address starts***********************/
	        if(bcc.length>0) {
          InternetAddress[] bccAddress = new InternetAddress[bcc.length];
          
          // To get the array of bccaddresses
          for( int i = 0; i < bcc.length; i++ ) {
          	
              bccAddress[i] = new InternetAddress(bcc[i]);
          }
          
          // Set bcc: header field of the header.
          for( int i = 0; i < bccAddress.length; i++) {
              message.addRecipient(Message.RecipientType.BCC, bccAddress[i]);
          }
	        }
          
  	    /***********************bcc address ends***********************/
	        message.setSubject(subject);
	        
	        
          Multipart multipart = new MimeMultipart();          
          BodyPart messageBodyPart = new MimeBodyPart();
          messageBodyPart.setContent(body,"text/html");
        multipart.addBodyPart(messageBodyPart);
        /***********************attachment starts ***********************/            

      	if(attachment.getSize()>0) {
         MimeBodyPart mimeBodyPart = new MimeBodyPart();
    
		File file = new File(System.getProperty("java.io.tmpdir")+attachment.getOriginalFilename());

    	FileDataSource fileDataSource = new FileDataSource(file);
  		mimeBodyPart.setDataHandler(new DataHandler(fileDataSource));
      	mimeBodyPart.setFileName(fileDataSource.getName());
      	multipart.addBodyPart(mimeBodyPart);    
      	}
      	message.setContent(multipart);
     	 message.setSentDate(new Date());
         
         Transport.send(message);

         }


	}


