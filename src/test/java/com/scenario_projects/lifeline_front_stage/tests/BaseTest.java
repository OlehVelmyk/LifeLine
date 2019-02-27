package com.scenario_projects.lifeline_front_stage.tests;

import com.scenario_projects.lifeline_front_stage.logging.EventHandler;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

import static com.scenario_projects.lifeline_front_stage.utils.DriverFactory.initDriver;

public class BaseTest {
    protected EventFiringWebDriver driver;
    protected String baseUrl = "https://lifeline-front-stage.scenario-projects.com/auth/login";
    protected String url = "https://www.google.com/";

    @BeforeClass
    @Parameters("browser")
    public void setUpDriver(@Optional("chrome") String browser) {
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
}
