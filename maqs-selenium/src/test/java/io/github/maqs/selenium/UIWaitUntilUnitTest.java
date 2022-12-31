/*
 * Copyright 2022 (C) MAQS, All rights Reserved
 */

package io.github.maqs.selenium;

import io.github.maqs.selenium.factories.UIWaitFactory;
import io.github.maqs.selenium.pageModel.AsyncPageModel;
import io.github.maqs.selenium.pageModel.AutomationPageModel;
import io.github.maqs.selenium.pageModel.IFramePageModel;
import io.github.maqs.utilities.helper.TestCategories;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * The UI Wait Until unit test class.
 */
public class UIWaitUntilUnitTest extends BaseSeleniumTest {

  /**
   * The IFrame page model.
   */
  private IFramePageModel iFramePageModel;

  /**
   * The Async page model.
   */
  private AsyncPageModel asyncPageModel;

  /**
   * The Automation page model.
   */
  private AutomationPageModel automationPageModel;

  /**
   * Sets up the page models for the unit tests.
   */
  @BeforeMethod
  private void setUp() {
    iFramePageModel = new IFramePageModel(this.getTestObject());
    asyncPageModel = new AsyncPageModel(this.getTestObject());
    automationPageModel = new AutomationPageModel(this.getTestObject());
  }

