package com.scenario_projects.lifeline_front_stage.logging;

import com.scenario_projects.lifeline_front_stage.tests.BaseTest;
import com.scenario_projects.lifeline_front_stage.utils.ReportFolderPath;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;

import static com.scenario_projects.lifeline_front_stage.dataProvider.DateProvider.currentDate;
import static com.scenario_projects.lifeline_front_stage.dataProvider.DateProvider.newCurrentTime;

public class ScreenshotListener extends TestListenerAdapter {
    ReportFolderPath reportFolderPath = new ReportFolderPath();

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        File file;
        if (!iTestResult.isSuccess()) {
            file = makeScreenshot(iTestResult, BaseTest.getDriver());
            addScreenshotToReportFile(file);
        }
    }

    private File makeScreenshot(ITestResult iTestResult, WebDriver driver) {
        String className = iTestResult.getInstanceName();
        String path;
        try {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String filePath = reportFolderPath.getPathToReportFolder();
            File newFile = new File(filePath + className + "-"
                    + currentDate() + "_" + newCurrentTime() + ".png");
            FileUtils.copyFile(scrFile, newFile);
            return newFile;
        } catch (IOException e) {
            path = "Failed to capture screenshot: " + e.getMessage();
        }
        return null;
    }

    private void addScreenshotToReportFile(File newFile) {
        String absolutePath = newFile.getAbsolutePath();
        Reporter.log("<a href=\"" + absolutePath + "\"><p align=\"left\">Failed Screenshot from " +
                currentDate() + " " + newCurrentTime() + "</p>");
        Reporter.log("<br> <a href='" + absolutePath + "'> <img src='" + absolutePath +
                "' height='600' width='1000'/> </a><br />");
        Reporter.setCurrentTestResult(null);
    }
}
