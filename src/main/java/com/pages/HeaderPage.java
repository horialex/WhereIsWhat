package com.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;

import java.util.List;

public class HeaderPage extends AbstractPage {

    @FindBy(css="ul[class='nav navbar-nav views']")
    WebElementFacade tabContainer;

    public void selectHeader(String header){
        List<WebElementFacade> headersList = tabContainer.thenFindAll(By.cssSelector("li a"));
        for (WebElementFacade element : headersList) {
            if(element.getText().contentEquals(header)) {
                element.click();
                break;
            }
        }
    }
}
