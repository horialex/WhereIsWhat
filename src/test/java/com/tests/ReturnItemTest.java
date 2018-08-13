package com.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.api.ApiBookingSteps;
import com.steps.api.flow_steps.ApiCreateItemFlowSteps;
import com.steps.frontend.BookingSteps;
import com.steps.frontend.HomePageSteps;
import com.steps.frontend.flow_steps.LoginFlowSteps;
import com.steps.frontend.flow_steps.PageNavigationFlowSteps;
import com.steps.validations.ItemValidationSteps;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class ReturnItemTest extends BaseTest {
	
	@Steps
	LoginFlowSteps loginFlowSteps;
	@Steps
	ItemValidationSteps itemValidationSteps;
	@Steps
	BookingSteps bookingSteps;
	@Steps
	ApiCreateItemFlowSteps apiCreateItemFlowSteps;
	@Steps
	ApiBookingSteps apiBookingSteps;
	@Steps
	HomePageSteps homePageSteps;
	@Steps
	PageNavigationFlowSteps pageNavigationFlowSteps;

	@Before
	public void setUp() {
		apiCreateItemFlowSteps.createItem();
		apiBookingSteps.bookAllItems();
	}

	@Test
	public void returnItemTest() {
		homePageSteps.navigateToHomePage();
		loginFlowSteps.login();
		pageNavigationFlowSteps.goToMyBookings();
		bookingSteps.returnAllItems();
		itemValidationSteps.validateItemsAreReturned();
	}
}
