/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.playwright.pageModel;

import io.github.openmaqs.playwright.IPlaywrightTestObject;
import io.github.openmaqs.playwright.PlaywrightConfig;
import io.github.openmaqs.playwright.PlaywrightSyncElement;
import com.microsoft.playwright.FrameLocator;

/**
 * The Playwright IFrame Page Model class for testing.
 */
public class IFramePageModel extends PageModel {

  /**
   * Initializes a new instance of the PageModel IFrame class
   * @param testObject The base Playwright test object
   */
  public IFramePageModel(IPlaywrightTestObject testObject) {
    super(testObject);
  }

  /**
   * holds the selector string values.
   */
  ElementPageModel elementPageModel = new ElementPageModel();

  /**
   * gets the page url.
   * @return the page url
   */
  public static String getUrl() {
    return PlaywrightConfig.getWebBase() + "iFrame.html";
  }

  /**
   * gets the test frame locator.
   * @return the test frame locator
   */
  private FrameLocator getFrame() {
    return this.getPageDriver().getAsyncPage().frameLocator(elementPageModel.frame);
  }

  /**
   * gets the loaded label.
   * @return the loaded label
   */
  public PlaywrightSyncElement getShowDialog() {
    return new PlaywrightSyncElement(getFrame(), elementPageModel.showDialog1);
  }

  /**
   * gets the close dialog element.
   * @return the close dialog element
   */
  public PlaywrightSyncElement getCloseDialog() {
    return new PlaywrightSyncElement(getFrame(), elementPageModel.closeButtonShowDialog);
  }

  /**
   * navigates to the page.
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
    return getShowDialog().isEventuallyVisible();
  }
}
