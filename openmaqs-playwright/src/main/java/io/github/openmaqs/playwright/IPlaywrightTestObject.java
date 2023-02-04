/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.playwright;

import io.github.openmaqs.base.ITestObject;

/**
 * The Playwright Test Object interface.
 */
public interface IPlaywrightTestObject extends ITestObject {

  /**
   * Gets the page driver.
   * @return the page driver
   */
  PageDriver getPageDriver();

  /**
   * Sets the page driver.
   * @param driver the new page driver to be set
   */
  void setPageDriver(PageDriver driver);

  /**
   * Gets the Playwright page manager.
   * @return the playwright driver manager
   */
  PlaywrightDriverManager getPageManager();

  /**
   * Override the Playwright page driver.
   * @param pageDriver the new page driver
   */
  void overridePageDriver(PageDriver pageDriver);
}
