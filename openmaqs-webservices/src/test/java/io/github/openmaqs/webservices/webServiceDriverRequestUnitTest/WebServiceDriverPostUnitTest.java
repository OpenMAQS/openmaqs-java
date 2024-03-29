/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.webservices.webServiceDriverRequestUnitTest;

import io.github.openmaqs.utilities.helper.TestCategories;
import io.github.openmaqs.webservices.HttpClientFactory;
import io.github.openmaqs.webservices.MediaType;
import io.github.openmaqs.webservices.WebServiceConfig;
import io.github.openmaqs.webservices.WebServiceDriver;
import io.github.openmaqs.webservices.WebServiceUtilities;
import io.github.openmaqs.webservices.models.Product;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.springframework.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The Web Driver Service Driver Post unit test class.
 */
public class WebServiceDriverPostUnitTest {

  /**
   * String to hold the URL.
   */
  private static final String baseUrl = WebServiceConfig.getWebServiceUri();

  /**
   * Product object to be used in a test.
   */
  private final Product product = new Product(4, "ff", "ff", BigDecimal.valueOf(3.25));

  /**
   * The web service driver to be used in a test.
   */
  private final WebServiceDriver webServiceDriver = new WebServiceDriver(
      HttpClientFactory.getDefaultClient(), HttpRequest.newBuilder());

