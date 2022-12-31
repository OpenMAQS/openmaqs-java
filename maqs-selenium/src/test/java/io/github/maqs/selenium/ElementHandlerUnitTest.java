/*
 * Copyright 2022 (C) MAQS, All rights Reserved
 */

package io.github.maqs.selenium;

import io.github.maqs.selenium.exceptions.ElementHandlerException;
import io.github.maqs.selenium.factories.UIWaitFactory;
import io.github.maqs.selenium.pageModel.AutomationPageModel;
import io.github.maqs.utilities.helper.ListProcessor;
import io.github.maqs.utilities.helper.TestCategories;
import io.github.maqs.utilities.logging.FileLogger;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

/**
 * Unit tests for ElementHandler class.
 */
public class ElementHandlerUnitTest extends BaseSeleniumTest {

  private AutomationPageModel automationPageModel;

  /**
   * Navigate to test page url and wait for page to load.
   */
  @BeforeMethod
  private void setUp() {
    automationPageModel = new AutomationPageModel(this.getTestObject());
    getWebDriver().navigate().to(automationPageModel.testSiteAutomationUrl);
    UIWaitFactory.getWaitDriver(getWebDriver()).waitForPageLoad();
  }

  /**
   * Unit Test for creating a sorted comma delimited String.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void createSortedCommaDelimitedStringFromWebElementsTest() {
    String expectedText = "Hard Drive, Keyboard, Monitor, Motherboard, Mouse, Power Supply";
    verifyText(ElementHandler.createCommaDelimitedString(
        getWebDriver(), automationPageModel.computerPartsListOptions, true), expectedText);
  }

  /**
   * Unit Test for creating a sorted comma delimited String.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void createCommaDelimitedStringFromWebElementsTest() {
    String expectedText = "Motherboard, Power Supply, Hard Drive, Monitor, Mouse, Keyboard";
    verifyText(ElementHandler.createCommaDelimitedString(
       getWebDriver(), automationPageModel.computerPartsListOptions), expectedText);
  }

  /**
   * Unit test for entering text into a text box and getting text from a text box.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void setTextBoxAndVerifyValueTest() {
    String expectedValue = "Tester";
    ElementHandler.setTextBox(getWebDriver(), automationPageModel.firstNameTextBox, expectedValue);
    String actualValue = ElementHandler.getElementAttribute(getWebDriver(), automationPageModel.firstNameTextBox);
    verifyText(actualValue, expectedValue);
  }

  /**
   * Unit test for entering nothing into the text box and getting the exception.
   */
  @Test(groups = TestCategories.SELENIUM, expectedExceptions = ElementHandlerException.class)
  public void setTextBoxException() {
    String expectedValue = "";
    ElementHandler.setTextBox(getWebDriver(), automationPageModel.firstNameTextBox, expectedValue);
  }

  /**
   * Unit Test for checking a radio button.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void checkRadioButtonTest() {
    ElementHandler.clickButton(getWebDriver(), automationPageModel.femaleRadioButton, false);
    Assert.assertTrue(UIWaitFactory.getWaitDriver(getWebDriver()).waitForClickableElement(
            automationPageModel.femaleRadioButton).isSelected(), "Radio button was not selected");
  }

  /**
   * Unit Test for checking a checkbox.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void checkCheckBoxTest() {
    ElementHandler.checkCheckBox(getWebDriver(), automationPageModel.checkbox, true);
    Assert.assertTrue(UIWaitFactory.getWaitDriver(getWebDriver()).waitForClickableElement(
        automationPageModel.checkbox).isSelected(), "Checkbox was not enabled");
  }

  /**
   * Unit Test for get element attribute function.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void getElementAttributeTest() {
    String actualText = ElementHandler.getElementAttribute(
        this.getWebDriver(), automationPageModel.firstNameTextBox, "type");
    verifyText(actualText, "text");
  }

  /**
   * Unit Test for selecting an item from a dropdown and getting the selected item
   * from a dropdown (By actual value).
   */
  @Test(groups = TestCategories.SELENIUM)
  public void selectItemFromDropDownTest() {
    String expectedSelection = "Emily";
    ElementHandler.selectDropDownOption(getWebDriver(), automationPageModel.nameDropdown, expectedSelection);
    String actualSelection = ElementHandler.getSelectedOptionFromDropdown(getWebDriver(),
        automationPageModel.nameDropdown);
    verifyText(actualSelection, expectedSelection);
  }

