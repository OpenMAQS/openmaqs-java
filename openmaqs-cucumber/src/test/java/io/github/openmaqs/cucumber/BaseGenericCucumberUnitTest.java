/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.cucumber;

import io.github.openmaqs.base.BaseGenericTest;
import io.github.openmaqs.base.BaseTest;
import io.github.openmaqs.utilities.helper.TestCategories;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit test class for BaseGenericCucumber class.
 */
public class BaseGenericCucumberUnitTest {

    /**
     * Verifies the specific base test object is a BaseGenericTest Object
     */
    @Test(groups = TestCategories.CUCUMBER)
    public void testCreateSpecificBaseTest() {
        BaseGenericCucumber genericCucumber = new BaseGenericCucumber();
        BaseTest testObject = genericCucumber.createSpecificBaseTest();
        Assert.assertTrue(testObject instanceof BaseGenericTest);
    }
}