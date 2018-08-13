package com.tests;

import com.steps.api.ApiBookingSteps;
import com.steps.api.ApiCategorySteps;
import com.steps.api.ApiItemSteps;
import com.steps.api.ApiLoginSteps;
import com.steps.frontend.BookingSteps;
import com.steps.frontend.HomePageSteps;
import com.steps.frontend.flow_steps.LoginFlowSteps;
import com.steps.frontend.flow_steps.PageNavigationFlowSteps;
import com.steps.validations.ItemValidationSteps;
import com.tests.BaseTest;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class ReturnMultipleItemsTest extends BaseTest {
	@Steps
	LoginFlowSteps loginFlowSteps;
	@Steps
	ItemValidationSteps itemValidationSteps;
	@Steps
	BookingSteps bookingSteps;
	@Steps
	ApiBookingSteps apiBookingSteps;
	@Steps
	HomePageSteps homePageSteps;
	@Steps
	ApiCategorySteps apiCategorySteps;
	@Steps
	ApiItemSteps apiItemSteps;
	@Steps
	ApiLoginSteps apiLoginSteps;
	@Steps
	PageNavigationFlowSteps pageNavigationFlowSteps;

	@Before
	public void setUp() {
		apiLoginSteps.loginAsAdmin();
		apiCategorySteps.createCategory();
		apiItemSteps.createItem();
		apiCategorySteps.createCategory();
		apiItemSteps.createItem();
		apiBookingSteps.bookAllItems();
	}

	@Test
	public void returnMultipleItemsTest() {
		homePageSteps.navigateToHomePage();
		loginFlowSteps.login();
		pageNavigationFlowSteps.goToMyBookings();
		bookingSteps.returnAllItems();
		itemValidationSteps.validateItemsAreReturned();
	}
}
