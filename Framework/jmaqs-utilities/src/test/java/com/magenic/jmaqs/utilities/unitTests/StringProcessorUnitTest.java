/* 
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.utilities.unitTests;

import com.magenic.jmaqs.utilities.helper.StringProcessor;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit tests for the StringProcessor class.
 */
public class StringProcessorUnitTest {
  /**
   * Test method for checking JSON strings.
   */
  @Test
  public void stringFormatterCheckForJson() {
    String message = StringProcessor.safeFormatter("{This is a test for JSON}");
    Assert.assertEquals("{This is a test for JSON}", message);
  }

  /**
   * Test method for checking string format.
   */
  @Test
  public void stringFormatterCheckForStringFormat() {
    String message = StringProcessor.safeFormatter("This %s should return %s", "Test", "Test");
    Assert.assertEquals("This Test should return Test", message);
  }

  /**
   * Verify that StringProcessor.SafeFormatter handles errors in the message as expected.
   */
  @Test
  public void stringFormatterThrowException() {
    String message = StringProcessor.safeFormatter("This {0} should return {5}", "Test", "Test", "Test");
    Assert.assertTrue(message.contains("This {0} should return {5}"));
  }
}
