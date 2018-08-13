package com.steps.frontend.flow_steps;

import com.steps.frontend.BookingSteps;
import com.steps.frontend.HeaderSteps;

import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Steps;

public class PageNavigationFlowSteps {
    @Steps
    BookingSteps bookingSteps;
    @Steps
    HeaderSteps headerSteps;


    @StepGroup
    public void goToMyBookings(){
        headerSteps.selectHeader("BOOKINGS");
        bookingSteps.checkBookings("My Bookings");
    }
}
