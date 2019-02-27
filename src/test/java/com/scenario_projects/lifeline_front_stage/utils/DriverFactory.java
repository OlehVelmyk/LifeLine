package com.scenario_projects.lifeline_front_stage.utils;

import com.scenario_projects.lifeline_front_stage.dataProvider.BrowserTypeDataProvider;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaDriver;

import java.io.File;

public class DriverFactory {
    /**
     * @param browser Driver type to use in tests.
     * @return New instance of {@link WebDriver} object.
     */
    public static WebDriver initDriver(String browser) {
        switch (browser) {
            case "firefox":
                System.setProperty(
                        "webdriver.gecko.driver",
                        new File(DriverFactory.class.getResource("/" + getDriverFileName(browser)).getFile()).getPath());
                BrowserTypeDataProvider.setBrowserType(browser);
                return new FirefoxDriver();
            case "ie":
            case "internet explorer":
                System.setProperty(
                        "webdriver.ie.driver",
                        new File(DriverFactory.class.getResource("/IEDriverServer.exe").getFile()).getPath());
                InternetExplorerOptions ieOptions = new InternetExplorerOptions()
                        .destructivelyEnsureCleanSession()
                        .setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT)
                        .enablePersistentHovering()
                        .requireWindowFocus();
                ieOptions.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
                BrowserTypeDataProvider.setBrowserType(browser);
                return new InternetExplorerDriver(ieOptions);
            case "opera":
                System.setProperty(
                        "webdriver.opera.driver",
                        new File(DriverFactory.class.getResource("/" + getDriverFileName(browser)).getFile()).getPath());
                BrowserTypeDataProvider.setBrowserType(browser);
                return new OperaDriver();
            case "chrome":
            default:
                System.setProperty(
                        "webdriver.chrome.driver",
                        new File(DriverFactory.class.getResource("/" + getDriverFileName(browser)).getFile()).getPath());
                BrowserTypeDataProvider.setBrowserType(browser);
                return new ChromeDriver();
        }
    }

    private static String getDriverFileName(String browserName) {
        String webDriver = null;
        switch (browserName) {
            case "chrome":
                webDriver = "chromedriver";
                break;
            case "opera":
                webDriver = "operadriver";
                break;
            default:
                webDriver = "geckodriver";
        }
        String osName = System.getProperty("os.name");
        if (osName.contains("nix"))
            return webDriver;
        if (osName.contains("Windows"))
            return webDriver + ".exe";
        return webDriver;
    }
}
