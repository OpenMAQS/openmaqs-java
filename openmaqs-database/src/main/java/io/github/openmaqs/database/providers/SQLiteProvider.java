/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.database.providers;

import io.github.openmaqs.database.constants.DataProviderType;
import javax.sql.DataSource;
import org.sqlite.SQLiteDataSource;

/**
 * Class SQLiteProvider.
 */
public class SQLiteProvider implements IDataSourceProvider {

  /**
   * Field dbUrl.
   */
  private final String dbUrl;
  /**
   * Field dataProviderType.
   */
  private static final DataProviderType dataProviderType = DataProviderType.SQLITE;

  /**
   * Constructor SQLiteProvider creates a new SQLiteProvider instance.
   *
   * @param dbUrl of type String
   */
  public SQLiteProvider(String dbUrl) {
    this.dbUrl = dbUrl;
  }

  @Override
  public DataSource getDataSource() {
    SQLiteDataSource dataSource = new SQLiteDataSource();
    dataSource.setUrl(dbUrl);
    return dataSource;
  }

  @Override
  public String getDialect() {
    return dataProviderType.getDialectString();
  }
}
