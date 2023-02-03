/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.maqs.appium;

import io.appium.java_client.AppiumDriver;
import io.github.maqs.base.BaseTestObject;
import io.github.maqs.utilities.logging.ILogger;
import java.util.function.Supplier;

/**
 * The Appium Test Object class.
 */
public class AppiumTestObject extends BaseTestObject implements IAppiumTestObject {

  /**
   * Instantiates a new Appium test object.
   *
   * @param appiumDriver           the appium driver
   * @param logger                 the logger
   * @param fullyQualifiedTestName the fully qualified test name
   */
  public AppiumTestObject(AppiumDriver appiumDriver, ILogger logger,
      String fullyQualifiedTestName) {
    this(() -> appiumDriver, logger, fullyQualifiedTestName);
  }

  /**
   * Instantiates a new Appium test object.
   *
   * @param appiumDriverSupplier   the appium driver supplier
   * @param logger                 the logger
   * @param fullyQualifiedTestName the fully qualified test name
   */
  public AppiumTestObject(Supplier<AppiumDriver> appiumDriverSupplier, ILogger logger,
      String fullyQualifiedTestName) {
    super(logger, fullyQualifiedTestName);
    this.getManagerStore().put(MobileDriverManager.class.getCanonicalName(),
        new MobileDriverManager(appiumDriverSupplier, this));
  }

  /**
   * {@inheritDoc}
   */
  public AppiumDriver getAppiumDriver() {
    return this.getAppiumManager().getMobileDriver();
  }

  /**
   * {@inheritDoc}
   */
  public MobileDriverManager getAppiumManager() {
    return (MobileDriverManager) this.getManagerStore()
        .get(MobileDriverManager.class.getCanonicalName());
  }

  /**
   * {@inheritDoc}
   */
  public void setAppiumDriver(AppiumDriver appiumDriver) {
    this.getManagerStore().put(MobileDriverManager.class.getCanonicalName(),
        new MobileDriverManager((() -> appiumDriver), this));
  }

  /**
   * {@inheritDoc}
   */
  public void setAppiumDriver(Supplier<AppiumDriver> appiumDriverSupplier) {
    this.getManagerStore().put(MobileDriverManager.class.getCanonicalName(),
        new MobileDriverManager(appiumDriverSupplier, this));
  }
}
