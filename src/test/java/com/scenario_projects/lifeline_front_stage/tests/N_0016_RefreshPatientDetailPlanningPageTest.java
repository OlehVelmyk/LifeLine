package com.scenario_projects.lifeline_front_stage.tests;

import com.scenario_projects.lifeline_front_stage.actionHelpers.ClosePatientCardHelper;
import com.scenario_projects.lifeline_front_stage.actionHelpers.CreateNewPatientHelper;
import com.scenario_projects.lifeline_front_stage.actionHelpers.LoginHelper;
import com.scenario_projects.lifeline_front_stage.dataProvider.LoginDataProvider;
import com.scenario_projects.lifeline_front_stage.pages.PatientDetailPlanningPage;
import com.scenario_projects.lifeline_front_stage.utils.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class N_0016_RefreshPatientDetailPlanningPageTest extends BaseTest {

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
    public void refreshPatientsDetailPlanningPage() {
        PatientDetailPlanningPage patientDetailPage = new PatientDetailPlanningPage(driver);
        patientDetailPage.clickRefreshButton();

        Assert.assertTrue(patientDetailPage.calendarDateIsPresent(), "Failed! Dashboard page is not loaded!");
    }

    @AfterMethod
    public void deleteNewPatientCard() {
        ClosePatientCardHelper closePatientCard = new ClosePatientCardHelper(driver);
        closePatientCard.closePatientCardHelper();
    }
}
