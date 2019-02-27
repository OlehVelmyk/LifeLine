package com.scenario_projects.lifeline_front_stage.tests;

import com.scenario_projects.lifeline_front_stage.actionHelpers.LoginHelper;
import com.scenario_projects.lifeline_front_stage.dataProvider.LoginDataProvider;
import com.scenario_projects.lifeline_front_stage.dataProvider.MenuDataProvider;
import com.scenario_projects.lifeline_front_stage.pages.PatientsPage;
import com.scenario_projects.lifeline_front_stage.pages.SideBarPanel;
import com.scenario_projects.lifeline_front_stage.utils.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class N_0007_RefreshPatientsPageTest extends BaseTest {

    @BeforeMethod
    public void login() {
        driver.get(baseUrl);

        LoginHelper login = new LoginHelper(driver);
        login.login(LoginDataProvider.email, LoginDataProvider.password);
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void refreshPatientsPage() {

        //Go to Patients page
        SideBarPanel sideBarPanel = new SideBarPanel(driver);
        sideBarPanel.chooseMenuItem(MenuDataProvider.patientsPage);

        //Check that filter button is present on patients page
        PatientsPage patientsPage = new PatientsPage(driver);
        Assert.assertTrue(patientsPage.filterButtonInactiveIsPresent(), "Failed! Patients page is not loaded!");

        patientsPage.clickRefreshButton();

        Assert.assertTrue(patientsPage.filterButtonInactiveIsPresent(), "Failed! Patients page is not loaded!");
    }
}
