package com.tools.email;

import com.tools.entities.MailObject;

public class YahooService implements EmailService{

	public void sendMessage() {
		System.out.println("Yahoo send message");
	}

	public void readMessage() {
		System.out.println("Yahoo read message");
	}

	@Override
	public MailObject getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

}
