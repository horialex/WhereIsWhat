package com.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;

import java.util.List;

public class UsersPage extends AbstractPage {
	@FindBy(css = "div[class*='list-container'] ul")
	WebElementFacade usersContainer;
	@FindBy(css="input#name")
	WebElementFacade searchBar;


	public void selectUser(String userName) {
		WebElementFacade selectedUser = getUser(userName);
		selectedUser.find(By.cssSelector("h3 a")).click();
	}

	public WebElementFacade getUser(String userName) {
		List<WebElementFacade> usersList = usersContainer.thenFindAll(By.cssSelector("li"));
		for (WebElementFacade user : usersList) {
			if (user.find(By.cssSelector("h3 a")).getText().trim().contentEquals(userName)) {
				return user;
			}
		}

		return null;
	}

	public void searchUser(String userName){
		searchBar.click();
		searchBar.sendKeys(userName);
	}
}
