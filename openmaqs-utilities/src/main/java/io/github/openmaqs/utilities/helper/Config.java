/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.utilities.helper;

import io.github.openmaqs.utilities.helper.exceptions.MaqsConfigException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.JSONConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.YAMLConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.sync.ReadWriteSynchronizer;

/**
 * The Configuration class.
 */
public class Config {

  private Config() {
    // Private constructor
  }

  /**
   * The default section GlobalMaqs.
   */
  public static final ConfigSection DEFAULT_MAQS_SECTION = ConfigSection.GLOBAL_MAQS;

  /**
   * The default config.xml file name.
   */
  public static String CONFIG_FILE = PropertyManager.get("maqs.config.location", "config.xml");

  /**
   * The configuration containing values loaded in from the config.xml file.
   */
  private static Configuration configValues;

  /**
   * The configuration containing values that were added to the configuration.
   */
  private static Configuration overrideConfig;

  /**
   * The base configs object.
   */
  private static final Configurations configs = new Configurations();

  // initialize the config object.
  static {
    try {
      CONFIG_FILE = checkForConfigFile();
      getConfigFile(CONFIG_FILE);
    } catch (ConfigurationException exception) {
      throw new MaqsConfigException(StringProcessor.safeFormatter(
          "Exception creating the configuration object from the file : %s", exception));
    } catch (FileNotFoundException notFoundException) {
      throw new MaqsConfigException(StringProcessor.safeFormatter("The file was not found: %s", notFoundException));
    }
  }

  private static String checkForConfigFile() {
    if (Paths.get("config.xml").toFile().exists()) {
      return "config.xml";
    } else if (Paths.get("appsettings.json").toFile().exists()) {
      return "appsettings.json";
    } else if (Paths.get("config.properties").toFile().exists()) {
      return "config.properties";
    } else if (Paths.get("config.yml").toFile().exists()) {
      return "config.yml";
    } else if (Paths.get("config.ini").toFile().exists()) {
      return "config.ini";
    } else {
      throw new MaqsConfigException("The file config is not supported");
    }
  }

  protected static void getConfigFile(String configName) throws ConfigurationException, FileNotFoundException {
    if (configName.contains(".xml")) {
      initializeXmlConfig();
    } else if (configName.contains(".json")) {
      initializeJsonConfig();
    } else if (configName.contains(".properties")) {
      initializePropertiesConfig();
    } else if (configName.contains(".yml")) {
      initializeYmlConfig();
    }
  }

  private static void initializeXmlConfig() throws ConfigurationException {
    configValues = configs.xml(CONFIG_FILE);
    configValues.setSynchronizer(new ReadWriteSynchronizer());

    overrideConfig = new XMLConfiguration();
    overrideConfig.setSynchronizer(new ReadWriteSynchronizer());
  }

  private static void initializeJsonConfig() throws ConfigurationException, FileNotFoundException {
//    FileBasedConfigurationBuilder<JSONConfiguration> builder = configs.fileBasedBuilder(
//           JSONConfiguration.class, "appsettings.json");

    JSONConfiguration configuration = new JSONConfiguration();
    configuration.read(new FileReader("appsettings.json"));

//    configValues = builder.configure().getConfiguration();
    configValues = configs.fileBased(JSONConfiguration.class, "appsettings.json");
    configValues.setSynchronizer(new ReadWriteSynchronizer());

    overrideConfig = new JSONConfiguration();
    overrideConfig.setSynchronizer(new ReadWriteSynchronizer());
  }

  private static void initializePropertiesConfig() throws ConfigurationException {
    configValues = configs.properties("config.properties");
    configValues.setSynchronizer(new ReadWriteSynchronizer());

    overrideConfig = new PropertiesConfiguration();
    overrideConfig.setSynchronizer(new ReadWriteSynchronizer());
  }

