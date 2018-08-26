package com.tools.email;

public class EmailProcessor {

	private EmailService emailService;

	public EmailProcessor(EmailService emailService) {
		this.emailService = emailService;
	}

	public void readEmail() {
		this.emailService.readMessage();
	}
	
	public void sendEmail() {
		this.emailService.sendMessage();
	}
	
}
