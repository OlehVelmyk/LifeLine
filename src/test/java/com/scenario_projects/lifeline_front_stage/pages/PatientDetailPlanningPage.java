package com.scenario_projects.lifeline_front_stage.pages;

import com.scenario_projects.lifeline_front_stage.logging.CustomReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PatientDetailPlanningPage extends BasePage {
    private final By backButton = By.xpath("//p[text()='Back']");
    private final By createTaskButton = By.className("round-button");
    private final By listDayTasks = By.cssSelector(".calendar-day-grid > div:nth-child(3)>div");
    private final By listNextDayTasks = By.cssSelector(".calendar-day-grid > div:nth-child(4)>div");
    private final By calendarDate = By.className("calendar-day-cell");
    private final By calendarEmptyDate = By.cssSelector(".calendar-day-grid.calendar__grid-wrapper > div:nth-child(3) > p");
    private final By refreshButton = By.cssSelector(".icon-btn-modified.icon-btn-modified--title-first>p");
    private final By weekDate = By.className("calendar-week-cell");
    private final By monthDate = By.className("calendar-month-cell");
    private final By successfullyPopUp = By.cssSelector("div.confirm");
    private final By closePopUpButton = By.className("confirm__btn");
    private final By dateTaskTime = By.className("day-task__time");
    private final By taskName = By.className("day-task__title");
    private final By dailyTab = By.cssSelector(".sub-tabs-header__tab-list > a:nth-child(1) > div:nth-child(1)");
    private final By weekTab = By.cssSelector(".sub-tabs-header__tab-list > a:nth-child(2) > div:nth-child(1)");
    private final By monthTab = By.cssSelector(".sub-tabs-header__tab-list > a:nth-child(3) > div:nth-child(1)");

    public PatientDetailPlanningPage(WebDriver driver) {
        super(driver);
    }

    public boolean calendarDateIsPresent() {
        CustomReporter.logAction("CHECK THAT THE CALENDAR DATE IS PRESENT ON THE PAGE");
        return driver.findElements(calendarDate).size() > 0;
    }

    public boolean weekDateIsPresent() {
        CustomReporter.logAction("CHECK THAT THE WEEK DATE IS PRESENT ON THE PAGE");
        return driver.findElements(weekDate).size() > 0;
    }

    public boolean monthDateIsPresent() {
        CustomReporter.logAction("CHECK THAT THE CALENDAR DATE IS PRESENT ON THE PAGE");
        return driver.findElements(monthDate).size() > 0;
    }

    public boolean successfullyPopUpIsPresent() {
        CustomReporter.logAction("CHECK THAT THE SUCCESSFULLY CREATED NEW TASK POPUP IS PRESENT ON THE PAGE");
        return driver.findElements(successfullyPopUp).size() > 0;
    }

    public void clickCreateTaskButton() {
        CustomReporter.logAction("CLICK ON CREATE TASK BUTTON ON THE PAGE");
        waitForClickable(createTaskButton);
        driver.findElement(createTaskButton).click();
    }

    public void clickBackButton() {
        CustomReporter.logAction("CLICK ON BACK BUTTON");
        waitForClickable(backButton);
        driver.findElement(backButton).click();
    }

    public String getDateTaskTime(int i) {
        CustomReporter.logAction("GET DATE TASK TIME TEXT");
        waitForClickable(dateTaskTime);
        List<WebElement> list = driver.findElements(dateTaskTime);
        return list.get(i).getText();
    }

    public String getTaskName(int i) {
        CustomReporter.logAction("GET TASK CONTENT TEXT");
        waitForLocated(taskName);
        List<WebElement> list = driver.findElements(taskName);
        return list.get(i).getText().toLowerCase();
    }

    public void clickRefreshButton() {
        CustomReporter.logAction("CLICK ON REFRESH BUTTON");
        waitForClickable(refreshButton);
        driver.findElement(refreshButton).click();
    }

    public void clickDayTask() {
        CustomReporter.logAction("CLICK ON TASKS");
        waitForClickable(listDayTasks);
        driver.findElement(listDayTasks).click();
    }

    public boolean listDayTasksIsPresent() {
        CustomReporter.logAction("CHECK THAT THE TASK IS PRESENT ON THE PAGE");
        return driver.findElements(listDayTasks).size() > 0;
    }

    public boolean lisNextDayTasksIsPresent() {
        CustomReporter.logAction("CHECK THAT THE TASK IS PRESENT ON THE PAGE");
        return driver.findElements(listNextDayTasks).size() > 0;
    }

    public boolean calendarEmptyDateIsPresent() {
        CustomReporter.logAction("CHECK THAT THE TASK IS PRESENT ON THE PAGE");
        return driver.findElements(calendarEmptyDate).size() > 0;
    }

    public void clickClosePopUpButton() {
        CustomReporter.logAction("CLICK ON CLOSE POPUP BUTTON");
        waitForClickable(closePopUpButton);
        driver.findElement(closePopUpButton).click();
    }

    public void clickDailyTab() {
        CustomReporter.logAction("CLICK ON DAILY TAB");
        waitForClickable(dailyTab);
        driver.findElement(dailyTab).click();
    }

    public void clickWeekTab() {
        CustomReporter.logAction("CLICK ON WEEK TAB");
        waitForClickable(weekTab);
        driver.findElement(weekTab).click();
    }

    public void clickMonthTab() {
        CustomReporter.logAction("CLICK ON MONTH TAB");
        waitForClickable(monthTab);
        driver.findElement(monthTab).click();
    }

    public int countingTasksNumberForToday() {
        int tasksNumber = 0;
        waitForLocated(listDayTasks);
        TasksPage taskPage = new TasksPage(driver);
        List<WebElement> list = driver.findElements(listDayTasks);
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                list.get(i).click();
                tasksNumber = taskPage.getTasksForDayNumber(tasksNumber);
            } else {
                waitForClickable(listDayTasks);
                List<WebElement> list1 = driver.findElements(listDayTasks);
                list1.get(i).click();
                tasksNumber = taskPage.getTasksForDayNumber(tasksNumber);
            }
        }
        return tasksNumber;
    }
}
