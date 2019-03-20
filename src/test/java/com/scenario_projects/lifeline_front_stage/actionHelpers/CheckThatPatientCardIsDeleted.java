package com.scenario_projects.lifeline_front_stage.actionHelpers;

import com.scenario_projects.lifeline_front_stage.logging.CustomReporter;
import com.scenario_projects.lifeline_front_stage.model.PatientCardData;
import com.scenario_projects.lifeline_front_stage.pages.ArchivePage;
import com.scenario_projects.lifeline_front_stage.pages.BasePage;
import com.scenario_projects.lifeline_front_stage.pages.PatientsPage;
import com.scenario_projects.lifeline_front_stage.utils.DataConverter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckThatPatientCardIsDeleted extends BasePage {

    public CheckThatPatientCardIsDeleted(WebDriver driver) {
        super(driver);
    }

    PatientsPage patientsPage = new PatientsPage(driver);
    ArchivePage archivePage = new ArchivePage(driver);

    public int checkThatPatientCardIsDeletedFromPatientPage() {
        int count;
        count = getCount(patientsPage.patientCardsIsPresent(), patientsPage.getFullNameField(), "NO PATIENT CARDS");
        return count;
    }

    public int checkThatPatientCardIsPresentOnArchivePage() {
        int count;
        count = getCount(archivePage.patientCardsIsPresent(), archivePage.getFullNameField(), "NO PATIENT CARD ON ARCHIVE PAGE");
        return count;
    }

    private int getCount(boolean patientCardsIsPresent, By fullNameField, String message) {
        int count = 0;
        String patientName;
        if (patientCardsIsPresent) {
            waitForClickable(fullNameField);
            List<WebElement> list = driver.findElements(fullNameField);
            patientName = DataConverter.parsePatientFullName(list.get(0).getText());
            if (patientName.equalsIgnoreCase(PatientCardData.getName())) {
                count += 1;
            }
        } else {
            CustomReporter.logAction(message);
        }
        return count;
    }
}
