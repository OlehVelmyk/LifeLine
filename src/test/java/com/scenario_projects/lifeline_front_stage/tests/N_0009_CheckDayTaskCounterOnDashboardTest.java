package com.scenario_projects.lifeline_front_stage.tests;

import com.scenario_projects.lifeline_front_stage.actionHelpers.CountDayTaskNumber;
import com.scenario_projects.lifeline_front_stage.actionHelpers.LoginHelper;
import com.scenario_projects.lifeline_front_stage.dataProvider.LoginDataProvider;
import com.scenario_projects.lifeline_front_stage.pages.DashBoardPage;
import com.scenario_projects.lifeline_front_stage.pages.PatientsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class N_0009_CheckDayTaskCounterOnDashboardTest extends BaseTest {
    private int dailyTaskCounterFromDashboard;
    private int dailyTaskCounterFromPatientPage;

    @BeforeMethod
    public void login() {
        driver.get(baseUrl);

        LoginHelper login = new LoginHelper(driver);
        login.login(LoginDataProvider.email, LoginDataProvider.password);
    }

    @Test//(retryAnalyzer = RetryAnalyzer.class)
    public void checkTaskDayCounter() {

        //Get counter tasks day at the Dashboard
        DashBoardPage dashBoardPage = new DashBoardPage(driver);
        dailyTaskCounterFromDashboard = dashBoardPage.getTaskDayCounter();

        //Click on counter tasks day on the Dashboard
        dashBoardPage.clickTaskDayCounter();

        PatientsPage patientsPage = new PatientsPage(driver);
        Assert.assertTrue(patientsPage.filterButtonActiveIsPresent(), "Failed! Patients page isn't loaded!");

        //Get counter tasks day at the Patients detail page
        CountDayTaskNumber countDayTaskNumber = new CountDayTaskNumber(driver);
        dailyTaskCounterFromPatientPage = countDayTaskNumber.countDayTaskNumberOnPatientsDetailPage();

        //Check that counter tasks day at the Dashboard equals counter tasks day at the Patients page
        Assert.assertEquals(dailyTaskCounterFromPatientPage, dailyTaskCounterFromDashboard);
    }
}
