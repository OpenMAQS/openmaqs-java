/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.utilities.helper.exceptions;

/**
 * Definition of exceptions which will be thrown when there is a problem with loading elements of the MAQS app config.
 */
public class MaqsLoggingConfigException extends RuntimeException {

  /**
   * MAQS config exception.
   * @param message Takes an exception message
   */
  public MaqsLoggingConfigException(String message) {
    super(message);
  }
}
