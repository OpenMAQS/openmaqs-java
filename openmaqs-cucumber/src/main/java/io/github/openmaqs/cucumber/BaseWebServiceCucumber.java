/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.cucumber;

import io.github.openmaqs.base.BaseTest;
import io.github.openmaqs.webservices.BaseWebServiceTest;

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



