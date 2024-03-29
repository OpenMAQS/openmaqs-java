/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.appium;

import io.appium.java_client.AppiumDriver;
import io.github.openmaqs.base.BaseExtendableTest;
import io.github.openmaqs.utilities.logging.FileLogger;
import io.github.openmaqs.utilities.logging.LoggingEnabled;
import io.github.openmaqs.utilities.logging.MessageType;
import org.testng.ITestResult;

/**
 * The Base Appium Test class.
 */
public class BaseAppiumTest extends BaseExtendableTest<AppiumTestObject> {

  /**
   * Initialize a new instance of the BaseAppiumTest class.
   */
  public BaseAppiumTest() {
    // Needs an empty constructor
  }

  /**
   * Gets the appium driver.
   *
   * @return the appium driver
   */
  public AppiumDriver getAppiumDriver() {
    return this.getTestObject().getAppiumDriver();
  }

  /**
   * Sets appium driver.
   *
   * @param mobileDriver the mobile driver
   */
  public void setAppiumDriver(AppiumDriver mobileDriver) {
    this.getTestObject().setAppiumDriver(mobileDriver);
  }

  /**
   * Gets new mobile driver.
   *
   * @return the mobile driver
   */
  protected AppiumDriver getMobileDriver() {
    return AppiumDriverFactory.getDefaultMobileDriver();
  }

  /**
   * Steps to take before logging teardown results.
   *
   * @param resultType The test result
   */
  @Override
  protected void beforeLoggingTeardown(ITestResult resultType) {
    try {

      if (this.getTestObject().getAppiumManager().isDriverInitialized() && this
          .getLogger() instanceof FileLogger && resultType.getStatus() != ITestResult.SUCCESS
          && this.loggingEnabledSetting != LoggingEnabled.NO) {
        AppiumUtilities.captureScreenshot(this.getAppiumDriver(), this.getTestObject(), "Final");
        if (AppiumConfig.getSavePageSourceOnFail()) {
          AppiumUtilities
              .savePageSource(this.getAppiumDriver(), this.getTestObject(), "FinalPageSource");
        }
      }

    } catch (Exception e) {
      this.tryToLog(MessageType.WARNING, "Failed to get screen shot because: %s", e.getMessage());
    }
  }

  @Override
  protected void createNewTestObject() {
    this.setTestObject(new AppiumTestObject(this::getMobileDriver, this.createLogger(),
        this.getFullyQualifiedTestClassName()));
  }
}
