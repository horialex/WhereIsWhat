package com.steps.frontend.flow_steps;

import java.util.List;

import com.steps.frontend.CategorySteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.ItemSteps;
import com.steps.frontend.UsersSteps;
import com.tools.constants.SerenityKeyConstants;
import com.tools.entities.Item;
import com.tools.utils.SerenitySessionUtils;

import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Steps;

public class BookingFlowSteps {
	@Steps
	HeaderSteps headerSteps;
	@Steps
	CategorySteps categorySteps;
	@Steps
	ItemSteps itemSteps;
	@Steps
	UsersSteps usersSteps;

	// TODO inca o metoda care sa ia ca param lista de itemi care vor fi booked pentru a putea controla asta
	@StepGroup
	public void bookAllItemsFromCategory() {
		List<Item> items = SerenitySessionUtils.getFromSession(SerenityKeyConstants.ITEMS);
		for (Item item : items) {
			SerenitySessionUtils.putOnSession(SerenityKeyConstants.ITEM, item);
			headerSteps.selectHeader("ITEMS");
			categorySteps.selectCategory(item.getCategory().getName());
			itemSteps.bookItem(item.getTitle());
		}
	}

	// TODO inca o metoda care sa ia ca param lista de itemi care vor fi booked pentru a putea controla asta
	@StepGroup
	public void bookAllItemFromSearchResults() {
		List<Item> items = SerenitySessionUtils.getFromSession(SerenityKeyConstants.ITEMS);
		headerSteps.selectHeader("ITEMS");
		for (Item item : items) {
			categorySteps.searchItem(item.getTitle());
			itemSteps.bookItem(item.getTitle());
		}
	}
}
