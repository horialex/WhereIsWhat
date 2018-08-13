package com.steps.api.flow_steps;

import com.steps.api.ApiCategorySteps;
import com.steps.api.ApiItemSteps;
import com.steps.api.ApiLoginSteps;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Steps;

public class ApiCreateItemFlowSteps {
    @Steps
    ApiLoginSteps apiLoginSteps;
    @Steps
    ApiCategorySteps apiCategorySteps;
    @Steps
    ApiItemSteps apiItemSteps;

    @StepGroup
    public void createItem(){
        apiLoginSteps.loginAsAdmin();
        apiCategorySteps.createCategory();
        apiItemSteps.createItem();
    }

}
