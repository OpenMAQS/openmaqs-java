/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.appium;

import io.appium.java_client.AppiumDriver;
import io.github.openmaqs.base.BaseGenericTest;
import io.github.openmaqs.utilities.helper.StringProcessor;
import io.github.openmaqs.utilities.helper.TestCategories;
import io.github.openmaqs.utilities.logging.ConsoleLogger;
import io.github.openmaqs.utilities.logging.FileLogger;
import io.github.openmaqs.utilities.logging.Logger;
import java.io.File;
import java.nio.file.Paths;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

/**
 * The Appium Utilities unit test class.
 */
@Ignore
public class AppiumUtilitiesUnitTest extends BaseGenericTest {

  /**
   * Placeholder for the timestamp as a string value.
   */
  private final String TIMESTAMP = Logger.DEFAULT_DATE_TIME_FORMAT;

  /**
   * Test capture screenshot no append.
   */
  @Test(groups = TestCategories.APPIUM)
  public void testCaptureScreenshotNoAppend() {
    AppiumDriver appiumDriver = AppiumDriverFactory.getDefaultMobileDriver();
    FileLogger fileLogger = (FileLogger) this.getTestObject().getLogger();
    AppiumTestObject testObject = new AppiumTestObject(appiumDriver, fileLogger,
        this.getTestObject().getFullyQualifiedTestName());
    this.setTestObject(testObject);

    // Open Google and take a screenshot
    appiumDriver.navigate().to("http://www.google.com");
    boolean isSuccess = AppiumUtilities.captureScreenshot(appiumDriver, testObject);

    // Assert screenshot was successful
    Assert.assertTrue(isSuccess, "Expected Screenshot to be successful.");
  }

  /**
   * Test capture screenshot append.
   */
  @Test(groups = TestCategories.APPIUM)
  public void testCaptureScreenshotAppend() {
    AppiumDriver appiumDriver = AppiumDriverFactory.getDefaultMobileDriver();
    FileLogger fileLogger = (FileLogger) this.getTestObject().getLogger();
    AppiumTestObject testObject = new AppiumTestObject(appiumDriver, fileLogger,
        this.getTestObject().getFullyQualifiedTestName());
    this.setTestObject(testObject);

    // Open Google and take a screenshot
    appiumDriver.navigate().to("http://www.google.com");
    boolean isSuccess = AppiumUtilities.captureScreenshot(appiumDriver, testObject, "testAppend");

    // Assert screenshot was successful
    Assert.assertTrue(isSuccess, "Expected Screenshot to be successful.");
  }

  /**
   * Test capture screenshot console logger.
   */
  @Test(groups = TestCategories.APPIUM)
  public void testCaptureScreenshotConsoleLogger() {
    AppiumDriver appiumDriver = AppiumDriverFactory.getDefaultMobileDriver();
    ConsoleLogger consoleLogger = new ConsoleLogger();
    AppiumTestObject testObject = new AppiumTestObject(appiumDriver, consoleLogger,
        this.getTestObject().getFullyQualifiedTestName());
    this.setTestObject(testObject);

    // Open Google and take a screenshot
    appiumDriver.navigate().to("http://www.google.com");
    boolean isSuccess = AppiumUtilities.captureScreenshot(appiumDriver, testObject, "testAppend");

    // Assert screenshot was NOT successful
    Assert.assertFalse(isSuccess, "Expected Screenshot to NOT be successful.");
  }

  /**
   * Test capture screenshot custom directory file name.
   */
  @Test(groups = TestCategories.APPIUM)
  public void testCaptureScreenshotCustomDirectoryFileName() {
    AppiumDriver appiumDriver = AppiumDriverFactory.getDefaultMobileDriver();
    FileLogger fileLogger = (FileLogger) this.getTestObject().getLogger();
    AppiumTestObject testObject = new AppiumTestObject(appiumDriver, fileLogger,
        this.getTestObject().getFullyQualifiedTestName());
    this.setTestObject(testObject);

    // Open Google and take a screenshot with a custom name and date/time appended
    appiumDriver.navigate().to("http://www.google.com");
    String dateTime = DateTimeFormatter.ofPattern(TIMESTAMP, Locale.getDefault())
        .format(LocalDateTime.now(Clock.systemUTC()));
    String filePath = AppiumUtilities.captureScreenshot(appiumDriver, testObject, fileLogger.getDirectory(),
        StringProcessor.safeFormatter("%s - %s", "TestCustomName", dateTime));

    // Assert File Path returned from Screenshot is the same as expected file path.
    Assert.assertEquals(filePath, Paths.get(StringProcessor
        .safeFormatter("%s%s - %s%s", fileLogger.getDirectory(), File.separator + "TestCustomName", dateTime, ".png"))
        .normalize().toString());
  }

