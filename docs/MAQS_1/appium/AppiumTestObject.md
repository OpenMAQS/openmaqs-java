# <img src="resources/MAQS.jpg" height="32" width="32"> Appium Test Object

## Inheritance Hierarchy
```java
BaseTestObject
    io.github.openmaqs.appium.AppiumTestObject
```
Package: io.github.openmaqs.appium;  
Assembly: import io.github.openmaqs.appium.AppiumTestObject

## Syntax
java
```java
public class AppiumTestObject {}
```

## Overview
Takes care of setup, teardown, and management of the appium driver.

[GetAppiumDriver](#GetAppiumDriver)  
[SetAppiumDriver](#SetAppiumDriver)  
[GetAppiumManager](#GetAppiumManager)     

## GetAppiumDriver
Gets the Appium driver
```java
AppiumDriver<WebElement> driver = this.AppiumManager.getAppiumDriver();
```

## SetAppiumDriver
Sets the Appium driver
```java
appiumTestObject.setAppiumDriver(AppiumDriverFactory.getDefaultMobileDriver());
```

## GetAppiumManager
Gets the Appium driver manager
```java
MoblieDriverManager mobileDriver = this.AppiumManager.getMobileDriver();
```