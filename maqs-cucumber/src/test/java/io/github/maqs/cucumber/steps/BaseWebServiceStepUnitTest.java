/*
 * Copyright 2022 (C) MAQS, All rights Reserved
 */

package io.github.maqs.cucumber.steps;

import io.github.maqs.cucumber.ScenarioContext;
import io.github.maqs.cucumber.unittestpagemodel.DummyBaseWebServiceStep;
import io.github.maqs.webservices.BaseWebServiceTest;
import io.github.maqs.utilities.helper.TestCategories;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit test class for BaseSeleniumStep class.
 */
public class BaseWebServiceStepUnitTest extends BaseWebServiceTest {

    /**
     * Verifies the Driver is not null
     */
    @Test(groups = TestCategories.CUCUMBER)
    public void testGetDriver() {
        DummyBaseWebServiceStep stepClass = new DummyBaseWebServiceStep();
        ScenarioContext.put(ScenarioContext.MAQS_HOLDER, this);
        Assert.assertNotNull(stepClass.getDriver(), "Checking that WebService Driver is not null through BaseWebServiceStep");
    }

    /**
     * Verifies the Test Object is not null
     */
    @Test(groups = TestCategories.CUCUMBER)
    public void testGetTestObject() {
        DummyBaseWebServiceStep stepClass = new DummyBaseWebServiceStep();
        ScenarioContext.put(ScenarioContext.MAQS_HOLDER, this);
        Assert.assertNotNull(stepClass.getTestObject(), "Checking that Test Object is not null through BaseWebServiceStep");
    }
}