  private static void initializeYmlConfig() throws ConfigurationException, FileNotFoundException {
    YAMLConfiguration configuration = new YAMLConfiguration();

    try {
      configuration.read(new FileReader(new File("config.yml").getAbsolutePath()));

    } catch (FileNotFoundException e) {
      throw new FileNotFoundException(e.getMessage());
    }

    // FileBasedConfigurationBuilder<YAMLConfiguration> builder =
    // configs.fileBasedBuilder(YAMLConfiguration.class,"config.yml");
//    YAMLConfiguration configuration = configs.fileBased(YAMLConfiguration.class, "config.yml");

    configValues = new YAMLConfiguration(configuration);
    configValues.setSynchronizer(new ReadWriteSynchronizer());

    overrideConfig = new YAMLConfiguration(configuration);
    overrideConfig.setSynchronizer(new ReadWriteSynchronizer());
  }

  /**
   * Validates the app config section by ensuring required values are present.
   * @param configSection The config section to be validated
   * @param configValidation A list of strings containing the required field names
   */
  public static void validate(ConfigSection configSection, ConfigValidation configValidation) {
    // Don't run the validation if the user has decided to skip the validation
    if (getGeneralValue("SkipConfigValidation").equals("Yes")) {
      return;
    }

    if (configValidation == null) {
      throw new MaqsConfigException("The value passed in for configValidation (required fields in a config) is null");
    }

    var configSectionPassed = getSection(configSection);
    List<String> exceptions = new ArrayList<>();

    // Check if we have any required fields
    if (configValidation.getRequiredFields() != null && !configValidation.getRequiredFields().isEmpty()) {
      for (var requiredField : configValidation.getRequiredFields()) {
        if (!configSectionPassed.containsKey(requiredField)) {
          exceptions.add("Key missing: " + requiredField);
        }
      }
    }

    // Check if we have any one of required fields
    if (configValidation.getRequiredOneOfFields() != null && !configValidation.getRequiredOneOfFields().isEmpty()
        && configValidation.requiredOneOfFields.stream().noneMatch(configSectionPassed::containsKey)) {
      // We have one of fields and didn't find any of them
      exceptions.add("Need at least one of the following keys: "
              + String.join(", ", configValidation.getRequiredOneOfFields()));
    }

    if (!exceptions.isEmpty()) {
      StringBuilder message = new StringBuilder();
      for (var exception : exceptions) {
        message.append(exception);
      }

      message.append("*This check can be skipped by setting the 'SkipConfigValidation' configuration value to 'Yes'.");
      throw new MaqsConfigException(message.toString());
    }
  }

  /**
   * Gets a section from the configuration.
   *
   * @param section The desired section
   * @return A HashMap of the values in the section
   */
  public static Map<String, String> getSection(ConfigSection section) {
    return getSection(section.toString());
  }

  /**
   * Gets a section from the configuration.
   *
   * @param section The desired section
   * @return A HashMap of the values in the section
   */
  public static Map<String, String> getSection(String section) {
    HashMap<String, String> sectionValues = new HashMap<>();

    // first parse the override config
    Iterator<String> overridePaths = overrideConfig.getKeys(section);
    while (overridePaths.hasNext()) {
      String key = overridePaths.next();
      sectionValues.put(key.replaceFirst(section + "\\.", ""), overrideConfig.getString(key));
    }

    // then parse the base config, ignoring duplicates
    Iterator<String> configValuePaths = configValues.getKeys(section);
    while (configValuePaths.hasNext()) {
      String key = configValuePaths.next();
      String editedKey = key.replaceFirst(section + "\\.", "");
      sectionValues.computeIfAbsent(editedKey, k -> configValues.getString(key));
    }
    return sectionValues;
  }

  /**
   * Add dictionary of values to maqs section.
   *
   * @param configurations   Dictionary of configuration values
   * @param overrideExisting True to override existing values, False otherwise
   */
  public static void addGeneralTestSettingValues(Map<String, String> configurations, boolean overrideExisting) {
    addTestSettingValues(configurations, DEFAULT_MAQS_SECTION, overrideExisting);
  }

  /**
   * Add dictionary of values to specified section.
   *
   * @param configurations   Dictionary of configuration values
   * @param section          Section to add the value to
   * @param overrideExisting True to override existing values, False otherwise
   */
  public static void addTestSettingValues(Map<String, String> configurations, ConfigSection section,
      boolean overrideExisting) {
    addTestSettingValues(configurations, section.toString(), overrideExisting);
  }

