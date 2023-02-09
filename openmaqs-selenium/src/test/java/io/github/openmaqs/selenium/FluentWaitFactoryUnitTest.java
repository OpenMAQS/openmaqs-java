/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.selenium;

import io.github.openmaqs.selenium.pageModel.AutomationPageModel;
import io.github.openmaqs.selenium.factories.FluentWaitFactory;
import io.github.openmaqs.selenium.factories.UIWaitFactory;
import io.github.openmaqs.utilities.helper.TestCategories;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The Fluent Wait Factory unit test class.
 */
public class FluentWaitFactoryUnitTest extends BaseSeleniumTest {

  /**
   * Test get fluent wait object.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void testGetFluentWaitObject() {
    int timeout = 1000;
    int polling = 500;

    AutomationPageModel pageModel = new AutomationPageModel(this.getTestObject());
    pageModel.open(pageModel.testSiteAutomationUrl);
    WebElement elementDriver = UIWaitFactory.getWaitDriver(pageModel.getWebDriver())
        .waitForClickableElement(pageModel.automationShowDialog1);

    FluentWait<WebElement> fluentWait = FluentWaitFactory
        .getNewElementFluentWait(elementDriver, timeout, polling);

    // Error string templates for assertion failures.
    String assertNotNullErrorTemplate = "The %s was null when it was expected to not be.";
    Assert.assertNotNull(fluentWait, String.format(assertNotNullErrorTemplate, "fluentWait"));
  }
}
