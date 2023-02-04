/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.cucumber.unittestpagemodel;

import io.github.openmaqs.base.BaseGenericTest;
import io.github.openmaqs.base.BaseTest;
import io.github.openmaqs.cucumber.BaseCucumberTestNG;

/**
 * A Dummy BaseCucumberTestNG class for testing
 */
public class DummyBaseCucumberTestNG extends BaseCucumberTestNG {
    /**
     * Create a test object.
     *
     * @return A generic base test object
     */
    @Override
    public BaseTest createSpecificBaseTest() {
        return new BaseGenericTest();
    }
}
