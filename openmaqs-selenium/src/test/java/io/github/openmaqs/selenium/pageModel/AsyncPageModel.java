/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.selenium.pageModel;

import io.github.openmaqs.selenium.ISeleniumTestObject;
import io.github.openmaqs.selenium.SeleniumConfig;
import org.openqa.selenium.By;

/**
 * The Async page model.
 */
public class AsyncPageModel extends HeaderPageModel {

  /**
   * Unit testing site URL - Async page.
   */
  public final String testSiteAsyncUrl = SeleniumConfig.getWebSiteBase() + "async.html";

  /**
   * Dropdown selector.
   */
  public final By asyncDropdownCssSelector = By.cssSelector("#Selector");

  /**
   * Dropdown label.
   */
  public final By asyncOptionsLabel = By.cssSelector("#Label");

  /**
   * Asynchronous div that loads after a delay on Async Testing Page.
   */
  public final By asyncLoadingTextDiv = By.cssSelector("div #loading-div-text");

  /**
   * Dropdown label - hidden once dropdown loads.
   */
  public final By asyncLoadingLabel = By.cssSelector("#LoadingLabel");

  /**
   * Instantiates a new Async page model.
   *
   * @param testObject the test object
   */
  public AsyncPageModel(ISeleniumTestObject testObject) {
    super(testObject);
  }
}