package com.tools.factories;

import com.tools.constants.EnvironmentConstants;
import com.tools.entities.Login;
import com.tools.entities.User;

public class LoginFactory {

    public static Login getLoginInstance() {
        Login login = new Login();
        User user = new User();
        user.setEmail(EnvironmentConstants.USER);
        user.setPassword(EnvironmentConstants.PASS);
        user.setName(EnvironmentConstants.USER_NAME);
        login.setUser(user);

        return login;
    }
}
