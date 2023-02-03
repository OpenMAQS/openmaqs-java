# <img src="resources/MAQS.jpg" height="32" width="32"> Playwright Test Object

## Inheritance Hierarchy
```java
BaseTestObject
    io.github.openmaqs.playwright.PlaywrightTestObject
```
Package: io.github.openmaqs.playwright;  
Assembly: import io.github.openmaqs.playwright.PlaywrightTestObject

## Syntax
java
```java
public class PlaywrightTestObject {}
```

## Overview
Takes care of setup, teardown, and management of the page driver.

[GetPageDriver](#GetPageDriver)  
[SetPageDriver](#SetPageDriver)  
[GetPageManager](#GetPageManager)     

## GetPageDriver
Gets the Playwright driver
```java
PageDriver driver = this.getTestObject.getPageDriver();
```

## SetPageDriver
Sets the Playwright driver
```java
this.getTestObject.setPageDriver(PageDriverFactory.getDefaultPageDriver());
```

## GetPageManager
Gets the Playwright driver manager
```java
PlaywrightDriverManager mobileDriver = this.PlaywrightManager.getPageManager();
```