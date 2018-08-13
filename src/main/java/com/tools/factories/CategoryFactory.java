package com.tools.factories;

import com.tools.entities.Category;
import com.tools.utils.FieldGenerator;

public class CategoryFactory {

    public static Category getCategoryInstance() {
        Category category = new Category();
        category.setName("Category - " + FieldGenerator.generateStringValue(6, FieldGenerator.TypeOfString.ALPHANUMERIC));

        return category;
    }
}
