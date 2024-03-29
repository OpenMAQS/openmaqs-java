/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.openmaqs.appium.constants.PlatformType;
import io.github.openmaqs.base.BaseGenericTest;
import io.github.openmaqs.utilities.helper.TestCategories;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

/**
 * The Appium Driver Factory unit test class.
 */
public class AppiumDriverFactoryUnitTest extends BaseGenericTest {

  /**
   * The Sauce labs config.
   */
  private static DesiredCapabilities sauceLabsConfig;

  /**
   * Sets up.
   */
  @BeforeClass
  public void setUp() {
    Map<String, Object> capabilitiesAsObjects = AppiumConfig.getCapabilitiesAsObjects();
    sauceLabsConfig = new DesiredCapabilities();
    sauceLabsConfig.setCapability("username", capabilitiesAsObjects.get("username"));
    sauceLabsConfig.setCapability("accessKey", capabilitiesAsObjects.get("accessKey"));
    sauceLabsConfig.setCapability("deviceOrientation", "portrait");
  }

  /**
   * Test get default mobile driver.
   */
  @Test(groups = TestCategories.APPIUM)
  public void testGetDefaultMobileDriver() {
    AppiumDriver defaultMobileDriver = AppiumDriverFactory.getDefaultMobileDriver();
    Assert.assertNotNull(defaultMobileDriver, "Checking if default driver is null");
  }

  /**
   * Test test get default mobile driver android.
   */
  @Test(groups = TestCategories.APPIUM)
  public void testGetDefaultMobileDriverAndroid() {
    AppiumDriver defaultMobileDriver = AppiumDriverFactory.getDefaultMobileDriver(PlatformType.ANDROID);
    Assert.assertNotNull(defaultMobileDriver, "Checking if default driver is null");
  }

  /**
   * Test get default mobile options.
   */
  @Test(groups = TestCategories.APPIUM)
  public void testGetDefaultMobileOptions() {
    final DesiredCapabilities defaultMobileOptions = AppiumDriverFactory.getDefaultMobileOptions();
    // Consumer is used by the iterator for bulk processing and verification of the keys in the Map.
    // More elegant solution oppose to a for each.
    Consumer<String> assertionConsumer = defaultMobileOptions::is;
    defaultMobileOptions.getCapabilityNames().forEach(assertionConsumer);
  }

  /**
   * Test test get default mobile options.
   */
  @Test(groups = TestCategories.APPIUM)
  public void testGetDefaultMobileOptionsUsingObjects() {
    final Map<String, Object> capabilitiesAsObjects = AppiumConfig.getCapabilitiesAsObjects();
    DesiredCapabilities capabilities = AppiumDriverFactory.getDefaultMobileOptions(capabilitiesAsObjects);
    // Consumer is used by the iterator for bulk processing and verification of the
    // keys in the Map.
    // More elegant solution oppose to a for each.
    Consumer<String> assertionConsumer = (String s) -> {
      capabilities.is(s);
      Assert.assertEquals(capabilities.getCapability(s), capabilitiesAsObjects.get(s),
          String.format("Checking if capability value for key %s matches", s));
    };
    capabilities.getCapabilityNames().forEach(assertionConsumer);
  }

  /**
   * Test get android driver.
   */
  @Test(groups = TestCategories.APPIUM)
  public void testGetAndroidDriver() {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
    capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
    capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "6.0");
    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android GoogleAPI Emulator");
    AppiumDriverFactory.mergeCapabilities(capabilities, sauceLabsConfig.asMap());
    AppiumDriver androidDriver = AppiumDriverFactory.getAndroidDriver(AppiumConfig.getMobileHubUrl(),
        capabilities, AppiumConfig.getMobileTimeout());
    Assert.assertNotNull(androidDriver, "Checking if android driver is null");
  }

  /**
   * Test get ios driver.
   */
  @Test(groups = TestCategories.APPIUM)
  public void testGetIOSDriver() {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
    capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
    capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.2");
    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone X Simulator");

    AppiumDriver iosDriver = AppiumDriverFactory.getIosDriver(AppiumConfig.getMobileHubUrl(),
        AppiumDriverFactory.mergeCapabilities(capabilities, sauceLabsConfig.asMap()), AppiumConfig.getMobileTimeout());
    Assert.assertNotNull(iosDriver, "Checking if ios driver is null");
  }

  /**
   * Test get Windows driver.
   */
  @Test(groups = TestCategories.APPIUM)
  @Ignore("Work on Windows implementation")
  public void testGetWindowsDriver() {
    DesiredCapabilities appCapabilities = new DesiredCapabilities();
    appCapabilities.setCapability("app", "Microsoft.WindowsCalculator_8wekyb3d8bbwe!App");
    appCapabilities.setCapability(MobileCapabilityType.UDID, "0C0E26E7-966B-4C89-A765-32C5C997A456");
    AppiumDriver windowsDriver = null;
    try {
      windowsDriver = AppiumDriverFactory.getWindowsDriver(new URL("http://127.0.0.1:4723"), appCapabilities,
          AppiumConfig.getMobileTimeout());
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
    Assert.assertNotNull(windowsDriver, "Checking if windows app driver is null");
  }

  /**
   * Test create driver.
   */
  @Test(groups = TestCategories.APPIUM, expectedExceptions = WebDriverException.class)
  public void testCreateDriverException() {
    Supplier<AppiumDriver> appiumDriverSupplier = () -> {
      try {
        return new AppiumDriver(new URL("http://127.0.0.1:4723"),
            new DesiredCapabilities());
      } catch (MalformedURLException e) {
        e.printStackTrace();
      }
      return null;
    };

    AppiumDriverFactory.createDriver(appiumDriverSupplier);
  }
}