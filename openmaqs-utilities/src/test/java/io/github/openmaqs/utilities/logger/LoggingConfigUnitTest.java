/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.utilities.logger;

import io.github.openmaqs.utilities.helper.Config;
import io.github.openmaqs.utilities.helper.StringProcessor;
import io.github.openmaqs.utilities.helper.TestCategories;
import io.github.openmaqs.utilities.helper.exceptions.MaqsLoggingConfigException;
import io.github.openmaqs.utilities.logging.ConsoleLogger;
import io.github.openmaqs.utilities.logging.FileLogger;
import io.github.openmaqs.utilities.logging.HtmlFileLogger;
import io.github.openmaqs.utilities.logging.Logger;
import io.github.openmaqs.utilities.logging.LoggingConfig;
import io.github.openmaqs.utilities.logging.LoggingEnabled;
import io.github.openmaqs.utilities.logging.MessageType;
import java.io.File;
import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The Logging Configuration unit test class.
 * Tests running in serial.
 */
@Test(singleThreaded = true)
public class LoggingConfigUnitTest {

    /**
     * Test getting Logging Enabled Setting. Override Config to 'YES'
     */
    @Test(groups = TestCategories.UTILITIES)
    public void getLoggingEnabledSettingTest() {
        HashMap<String, String> newValueMap = new HashMap<>();
        newValueMap.put("Log", "YES");
        Config.addGeneralTestSettingValues(newValueMap, true);
        Assert.assertEquals(LoggingConfig.getLoggingEnabledSetting(), LoggingEnabled.YES,
                "Expected Logging Enabled Setting YES.");
    }

    /**
     * Test getting Logging Enabled Setting. Override Config to 'ONFAIL'
     */
    @Test(groups = TestCategories.UTILITIES)
    public void getLoggingEnabledOnFailSettingTest() {
        HashMap<String, String> newValueMap = new HashMap<>();
        newValueMap.put("Log", "ONFAIL");
        Config.addGeneralTestSettingValues(newValueMap, true);
        Assert.assertEquals(LoggingConfig.getLoggingEnabledSetting(), LoggingEnabled.ONFAIL,
                "Expected Logging Enabled Setting ONFAIL.");
    }

    /**
     * Test getting Logging Enabled Setting. Override Config to 'NO'
     */
    @Test(groups = TestCategories.UTILITIES)
    public void getLoggingDisabledSettingTest() {
        HashMap<String, String> newValueMap = new HashMap<>();
        newValueMap.put("Log", "NO");
        Config.addGeneralTestSettingValues(newValueMap, true);
        Assert.assertEquals(LoggingConfig.getLoggingEnabledSetting(), LoggingEnabled.NO,
                "Expected Logging Enabled Setting NO.");
    }

    /**
     * Test getting Logging Enabled Setting with an Illegal Argument Override Config
     * to 'INVALIDVALUE' - Expect IllegalArgumentException
     */
    @Test(expectedExceptions = MaqsLoggingConfigException.class)
    public void getCreateLogInvalidArgumentTest() {
        HashMap<String, String> newValueMap = new HashMap<>();
        newValueMap.put("Log", "INVALIDVALUE");
        Config.addGeneralTestSettingValues(newValueMap, true);
        LoggingConfig.getLoggingEnabledSetting();

        newValueMap.clear();
        newValueMap.put("Log", "OnFail");
        Config.addGeneralTestSettingValues(newValueMap, true);
    }

    /**
     * Test getting Logging Level Setting. Override Config to 'VERBOSE'
     */
    @Test(groups = TestCategories.UTILITIES)
    public void getLoggingLevelVerboseSettingTest() {
        HashMap<String, String> newValueMap = new HashMap<>();
        newValueMap.put("LogLevel", "VERBOSE");
        Config.addGeneralTestSettingValues(newValueMap, true);
        Assert.assertEquals(LoggingConfig.getLoggingLevelSetting(), MessageType.VERBOSE,
                "Expected Logging Level Setting VERBOSE.");
    }

