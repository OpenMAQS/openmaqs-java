/*
 * Copyright 2022 (C) MAQS-Framework, All rights Reserved
 */

package com.maqs.utilities.logging;

/**
 * The Logging Enabled enum class.
 * Sets when and if to log the message.
 */
public enum LoggingEnabled {

  /**
   * Yes log.
   */
  YES,

  /**
   * Only save a log when there is a failure.
   */
  ONFAIL,

  /**
   * No, don't log.
   */
  NO,
}