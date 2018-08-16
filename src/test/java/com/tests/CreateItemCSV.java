package com.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.api.ApiCategorySteps;
import com.steps.api.ApiItemSteps;
import com.steps.api.ApiLoginSteps;
import com.steps.frontend.HomePageSteps;
import com.steps.frontend.flow_steps.ItemFlowSteps;
import com.steps.frontend.flow_steps.LoginFlowSteps;
import com.steps.validations.ItemValidationSteps;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class CreateItemCSV extends BaseTest {

	@Steps
	ApiLoginSteps apiLoginSteps;
	@Steps
	ApiCategorySteps apiCategorySteps;
	@Steps
	public ApiItemSteps apiItemsSteps;
	@Steps
	LoginFlowSteps loginFlowSteps;
	@Steps
	ItemValidationSteps itemValidationSteps;
	@Steps
	HomePageSteps homePageSteps;
	@Steps
	ItemFlowSteps itemFlowSteps;

	@Before
	public void setup() {
		apiLoginSteps.loginAsAdmin();
		apiCategorySteps.createCategory();
	}

	@Test
	public void createItemCSV() throws Exception {
		apiItemsSteps.createItemCSV();
		homePageSteps.navigateToHomePage();
		loginFlowSteps.login();
		itemFlowSteps.verifyItemsAreCreated();
	}

}
