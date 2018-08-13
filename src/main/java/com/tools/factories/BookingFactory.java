package com.tools.factories;

import com.tools.constants.DateConstants;
import com.tools.constants.EntityConstants;
import com.tools.constants.SerenityKeyConstants;
import com.tools.entities.Booking;
import com.tools.entities.Item;
import com.tools.entities.User;
import com.tools.utils.DateFormatter;
import com.tools.utils.DateUtils;
import com.tools.utils.SerenitySessionUtils;

public class BookingFactory {
	public static Booking getBookingInstance() {
		User sessionUser = SerenitySessionUtils.getFromSession(SerenityKeyConstants.USER);
		Item item = SerenitySessionUtils.getFromSession(SerenityKeyConstants.ITEM);
		Booking booking = new Booking();

		booking.setStatus(EntityConstants.ACCEPTED);
		booking.setStartDate(DateFormatter.formatDate(DateUtils.addHoursToCurrentDate(2), DateConstants.WW_PATTERN));
		booking.setEndDate(DateFormatter.formatDate(DateUtils.addHoursToCurrentDate(3), DateConstants.WW_PATTERN));
		booking.setItem(item);
		booking.setUser(sessionUser);

		return booking;
	}

	public static Booking getApiBookingInstance() {
		Item item = SerenitySessionUtils.getFromSession(SerenityKeyConstants.ITEM);
		User userRequest = SerenitySessionUtils.getFromSession(SerenityKeyConstants.USER);
		Booking booking = new Booking();
		booking.setItemId(item.getId());
		booking.setUserId(userRequest.getId());
		booking.setItem(item);
		booking.setUser(userRequest);
		booking.setStartDate(DateFormatter.formatDate(DateUtils.getCurrentDate(), DateConstants.WW_PATTERN));
		booking.setEndDate(DateFormatter.formatDate(DateUtils.addHoursToCurrentDate(1), DateConstants.WW_PATTERN));
		booking.setClientTime(DateFormatter.formatDate(DateUtils.getCurrentDate(), DateConstants.WW_PATTERN));

		return booking;
	}

	public static Booking getApiBookingReturnInstance(){
		Booking booking = SerenitySessionUtils.getFromSession(SerenityKeyConstants.BOOKING);
		booking.setReturnItem(true);

		return booking;
	}
}
