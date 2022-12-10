/*
 * Copyright 2022 (C) MAQS-Framework, All rights Reserved
 */

package com.maqs.cucumber.steps;

import com.maqs.base.BaseGenericTest;
import com.maqs.cucumber.ScenarioContext;
import com.maqs.cucumber.unittestpagemodel.DummyBaseGenericStep;
import com.maqs.utilities.helper.TestCategories;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit test class for BaseGenericStep class.
 */
public class BaseGenericStepUnitTest extends BaseGenericTest {

    /**
     * Verifies the Logger is not null
     */
    @Test(groups = TestCategories.CUCUMBER)
    public void testGetLogger() {
        DummyBaseGenericStep stepClass = new DummyBaseGenericStep();
        ScenarioContext.put(ScenarioContext.MAQS_HOLDER, this);
        Assert.assertNotNull(stepClass.getLogger(), "Checking that Logger is not null through BaseGenericStep");
    }

    /**
     * Verifies the Test Object is not null
     */
    @Test(groups = TestCategories.CUCUMBER)
    public void testGetTestObject() {
        DummyBaseGenericStep stepClass = new DummyBaseGenericStep();
        ScenarioContext.put(ScenarioContext.MAQS_HOLDER, this);
        Assert.assertNotNull(stepClass.getTestObject(), "Checking that Test Object is not null through BaseGenericStep");
    }
}