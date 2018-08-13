package com.tools.customerDrivers;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import net.thucydides.core.webdriver.DriverSource;

public class CustomDriver implements DriverSource {

	@Override
	public WebDriver newDriver() {
		return setChromeMobile();
	}

	@Override
	public boolean takesScreenshots() {
		return true;
	}

	private WebDriver setChromeMobile() {
		Map<String, Object> deviceMetrics = new HashMap<String, Object>();
		deviceMetrics.put("width", 732);
		deviceMetrics.put("height", 412);
		deviceMetrics.put("pixelRatio", 3.0);
		Map<String, Object> mobileEmulation = new HashMap<String, Object>();
		mobileEmulation.put("deviceMetrics", deviceMetrics);
		mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Mobile Safari/537.36");
		Map<String, Object> chromeOptions = new HashMap<String, Object>();
		chromeOptions.put("mobileEmulation", mobileEmulation);
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		return new ChromeDriver(capabilities);
	}
}