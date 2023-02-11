/*
 * Copyright 2022 (C) MAQS, All rights Reserved
 */

package io.github.openmaqs.nosql;

import io.github.openmaqs.utilities.helper.Config;
import io.github.openmaqs.utilities.helper.ConfigSection;

/**
 * The MongoDB Config class.
 */
public class MongoDBConfig {

  private MongoDBConfig() {
  }

  /**
   * The MongoDB configuration section.
   */
  private static final ConfigSection NOSQL_MAQS = ConfigSection.NOSQL_MAQS;

  /**
   * Get the client connection string.
   * @return The connection type
   */
  public static String getConnectionString() {
    return Config.getValueForSection(NOSQL_MAQS, "MongoConnectionString");
  }

  /**
   * Get the database connection string.
   * @return The database name
   */
  public static String getDatabaseString() {
    return Config.getValueForSection(NOSQL_MAQS, "MongoDatabase");
  }

  /**
   * Get the mongo collection string.
   * @return The mongo collection string
   */
  public static String getCollectionString() {
    return Config.getValueForSection(NOSQL_MAQS, "MongoCollection");
  }

  /**
   * Get the database timeout in seconds.
   * @return The timeout in seconds from the config file or default
   *     of 30 seconds when no config.xml key is found
   */
  public static int getQueryTimeout() {
    return Integer.parseInt(Config.getValueForSection(NOSQL_MAQS, "MongoTimeout", "30"));
  }
}
