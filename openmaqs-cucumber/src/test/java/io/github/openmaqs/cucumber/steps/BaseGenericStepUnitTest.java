/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.cucumber.steps;

import io.github.openmaqs.base.BaseGenericTest;
import io.github.openmaqs.cucumber.ScenarioContext;
import io.github.openmaqs.cucumber.unittestpagemodel.DummyBaseGenericStep;
import io.github.openmaqs.utilities.helper.TestCategories;
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