package com.tools.customerDrivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import net.thucydides.core.webdriver.DriverSource;

public class CustomDriver2 implements DriverSource {

	@Override
	public WebDriver newDriver() {
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		// add custom capabilities when needed
		return new FirefoxDriver(capabilities);
	}

	@Override
	public boolean takesScreenshots() {
		return true;
	}

}
