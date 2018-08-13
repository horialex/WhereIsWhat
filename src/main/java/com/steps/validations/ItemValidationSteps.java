package com.steps.validations;

import java.util.List;

import com.pages.BookingsPage;
import com.tools.constants.SerenityKeyConstants;
import com.tools.entities.Booking;
import com.tools.utils.SerenitySessionUtils;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

public class ItemValidationSteps {
	BookingsPage bookingsPage;

	@Steps
	SoftValidation softValidation;

	@Step
	public void validateBookedItems() {
		List<Booking> expectedBookings = SerenitySessionUtils.getFromSession(SerenityKeyConstants.BOOKINGS);
		List<Booking> actualdBookings = bookingsPage.getBookings(expectedBookings);
		validateBookings(expectedBookings, actualdBookings);
	}

	@Step
	public void validateItemsAreReturned() {
		List<Booking> expectedBookings = SerenitySessionUtils.getFromSession(SerenityKeyConstants.BOOKINGS);
		List<Booking> actualdBookings = bookingsPage.getBookings(expectedBookings);
		validateBookings(expectedBookings, actualdBookings);
	}

	private void validateBookings(List<Booking> expectedBookings, List<Booking> actualdBookings) {
		int counter = 0;
		for (Booking expectedBooking : expectedBookings) {

			Booking actualBooking = findBookingInList(expectedBooking, actualdBookings);

			SoftValidation.verifyStringValuesIgnoreCase("status", expectedBooking.getStatus(),
					actualBooking.getStatus());
			SoftValidation.verifyStringValues("end date", expectedBooking.getEndDate(), actualBooking.getEndDate());
			SoftValidation.verifyStringValues("start date", expectedBooking.getStartDate(),
					actualBooking.getStartDate());
			SoftValidation.verifyStringValues("user", expectedBooking.getUser().getName(),
					actualBooking.getUser().getName());
			SoftValidation.verifyStringValues("item", expectedBooking.getItem().getTitle(),
					actualBooking.getItem().getTitle());
			//todo fa ceva cu counter ca l-ai stricat. @mihai
			counter++;
		}
		SoftValidation.verifyIntValues("no. of validations vs booking list size:", counter, expectedBookings.size());
		SoftValidation.verifyIntValues("booking list size:", actualdBookings.size(), expectedBookings.size());
		softValidation.printErrors();
	}

	private Booking findBookingInList(Booking searchedBooking, List<Booking> bookings) {
		for (Booking booking : bookings) {
			if ((booking.getItem().getTitle().contentEquals(searchedBooking.getItem().getTitle())
					&& (booking.getStartDate().contentEquals(searchedBooking.getStartDate())))) {
				return booking;
			}
		}
		return null;
	}
  
}
