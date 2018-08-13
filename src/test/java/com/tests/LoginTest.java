package com.tests;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.api.ApiLoginSteps;
import com.tests.BaseTest;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class LoginTest extends BaseTest {
    @Steps
    public ApiLoginSteps apiLoginStepsSteps;

    @Test
    public void loginAsAdmin() {
       apiLoginStepsSteps.loginAsAdmin();
    }

} 