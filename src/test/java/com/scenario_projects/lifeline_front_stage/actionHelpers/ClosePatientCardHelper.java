package com.scenario_projects.lifeline_front_stage.actionHelpers;

import com.scenario_projects.lifeline_front_stage.dataProvider.MenuDataProvider;
import com.scenario_projects.lifeline_front_stage.pages.ArchivePage;
import com.scenario_projects.lifeline_front_stage.pages.PatientsPage;
import com.scenario_projects.lifeline_front_stage.pages.SideBarPanel;
import com.scenario_projects.lifeline_front_stage.pages.SideBarPatientDetailPagePanel;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ClosePatientCardHelper {
    protected WebDriver driver;

    public ClosePatientCardHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void closePatientCardHelper() {

        SideBarPatientDetailPagePanel patientPanel = new SideBarPatientDetailPagePanel(driver);
        patientPanel.clickCloseFileLink();

        Assert.assertTrue(patientPanel.closePatientPopupIsPresent(), "Failed! Close patient popup is appeared!");

        patientPanel.fillInCommentField();
        patientPanel.clickCloseFileButton();

        PatientsPage patientPage = new PatientsPage(driver);
        Assert.assertTrue(patientPage.successfullyPopUpIsPresent(), "Failed! Successfully deleted patient card is loaded!");

        patientPage.clickClosePopUpButton();

        CheckThatPatientCardIsDeleted checkThatPatientCardIsDeleted = new CheckThatPatientCardIsDeleted(driver);
        Assert.assertEquals(checkThatPatientCardIsDeleted.checkThatPatientCardIsDeletedFromPatientPage(), 0);

        //Select Archive menu item on sidebar panel
        SideBarPanel sideBarPanel = new SideBarPanel(driver);
        sideBarPanel.chooseMenuItem(MenuDataProvider.archivePage);

        ArchivePage archivePage = new ArchivePage(driver);
        Assert.assertTrue(archivePage.patientCardsIsPresent(), "Failed! Archive page isn't loaded!");

        Assert.assertEquals(checkThatPatientCardIsDeleted.checkThatPatientCardIsPresentOnArchivePage(), 1);
    }
}