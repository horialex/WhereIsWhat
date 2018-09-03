package com.tools.email;

import com.tools.entities.Mail;

public class EmailProcessor {

	private EmailService emailService;

	public EmailProcessor(EmailService emailService) {
		this.emailService = emailService;
	}

	public Mail readEmail() {
		return this.emailService.readEmail();
	}
	
}
