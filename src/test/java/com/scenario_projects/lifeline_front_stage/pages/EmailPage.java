package com.scenario_projects.lifeline_front_stage.pages;

import com.scenario_projects.lifeline_front_stage.logging.CustomReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class EmailPage extends BasePage {
    private final By gmailLink = By.linkText("Gmail");
    private final By inputLink = By.className("gmail-nav__nav-link__sign-in");
    private final By emailField = By.id("identifierId");
    private final By emailNextButton = By.id("identifierNext");
    private final By passwordField = By.name("password");
    private final By passNextButton = By.id("passwordNext");
    private final By emailMessage = By.xpath("//*/span[text()='lifeline.product.ca.']/../../..");
    private final By emailMessageLink = By.cssSelector(".a3s.aXjCH>a");
    private final By threePointsIcon = By.cssSelector("img.ajT");
    private final By nextEmailMessageLink = By.cssSelector(".a3s>div>div>a");

    public EmailPage(WebDriver driver) {
        super(driver);
    }

    public void clickGmailLink() {
        CustomReporter.logAction("CLICK ON GMAIL LINK");
        waitForClickable(gmailLink);
        driver.findElement(gmailLink).click();
    }

    public void clickInputLink() {
        CustomReporter.logAction("CLICK ON GMAIL LINK");
        waitForClickable(inputLink);
        driver.findElement(inputLink).click();
    }

    public void fillLogin(String login) {
        CustomReporter.logAction("FILL IN EMAIL FIELD");
        waitForLocated(emailField);
        driver.findElement(emailField).sendKeys(login);
    }

    public void clickEmailNextButton() {
        CustomReporter.logAction("CLICK ON NEXT BUTTON");
        waitForClickable(emailNextButton);
        driver.findElement(emailNextButton).click();
    }

    public void fillPassword(String password) {
        CustomReporter.logAction("FILL IN PASSWORD FIELD");
        sleep(2000);
        waitForLocated(passwordField);
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickPassNextButton() {
        CustomReporter.logAction("CLICK ON NEXT BUTTON");
        sleep(2000);
        waitForClickable(passNextButton);
        driver.findElement(passNextButton).click();
    }

    public void clickEmailMessage() {
        try {
            CustomReporter.logAction("CLICK ON EMAIL MESSAGE");
            waitForClickable(emailMessage);
            driver.findElement(emailMessage).click();
        } catch (TimeoutException ex) {
            CustomReporter.logAction("RESET PASSWORD MESSAGE HASN'T APPEARED!!!");
        }
    }

    public String getEmailMessageLinkText() {
        try {
            waitForClickableShort(emailMessageLink);
            List<WebElement> list = driver.findElements(emailMessageLink);
            CustomReporter.logAction("GET EMAIL MESSAGE LINK");
            if (list.size() >= 1) {
                return list.get(list.size() - 1).getText();
            }
        } catch (TimeoutException ex) {

            waitForClickable(threePointsIcon);
            List<WebElement> list1 = driver.findElements(threePointsIcon);

            CustomReporter.logAction("CLICK ON THREE POINTS ICON");
            list1.get(list1.size() - 1).click();

            waitForClickable(nextEmailMessageLink);
            CustomReporter.logAction("GET EMAIL MESSAGE LINK");
            return driver.findElement(nextEmailMessageLink).getText();
        }
        return null;
    }
}