/*
 * Copyright 2022 (C) MAQS-Framework, All rights Reserved
 */

package stepdefs.selenium;

import com.maqs.cucumber.steps.BaseSeleniumStep;
import io.cucumber.java.en.Then;

public class SeleniumSteps3 extends BaseSeleniumStep {

    @Then("Selenium Step3")
    public void Step3() throws Throwable {
        this.getLogger().logMessage("Step3");
    }
}
