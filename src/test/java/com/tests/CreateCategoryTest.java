package com.tests;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.api.ApiCategorySteps;
import com.steps.api.ApiLoginSteps;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class CreateCategoryTest extends BaseTest {
    @Steps
    public ApiLoginSteps apiLoginStepsSteps;
    @Steps
    ApiCategorySteps apiCategoryStepsSteps;
    

    @Test
    public void createCategory() throws Exception {
        apiLoginStepsSteps.loginAsAdmin();
        apiCategoryStepsSteps.removeAllCategories();
        apiCategoryStepsSteps.createCategory();
    }
}