package com.scenario_projects.lifeline_front_stage.tests;

import com.scenario_projects.lifeline_front_stage.actionHelpers.LoginHelper;
import com.scenario_projects.lifeline_front_stage.dataProvider.LoginDataProvider;
import com.scenario_projects.lifeline_front_stage.utils.RetryAnalyzer;
import org.testng.annotations.Test;

public class N_0001_LoginTest extends BaseTest {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void loginTest() {
        driver.get(baseUrl);

        LoginHelper login = new LoginHelper(driver);
        login.login(LoginDataProvider.email, LoginDataProvider.password);
    }
}
