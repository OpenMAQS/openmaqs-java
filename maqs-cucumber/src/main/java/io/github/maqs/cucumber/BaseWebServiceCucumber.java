/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.cucumber;

import com.cognizantsoftvision.maqs.base.BaseTest;
import com.cognizantsoftvision.maqs.webservices.BaseWebServiceTest;

/**
 * The base WebService cucumber object.
 */
public class BaseWebServiceCucumber extends BaseCucumberTestNG {

  /**
   * Create a test object.
   *
   * @return A base webservice test object
   */
  @Override
  public BaseTest createSpecificBaseTest() {
    return new BaseWebServiceTest();
  }
}



