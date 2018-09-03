package com.tools.email;

import com.tools.entities.Mail;

public class MalinatorService implements EmailService {

	@Override
	public Mail readEmail() {
		Mail mailObject = new Mail();
		return mailObject;
	}

}
