/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.cucumber;

import io.github.openmaqs.base.BaseTest;
import io.github.openmaqs.selenium.BaseSeleniumTest;
import io.github.openmaqs.utilities.helper.TestCategories;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit test class for BaseSeleniumCucumber class.
 */
public class BaseSeleniumCucumberUnitTest {

    /**
     * Verifies the specific base test object is a BaseSeleniumTest Object
     */
    @Test(groups = TestCategories.CUCUMBER)
    public void testCreateSpecificBaseTest() {
        BaseSeleniumCucumber seleniumCucumber = new BaseSeleniumCucumber();
        BaseTest testObject = seleniumCucumber.createSpecificBaseTest();
        Assert.assertTrue(testObject instanceof BaseSeleniumTest);
    }
}