/*
 * Copyright 2022 (C) MAQS-Framework, All rights Reserved
 */

package com.maqs.cucumber.steps;

import com.maqs.cucumber.ScenarioContext;
import com.maqs.selenium.BaseSeleniumTest;
import com.maqs.selenium.SeleniumTestObject;
import org.openqa.selenium.WebDriver;

/**
 * Base Selenium cucumber step.
 */
public class BaseSeleniumStep extends BaseGenericStep {

  /**
   * Get the web driver.
   *
   * @return The web driver
   */
  public WebDriver getDriver() {
    return getTestObject().getWebDriver();
  }

  /**
   * Get the Selenium test object.
   *
   * @return Selenium test object
   */
  @Override
  public SeleniumTestObject getTestObject() {
    return (SeleniumTestObject) ScenarioContext.get(
        ScenarioContext.MAQS_HOLDER, BaseSeleniumTest.class).getTestObject();
  }
}



