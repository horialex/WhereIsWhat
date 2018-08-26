package com.tools.email;

import com.tools.entities.MailObject;

public interface EmailService {
	public void sendMessage();
	public void readMessage();
	public MailObject getMessage();
}
