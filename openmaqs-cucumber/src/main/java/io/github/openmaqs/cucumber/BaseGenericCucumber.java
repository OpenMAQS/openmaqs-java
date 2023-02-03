/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.cucumber;

import io.github.openmaqs.base.BaseGenericTest;
import io.github.openmaqs.base.BaseTest;

/**
 * The base generic cucumber object.
 */
public class BaseGenericCucumber extends BaseCucumberTestNG {

  /**
   * Create a test object.
   *
   * @return A generic base test object
   */
  @Override
  public BaseTest createSpecificBaseTest() {
    return new BaseGenericTest();
  }
}



