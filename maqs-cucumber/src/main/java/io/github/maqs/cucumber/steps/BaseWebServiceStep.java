/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package io.github.maqs.cucumber.steps;

import io.github.maqs.cucumber.ScenarioContext;
import io.github.maqs.webservices.BaseWebServiceTest;
import io.github.maqs.webservices.WebServiceDriver;
import io.github.maqs.webservices.WebServiceTestObject;


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



