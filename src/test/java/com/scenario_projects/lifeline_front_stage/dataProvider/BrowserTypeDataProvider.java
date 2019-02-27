package com.scenario_projects.lifeline_front_stage.dataProvider;

public class BrowserTypeDataProvider {
    private static String browserType;

    public static String getBrowserType() {
        return browserType;
    }

    public static void setBrowserType(String browserType) {
        BrowserTypeDataProvider.browserType = browserType;
    }
}
