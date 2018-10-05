package com.tests;

import com.tools.email.EmailProcessor;
import com.tools.email.GmailService;
import com.tools.entities.Mail;

public class ReadEmailTest {
	public static void main(String[] args) {
		EmailProcessor emailProcessor = new EmailProcessor(new GmailService());
		Mail mailObject = emailProcessor.readEmail();
		System.out.println("Mail sender is " + mailObject.getSender());
		System.out.print("Content is : " + mailObject.getMailContent());
	}

}
