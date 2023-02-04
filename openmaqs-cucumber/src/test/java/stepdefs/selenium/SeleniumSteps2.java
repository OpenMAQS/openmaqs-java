/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package stepdefs.selenium;

import io.github.openmaqs.cucumber.steps.BaseSeleniumStep;
import io.cucumber.java.en.When;

public class SeleniumSteps2  extends BaseSeleniumStep {

    @When("Selenium Step2")
    public void Step2() {
        this.getLogger().logMessage("Step2");
    }
}
