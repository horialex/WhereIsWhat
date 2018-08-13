package com.tests;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.api.ApiBookingSteps;
import com.steps.api.ApiCategorySteps;
import com.steps.api.ApiItemSteps;
import com.steps.api.ApiLoginSteps;
import com.steps.api.flow_steps.ApiCreateItemFlowSteps;
import com.steps.frontend.flow_steps.BookingFlowSteps;
import com.steps.frontend.flow_steps.LoginFlowSteps;
import com.steps.validations.ItemValidationSteps;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class ApiBookItemTest extends BaseTest {
	@Steps
	ApiLoginSteps apiLoginStepsSteps;
	@Steps
	ApiCategorySteps apiCategoryStepsSteps;
	@Steps
	ApiItemSteps apiItemsStepsSteps;
	@Steps
	ApiBookingSteps apiBookingStepsSteps;
	@Steps
	LoginFlowSteps loginFlowSteps;
	@Steps
	ItemValidationSteps itemValidationSteps;
	@Steps
	BookingFlowSteps bookingFlowSteps;
	@Steps
	ApiCreateItemFlowSteps apiCreateItemFlowSteps;
	@Steps
	ApiBookingSteps apiBookingSteps;

	@Test
	public void apiBookItem() throws IllegalAccessException, InstantiationException {
		apiLoginStepsSteps.loginAsAdmin();
		apiCategoryStepsSteps.createCategory();
		apiItemsStepsSteps.createItem();
		apiBookingSteps.bookAllItems();
	}
}
