/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.utilities.logging;

import io.github.openmaqs.utilities.helper.Config;
import io.github.openmaqs.utilities.helper.StringProcessor;
import io.github.openmaqs.utilities.helper.exceptions.MaqsLoggingConfigException;
import java.io.File;

/**
 * The Logging Configuration class.
 * Gets the logging config data.
 */
public class LoggingConfig {

  public static final String CONSOLE = "CONSOLE";
  public static final String TXT = "TXT";

  private LoggingConfig() {
    // Hiding implicit constructor
  }

  /**
   * Get our logging state - Yes, no or on failure.
   *
   * @return The log enabled state
   */
  public static LoggingEnabled getLoggingEnabledSetting() {
    switch (Config.getGeneralValue("Log", "NO").toUpperCase()) {
      case "YES":
        return LoggingEnabled.YES;
      case "ONFAIL":
        return LoggingEnabled.ONFAIL;
      case "NO":
        return LoggingEnabled.NO;
      default:
        throw new MaqsLoggingConfigException(
            StringProcessor.safeFormatter("Log value %s is not a valid option", Config.getGeneralValue("Log", "NO")));
    }
  }

  /**
   * Get our logging level.
   *
   * @return MessageType - The current log level.
   */
  public static MessageType getLoggingLevelSetting() {
    switch (Config.getGeneralValue("LogLevel", "INFORMATION").toUpperCase()) {
      case "VERBOSE":
        return MessageType.VERBOSE;         // Includes this and all of those below
      case "INFORMATION":
        return MessageType.INFORMATION;     // Includes this and all of those below
      case "ACTION":
        return MessageType.ACTION;        // Includes this and all of those below
      case "GENERIC":
        return MessageType.GENERIC;         // Includes this and all of those below
      case "SUCCESS":
        return MessageType.SUCCESS;         // Includes this and all of those below
      case "WARNING":
        return MessageType.WARNING;         // Includes this and all of those below
      case "ERROR":
        return MessageType.ERROR;           // Includes errors only
      case "SUSPENDED":
        return MessageType.SUSPENDED;       // All logging is suspended
      default:
        throw new MaqsLoggingConfigException(
                "Logging level value " + Config.getGeneralValue("LogLevel") + " is not a valid option");
    }
  }

  /**
   * Get the logger.
   *
   * @param fileName File name to use for the log
   * @return The logger
   */
  public static Logger getLogger(String fileName) {
    // Disable logging means we just send any logged messages to the console
    if (getLoggingEnabledSetting() == LoggingEnabled.NO) {
      return new ConsoleLogger();
    }

    String logDirectory = getLogDirectory();
    MessageType loggingLevel = getLoggingLevelSetting();

    switch (Config.getGeneralValue("LogType", CONSOLE).toUpperCase()) {
      case CONSOLE:
        return new ConsoleLogger(loggingLevel);
      case TXT:
        return new FileLogger(false, logDirectory, fileName, loggingLevel);
      case "HTML":
        return new HtmlFileLogger(logDirectory, fileName, loggingLevel);
      default:
        throw new MaqsLoggingConfigException(StringProcessor
            .safeFormatter("Log type %s is not a valid option", Config.getGeneralValue("LogType", CONSOLE)));
    }
  }

  /**
   * Gets the File Directory to store log files.
   *
   * @return String of file path
   */
  public static String getLogDirectory() {
    String path = new File("").getAbsolutePath()
        .concat(File.separator + "target" + File.separator + "logs");
    return new File(Config.getGeneralValue("FileLoggerPath", path)).getAbsolutePath();
  }
}