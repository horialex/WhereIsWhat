package com.steps.frontend.flow_steps;

import java.util.List;

import com.steps.frontend.CategorySteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.ItemSteps;
import com.tools.constants.SerenityKeyConstants;
import com.tools.entities.Item;
import com.tools.utils.SerenitySessionUtils;

import net.thucydides.core.annotations.Steps;

public class ItemFlowSteps {
	
	@Steps
	HeaderSteps headerSteps;
	@Steps
	CategorySteps categorySteps;
	@Steps
	ItemSteps itemSteps;
	
	public void verifyItemsAreCreated() {
		  List<Item> items = SerenitySessionUtils.getFromSession(SerenityKeyConstants.ITEMS);
	      items.forEach(s -> {
	    	  headerSteps.selectHeader("ITEMS");
	    	  categorySteps.selectCategory(s.getCategory().getName()); 
	    	  itemSteps.verifyItemIsPresent(s.getTitle());
	      });
	}

}
