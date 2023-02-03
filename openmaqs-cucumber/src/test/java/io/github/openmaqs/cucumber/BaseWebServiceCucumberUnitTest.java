/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.cucumber;

import io.github.openmaqs.base.BaseTest;
import io.github.openmaqs.webservices.BaseWebServiceTest;
import io.github.openmaqs.utilities.helper.TestCategories;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit test class for BaseSeleniumCucumber class.
 */
public class BaseWebServiceCucumberUnitTest {

    /**
     * Verifies the specific base test object is a BaseSeleniumTest Object
     */
    @Test(groups = TestCategories.CUCUMBER)
    public void testCreateSpecificBaseTest() {
        BaseWebServiceCucumber webserviceCucumber = new BaseWebServiceCucumber();
        BaseTest testObject = webserviceCucumber.createSpecificBaseTest();
        Assert.assertTrue(testObject instanceof BaseWebServiceTest);
    }
}