/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.webservices.webServiceDriverRequestUnitTest;

import io.github.openmaqs.utilities.helper.TestCategories;
import io.github.openmaqs.webservices.HttpClientFactory;
import io.github.openmaqs.webservices.MediaType;
import io.github.openmaqs.webservices.WebServiceConfig;
import io.github.openmaqs.webservices.WebServiceDriver;
import io.github.openmaqs.webservices.models.Product;
import org.springframework.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.http.HttpResponse;
import java.util.Collections;

/**
 * The Web Driver Service Driver Delete unit test class.
 */
public class WebServiceDriverDeleteUnitTest {

  /**
   * String to hold the URL.
   */
  private static final String baseUrl = WebServiceConfig.getWebServiceUri();

  /**
   * The web service driver to be used in a test.
   */
  private static final WebServiceDriver webServiceDriver = new WebServiceDriver(HttpClientFactory.getDefaultClient());

  /**
   * Delete Json request to assert status code.
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void deleteJSONSerializedVerifyStatusCode()
      throws IOException, InterruptedException {
    HttpResponse<String> result = webServiceDriver.delete(
        baseUrl + "/api/XML_JSON/Delete/1", MediaType.APP_JSON, true);
    Assert.assertEquals(result.statusCode(), HttpStatus.OK.value());
  }

  /**
   * Delete Json request to assert status code.
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void deleteJSONSerializedVerifyStatusCodeWithoutHeaderOverride()
      throws IOException, InterruptedException {
    HttpResponse<String> result = webServiceDriver.delete(
        baseUrl + "/api/XML_JSON/Delete/2", MediaType.APP_JSON, false);
    Assert.assertEquals(result.statusCode(), HttpStatus.UNAUTHORIZED.value());
  }

  /**
   * Delete Json request to assert status code.
   * @throws IOException if the exception is thrown
   * @throws InterruptedException if the exception is thrown
   */
  @Ignore
  @Test(groups = TestCategories.WEB_SERVICE)
  public void deleteJSONSerializedVerifyStatusCodeWithHeaderOverride()
      throws IOException, InterruptedException {
    HttpResponse<String> result = webServiceDriver.delete(
        baseUrl + "/api/XML_JSON/Delete/2", MediaType.APP_JSON,
        (Type) Collections.singletonMap("pass", "word"), true);
    Assert.assertEquals(result.statusCode(), HttpStatus.OK.value());
    result = webServiceDriver.delete(
        baseUrl + "/api/XML_JSON/Delete/2", MediaType.APP_JSON,
        (Type) Collections.singletonMap("pass", "word"), HttpStatus.OK);
    Assert.assertEquals(result.statusCode(), HttpStatus.OK.value());
  }

  /**
   * Delete with JSON type.
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void deleteJSONWithType() throws IOException, InterruptedException {
    HttpResponse<String> result = webServiceDriver.delete(baseUrl + "/api/XML_JSON/Delete/1",
        MediaType.APP_JSON, true);
    Assert.assertEquals(result.body(), "");
  }

  /**
   * Delete XML request using status code
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void deleteXMLSerializedVerifyStatusCode() throws IOException, InterruptedException {
    HttpResponse<String> result = webServiceDriver.delete(
        baseUrl + "/api/XML_JSON/Delete/1", MediaType.APP_XML, true);
    Assert.assertEquals(result.statusCode(), HttpStatus.OK.value());
  }

  /**
   * Verify that the response does not return a message.
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void deleteXMLSerializedVerifyEmptyString() throws IOException, InterruptedException {
    HttpResponse<String> result = webServiceDriver.delete(
       baseUrl + "/api/XML_JSON/Delete/1", MediaType.APP_XML, true);
    Assert.assertEquals(result.body(),"");
  }

  /**
   * Delete string request without content utility.
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void deleteStringWithoutMakeContent() throws IOException, InterruptedException {
    HttpResponse<String> result = webServiceDriver.delete(
      baseUrl + "/api/String/Delete/1", MediaType.PLAIN_TEXT, true);
    Assert.assertEquals(result.body(),"");
  }

  /**
   * Delete string request to verify status code.
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void deleteStringMakeContentStatusCode() throws IOException, InterruptedException {
    HttpResponse<String> result = webServiceDriver.delete(
        baseUrl + "/api/String/Delete/1", MediaType.PLAIN_TEXT, true);
    Assert.assertEquals(result.statusCode(), HttpStatus.OK.value());
  }

  /**
   * Delete request to vi
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void deleteExpectContentError() throws IOException, InterruptedException {
   HttpResponse<String> result = webServiceDriver.delete(
        baseUrl + "/api/String/Delete/43", MediaType.PLAIN_TEXT, false);
    Assert.assertEquals(result.statusCode(), HttpStatus.NOT_FOUND.value());
  }

  /**
   * Delete error.
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void deleteExpectError() throws IOException, InterruptedException {
   HttpResponse<String> result = webServiceDriver.delete(baseUrl + "/api/String/Delete/43",
        MediaType.PLAIN_TEXT, false);
    Assert.assertEquals(result.body(), "{\"message\":\"Resource was not found\"}");
  }

  /**
   * Test type parameterized Delete request with expected status.
   * @throws IOException if an exception is thrown
   * @throws InterruptedException if an exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void deleteTypeParamWithExpectedStatus() throws IOException, InterruptedException {
    HttpResponse<String> result = webServiceDriver.delete(
        baseUrl +"/api/XML_JSON/Delete/1", MediaType.APP_JSON, Product.class, HttpStatus.OK);
    Assert.assertNull(result);
  }

  /**
   * Test type parameterized Delete request  True expected status.
   * @throws IOException if an exception is thrown
   * @throws InterruptedException if an exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void deleteTypeParamWithTrueExpectedStatus() throws IOException, InterruptedException {
    HttpResponse<String> result = webServiceDriver.delete(baseUrl +"/api/XML_JSON/Delete/1",
        MediaType.APP_JSON, Product.class, true);
    Assert.assertNull(result);
  }

  /**
   * Test Delete request with expected status.
   * @throws IOException if an exception is thrown
   * @throws InterruptedException if an exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void deleteWithExpectedStatus() throws IOException, InterruptedException {
    HttpResponse<String> result = webServiceDriver.delete(
        baseUrl +"/api/XML_JSON/Delete/1", MediaType.APP_JSON, HttpStatus.OK);
    Assert.assertNotNull(result);
  }

  /**
   * Test Delete with response request with expected status.
   * @throws IOException if an exception is thrown
   * @throws InterruptedException if an exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void deleteWithResponseWithExpectedStatus() throws IOException, InterruptedException {
    HttpResponse<String> result = webServiceDriver.delete(
        baseUrl +"/api/XML_JSON/Delete/1", MediaType.APP_JSON, HttpStatus.OK);
    Assert.assertNotNull(result);
  }
}