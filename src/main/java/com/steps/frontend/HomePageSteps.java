package com.steps.frontend;

import com.pages.HomePage;
import com.steps.AbstractSteps;
import com.tools.constants.EnvironmentConstants;
import net.thucydides.core.annotations.Step;

public class HomePageSteps extends AbstractSteps {
	
	private static final long serialVersionUID = 1L;
	
	HomePage homePage;

    @Step
    public void clickLogin(){
        homePage.clickLogin();
    }

    public void navigateToHomePage(){
        getDriver().get(EnvironmentConstants.BASE_URL);
        getDriver().manage().window().maximize();
    }
}
