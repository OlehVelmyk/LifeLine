package com.scenario_projects.lifeline_front_stage.tests;

import com.scenario_projects.lifeline_front_stage.pages.LoginPage;
import com.scenario_projects.lifeline_front_stage.utils.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.Test;

public class N_0008_VisibilityPasswordOnLoginPageTest extends BaseTest {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void checkVisibilityPassword() {

        driver.get(baseUrl);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickVisibleIcon();

        Assert.assertNotNull(loginPage.textTypeFieldIsPresent(), "Password isn't visible!");
    }
}
