package com.tests;

import com.tools.email.EmailProcessor;
import com.tools.email.MalinatorService;

public class ReadEmailTest {
	public static void main(String[] args) {
		EmailProcessor emailProcessor = new EmailProcessor(new MalinatorService());
		emailProcessor.readEmail();
	}

}
