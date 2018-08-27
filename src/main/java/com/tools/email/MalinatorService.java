package com.tools.email;

import com.tools.entities.MailObject;

public class MalinatorService implements EmailService {

	@Override
	public MailObject readEmail() {
		MailObject mailObject = new MailObject();
		return mailObject;
	}

}
