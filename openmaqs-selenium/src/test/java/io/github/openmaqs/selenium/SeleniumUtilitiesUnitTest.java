/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.selenium;

import io.github.openmaqs.base.BaseGenericTest;
import io.github.openmaqs.selenium.factories.UIWaitFactory;
import io.github.openmaqs.utilities.helper.StringProcessor;
import io.github.openmaqs.utilities.helper.TestCategories;
import io.github.openmaqs.utilities.logging.ConsoleLogger;
import io.github.openmaqs.utilities.logging.FileLogger;
import io.github.openmaqs.utilities.logging.HtmlFileLogger;
import io.github.openmaqs.utilities.logging.Logger;
import java.io.File;
import java.nio.file.Paths;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The Selenium Utilities unit test class.
 */
public class SeleniumUtilitiesUnitTest extends BaseGenericTest {

    /**
     * Test capture screenshot no append.
     */
    @Test(groups = TestCategories.SELENIUM)
    public void testCaptureScreenshotNoAppend() {
        WebDriver webDriver = WebDriverFactory.getDefaultBrowser();
        try {
            FileLogger fileLogger = (FileLogger) this.getTestObject().getLogger();
            SeleniumTestObject testObject = new SeleniumTestObject(webDriver, fileLogger,
                    this.getTestObject().getFullyQualifiedTestName());
            this.setTestObject(testObject);

            // Open Google and take a screenshot
            webDriver.navigate().to("http://www.google.com");
            boolean isSuccess = SeleniumUtilities.captureScreenshot(webDriver, testObject);

            // Assert screenshot was successful
            Assert.assertTrue(isSuccess, "Expected Screenshot to be successful.");
            String[] arrayOfAssociatedFiles = testObject.getArrayOfAssociatedFiles();
            Assert.assertTrue(
                    new File((arrayOfAssociatedFiles[arrayOfAssociatedFiles.length - 1])).exists(),
                    "Checking that expected file path exists");
        } finally {
            webDriver.quit();
        }
    }

    /**
     * Test capture screenshot append.
     */
    @Test(groups = TestCategories.SELENIUM)
    public void testCaptureScreenshotAppend() {
        WebDriver webDriver = WebDriverFactory.getDefaultBrowser();
        try {
            FileLogger fileLogger = (FileLogger) this.getTestObject().getLogger();
            SeleniumTestObject testObject = new SeleniumTestObject(webDriver, fileLogger,
                    this.getTestObject().getFullyQualifiedTestName());
            this.setTestObject(testObject);

            // Open Google and take a screenshot
            webDriver.navigate().to("http://www.google.com");
            final String testAppend = "testAppend";
            boolean isSuccess = SeleniumUtilities.captureScreenshot(webDriver, testObject, testAppend);

            // Assert screenshot was successful
            Assert.assertTrue(isSuccess, "Expected Screenshot to be successful.");
            String[] arrayOfAssociatedFiles = testObject.getArrayOfAssociatedFiles();
            Assert.assertTrue(
                    new File((arrayOfAssociatedFiles[arrayOfAssociatedFiles.length - 1])).exists(),
                    "Checking that expected file path exists");
            Assert.assertTrue(
                    new File((arrayOfAssociatedFiles[arrayOfAssociatedFiles.length - 1])).getName()
                            .contains(testAppend),
                    "Checking that appended value was added to file");
        } finally {
            webDriver.quit();
        }
    }

    /**
     * Test capture screenshot append.
     */
    @Test(groups = TestCategories.SELENIUM)
    public void testCaptureScreenshotNullWebDriver() {
        WebDriver webDriver = WebDriverFactory.getDefaultBrowser();
        try {
            FileLogger fileLogger = (FileLogger) this.getTestObject().getLogger();
            SeleniumTestObject testObject = new SeleniumTestObject(webDriver, fileLogger,
                    this.getTestObject().getFullyQualifiedTestName());
            this.setTestObject(testObject);

            // Open Google and take a screenshot
            webDriver.navigate().to("http://www.google.com");
            final String testAppend = "testAppend";
            boolean isSuccess = SeleniumUtilities.captureScreenshot(null, testObject, testAppend);

            // Assert screenshot was successful
            Assert.assertFalse(isSuccess, "Expected Screenshot to be successful.");
        } finally {
            webDriver.quit();
        }
    }

