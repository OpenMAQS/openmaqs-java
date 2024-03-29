/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.windows.WindowsDriver;
import io.github.openmaqs.appium.constants.PlatformType;
import io.github.openmaqs.utilities.helper.StringProcessor;
import java.net.URL;
import java.time.Duration;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * The Appium Driver Factory class.
 */
public class AppiumDriverFactory {

  /**
   * Instantiates a new Appium driver factory.
   */
  private AppiumDriverFactory() {
  }

  /**
   * Gets default mobile driver.
   *
   * @return the default mobile driver
   */
  public static AppiumDriver getDefaultMobileDriver() {
    return getDefaultMobileDriver(AppiumConfig.getDeviceType());
  }

  /**
   * Gets default mobile driver.
   *
   * @param deviceType the device type
   * @return the default mobile driver
   */
  public static AppiumDriver getDefaultMobileDriver(PlatformType deviceType) {
    AppiumDriver appiumDriver;
    URL mobileHubUrl = AppiumConfig.getMobileHubUrl();
    Duration duration = AppiumConfig.getCommandTimeout();
    DesiredCapabilities capabilities = getDefaultMobileOptions();

    switch (deviceType) {
      case ANDROID:
        appiumDriver = getAndroidDriver(mobileHubUrl, capabilities, duration);
        break;
      case IOS:
        appiumDriver = getIosDriver(mobileHubUrl, capabilities, duration);
        break;
      case WINDOWS:
        appiumDriver = getWindowsDriver(mobileHubUrl, capabilities, duration);
        break;
      default:
        throw new IllegalStateException(
            StringProcessor.safeFormatter("Mobile OS type '%s' is not supported " + deviceType));
    }

    if (deviceType != PlatformType.WINDOWS) {
      appiumDriver.manage().timeouts()
          .implicitlyWait(AppiumConfig.getMobileTimeout());
    }

    return appiumDriver;
  }

  /**
   * Gets default mobile options.
   *
   * @return the default mobile options
   */
  public static DesiredCapabilities getDefaultMobileOptions() {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, AppiumConfig.getDeviceName());
    capabilities.setCapability(CapabilityType.PLATFORM_NAME, AppiumConfig.getPlatformName());
    capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, AppiumConfig.getPlatformVersion());
    return mergeCapabilities(capabilities, AppiumConfig.getCapabilitiesAsObjects());
  }

  /**
   * Gets default mobile options.
   *
   * @param capabilities the capabilities
   * @return the default mobile options
   */
  public static DesiredCapabilities getDefaultMobileOptions(Map<String, Object> capabilities) {
    return new DesiredCapabilities(capabilities);
  }

  /**
   * Gets android driver.
   *
   * @param mobileHub the mobile hub
   * @param options   the options
   * @param timeout   the timeout
   * @return the android driver
   */
  public static AppiumDriver getAndroidDriver(URL mobileHub, DesiredCapabilities options,
      Duration timeout) {

    return createDriver(() -> {
      AppiumDriver driver = new AndroidDriver(mobileHub, options);
      driver.manage().timeouts().implicitlyWait(timeout);
      return driver;
    });
  }

  /**
   * Gets ios driver.
   *
   * @param mobileHub the mobile hub
   * @param options   the options
   * @param timeout   the timeout
   * @return the ios driver
   */
  public static AppiumDriver getIosDriver(URL mobileHub, DesiredCapabilities options, Duration timeout) {
    return createDriver(() -> {
      AppiumDriver driver = new IOSDriver(mobileHub, options);
      driver.manage().timeouts().implicitlyWait(timeout);
      return driver;
    });
  }

  /**
   * Gets Windows driver.
   *
   * @param mobileHub the mobile hub
   * @param options   the options
   * @param timeout   the timeout
   * @return the Windows driver
   */
  public static AppiumDriver getWindowsDriver(URL mobileHub, DesiredCapabilities options,
      Duration timeout) {
    return createDriver(() -> {
      AppiumDriver driver = new WindowsDriver(mobileHub, options);
      driver.manage().timeouts().implicitlyWait(timeout);
      return driver;
    });

  }

  /**
   * Merge capabilities desired capabilities.
   *
   * @param capabilities          original capabilities object
   * @param capabilitiesAsObjects Map of String, Object
   * @return merged capabilities object
   */
  public static DesiredCapabilities mergeCapabilities(DesiredCapabilities capabilities,
      Map<String, Object> capabilitiesAsObjects) {

    Consumer<String> mergeConsumer = (String s) -> capabilities
        .setCapability(s, capabilitiesAsObjects.get(s));
    capabilitiesAsObjects.keySet().iterator().forEachRemaining(mergeConsumer);
    return capabilities;
  }

  /**
   * Create driver appium driver.
   *
   * @param createFunction the create function
   * @return the appium driver
   */
  public static AppiumDriver createDriver(
      Supplier<AppiumDriver> createFunction) {
    AppiumDriver appiumDriver = null;

    try {
      appiumDriver = createFunction.get();
      return appiumDriver;
    } catch (Exception e) {
      if (e.getClass().isInstance(IllegalArgumentException.class)) {
        throw e;
      } else {
        try {
          Optional<AppiumDriver> driverOptional = Optional.ofNullable(appiumDriver);
          driverOptional.ifPresent(AppiumDriver::quit);
        } catch (Exception quitException) {
          throw new WebDriverException(
              "Appium driver setup and teardown failed. Your driver may be out of date",
              quitException);
        }
      }

      throw new WebDriverException("Your driver may be out of date or unsupported.", e);
    }
  }
}
