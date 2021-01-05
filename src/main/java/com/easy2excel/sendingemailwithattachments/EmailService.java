package com.easy2excel.sendingemailwithattachments;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;

import org.springframework.web.multipart.MultipartFile;

public interface EmailService {

	public void sendEmailWithAttachments(String[]to,String[]cc,String[]bcc,String subject
            ,MultipartFile attachment) throws NoSuchProviderException, IOException, MessagingException;
}
