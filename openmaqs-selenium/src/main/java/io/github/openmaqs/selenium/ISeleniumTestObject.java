/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.selenium;

import io.github.openmaqs.base.ITestObject;
import java.util.function.Supplier;
import org.openqa.selenium.WebDriver;

/**
 * The Selenium Test Object interface class.
 */
public interface ISeleniumTestObject extends ITestObject {

  /**
   * Get the WebDriver Object.
   *
   * @return A WebDriver Object
   */
  WebDriver getWebDriver();

  /**
   * Sets web driver.
   *
   * @param driver the driver
   */
  void setWebDriver(WebDriver driver);

  /**
   * Sets web driver using Supplier.
   *
   * @param webDriverSupplier the web driver supplier
   */
  void setWebDriver(Supplier<WebDriver> webDriverSupplier);

  /**
   * Gets the Selenium driver manager.
   *
   * @return the web manager
   */
  SeleniumDriverManager getWebManager();
}
