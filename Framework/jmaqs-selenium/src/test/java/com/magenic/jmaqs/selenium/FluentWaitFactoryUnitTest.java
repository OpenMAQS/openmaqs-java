/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.selenium;

import com.magenic.jmaqs.selenium.factories.FluentWaitFactory;
import com.magenic.jmaqs.selenium.factories.UIWaitFactory;
import com.magenic.jmaqs.selenium.unittestpagemodel.PageElementsPageModel;
import com.magenic.jmaqs.utilities.helper.TestCategories;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

public class FluentWaitFactoryUnitTest extends BaseSeleniumTest {

  /**
   * Url for the site.
   */
  private static String siteUrl = SeleniumConfig.getWebSiteBase();

  /**
   * Automation site url.
   */
  private static String siteAutomationUrl = siteUrl + "Automation/";

  /**
   * Error string templates for assertion failures
   */
  private static String assertNotNullErrorTemplate = "The %s was null when it was expected to not be.";

  @Test(groups = TestCategories.Selenium)
  public void testGetFluentWaitObject() {
    int timeout = 1000;
    int polling = 500;

    PageElementsPageModel pageModel = new PageElementsPageModel(this.getTestObject());
    pageModel.open(siteAutomationUrl);
    WebElement elementDriver = UIWaitFactory
        .getWaitDriver(pageModel.getSeleniumTestObject().getWebDriver())
        .waitForClickableElement(pageModel.showDialog1ButtonLocator);

    FluentWait<WebElement> fluentWait = FluentWaitFactory
        .getNewElementFluentWait(elementDriver, timeout, polling);

    assertNotNull(fluentWait, String.format(assertNotNullErrorTemplate, "fluentWait"));
  }

  @Override
  protected void postSetupLogging() throws Exception {

  }
}
