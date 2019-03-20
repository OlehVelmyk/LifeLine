package com.scenario_projects.lifeline_front_stage.pages;

import com.scenario_projects.lifeline_front_stage.logging.CustomReporter;
import com.scenario_projects.lifeline_front_stage.utils.DataConverter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SideBarPatientDetailPagePanel extends BasePage {
    private final By patientProfileLink = By.xpath("//span[text()='Patient’s Profile']");
    private final By fullName = By.cssSelector("div:nth-child(1) > div > div:nth-child(1) > span.info-card__value");
    private final By unitName = By.cssSelector("div:nth-child(1) > div > div:nth-child(7) > span.info-card__value");
    private final By birthDate = By.cssSelector("div:nth-child(2) > div > div:nth-child(1) > span.info-card__value");
    private final By genderName = By.cssSelector("div:nth-child(2) > div > div:nth-child(2) > span.info-card__value");
    private final By countryName = By.cssSelector(" div:nth-child(2) > div > div:nth-child(4) > span.info-card__value");
    private final By postCodeValue = By.cssSelector("div:nth-child(2) > div > div:nth-child(6) > span.info-card__value");
    private final By addressValue = By.cssSelector("div:nth-child(2) > div > div:nth-child(5) > span.info-card__value");
    private final By closeFileLink = By.xpath("//button[text()='Close the File']");
    private final By commentField = By.name("description");
    private final By closeFileButton = By.className("rectangular-button__wrapper");
    private final By closePatientPopup = By.className("popup__modal--open");

    public SideBarPatientDetailPagePanel(WebDriver driver) {
        super(driver);
    }

    public boolean closePatientPopupIsPresent() {
        CustomReporter.logAction("CHECK THAT THE DELETE TASK POPUP IS PRESENT ON THE PAGE");
        return driver.findElements(closePatientPopup).size() > 0;
    }

    public void clickPatientProfileLink() {
        CustomReporter.logAction("CLICK ON PATIENT PROFILE LINK");
        waitForClickable(patientProfileLink);
        driver.findElement(patientProfileLink).click();
    }

    public void clickCloseFileLink() {
        CustomReporter.logAction("CLICK ON CLOSE FILE LINK");
        waitForClickable(closeFileLink);
        driver.findElement(closeFileLink).click();
    }

    public void clickCloseFileButton() {
        CustomReporter.logAction("CLICK ON CLOSE FILE Button");
        waitForClickable(closeFileButton);
        driver.findElement(closeFileButton).click();
    }

    public void fillInCommentField() {
        CustomReporter.logAction("FILL IN COMMENT FIELD");
        waitForClickable(commentField);
        driver.findElement(commentField).sendKeys("Test");
    }

    public String getFullName() {
        waitForLocated(fullName);
        return driver.findElement(fullName).getText();
    }

    public String getUnitName() {
        waitForLocated(unitName);
        return driver.findElement(unitName).getText();
    }

    public String getBirthDate() {
        String getDate, month, date, year, monthPlusDate, birthDateNew;
        waitForLocated(birthDate);
        getDate = driver.findElement(birthDate).getText();
        month = DataConverter.parseMonthValue(getDate);
        date = DataConverter.parseDateValue(getDate);
        year = DataConverter.parseYearValue(getDate);
        monthPlusDate = month.concat(date);
        birthDateNew = monthPlusDate.concat(year);
        return birthDateNew;
    }

    public String getGenderName() {
        waitForLocated(genderName);
        return driver.findElement(genderName).getText();
    }

    public String getCountryName() {
        waitForLocated(countryName);
        return driver.findElement(countryName).getText();
    }

    public String getPostCodeValue() {
        waitForLocated(postCodeValue);
        return driver.findElement(postCodeValue).getText();
    }

    public String getAddressValue() {
        waitForLocated(addressValue);
        return driver.findElement(addressValue).getText();
    }
}
