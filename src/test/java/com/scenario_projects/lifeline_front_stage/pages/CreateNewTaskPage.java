package com.scenario_projects.lifeline_front_stage.pages;

import com.scenario_projects.lifeline_front_stage.dataProvider.DateProvider;
import com.scenario_projects.lifeline_front_stage.logging.CustomReporter;
import com.scenario_projects.lifeline_front_stage.utils.DataConverter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CreateNewTaskPage extends BasePage {
    private final By categoriesContent = By.cssSelector(".categories-board__category-block > div.categories-board__block-body " +
            "> div > div > div:nth-child(1)");
    private final By taskContent = By.cssSelector(".categories-board__details-block--transform > div.categories-board__block-body" +
            " > div > div > div:nth-child(1)");
    private final By taskContentName = By.cssSelector(".categories-board__details-block--transform > div.categories-board__block-body " +
            "> div > div > div:nth-child(1)>div:nth-child(1)");
    private final By datePickerField = By.name("date");
    private final By selectTimeField = By.cssSelector(".simple-select__select>option:nth-child(5)");
    private final By commentField = By.name("comment");
    private final By createTaskButton = By.className("rectangular-button__wrapper");
    private final By setFixedHoursTab = By.cssSelector("a.query-link:nth-child(2)");
    private final By setTimeSlotTab = By.cssSelector("a.query-link:nth-child(3)");
    private final By immediateTab = By.cssSelector("a.query-link:nth-child(4)");
    private final By addTimeButton = By.cssSelector(" div:nth-child(2) > div > div > div.css-1aya2g8.multi-select__control > " +
            "div.css-1wy0on6.multi-select__indicators > div.css-1ep9fjw.multi-select__indicator.multi-select__dropdown-indicator");
    private final By timeValue = By.xpath("//div[@role='option']");
    private final By taskForm = By.className("create-task-form-struct__block");

    public CreateNewTaskPage(WebDriver driver) {
        super(driver);
    }

    public void clickCategoriesContent() {
        CustomReporter.logAction("CLICK ON CATEGORIES CONTENT");
        waitForClickable(categoriesContent);
        driver.findElement(categoriesContent).click();
    }

    public void clickTaskContent() {
        CustomReporter.logAction("CLICK ON TASK CONTENT");
        waitForClickable(taskContent);
        driver.findElement(taskContent).click();
    }

    public String getTaskContentText() {
        CustomReporter.logAction("GET TASK CONTENT TEXT");
        waitForClickable(taskContentName);
        return driver.findElement(taskContentName).getText().toLowerCase();
    }

    public void clickSetFixedHoursTab() {
        CustomReporter.logAction("CLICK ON SET FIXED HOURS TAB");
        waitForClickable(setFixedHoursTab);
        driver.findElement(setFixedHoursTab).click();
    }

    public void clickSetTimeSlotTab() {
        CustomReporter.logAction("CLICK ON SET TIME SLOT TAB");
        waitForClickable(setTimeSlotTab);
        driver.findElement(setTimeSlotTab).click();
    }

    public void clickImmediateTab() {
        CustomReporter.logAction("CLICK ON SET TIME SLOT TAB");
        waitForClickable(immediateTab);
        driver.findElement(immediateTab).click();
    }

    public void fillDatePickerField(String browserName, String date) {
        CustomReporter.logAction("CLICK ON DATE PICKER CONTENT");
        waitForClickable(datePickerField);
        switch (browserName) {
            case "firefox":
                driver.findElement(By.name("date")).sendKeys(date);
                break;
            default:
                driver.findElement(datePickerField).click();
                for (int i = 0; i < DataConverter.parseNewMonthValue(date); i++) {
                    driver.findElement(datePickerField).sendKeys(Keys.ARROW_UP);
                }
                driver.findElement(datePickerField).sendKeys(Keys.ARROW_RIGHT);
                for (int i = 0; i < DataConverter.parseSecondDigitValue(date); i++) {
                    driver.findElement(datePickerField).sendKeys(Keys.ARROW_UP);
                }
                driver.findElement(datePickerField).sendKeys(Keys.ARROW_RIGHT);
                driver.findElement(datePickerField).sendKeys(Keys.ARROW_UP);
                break;
        }
    }

    public String fillSelectTimerField() {
        CustomReporter.logAction("CLICK ON SELECT TIME FIELD");
        waitForClickable(selectTimeField);
        driver.findElement(selectTimeField).click();
        return driver.findElement(selectTimeField).getText();
    }

    public void clickCommentField() {
        CustomReporter.logAction("CLICK ON COMMENT FIELD");
        waitForClickable(commentField);
        driver.findElement(commentField).click();
    }


    public void clickSelectFixedHoursField() {
        CustomReporter.logAction("CLICK ON SELECT FIXED HOURS FIELD");
        waitForClickable(addTimeButton);
        driver.findElement(addTimeButton).click();
    }

    public void clickCreateTaskButton() {
        CustomReporter.logAction("CLICK ON CREATE TASK BUTTON");
        waitForClickable(createTaskButton);
        driver.findElement(createTaskButton).click();
    }

    public boolean timeValueIsPresent() {
        CustomReporter.logAction("CHECK THAT THE CALENDAR DATE IS PRESENT ON THE PAGE");
        return driver.findElements(timeValue).size() > 0;
    }

    public String clickTimeValue(int i) {
        String taskTime;
        waitForLocated(timeValue);
        List<WebElement> list = driver.findElements(timeValue);
        taskTime = list.get(i).getText();
        list.get(i).click();
        return taskTime;
    }

    public void clickTaskForm() {
        waitForClickable(taskForm);
        driver.findElement(taskForm).click();
    }

    public String changeDataTime(String dataTime) {
        String dayTime, newDayTime;
        dayTime = DataConverter.parseDataTime(dataTime).replaceAll("\\sam", ":00");
        newDayTime = "0".concat(dayTime);
        return newDayTime;
    }

    public String getTaskTime() {
        int minutes, hours = 0;
        String currentTime, taskTime;
        currentTime = DateProvider.currentTime();
        minutes = DataConverter.parseSecondDigitValue(currentTime);
        if ((minutes == 0)) {
            taskTime = currentTime;
        } else {
            if (minutes <= 30) {
                taskTime = DataConverter.parseYearValue(currentTime).concat(":30");
            } else {
                hours = Integer.parseInt(DataConverter.parseYearValue(currentTime)) + 1;
                taskTime = String.valueOf(hours).concat(":00");
            }
        }
        return taskTime;
    }
}
