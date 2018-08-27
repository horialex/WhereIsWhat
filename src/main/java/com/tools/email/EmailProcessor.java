package com.tools.email;

import com.tools.entities.MailObject;

public class EmailProcessor {

	private EmailService emailService;

	public EmailProcessor(EmailService emailService) {
		this.emailService = emailService;
	}

	public MailObject readEmail() {
		return this.emailService.readEmail();
	}
	
}
