package com.tests;

import net.thucydides.core.annotations.Managed;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public class BaseTest {
	@Managed(uniqueSession = true)
	public WebDriver webdriver;

	@Before
	public void setup() {

		System.setProperty("http.proxyHost", "localhost");
		System.setProperty("http.proxyPort", "8080");
		System.setProperty("https.proxyHost", "localhost");
		System.setProperty("https.proxyPort", "8080");
		System.setProperty("env", "qa-env");

	}

}