    /**
     * Test capture screenshot append.
     */
    @Test(groups = TestCategories.SELENIUM)
    public void testCaptureScreenshotAppendScreenshot() {
        WebDriver webDriver = WebDriverFactory.getDefaultBrowser();
        try {
            HtmlFileLogger fileLogger = new HtmlFileLogger("Capture Screenshot Append");
            this.getTestObject().setLogger(fileLogger);
            SeleniumTestObject testObject = new SeleniumTestObject(webDriver, fileLogger,
                    this.getTestObject().getFullyQualifiedTestName());
            this.setTestObject(testObject);

            // Open Google and take a screenshot
            webDriver.navigate().to("http://www.google.com");
            final String testAppend = "testAppend";
            boolean isSuccess = SeleniumUtilities.captureScreenshot(webDriver, testObject, testAppend);

            // Assert screenshot was successful
            Assert.assertTrue(isSuccess, "Expected Screenshot to be successful.");
            String[] arrayOfAssociatedFiles = testObject.getArrayOfAssociatedFiles();
            Assert.assertTrue(
                    new File((arrayOfAssociatedFiles[arrayOfAssociatedFiles.length - 1])).exists(),
                    "Checking that expected file path exists");
            Assert.assertTrue(
                    new File((arrayOfAssociatedFiles[arrayOfAssociatedFiles.length - 1])).getName()
                            .contains(testAppend),
                    "Checking that appended value was added to file");
        } finally {
            webDriver.quit();
        }
    }

    /**
     * Test capture screenshot console logger.
     */
    @Test(groups = TestCategories.SELENIUM)
    public void testCaptureScreenshotConsoleLogger() {
        WebDriver webDriver = WebDriverFactory.getDefaultBrowser();
        try {
            ConsoleLogger consoleLogger = new ConsoleLogger();
            SeleniumTestObject testObject = new SeleniumTestObject(webDriver, consoleLogger,
                    this.getTestObject().getFullyQualifiedTestName());
            this.setTestObject(testObject);

            // Open Google and take a screenshot
            webDriver.navigate().to("http://www.google.com");
            boolean isSuccess = SeleniumUtilities.captureScreenshot(webDriver, testObject, "testAppend");

            // Assert screenshot was NOT successful
            Assert.assertFalse(isSuccess, "Expected Screenshot to NOT be successful.");
        } finally {
            webDriver.quit();
        }
    }

    /**
     * Test capture screenshot custom directory file name.
     */
    @Test(groups = TestCategories.SELENIUM)
    public void testCaptureScreenshotCustomDirectoryFileName() {
        WebDriver webDriver = WebDriverFactory.getDefaultBrowser();
        try {
            FileLogger fileLogger = (FileLogger) this.getTestObject().getLogger();
            SeleniumTestObject testObject = new SeleniumTestObject(webDriver, fileLogger,
                    this.getTestObject().getFullyQualifiedTestName());
            this.setTestObject(testObject);

            // Open Google and take a screenshot
            webDriver.navigate().to("http://www.google.com");
            String dateTime = DateTimeFormatter.ofPattern(Logger.DEFAULT_DATE_TIME_FORMAT,
                    Locale.getDefault()).format(LocalDateTime.now(Clock.systemUTC()));
            String filePath = SeleniumUtilities.captureScreenshot(webDriver, testObject,
                    fileLogger.getDirectory(),
                    StringProcessor.safeFormatter("%s - %s", "TestCustomName", dateTime));

            Assert.assertEquals(filePath,
                    Paths.get(StringProcessor.safeFormatter("%s%s - %s%s",
                            fileLogger.getDirectory(), File.separator + "TestCustomName", dateTime,
                            ".png")).normalize().toString());
            Assert.assertTrue(new File(filePath).exists(),
                    "Checking that screenshot file exists at expected path.");
        } finally {
            webDriver.quit();
        }
    }