  /**
   * Unit Test for selecting an item from a dropdown and getting the selected item
   * from a dropdown (By list value).
   */
  @Test(groups = TestCategories.SELENIUM)
  public void selectItemFromDropDownByValueTest() {
    String expectedSelection = "Jack";
    ElementHandler.selectDropDownOptionByValue(getWebDriver(), automationPageModel.nameDropdown, "two");
    String actualSelection = ElementHandler.getSelectedOptionFromDropdown(getWebDriver(),
        automationPageModel.nameDropdown);
    verifyText(actualSelection, expectedSelection);
  }

  /**
   * Unit Test for selecting multiple items from a list box and getting all
   * selected items in a list box(By actual value).
   */
  @Test(groups = TestCategories.SELENIUM)
  public void selectMultipleItemsFromListBoxTest() {
    final StringBuilder results = new StringBuilder();
    ArrayList<String> itemsToSelect = new ArrayList<>();
    itemsToSelect.add("Monitor");
    itemsToSelect.add("Hard Drive");
    itemsToSelect.add("Keyboard");

    ElementHandler.selectMultipleElementsFromListBox(
        getWebDriver(), automationPageModel.computerPartsList, itemsToSelect);
    ArrayList<String> selectedItems = (ArrayList<String>) ElementHandler.getSelectedOptionsFromDropdown(
        getWebDriver(), automationPageModel.computerPartsList);
    ListProcessor.listOfStringsComparer(itemsToSelect, selectedItems, results, false);

    if (results.length() > 0) {
      Assert.fail(results.toString());
    }
  }

  /**
   * Unit Test for selecting multiple items from a list box and getting all
   * selected items in a list box(By list value).
   */
  @Test(groups = TestCategories.SELENIUM)
  public void selectMultipleItemsFromListBoxTestByValue() {
    ArrayList<String> itemsToSelect = new ArrayList<>();
    itemsToSelect.add("one");
    itemsToSelect.add("four");
    itemsToSelect.add("five");

    ElementHandler.selectMultipleElementsFromListBoxByValue(
        getWebDriver(), automationPageModel.computerPartsList, itemsToSelect);
    ArrayList<String> selectedItems = (ArrayList<String>) ElementHandler.getSelectedOptionsFromDropdown(
        getWebDriver(), automationPageModel.computerPartsList);

    if (selectedItems.size() != 3) {
      Assert.fail("Does not contain 3 elements: " + selectedItems);
    }
  }

  /**
   * Unit test for ClickElementByJavaScript using a hover dropdown, where dropdown
   * is not visible.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void clickElementByJavascriptFromHoverDropdown() {
    ElementHandler.clickElementByJavaScript(getWebDriver(), automationPageModel.iFrameDropDownButton);
    UIWaitFactory.getWaitDriver(getWebDriver()).waitForPageLoad();
    UIWaitFactory.getWaitDriver(getWebDriver()).waitForExactText(automationPageModel.iFramePageTitle, "Index");
  }

  /**
   * Test to verify scrolling into view.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void scrollIntoView() {
    ElementHandler.scrollIntoView(getWebDriver(), automationPageModel.checkbox);
  }

  /**
   * Test to verify scrolling into view.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void scrollIntoViewWithCoordinates() {
    ElementHandler.scrollIntoView(getWebDriver(), automationPageModel.checkbox, 50, 0);
  }

  /**
   * Test to verify scrolling into view when passing a parent WebElement.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void scrollIntoViewElement() {
    WebElement element = this.getWebDriver().findElement(By.cssSelector("body"));
    ElementHandler.scrollIntoView(element, automationPageModel.checkbox);
  }

  /**
   * Test to verify scrolling into view when passing a parent WebElement with coordinates.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void scrollIntoViewElementWithCoordinates() {
    WebElement element = this.getWebDriver().findElement(By.cssSelector("body"));
    ElementHandler.scrollIntoView(element, automationPageModel.checkbox, 50, 0);
  }

  /**
   * Test to verify scrolling into view.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void executingScrolling() {
    ElementHandler.executeScrolling(getWebDriver(), 50, 0);
  }

  /**
   * Unit test for ClickElementByJavaScript where the element is not present.
   */
  @Test(expectedExceptions = NoSuchElementException.class, groups = TestCategories.SELENIUM)
  public void clickElementByJavascriptFromHoverDropdownNotFound() {
    ElementHandler.clickElementByJavaScript(getWebDriver(), By.cssSelector(".NotPresent"));
  }