    /**
     * Test getting Logging Level Setting. Override Config to 'INFORMATION'
     */
    @Test(groups = TestCategories.UTILITIES)
    public void getLoggingLevelInformationSettingTest() {
        HashMap<String, String> newValueMap = new HashMap<>();
        newValueMap.put("LogLevel", "INFORMATION");
        Config.addGeneralTestSettingValues(newValueMap, true);
        Assert.assertEquals(LoggingConfig.getLoggingLevelSetting(), MessageType.INFORMATION,
                "Expected Logging Level Setting INFORMATION.");
    }

    /**
     * Test getting Logging Level Setting. Override Config to 'INFORMATION'
     */
    @Test(groups = TestCategories.UTILITIES)
    public void getLoggingLevelActionSettingTest() {
        HashMap<String, String> newValueMap = new HashMap<>();
        newValueMap.put("LogLevel", "ACTION");
        Config.addGeneralTestSettingValues(newValueMap, true);
        Assert.assertEquals(LoggingConfig.getLoggingLevelSetting(), MessageType.ACTION,
            "Expected Logging Level Setting ACTION.");
    }

    /**
     * Test getting Logging Level Setting. Override Config to 'GENERIC'
     */
    @Test(groups = TestCategories.UTILITIES)
    public void getLoggingLevelGenericSettingTest() {
        HashMap<String, String> newValueMap = new HashMap<>();
        newValueMap.put("LogLevel", "GENERIC");
        Config.addGeneralTestSettingValues(newValueMap, true);
        Assert.assertEquals( LoggingConfig.getLoggingLevelSetting(), MessageType.GENERIC,
                "Expected Logging Level Setting GENERIC.");
    }

    /**
     * Test getting Logging Level Setting. Override Config to 'SUCCESS'
     */
    @Test(groups = TestCategories.UTILITIES)
    public void getLoggingLevelSuccessSettingTest() {
        HashMap<String, String> newValueMap = new HashMap<>();
        newValueMap.put("LogLevel", "SUCCESS");
        Config.addGeneralTestSettingValues(newValueMap, true);
        Assert.assertEquals(LoggingConfig.getLoggingLevelSetting(), MessageType.SUCCESS,
                "Expected Logging Level Setting SUCCESS.");
    }

    /**
     * Test getting Logging Level Setting. Override Config to 'WARNING'
     */
    @Test(groups = TestCategories.UTILITIES)
    public void getLoggingLevelWarningSettingTest() {
        HashMap<String, String> newValueMap = new HashMap<>();
        newValueMap.put("LogLevel", "WARNING");
        Config.addGeneralTestSettingValues(newValueMap, true);
        Assert.assertEquals(LoggingConfig.getLoggingLevelSetting(), MessageType.WARNING,
                "Expected Logging Level Setting WARNING.");
    }

    /**
     * Test getting Logging Level Setting. Override Config to 'ERROR'
     */
    @Test(groups = TestCategories.UTILITIES)
    public void getLoggingLevelErrorSettingTest() {
        HashMap<String, String> newValueMap = new HashMap<>();
        newValueMap.put("LogLevel", "ERROR");
        Config.addGeneralTestSettingValues(newValueMap, true);
        Assert.assertEquals(LoggingConfig.getLoggingLevelSetting(), MessageType.ERROR,
                "Expected Logging Level Setting ERROR.");
    }

    /**
     * Test getting Logging Level Setting. Override Config to 'SUSPENDED'
     */
    @Test(groups = TestCategories.UTILITIES)
    public void getLoggingLevelSuspendedSettingTest() {
        HashMap<String, String> newValueMap = new HashMap<>();
        newValueMap.put("LogLevel", "SUSPENDED");
        Config.addGeneralTestSettingValues(newValueMap, true);
        Assert.assertEquals(LoggingConfig.getLoggingLevelSetting(), MessageType.SUSPENDED,
                "Expected Logging Level Setting SUSPENDED.");
    }

