/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.appium;

import io.github.openmaqs.appium.constants.PlatformType;
import io.github.openmaqs.utilities.helper.Config;
import io.github.openmaqs.utilities.helper.ConfigSection;
import io.github.openmaqs.utilities.helper.TestCategories;
import java.util.HashMap;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * The Appium Config unit test class.
 */
public class AppiumConfigUnitTest {

  /**
   * The Username.
   */
  private final String username = "username";
  /**
   * The Browser name.
   */
  private final String browserName = "browserName";
  /**
   * The Access key.
   */
  private final String accessKey = "accessKey";
  /**
   * The Device orientation.
   */
  private final String deviceOrientation = "deviceOrientation";

  /**
   * Test get os version.
   */
  @Test(groups = TestCategories.APPIUM)
  public void testGetOSVersion() {
    String osVersion = AppiumConfig.getPlatformVersion();
    Assert.assertTrue(osVersion.equalsIgnoreCase("11.0"));
  }

  /**
   * Test get device name.
   */
  @Test(groups = TestCategories.APPIUM)
  public void testGetDeviceName() {
    String deviceName = AppiumConfig.getDeviceName();
    Assert.assertTrue(deviceName.equalsIgnoreCase("Android GoogleAPI Emulator"));
  }

  /**
   * Test get mobile hub url string.
   */
  // FIXME: Oct 31
  @Ignore
  @Test(groups = TestCategories.APPIUM)
  public void testGetMobileHubUrlString() {
    String mobileHubUrl = AppiumConfig.getMobileHubUrlString();
    Assert.assertEquals(mobileHubUrl, "http://ondemand.saucelabs.com:80/wd/hub");
  }

  /**
   * Test get capabilities as strings.
   */
  @Test(groups = TestCategories.APPIUM)
  public void testGetCapabilitiesAsStrings() {
    Map<String, String> capabilitiesAsStrings = AppiumConfig.getCapabilitiesAsStrings();

    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(capabilitiesAsStrings.containsKey(username));
    softAssert.assertEquals(capabilitiesAsStrings.get(username), "JMAQS");
    softAssert.assertTrue(capabilitiesAsStrings.containsKey(accessKey));
    softAssert
        .assertNotEquals(capabilitiesAsStrings.get(accessKey), "");
    softAssert.assertTrue(capabilitiesAsStrings.containsKey(browserName));
    softAssert.assertEquals(capabilitiesAsStrings.get(browserName), "Chrome");
    softAssert.assertTrue(capabilitiesAsStrings.containsKey(deviceOrientation));
    softAssert.assertEquals(capabilitiesAsStrings.get(deviceOrientation), "portrait");
    softAssert.assertAll();
  }

  /**
   * Test get capabilities as objects.
   */
  @Test(groups = TestCategories.APPIUM)
  public void testGetCapabilitiesAsObjects() {
    Map<String, Object> capabilitiesAsObjects = AppiumConfig.getCapabilitiesAsObjects();

    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(capabilitiesAsObjects.containsKey(username));
    softAssert.assertEquals(capabilitiesAsObjects.get(username), "JMAQS");
    softAssert.assertTrue(capabilitiesAsObjects.containsKey(accessKey));
    softAssert
        .assertNotEquals(capabilitiesAsObjects.get(accessKey), "");
    softAssert.assertTrue(capabilitiesAsObjects.containsKey(browserName));
    softAssert.assertEquals(capabilitiesAsObjects.get(browserName), "Chrome");
    softAssert.assertTrue(capabilitiesAsObjects.containsKey(deviceOrientation));
    softAssert.assertEquals(capabilitiesAsObjects.get(deviceOrientation), "portrait");
    softAssert.assertAll();
  }

  /**
   * Test get save page source on fail.
   */
  @Test(groups = TestCategories.APPIUM)
  public void testGetSavePageSourceOnFail() {
    Assert.assertFalse(AppiumConfig.getSavePageSourceOnFail());
  }

  /**
   * Test get soft assert screenshot.
   */
  @Test(groups = TestCategories.APPIUM)
  public void testGetSoftAssertScreenShot() {
    Assert.assertFalse(AppiumConfig.getSoftAssertScreenShot());
  }

  /**
   * Test get command timeout.
   */
  @Test(groups = TestCategories.APPIUM)
  public void testGetCommandTimeout() {
    Assert.assertEquals(AppiumConfig.getCommandTimeout().toMillis(), 122000);
  }

  /**
   * Test get command timeout error.
   */
  @Test(groups = TestCategories.APPIUM, expectedExceptions = NumberFormatException.class)
  @Ignore("Impacting future tests since there is no way to reload config")
  public void testGetCommandTimeoutError() {
    HashMap<String, String> configValues = new HashMap<>();
    configValues.put("MobileCommandTimeout", "sixty thousand");
    Config.addTestSettingValues(configValues, ConfigSection.APPIUM_MAQS, true);
    AppiumConfig.getCommandTimeout();
  }

  /**
   * Test get mobile timeout.
   */
  @Test(groups = TestCategories.APPIUM)
  public void testGetMobileTimeout() {
    Assert.assertEquals(AppiumConfig.getMobileTimeout().toMillis(), 10000);
  }

  /**
   * Test get device type.
   */
  @Test(groups = TestCategories.APPIUM)
  public void testGetDeviceType() {
    Assert.assertEquals(AppiumConfig.getDeviceType(), PlatformType.ANDROID);
  }

  /**
   * Test get device type android.
   */
  @Test(groups = TestCategories.APPIUM)
  public void testGetDeviceTypeAndroid() {
    Assert.assertEquals(AppiumConfig.getDeviceType("android"), PlatformType.ANDROID);
  }

  /**
   * Test get device type ios.
   */
  @Test(groups = TestCategories.APPIUM)
  public void testGetDeviceTypeIOS() {
    Assert.assertEquals(AppiumConfig.getDeviceType("ios"), PlatformType.IOS);
  }

  /**
   * Test get device type windows.
   */
  @Test(groups = TestCategories.APPIUM)
  public void testGetDeviceTypeWindows() {
    Assert.assertEquals(AppiumConfig.getDeviceType("windows"), PlatformType.WINDOWS);
  }

  /**
   * Test get device type error.
   */
  @Test(groups = TestCategories.APPIUM, expectedExceptions = IllegalArgumentException.class)
  public void testGetDeviceTypeError() {
    AppiumConfig.getDeviceType("linux");
  }
}
