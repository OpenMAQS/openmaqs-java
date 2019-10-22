/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.appium;

import com.magenic.jmaqs.base.BaseExtendableTest;
import com.magenic.jmaqs.base.BaseTest;
import com.magenic.jmaqs.selenium.SeleniumWait;
import com.magenic.jmaqs.utilities.helper.StringProcessor;
import com.magenic.jmaqs.utilities.logging.MessageType;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.WebElement;
import org.testng.ITestResult;

/**
 * Base Appium Test Class.
 */
public abstract class BaseAppiumTest extends BaseExtendableTest<AppiumTestObject> {

  /**
   * Thread local storage of AppiumTestObject.
   *
   * @deprecated methodology no longer used
   */
  @Deprecated
  private ThreadLocal<AppiumTestObject> appiumTestObject = new ThreadLocal<AppiumTestObject>();

  /**
   * Initialize a new instance of the BaseAppiumTest class.
   */
  public BaseAppiumTest() {

  }

  /**
   * Gets the appium driver.
   *
   * @return the appium driver
   */
  public AppiumDriver getAppiumDriver() {
    return this.getTestObject().getAppiumDriver();
  }

  public void setAppiumDriver() {

  }

  /**
   * Gets the appium wait.
   *
   * @return the appium wait
   * @deprecated {@link com.magenic.jmaqs.appium.AppiumWait} has been deprecated
   */
  @Deprecated
  public SeleniumWait getAppiumWait() {
    return this.appiumTestObject.get().getAppiumWait();
  }

  /**
   * Gets the appium test object.
   *
   * @return the appium test object
   * @deprecated methodology no longer used
   */
  @Deprecated
  public AppiumTestObject getAppiumTestObject() {
    return this.appiumTestObject.get();
  }

  protected AppiumDriver<WebElement> getMobileDriver() {
    return AppiumDriverFactory.getDefaultMobileDriver();
  }

  /**
   * Overload function for doing post setup logging.
   * @deprecated methodology no longer used.
   */
  @Deprecated
  protected void postSetupLogging() {
    try {

      AppiumDriver driver = AppiumConfig.mobileDevice();
      AppiumWait wait = new AppiumWait(driver);

      appiumTestObject.set(new AppiumTestObject(driver, wait, this.getFullyQualifiedTestClassName(),
          this.getLogger()));
      this.getLogger()
          .logMessage(MessageType.INFORMATION, "Loaded driver: %s", AppiumConfig.getPlatformName());
      // }
    } catch (Exception e) {
      this.getLogger()
          .logMessage(MessageType.ERROR, "Failed to start driver because: %s", e.getMessage());
      System.out.println(
          StringProcessor.safeFormatter("Browser type %s is not supported", e.getMessage()));
    }
  }

  /**
   * Steps to do before logging teardown results.
   *
   * @param resultType The test result
   */
  protected void beforeLoggingTeardown(ITestResult resultType) {
    try {
      // TODO add screen capture once AppiumUtilities has been created
      /*if ((this.getLogger() instanceof FileLogger) && (resultType != TestResultType.PASS) && (
          this.loggingEnabledSetting != LoggingEnabled.NO))
      {
        AppiumUtilities.CaptureScreenshot(this.getAppiumDriver(), this.getLogger());
      }*/
    } catch (Exception e) {
      this.tryToLog(MessageType.WARNING, "Failed to get screen shot because: %s", e.getMessage());
    }
    this.tryToLog(MessageType.INFORMATION, "Close");

    try {
      this.appiumTestObject.get().appiumDriver.quit();
    } catch (Exception e) {
      this.tryToLog(MessageType.WARNING, "Failed to quit because: %s", e.getMessage());
    }
  }
}
