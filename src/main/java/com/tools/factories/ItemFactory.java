package com.tools.factories;

import java.util.ArrayList;
import java.util.List;

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
		
		item.setName(null);
		item.setItemTag(null);
		item.setDescription(null);
		item.setCategoryTitle(null);

		return item;
	}
	
	
	public static Item geItemCSVInstance() {
		Category category = SerenitySessionUtils.getFromSession(SerenityKeyConstants.CATEGORY);
		Item item = new Item();
		item.setTitle("Item - " + FieldGenerator.generateStringValue(6, FieldGenerator.TypeOfString.ALPHANUMERIC));
		item.setCustomFields(new Object[0]);
		item.setCategoryId(category.getId());
		item.setCategory(category);
		
		item.setName(item.getTitle());
		item.setItemTag(item.getTitle() + "_tag");
		item.setDescription("Test item description");
		item.setCategoryTitle(category.getName());

		return item;
	}
	
	public static List<Item> getItemCSVInstantceList(int numberOfItems){
		Category category = SerenitySessionUtils.getFromSession(SerenityKeyConstants.CATEGORY);
		List<Item> items = new ArrayList<Item>();
		
		for(int i=0; i<numberOfItems; i++) {
			Item item = new Item();
			item.setTitle("Item - " + FieldGenerator.generateStringValue(6, FieldGenerator.TypeOfString.ALPHANUMERIC));
			item.setCustomFields(new Object[0]);
			item.setCategoryId(category.getId());
			item.setCategory(category);
			
			item.setName(item.getTitle());
			item.setItemTag(item.getTitle() + "_tag");
			item.setDescription("Test item description");
			item.setCategoryTitle(category.getName());
			items.add(item);
		}
		return items;
	}
	
	
}
