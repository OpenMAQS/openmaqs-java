/*
 *  Copyright 2022 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.utilities.helper.exceptions;

/**
 * The Framework Configuration Exception class.
 */
public class MaqsConfigException extends RuntimeException {

  /**
   * Instantiates a new Framework configuration exception.
   *
   * @param message the message
   */
  public MaqsConfigException(String message) {
    super(message);
  }
}