    /**
     * Test getting Logging Level Setting with Illegal Argument. Override Config to
     * 'INVALIDVALUE' - Expect IllegalArgumentException
     */
    @Test(groups = TestCategories.UTILITIES, expectedExceptions = MaqsLoggingConfigException.class)
    public void getLoggingLevelInvalidArgumentTest() {
        HashMap<String, String> newValueMap = new HashMap<>();
        newValueMap.put("LogLevel", "INVALIDVALUE");
        Config.addGeneralTestSettingValues(newValueMap, true);
        LoggingConfig.getLoggingLevelSetting();

        newValueMap.clear();
        newValueMap.put("LogLevel", "OnFail");
        Config.addGeneralTestSettingValues(newValueMap, true);
    }

    /**
     * Test getting File Logger. Override Config LogType to 'TXT' which creates
     * FileLogger.
     */
    @Test(groups = TestCategories.UTILITIES)
    public void getTextFileLoggerTest() {
        HashMap<String, String> newValueMap = new HashMap<>();
        newValueMap.put("LogType", "TXT");
        newValueMap.put("Log", "YES");
        Config.addGeneralTestSettingValues(newValueMap, true);
        String fileName = "TestLog.txt";
        Logger logger = LoggingConfig.getLogger(fileName);
        Assert.assertTrue(logger instanceof FileLogger, "Expected Logger to be of Type FileLogger.");
    }

    /**
     * Test getting File Logger. Override Config LogType to 'TXT' which creates
     * FileLogger.
     */
    @Test(groups = TestCategories.UTILITIES)
    public void getHTMLFileLoggerTest() {
        HashMap<String, String> newValueMap = new HashMap<>();
        newValueMap.put("LogType", "HTML");
        newValueMap.put("Log", "YES");
        Config.addGeneralTestSettingValues(newValueMap, true);
        String fileName = "TestLog.html";
        Logger logger = LoggingConfig.getLogger(fileName);
        Assert.assertTrue(logger instanceof HtmlFileLogger, "Expected Logger to be of Type HtmlFileLogger.");
    }

    /**
     * Test getting File Logger. Override Config LogType to 'TXT' which creates
     * FileLogger.
     */
    @Test(groups = TestCategories.UTILITIES, expectedExceptions = MaqsLoggingConfigException.class)
    public void getFileLoggerInvalidLogTypeTest() {
        HashMap<String, String> newValueMap = new HashMap<>();
        newValueMap.put("LogType", "XML");
        newValueMap.put("Log", "YES");
        Config.addGeneralTestSettingValues(newValueMap, true);
        String fileName = "TestLog.txt";
        LoggingConfig.getLogger(fileName);
    }

    /**
     * Test getting File Logger. Override Config LogType to 'CONSOLE' which creates
     * ConsoleLogger.
     */
    @Test(groups = TestCategories.UTILITIES)
    public void getConsoleLoggerTest() {
        HashMap<String, String> newValueMap = new HashMap<>();
        newValueMap.put("LogType", "CONSOLE");
        newValueMap.put("Log", "YES");
        Config.addGeneralTestSettingValues(newValueMap, true);
        String fileName = "TestLog.txt";
        Logger logger = LoggingConfig.getLogger(fileName);
        Assert.assertTrue(logger instanceof ConsoleLogger, "Expected Logger to be of Type ConsoleLogger.");
    }

    /**
     * Test getting File Logger. Override Config Log to 'NO' which creates
     * ConsoleLogger by default.
     */
    @Test(groups = TestCategories.UTILITIES)
    public void getConsoleLoggerLoggingDisabledTest() {
        HashMap<String, String> newValueMap = new HashMap<>();
        newValueMap.put("Log", "NO");
        Config.addGeneralTestSettingValues(newValueMap, true);
        String fileName = "TestLog.txt";
        Logger logger = LoggingConfig.getLogger(fileName);
        Assert.assertTrue(logger instanceof ConsoleLogger, "Expected Logger to be of Type ConsoleLogger.");
    }

    /**
     * Test getting Log Directory.
     * Config value not defined - Compare to Default Path.
     */
    @Test(groups = TestCategories.UTILITIES)
    public void getLogDirectoryTest() {
        String defaultPath = new File("").getAbsolutePath().concat(
            File.separator + "target" + File.separator + "logs");
        Assert.assertEquals(defaultPath, LoggingConfig.getLogDirectory(),
        StringProcessor.safeFormatter( "Expected Default Path '{0)'.", defaultPath));
    }
}
