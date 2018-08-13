package com.tools.factories;

import com.tools.constants.SerenityKeyConstants;
import com.tools.entities.Category;
import com.tools.entities.Item;
import com.tools.utils.FieldGenerator;
import com.tools.utils.SerenitySessionUtils;

public class ItemFactory {

	public static Item geItemInstance() {
		Category category = SerenitySessionUtils.getFromSession(SerenityKeyConstants.CATEGORY);
		Item item = new Item();
		item.setTitle("Item - " + FieldGenerator.generateStringValue(6, FieldGenerator.TypeOfString.ALPHANUMERIC));
		item.setCustomFields(new Object[0]);
		item.setCategoryId(category.getId());
		item.setCategory(category);

		return item;
	}
}
