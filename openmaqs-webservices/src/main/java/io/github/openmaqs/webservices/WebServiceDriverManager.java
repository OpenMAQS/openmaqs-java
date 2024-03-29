/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.webservices;

import io.github.openmaqs.base.DriverManager;
import io.github.openmaqs.base.ITestObject;
import java.net.http.HttpClient;
import java.util.function.Supplier;

/**
 * The Web Service Driver Manager class.
 */
public class WebServiceDriverManager extends DriverManager<HttpClient> {

  /**
   * Web Service Driver variable.
   */
  private WebServiceDriver webServiceDriver;

  /**
   * Instantiates a new Web Service Driver Manager.
   *
   * @param getDriverFunction Function that specifies how to get the driver.
   * @param baseTestObject    The Base Test Object.
   */
  public WebServiceDriverManager(Supplier<HttpClient> getDriverFunction, ITestObject baseTestObject) {
    super(getDriverFunction, baseTestObject);
  }

  /**
   * Instantiates a new Web Service Driver Manager.
   *
   * @param driver         Web Service Driver
   * @param baseTestObject The Base Test Object.
   */
  public WebServiceDriverManager(WebServiceDriver driver, ITestObject baseTestObject) {
    super(driver::getHttpClient, baseTestObject);
    this.webServiceDriver = driver;
  }

  /**
   * Get the Web Service Driver.
   *
   * @return The Web Service Driver.
   */
  public WebServiceDriver getWebServiceDriver() {
    // Create default Web Service Driver if null.
    if (this.webServiceDriver == null) {
      this.webServiceDriver = new WebServiceDriver(getBase());
    }

    return this.webServiceDriver;
  }

  /**
   * Overrides the Web Service Driver.
   *
   * @param driver Web Service Driver
   */
  public void overrideDriver(WebServiceDriver driver) {
    this.webServiceDriver = driver;
  }

  /**
   * Close Method sets Base Driver to Null.
   */
  public void close() {
    this.setBaseDriver(null);
  }
}
