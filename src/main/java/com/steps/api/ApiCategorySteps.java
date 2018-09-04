package com.steps.api;

import java.util.Arrays;
import java.util.List;

import com.tools.constants.ApiUrlConstants;
import com.tools.constants.SerenityKeyConstants;
import com.tools.entities.CategoriesCollection;
import com.tools.entities.Category;
import com.tools.factories.CategoryFactory;
import com.tools.utils.InstanceUtils;
import com.tools.utils.SerenitySessionUtils;

import net.thucydides.core.annotations.Step;

public class ApiCategorySteps extends AbstractApiSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void createCategory() {
		Category categoryRequest = CategoryFactory.getCategoryInstance();
		Category categoryResponse = createResource(ApiUrlConstants.CATEGORIES, categoryRequest, Category.class);

		categoryRequest = (Category) InstanceUtils.mergeObjects(categoryRequest, categoryResponse);
		SerenitySessionUtils.putOnSession(SerenityKeyConstants.CATEGORY, categoryRequest);
	}
	
	@Step
	public void removeAllCategories() {
		CategoriesCollection[] categories = getResource(ApiUrlConstants.CATEGORIES + "?perPage=9999", CategoriesCollection[].class);
		List<CategoriesCollection> pojoList = Arrays.asList(categories);
		pojoList.forEach(s -> {
			deleteResource(ApiUrlConstants.CATEGORIES,  s.getId());
		});
		
	}
}
