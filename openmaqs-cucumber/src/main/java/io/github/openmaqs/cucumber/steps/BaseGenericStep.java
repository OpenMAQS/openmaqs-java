/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.cucumber.steps;

import io.github.openmaqs.base.BaseTest;
import io.github.openmaqs.base.BaseTestObject;
import io.github.openmaqs.cucumber.ScenarioContext;
import io.github.openmaqs.utilities.logging.ILogger;

/**
 * Base generic cucumber step.
 */
public abstract class BaseGenericStep implements IBaseGenericStep {

  /**
   * Get the logger.
   *
   * @return The logger
   */
  public ILogger getLogger() {
    return ScenarioContext.get(ScenarioContext.MAQS_HOLDER, BaseTest.class).getLogger();
  }

  /**
   * Get the generic test object.
   *
   * @return The generic test object
   */
  public BaseTestObject getTestObject() {
    return (BaseTestObject) ScenarioContext.get(ScenarioContext.MAQS_HOLDER, BaseTest.class).getTestObject();
  }
}
