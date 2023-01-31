/*
 * Copyright 2022 (C) MAQS, All rights Reserved
 */

package io.github.maqs.cucumber;

import io.github.maqs.base.BaseTest;
import io.github.maqs.webservices.BaseWebServiceTest;

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



