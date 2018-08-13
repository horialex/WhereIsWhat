package com.steps.frontend;

import com.pages.CategoriesPage;
import com.steps.AbstractSteps;

import net.thucydides.core.annotations.Step;

public class CategorySteps extends AbstractSteps {
    
	private static final long serialVersionUID = 1L;
	
	CategoriesPage categoriesPage;

    @Step
    public void selectCategory(String categoryName){
        categoriesPage.selectCategory(categoryName);
    }

    @Step
    public void searchItem(String itemName){
        categoriesPage.searchItem(itemName);
    }


}
