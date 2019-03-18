package com.scenario_projects.lifeline_front_stage.pages;

import com.scenario_projects.lifeline_front_stage.logging.CustomReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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

    public By getPatientCards() {
        return patientCards;
    }

    public By getTasksCounter() {
        return tasksCounter;
    }

    public By getFullNameField() {
        return fullNameField;
    }

    public By getPaginationBlock() {
        return paginationBlock;
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

    public void clickOnThePatientsCard(int i) {
        waitForClickable(patientCards);
        List<WebElement> list1 = driver.findElements(patientCards);
        list1.get(i).click();
    }

    public void clickOnCreatedNewPatientCard() {
        if (patientCardsIsPresent()) {
            clickOnThePatientsCard(0);
        } else {
            System.out.println("No due tasks");
            CustomReporter.logAction("NO DUE TASKS");
        }
    }
}

