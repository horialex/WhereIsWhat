package com.tests;

import com.tools.email.EmailProcessor;
import com.tools.email.GmailService;
import com.tools.entities.MailObject;

public class ReadEmailTest {
	public static void main(String[] args) {
		EmailProcessor emailProcessor = new EmailProcessor(new GmailService());
		MailObject mailObject = emailProcessor.readEmail();
		System.out.println("Mail sender is " + mailObject.getSender());
	}

}
