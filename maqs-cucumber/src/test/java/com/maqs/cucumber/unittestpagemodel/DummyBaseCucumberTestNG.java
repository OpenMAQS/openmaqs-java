/*
 * Copyright 2022 (C) MAQS-Framework, All rights Reserved
 */

package com.maqs.cucumber.unittestpagemodel;

import com.maqs.base.BaseGenericTest;
import com.maqs.base.BaseTest;
import com.maqs.cucumber.BaseCucumberTestNG;

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
