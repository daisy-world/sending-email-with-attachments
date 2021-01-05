package com.easy2excel.sendingemailwithattachments;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class EmailController {
	@Autowired
	EmailService emailService;
	@GetMapping("/")
	
	public String sayHello() {
		return "hello";
		
		
	}
	@PostMapping("/sendEmail")

	public String sendEmail(@RequestParam("to") String[] to,
			@RequestParam("cc") String[] cc,
			@RequestParam("bcc") String[] bcc,
			@RequestParam("subject") String subject,			
			@RequestParam("attachment") MultipartFile attachment) throws NoSuchProviderException, IOException, MessagingException {
		
		emailService.sendEmailWithAttachments(to,cc,bcc,subject,attachment);
		return "email sent successfully";
		
		
	}

}
