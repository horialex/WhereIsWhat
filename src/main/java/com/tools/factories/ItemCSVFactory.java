package com.tools.factories;

import com.tools.entities.ItemCsv;
import com.tools.utils.FieldGenerator;

public class ItemCSVFactory {
	
	public static ItemCsv geItemCSVInstance() {
		ItemCsv itemCsv = new ItemCsv();
		
		itemCsv.setItemTag("Item-tag - " + FieldGenerator.generateStringValue(3, FieldGenerator.TypeOfString.ALPHANUMERIC));
		itemCsv.setDescription("Item-tag - " + FieldGenerator.generateStringValue(10, FieldGenerator.TypeOfString.ALPHA));
		
		return itemCsv;
	}

}
