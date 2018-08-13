package com.tests;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.api.ApiCategorySteps;
import com.steps.api.ApiItemSteps;
import com.steps.api.ApiLoginSteps;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class CreateItemTest extends BaseTest {
    @Steps
    public ApiLoginSteps apiLoginStepsSteps;
    @Steps
    public ApiCategorySteps apiCategoryStepsSteps;
    @Steps
    public ApiItemSteps apiItemsStepsSteps;

    @Test
    public void createCategoryItem() throws IllegalAccessException, InstantiationException {
        apiLoginStepsSteps.loginAsAdmin();
        apiCategoryStepsSteps.createCategory();
        apiItemsStepsSteps.createItem();
        
        
    }
}