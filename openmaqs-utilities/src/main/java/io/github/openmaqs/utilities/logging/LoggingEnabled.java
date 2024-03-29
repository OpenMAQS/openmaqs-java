/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.utilities.logging;

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