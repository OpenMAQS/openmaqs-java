/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.selenium;

import io.github.openmaqs.base.BaseExtendableTest;
import io.github.openmaqs.selenium.exceptions.WebDriverFactoryException;
import io.github.openmaqs.utilities.helper.StringProcessor;
import io.github.openmaqs.utilities.logging.LoggingEnabled;
import io.github.openmaqs.utilities.logging.MessageType;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

/**
 * Base Selenium Test class.
 */
public class BaseSeleniumTest extends BaseExtendableTest<ISeleniumTestObject> {

  /**
   * Initialize a new instance of the BaseSeleniumTest class.
   */
  public BaseSeleniumTest() {
    // This initializer is intentionally left blank
  }

  /**
   * Get WebDriver.
   *
   * @return WebDriver web driver
   */
  public WebDriver getWebDriver() {
    return this.getTestObject().getWebDriver();
  }

  /**
   * Sets web driver.
   *
   * @param webDriver the web driver
   */
  public void setWebDriver(WebDriver webDriver) {
    this.getTestObject().setWebDriver(webDriver);
  }

  /**
   * Take a screenshot if needed and tear down the web driver.
   *
   * @param resultType The test result type
   */
  @Override
  protected void beforeLoggingTeardown(ITestResult resultType) {
    // Try to take a screenshot
    try {
      if (this.getWebDriver() != null && resultType.getStatus() != ITestResult.SUCCESS
          && this.getLoggingEnabled() != LoggingEnabled.NO) {
        SeleniumUtilities.captureScreenshot(getWebDriver(), getTestObject());
      }
    } catch (Exception e) {
      this.tryToLog(MessageType.WARNING, "Failed to get screen shot because: %s", e.getMessage());
    }
  }

  /**
   * Get the current browser.
   *
   * @return Current browser Web Driver
   * @throws WebDriverFactoryException Throws exception
   */
  protected WebDriver getBrowser() {
    // Returns the web driver
    return WebDriverFactory.getDefaultBrowser();
  }

  @Override
  protected void createNewTestObject()  {
    try {
      this.setTestObject(
          new SeleniumTestObject(() -> {
            try {
              return getBrowser();
            } catch (WebDriverFactoryException e) {
              getLogger().logMessage(StringProcessor.safeFormatter("Failed setup driver: %s", e.toString()));
              throw e;
            }
          }, this.createLogger(), this.getFullyQualifiedTestClassName()));
    } catch (Exception e) {
      getLogger().logMessage(StringProcessor.safeFormatter("Test Object could not be created: %s", e.getMessage()));
      throw e;
    }
  }
}