package com.scenario_projects.lifeline_front_stage.actionHelpers;

import com.scenario_projects.lifeline_front_stage.pages.DashBoardPage;
import com.scenario_projects.lifeline_front_stage.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginHelper {
    protected WebDriver driver;

    public LoginHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String login, String password) {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillLogin(login);

        loginPage.fillPassword(password);

        loginPage.clickLogInButton();

        DashBoardPage dashBoardPage = new DashBoardPage(driver);
        Assert.assertTrue(dashBoardPage.blocksIsPresent(), "Failed! Dashboard page is not loaded!");
    }
}
