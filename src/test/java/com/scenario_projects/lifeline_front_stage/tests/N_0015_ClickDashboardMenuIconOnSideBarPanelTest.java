package com.scenario_projects.lifeline_front_stage.tests;

import com.scenario_projects.lifeline_front_stage.actionHelpers.LoginHelper;
import com.scenario_projects.lifeline_front_stage.dataProvider.LoginDataProvider;
import com.scenario_projects.lifeline_front_stage.dataProvider.MenuDataProvider;
import com.scenario_projects.lifeline_front_stage.pages.ArchivePage;
import com.scenario_projects.lifeline_front_stage.pages.DashBoardPage;
import com.scenario_projects.lifeline_front_stage.pages.SideBarPanel;
import com.scenario_projects.lifeline_front_stage.utils.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class N_0015_ClickDashboardMenuIconOnSideBarPanelTest extends BaseTest {

    @BeforeMethod
    public void createPatientCard() {
        driver.get(baseUrl);

        //Login to the system
        LoginHelper login = new LoginHelper(driver);
        login.login(LoginDataProvider.email, LoginDataProvider.password);
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void goToDashboardPage() {

        //Select Archive menu item on sidebar panel
        SideBarPanel sideBarPanel = new SideBarPanel(driver);
        sideBarPanel.chooseMenuItem(MenuDataProvider.archivePage);

        ArchivePage archivePage = new ArchivePage(driver);
        Assert.assertTrue(archivePage.patientCardsIsPresent(), "Failed! Archive page isn't loaded!");

        //Select DashBoard menu item on sidebar panel
        sideBarPanel.chooseMenuItem(MenuDataProvider.dashboard);

        DashBoardPage dashBoard = new DashBoardPage(driver);
        Assert.assertTrue(dashBoard.blocksIsPresent(), "Failed! DashBoard page isn't loaded!");
    }
}
