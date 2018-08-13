package com.steps.frontend;

import com.pages.ItemsPage;
import com.steps.AbstractSteps;
import com.tools.constants.SerenityKeyConstants;
import com.tools.entities.Booking;
import com.tools.factories.BookingFactory;
import com.tools.utils.SerenitySessionUtils;

import net.thucydides.core.annotations.Step;

public class ItemSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	ItemsPage itemsPage;

	@Step
	public void bookItem(String itemTitle){
		Booking booking = BookingFactory.getBookingInstance();
		itemsPage.clickBookItem(itemTitle);
		itemsPage.selectStartDate(booking.getStartDate());
		itemsPage.selectStartHour(booking.getStartDate());
		itemsPage.selectEndDate(booking.getEndDate());
		itemsPage.selectEndHour(booking.getEndDate());
		itemsPage.saveBooking();

		SerenitySessionUtils.saveObjectListInSerenitySession(SerenityKeyConstants.BOOKINGS, booking);
	}
}
