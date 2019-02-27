package com.scenario_projects.lifeline_front_stage.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;

import java.io.File;
import java.io.IOException;

import static com.scenario_projects.lifeline_front_stage.dataProvider.DateProvider.newCurrentTime;

public class CaptureScreen {

    public static void captureScreen(WebDriver driver, String testName) {
        String path;
        try {
            WebDriver augmentedDriver = new Augmenter().augment(driver);
            File source = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
            path = "./target/screenshots/" + testName + "_" + newCurrentTime();
            FileUtils.copyFile(source, new File(path));
        } catch (IOException e) {
            path = "Failed to capture screenshot: " + e.getMessage();
        }
    }
}
