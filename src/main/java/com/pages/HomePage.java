package com.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class HomePage extends AbstractPage {
	
    @FindBy(css = ".sign-in-a")
    WebElementFacade loginButton;

    public void clickLogin(){
        loginButton.click();
    }
}
