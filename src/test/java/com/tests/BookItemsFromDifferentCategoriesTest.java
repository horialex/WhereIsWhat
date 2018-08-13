package com.tests;

import com.steps.api.ApiCategorySteps;
import com.steps.api.ApiItemSteps;
import com.steps.api.ApiLoginSteps;
import com.steps.frontend.HomePageSteps;
import com.steps.frontend.flow_steps.BookingFlowSteps;
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
public class BookItemsFromDifferentCategoriesTest extends BaseTest {
	@Steps
	LoginFlowSteps loginFlowSteps;
	@Steps
	ItemValidationSteps itemValidationSteps;
	@Steps
    BookingFlowSteps bookingFlowSteps;
	@Steps
	ApiLoginSteps apiLoginSteps;
	@Steps
	ApiCategorySteps apiCategorySteps;
	@Steps
    ApiItemSteps apiItemSteps;
	@Steps
	HomePageSteps homePageSteps;
	@Steps
	PageNavigationFlowSteps pageNavigationFlowSteps;

	@Before
	public void setUp() {
		apiLoginSteps.loginAsAdmin();
		apiCategorySteps.createCategory();
		apiItemSteps.createItem();
		apiCategorySteps.createCategory();
		apiItemSteps.createItem();
	}

	@Test
	public void bookItemsFromDifferentCategoriesTest(){
		homePageSteps.navigateToHomePage();
		loginFlowSteps.login();
		bookingFlowSteps.bookAllItemsFromCategory();
		pageNavigationFlowSteps.goToMyBookings();
		itemValidationSteps.validateBookedItems();
	}
}
