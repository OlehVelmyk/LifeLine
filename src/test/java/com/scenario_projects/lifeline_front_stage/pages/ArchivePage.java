package com.scenario_projects.lifeline_front_stage.pages;

import com.scenario_projects.lifeline_front_stage.logging.CustomReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ArchivePage extends BasePage {
    private final By patientCards = By.className("desktop-table-card__buttons-group");
    private final By fullNameField = By.cssSelector(".desktop-table-card>div:nth-child(2)>div");

    public ArchivePage(WebDriver driver) {
        super(driver);
    }

    public By getFullNameField() {
        return fullNameField;
    }

    public boolean patientCardsIsPresent() {
        CustomReporter.logAction("CHECK THAT THE FILTER BUTTON ACTIVE IS PRESENT ON THE PAGE");
        return driver.findElements(patientCards).size() > 0;
    }
}
