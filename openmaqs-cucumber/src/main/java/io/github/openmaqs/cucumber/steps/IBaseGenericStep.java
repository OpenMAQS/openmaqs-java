/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.cucumber.steps;

import io.github.openmaqs.base.BaseTestObject;
import io.github.openmaqs.utilities.logging.ILogger;

public interface IBaseGenericStep {

  /**
   * Get the logger.
   *
   * @return The logger
   */
  ILogger getLogger();

  /**
   * Get the generic test object.
   *
   * @return The generic test object
   */
  BaseTestObject getTestObject();
}
