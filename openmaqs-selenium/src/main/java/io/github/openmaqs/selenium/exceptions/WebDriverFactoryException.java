/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.selenium.exceptions;

import io.github.openmaqs.base.exceptions.MAQSRuntimeException;

/**
 * The type Web driver factory exception.
 */
public class WebDriverFactoryException extends MAQSRuntimeException {

  /**
   * Instantiates a new Web driver factory exception.
   *
   * @param message   the message
   * @param exception the exception
   */
  public WebDriverFactoryException(String message, Exception exception) {
    super(message, exception);
  }
}
