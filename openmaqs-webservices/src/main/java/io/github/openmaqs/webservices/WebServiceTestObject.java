/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.webservices;

import io.github.openmaqs.base.BaseTestObject;
import io.github.openmaqs.utilities.logging.ILogger;
import java.net.http.HttpClient;
import java.util.function.Supplier;

/**
 * The Web Service Test Object class.
 */
public class WebServiceTestObject extends BaseTestObject implements IWebServiceTestObject {

  /**
   * Instantiates a new Web Service Test Object with a supplier.
   * 
   * @param getDriverSupplier      the get driver supplier.
   * @param logger                 the Logger.
   * @param fullyQualifiedTestName the fully qualified test name.
   */
  public WebServiceTestObject(Supplier<HttpClient> getDriverSupplier,
      ILogger logger, String fullyQualifiedTestName) {
    super(logger, fullyQualifiedTestName);
    this.getManagerStore().put((WebServiceDriverManager.class).getCanonicalName(),
        new WebServiceDriverManager(getDriverSupplier, this));
  }

  /**
   * Initializes a new instance of the WebServiceTestObject.
   * 
   * @param driver                 The web service driver.
   * @param logger                 The Logger Object.
   * @param fullyQualifiedTestName The fully qualified test name.
   */
  public WebServiceTestObject(WebServiceDriver driver,
      ILogger logger, String fullyQualifiedTestName) {
    super(logger, fullyQualifiedTestName);
    this.getManagerStore().put((WebServiceDriverManager.class).getCanonicalName(),
        new WebServiceDriverManager(driver, this));
  }

  /**
   * Get the web service wrapper for the WebServiceTestObject.
   * 
   * @return A web service driver
   */
  public WebServiceDriver getWebServiceDriver() {
    return this.getWebServiceDriverManager().getWebServiceDriver();
  }

  /**
   * Gets the Web Service driver managers.
   *
   * @return the web service manager.
   */
  public WebServiceDriverManager getWebServiceDriverManager() {
    return (WebServiceDriverManager) this.getManagerStore()
        .get(WebServiceDriverManager.class.getCanonicalName());
  }

  /**
   * overrides the web service driver using the WebServiceDriver.
   * 
   * @param driver the Web Service Driver to be committed.
   */
  public void setWebServiceDriver(WebServiceDriver driver) {
    this.getManagerStore().put(WebServiceDriverManager.class.getCanonicalName(),
        new WebServiceDriverManager(driver, this));
  }

  /**
   * overrides the web service driver using a closable Http Client.
   * 
   * @param httpClient the Http Client to be set.
   */
  public void setWebServiceDriver(HttpClient httpClient) {
    this.getManagerStore().put(WebServiceDriverManager.class.getCanonicalName(),
        new WebServiceDriverManager((() -> httpClient), this));
  }

  /**
   * overrides the Web Service driver using a supplier.
   * 
   * @param webServiceSupplier the web service driver supplier.
   */
  public void setWebServiceDriver(Supplier<HttpClient> webServiceSupplier) {
    this.getManagerStore().put(WebServiceDriverManager.class.getCanonicalName(),
        new WebServiceDriverManager(webServiceSupplier, this));
  }
}
