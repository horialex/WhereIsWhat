package com.tests;

import com.steps.frontend.HomePageSteps;
import com.steps.frontend.flow_steps.LoginFlowSteps;
import com.steps.frontend.flow_steps.PageNavigationFlowSteps;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.api.flow_steps.ApiCreateItemFlowSteps;
import com.steps.frontend.flow_steps.BookingFlowSteps;
import com.steps.validations.ItemValidationSteps;
import com.tests.BaseTest;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class BookItemTest extends BaseTest {
	@Steps
	LoginFlowSteps loginFlowSteps;
	@Steps
	ItemValidationSteps itemValidationSteps;
	@Steps
    BookingFlowSteps bookingFlowSteps;
	@Steps
	ApiCreateItemFlowSteps apiCreateItemFlowSteps;
	@Steps
	HomePageSteps homePageSteps;
	@Steps
	PageNavigationFlowSteps pageNavigationFlowSteps;

	@Before
	public void setUp() {
		apiCreateItemFlowSteps.createItem();
	}

	@Test
	public void bookItemTest(){
		homePageSteps.navigateToHomePage();
		loginFlowSteps.login();
		bookingFlowSteps.bookAllItemsFromCategory();
		pageNavigationFlowSteps.goToMyBookings();
		itemValidationSteps.validateBookedItems();
	}
}
