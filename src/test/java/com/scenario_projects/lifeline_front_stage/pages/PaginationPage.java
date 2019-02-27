package com.scenario_projects.lifeline_front_stage.pages;

import com.scenario_projects.lifeline_front_stage.logging.CustomReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PaginationPage extends BasePage {

    public PaginationPage(WebDriver driver) {
        super(driver);
    }

    public boolean paginationBlockIsPresent(By pagBlock) {
        CustomReporter.logAction("CHECK THAT THE ELEMENTS ARE PRESENT ON THE DASHBOARD PAGE");
        return driver.findElements(pagBlock).size() > 0;
    }

    public void clickPaginationItems(List<WebElement> list2, int i) {
        list2.get(i).click();
    }
}
