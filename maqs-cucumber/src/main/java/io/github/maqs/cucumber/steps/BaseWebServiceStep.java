/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.cucumber.steps;

import com.cognizantsoftvision.maqs.cucumber.ScenarioContext;
import com.cognizantsoftvision.maqs.webservices.BaseWebServiceTest;
import com.cognizantsoftvision.maqs.webservices.WebServiceDriver;
import com.cognizantsoftvision.maqs.webservices.WebServiceTestObject;


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



