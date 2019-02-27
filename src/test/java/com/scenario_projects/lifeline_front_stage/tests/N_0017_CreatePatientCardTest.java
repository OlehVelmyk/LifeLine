package com.scenario_projects.lifeline_front_stage.tests;

import com.scenario_projects.lifeline_front_stage.actionHelpers.ClosePatientCardHelper;
import com.scenario_projects.lifeline_front_stage.actionHelpers.CreateNewPatientHelper;
import com.scenario_projects.lifeline_front_stage.actionHelpers.LoginHelper;
import com.scenario_projects.lifeline_front_stage.dataProvider.LoginDataProvider;
import com.scenario_projects.lifeline_front_stage.model.PatientCardData;
import com.scenario_projects.lifeline_front_stage.pages.SideBarPatientDetailPagePanel;
import com.scenario_projects.lifeline_front_stage.utils.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class N_0017_CreatePatientCardTest extends BaseTest {

    @BeforeMethod
    public void login() {
        driver.get(baseUrl);
        LoginHelper login = new LoginHelper(driver);
        login.login(LoginDataProvider.email, LoginDataProvider.password);
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void createdPatientCard() {
        //Create a new patient
        CreateNewPatientHelper newPatient = new CreateNewPatientHelper(driver);
        newPatient.createNewPatientHelper();

        SideBarPatientDetailPagePanel patientPanel = new SideBarPatientDetailPagePanel(driver);
        patientPanel.clickPatientProfileLink();

        //Verify patients data
        Assert.assertEquals(patientPanel.getFullName(), PatientCardData.getName());
        Assert.assertEquals(patientPanel.getUnitName(), PatientCardData.getUnit());
        Assert.assertEquals(patientPanel.getBirthDate(), PatientCardData.getBirthDate());
        Assert.assertEquals(patientPanel.getGenderName(), PatientCardData.getGender());
        Assert.assertEquals(patientPanel.getCountryName(), PatientCardData.getCountry());
        Assert.assertEquals(patientPanel.getPostCodeValue(), PatientCardData.getPostCode());
        Assert.assertEquals(patientPanel.getAddressValue(), PatientCardData.getAddress());
    }

    @AfterMethod
    public void deleteNewPatientCard() {
        ClosePatientCardHelper closePatientCard = new ClosePatientCardHelper(driver);
        closePatientCard.closePatientCardHelper();
    }
}
