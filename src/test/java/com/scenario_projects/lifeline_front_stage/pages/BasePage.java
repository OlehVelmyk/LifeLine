package com.scenario_projects.lifeline_front_stage.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver;

    private int TIMEOUT_5 = 5;
    private int TIMEOUT_10 = 10;
    private int TIMEOUT_30 = 30;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void sleep(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitForClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_10);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForClickableShort(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_5);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForLocated(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_10);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}