    /**
     * Test save page source no append.
     */
    @Test(groups = TestCategories.SELENIUM)
    public void testSavePageSourceNoAppend() {
        WebDriver webDriver = WebDriverFactory.getDefaultBrowser();
        try {
            FileLogger fileLogger = (FileLogger) this.getTestObject().getLogger();
            SeleniumTestObject testObject = new SeleniumTestObject(webDriver, fileLogger,
                    this.getTestObject().getFullyQualifiedTestName());
            this.setTestObject(testObject);

            // Open Google and take a screenshot
            webDriver.navigate().to("http://www.google.com");
            boolean isSuccess = SeleniumUtilities.savePageSource(webDriver, testObject);

            // Assert screenshot was successful
            Assert.assertTrue(isSuccess, "Expected Screenshot to be successful.");
            String[] arrayOfAssociatedFiles = testObject.getArrayOfAssociatedFiles();
            Assert.assertTrue(
                    new File((arrayOfAssociatedFiles[arrayOfAssociatedFiles.length - 1])).exists(),
                    "Checking that expected file path exists");
        } finally {
            webDriver.quit();
        }
    }

    /**
     * Test save page source append.
     */
    @Test(groups = TestCategories.SELENIUM)
    public void testSavePageSourceAppend() {
        WebDriver webDriver = WebDriverFactory.getDefaultBrowser();
        try {
            FileLogger fileLogger = (FileLogger) this.getTestObject().getLogger();
            SeleniumTestObject testObject = new SeleniumTestObject(webDriver, fileLogger,
                    this.getTestObject().getFullyQualifiedTestName());
            this.setTestObject(testObject);

            // Open Google and take a screenshot
            webDriver.navigate().to("http://www.google.com");
            final String testAppend = "testAppend";
            boolean isSuccess = SeleniumUtilities.savePageSource(webDriver, testObject, testAppend);

            // Assert screenshot was successful
            Assert.assertTrue(isSuccess, "Expected Screenshot to be successful.");
            String[] arrayOfAssociatedFiles = testObject.getArrayOfAssociatedFiles();
            Assert.assertTrue(
                    new File((arrayOfAssociatedFiles[arrayOfAssociatedFiles.length - 1])).exists(),
                    "Checking that expected file path exists");
            Assert.assertTrue(
                    new File((arrayOfAssociatedFiles[arrayOfAssociatedFiles.length - 1])).getName()
                            .contains(testAppend),
                    "Checking that appended value was added to file");
        } finally {
            webDriver.quit();
        }
    }

    /**
     * Test save page source append.
     */
    @Test(groups = TestCategories.SELENIUM)
    public void testSavePageSourceNullWebDriver() {
        WebDriver webDriver = WebDriverFactory.getDefaultBrowser();
        try {
            FileLogger fileLogger = (FileLogger) this.getTestObject().getLogger();
            SeleniumTestObject testObject = new SeleniumTestObject(webDriver, fileLogger,
                    this.getTestObject().getFullyQualifiedTestName());
            this.setTestObject(testObject);

            // Open Google and take a screenshot
            webDriver.navigate().to("http://www.google.com");
            final String testAppend = "testAppend";
            boolean isSuccess = SeleniumUtilities.savePageSource(null, testObject, testAppend);

            // Assert screenshot was not successful
            Assert.assertFalse(isSuccess, "Expected Screenshot to be successful.");
        } finally {
            webDriver.quit();
        }
    }


