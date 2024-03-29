/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.utilities.helper;

import java.util.ArrayList;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The List Processor unit test class.
 */
public class ListProcessorUnitTest {

  /**
   * Unit Test for creating a comma delimited string.
   */
  @Test(groups = TestCategories.UTILITIES)
  public void createCommaDelimitedStringTest() {
    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("Maine");
    stringList.add("Massachusetts");
    stringList.add("New Hampshire");
    stringList.add("Connecticut");
    stringList.add("Rhode Island");
    stringList.add("Vermont");

    String expectedText = "Maine, Massachusetts, New Hampshire, Connecticut, Rhode Island, Vermont";
    String actualText = ListProcessor.createCommaDelimitedString(stringList, false);

    if (!expectedText.equals(actualText)) {
      Assert.fail(StringProcessor.safeFormatter(
          "Expected string %s does not match Actual string %s", expectedText, actualText));
    }
  }

  /**
   * Unit Test for creating a sorted comma delimited string.
   */
  @Test(groups = TestCategories.UTILITIES)
  public void createSortedCommaDelimitedStringTest() {
    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("Maine");
    stringList.add("Massachusetts");
    stringList.add("New Hampshire");
    stringList.add("Connecticut");
    stringList.add("Rhode Island");
    stringList.add("Vermont");

    String expectedText = "Connecticut, Maine, Massachusetts, New Hampshire, Rhode Island, Vermont";
    String actualText = ListProcessor.createCommaDelimitedString(stringList, true);

    if (!expectedText.equals(actualText)) {
      Assert.fail(StringProcessor.safeFormatter(
          "Expected string %s does not match Actual string %s", expectedText, actualText));
    }
  }

  /**
   * Unit Test for comparing two lists of strings.
   */
  @Test(groups = TestCategories.UTILITIES)
  public void listOfStringsToCompareTest() {
    StringBuilder results = new StringBuilder();
    ArrayList<String> expectedList = new ArrayList<>();
    expectedList.add("Maine");
    expectedList.add("Massachusetts");
    expectedList.add("New Hampshire");
    expectedList.add("Connecticut");
    expectedList.add("Rhode Island");
    expectedList.add("Vermont");

    ArrayList<String> actualList = new ArrayList<>();
    actualList.add("Massachusetts");
    actualList.add("Connecticut");
    actualList.add("Rhode Island");
    actualList.add("Vermont");
    actualList.add("Maine");
    actualList.add("New Hampshire");

    ListProcessor.listOfStringsComparer(expectedList, actualList, results, false);

    if (results.length() > 0) {
      Assert.fail(StringProcessor.safeFormatter("%s%s", results.toString(), System.lineSeparator()));
    }
  }

  /**
   * Unit Test for comparing two lists of strings by order.
   */
  @Test(groups = TestCategories.UTILITIES)
  public void listOfStringsToCompareByOrderTest() {
    ArrayList<String> expectedList = new ArrayList<>();
    expectedList.add("Maine");
    expectedList.add("Massachusetts");
    expectedList.add("New Hampshire");
    expectedList.add("Connecticut");
    expectedList.add("Rhode Island");
    expectedList.add("Vermont");

    ArrayList<String> actualList = new ArrayList<>();
    actualList.add("Maine");
    actualList.add("Massachusetts");
    actualList.add("New Hampshire");
    actualList.add("Connecticut");
    actualList.add("Rhode Island");
    actualList.add("Vermont");

    StringBuilder results = new StringBuilder();
    ListProcessor.listOfStringsComparer(expectedList, actualList, results, true);

    if (results.length() > 0) {
      Assert.fail(StringProcessor.safeFormatter("%s%s", results.toString(), System.lineSeparator()));
    }
  }

  /**
   * Verify that listOfStringsToCompare handles lists of unequal length as expected.
   */
  @Test(groups = TestCategories.UTILITIES)
  public void listOfStringsToCompareUnequalLengths() {
    ArrayList<String> expectedList = new ArrayList<>();
    expectedList.add("A");
    expectedList.add("B");

    ArrayList<String> actualList = new ArrayList<>();
    actualList.add("A");

    StringBuilder results = new StringBuilder();
    boolean isEqual = ListProcessor.listOfStringsComparer(expectedList, actualList, results, true);
    Assert.assertTrue(results.toString().contains("The following lists are not the same size:"));
    Assert.assertFalse(isEqual);
  }

  /**
   * Verify that ListOfStringsToCompare handles not finding an item in the expected list correctly.
   */
  @Test(groups = TestCategories.UTILITIES)
  public void listOfStringToCompareItemNotFound() {
    ArrayList<String> expectedList = new ArrayList<>();
    expectedList.add("A");
    expectedList.add("B");

    ArrayList<String> actualList = new ArrayList<>();
    actualList.add("A");
    actualList.add("C");

    StringBuilder results = new StringBuilder();
    boolean isEqual = ListProcessor.listOfStringsComparer(expectedList, actualList, results, false);
    Assert.assertTrue(
        results.toString().contains("[C] was found in the ArrayList but was not expected"));
    Assert.assertFalse(isEqual);
  }

  /**
   * Verify that listOfStringsToCompare handles inequality between lists as expected.
   */
  @Test(groups = TestCategories.UTILITIES)
  public void listOfStringsToCompareItemNotMatching() {
    ArrayList<String> expectedList = new ArrayList<>();
    expectedList.add("A");
    expectedList.add("B");

    ArrayList<String> actualList = new ArrayList<>();
    actualList.add("A");
    actualList.add("C");

    StringBuilder results = new StringBuilder();
    boolean isEqual = ListProcessor.listOfStringsComparer(expectedList, actualList, results, true);
    Assert.assertTrue(results.toString().contains("Expected [B] but found [C]"));
    Assert.assertFalse(isEqual);
  }
}
