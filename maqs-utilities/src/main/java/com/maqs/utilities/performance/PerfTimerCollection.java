/*
 * Copyright 2022 (C) MAQS-Framework, All rights Reserved
 */

package com.maqs.utilities.performance;

import com.maqs.utilities.logging.ILogger;
import com.maqs.utilities.logging.Logger;

/**
 * The Performance Timer Collection class.
 * Object to be owned by Test Class (Object),
 * and passed to page Constructors to insert Performance Timers.
 */
public class PerfTimerCollection implements IPerfTimerCollection {

  /**
   * The logger.
   */
  protected ILogger log;

  /**
   * The Test Name.
   */
  private String testName;

  /**
   * Gets the Logger.
   *
   * @return The Logger
   */
  protected ILogger getLog() {
    return this.log;
  }

  /**
   * Sets the Logger.
   *
   * @param logger The logger to set.
   */
  public void setLog(Logger logger) {
    this.log = logger;
  }

  /**
   * Gets the Test Name.
   *
   * @return The Test Name
   */
  public String getTestName() {
    return this.testName;
  }

  /**
   * Sets the Test Name.
   *
   * @param testName The Test Name
   */
  public void setTestName(String testName) {
    this.testName = testName;
  }

  /**
   * Initializes a new instance of the PerfTimerCollection class.
   *
   * @param logger                 Logger to use
   * @param fullyQualifiedTestName Test Name
   */
  public PerfTimerCollection(ILogger logger, String fullyQualifiedTestName) {
    this.log = logger;
    this.testName = fullyQualifiedTestName;
  }
}
