/*
 * Copyright 2022 (C) MAQS, All rights Reserved
 */

package com.maqs.cucumber;

import com.maqs.base.BaseGenericTest;
import com.maqs.base.BaseTest;

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



