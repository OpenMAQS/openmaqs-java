/*
 * Copyright 2022 (C) MAQS-Framework, All rights Reserved
 */

package com.maqs.appium.exceptions;

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
