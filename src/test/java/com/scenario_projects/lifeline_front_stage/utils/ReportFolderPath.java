package com.scenario_projects.lifeline_front_stage.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReportFolderPath {

    public String getPathToReportFolder() {
        String path;
        try {
            InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("my.properties");
            Properties prop = new Properties();
            prop.load(resourceAsStream);
            String reportFolder = prop.getProperty("sureFireDir");
            return reportFolder + "html/";
        } catch (IOException e) {
            path = "Failed to capture screenshot: " + e.getMessage();
        }
        return null;
    }
}
