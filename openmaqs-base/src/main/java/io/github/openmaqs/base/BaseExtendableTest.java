/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.base;

import java.lang.reflect.Method;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;

/**
 * The type Base extendable test.
 *
 * @param <T> the type parameter
 */
public abstract class BaseExtendableTest<T extends ITestObject> extends BaseTest {

  /**
   * Instantiates a new Base extendable test.
   */
  protected BaseExtendableTest() {
  }

  @Override
  @SuppressWarnings("unchecked")
  public T getTestObject() {
    return (T) super.getTestObject();
  }

  @BeforeMethod
  @Override
  public void setup(Method method, ITestContext testContext) {
    super.setup(method, testContext);
  }

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