    /**
     * Test save page source custom directory file name.
     */
    @Test(groups = TestCategories.SELENIUM)
    public void testSavePageSourceCustomDirectoryFileName() {
        WebDriver webDriver = WebDriverFactory.getDefaultBrowser();
        try {
            FileLogger fileLogger = (FileLogger) this.getTestObject().getLogger();
            SeleniumTestObject testObject = new SeleniumTestObject(webDriver, fileLogger,
                    this.getTestObject().getFullyQualifiedTestName());
            this.setTestObject(testObject);

            // Open Google and take a screenshot
            webDriver.navigate().to("http://www.google.com");
            String dateTime = DateTimeFormatter.ofPattern(Logger.DEFAULT_DATE_TIME_FORMAT,
                    Locale.getDefault()).format(LocalDateTime.now(Clock.systemUTC()));
            String filePath = SeleniumUtilities.savePageSource(webDriver, testObject,
                    fileLogger.getDirectory(),
                    StringProcessor.safeFormatter("%s - %s", "TestCustomName", dateTime));

            // Assert File Path returned from Screenshot is the same as expected file path.
            Assert.assertEquals(filePath,
                    Paths.get(StringProcessor.safeFormatter("%s%s - %s%s",
                            fileLogger.getDirectory(), File.separator + "TestCustomName", dateTime,
                            ".txt")).normalize().toString());
            Assert.assertTrue(new File(filePath).exists(),
                    "Checking that page source file exists at expected path.");
            String[] arrayOfAssociatedFiles = testObject.getArrayOfAssociatedFiles();
            Assert.assertTrue(
                    new File((arrayOfAssociatedFiles[arrayOfAssociatedFiles.length - 1])).exists(),
                    "Checking that expected file path exists");
        } finally {
            webDriver.quit();
        }
    }

    /**
     * Test save page source console logger.
     */
    @Test(groups = TestCategories.SELENIUM)
    public void testSavePageSourceConsoleLogger() {
        WebDriver webDriver = WebDriverFactory.getDefaultBrowser();
        try {
            ConsoleLogger consoleLogger = new ConsoleLogger();
            SeleniumTestObject testObject = new SeleniumTestObject(webDriver, consoleLogger,
                    this.getTestObject().getFullyQualifiedTestName());
            this.setTestObject(testObject);

            // Open Google and take a screenshot
            webDriver.navigate().to("http://www.google.com");
            boolean isSuccess = SeleniumUtilities.savePageSource(webDriver, testObject);

            // Assert screenshot was successful
            Assert.assertTrue(isSuccess, "Expected Screenshot to be successful.");
            String[] arrayOfAssociatedFiles = testObject.getArrayOfAssociatedFiles();
            Assert.assertTrue(
                    new File((arrayOfAssociatedFiles[arrayOfAssociatedFiles.length - 1])).exists(),
                    "Checking that expected file path exists");
        } finally {
            webDriver.quit();
        }
    }

    /**
     * Test web element to web driver.
     */
    @Test(groups = TestCategories.SELENIUM)
    public void testWebElementToWebDriver() {
        WebDriver webDriver = WebDriverFactory.getDefaultBrowser();
        try {
            WebDriverListener listener = new EventHandler(this.getLogger());
            webDriver = new EventFiringDecorator(listener).decorate(webDriver);

            ConsoleLogger consoleLogger = new ConsoleLogger();
            SeleniumTestObject testObject = new SeleniumTestObject(webDriver, consoleLogger,
                    this.getTestObject().getFullyQualifiedTestName());
            this.setTestObject(testObject);

            // Open Google and take a screenshot
            webDriver.navigate().to("http://www.google.com");
            UIWaitFactory.getWaitDriver(webDriver).waitForPageLoad();
            WebElement input = webDriver.findElement(By.tagName("div"));
            WebDriver webElementToWebDriver = SeleniumUtilities.webElementToWebDriver(input);
            Assert.assertNotNull(webElementToWebDriver, "Expected extracted web driver to not be null");
        } finally {
            webDriver.quit();
        }
    }

    /**
     * Test kill driver.
     */
    @Test(groups = TestCategories.SELENIUM)
    public void testKillDriver() {
        WebDriver webDriver = WebDriverFactory.getDefaultBrowser();
        try {
            ConsoleLogger consoleLogger = new ConsoleLogger();
            SeleniumTestObject testObject = new SeleniumTestObject(webDriver, consoleLogger,
                    this.getTestObject().getFullyQualifiedTestName());
            this.setTestObject(testObject);

            // Open Google then Kill the Driver
            webDriver.navigate().to("http://www.google.com");
            SeleniumUtilities.killDriver(webDriver);

            // Assert that the Session ID is null in the Selenium Driver
            Assert.assertNull(((RemoteWebDriver) webDriver).getSessionId(),
                    "Expected Selenium Driver Session ID to be null.");
        } finally {
            webDriver.quit();
        }
    }
}