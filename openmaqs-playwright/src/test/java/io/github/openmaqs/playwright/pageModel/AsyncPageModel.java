/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.playwright.pageModel;

import io.github.openmaqs.playwright.BasePlaywrightPageModel;
import io.github.openmaqs.playwright.IPlaywrightTestObject;
import io.github.openmaqs.playwright.PageDriver;
import io.github.openmaqs.playwright.PlaywrightConfig;
import io.github.openmaqs.playwright.PlaywrightSyncElement;

/**
 * The Other Playwright page model class for testing.
 */
public class AsyncPageModel extends BasePlaywrightPageModel {

  /**
   * The element page model that holds all the elements as a string value.
   */
  static ElementPageModel elementPageModel = new ElementPageModel();

  /**
   * sets up the page model other class.
   * @param testObject the test object to be used
   * @param otherDriver the page driver to be used
   */
  public AsyncPageModel(IPlaywrightTestObject testObject, PageDriver otherDriver) {
    super(testObject, otherDriver);
  }

  /**
   * gets the page url.
    * @return the page url
   */
  public static String getUrl(){
    return PlaywrightConfig.getWebBase() + "async.html";
  }

  /**
   * gets the root body element.
   * @return the root body element
   */
  private PlaywrightSyncElement getBody() {
    return new PlaywrightSyncElement(this.getPageDriver().getAsyncPage(), elementPageModel.body);
  }

  /**
   * gets the loaded label element.
    * @return the loaded label element
   */
  public PlaywrightSyncElement getLoadedPlaywrightElement() {
    return new PlaywrightSyncElement(getBody(), elementPageModel.loadedAsyncText);
  }

  /**
   * navigates to the page url.
   */
  public void openPage() {
    this.getPageDriver().navigateTo(getUrl());
  }

  /**
   * check if the page has been loaded.
   * @return true if the page was loaded
   */
  @Override
  public boolean isPageLoaded() {
    return this.getLoadedPlaywrightElement().isEventuallyVisible();
  }
}
