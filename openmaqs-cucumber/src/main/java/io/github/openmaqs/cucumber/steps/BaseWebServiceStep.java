/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.cucumber.steps;

import io.github.openmaqs.cucumber.ScenarioContext;
import io.github.openmaqs.webservices.BaseWebServiceTest;
import io.github.openmaqs.webservices.WebServiceDriver;
import io.github.openmaqs.webservices.WebServiceTestObject;


/**
 * Base WebService cucumber step.
 */
public class BaseWebServiceStep extends BaseGenericStep {

  /**
   * Get the web driver.
   *
   * @return The web driver
   */
  public WebServiceDriver getDriver() {
    return getTestObject().getWebServiceDriver();
  }

  /**
   * Get the webservice test object.
   *
   * @return Selenium test object
   */
  @Override
  public WebServiceTestObject getTestObject() {
    return (WebServiceTestObject) ScenarioContext.get(
        ScenarioContext.MAQS_HOLDER, BaseWebServiceTest.class).getTestObject();
  }
}



