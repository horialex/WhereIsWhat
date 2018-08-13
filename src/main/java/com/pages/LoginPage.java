package com.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class LoginPage extends AbstractPage {

    @FindBy(css = "input#email")
    WebElementFacade email;
    
    @FindBy(css = "input#pass")
    WebElementFacade password;
    
    @FindBy(css = "input[class*='signin']")
    WebElementFacade signIn;

    public void enterEmail(String email){
        this.email.sendKeys(email);
    }

    public void enterPassword(String password){
        this.password.sendKeys(password);
    }

    public void submit(){
        signIn.click();
    }
}
