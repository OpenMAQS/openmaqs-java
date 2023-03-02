/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.utilities.logger;

import io.github.openmaqs.utilities.helper.Config;
import io.github.openmaqs.utilities.helper.TestCategories;
import io.github.openmaqs.utilities.helper.exceptions.MaqsLoggingConfigException;
import io.github.openmaqs.utilities.logging.ConsoleLogger;
import io.github.openmaqs.utilities.logging.FileLogger;
import io.github.openmaqs.utilities.logging.HtmlFileLogger;
import io.github.openmaqs.utilities.logging.ILogger;
import io.github.openmaqs.utilities.logging.LoggerFactory;
import io.github.openmaqs.utilities.logging.MessageType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * The Logger Factory unit test class.
 */
@Test(singleThreaded = true)
public class LoggerFactoryUnitTest {

  /**
   * Revert the config back to what it was before.
   */
  @AfterMethod
  private void cleanUp() {
    HashMap<String, String> newValueMap = new HashMap<>();
    newValueMap.put("Log", "OnFail");
    newValueMap.put("LogLevel", "VERBOSE");
    newValueMap.put("LogType", "TXT");
    Config.addGeneralTestSettingValues(newValueMap, true);
  }

  /**
   * Test getting the logger.
   */
  @Test(groups = TestCategories.UTILITIES)
  public void testGetLogger() {
    Assert.assertNotNull(LoggerFactory.getLogger("Test Name"));

    HashMap<String, String> newValueMap = new HashMap<>();
    newValueMap.put("Log", "NO");
    newValueMap.put("LogType", "TXT");
    Config.addGeneralTestSettingValues(newValueMap, true);
    Assert.assertNotNull(LoggerFactory.getLogger("Test Name"));
  }

  /**
   * Test getting the console logger.
   */
  @Test(groups = TestCategories.UTILITIES)
  public void testGetConsoleLogger() {
    Assert.assertNotNull(LoggerFactory.getConsoleLogger());
    ILogger logger = LoggerFactory.getLogger("logName", "CONSOLE", MessageType.WARNING);
    Assert.assertTrue(logger instanceof ConsoleLogger);
  }

  /**
   * Tests getting the file logger.
   */
  @Test(groups = TestCategories.UTILITIES)
  public void testGetFileLogger() {
    List<String> logType = new ArrayList<>();
    logType.add("TXT");
    logType.add("TEXT");

    for (String log : logType) {
      ILogger logger = LoggerFactory.getLogger("logName", log, MessageType.ACTION);
      Assert.assertTrue(logger instanceof FileLogger);
    }
  }

  /**
   * Test getting the HTML file logger.
   */
  @Test(groups = TestCategories.UTILITIES)
  public void testGetHtmlFileLogger() {
    List<String> logType = new ArrayList<>();
    logType.add("HTML");
    logType.add("HTM");

    for (String log : logType) {
      ILogger logger = LoggerFactory.getLogger("logName", log, MessageType.ACTION);
      Assert.assertTrue(logger instanceof HtmlFileLogger);
    }
  }

  /**
   * Test throwing an exception with an unsupported log type.
   */
  @Test(groups = TestCategories.UTILITIES, expectedExceptions = MaqsLoggingConfigException.class)
  public void testGetInvalidLogger() {
    LoggerFactory.getLogger("logName", "TEST", MessageType.ACTION);
  }
}
