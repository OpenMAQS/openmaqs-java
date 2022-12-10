/*
 * Copyright 2022 (C) MAQS-Framework, All rights Reserved
 */

package stepdefs.selenium;

import com.maqs.cucumber.steps.BaseSeleniumStep;
import io.cucumber.java.en.When;

public class SeleniumSteps2  extends BaseSeleniumStep {

    @When("Selenium Step2")
    public void Step2() {
        this.getLogger().logMessage("Step2");
    }
}
