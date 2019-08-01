package com.runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = { "src/test/resources/features/" },
				glue = { "com.stepdefinitions" }
		)
public class CucumberRunner {
}
