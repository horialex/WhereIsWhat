package com.tests;

import com.steps.api.ApiBookingSteps;
import com.steps.api.flow_steps.ApiCreateItemFlowSteps;
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
public class BookReturnedItemTest extends BaseTest {

    @Steps
    ApiCreateItemFlowSteps apiCreateItemFlowSteps;
    @Steps
    ApiBookingSteps apiBookingSteps;
    @Steps
    LoginFlowSteps loginFlowSteps;
    @Steps
    ItemValidationSteps itemValidationSteps;
    @Steps
    BookingFlowSteps bookingFlowSteps;
    @Steps
    PageNavigationFlowSteps pageNavigationFlowSteps;
    @Steps
    HomePageSteps homePageSteps;

    @Before
    public void setUp() {
        apiCreateItemFlowSteps.createItem();
        apiBookingSteps.bookAllItems();
        apiBookingSteps.returnAllBookedItems();
    }

    @Test
    public void bookReturnedItemTest(){
        homePageSteps.navigateToHomePage();
        loginFlowSteps.login();
        bookingFlowSteps.bookAllItemsFromCategory();
        pageNavigationFlowSteps.goToMyBookings();
        itemValidationSteps.validateBookedItems();
    }
}
