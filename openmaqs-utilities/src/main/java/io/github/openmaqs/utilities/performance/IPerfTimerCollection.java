/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.utilities.performance;

import io.github.openmaqs.utilities.logging.Logger;

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
