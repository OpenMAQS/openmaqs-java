/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.maqs.appium.exceptions;

/**
 * The Appium Config Exception class.
 */
public class AppiumConfigException extends RuntimeException {

  /**
   * Throws an Appium Config Exception if an error occurs.
   * @param e the exception to be thrown
   */
  public AppiumConfigException(Exception e) {
    super(e);
  }
}
