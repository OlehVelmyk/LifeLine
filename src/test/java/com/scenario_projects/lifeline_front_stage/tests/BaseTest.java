package com.scenario_projects.lifeline_front_stage.tests;

import com.scenario_projects.lifeline_front_stage.logging.EventHandler;
import com.scenario_projects.lifeline_front_stage.logging.ScreenshotListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static com.scenario_projects.lifeline_front_stage.utils.DriverFactory.initDriver;


@Listeners({ScreenshotListener.class, /*TestFinish.class*/})
public class BaseTest {
    protected static EventFiringWebDriver driver;
    protected String baseUrl = "https://lifeline-front-stage.scenario-projects.com/auth/login";
    protected String url = "https://www.google.com/";

    public static EventFiringWebDriver getDriver() {
        return driver;
    }

    @BeforeClass
    @Parameters("browser")
    public void setUpDriver(@Optional("firefox") String browser) {
        driver = new EventFiringWebDriver(initDriver(browser));
        driver.register(new EventHandler());

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDownDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite
    public void sendReportToEmail() {
        //AddFileToArchive addFileToArchive = new AddFileToArchive();
        //addFileToArchive.createArchive();
        //SendMailSSLWithAttachment.sendReportFileToMail();
        //DeleteReportZipFile.deleteZipFile();
    }
}
