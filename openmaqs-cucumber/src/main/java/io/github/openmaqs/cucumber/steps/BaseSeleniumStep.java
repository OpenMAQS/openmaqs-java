/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.cucumber.steps;

import io.github.openmaqs.cucumber.ScenarioContext;
import io.github.openmaqs.selenium.BaseSeleniumTest;
import io.github.openmaqs.selenium.SeleniumTestObject;
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



