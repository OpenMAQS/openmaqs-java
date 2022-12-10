/*
 * Copyright 2022 (C) MAQS-Framework, All rights Reserved
 */

package com.maqs.selenium.exceptions;

import com.maqs.base.exceptions.MAQSRuntimeException;

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
