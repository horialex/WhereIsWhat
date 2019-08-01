package com.stepdefinitions;

import java.nio.charset.Charset;
import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Assert;

import com.steps.api.flow_steps.ApiCreateItemFlowSteps;
import com.steps.frontend.HomePageSteps;
import com.steps.frontend.flow_steps.LoginFlowSteps;

import cucumber.api.java.en.Given;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

public class StepDefinitions {

	@Steps
	HomePageSteps homePageSteps;

	@Steps
	LoginFlowSteps loginFlowSteps;
	@Steps
	ApiCreateItemFlowSteps apiCreateItemFlowSteps;

	@Given("^Login$")
	public void stepA() throws InterruptedException {
		System.out.println("Thread ID " + Thread.currentThread().getId());
		homePageSteps.getDriver().get("https://gamespot.com");
		homePageSteps.getDriver().manage().window().maximize();
		Thread.sleep(1000);
		int length = 10;
		boolean useLetters = true;
		boolean useNumbers = false;
		String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
		System.out.println("STRING IS " + generatedString);
		Serenity.setSessionVariable("name").to(generatedString);
		// apiCreateItemFlowSteps.createItem();
		// homePageSteps.navigateToHomePage();
		// loginFlowSteps.login();
	}

	@Given("^Step definition B$")
	public void stepB() throws InterruptedException {
		System.out.println("*******************************");
		String currentUserName = Serenity.sessionVariableCalled("name");
		System.out.println("Thread name: " + Thread.currentThread().getId() + " : " + currentUserName);
		System.out.println("*******************************");
		Thread.sleep(200);
		System.out.println("Step B");
	}

	@Given("^Step definition C$")
	public void stepC() throws InterruptedException {
		System.out.println("*******************************");
		String currentUserName = Serenity.sessionVariableCalled("name");
		System.out.println("Thread name: " + Thread.currentThread().getId() + " : " + currentUserName);
		System.out.println("*******************************");
		Thread.sleep(1000);
		System.out.println("Step C");

	}

}
