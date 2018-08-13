package com.pages;

import java.util.ArrayList;
import java.util.List;

import com.tools.constants.EntityConstants;
import com.tools.entities.Item;
import org.openqa.selenium.By;

import com.tools.entities.Booking;
import com.tools.entities.User;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;


public class BookingsPage extends AbstractPage {
	@FindBy(css = "ul[class*='bookings-listing']")
	WebElementFacade itemBookingsContainer;
	@FindBy(css = "button#item-return-item")
	WebElementFacade returnItemConfirmationPopup;
	@FindBy(css="ul[class*='nav-tabs']")
	WebElementFacade headerContainer;


	public void checkBookingCategory(String bookingHeader){
		List<WebElementFacade> headerList = headerContainer.thenFindAll(By.cssSelector("li a"));
		for(WebElementFacade headerElement : headerList){
			String header = headerElement.getText().replace("\"", "").trim();
			if(header.contentEquals(bookingHeader)){
				headerElement.click();
			}
		}
	}

	public void returnItem(String itemName) {
		WebElementFacade selectedBooking = selectBookingToReturn(itemName);
		selectedBooking.find(By.cssSelector("button[class*='return-item']")).click();
		returnItemConfirmationPopup.click();
	}

	public List<Booking> getBookings(List<Booking> bookingList) {
		List<Booking> createdBookings = new ArrayList<>();
		List<WebElementFacade> selectedBookings = selectBookings(bookingList);

		for (WebElementFacade booking : selectedBookings) {
			Booking bookedItem = new Booking();
			String actualItemName = booking
					.find(By.cssSelector("div[class*='booking-details-container'] ul li:nth-child(1) a")).getText().trim();
			String from = booking
					.find(By.cssSelector("div[class*='booking-details-container'] ul li:nth-child(2) span:nth-child(2)")).getText();
			String to = booking
					.find(By.cssSelector("div[class*='booking-details-container'] ul li:nth-child(2) span:nth-child(4)")).getText();
			String userName = booking
					.find(By.cssSelector("div[class*='booking-details-container'] ul li:nth-child(3) a")).getText().trim();

			User user = new User();
			user.setName(userName);
			Item item = new Item();
			item.setTitle(actualItemName);

			bookedItem.setStatus(booking.find(By.cssSelector(".status-label")).getText());
			bookedItem.setStartDate(from);
			bookedItem.setEndDate(to);
			bookedItem.setUser(user);
			bookedItem.setItem(item);

			createdBookings.add(bookedItem);
		}

		return createdBookings;
	}

	public List<WebElementFacade> selectBookings(List<Booking> bookingList) {
		List<WebElementFacade> machedBookings = new ArrayList<>();
		List<WebElementFacade> bookings = itemBookingsContainer
				.thenFindAll(By.cssSelector("div[class*='item-booking-container']"));

		for(Booking b : bookingList) {
			for (WebElementFacade booking : bookings) {
				String actualItemName = booking
						.find(By.cssSelector("div[class*='booking-details-container'] ul li:nth-child(1) a")).getText().trim();
				String from = booking
						.find(By.cssSelector("div[class*='booking-details-container'] ul li:nth-child(2) span:nth-child(2)")).getText();
				if (actualItemName.contentEquals(b.getItem().getTitle()) && from.contentEquals(b.getStartDate())) {
					machedBookings.add(booking);
				}
			}
		}

		return machedBookings;
	}



	public WebElementFacade selectBookingToReturn(String itemName){
		List<WebElementFacade> bookings = itemBookingsContainer
				.thenFindAll(By.cssSelector("div[class*='item-booking-container']"));

		for (WebElementFacade booking : bookings) {
			String actualItemName = booking
					.find(By.cssSelector("div[class*='booking-details-container'] ul li:nth-child(1) a")).getText().trim();
			String status = booking
					.find(By.cssSelector(".status-label")).getText();
			boolean returnButtonIsPresent =  booking.containsElements(By.cssSelector("button[class*='return-item']"));
			if (actualItemName.contentEquals(itemName) && status.contentEquals(EntityConstants.ACCEPTED)
					&& returnButtonIsPresent) {
				return booking;
			}
		}

		return null;
	}
}
