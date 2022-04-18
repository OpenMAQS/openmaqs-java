/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.playwright;

import com.cognizantsoftvision.maqs.base.BaseGenericTest;
import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import com.microsoft.playwright.Page;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test driver manager.
 */
public class PlaywrightDriverManagerUnitTest extends BaseGenericTest {

  /**
   * Make we can update the store with a new manager using a Page.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void respectsNewPageViaManager() {
    Page newPage = getNewPage();
    this.getManagerStore().putOrOverride(new PageDriverManager(() -> new PageDriver(newPage) , this.getTestObject()));
    PageDriverManager manager = (PageDriverManager) this.getManagerStore().getManager(PageDriverManager.class.getName());
    Assert.assertEquals(newPage, manager.getPageDriver().getAsyncPage());
  }

  /**
   * Make we can update the drive with a Page object.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void respectsNewPageViaOverride() {
    Page newPage = getNewPage();
    this.getTestObject().overrideDriverManager("playwrightDriver",
        new PageDriverManager(() -> new PageDriver(newPage) , this.getTestObject()));
    PageDriverManager manager = (PageDriverManager) this.getManagerStore().getManager("playwrightDriver");
    Assert.assertEquals(newPage, manager.getPageDriver().getAsyncPage());
  }

//  /**
//   * Make we can update the drive with a Page function.
//   */
//  @Test(groups = TestCategories.PLAYWRIGHT)
//  public void respectsNewPageViaOverrideFunc() {
//    Page newPage = getNewPage();
//    this.getTestObject().overrideDriverManager("Playwright driver", () -> new PageDriver(newPage));
//    Assert.assertEquals(newPage, this.getsTestObject().getPageDriver().getAsyncPage());
//  }

  /**
   * Get a new Page.
   * @return A new Page
   */
  private static Page getNewPage() {
    return PageDriverFactory.getPageDriverForBrowserWithDefaults(PlaywrightBrowser.WEBKIT).getAsyncPage();
  }
}
