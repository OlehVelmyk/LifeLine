package com.scenario_projects.lifeline_front_stage.pages;

import com.scenario_projects.lifeline_front_stage.logging.CustomReporter;
import com.scenario_projects.lifeline_front_stage.model.PatientCardData;
import com.scenario_projects.lifeline_front_stage.utils.DataConverter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PatientsPage extends BasePage {
    private final By filterButtonInactive = By.cssSelector(".rectangular-button__wrapper.rectangular-button__wrapper--darkGrey");
    private final By filterButtonActive = By.cssSelector(".rectangular-button__wrapper.rectangular-button__wrapper--blue");
    private final By refreshButton = By.className("icon-btn-modified__title");
    private final By patientCards = By.cssSelector(".list--content>div");
    private final By tasksCounter = By.cssSelector(".desktop-table-card>div:nth-child(5)>div");
    private final By fullNameField = By.cssSelector(".desktop-table-card>div:nth-child(3)>div");
    private final By createPatientButton = By.className("round-button");
    private final By paginationBlock = By.cssSelector("ul.pagination>li");
    private final By successfullyPopUp = By.cssSelector("div.confirm");
    private final By closePopUpButton = By.className("confirm__btn");

    public PatientsPage(WebDriver driver) {
        super(driver);
    }

    public boolean filterButtonInactiveIsPresent() {
        CustomReporter.logAction("CHECK THAT THE FILTER BUTTON INACTIVE IS PRESENT ON THE PAGE");
        return driver.findElements(filterButtonInactive).size() > 0;
    }

    public boolean filterButtonActiveIsPresent() {
        CustomReporter.logAction("CHECK THAT THE FILTER BUTTON ACTIVE IS PRESENT ON THE PAGE");
        return driver.findElements(filterButtonActive).size() > 0;
    }

    public boolean successfullyPopUpIsPresent() {
        CustomReporter.logAction("CHECK THAT THE SUCCESSFULLY CREATED PATIENT CARD POPUP IS PRESENT ON THE PAGE");
        return driver.findElements(successfullyPopUp).size() > 0;
    }

    public boolean patientCardsIsPresent() {
        CustomReporter.logAction("CHECK THAT THE PATIENT CARDS ARE PRESENT ON THE DASHBOARD PAGE");
        return driver.findElements(patientCards).size() > 0;
    }

    public void clickRefreshButton() {
        CustomReporter.logAction("CLICK ON REFRESH BUTTON");
        waitForClickable(refreshButton);
        driver.findElement(refreshButton).click();
    }

    public void clickCreateNewPatientButton() {
        CustomReporter.logAction("CLICK ON CREATE NEW PATIENT BUTTON");
        waitForClickable(createPatientButton);
        driver.findElement(createPatientButton).click();
    }

    public void clickFilterButtonActive() {
        CustomReporter.logAction("CLICK ON ACTIVE FILTER BUTTON");
        waitForClickable(filterButtonActive);
        driver.findElement(filterButtonActive).click();
    }

    public void clickClosePopUpButton() {
        CustomReporter.logAction("CLICK ON CLOSE POPUP BUTTON");
        waitForClickable(closePopUpButton);
        driver.findElement(closePopUpButton).click();
    }

    public int countDayTaskNumberOnPatientsDetailPage() {
        int newTasksNumber = 0;
        if (new PaginationPage(driver).paginationBlockIsPresent(paginationBlock)) {
            newTasksNumber = getDayTaskCounterFromPatientCardOnPatientsDetailPage(newTasksNumber);
            CustomReporter.logAction("GET PAGINATION PAGES LIST");
            waitForClickable(paginationBlock);
            List<WebElement> list2 = driver.findElements(paginationBlock);
            for (int i = list2.size() - 3; i > 0; i--) {
                new PaginationPage(driver).clickPaginationItems(list2, i);
                newTasksNumber = getDayTaskCounterFromPatientCardOnPatientsDetailPage(newTasksNumber);
            }
        } else {
            newTasksNumber = getDayTaskCounterFromPatientCardOnPatientsDetailPage(newTasksNumber);
        }
        CustomReporter.logAction("COUNT TASKS NUMBER FOR TODAY ON PATIENTS PAGE");
        return newTasksNumber;
    }

    public int getDayTaskCounterFromPatientCardOnPatientsDetailPage(int newTasksNumber) {
        int tasksNumber;
        CustomReporter.logAction("GET PATIENTS CARDS LIST");
        if (patientCardsIsPresent()) {
            waitForClickable(patientCards);
            List<WebElement> list = driver.findElements(patientCards);
            for (int i = 0; i < list.size(); i++) {
                if (i == 0) {
                    list.get(i).click();
                    tasksNumber = new PatientDetailPlanningPage(driver).countingTasksNumberForToday();
                    new PatientDetailPlanningPage(driver).clickBackButton();
                } else {
                    try {
                        clickOnThePatientsCard(i);
                        tasksNumber = new PatientDetailPlanningPage(driver).countingTasksNumberForToday();
                        new PatientDetailPlanningPage(driver).clickBackButton();
                    } catch (WebDriverException ex) {
                        clickFilterButtonActive();
                        clickOnThePatientsCard(i);
                        tasksNumber = new PatientDetailPlanningPage(driver).countingTasksNumberForToday();
                        new PatientDetailPlanningPage(driver).clickBackButton();
                    }
                }
                newTasksNumber += tasksNumber;
            }
        } else {
            System.out.println("No tasks for today");
            CustomReporter.logAction("NO TASKS FOR TODAY");
        }
        CustomReporter.logAction("COUNT TASKS NUMBER FOR TODAY ON PATIENTS DETAIL PAGE");
        return newTasksNumber;
    }

    public void clickOnThePatientsCard(int i) {
        waitForClickable(patientCards);
        List<WebElement> list1 = driver.findElements(patientCards);
        list1.get(i).click();
    }

    public int getDueTasksCounterFromEachPatientsCard(int newTasksNumber) {
        int tasksNumber;
        CustomReporter.logAction("GET PATIENTS CARDS LIST");
        if (patientCardsIsPresent()) {
            waitForClickable(tasksCounter);
            List<WebElement> list = driver.findElements(tasksCounter);
            for (int i = 0; i < list.size(); i++) {
                tasksNumber = DataConverter.parseSecondDigitValue(list.get(i).getText());
                newTasksNumber += tasksNumber;
            }
        } else {
            System.out.println("No due tasks");
            CustomReporter.logAction("NO DUE TASKS");
        }
        CustomReporter.logAction("COUNT DUE NUMBER IN EACH PATIENTS CARD ON PATIENTS PAGE");
        return newTasksNumber;
    }

    public int DueTasksNumberOnPatientsPage() {
        int newTasksNumber = 0;
        if (new PaginationPage(driver).paginationBlockIsPresent(paginationBlock)) {
            newTasksNumber = getDueTasksCounterFromEachPatientsCard(newTasksNumber);
            CustomReporter.logAction("GET PAGINATION PAGES LIST");
            waitForClickable(paginationBlock);
            List<WebElement> list2 = driver.findElements(paginationBlock);
            for (int i = 2; i < list2.size() - 1; i++) {
                new PaginationPage(driver).clickPaginationItems(list2, i);
                newTasksNumber = getDueTasksCounterFromEachPatientsCard(newTasksNumber);
            }
        } else {
            newTasksNumber = getDueTasksCounterFromEachPatientsCard(newTasksNumber);
        }
        CustomReporter.logAction("COUNT DUE TASKS NUMBER ON PATIENTS PAGE");
        return newTasksNumber;
    }

    public void clickOnCreatedNewPatientCard() {
        if (patientCardsIsPresent()) {
            clickOnThePatientsCard(0);
        } else {
            System.out.println("No due tasks");
            CustomReporter.logAction("NO DUE TASKS");
        }
    }

    public int checkThatPatientCardIsDeleted() {
        int count = 0;
        String patientName;
        if (patientCardsIsPresent()) {
            waitForClickable(fullNameField);
            List<WebElement> list = driver.findElements(fullNameField);
            patientName = DataConverter.parsePatientFullName(list.get(0).getText());
            if (patientName.equalsIgnoreCase(PatientCardData.getName())) {
                count += 1;
            }
        } else {
            System.out.println("No patient cards");
            CustomReporter.logAction("NO PATIENT CARDS");
        }
        return count;
    }
}

