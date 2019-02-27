package com.scenario_projects.lifeline_front_stage.pages;

import com.scenario_projects.lifeline_front_stage.logging.CustomReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TasksPage extends BasePage {
    private final By taskStatus = By.cssSelector("div:nth-child(1) > span.info-card__value.info-card__value--default");
    private final By threePointsButton = By.className("page-title__side-block");
    private final By editTaskLink = By.cssSelector("div.dropdown-actions__body > div:nth-child(2) > button");
    private final By deleteTaskLink = By.cssSelector("div.dropdown-actions__body > div:nth-child(1) > button");
    private final By datePickerField = By.name("date");
    private final By selectNewTaskField = By.cssSelector(".simple-select__select>option:nth-child(8)");
    private final By createTaskButton = By.className("rectangular-button__wrapper");
    private final By editTaskPopup = By.className("popup__modal--open");
    private final By reasonField = By.name("journal_comment");
    private final By yesButton = By.cssSelector(".confirm-popup__button-section > div:nth-child(2) > button");
    private final By undoneTaskButton = By.className("rectangular-button__wrapper--white");
    private final By confirmTaskButton = By.cssSelector(".task-details__buttons-group > button:nth-child(2)");

    public TasksPage(WebDriver driver) {
        super(driver);
    }

    public boolean editTaskPopupIsPresent() {
        CustomReporter.logAction("CHECK THAT THE DELETE TASK POPUP IS PRESENT ON THE PAGE");
        return driver.findElements(editTaskPopup).size() > 0;
    }

    public void clickThreePointsButton() {
        CustomReporter.logAction("CLICK ON THREE POINTS BUTTON");
        waitForClickable(threePointsButton);
        driver.findElement(threePointsButton).click();
    }

    public void clickUndoneTaskButton() {
        CustomReporter.logAction("CLICK ON UNDONE TASK BUTTON");
        waitForLocated(undoneTaskButton);
        driver.findElement(undoneTaskButton).click();
    }

    public void clickEditTaskLink() {
        CustomReporter.logAction("CLICK ON EDIT TASK LINK");
        waitForClickable(editTaskLink);
        driver.findElement(editTaskLink).click();
    }

    public void clickDeleteTaskLink() {
        CustomReporter.logAction("CLICK ON DELETE TASK LINK");
        waitForClickable(deleteTaskLink);
        driver.findElement(deleteTaskLink).click();
    }

    public String fillSelectTimerField() {
        CustomReporter.logAction("CLICK ON SELECT TIME FIELD");
        waitForClickable(selectNewTaskField);
        driver.findElement(selectNewTaskField).click();
        return driver.findElement(selectNewTaskField).getText();
    }

    public void clickCreateTaskButton() {
        CustomReporter.logAction("CLICK ON CREATE TASK BUTTON");
        waitForClickable(createTaskButton);
        driver.findElement(createTaskButton).click();
    }

    public void fillReasonField() {
        CustomReporter.logAction("FILL IN REASON FIELD");
        waitForClickable(reasonField);
        driver.findElement(reasonField).sendKeys("Test");
    }

    public void clickYesButton() {
        CustomReporter.logAction("CLICK ON YES BUTTON");
        waitForClickable(yesButton);
        driver.findElement(yesButton).click();
    }

    public void clickConfirmTaskButton() {
        CustomReporter.logAction("CLICK ON CONFIRM TASK BUTTON");
        waitForClickable(confirmTaskButton);
        driver.findElement(confirmTaskButton).click();
    }

    public void editDatePickerField(String browserName, String date) {
        CustomReporter.logAction("CLICK ON DATE PICKER CONTENT");
        waitForClickable(datePickerField);
        switch (browserName) {
            case "firefox":
                driver.findElement(By.name("date")).sendKeys(date);
                break;
            default:
                driver.findElement(datePickerField).click();
                driver.findElement(datePickerField).sendKeys(Keys.ARROW_RIGHT);
                driver.findElement(datePickerField).sendKeys(Keys.ARROW_UP);
                break;
        }
    }

    public int getTasksForDayNumber(int tasksNumber) {
        waitForLocated(taskStatus);
        WebElement taskStatusText = driver.findElement(taskStatus);
        if (taskStatusText.getText().equalsIgnoreCase("Done") || taskStatusText.getText().equalsIgnoreCase("Undone")) {
            new PatientDetailPlanningPage(driver).clickBackButton();
        } else {
            tasksNumber += 1;
            new PatientDetailPlanningPage(driver).clickBackButton();
        }
        return tasksNumber;
    }

    public boolean checkTaskStatus(String taskStatusName) {
        waitForLocated(taskStatus);
        WebElement taskStatusText = driver.findElement(taskStatus);
        if (taskStatusText.getText().equalsIgnoreCase(taskStatusName)) {
            return true;
        }
        return false;
    }
}
