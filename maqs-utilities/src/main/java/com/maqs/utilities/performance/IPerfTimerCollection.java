/*
 * Copyright 2022 (C) MAQS-Framework, All rights Reserved
 */

package com.maqs.utilities.performance;

import com.maqs.utilities.logging.Logger;

/**
 * The Performance Timer Collection interface class.
 */
public interface IPerfTimerCollection {

  /**
   * Sets the Logger.
   *
   * @param logger The logger to set.
   */
  void setLog(Logger logger);

  /**
   * Gets the Test Name.
   *
   * @return The Test Name
   */
  String getTestName();

  /**
   * Sets the Test Name.
   *
   * @param testName The Test Name
   */
  void setTestName(String testName);
}
