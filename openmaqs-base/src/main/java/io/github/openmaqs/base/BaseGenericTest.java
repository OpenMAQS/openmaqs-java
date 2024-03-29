/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.base;

import org.testng.ITestResult;

/**
 * Base Generic Test class for Unit Tests and Other testing scenarios.
 */
public class BaseGenericTest extends BaseExtendableTest<ITestObject> {

  @Override
  protected void beforeLoggingTeardown(ITestResult resultType) {
    // Before logging steps are not needed in this scenario
  }

  @Override
  protected void createNewTestObject() {
    this.setTestObject(
        new BaseTestObject(this.createLogger(), this.getFullyQualifiedTestClassName()));
  }
}