package com.scenario_projects.lifeline_front_stage.actionHelpers;

import com.scenario_projects.lifeline_front_stage.logging.CustomReporter;
import com.scenario_projects.lifeline_front_stage.model.PatientCardData;
import com.scenario_projects.lifeline_front_stage.pages.ArchivePage;
import com.scenario_projects.lifeline_front_stage.pages.BasePage;
import com.scenario_projects.lifeline_front_stage.pages.PatientsPage;
import com.scenario_projects.lifeline_front_stage.utils.DataConverter;
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
        int count = 0;
        String patientName;

        if (patientsPage.patientCardsIsPresent()) {
            waitForClickable(patientsPage.getFullNameField());
            List<WebElement> list = driver.findElements(patientsPage.getFullNameField());
            patientName = DataConverter.parsePatientFullName(list.get(0).getText());
            if (patientName.equalsIgnoreCase(PatientCardData.getName())) {
                count += 1;
            }
        } else {
            CustomReporter.logAction("NO PATIENT CARDS");
        }
        return count;
    }

    public int checkThatPatientCardIsPresentOnArchivePage() {
        int count = 0;
        String patientName;

        if (archivePage.patientCardsIsPresent()) {
            waitForClickable(archivePage.getFullNameField());
            List<WebElement> list = driver.findElements(archivePage.getFullNameField());
            patientName = DataConverter.parsePatientFullName(list.get(0).getText());
            if (patientName.equalsIgnoreCase(PatientCardData.getName())) {
                count += 1;
            }
        } else {
            CustomReporter.logAction("NO PATIENT CARD");
        }
        return count;
    }
}
