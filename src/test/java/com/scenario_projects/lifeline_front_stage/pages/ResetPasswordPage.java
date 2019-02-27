package com.scenario_projects.lifeline_front_stage.pages;

import com.scenario_projects.lifeline_front_stage.logging.CustomReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ResetPasswordPage extends BasePage {
    private final By emailInput = By.name("email");
    private final By sendLinkButton = By.xpath("//button[text()='Send Link']");
    private final By backToLoginButton = By.xpath("//button[text()='Back to Login']");
    private final By resendLinkButton = By.xpath("//button[text()='Resend Link']");

    public ResetPasswordPage(WebDriver driver) {
        super(driver);
    }

    public void fillEmailField(String login) {
        CustomReporter.logAction("FILL IN EMAIL FIELD");
        waitForLocated(emailInput);
        driver.findElement(emailInput).sendKeys(login);
    }

    public void clickSendLinkButton() {
        CustomReporter.logAction("CLICK ON SEND LINK BUTTON");
        waitForClickable(sendLinkButton);
        driver.findElement(sendLinkButton).click();
    }

    public WebElement resendLinkButtonIsPresent() {
        CustomReporter.logAction("RESEND LINK BUTTON IS PRESENT");
        waitForLocated(resendLinkButton);
        return driver.findElement(resendLinkButton);
    }

    public void clickBackToLoginButton() {
        CustomReporter.logAction("CLICK ON BACK TO LOGIN BUTTON");
        waitForClickable(backToLoginButton);
        driver.findElement(backToLoginButton).click();
    }

}
