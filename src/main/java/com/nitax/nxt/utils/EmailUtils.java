package com.nitax.nxt.utils;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.nitax.nxt.dto.UserMsgDto;
@Component
public class EmailUtils {
	private JavaMailSender javaMailSender;
	
	public EmailUtils(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	//public boolean sendMail(UserMsgDto umd) {
	public boolean sendMail(UserMsgDto umd) {
		
		try {
			MimeMessage mimeMsg=javaMailSender.createMimeMessage();
			MimeMessageHelper helper =new MimeMessageHelper(mimeMsg, true);
			
			//helper.setFrom("info@nxt.ng");
			helper.setTo(umd.getEmail());
			helper.setBcc("info@nxt.ng");
			helper.setSubject("Re: "+umd.getMsg());
			 String mail="Dear {FNAME}," 
			    		+ '\n' + '\n' 
			    		+"Thank you for applying to NXT." + '\n'
			    		+"Your Application has been successfully received. Due to the large number of applications we receive, only shortlisted candidates will be contacted." + '\n'
			    		+"This is an automated email, please do not reply." + '\n' 
			    		+"Kind regards," + '\n' + '\n'
			    		+"HR Team"
			    		+'\n' + '\n'
			    		+ '\n' + '\n' 
			    		+ '\n' + '\n' 
			    		+"Email:  {EMAIL}" + "  "+ "Phone Number: {CONTACT}"
			    		;
			 mail= mail.replace("{FNAME}", umd.getName());
			 mail= mail.replace("{EMAIL}", umd.getEmail());
			 mail= mail.replace("{CONTACT}", umd.getPhoneNo());
			 
			//helper.setText(getEmailBody(umd));
			 helper.setText(mail);
			javaMailSender.send(mimeMsg);
			//isSent= true;
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		//return isSent;
		return false;
	}

}
