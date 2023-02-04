/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.base;

import io.github.openmaqs.utilities.helper.TestCategories;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The Driver Manager unit test class.
 */
public class DriverManagerUnitTest extends BaseGenericTest {

  @Test(groups = TestCategories.FRAMEWORK)
  public void testGetTestObject() {
    DriverManager<String> driverManager = getDriverManager();
    Assert.assertNotNull(driverManager.getTestObject());
  }

  @Test(groups = TestCategories.FRAMEWORK)
  public void testGetBaseDriver() {
    DriverManager<String> driverManager = getDriverManager();
    driverManager.setBaseDriver("Fake String");
    Assert.assertNotNull(driverManager.getBaseDriver());

  }

  @Test(groups = TestCategories.FRAMEWORK)
  public void testSetBaseDriver() {
    DriverManager<String> driverManager = getDriverManager();
    driverManager.setBaseDriver("Fake String");
    Assert.assertNotNull(driverManager.getBaseDriver());
  }

  @Test(groups = TestCategories.FRAMEWORK)
  public void testIsDriverInitializedTrue() {
    DriverManager<String> driverManager = getDriverManager();
    Assert.assertNotNull(driverManager.getBase());
    Assert.assertTrue(driverManager.isDriverInitialized());
  }

  @Test(groups = TestCategories.FRAMEWORK)
  public void testIsDriverInitializedFalse() {
    DriverManager<String> driverManager = getDriverManager();
    Assert.assertFalse(driverManager.isDriverInitialized());
  }

  @Test(groups = TestCategories.FRAMEWORK)
  public void testGetLogger() {
    DriverManager<String> driverManager = getDriverManager();
    Assert.assertNotNull(driverManager.getLogger());
  }

  @Test(groups = TestCategories.FRAMEWORK)
  public void testGetBase() {
    DriverManager<String> driverManager = getDriverManager();
    Assert.assertNotNull(driverManager.getBase());
  }

  private DriverManager<String> getDriverManager() {
    return new DriverManager<>(() -> "Fake String here", getTestObject()) {
      @Override public void close() {
        this.close();
      }
    };
  }
}