  /**
   * Post JSON request to verify status codes.
   * @throws IOException if the exception is thrown
   * @throws InterruptedException if the exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void postJSONSerializedVerifyStatusCode() throws IOException, InterruptedException {
    HttpResponse<String> result = webServiceDriver.post(baseUrl + "/api/XML_JSON/Post",
        MediaType.APP_JSON, product, true);
    Assert.assertEquals(result.statusCode(), HttpStatus.OK.value());
  }

  /**
   * Post JSON stream request to verify status codes.
   * @throws IOException if the exception is thrown
   * @throws InterruptedException if the exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void postJSONStreamSerializedVerifyStatusCode()
      throws IOException, InterruptedException {
    HttpResponse<String> result = webServiceDriver.post(baseUrl + "/api/XML_JSON/Post",
        MediaType.APP_JSON, product, HttpStatus.OK);
    Assert.assertEquals(result.statusCode(), HttpStatus.OK.value());
  }

  /**
   * Post XML request to verify status codes.
   * @throws IOException if the exception is thrown
   * @throws InterruptedException if the exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void postXMLSerializedVerifyStatusCode()
      throws IOException, InterruptedException {
    String content = WebServiceUtilities.createStringEntity(product, MediaType.APP_XML);
    HttpResponse<String> result = webServiceDriver.post(baseUrl + "/api/XML_JSON/Post",
        MediaType.APP_XML, content, HttpStatus.OK);
    Assert.assertEquals(result.statusCode(), HttpStatus.OK.value());
  }

  /**
   * Post with JSON.
   * @throws IOException if exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void postWithJson() throws IOException, InterruptedException {
    String content = WebServiceUtilities.createStringEntity(product, MediaType.APP_JSON);
    HttpResponse<String> result = webServiceDriver.post(baseUrl + "/api/XML_JSON/Post",
        MediaType.APP_JSON, content, true);
    Assert.assertTrue(result.body().isEmpty());
  }

  /**
   * Post with JSON.
   * @throws IOException if exception is thrown
   * @throws InterruptedException if exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void postWithXml() throws IOException, InterruptedException {
    String content = WebServiceUtilities.createStringEntity(product, MediaType.APP_XML);
    HttpResponse<String> result = webServiceDriver.post(baseUrl + "/api/XML_JSON/Post",
        MediaType.APP_XML, content, true);
    Assert.assertTrue(result.body().isEmpty());
  }

  /**
   * Post XML to verify no string is returned.
   * @throws IOException if the exception is thrown
   * @throws InterruptedException if the exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void postXMLSerializedVerifyEmptyStringError() throws IOException, InterruptedException {
    HttpResponse<String> result = webServiceDriver.post(baseUrl + "/api/XML_JSON/Post",
        MediaType.APP_XML, null, false);
    Assert.assertTrue(result.body().contains("An error occurred while deserializing input data."));
  }

  /**
   * Posts a string without utility.
   * @throws IOException if the exception is thrown
   * @throws InterruptedException if the exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void postStringWithoutMakeContent() throws IOException, InterruptedException {
    HttpResponse<String> result = webServiceDriver.post(baseUrl + "/api/String",
        MediaType.PLAIN_TEXT, "test", true);
    Assert.assertEquals(result.body(), "");
  }

  /**
   * Posts a stream without utility.
   * @throws IOException if the exception is thrown
   * @throws InterruptedException if the exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void postReturnWhenNoContentInResponse() throws IOException, InterruptedException {
    Product result = webServiceDriver.post(baseUrl + "/api/XML_JSON/Post",
        MediaType.APP_JSON, product, Product.class, HttpStatus.OK);
    Assert.assertNull(result);

    result = webServiceDriver.post(baseUrl + "/api/XML_JSON/Post",
        MediaType.APP_XML, product, Product.class, true);
    Assert.assertNull(result);
  }

  /**
   * Posts a string with utility.
   * @throws IOException if the exception is thrown
   * @throws InterruptedException if the exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void postStringWithMakeContent() throws IOException, InterruptedException {
    String content = WebServiceUtilities.createStringEntity("Test", MediaType.PLAIN_TEXT);
    HttpResponse<String> result = webServiceDriver.post(baseUrl + "/api/String",
        MediaType.PLAIN_TEXT, content, true);
    Assert.assertEquals(result.body(), "");
  }

  /**
   * Posts a string without utility to verify status code.
   * @throws IOException if the exception is thrown
   * @throws InterruptedException if the exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void postStringWithoutContentStatusCode() throws IOException, InterruptedException {
    HttpResponse<String> result = webServiceDriver.post(baseUrl + "/api/String",
        MediaType.PLAIN_TEXT, "Test" , true);
    Assert.assertEquals(result.statusCode(), HttpStatus.OK.value());
  }

  /**
   * Verifying other http status codes.
   * @throws IOException if the exception is thrown
   * @throws InterruptedException if the exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void postExpectContentError() throws IOException, InterruptedException {
    HttpResponse<String> result = webServiceDriver.post(baseUrl + "/api/String",
        MediaType.PLAIN_TEXT, "", false);
    Assert.assertEquals(result.statusCode(), HttpStatus.BAD_REQUEST.value());
  }

  /**
   * Testing string returned.
   * @throws IOException if the exception is thrown
   * @throws InterruptedException if the exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void postExpectStringError() throws IOException, InterruptedException {
    HttpResponse<String> result = webServiceDriver.post(baseUrl + "/api/String",
        MediaType.PLAIN_TEXT, "", false);
    Assert.assertEquals(result.body(), "{\"message\":\"Value is required\"}");
  }

  /**
   * Testing string returned.
   * @throws IOException if the exception is thrown
   * @throws InterruptedException if the exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void postExpectStringErrorEmptyHttpContent() throws IOException, InterruptedException {
    HttpResponse<String> result = webServiceDriver.post(baseUrl + "/api/String",
        MediaType.PLAIN_TEXT, "", false);
    Assert.assertEquals(result.body(), "{\"message\":\"Value is required\"}");
  }

  /**
   * Test type parameterized Posting a request with expected status.
   * @throws IOException if an exception is thrown
   * @throws InterruptedException if an exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void postTypeParamWithExpectedStatus() throws IOException, InterruptedException {
    HttpResponse<String> res = webServiceDriver.post(baseUrl + "/api/XML_JSON/Post",
        MediaType.APP_JSON, product, HttpStatus.OK);
    Assert.assertNotNull(res);
  }

  /**
   * Test Post request with expected status.
   * @throws IOException if an exception is thrown
   * @throws InterruptedException if an exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void postWithExpectedStatus() throws IOException, InterruptedException {
    String req = WebServiceUtilities.createStringEntity(product, MediaType.APP_JSON);
    HttpResponse<String> res = webServiceDriver.post(baseUrl + "/api/XML_JSON/Post",
        MediaType.APP_JSON, req, HttpStatus.OK);
    Assert.assertNotNull(res);
  }

  /**
   * Test more parameters Posting a request with expected status
   * @throws IOException if an exception is thrown
   * @throws InterruptedException if an exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void postMoreParamsWithExpectedStatus() throws IOException, InterruptedException {
    HttpResponse<String> res = webServiceDriver.post(baseUrl + "/api/XML_JSON/Post",
        MediaType.APP_JSON, product, true);
    Assert.assertNotNull(res);
  }

  /**
   * Test more parameters Post with response request with expected status.
   * @throws IOException if an exception is thrown
   * @throws InterruptedException if an exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void postMoreParamsWithResponseWithExpectedStatus() throws IOException, InterruptedException {
    HttpResponse<String> res = webServiceDriver.post(baseUrl + "/api/XML_JSON/Post",
        MediaType.APP_JSON, product, true);
    Assert.assertNotNull(res);
  }

  /**
   * Test Post with response request with expected status.
   * @throws IOException if an exception is thrown
   * @throws InterruptedException if an exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void postWithResponseWithExpectedStatus() throws IOException, InterruptedException {
    HttpResponse<String> res = webServiceDriver.post(baseUrl + "/api/XML_JSON/Post",
        MediaType.APP_JSON, product, HttpStatus.OK);
    Assert.assertNotNull(res);
  }
}