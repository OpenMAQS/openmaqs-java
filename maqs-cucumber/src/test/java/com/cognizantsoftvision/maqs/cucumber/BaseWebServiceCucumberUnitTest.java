package com.cognizantsoftvision.maqs.cucumber;

import com.cognizantsoftvision.maqs.base.BaseTest;
import com.cognizantsoftvision.maqs.webservices.BaseWebServiceTest;
import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
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