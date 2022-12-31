/*
 * Copyright 2022 (C) MAQS, All rights Reserved
 */

package io.github.maqs.selenium;

import io.github.maqs.selenium.factories.UIFindFactory;
import io.github.maqs.selenium.pageModel.AutomationPageModel;
import io.github.maqs.utilities.helper.TestCategories;
import java.util.List;

import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * The UI Find functionality unit test class.
 */
public class UIFindUnitTest extends BaseSeleniumTest {

  /**
   * The Automation Page Model.
   */
  private AutomationPageModel automationPageModel;

  /**
   * The UI Find to be used in the unit tests.
   */
  private UIFind find;

  /**
   * Sets up the page models for the test.
   */
  @BeforeMethod
  private void setUp() {
    automationPageModel = new AutomationPageModel(this.getTestObject());
    this.getWebDriver().navigate().to(automationPageModel.testSiteAutomationUrl);
    find = UIFindFactory.getFind(this.getWebDriver());
  }

  /**
   * Verify findElement works, validating a specific selector is found.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void findElementFound() {
    WebElement element = find.findElement(automationPageModel.automationNamesLabel);
    Assert.assertEquals(element.getText(),"Names");
  }

  /**
   * Verify findElement works, validating a specific selector is not found.
   * The expected exception is to catch the exception thrown in find.findElement.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void findElementNotFound() {
    Assert.assertNull(find.findElement(automationPageModel.notInPage, false));
  }

  /**
   * Verify findElement throws exception if element is not found.
   */
  @Test(groups = TestCategories.SELENIUM, expectedExceptions = NotFoundException.class)
  public void findElementCatchException() {
    find.findElement(automationPageModel.notInPage, true);
  }

  /**
   * Verify findElements works, validating that there are 3 found.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void findElementsFound() {
    List<WebElement> list = find.findElements(automationPageModel.dropdownToggleClassSelector);
    Assert.assertEquals(list.size(),2, "There are 2 elements with dropdown classes");

    Assert.assertEquals(list.get(0).getText(), "Automation");
    Assert.assertTrue(list.get(0).isDisplayed());
    Assert.assertEquals(list.get(1).getText(), "Training");
    Assert.assertTrue(list.get(1).isDisplayed());

    List<WebElement> elements = find.findElements(automationPageModel.flowerTable);
    Assert.assertEquals(elements.size(), 20);
  }

  /**
   * Verify findElements works, validating that there are none found.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void findElementsNotFound() {
    List<WebElement> list = find.findElements(automationPageModel.notInPage, false);
    Assert.assertEquals(list.size(), 0);
  }

  /**
   * Verify Find.Elements() throws an exception when there are no Elements existing on the page.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void findElementsNotFoundThrowException() {
    List<WebElement> elements = find.findElements(automationPageModel.notInPage, false);
    Assert.assertEquals(elements.size(), 0);
  }

  /**
   * Verify FindElementWithText = Validate null is returned if the element is not found.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void findElementWithTextElementNotFound() {
    Assert.assertNull(find.findElementWithText(automationPageModel.notInPage, "notInPage", false),
        "Element was not found");
  }

  /**
   * Verify FindElementWithText,
   * Validating specific text is found within a specific selector.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void findElementWithText() {
    String text = find.findElement(automationPageModel.automationShowDialog1).getText();
    Assert.assertNotNull(find.findElementWithText(automationPageModel.automationShowDialog1, text),
        "Element was not found");
  }

  /**
   * Verify FindElementWithText,
   * Validating specific text is NOT found within a specific selector.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void findElementWithTextNotFound() {
    Assert.assertNull(find.findElementWithText(automationPageModel.homeButton, "#notfound", false),
        "Element was not found");
  }

  /**
   * Verify FindIndexOfElementWithText works,
   * Validating the correct index is returned for a specific Selector and text.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void findIndexOfElementWithText() {
    Assert.assertEquals(find.findIndexOfElementWithText(automationPageModel.flowerTable, "Red"), 3);
  }

  /**
   * Verify FindIndexOfElementWithText works,
   * Validating index is not returned for a specific Selector and text.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void findIndexOfElementWithTextNotFound() {
    Assert.assertEquals(find.findIndexOfElementWithText(automationPageModel.flowerTable,
        "#notfound", false), -1);
  }

  /**
   *  Verify FindIndexOfElementWithText works,
   *  Validate that index of -1 is returned if an empty list is returned by ElemList.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void findIndexOfElementWithTextWithNotFoundElement() {
    Assert.assertEquals(find.findIndexOfElementWithText(automationPageModel.notInPage,
        "#notfound", false), -1);
  }

  /**
   * Verify FindIndexOfElementWithText works,
   * Validating the correct index is returned for a specific collection and text.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void findIndexOfElementInCollection() {
    Assert.assertEquals(find.findIndexOfElementWithText(
        find.findElements(automationPageModel.flowerTable), "10 in"), 0);
  }

  /**
   * Verify FindIndexOfElementWithText throws an error if the index is not found.
   */
  @Test(groups = TestCategories.SELENIUM, expectedExceptions = NotFoundException.class)
  public void findIndexOfElementInCollectionThrowException() {
    Assert.assertEquals(find.findIndexOfElementWithText(
        find.findElements(automationPageModel.notInPage), "not In page"), 0);
  }

  /**
   * Verify FindIndexOfElementWithText works,
   * Validating -1 is returned for a specific collection and text.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void findIndexOfElementInCollectionNotFound() {
    Assert.assertEquals(find.findIndexOfElementWithText(find.findElements(automationPageModel.flowerTable),
        "#notfound", false), -1);
  }

  /**
   * Verify FindIndexOfElementWithText works,
   * NotFoundException is thrown when an Empty input list is entered with assert == true.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void findIndexOfElementInCollectionEmptyInputList() {
    int index = find.findIndexOfElementWithText(
        (List<WebElement>) null, "#notfound", false);
    Assert.assertEquals(index, -1);
  }

  /**
   * Verify FindIndexOfElementWithText works,
   * NotFoundException is thrown when the element is not found and assert == true.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void findIndexOfElementInCollectionTextNotFoundAssertIsTrue() {
    int index = find.findIndexOfElementWithText(find.findElements(automationPageModel.flowerTable),
        "#notfound", false);
    Assert.assertEquals(index, -1);
  }
}