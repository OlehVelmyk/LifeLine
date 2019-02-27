package com.scenario_projects.lifeline_front_stage.tests;

import com.scenario_projects.lifeline_front_stage.actionHelpers.LoginHelper;
import com.scenario_projects.lifeline_front_stage.logging.CustomReporter;
import com.scenario_projects.lifeline_front_stage.pages.EmailPage;
import com.scenario_projects.lifeline_front_stage.pages.LoginPage;
import com.scenario_projects.lifeline_front_stage.pages.NewPasswordPage;
import com.scenario_projects.lifeline_front_stage.pages.ResetPasswordPage;
import com.scenario_projects.lifeline_front_stage.utils.RetryAnalyzer;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class N_0002_ResetPasswordTest extends BaseTest {
    private final String email = "testspro123@gmail.com";
    private final String googlePassword = "80637115010";
    private String newPasswordPage;
    private final String newPassword = "Qwerty123!" + System.currentTimeMillis();

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void resetPasswordTest() {
        driver.get(baseUrl);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickResetPasswordButton();

        ResetPasswordPage resetPasswordPage = new ResetPasswordPage(driver);
        resetPasswordPage.fillEmailField(email);
        resetPasswordPage.clickSendLinkButton();

        CustomReporter.logAction("CHECK THAT THE ELEMENT IS PRESENT ON THE DASHBOARD PAGE");
        Assert.assertNotNull(resetPasswordPage.resendLinkButtonIsPresent(), "Resend link button didn't appear on the page!");
    }

    @Test(dependsOnMethods = {"resetPasswordTest"}, retryAnalyzer = RetryAnalyzer.class)
    public void emailTest() {
        driver.get(url);

        EmailPage emailPage = new EmailPage(driver);

        emailPage.clickGmailLink();
        emailPage.clickInputLink();
        emailPage.fillLogin(email);
        emailPage.clickEmailNextButton();
        emailPage.fillPassword(googlePassword);
        emailPage.clickPassNextButton();
        emailPage.clickEmailMessage();

        newPasswordPage = emailPage.getEmailMessageLinkText();
    }

    @Test(dependsOnMethods = {"emailTest"}, retryAnalyzer = RetryAnalyzer.class)
    public void changePasswordTest() {
        driver.get(newPasswordPage);
        try {
            Alert alt = driver.switchTo().alert();
            alt.accept();
            changePassword();
        } catch (NoAlertPresentException noe) {
            changePassword();
        }
    }

    public void changePassword() {

        NewPasswordPage newPasswordPage = new NewPasswordPage(driver);

        //Enter and save a new password
        newPasswordPage.fillPassword(newPassword);
        newPasswordPage.fillConfirmPassword(newPassword);
        newPasswordPage.clickChangePasswordButton();
        Assert.assertNotNull(newPasswordPage.successfullyIconIsPresent(), "Failed! Login page is not loaded!");
        newPasswordPage.clickLoginButton();

        //Check that Login page is loaded
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertNotNull(loginPage.resetPasswordButtonIsPresent(), "Failed! Login page is not loaded!");

        //Login with a new password
        LoginHelper login = new LoginHelper(driver);
        login.login(email, newPassword);
        }
}

