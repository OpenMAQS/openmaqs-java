/*
 * Copyright 2022 (C) MAQS-Framework, All rights Reserved
 */

package com.maqs.cucumber.steps;

import com.maqs.base.BaseTest;
import com.maqs.base.BaseTestObject;
import com.maqs.cucumber.ScenarioContext;
import com.maqs.utilities.logging.ILogger;

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
