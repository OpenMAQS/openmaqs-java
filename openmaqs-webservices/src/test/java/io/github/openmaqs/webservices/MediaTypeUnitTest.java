/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.webservices;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The Media Type unit test class.
 */
public class MediaTypeUnitTest {

  /**
   * Tests the string value of App_Json
   */
  @Test
  public void testTestToStringAppJson() {
    Assert.assertEquals(MediaType.APP_JSON.toString(), MediaType.APP_JSON.getMediaTypeString());
  }

  /**
   * Tests the string value of App_Xml
   */
  @Test
  public void testTestToStringAppXml() {
    Assert.assertEquals(MediaType.APP_XML.toString(), MediaType.APP_XML.getMediaTypeString());
  }

  /**
   * Tests the string value of Plain_Text
   */
  @Test
  public void testTestToStringPlainText() {
    Assert.assertEquals(MediaType.PLAIN_TEXT.toString(), MediaType.PLAIN_TEXT.getMediaTypeString());
  }

  /**
   * Test the string value of Image_Png
   */
  @Test
  public void testTestToStringImagePng() {
    Assert.assertEquals(MediaType.IMAGE_PNG.toString(), MediaType.IMAGE_PNG.getMediaTypeString());
  }
}