package com.scenario_projects.lifeline_front_stage.pages;

import com.scenario_projects.lifeline_front_stage.logging.CustomReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {
    private final By emailInput = By.name("email");
    private final By passwordInput = By.name("password");
    private final By logInButton = By.cssSelector("button[class='rectangular-button__wrapper  ']");
    private final By resetPasswordButton = By.xpath("//button[text()='Reset Password']");
    private final By visibleIcon = By.className("visible-icon");
    private final By textTypeField = By.xpath("//input[@type='text']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void fillLogin(String login) {
        CustomReporter.logAction("FILL IN EMAIL FIELD");
        waitForClickable(emailInput);
        driver.findElement(emailInput).sendKeys(login);
    }

    public void fillPassword(String password) {
        CustomReporter.logAction("FILL IN PASSWORD FIELD");
        waitForClickable(passwordInput);
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLogInButton() {
        CustomReporter.logAction("CLICK ON LOGIN BUTTON");
        waitForClickable(logInButton);
        driver.findElement(logInButton).click();
    }

    public void clickResetPasswordButton() {
        CustomReporter.logAction("CLICK ON RESET PASSWORD BUTTON");
        waitForClickable(resetPasswordButton);
        driver.findElement(resetPasswordButton).click();
    }

    public WebElement resetPasswordButtonIsPresent() {
        CustomReporter.logAction("CHECK THAT THE USER BACK TO LOGIN PAGE");
        waitForLocated(resetPasswordButton);
        return driver.findElement(resetPasswordButton);
    }

    public void clickVisibleIcon() {
        CustomReporter.logAction("CLICK ON LOGIN BUTTON");
        waitForClickable(visibleIcon);
        driver.findElement(visibleIcon).click();
    }

    public boolean textTypeFieldIsPresent() {
        CustomReporter.logAction("CHECK THAT THE USER BACK TO LOGIN PAGE");
        return driver.findElements(textTypeField).size() > 0;
    }
}
