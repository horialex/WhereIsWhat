package com.steps.frontend;

import com.pages.UsersPage;
import com.steps.AbstractSteps;
import com.tools.constants.SerenityKeyConstants;
import com.tools.entities.User;
import com.tools.utils.SerenitySessionUtils;

import net.thucydides.core.annotations.Step;

public class UsersSteps extends AbstractSteps{

	private static final long serialVersionUID = 1L;
	
	UsersPage usersPage;

    @Step
    public void selectUser(){
        User user = SerenitySessionUtils.getFromSession(SerenityKeyConstants.USER);
        searchUser(user.getName());
        usersPage.selectUser(user.getName());
    }

    @Step
    public void searchUser(String userName){
        usersPage.searchUser(userName);
    }

}
