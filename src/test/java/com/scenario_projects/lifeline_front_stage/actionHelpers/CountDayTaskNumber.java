package com.scenario_projects.lifeline_front_stage.actionHelpers;

import com.scenario_projects.lifeline_front_stage.logging.CustomReporter;
import com.scenario_projects.lifeline_front_stage.pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CountDayTaskNumber extends BasePage {
    protected WebDriver driver;

    public CountDayTaskNumber(WebDriver driver) {
        super(driver);
    }

    PatientsPage patientsPage = new PatientsPage(driver);
    PatientDetailPlanningPage patientDetailPlanningPage = new PatientDetailPlanningPage(driver);
    TasksPage tasksPage = new TasksPage(driver);

    public int countDayTaskNumberOnPatientsDetailPage() {
        int newTasksNumber = 0;
        if (new PaginationPage(driver).paginationBlockIsPresent(patientsPage.getPaginationBlock())) {
            newTasksNumber = getDayTaskCounterFromPatientCardOnPatientsDetailPage(newTasksNumber);
            CustomReporter.logAction("GET PAGINATION PAGES LIST");
            waitForClickable(patientsPage.getPaginationBlock());
            List<WebElement> list2 = driver.findElements(patientsPage.getPaginationBlock());
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
        if (patientsPage.patientCardsIsPresent()) {
            waitForClickable(patientsPage.getPatientCards());
            List<WebElement> list = driver.findElements(patientsPage.getPatientCards());
            for (int i = 0; i < list.size(); i++) {
                if (i == 0) {
                    list.get(i).click();
                    tasksNumber = countingTasksNumberForToday();
                    patientDetailPlanningPage.clickBackButton();
                } else {
                    try {
                        patientsPage.clickOnThePatientsCard(i);
                        tasksNumber = countingTasksNumberForToday();
                        patientDetailPlanningPage.clickBackButton();
                    } catch (WebDriverException ex) {
                        patientsPage.clickFilterButtonActive();
                        patientsPage.clickOnThePatientsCard(i);
                        tasksNumber = countingTasksNumberForToday();
                        patientDetailPlanningPage.clickBackButton();
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

    public int countingTasksNumberForToday() {
        int tasksNumber = 0;
        waitForLocated(patientDetailPlanningPage.getListDayTasks());
        List<WebElement> list = driver.findElements(patientDetailPlanningPage.getListDayTasks());
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                list.get(i).click();
                tasksNumber = getTasksForDayNumber(tasksNumber);
            } else {
                waitForClickable(patientDetailPlanningPage.getListDayTasks());
                List<WebElement> list1 = driver.findElements(patientDetailPlanningPage.getListDayTasks());
                list1.get(i).click();
                tasksNumber = getTasksForDayNumber(tasksNumber);
            }
        }
        return tasksNumber;
    }

    public int getTasksForDayNumber(int tasksNumber) {
        waitForLocated(tasksPage.getTaskStatus());
        WebElement taskStatusText = driver.findElement(tasksPage.getTaskStatus());
        if (taskStatusText.getText().equalsIgnoreCase("Done") || taskStatusText.getText().equalsIgnoreCase("Undone")) {
            new PatientDetailPlanningPage(driver).clickBackButton();
        } else {
            tasksNumber += 1;
            new PatientDetailPlanningPage(driver).clickBackButton();
        }
        return tasksNumber;
    }
}
