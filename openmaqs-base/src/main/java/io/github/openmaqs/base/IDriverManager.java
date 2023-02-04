/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.base;

import io.github.openmaqs.utilities.logging.ILogger;

/**
 * The Driver Manager interface class.
 * @param <T> the type of driver being used
 */
public interface IDriverManager<T> extends AutoCloseable {

  /**
   * Gets the testing object logger.
   */
  ILogger getLogger();

  /**
   * Gets base driver.
   */
  Object getBaseDriver();

  /**
   * Sets base driver.
   */
  void setBaseDriver(T baseDriver);

  /**
   * Is driver initialized boolean.
   */
  boolean isDriverInitialized();
}
