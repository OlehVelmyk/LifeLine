package com.scenario_projects.lifeline_front_stage.tests;

import com.scenario_projects.lifeline_front_stage.pages.LoginPage;
import com.scenario_projects.lifeline_front_stage.pages.ResetPasswordPage;
import com.scenario_projects.lifeline_front_stage.utils.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.Test;

public class N_0003_BackToLoginPageTest extends BaseTest {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void backToLoginTest() {
        driver.get(baseUrl);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickResetPasswordButton();

        ResetPasswordPage resetPasswordPage = new ResetPasswordPage(driver);
        resetPasswordPage.clickBackToLoginButton();

        Assert.assertNotNull(loginPage.resetPasswordButtonIsPresent(), "Failed! Login page is not loaded!");
    }
}
