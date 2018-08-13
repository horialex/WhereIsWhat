package com.steps.frontend;

import com.pages.LoginPage;
import com.steps.AbstractSteps;
import com.tools.constants.SerenityKeyConstants;
import com.tools.entities.User;
import com.tools.utils.SerenitySessionUtils;

import net.thucydides.core.annotations.Step;

public class LoginSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	LoginPage loginPage;

	@Step
	public void login() {
		User user = SerenitySessionUtils.getFromSession(SerenityKeyConstants.USER);
		loginPage.enterEmail(user.getEmail());
		loginPage.enterPassword(user.getPassword());
		loginPage.submit();
	}
}
