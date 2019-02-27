package com.scenario_projects.lifeline_front_stage.tests;

import com.scenario_projects.lifeline_front_stage.actionHelpers.ClosePatientCardHelper;
import com.scenario_projects.lifeline_front_stage.actionHelpers.CreateNewPatientHelper;
import com.scenario_projects.lifeline_front_stage.actionHelpers.LoginHelper;
import com.scenario_projects.lifeline_front_stage.dataProvider.BrowserTypeDataProvider;
import com.scenario_projects.lifeline_front_stage.dataProvider.DateProvider;
import com.scenario_projects.lifeline_front_stage.dataProvider.LoginDataProvider;
import com.scenario_projects.lifeline_front_stage.pages.CreateNewTaskPage;
import com.scenario_projects.lifeline_front_stage.pages.PatientDetailPlanningPage;
import com.scenario_projects.lifeline_front_stage.utils.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class N_0023_CreateSetTimeSlotTaskTest extends BaseTest {
    private String DataTime, taskName;

    @BeforeMethod
    public void createPatientCard() {
        driver.get(baseUrl);

        //Login to the system
        LoginHelper login = new LoginHelper(driver);
        login.login(LoginDataProvider.email, LoginDataProvider.password);

        //Create a new patient
        CreateNewPatientHelper newPatient = new CreateNewPatientHelper(driver);
        newPatient.createNewPatientHelper();
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void createSetFixedHoursTask() {
        PatientDetailPlanningPage createTask = new PatientDetailPlanningPage(driver);
        createTask.clickCreateTaskButton();

        CreateNewTaskPage newTask = new CreateNewTaskPage(driver);
        newTask.clickCategoriesContent();
        taskName = newTask.getTaskContentText();
        newTask.clickTaskContent();
        newTask.clickSetTimeSlotTab();
        newTask.fillDatePickerField(BrowserTypeDataProvider.getBrowserType(), DateProvider.currentDate());
        newTask.clickSelectFixedHoursField();

        Assert.assertTrue(newTask.timeValueIsPresent(), "Time value isn't present!!!");
        DataTime = newTask.clickTimeValue(0);

        newTask.clickCommentField();
        newTask.clickCreateTaskButton();

        PatientDetailPlanningPage patientDetailPage = new PatientDetailPlanningPage(driver);
        Assert.assertTrue(patientDetailPage.successfullyPopUpIsPresent(), "Failed! Successfully popup isn't appeared!");
        patientDetailPage.clickClosePopUpButton();

        Assert.assertTrue(patientDetailPage.listDayTasksIsPresent(), "Failed! Created task hasn't appear on the patient detail page!");
        Assert.assertEquals(patientDetailPage.getDateTaskTime(0), newTask.changeDataTime(DataTime));
        Assert.assertEquals(patientDetailPage.getTaskName(0), taskName);
    }

    @AfterMethod
    public void deleteNewPatientCard() {
        ClosePatientCardHelper closePatientCard = new ClosePatientCardHelper(driver);
        closePatientCard.closePatientCardHelper();
    }
}
