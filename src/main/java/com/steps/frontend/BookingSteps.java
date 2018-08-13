package com.steps.frontend;

import java.util.List;

import com.pages.BookingsPage;
import com.steps.AbstractSteps;
import com.tools.constants.DateConstants;
import com.tools.constants.EntityConstants;
import com.tools.constants.SerenityKeyConstants;
import com.tools.entities.Booking;
import com.tools.utils.DateFormatter;
import com.tools.utils.DateUtils;
import com.tools.utils.SerenitySessionUtils;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Title;

public class BookingSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	BookingsPage bookingsPage;

	@StepGroup
	public void returnAllItems() {
		List<Booking> bookings = SerenitySessionUtils.getFromSession(SerenityKeyConstants.BOOKINGS);
		for (Booking booking : bookings) {
			if (booking.getStatus().equalsIgnoreCase(EntityConstants.ACCEPTED))
				returnItem(booking);
		}
	}

	@StepGroup
	public void returnItems(List<Booking> bookings) {
		for (Booking booking : bookings) {
			returnItem(booking);
		}
	}

	@Title("Return item")
	@Step
	public void returnItem(Booking booking) {
		bookingsPage.returnItem(booking.getItem().getTitle());
		booking.setEndDate(DateFormatter.formatDate(DateUtils.getCurrentDate(), DateConstants.WW_PATTERN));
		booking.setStatus(EntityConstants.COMPLETED);
	}

	@Step
	public void checkBookings(String bookingCategory) {
		bookingsPage.checkBookingCategory(bookingCategory);
	}
}
