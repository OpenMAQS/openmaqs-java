/*
 * Copyright 2022 (C) MAQS, All rights Reserved
 */

package io.github.maqs.appium;

import io.appium.java_client.AppiumDriver;
import io.github.maqs.base.DriverManager;
import io.github.maqs.base.ITestObject;
import io.github.maqs.utilities.helper.StringProcessor;
import io.github.maqs.utilities.logging.MessageType;
import java.util.function.Supplier;
import org.openqa.selenium.WebElement;

/**
 * The Mobile Driver Manager class.
 */
public class MobileDriverManager extends DriverManager<AppiumDriver<WebElement>> {
  /**
   * Instantiates a new Mobile Driver Manager.
   *
   * @param getDriverFunction Function that specifies how to get the driver.
   * @param baseTestObject    The Base Test Object.
   */
  public MobileDriverManager(Supplier<AppiumDriver<WebElement>> getDriverFunction, ITestObject baseTestObject) {
    super(getDriverFunction, baseTestObject);
  }

  /**
   * Instantiates a new Appium Driver Manager.
   *
   * @param driver         Appium Driver
   * @param baseTestObject The Base Test Object.
   */
  public MobileDriverManager(AppiumDriver<WebElement> driver, ITestObject baseTestObject) {
    super(() -> driver, baseTestObject);
    this.baseDriver = driver;
  }

  /**
   * Get the Appium driver.
   *
   * @return The Appium Driver
   */
  public AppiumDriver<WebElement> getMobileDriver() {
    return getBase();
  }

  /**
   * Cleanup the Appium Driver.
   */
  public void close() {
    // If we never created the driver we don't have any cleanup to do
    if (!this.isDriverInitialized()) {
      return;
    }

    try {
      AppiumDriver<WebElement> driver = this.getMobileDriver();
      driver.quit();
    } catch (Exception e) {
      this.getLogger().logMessage(MessageType.ERROR,
          StringProcessor.safeFormatter("Failed to close mobile driver because: %s", e.getMessage()));
    }

    this.baseDriver = null;
  }
}