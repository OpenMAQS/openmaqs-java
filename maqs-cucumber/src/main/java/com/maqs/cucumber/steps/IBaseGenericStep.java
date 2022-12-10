/*
 * Copyright 2022 (C) MAQS-Framework, All rights Reserved
 */

package com.maqs.cucumber.steps;

import com.maqs.base.BaseTestObject;
import com.maqs.utilities.logging.ILogger;

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
