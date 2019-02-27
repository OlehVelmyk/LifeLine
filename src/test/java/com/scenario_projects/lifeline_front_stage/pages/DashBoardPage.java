package com.scenario_projects.lifeline_front_stage.pages;

import com.scenario_projects.lifeline_front_stage.logging.CustomReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashBoardPage extends BasePage {
    private final By blocksIsPresent = By.className("dashboard-block__header");
    private final By refreshButton = By.className("icon-btn-modified__title");
    private final By taskDayCounter = By.cssSelector(".task-item:nth-child(1)>.task-item__count");
    private final By dueTasksCounter = By.cssSelector(".task-item:nth-child(3)>.task-item__count");


    public DashBoardPage(WebDriver driver) {
        super(driver);
    }

    public boolean blocksIsPresent() {
        CustomReporter.logAction("CHECK THAT THE ELEMENTS ARE PRESENT ON THE DASHBOARD PAGE");
        return driver.findElements(blocksIsPresent).size() > 0;
    }

    public void clickRefreshButton() {
        CustomReporter.logAction("CLICK ON REFRESH BUTTON");
        waitForClickable(refreshButton);
        driver.findElement(refreshButton).click();
    }

    public int getTaskDayCounter() {
        CustomReporter.logAction("GET DAY TASK COUNT ON THE DASHBOARD");
        waitForClickable(taskDayCounter);
        return Integer.parseInt(driver.findElement(taskDayCounter).getText());
    }

    public int getDueTaskCounterOnDashBoard() {
        CustomReporter.logAction("GET TASK ASSIGNED TO ME COUNT ON THE DASHBOARD");
        waitForClickable(dueTasksCounter);
        return Integer.parseInt(driver.findElement(dueTasksCounter).getText());
    }

    public void clickTaskDayCounter() {
        CustomReporter.logAction("CLICK ON DAY TASK COUNTER");
        waitForClickable(taskDayCounter);
        driver.findElement(taskDayCounter).click();
    }

    public void clickDueTaskCounter() {
        CustomReporter.logAction("CLICK ON DUE TASK COUNTER");
        waitForClickable(dueTasksCounter);
        driver.findElement(dueTasksCounter).click();
    }
}
