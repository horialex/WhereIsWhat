package com.steps.api;

import java.util.List;

import com.tools.constants.ApiUrlConstants;
import com.tools.constants.DateConstants;
import com.tools.constants.SerenityKeyConstants;
import com.tools.entities.Booking;
import com.tools.entities.Item;
import com.tools.factories.BookingFactory;
import com.tools.utils.DateFormatter;
import com.tools.utils.InstanceUtils;
import com.tools.utils.SerenitySessionUtils;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

public class ApiBookingSteps extends AbstractApiSteps {

	private static final long serialVersionUID = 1L;

	@StepGroup
	public void bookAllItems() {
		List<Item> items = SerenitySessionUtils.getFromSession(SerenityKeyConstants.ITEMS);
		for (Item item : items) {
			SerenitySessionUtils.putOnSession(SerenityKeyConstants.ITEM, item);
			bookItem();
		}
	}

	public void bookItems(List<Item> items) {
		for (Item item : items) {
			SerenitySessionUtils.putOnSession(SerenityKeyConstants.ITEM, item);
			bookItem();
		}
	}

	@Step
	public void bookItem() {
		Booking bookingRequest = BookingFactory.getApiBookingInstance();
		Booking bookingResponse = createResource(ApiUrlConstants.BOOKINGS, bookingRequest, Booking.class);

		bookingRequest = (Booking) InstanceUtils.mergeObjects(bookingRequest, bookingResponse);
		SerenitySessionUtils.saveObjectListInSerenitySession(SerenityKeyConstants.BOOKINGS, bookingRequest);
	}

	// TODO trebe sa se bazeze pe lista de items booked.
	// sunt 2 posibilitati, ori avem doar un booking unic in lista de bookings
	// pt fiecare item,
	// ori ne bazam pe o lista de items bookuiti.
	@StepGroup
	public void returnAllBookedItems() {
		List<Booking> bookings = SerenitySessionUtils.getFromSession(SerenityKeyConstants.BOOKINGS);
		for (Booking booking : bookings) {
			SerenitySessionUtils.putOnSession(SerenityKeyConstants.BOOKING, booking);
			// eu as scoate conditia asta, avand in vedere ca ma astept ca
			// bookingurile mele sa poata fi returnate, eu le-am creat si ma
			// astept sa fie acolo.Daca
			// booking.isCanReturn() == false vreau sa crape, ceva nu e in
			// regula cu ea, e marcata ca returnata sau ceva si nu ar trebui
			if (booking.isCanReturn() == true)
				returnBookedItem();
		}
	}

	// TODO trebe sa se bazeze pe lista de items booked.
	// sunt 2 posibilitati, ori avem doar un booking unic in lista de bookings
	// pt fiecare item,
	// ori ne bazam pe o lista de items bookuiti.
	@StepGroup
	public void returnBookedItems(List<Booking> bookings) {
		for (Booking booking : bookings) {
			SerenitySessionUtils.putOnSession(SerenityKeyConstants.BOOKING, booking);
			// eu as scoate conditia asta, avand in vedere ca ma astept ca
			// bookingurile mele sa poata fi returnate, eu le-am creat si ma
			// astept sa fie acolo.Daca
			// booking.isCanReturn() == false vreau sa crape, ceva nu e in
			// regula cu ea, e marcata ca returnata sau ceva si nu ar trebui
			if (booking.isCanReturn() == true)
				returnBookedItem();
		}
	}

	@Step
	public void returnBookedItem() {
		Booking bookingReturnRequest = BookingFactory.getApiBookingReturnInstance();
		Booking bookingReturnResponse = updateResource(ApiUrlConstants.RETURN, bookingReturnRequest, Booking.class,
				bookingReturnRequest.getId());
		bookingReturnRequest.setStatus(bookingReturnResponse.getStatus());
		bookingReturnRequest.setEndDate(
				DateFormatter.formatStringDate(bookingReturnResponse.getReturnDate(), DateConstants.WW_PATTERN));
	}
}