  /**
   * Test save page source no append.
   */
  @Test(groups = TestCategories.APPIUM)
  public void testSavePageSourceNoAppend() {
    AppiumDriver appiumDriver = AppiumDriverFactory.getDefaultMobileDriver();
    FileLogger fileLogger = (FileLogger) this.getTestObject().getLogger();
    AppiumTestObject testObject = new AppiumTestObject(appiumDriver, fileLogger,
        this.getTestObject().getFullyQualifiedTestName());
    this.setTestObject(testObject);

    // Open Google and save page source
    appiumDriver.navigate().to("http://www.google.com");
    boolean isSuccess = AppiumUtilities.savePageSource(appiumDriver, testObject);

    // Assert screenshot was successful
    Assert.assertTrue(isSuccess, "Expected Screenshot to be successful.");
  }

  /**
   * Test save page source append.
   */
  @Test(groups = TestCategories.APPIUM)
  public void testSavePageSourceAppend() {
    AppiumDriver appiumDriver = AppiumDriverFactory.getDefaultMobileDriver();
    FileLogger fileLogger = (FileLogger) this.getTestObject().getLogger();
    AppiumTestObject testObject = new AppiumTestObject(appiumDriver, fileLogger,
        this.getTestObject().getFullyQualifiedTestName());
    this.setTestObject(testObject);

    // Open Google and save page source with Appended Name
    appiumDriver.navigate().to("http://www.google.com");
    boolean isSuccess = AppiumUtilities.savePageSource(appiumDriver, testObject, "testAppend");

    // Assert screenshot was successful
    Assert.assertTrue(isSuccess, "Expected Screenshot to be successful.");
  }

  /**
   * Test save page source custom directory file name.
   */
  @Test(groups = TestCategories.APPIUM)
  public void testSavePageSourceCustomDirectoryFileName() {
    AppiumDriver appiumDriver = AppiumDriverFactory.getDefaultMobileDriver();
    FileLogger fileLogger = (FileLogger) this.getTestObject().getLogger();
    AppiumTestObject testObject = new AppiumTestObject(appiumDriver, fileLogger,
        this.getTestObject().getFullyQualifiedTestName());
    this.setTestObject(testObject);

    // Open Google and take a screenshot with a custom name and date/time appended
    appiumDriver.navigate().to("http://www.google.com");
    String dateTime = DateTimeFormatter.ofPattern(TIMESTAMP, Locale.getDefault())
        .format(LocalDateTime.now(Clock.systemUTC()));
    String filePath = AppiumUtilities.savePageSource(appiumDriver, testObject, fileLogger.getDirectory(),
        StringProcessor.safeFormatter("%s - %s", "TestCustomName", dateTime));

    // Assert File Path returned from Screenshot is the same as expected file path.
    Assert.assertEquals(filePath, Paths.get(StringProcessor
        .safeFormatter("%s%s - %s%s", fileLogger.getDirectory(), File.separator + "TestCustomName", dateTime, ".txt"))
        .normalize().toString());
  }

  /**
   * Test save page source console logger.
   */
  @Test(groups = TestCategories.APPIUM)
  public void testSavePageSourceConsoleLogger() {
    AppiumDriver appiumDriver = AppiumDriverFactory.getDefaultMobileDriver();
    ConsoleLogger consoleLogger = new ConsoleLogger();
    AppiumTestObject testObject = new AppiumTestObject(appiumDriver, consoleLogger,
        this.getTestObject().getFullyQualifiedTestName());
    this.setTestObject(testObject);

    // Open Google and take a screenshot
    appiumDriver.navigate().to("http://www.google.com");
    boolean isSuccess = AppiumUtilities.savePageSource(appiumDriver, testObject, "testAppend");

    // Assert screenshot was successful
    Assert.assertTrue(isSuccess, "Expected Screenshot to be successful.");
  }

  /**
   * Test kill driver.
   */
  @Test(groups = TestCategories.APPIUM)
  public void testKillDriver() {
    AppiumDriver appiumDriver = AppiumDriverFactory.getDefaultMobileDriver();
    FileLogger fileLogger = (FileLogger) this.getTestObject().getLogger();
    AppiumTestObject testObject = new AppiumTestObject(appiumDriver, fileLogger,
        this.getTestObject().getFullyQualifiedTestName());
    this.setTestObject(testObject);

    // Open Google then Kill the Driver
    appiumDriver.navigate().to("http://www.google.com");
    AppiumUtilities.killDriver(appiumDriver);

    // Assert that the Session ID is null in the Appium Driver
    Assert.assertNull(appiumDriver.getSessionId(), "Expected Appium Driver Session ID to be null.");
  }
}
