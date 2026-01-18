package com.app.ecomm.service;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

	
	private final JavaMailSender mailSender;

	public EmailService(JavaMailSender mailsender) {
		super();
		this.mailSender = mailsender;
	}



	public void sendOtpVerificationMail(String userEmail,String otp,String subject,String text) throws MessagingException {
		
		try {
			MimeMessage msg=mailSender.createMimeMessage();
			MimeMessageHelper msgHelper=new MimeMessageHelper(
					msg,"utf-8");
			
			msgHelper.setSubject(subject);
			msgHelper.setText(text);
			msgHelper.setTo(userEmail);
			mailSender.send(msg);
			
		}
		catch(MailException e) {
			throw new MailSendException(e.getMessage());
		}
		
	}
	
}
