package com.steps.frontend.flow_steps;

import com.steps.frontend.HomePageSteps;
import com.steps.frontend.LoginSteps;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

public class LoginFlowSteps {
    @Steps
    HomePageSteps homePageSteps;
    @Steps
    LoginSteps loginSteps;

    @Step
    public void login(){
        homePageSteps.clickLogin();
        loginSteps.login();
    }
}