  /**
   * Tests the functionality that waits until the IFrame to load.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilIFrameToLoad() {
    this.getWebDriver().navigate().to(iFramePageModel.testSiteIFrameUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    wait.waitUntilPageLoad();
    WebDriverFactory.setBrowserSize(this.getWebDriver(), "Maximize");
    Assert.assertTrue(wait.waitUntilIframeToLoad(iFramePageModel.iframeLocator));

    this.getWebDriver().navigate().to(iFramePageModel.testSiteIFrameUrl);
    wait.waitUntilPageLoad();
    Assert.assertTrue(wait.waitUntilIframeToLoad(iFramePageModel.iframeLocator, 5000, 1000));

  }

  /**
   * Tests the functionality that waits until the attribute texts equals.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilAttributeTextEquals() {
    this.getWebDriver().navigate().to(asyncPageModel.testSiteAsyncUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilAttributeTextEquals(asyncPageModel.asyncLoadingLabel, "style", "display: none;"));
    Assert.assertTrue(wait.waitUntilAttributeTextEquals(asyncPageModel.asyncLoadingLabel, "style", "display: none;", 10000, 2000));
  }

  /**
   * Tests the functionality that waits until an attribute contains text.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilAttributeTextContainsFound() {
    this.getWebDriver().navigate().to(asyncPageModel.testSiteAsyncUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilAttributeTextContains(
        asyncPageModel.asyncDropdownCssSelector, "id", ""));
    Assert.assertTrue(wait.waitUntilAttributeTextContains(
        asyncPageModel.asyncDropdownCssSelector, "id", "", 10000, 1000));
  }

  /**
   * Verify that the WaitUntilAttributeTextContains method returns false for
   * objects that don't have this text inside attribute value within timeout.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilAttributeTextContainsFalse() {
    this.getWebDriver().navigate().to(asyncPageModel.testSiteAsyncUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertFalse(wait.waitUntilAttributeTextContains(
        asyncPageModel.asyncDropdownCssSelector, "notTheRightEd", "id"));
    Assert.assertFalse(wait.waitUntilAttributeTextContains(
        asyncPageModel.asyncDropdownCssSelector, "notTheRightId", "id", 10000, 1000));
  }

  /**
   * Verify that the WaitUntilAttributeTextContains method returns false for
   * objects that don't have this attribute value within timeout.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilAttributeTextEqualsFalse() {
    getWebDriver().navigate().to(asyncPageModel.testSiteAsyncUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertFalse(wait.waitUntilAttributeTextEquals(asyncPageModel.asyncLoadingLabel, "display:", "style"));
    Assert.assertFalse(wait.waitUntilAttributeTextEquals(asyncPageModel.asyncLoadingLabel, "display:", "style", 10000, 1000));
  }

  /**
   * Tests the functionality that waits until a clickable element scrolls into
   * view.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilClickableElementAndScrollIntoView() {
    this.getWebDriver().navigate().to(automationPageModel.testSiteAutomationUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilClickableElementAndScrollIntoView(
        automationPageModel.automationShowDialog1));
    Assert.assertTrue(wait.waitUntilClickableElementAndScrollIntoView(
        automationPageModel.automationShowDialog1, 10000, 1000));
  }

  /**
   * Tests the functionality that waits until an enabled element.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilEnabledElement() {
    this.getWebDriver().navigate().to(automationPageModel.testSiteAutomationUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilEnabledElement(automationPageModel.flowerTableTitle));
    Assert.assertTrue(wait.waitUntilEnabledElement(automationPageModel.flowerTableTitle, 10000, 1000));
  }

  /**
   * Tests the functionality that waits until a disabled element.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilDisabledElement() {
    this.getWebDriver().navigate().to(automationPageModel.testSiteAutomationUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilDisabledElement(automationPageModel.disabledField));
    Assert.assertTrue(wait.waitUntilDisabledElement(automationPageModel.disabledField, 10000, 1000));
  }

  /**
   * Test the functionality that resets the wait driver.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void resetWaitDriver() {
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertNotNull(wait.resetWaitDriver());
    Assert.assertNotNull(wait.getNewWaitDriver(20000));

  }

  /**
   * Test the functionality that gets a new wait driver.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void getNewWaitDriver() {
    this.getWebDriver().navigate().to(automationPageModel.testSiteUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertNotNull(wait.getNewWaitDriver());
    Assert.assertNotNull(wait.getNewWaitDriver(10000));
    Assert.assertNotNull(wait.getNewWaitDriver(this.getTestObject().getWebDriver()));
    Assert.assertNotNull(wait.getNewWaitDriver(this.getTestObject().getWebDriver(), 10000, 1000));
  }

  /**
   * Verify WaitUntilPageLoad wait works.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilPageLoad() {
    getWebDriver().navigate().to(automationPageModel.testSiteUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilPageLoad(), "Page failed to load");
  }

  /**
   * Verify WaitUntilClickableElement wait works.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilClickableElement() {
    this.getWebDriver().navigate().to(automationPageModel.testSiteAutomationUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilClickableElement(
        automationPageModel.automationShowDialog1), "Failed to find element");
    Assert.assertTrue(wait.waitUntilClickableElement(
        automationPageModel.automationShowDialog1, 10000, 1000), "Failed to find element");
  }

  /**
   * Verify WaitUntilVisibleElement wait works.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilVisibleElement() {
    this.getWebDriver().navigate().to(automationPageModel.testSiteAutomationUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilVisibleElement(
        automationPageModel.automationShowDialog1), "Failed to find element");
    Assert.assertTrue(wait.waitUntilVisibleElement(
        automationPageModel.automationShowDialog1, 10000, 1000), "Failed to find element");
    Assert.assertFalse(wait.waitUntilVisibleElement(automationPageModel.notInPage), "Failed to find element");
  }

  /**
   * Verify WaitUntilExactText wait works.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilExactText() {
    this.getWebDriver().navigate().to(automationPageModel.testSiteAutomationUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilExactText(
        automationPageModel.automationShowDialog1, "Show dialog"), "Failed to find element");
    Assert.assertTrue(wait.waitUntilExactText(automationPageModel.automationShowDialog1,
        "Show dialog", 10000, 1000), "Failed to find element");
  }

  /**
   * Verify WaitUntilContainsText wait works.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilContainsText() {
    this.getWebDriver().navigate().to(automationPageModel.testSiteAutomationUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilContainsText(
        automationPageModel.automationShowDialog1, "dialog"), "Failed to find element");
    Assert.assertTrue(wait.waitUntilContainsText(automationPageModel.automationShowDialog1,
            "dialog", 10000, 1000), "Failed to find element");
    Assert.assertFalse(wait.waitUntilContainsText(
        automationPageModel.notInPage, "notInPage"), "Failed to find element");
  }

  /**
   * Test for the wait until absent.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilAbsentElement() {
    this.getWebDriver().navigate().to(automationPageModel.testSiteUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilAbsentElement(automationPageModel.notInPage));
    Assert.assertTrue(wait.waitUntilAbsentElement(
        automationPageModel.notInPage, 10000, 1000));
  }

  /**
   * Verify that the WaitUntilAttributeTextEquals works with async objects.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilAttributeEquals() {
    this.getWebDriver().navigate().to(asyncPageModel.testSiteAsyncUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilAttributeTextEquals(
        asyncPageModel.asyncLoadingLabel, "style", "display: none;"));
  }

  /**
   * Verify that the WaitUntilAttributeTextContains works with async objects.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilAttributeContains() {
    this.getWebDriver().navigate().to(asyncPageModel.testSiteAsyncUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilAttributeTextContains(
        asyncPageModel.asyncLoadingLabel, "style", "none;"));
  }
}