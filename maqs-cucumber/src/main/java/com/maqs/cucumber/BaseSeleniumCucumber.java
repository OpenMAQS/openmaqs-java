/*
 * Copyright 2022 (C) MAQS-Framework, All rights Reserved
 */

package com.maqs.cucumber;

import com.maqs.base.BaseTest;
import com.maqs.selenium.BaseSeleniumTest;

/**
 * The base Selenium cucumber object.
 */
public class BaseSeleniumCucumber extends BaseCucumberTestNG {

  /**
   * Create a test object.
   *
   * @return A Selenium base test object
   */
  @Override
  public BaseTest createSpecificBaseTest() {
    return new BaseSeleniumTest();
  }
}



