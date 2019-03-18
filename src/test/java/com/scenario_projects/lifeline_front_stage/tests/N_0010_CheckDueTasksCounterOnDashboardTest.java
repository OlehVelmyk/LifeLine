package com.scenario_projects.lifeline_front_stage.tests;

import com.scenario_projects.lifeline_front_stage.actionHelpers.CountDueTaskNumber;
import com.scenario_projects.lifeline_front_stage.actionHelpers.LoginHelper;
import com.scenario_projects.lifeline_front_stage.dataProvider.LoginDataProvider;
import com.scenario_projects.lifeline_front_stage.pages.DashBoardPage;
import com.scenario_projects.lifeline_front_stage.pages.PatientsPage;
import com.scenario_projects.lifeline_front_stage.utils.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class N_0010_CheckDueTasksCounterOnDashboardTest extends BaseTest {
    private int dueCounterFromDashboard;
    private int dueCounterFromPatientPage;

    @BeforeMethod
    public void login() {
        driver.get(baseUrl);

        LoginHelper login = new LoginHelper(driver);
        login.login(LoginDataProvider.email, LoginDataProvider.password);
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void checkTaskDayCounter() {

        //Get counter due tasks at the Dashboard
        DashBoardPage dashBoardPage = new DashBoardPage(driver);
        dueCounterFromDashboard = dashBoardPage.getDueTaskCounterOnDashBoard();

        //Click on counter tasks day on the Dashboard
        dashBoardPage.clickDueTaskCounter();

        PatientsPage patientsPage = new PatientsPage(driver);
        Assert.assertTrue(patientsPage.filterButtonActiveIsPresent(), "Failed! Patients page is not loaded!");

        //Get counter due tasks  at the Patients page
        CountDueTaskNumber countDueTaskNumber = new CountDueTaskNumber(driver);
        dueCounterFromPatientPage = countDueTaskNumber.DueTasksNumberOnPatientsPage();

        //Check that counter tasks day at the Dashboard equals counter tasks day at the Patients page
        Assert.assertEquals(dueCounterFromPatientPage, dueCounterFromDashboard);
    }

}
