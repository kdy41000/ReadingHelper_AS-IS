package com.readbook.login.controller;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends Authenticator{
	
	   public SMTPAuthenticator() {
	       super();
	   }

	   public PasswordAuthentication getPasswordAuthentication() {   //보내는 사람
		   
	       String username = "kdy41000@gmail.com";
	       String password = "vqgolmjiwlulwfwk";
	       return new PasswordAuthentication(username, password);
	   }
}
