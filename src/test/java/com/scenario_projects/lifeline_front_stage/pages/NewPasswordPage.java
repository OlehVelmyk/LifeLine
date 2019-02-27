package com.scenario_projects.lifeline_front_stage.pages;

import com.scenario_projects.lifeline_front_stage.logging.CustomReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NewPasswordPage extends BasePage {
    private final By passwordField = By.name("new_password1");
    private final By confirmPasswordField = By.name("new_password2");
    private final By changePasswordButton = By.xpath("//button[text()='Change Password']");
    private final By successfullyIcon = By.cssSelector(".new-password-form__logo-wrapper>div>div>.injected-svg");
    private final By loginButton = By.xpath("//button[text()='Login']");

    public NewPasswordPage(WebDriver driver) {
        super(driver);
    }

    public void fillPassword(String password) {
        CustomReporter.logAction("FILL IN PASSWORD FIELD");
        waitForLocated(passwordField);
        driver.findElement(passwordField).sendKeys(password);
    }

    public void fillConfirmPassword(String password) {
        CustomReporter.logAction("FILL IN CONFIRM PASSWORD FIELD");
        waitForLocated(confirmPasswordField);
        driver.findElement(confirmPasswordField).sendKeys(password);
    }

    public void clickChangePasswordButton() {
        CustomReporter.logAction("CLICK ON CHANGE PASSWORD BUTTON");
        waitForClickable(changePasswordButton);
        driver.findElement(changePasswordButton).click();
    }

    public WebElement successfullyIconIsPresent() {
        CustomReporter.logAction("CHECK THAT THE ELEMENT IS PRESENT ON THE PAGE");
        waitForLocated(successfullyIcon);
        return driver.findElement(successfullyIcon);
    }

    public void clickLoginButton() {
        CustomReporter.logAction("CLICK ON LOGIN BUTTON");
        waitForClickable(loginButton);
        driver.findElement(loginButton).click();
    }


}
