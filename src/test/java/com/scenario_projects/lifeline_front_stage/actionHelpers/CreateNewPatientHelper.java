package com.scenario_projects.lifeline_front_stage.actionHelpers;

import com.scenario_projects.lifeline_front_stage.dataProvider.BrowserTypeDataProvider;
import com.scenario_projects.lifeline_front_stage.dataProvider.MenuDataProvider;
import com.scenario_projects.lifeline_front_stage.model.PatientCardData;
import com.scenario_projects.lifeline_front_stage.pages.CreateNewPatientPage;
import com.scenario_projects.lifeline_front_stage.pages.PatientsPage;
import com.scenario_projects.lifeline_front_stage.pages.SideBarPanel;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CreateNewPatientHelper {
    protected WebDriver driver;

    public CreateNewPatientHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void createNewPatientHelper() {

        //Go to Patients page
        SideBarPanel sideBarPanel = new SideBarPanel(driver);
        sideBarPanel.chooseMenuItem(MenuDataProvider.patientsPage);

        //Check that filter button is present on patients page
        PatientsPage patientsPage = new PatientsPage(driver);
        Assert.assertTrue(patientsPage.filterButtonInactiveIsPresent(), "Failed! Patients page isn't loaded!");

        patientsPage.clickCreateNewPatientButton();

        //Check that create new patient page is loaded
        CreateNewPatientPage createNewPatientPage = new CreateNewPatientPage(driver);
        Assert.assertTrue(createNewPatientPage.patientCardsIsPresent(), "Failed! Create new patient page isn't loaded!");

        PatientCardData newPatient = new PatientCardData("New patient " + System.currentTimeMillis(), createNewPatientPage.getUnitType(),
                "02031975", createNewPatientPage.getGenderType(), "UKRAINE", "643993", "Popova street, 5");

        //Fill in required field
        createNewPatientPage.fillFullNameField(newPatient.getName());
        createNewPatientPage.clickUnitFromUnitField();
        createNewPatientPage.selectUnitType();
        createNewPatientPage.fillBirthDateField(BrowserTypeDataProvider.getBrowserType());
        createNewPatientPage.selectGenderType();
        createNewPatientPage.selectCountry();
        createNewPatientPage.fillPostCodeField(newPatient.getPostCode());
        createNewPatientPage.fillAddressField(newPatient.getAddress());

        //Go to next creating patient page
        createNewPatientPage.clickProceedButton();
        createNewPatientPage.clickProceedButton();

        //Save a new patient card
        createNewPatientPage.clickSaveButton();

        Assert.assertTrue(patientsPage.successfullyPopUpIsPresent(), "Failed! Successfully created patient card isn't loaded!");

        patientsPage.clickClosePopUpButton();
        patientsPage.clickOnCreatedNewPatientCard();
    }

}
