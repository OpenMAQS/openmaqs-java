/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.appium;

import io.appium.java_client.AppiumDriver;
import io.github.openmaqs.base.BaseGenericTest;
import java.util.function.Supplier;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The Mobile Driver Manager unit test class.
 */
public class MobileDriverManagerUnitTest extends BaseGenericTest {

  /**
   * Test get mobile driver.
   */
  @Test
  public void testGetMobileDriver() {
    Supplier<AppiumDriver> supplier = AppiumDriverFactory::getDefaultMobileDriver;
    AppiumTestObject appiumTestObject = new AppiumTestObject(supplier, this.getLogger(),
        this.getTestObject().getFullyQualifiedTestName());

    try (MobileDriverManager mobileDriverManager = new MobileDriverManager(supplier, appiumTestObject)) {
      Assert.assertNotNull(mobileDriverManager.getMobileDriver(), "Expected Mobile Driver to not be null.");
    }
  }

  /**
   * Test close.
   */
  @Test
  public void testClose() {
    Supplier<AppiumDriver> supplier = AppiumDriverFactory::getDefaultMobileDriver;
    MobileDriverManager mobileDriverManager = new MobileDriverManager(supplier, this.getTestObject());

    mobileDriverManager.close();
    Assert.assertNull(mobileDriverManager.getBaseDriver(), "Expected Mobile Driver to be null.");
  }

  /**
   * Test close with instantiated driver.
   */
  @Test
  public void testCloseWithInstantiatedDriver() {
    MobileDriverManager mobileDriverManager = new MobileDriverManager(
        AppiumDriverFactory.getDefaultMobileDriver(), this.getTestObject());
    mobileDriverManager.close();
    Assert.assertNull(mobileDriverManager.getBaseDriver(), "Expected Mobile Driver to be null.");
  }

  /**
   * Test close null base driver.
   */
  @Test
  public void testCloseNullBaseDriver() {
    Supplier<AppiumDriver> supplier = AppiumDriverFactory::getDefaultMobileDriver;
    MobileDriverManager mobileDriverManager = new MobileDriverManager(supplier, this.getTestObject());

    // Close once to make Base Driver null
    mobileDriverManager.close();

    // Close again with driver not initialized
    mobileDriverManager.close();

    Assert.assertNull(mobileDriverManager.getBaseDriver(), "Expected Base Driver to be null.");
  }
}
