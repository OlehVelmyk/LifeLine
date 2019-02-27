package com.scenario_projects.lifeline_front_stage.pages;

import com.scenario_projects.lifeline_front_stage.logging.CustomReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MessagesPage extends BasePage {
    private final By tabsButton = By.className("sub-tabs-header__tab-list");
    private final By refreshButton = By.className("icon-btn-modified__title");


    public MessagesPage(WebDriver driver) {
        super(driver);
    }

    public boolean tabsButtonsArePresent() {
        CustomReporter.logAction("VERIFY THAT THE TABS BUTTONS ARE PRESENT ON THE PAGE");
        return driver.findElement(tabsButton).isDisplayed();
    }

    public void clickRefreshButton() {
        CustomReporter.logAction("CLICK ON REFRESH BUTTON");
        waitForClickable(refreshButton);
        driver.findElement(refreshButton).click();
    }
}