  /**
   * Add dictionary of values to specified section.
   *
   * @param configurations   Dictionary of configuration values
   * @param section          Section to add the value to
   * @param overrideExisting True to override existing values, False otherwise
   */
  public static void addTestSettingValues(Map<String, String> configurations, String section,
      boolean overrideExisting) {
    for (Map.Entry<String, String> entry : configurations.entrySet()) {
      String sectionedKey = section + "." + entry.getKey();

      if (!overrideConfig.containsKey(sectionedKey) || overrideExisting) {
        overrideConfig.setProperty(sectionedKey, entry.getValue());
      }
    }
  }

  /**
   * Get the specified value out of the default section.
   *
   * @param key The key
   * @return The configuration value
   */
  public static String getGeneralValue(String key) {
    return getValueForSection(DEFAULT_MAQS_SECTION, key);
  }

  /**
   * Get the specified value out of the default section.
   *
   * @param key          The key
   * @param defaultValue The value to return if the key does not exist
   * @return The configuration value
   */
  public static String getGeneralValue(String key, String defaultValue) {
    return getValueForSection(DEFAULT_MAQS_SECTION, key, defaultValue);
  }

  /**
   * Get the specified value out of the specified section.
   *
   * @param section The section to search
   * @param key     The key
   * @return The configuration value
   */
  public static String getValueForSection(ConfigSection section, String key) {
    return getValueForSection(section, key, "");
  }

  /**
   * Get the specified value out of the specified section.
   *
   * @param section The section to search
   * @param key     The key
   * @return The configuration value
   */
  public static String getValueForSection(String section, String key) {
    return getValueForSection(section, key, "");
  }

  /**
   * Get the specified value out of the specified section.
   *
   * @param section      The section to search
   * @param key          The key
   * @param defaultValue The value to return if the key is not found
   * @return The configuration value
   */
  public static String getValueForSection(ConfigSection section, String key, String defaultValue) {
    return getValueForSection(section.toString(), key, defaultValue);
  }

  /**
   * Get the specified value out of the specified section.
   *
   * @param section      The section to search
   * @param key          The key
   * @param defaultValue The value to return if the key is not found
   * @return The configuration value
   */
  public static String getValueForSection(String section, String key, String defaultValue) {
    String keyWithSection = section + "." + key;
    return getValue(keyWithSection, defaultValue);
  }

  /**
   * Get the configuration value for a specific key. Does not assume a section.
   *
   * @param key The key
   * @return The configuration value - Returns the empty string if the key is not
   *         found
   */
  public static String getValue(String key) {
    String retVal = overrideConfig.getString(key, "");
    return retVal.isEmpty() ? configValues.getString(key, "") : retVal;
  }

  /**
   * Get the configuration value for a specific key. Does not assume a section.
   *
   * @param key          The key
   * @param defaultValue Value to return if the key does not exist
   * @return The configuration value - Returns the default string if the key is
   *         not found
   */
  public static String getValue(String key, String defaultValue) {
    String retVal = getValue(key);
    return retVal.isEmpty() ? defaultValue : retVal;
  }

  /**
   * Check the config for a specific key. Does not assume a section.
   *
   * @param key The key
   * @return True if the key exists, false otherwise
   */
  public static boolean doesKeyExist(String key) {
    return overrideConfig.containsKey(key) || configValues.containsKey(key);
  }

  /**
   * Check the config for a specific key. Searches the specified section.
   *
   * @param key     The key
   * @param section The specified section
   * @return True if the key exists, false otherwise
   */
  public static boolean doesKeyExist(String key, ConfigSection section) {
    return doesKeyExist(key, section.toString());
  }

  /**
   * Check the config for a specific key. Searches the specified section.
   *
   * @param key     The key
   * @param section The specified section
   * @return True if the key exists, false otherwise
   */
  public static boolean doesKeyExist(String key, String section) {
    String keyWithSection = section + "." + key;
    return doesKeyExist(keyWithSection);
  }

  /**
   * Check the config for a specific key. Searches the default section.
   *
   * @param key The key
   * @return True if the key exists, false otherwise
   */
  public static boolean doesGeneralKeyExist(String key) {
    return doesKeyExist(key, DEFAULT_MAQS_SECTION);
  }
}
