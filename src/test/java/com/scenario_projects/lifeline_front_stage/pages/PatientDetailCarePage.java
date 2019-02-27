package com.scenario_projects.lifeline_front_stage.pages;

import com.scenario_projects.lifeline_front_stage.logging.CustomReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PatientDetailCarePage extends BasePage {
    private final By careTab = By.cssSelector(".base-tabs-header__tab-list>a:nth-child(2)");
    private final By vitalParametersElement = By.cssSelector(".list>.list--content>.card-link-item");

    public PatientDetailCarePage(WebDriver driver) {
        super(driver);
    }

    public boolean vitalParametersElementIsPresent() {
        CustomReporter.logAction("CHECK THAT THE WEEK DATE IS PRESENT ON THE PAGE");
        return driver.findElements(vitalParametersElement).size() > 0;
    }

    public void clickCareTab() {
        CustomReporter.logAction("CLICK ON CARE TAB");
        waitForClickable(careTab);
        driver.findElement(careTab).click();
    }
}
