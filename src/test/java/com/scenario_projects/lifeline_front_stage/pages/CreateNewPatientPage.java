package com.scenario_projects.lifeline_front_stage.pages;

import com.scenario_projects.lifeline_front_stage.logging.CustomReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CreateNewPatientPage extends BasePage {
    private final By proceedButton = By.xpath("//button[text()='Proceed']");
    private final By fullNameField = By.name("full_name");
    private final By unitSelectField = By.name("unit");
    private final By birthDateField = By.name("birth_date");
    private final By postCodeField = By.name("post_code");
    private final By addressField = By.name("address");
    private final By unitType = By.xpath("//option[@value='1']");
    private final By genderType = By.xpath("//option[@value='M']");
    private final By country = By.xpath("//select[@name='country']/option[@value='UKR']");
    private final By saveButton = By.xpath("//button[text()='Save']");

    public CreateNewPatientPage(WebDriver driver) {
        super(driver);
    }

    public boolean patientCardsIsPresent() {
        CustomReporter.logAction("CHECK THAT THE PROCEED BUTTON IS PRESENT ON THE CREATE PATIENT PAGE");
        return driver.findElements(proceedButton).size() > 0;
    }

    public void clickProceedButton() {
        CustomReporter.logAction("CLICK ON PROCEED BUTTON BUTTON");
        waitForClickable(proceedButton);
        driver.findElement(proceedButton).click();
    }

    public void clickSaveButton() {
        CustomReporter.logAction("CLICK ON SAVE BUTTON BUTTON");
        waitForClickable(saveButton);
        driver.findElement(saveButton).click();
    }

    public void fillFullNameField(String name) {
        CustomReporter.logAction("FILL IN NAME FIELD");
        waitForLocated(fullNameField);
        driver.findElement(fullNameField).sendKeys(name);
    }

    public void clickUnitFromUnitField() {
        CustomReporter.logAction("CLICK ON UNIT FIELD");
        waitForClickable(unitSelectField);
        driver.findElement(unitSelectField).click();
    }

    public void selectUnitType() {
        CustomReporter.logAction("SELECT UNIT FROM UNIT FIELD");
        waitForClickable(unitType);
        driver.findElement(unitType).click();
    }

    public String getUnitType() {
        CustomReporter.logAction("GET UNIT NAME FROM UNIT FIELD");
        waitForClickable(unitType);
        return driver.findElement(unitType).getText();
    }

    public void fillBirthDateField(String browserName) {
        CustomReporter.logAction("FILL IN BIRTH DATE FIELD");
        waitForClickable(birthDateField);
        switch (browserName) {
            case "firefox":
                driver.findElement(By.name("birth_date")).sendKeys("1975-02-03");
                break;
            default:
                driver.findElement(By.name("birth_date")).sendKeys("02031975");
                break;
        }
    }

    public void selectGenderType() {
        CustomReporter.logAction("SELECT GENDER FROM GENDER FIELD");
        waitForClickable(genderType);
        List<WebElement> list = driver.findElements(genderType);
        list.get(0).click();
    }

    public String getGenderType() {
        CustomReporter.logAction("GET GENDER NAME FROM GENDER FIELD");
        waitForClickable(genderType);
        List<WebElement> list = driver.findElements(genderType);
        return list.get(0).getText();
    }

    public void selectCountry() {
        CustomReporter.logAction("SELECT COUNTRY FROM COUNTRY FIELD");
        waitForClickable(country);
        driver.findElement(country).click();
    }

    public void fillPostCodeField(String postCode) {
        CustomReporter.logAction("FILL IN POST CODE FIELD");
        waitForClickable(postCodeField);
        driver.findElement(postCodeField).sendKeys(postCode);
    }

    public void fillAddressField(String address) {
        CustomReporter.logAction("FILL IN ADDRESS FIELD");
        waitForClickable(addressField);
        driver.findElement(addressField).sendKeys(address);
    }
}
