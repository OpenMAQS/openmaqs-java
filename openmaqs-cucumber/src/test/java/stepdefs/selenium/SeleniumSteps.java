/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package stepdefs.selenium;

import io.github.openmaqs.cucumber.steps.BaseSeleniumStep;
import io.cucumber.java.en.Given;

public class SeleniumSteps extends BaseSeleniumStep {

    @Given("^Selenium Step$")
    public void Step() {
        this.getDriver().navigate().to("https://magenicautomation.azurewebsites.net/");
        this.getLogger().logMessage("info");
    }
}