  /**
   * Verify slow type text is correctly typed.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void slowTypeTest() {
    ElementHandler.slowType(getWebDriver(), automationPageModel.firstNameTextBox, "Test input slow type");
    Assert.assertEquals(
        UIWaitFactory.getWaitDriver(getWebDriver()).waitForClickableElement(automationPageModel.firstNameTextBox)
            .getAttribute("value"), "Test input slow type");
  }

  /**
   * Verify Send Secret Keys suspends logging.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void sendSecretTextSuspendLoggingTest() throws IOException {
    this.getWebDriver().findElement(automationPageModel.firstNameTextBox).sendKeys("somethingTest");
    this.getWebDriver().findElement(automationPageModel.firstNameTextBox).clear();
    ElementHandler.sendSecretKeys(getWebDriver(), automationPageModel.firstNameTextBox, "secretKeys", this.getLogger());

    FileLogger logger = (FileLogger) this.getTestObject().getLogger();
    File file = new File(logger.getFilePath());

    Assert.assertTrue(FileUtils.readFileToString(file, StandardCharsets.UTF_8).contains("somethingTest"),
        "Failed to assert the logger logged normal keys.");
    Assert.assertFalse(FileUtils.readFileToString(file, StandardCharsets.UTF_8).contains("secretKeys"),
        "Failed to assert the logger did not log secret keys.");
  }

  /**
   * Verify Send Secret Keys re-enables after suspending logging.
   */
  @Ignore
  @Test(groups = TestCategories.SELENIUM)
  public void sendSecretTextContinueLoggingTest() throws IOException {
    ElementHandler.sendSecretKeys(getWebDriver(), automationPageModel.firstNameTextBox, "secretKeys", this.getLogger());
    this.getWebDriver().findElement(automationPageModel.firstNameTextBox).clear();
    this.getWebDriver().findElement(automationPageModel.firstNameTextBox).sendKeys("somethingTest");

    FileLogger logger = (FileLogger) this.getTestObject().getLogger();
    File file = new File(logger.getFilePath());

    Assert.assertFalse(FileUtils.readFileToString(file, StandardCharsets.UTF_8).contains("secretKeys"),
        "Failed to assert the logger did not log the secret keys.");
    Assert.assertTrue(FileUtils.readFileToString(file, StandardCharsets.UTF_8).contains("somethingTest"),
        "Failed to assert the logger did continued logging normally after secret keys.");
  }

  /**
   * Verify Send Secret throws exception if element is not intractable.
   */
  @Test(groups = TestCategories.SELENIUM, expectedExceptions = ElementHandlerException.class)
  public void sendSecretTextCatchException() {
    this.getWebDriver().findElement(automationPageModel.firstNameTextBox).sendKeys("somethingTest");
    this.getWebDriver().findElement(automationPageModel.firstNameTextBox).clear();
    ElementHandler.sendSecretKeys(getWebDriver(), automationPageModel.flowerTableTitle, "secretKeys", this.getLogger());
  }

    /**
     * Verify two Strings are equal. If not fail test.
     *
     * @param actualValue   Actual displayed text
     * @param expectedValue Expected text
     */
  private static void verifyText(String actualValue, String expectedValue) {
    Assert.assertEquals(actualValue, expectedValue, "Expected String does not match actual");
  }
}