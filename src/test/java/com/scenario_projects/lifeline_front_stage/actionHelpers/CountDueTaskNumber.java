package com.scenario_projects.lifeline_front_stage.actionHelpers;

import com.scenario_projects.lifeline_front_stage.logging.CustomReporter;
import com.scenario_projects.lifeline_front_stage.pages.BasePage;
import com.scenario_projects.lifeline_front_stage.pages.PaginationPage;
import com.scenario_projects.lifeline_front_stage.pages.PatientsPage;
import com.scenario_projects.lifeline_front_stage.utils.DataConverter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CountDueTaskNumber extends BasePage {

    public CountDueTaskNumber(WebDriver driver) {
        super(driver);
    }

    PatientsPage patientsPage = new PatientsPage(driver);

    public int DueTasksNumberOnPatientsPage() {
        int newTasksNumber = 0;
        if (new PaginationPage(driver).paginationBlockIsPresent(patientsPage.getPaginationBlock())) {
            newTasksNumber = getDueTasksCounterFromEachPatientsCard(newTasksNumber);
            CustomReporter.logAction("GET PAGINATION PAGES LIST");
            waitForClickable(patientsPage.getPaginationBlock());
            List<WebElement> list2 = driver.findElements(patientsPage.getPaginationBlock());
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

    public int getDueTasksCounterFromEachPatientsCard(int newTasksNumber) {
        int tasksNumber;
        CustomReporter.logAction("GET PATIENTS CARDS LIST");
        if (patientsPage.patientCardsIsPresent()) {
            waitForClickable(patientsPage.getTasksCounter());
            List<WebElement> list = driver.findElements(patientsPage.getTasksCounter());
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
}
