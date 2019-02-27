package com.scenario_projects.lifeline_front_stage.pages;

import com.scenario_projects.lifeline_front_stage.logging.CustomReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SideBarPanel extends BasePage {
    private final By profileIcon = By.className("main-navigation-bar__profile-wrapper");
    private final By logoutLink = By.className("profile-menu__logout-btn");
    private final By textLabel = By.className("nav-bar-link__label");

    public SideBarPanel(WebDriver driver) {
        super(driver);
    }

    public void clickProfileIcon() {
        CustomReporter.logAction("CLICK ON PROFILE ICON");
        waitForClickable(profileIcon);
        driver.findElement(profileIcon).click();
    }

    public void clickLogoutLink() {
        CustomReporter.logAction("CLICK ON LOGOUT LINK");
        sleep(1000);
        driver.findElement(logoutLink).click();
    }

    public void chooseMenuItem(String item) {
        waitForClickable(textLabel);
        List<WebElement> list = driver.findElements(textLabel);
        CustomReporter.logAction("CLICK ON " + item.toUpperCase() + " MENU ICON");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getText().equals(item)) {
                list.get(i).click();
                break;
            } else {
                CustomReporter.logAction(list.get(i).getText() + " doesn't equal " + item);
                System.out.println(list.get(i).getText() + " doesn't equal " + item);
            }
        }
    }
}