/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.maqs.base.exceptions;

/**
 * The type Driver disposal exception.
 */
public class DriverDisposalException extends RuntimeException {

  /**
   * Instantiates a new Driver disposal exception.
   *
   * @param message   the message
   * @param exception the exception
   */
  public DriverDisposalException(String message, Exception exception) {
    super(message, exception);
  }
}
