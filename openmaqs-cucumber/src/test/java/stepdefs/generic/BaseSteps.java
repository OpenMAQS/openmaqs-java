/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package stepdefs.generic;

import io.github.openmaqs.cucumber.steps.BaseGenericStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BaseSteps extends BaseGenericStep {

    @Given("^Base Step$")
    public void Step() {

        this.getLogger().logMessage("Base step");
    }
    @When("Base Step2")
    public void Step2() {
        this.getLogger().logMessage("Base step2");
    }

    @Then("Base Step3")
    public void Step3() {
        this.getLogger().logMessage("Base step3");
    }
}